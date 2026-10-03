param([switch]$SkipDockerSmoke)
. "$PSScriptRoot/common.ps1"
$previousMaven = $env:MAVEN_OPTS
$previousNode = $env:NODE_OPTIONS
$previousMock = $env:GEMINI_MOCK_E2E
try {
    $env:MAVEN_OPTS = '-Xmx384m'
    $env:NODE_OPTIONS = '--max-old-space-size=384'
    Push-Location $ProjectRoot
    try {
        Invoke-Checked './backend/mvnw.cmd' @('-f','mde/pom.xml','--batch-mode','--no-transfer-progress','clean','install')
        Invoke-Checked './backend/mvnw.cmd' @('-f','backend/pom.xml','--batch-mode','--no-transfer-progress','clean','test')
        Invoke-Checked './backend/mvnw.cmd' @('-f','backend/pom.xml','--batch-mode','--no-transfer-progress','verify')
        Invoke-Checked './backend/mvnw.cmd' @('-f','mde/com.project.mde.programming.generator/pom.xml','exec:exec','-Dmodel=../com.project.mde.programming.model/examples/sequence-basic.programming','-Doutput=target/phase13-cli')
        Invoke-Checked node @('--check','mde/com.project.mde.programming.generator/target/phase13-cli/program.js')
    } finally { Pop-Location }
    Push-Location (Join-Path $ProjectRoot 'frontend')
    try {
        Invoke-Checked npm.cmd @('ci')
        Invoke-Checked npm.cmd @('test','--','--maxWorkers=1')
        Invoke-Checked npm.cmd @('run','typecheck')
        Invoke-Checked npm.cmd @('run','build')
        Invoke-Checked npm.cmd @('audit')
        try {
            Invoke-Checked powershell.exe @('-NoProfile','-ExecutionPolicy','Bypass','-File',"$PSScriptRoot/start-dev.ps1",'-BackendOnly','-TestCredentials')
            $env:GEMINI_MOCK_E2E = 'false'
            Invoke-Checked npx.cmd @('playwright','test','--workers=1')
        } finally { Invoke-Checked powershell.exe @('-NoProfile','-ExecutionPolicy','Bypass','-File',"$PSScriptRoot/stop-dev.ps1") }
        try {
            Invoke-Checked powershell.exe @('-NoProfile','-ExecutionPolicy','Bypass','-File',"$PSScriptRoot/start-dev.ps1",'-BackendOnly','-TestCredentials','-GeminiMock')
            $env:GEMINI_MOCK_E2E = 'true'
            Invoke-Checked npx.cmd @('playwright','test','gemini.spec.ts','quality-gemini.spec.ts','--workers=1')
        } finally { Invoke-Checked powershell.exe @('-NoProfile','-ExecutionPolicy','Bypass','-File',"$PSScriptRoot/stop-dev.ps1") }
    } finally { Pop-Location }
    Invoke-Checked powershell.exe @('-NoProfile','-ExecutionPolicy','Bypass','-File',"$PSScriptRoot/check-secrets.ps1",'-IncludeBundle','-IncludeLogs')
    if (-not $SkipDockerSmoke) { Invoke-Checked powershell.exe @('-NoProfile','-ExecutionPolicy','Bypass','-File',"$PSScriptRoot/docker-smoke.ps1",'-Browser') }
    Write-Host 'All requested verification stages PASS.'
} catch { Write-Error $_; exit 1 } finally {
    $env:MAVEN_OPTS=$previousMaven; $env:NODE_OPTIONS=$previousNode; $env:GEMINI_MOCK_E2E=$previousMock
}
