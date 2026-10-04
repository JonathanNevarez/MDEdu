param([switch]$SkipBuild, [switch]$SkipSuites, [string]$BrowserFilter='')
. "$PSScriptRoot/common.ps1"
$taskId='mdedu-production-test-'+(Get-Date -Format 'yyyyMMddHHmmss')
$taskTemp=Join-Path ([IO.Path]::GetTempPath()) $taskId
$db="$taskId-db"; $app="$taskId-app"; $volume="$taskId-data"
$proxy=$null; $oldEnv=@{}
try {
    foreach($port in @(8089,8443)){if(Get-NetTCPConnection -State Listen -LocalPort $port -ErrorAction SilentlyContinue){throw "Test port $port is busy."}}
    New-Item -ItemType Directory -Path $taskTemp | Out-Null
    New-Item -ItemType Directory -Force -Path $QualityDirectory | Out-Null
    $settings=Read-TestSettings
    [IO.File]::WriteAllLines((Join-Path $taskTemp 'db.env'),@('POSTGRES_DB=deployment_test','POSTGRES_USER=deployment_test',"POSTGRES_PASSWORD=$($settings['DB_PASSWORD'])"))
    [IO.File]::WriteAllLines((Join-Path $taskTemp 'app.env'),@(
        'SPRING_PROFILES_ACTIVE=prod,render','PORT=10000','RENDER_EXTERNAL_URL=https://127.0.0.1:8443',
        "SPRING_DATASOURCE_URL=jdbc:postgresql://${db}:5432/deployment_test?sslmode=require",'SPRING_DATASOURCE_USERNAME=deployment_test',
        "SPRING_DATASOURCE_PASSWORD=$($settings['DB_PASSWORD'])","META_UI_USERNAME=$($settings['META_UI_USERNAME'])",
        "META_UI_PASSWORD=$($settings['META_UI_PASSWORD'])",'LLM_PROVIDER=DISABLED'))
    if(-not $SkipSuites){
        $oldEnv['MAVEN_OPTS']=$env:MAVEN_OPTS;$env:MAVEN_OPTS='-Xmx384m'
        $oldEnv['NODE_OPTIONS']=$env:NODE_OPTIONS;$env:NODE_OPTIONS='--max-old-space-size=384'
        Push-Location $ProjectRoot
        try {
            Invoke-Checked ./backend/mvnw.cmd @('-f','mde/pom.xml','--batch-mode','--no-transfer-progress','clean','install')
            Invoke-Checked ./backend/mvnw.cmd @('-f','backend/pom.xml','--batch-mode','--no-transfer-progress','verify')
        } finally {Pop-Location}
        Push-Location (Join-Path $ProjectRoot 'frontend')
        try {Invoke-Checked npm.cmd @('ci');Invoke-Checked npm.cmd @('test','--','--maxWorkers=1');Invoke-Checked npm.cmd @('run','build');Invoke-Checked npm.cmd @('audit')}
        finally {Pop-Location}
    }
    if(-not $SkipBuild){
        & docker buildx inspect mdedu-quality *> $null
        if($LASTEXITCODE -ne 0){Invoke-Checked docker @('buildx','create','--name','mdedu-quality','--driver','docker-container','--driver-opt','memory=1536m,memory-swap=1536m,cpu-quota=200000,cpu-period=100000,default-load=true,restart-policy=no','--bootstrap')}
        Invoke-Checked docker @('buildx','build','--builder','mdedu-quality','--load','-f',(Join-Path $ProjectRoot 'Dockerfile.render'),'-t','mdedu-render:test',$ProjectRoot)
        Invoke-Checked docker @('buildx','stop','mdedu-quality')
    }
    $runtimeUser=& docker image inspect --format '{{.Config.User}}' mdedu-render:test
    if($LASTEXITCODE -ne 0 -or $runtimeUser -ne '10001:10001'){throw 'Expected non-root runtime.'}
    Invoke-Checked docker @('run','--rm','--entrypoint','sh','mdedu-render:test','-c','test ! -e /workspace && test ! -e /app/.env && test ! -e /root/.m2')
    Invoke-Checked docker @('network','create',$taskId)
    Invoke-Checked docker @('volume','create',$volume)
    # Disposable PostgreSQL uses its image-provided test certificate, never a real server key.
    Invoke-Checked docker @('run','-d','--name',$db,'--network',$taskId,'--memory','256m','--env-file',(Join-Path $taskTemp 'db.env'),'-v',"${volume}:/var/lib/postgresql/data",'--entrypoint','sh','postgres:17-bookworm','-c','cp /etc/ssl/private/ssl-cert-snakeoil.key /tmp/test.key && chown postgres:postgres /tmp/test.key && chmod 600 /tmp/test.key && exec docker-entrypoint.sh postgres -c ssl=on -c ssl_cert_file=/etc/ssl/certs/ssl-cert-snakeoil.pem -c ssl_key_file=/tmp/test.key')
    Invoke-Checked docker @('cp',"${db}:/etc/ssl/certs/ssl-cert-snakeoil.pem",(Join-Path $taskTemp 'cert.pem'))
    Invoke-Checked docker @('cp',"${db}:/etc/ssl/private/ssl-cert-snakeoil.key",(Join-Path $taskTemp 'key.pem'))
    Invoke-Checked docker @('run','-d','--name',$app,'--network',$taskId,'--memory','512m','--memory-swap','512m','--cpus','1','--env-file',(Join-Path $taskTemp 'app.env'),'-p','127.0.0.1:8089:10000','mdedu-render:test')
    Wait-Ready 'http://127.0.0.1:8089/actuator/health/readiness'
    $proxy=Start-Process node -ArgumentList @(('"'+(Join-Path $PSScriptRoot 'production-test-proxy.cjs')+'"'),('"'+(Join-Path $taskTemp 'cert.pem')+'"'),('"'+(Join-Path $taskTemp 'key.pem')+'"')) -WindowStyle Hidden -PassThru -RedirectStandardOutput (Join-Path $taskTemp 'proxy.log') -RedirectStandardError (Join-Path $taskTemp 'proxy-error.log')
    foreach($key in @('PLAYWRIGHT_BASE_URL','VITE_API_BASE_URL','PRODUCTION_SMOKE','PRODUCTION_TEST_CONTAINER','GEMINI_MOCK_E2E')){$oldEnv[$key]=[Environment]::GetEnvironmentVariable($key,'Process')}
    $env:PLAYWRIGHT_BASE_URL='https://127.0.0.1:8443';$env:VITE_API_BASE_URL=$env:PLAYWRIGHT_BASE_URL
    $env:PRODUCTION_SMOKE='true';$env:PRODUCTION_TEST_CONTAINER=$app;$env:GEMINI_MOCK_E2E='false'
    Push-Location (Join-Path $ProjectRoot 'frontend')
    try {
        $browserArguments=@('playwright','test','--workers=1')
        if($BrowserFilter){$browserArguments+=@($BrowserFilter)}
        Invoke-Checked npx.cmd $browserArguments
    } finally {Pop-Location}
    Invoke-Checked docker @('stats','--no-stream','--format','{{.Name}} {{.MemUsage}}',$app)
    $oom=& docker inspect --format '{{.State.OOMKilled}}' $app
    if($oom -ne 'false'){throw 'Production test exceeded memory limit.'}
    $migrations=& docker exec $db psql -U deployment_test -d deployment_test -Atc 'select count(*) from flyway_schema_history where success;'
    if($LASTEXITCODE -ne 0 -or $migrations.Trim() -ne '9'){throw 'Fresh database must contain all nine successful migrations.'}
    $tls=& docker exec $db psql -U deployment_test -d deployment_test -Atc 'select count(*) from pg_stat_ssl where ssl and pid in (select pid from pg_stat_activity where usename=''deployment_test'');'
    if($LASTEXITCODE -ne 0 -or [int]$tls -lt 1){throw 'Expected active TLS database connections.'}
    Write-Host "Flyway migrations: $migrations; active SSL connections: $tls"
    $pidName=& docker exec $app cat /proc/1/comm
    if($LASTEXITCODE -ne 0 -or $pidName.Trim() -ne 'java'){throw 'Expected Java as PID 1.'}
    Invoke-Checked docker @('stop',$db)
    $status=0
    try{$null=Invoke-WebRequest -UseBasicParsing 'http://127.0.0.1:8089/actuator/health/readiness' -TimeoutSec 15}catch{if($_.Exception.Response){$status=[int]$_.Exception.Response.StatusCode}}
    if($status -ne 503){throw 'Readiness must fail while DB is stopped.'}
    Wait-Ready 'http://127.0.0.1:8089/actuator/health/liveness'
    Invoke-Checked docker @('start',$db)
    Wait-Ready 'http://127.0.0.1:8089/actuator/health/readiness'
    Write-Host 'Production smoke PASS: single Java process, HTTPS proxy, SSL PostgreSQL, fresh Flyway, 512 MiB, restart persistence and DB recovery.'
} finally {
    $cleanupPreference=$ErrorActionPreference
    $ErrorActionPreference='Continue'
    foreach($key in $oldEnv.Keys){[Environment]::SetEnvironmentVariable($key,$oldEnv[$key],'Process')}
    if($proxy -and -not $proxy.HasExited){Stop-Process -Id $proxy.Id}
    & docker logs $app *> (Join-Path $QualityDirectory "$taskId.log")
    & docker rm -f $app $db 2>$null
    & docker network rm $taskId 2>$null
    & docker buildx stop mdedu-quality *> $null
    # Delete only individual files created in this unique temporary directory.
    foreach($name in @('db.env','app.env','cert.pem','key.pem','proxy.log','proxy-error.log')){
        $file=Join-Path $taskTemp $name;if(Test-Path -LiteralPath $file){Remove-Item -LiteralPath $file}
    }
    if(Test-Path -LiteralPath $taskTemp){Remove-Item -LiteralPath $taskTemp}
    Write-Host "Test volume preserved: $volume. Existing project data untouched."
    $ErrorActionPreference=$cleanupPreference
}
