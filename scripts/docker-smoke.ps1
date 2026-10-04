param([switch]$SkipBuild, [switch]$Browser)
. "$PSScriptRoot/common.ps1"
$temporary = Join-Path ([IO.Path]::GetTempPath()) ('mdedu-smoke-' + [guid]::NewGuid().ToString('N') + '.env')
$project = 'mdedu-smoke-' + (Get-Date -Format 'yyyyMMddHHmmss')
$compose = @('compose','--env-file',$temporary,'-p',$project,'-f',(Join-Path $ProjectRoot 'docker-compose.app.yml'))
try {
    if (Get-NetTCPConnection -LocalPort 8088 -State Listen -ErrorAction SilentlyContinue) { throw 'Port 8088 already in use.' }
    $settings=Read-TestSettings
    $lines=@("META_UI_USERNAME=$($settings['META_UI_USERNAME'])","META_UI_PASSWORD=$($settings['META_UI_PASSWORD'])","DB_PASSWORD=$($settings['DB_PASSWORD'])",'LLM_PROVIDER=DISABLED','SESSION_COOKIE_SECURE=false','CORS_ALLOWED_ORIGINS=http://127.0.0.1:8088','GEMINI_API_KEY=','LLM_API_KEY=')
    [IO.File]::WriteAllLines($temporary,$lines,[Text.UTF8Encoding]::new($false))
    if (-not $SkipBuild) {
        & docker buildx inspect mdedu-quality *> $null
        if ($LASTEXITCODE -ne 0) { Invoke-Checked docker @('buildx','create','--name','mdedu-quality','--driver','docker-container','--driver-opt','memory=1536m,memory-swap=1536m,cpu-quota=200000,cpu-period=100000,default-load=true,restart-policy=no','--bootstrap') }
        foreach ($service in @('backend','frontend')) { Invoke-Checked docker ($compose + @('build','--builder','mdedu-quality',$service)) }
        Invoke-Checked docker @('buildx','stop','mdedu-quality')
    }
    Invoke-Checked docker ($compose + @('up','-d','--wait','--wait-timeout','180'))
    foreach ($image in @('mdedu-backend:local','mdedu-frontend:local')) {
        $runtimeUser = & docker image inspect --format '{{.Config.User}}' $image
        if ($LASTEXITCODE -ne 0 -or [string]::IsNullOrWhiteSpace($runtimeUser) -or $runtimeUser -match '^(root|0)(:|$)') { throw "Runtime user is not non-root: $image" }
        Invoke-Checked docker @('run','--rm','--entrypoint','sh',$image,'-c','test ! -e /app/.env && test ! -e /workspace && test ! -e /usr/share/nginx/html/.env')
    }
    Wait-Ready 'http://127.0.0.1:8088/health'
    Wait-Ready 'http://127.0.0.1:8088/actuator/health/readiness'
    $spa=Invoke-WebRequest -UseBasicParsing 'http://127.0.0.1:8088/docente/login'
    if ($spa.Content -notmatch '<div id="root">') { throw 'SPA fallback failed.' }
    if (-not $spa.Headers['Content-Security-Policy']) { throw 'CSP missing.' }
    $api='http://127.0.0.1:8088/api'
    $csrf=Invoke-RestMethod "$api/teacher/csrf" -SessionVariable teacherSession
    $headers=@{};$headers[$csrf.headerName]=$csrf.token
    $null=Invoke-WebRequest -UseBasicParsing "$api/teacher/login" -Method Post -WebSession $teacherSession -Headers $headers -Body @{username=$settings['META_UI_USERNAME'];password=$settings['META_UI_PASSWORD']}
    $csrf=Invoke-RestMethod "$api/teacher/csrf" -WebSession $teacherSession
    $headers=@{};$headers[$csrf.headerName]=$csrf.token
    $issued=Invoke-RestMethod "$api/teacher/participants" -Method Post -WebSession $teacherSession -Headers $headers
    $student=@{id=$issued.participant.studentId}
    $csrf=Invoke-RestMethod "$api/auth/student/csrf" -SessionVariable studentSession
    $studentHeaders=@{};$studentHeaders[$csrf.headerName]=$csrf.token
    $null=Invoke-RestMethod "$api/auth/student/login" -Method Post -WebSession $studentSession -Headers $studentHeaders -ContentType 'application/json' -Body (@{studentCode=$issued.participant.studentCode;password=$issued.temporaryPassword}|ConvertTo-Json)
    $issued=$null
    $csrf=Invoke-RestMethod "$api/auth/student/csrf" -WebSession $studentSession
    $studentHeaders=@{};$studentHeaders[$csrf.headerName]=$csrf.token
    foreach ($level in @('SEQ-01','VAR-01','COND-01','LOOP-01')) {
        $fixture=if($level -eq 'LOOP-01'){'LOOP-01_MANUAL'}else{$level}
        $program=Get-Content -Raw (Join-Path $ProjectRoot "backend/src/test/resources/challenges/$fixture.json") | ConvertFrom-Json
        $body=@{studentId=$student.id;levelId=$level;program=$program;hintCount=0;resolutionTimeMs=1000}|ConvertTo-Json -Depth 100
        $attempt=Invoke-RestMethod "$api/attempts" -Method Post -WebSession $studentSession -Headers $studentHeaders -ContentType 'application/json' -Body $body
    }
    $feedback=Invoke-RestMethod "$api/feedback/generate" -Method Post -WebSession $studentSession -Headers $studentHeaders -ContentType 'application/json' -Body (@{studentId=$student.id;attemptId=$attempt.attemptId}|ConvertTo-Json)
    if($feedback.source -ne 'FALLBACK' -or $feedback.llmUsed){throw 'DISABLED fallback failed.'}
    $null=Invoke-RestMethod "$api/students/$($student.id)/attempts/$($attempt.attemptId)/ui-configuration" -WebSession $studentSession
    $trace=Invoke-RestMethod "$api/students/$($student.id)/attempts/$($attempt.attemptId)/timeline" -WebSession $teacherSession
    if($trace.traceStatus -ne 'COMPLETE'){throw 'Timeline incomplete.'}
    if ($Browser) {
        $previousBase=$env:PLAYWRIGHT_BASE_URL; $previousApi=$env:VITE_API_BASE_URL
        try {
            $env:PLAYWRIGHT_BASE_URL='http://127.0.0.1:8088'; $env:VITE_API_BASE_URL=$env:PLAYWRIGHT_BASE_URL
            Push-Location (Join-Path $ProjectRoot 'frontend')
            try { Invoke-Checked npx.cmd @('playwright','test','adventure.spec.ts','quality.spec.ts','student-auth.spec.ts','laboratory.spec.ts','--workers=1') } finally { Pop-Location }
        } finally { $env:PLAYWRIGHT_BASE_URL=$previousBase; $env:VITE_API_BASE_URL=$previousApi }
    }
    Invoke-Checked docker ($compose+@('stop','database'))
    $downStatus=0
    try { $null=Invoke-WebRequest -UseBasicParsing 'http://127.0.0.1:8088/actuator/health/readiness' -TimeoutSec 15 } catch {
        if ($_.Exception.Response) { $downStatus=[int]$_.Exception.Response.StatusCode }
    }
    if($downStatus -ne 503){throw 'Readiness must report unavailable when DB is down.'}
    Wait-Ready 'http://127.0.0.1:8088/actuator/health/liveness'
    Invoke-Checked docker ($compose+@('up','-d','--wait','database'))
    Wait-Ready 'http://127.0.0.1:8088/actuator/health/readiness'
    Invoke-Checked docker ($compose+@('ps'))
    Write-Host 'Docker full-stack smoke PASS; fresh database, four activities, fallback, UI, authenticated timeline.'
} catch { Write-Error $_; exit 1 } finally {
    & docker buildx stop mdedu-quality *> $null
    try { Invoke-Checked docker ($compose+@('down','--timeout','20')) } finally {
        if(Test-Path -LiteralPath $temporary){Remove-Item -LiteralPath $temporary}
    }
    Write-Host "Smoke database volume preserved for project $project. No existing volume removed."
}
