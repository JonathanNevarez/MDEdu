# Inspección de requisitos; no instala ni cambia variables del sistema.
$ErrorActionPreference = 'Stop'
$missingRequired = @()
foreach ($toolName in @('java', 'javac', 'mvn', 'node', 'npm', 'docker')) {
    $toolCommand = Get-Command $toolName -ErrorAction SilentlyContinue
    if ($null -eq $toolCommand) {
        Write-Output "$toolName : NO DISPONIBLE EN PATH"
        $missingRequired += $toolName
    } else {
        Write-Output "$toolName : $($toolCommand.Source)"
    }
}
foreach ($toolName in @('git', 'eclipse')) {
    $toolCommand = Get-Command $toolName -ErrorAction SilentlyContinue
    if ($null -eq $toolCommand) {
        Write-Output "$toolName : NO DISPONIBLE EN PATH (complementario)"
    } else {
        Write-Output "$toolName : $($toolCommand.Source)"
    }
}
if ($missingRequired.Count -gt 0) {
    Write-Output 'Fase 0 pendiente: consultar docs/HERRAMIENTAS.md.'
    exit 1
}
Write-Output 'Binarios encontrados. Aun debe verificar versiones, Docker Engine y pruebas de arranque.'
exit 0
