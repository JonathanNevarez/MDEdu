# Learning Model (Fase 6)

Modelo fuente: model/learning.ecore; GenModel Java 21: model/learning.genmodel.
26 archivos src-gen generados por EMF, sin modificaciones manuales.
Las invariantes manuales residen en src/.../validation/LearningModels.java.
Los ejemplos son snapshots reales producidos por LearningIT desde PostgreSQL
Testcontainers (identidades de prueba, no personas reales).

Generación reproducible con Eclipse Modeling instalado, desde raíz del repositorio:

```powershell
$learningProject = Join-Path (Get-Location) 'mde\com.project.mde.learning.model'
& "$env:LOCALAPPDATA\Programs\Eclipse\eclipse-modeling-2026-09-R\eclipsec.exe" -nosplash -data "$env:LOCALAPPDATA\EclipseWorkspaces\MDEdu-fase6-generation" -application org.eclipse.emf.codegen.ecore.Generator -import $learningProject -model -autoBuild false 'platform:/resource/com.project.mde.learning.model/model/learning.genmodel'
```

Workspace externo al repositorio. No genera Edit/Editor ni modifica programming.
Metadata PDE: bundle 0.1.0.qualifier, JavaSE-21, fuentes src-gen/ y src/.
Build desde mde/: `mvn.cmd --batch-mode --no-transfer-progress clean install`.
Backend consume el artefacto instalado mediante dependencia Maven normal.

ValidateLearning.java comprueba Ecore y los dos ejemplos usando el JAR construido.
Usar el mismo classpath Common/Ecore/XMI de validation/README.md, sustituyendo el
JAR por com.project.mde.learning.model y ejecutando validation/ValidateLearning.java.
Las pruebas de proyección, invariantes, política y persistencia están en
backend/src/test/java/com/project/student/.
