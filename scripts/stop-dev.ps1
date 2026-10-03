. "$PSScriptRoot/common.ps1"
try {
    $statePath = Join-Path $QualityDirectory 'processes.json'
    if (Test-Path -LiteralPath $statePath) {
        $records = Get-Content -Raw -LiteralPath $statePath | ConvertFrom-Json
        foreach ($record in $records) {
            $process = Get-Process -Id $record.id -ErrorAction SilentlyContinue
            if ($process -and $process.ProcessName -eq $record.name -and $process.StartTime.ToUniversalTime().ToString('o') -eq $record.started) {
                Stop-Process -Id $process.Id
                if (-not $process.WaitForExit(15000)) { throw "Process $($record.id) did not terminate." }
            } elseif ($process) {
                throw "Process $($record.id) has a different identity; refusing to stop it. State file retained."
            }
        }
        Remove-Item -LiteralPath $statePath
    }
    Push-Location $ProjectRoot
    try { Invoke-Checked docker @('compose', 'stop', 'postgres') } finally { Pop-Location }
    Write-Host 'Development processes stopped. Database volume preserved.'
} catch { Write-Error $_; exit 1 }
