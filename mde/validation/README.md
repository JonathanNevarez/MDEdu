# Validación estructural EMF

`ValidateProgramming.java` carga Ecore y los cuatro fixtures mediante EMF real,
comprueba inventario, herencia, multiplicidades y referencias, y ejecuta
`Diagnostician`. Guarda copias bajo `target/roundtrip/`, descarga los recursos y
los recarga en un ResourceSet nuevo. Compara estructura e identidad de
`Assignment.target` respecto de la declaración contenida tras cada carga.

No es una suite semántica ni de transformaciones; no forma parte de los tests
de Tycho. Su resultado se informa por separado del build.

Después del build, desde la raíz del repositorio, con Java 21 y los bundles EMF
de la instalación Eclipse validada:

```powershell
$plugin = Join-Path (Get-Location) 'mde\com.project.mde.programming.model'
$emfDir = Join-Path $env:LOCALAPPDATA 'Programs\Eclipse\eclipse-modeling-2026-09-R\plugins'
$emfJars = Get-ChildItem -LiteralPath $emfDir -File |
    Where-Object Name -match '^org\.eclipse\.emf\.(common|ecore|ecore\.xmi)_[0-9]'
$bundle = Join-Path $plugin 'target\com.project.mde.programming.model-0.1.0-SNAPSHOT.jar'
$cp = (@($bundle) + @($emfJars.FullName)) -join ';'
java --class-path $cp mde\validation\ValidateProgramming.java $plugin
```

Adaptar únicamente la ruta de Eclipse en otra máquina. Los bundles utilizados
están identificados en `docs/VERIFICACION_FASE_1.md`.
`--create-examples` se usó una vez para producir los fixtures con ProgrammingFactory;
rechaza sobrescribir archivos existentes. No se necesita para verificar los ejemplos.
