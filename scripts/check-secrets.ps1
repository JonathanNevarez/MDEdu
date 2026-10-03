param([switch]$IncludeBundle, [switch]$IncludeLogs)
. "$PSScriptRoot/common.ps1"
try {
    Push-Location $ProjectRoot
    try { $paths = @(& git ls-files --cached --others --exclude-standard); if ($LASTEXITCODE -ne 0) { throw 'Cannot enumerate project files.' } } finally { Pop-Location }
    $findings = @()
    foreach ($path in $paths) {
        $absolute = Join-Path $ProjectRoot $path
        if (-not (Test-Path -LiteralPath $absolute -PathType Leaf)) { continue }
        if ($path -match '(\.(png|jpg|gif|woff2?|jar|zip|ico|pdf)$)') { continue }
        $content = [IO.File]::ReadAllText($absolute)
        if ($content -match ('AIza' + '[A-Za-z0-9_-]{35}') -or $content -match ('sk-' + '[A-Za-z0-9_-]{40,}')) { $findings += $path }
        if ($path -eq '.env.example' -and $content -match '(?m)^(?:GEMINI_API_KEY|LLM_API_KEY|META_UI_PASSWORD)=\S+') { $findings += $path }
    }
    $sentinels = @()
    $fixtures = Read-TestSettings
    foreach ($key in @('META_UI_PASSWORD','GEMINI_API_KEY','DB_PASSWORD')) { $sentinels += $fixtures[$key] }
    $localEnvironment = Join-Path $ProjectRoot '.env'
    if (Test-Path -LiteralPath $localEnvironment) {
        foreach ($line in Get-Content -LiteralPath $localEnvironment) {
            if ($line -match '^(?:META_UI_PASSWORD|GEMINI_API_KEY|LLM_API_KEY|DB_PASSWORD)=(.{8,})$') { $sentinels += $matches[1] }
        }
    }
    $scanFiles = @()
    if ($IncludeBundle) {
        $bundle = Join-Path $ProjectRoot 'frontend/dist'
        if (-not (Test-Path $bundle)) { throw 'Build frontend before scanning its bundle.' }
        $scanFiles += @(Get-ChildItem -LiteralPath $bundle -Recurse -File | Where-Object Extension -in @('.js','.css','.html','.json','.map'))
    }
    if ($IncludeLogs -and (Test-Path -LiteralPath $QualityDirectory)) {
        $scanFiles += @(Get-ChildItem -LiteralPath $QualityDirectory -Filter '*.log' -File)
    }
        foreach ($file in $scanFiles) {
            $content = [IO.File]::ReadAllText($file.FullName)
            foreach ($sentinel in $sentinels) {
                if ($content.Contains($sentinel)) { $findings += $file.FullName }
            }
            if ($content -match ('AIza' + '[A-Za-z0-9_-]{35}') -or $content -match ('sk-' + '[A-Za-z0-9_-]{40,}')) { $findings += $file.FullName }
        }
    if ($findings.Count) { throw ('Potential secrets detected in: ' + (($findings | Select-Object -Unique) -join ', ')) }
    Write-Host 'Secret format/example/bundle checks PASS. No secret values printed.'
} catch { Write-Error $_; exit 1 }
