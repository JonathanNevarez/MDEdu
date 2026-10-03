Set-StrictMode -Version Latest
$ErrorActionPreference = 'Stop'
$ProjectRoot = Split-Path $PSScriptRoot -Parent
$QualityDirectory = Join-Path $ProjectRoot '.quality'
function Invoke-Checked {
    param([string]$Command, [string[]]$Arguments = @())
    $null = Get-Command $Command -ErrorAction Stop
    $previous = $ErrorActionPreference
    try {
        $ErrorActionPreference = 'Continue'
        & $Command @Arguments
        $result = $LASTEXITCODE
    } finally { $ErrorActionPreference = $previous }
    if ($result -ne 0) { throw "$Command failed with exit code $result" }
}
function Wait-Ready {
    param([string]$Url)
    $deadline = (Get-Date).AddSeconds(90)
    do {
        try { if ((Invoke-WebRequest -UseBasicParsing -Uri $Url -TimeoutSec 3).StatusCode -eq 200) { return } } catch {}
        Start-Sleep -Seconds 2
    } while ((Get-Date) -lt $deadline)
    throw "Readiness timeout: $Url"
}
function Read-TestSettings {
    $settings = @{}
    Get-Content -LiteralPath (Join-Path $ProjectRoot 'backend/src/test/resources/teacher-test.properties') | ForEach-Object {
        if ($_ -match '^([^#=]+)=(.*)$') { $settings[$matches[1]] = $matches[2] }
    }
    return $settings
}
