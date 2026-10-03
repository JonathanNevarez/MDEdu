param([switch]$BackendOnly, [switch]$TestCredentials, [switch]$GeminiMock)
. "$PSScriptRoot/common.ps1"
$savedEnvironment = @{}
$processes = @()
function Set-TemporaryEnvironment([string]$Name, [string]$Value) {
    if (-not $savedEnvironment.ContainsKey($Name)) { $savedEnvironment[$Name] = [Environment]::GetEnvironmentVariable($Name, 'Process') }
    [Environment]::SetEnvironmentVariable($Name, $Value, 'Process')
}
function Save-Process($Process) {
    $script:processes += @{ id=$Process.Id; name=$Process.ProcessName; started=$Process.StartTime.ToUniversalTime().ToString('o') }
    ConvertTo-Json -InputObject @($script:processes) | Set-Content -LiteralPath (Join-Path $QualityDirectory 'processes.json')
}
try {
    if ($GeminiMock -and -not $TestCredentials) { throw 'GeminiMock requires TestCredentials.' }
    if (Test-Path (Join-Path $QualityDirectory 'processes.json')) { throw 'Run stop-dev.ps1 before starting another managed session.' }
    foreach ($port in @(8080,5173)) {
        if (Get-NetTCPConnection -State Listen -LocalPort $port -ErrorAction SilentlyContinue) { throw "Port $port is already in use." }
    }
    if (-not (Test-Path (Join-Path $ProjectRoot '.env'))) { throw 'Configure .env from .env.example first.' }
    New-Item -ItemType Directory -Force -Path $QualityDirectory | Out-Null
    Push-Location $ProjectRoot
    try { Invoke-Checked docker @('compose','up','-d','--wait','postgres') } finally { Pop-Location }
    Set-TemporaryEnvironment LLM_PROVIDER DISABLED
    Set-TemporaryEnvironment CORS_ALLOWED_ORIGINS 'http://127.0.0.1:5173,http://127.0.0.1:4173'
    if ($TestCredentials) {
        $settings = Read-TestSettings
        foreach ($key in @('META_UI_USERNAME','META_UI_PASSWORD')) { Set-TemporaryEnvironment $key $settings[$key] }
    }
    $backend = Join-Path $ProjectRoot 'backend'
    $arguments = @('-Xmx384m','-jar','target/adaptativa-backend-0.1.0-SNAPSHOT.jar')
    if ($GeminiMock) {
        Set-TemporaryEnvironment LLM_PROVIDER GEMINI
        Set-TemporaryEnvironment GEMINI_API_KEY $settings['GEMINI_API_KEY']
        Set-TemporaryEnvironment GEMINI_MODEL $settings['GEMINI_MODEL']
        Set-TemporaryEnvironment LLM_RATE_LIMIT_REQUESTS '1'
        Push-Location $backend
        try { Invoke-Checked '.\mvnw.cmd' @('dependency:build-classpath','-Dmdep.includeScope=test','-Dmdep.outputFile=target/quality-classpath.txt') } finally { Pop-Location }
        $classpath = 'target/classes;target/test-classes;' + (Get-Content -Raw (Join-Path $backend 'target/quality-classpath.txt')).Trim()
        [IO.File]::WriteAllText((Join-Path $backend 'target/quality-runtime.args'), '-Xmx384m -cp "' + $classpath.Replace('\','/') + '" com.project.llm.GeminiMockApplication', [Text.UTF8Encoding]::new($false))
        $arguments = @('@target/quality-runtime.args')
    } elseif (-not (Test-Path (Join-Path $backend $arguments[2]))) { throw 'Build the backend with Maven verify first.' }
    $java = (Get-Command java).Source
    $process = Start-Process $java -ArgumentList $arguments -WorkingDirectory $backend -WindowStyle Hidden -PassThru -RedirectStandardOutput (Join-Path $QualityDirectory 'backend.log') -RedirectStandardError (Join-Path $QualityDirectory 'backend-error.log')
    Save-Process $process
    Wait-Ready 'http://127.0.0.1:8080/actuator/health/readiness'
    if (-not $BackendOnly) {
        $vite = Join-Path $ProjectRoot 'frontend/node_modules/vite/bin/vite.js'
        $process = Start-Process (Get-Command node).Source -ArgumentList @('--max-old-space-size=384', ('"' + $vite + '"'),'--host','127.0.0.1','--port','5173','--strictPort') -WorkingDirectory (Join-Path $ProjectRoot 'frontend') -WindowStyle Hidden -PassThru -RedirectStandardOutput (Join-Path $QualityDirectory 'frontend.log') -RedirectStandardError (Join-Path $QualityDirectory 'frontend-error.log')
        Save-Process $process
        Wait-Ready 'http://127.0.0.1:5173'
    }
    Write-Host 'Development services ready. Run scripts/stop-dev.ps1 to stop them.'
} catch {
    if ($processes.Count -gt 0) { & "$PSScriptRoot/stop-dev.ps1" }
    Write-Error $_; exit 1
} finally {
    foreach ($key in $savedEnvironment.Keys) { [Environment]::SetEnvironmentVariable($key, $savedEnvironment[$key], 'Process') }
}
