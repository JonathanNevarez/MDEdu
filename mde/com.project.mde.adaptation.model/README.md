# Adaptation model

Modelo canónico Fase 7: model/adaptation.ecore; GenModel Java 21.
Generación real EMF (PowerShell, desde raíz del repositorio):

```powershell
$adaptationProject = Join-Path (Get-Location) 'mde/com.project.mde.adaptation.model'
& "$env:LOCALAPPDATA/Programs/Eclipse/eclipse-modeling-2026-09-R/eclipsec.exe" -nosplash -data "$env:LOCALAPPDATA/EclipseWorkspaces/MDEdu-fase7-generation" -application org.eclipse.emf.codegen.ecore.Generator -import $adaptationProject -model -autoBuild false 'platform:/resource/com.project.mde.adaptation.model/model/adaptation.genmodel'
```

La ruta corresponde a la instalación de herramientas validada, no a una dependencia
Maven del backend. Importar el plugin y ejecutar Generate Model Code desde Eclipse
es equivalente. No editar src-gen; typeSafeEnumCompatible=false está definido en
cada genEnum. Si cambia esa opción, generar en carpeta limpia para evitar el merge
de las constantes anteriores. Se versionan 44 Java src-gen; validación manual en
src/.../validation. Metadata PDE fija JavaSE-21 y bundle 0.1.0.qualifier.

Desde mde: `mvn.cmd --batch-mode --no-transfer-progress clean install`.
El módulo DSL prueba Ecore, ejemplos XMI y reglas de validación compartidas.
[Documentación](../../docs/FASE_7_DSL_REGLAS_ADAPTACION.md).
