# Evidencia de Fase 1 — 01/10/2026

## Punto de partida y alcance

HEAD inicial: `3249e26c0b1bc3d50049103fc49c7d1968b8e3ed`,
`chore: complete phase 0 development environment`.
`git status`: working tree clean. Rama main sincronizada con origin/main.
Origin: `https://github.com/JonathanNevarez/MDEdu.git`.
Se revisaron status, rev-parse corto/completo, branch -vv y log -10.

Antes de crear archivos, mde/ contenía README y placeholders en acceleo, atl,
generated, metamodels, models y xtext. No existía el proyecto propuesto ni un
namespace equivalente. Se conservaron los placeholders y se aclaró la política
provisional de generated: los fuentes del runtime EMF se versionan en src-gen.

## Herramientas efectivamente usadas

| Herramienta | Versión |
| --- | --- |
| Eclipse Modeling Tools | 2026-09 R |
| Platform | 4.41.0.v20260828-1142 |
| Java interno de Eclipse | JustJ/Temurin 25.0.4.1+1-LTS |
| EMF SDK | 2.47.0.v20260704-1256 |
| Ecore Tools | 3.6.0.202604070657 |
| EMF Common del classpath de validación | 2.46.0.v20260704-1256 |
| EMF Ecore del classpath de validación | 2.43.0.v20260704-1256 |
| EMF Ecore XMI del classpath de validación | 2.41.0.v20260704-1256 |
| Maven | 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5) |
| Java headless | Eclipse Adoptium 21.0.12.1 |
| Tycho | 5.0.4 (c4349ee27dfde2817dfc7121b5f84bfb9eeb0195) |

No se reinstalaron ni actualizaron herramientas. JAVA_HOME permaneció en
`C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\`.
Se refrescó únicamente PATH del proceso de terminal desde los valores persistentes;
no se cambió PATH del sistema ni del usuario. Eclipse conservó Java 25 embebido.

Workspace creado fuera del repo: `%LOCALAPPDATA%\EclipseWorkspaces\MDEdu-fase1`.
El proyecto físico se importó desde `mde/com.project.mde.programming.model`,
sin copiarlo al workspace.

## Ecore y generación real

EPackage programming, nsPrefix programming, nsURI
`https://mdedu.espoch.edu.ec/model/programming/1.0`.
Inventario comprobado mediante EMF: **17 EClasses, 2 abstractas, 4 EEnums,
24 features propias**. Herencia, tipos, multiplicidades, ordered/unique y
containment contrastados con el contrato de Fase 1.

En Sample Ecore Editor se ejecutó **Validate** sobre el EPackage:
**Validation completed successfully**. Validación programática adicional:
`Ecore Validate: severity=0, diagnostics=[]`.

GenModel creado con el wizard EMF, importador **Ecore model**.
Model Plugin ID com.project.mde.programming.model; Base Package com.project.mde;
Model Directory /com.project.mde.programming.model/src-gen; Compliance 21.0.
Se ejecutó **Generate Model Code**, sin generar Edit/Editor.
Resultado real: **44 archivos Java** en src-gen, sin edición manual.

Metadata revisada: JavaSE-21, versión 0.1.0.qualifier, compilador 21,
encoding UTF-8, source..=src-gen/, output..=bin/ y registro generated_package.
Dependencias runtime generadas: core.runtime y ecore reexport; Common transitivo.
No se añadieron bundles Xtext/ATL/Acceleo.

Se ejecutó **Project > Clean**. Inicialmente Problems mostró dos warnings:
encoding no explícito y JRE container sin coincidencia perfecta con JavaSE-21.
La metadata ya ajustada en disco aún no estaba refrescada. Tras refrescar el
proyecto (F5) y su build automático, Problems mostró **0 items: 0 errores,
0 warnings**. No se cambió el Java global ni se ocultaron diagnósticos.

## XMI y round-trip con EMF real

Los fixtures se crearon con ProgrammingFactory y XMIResourceFactoryImpl.
La utilidad versionada `mde/validation/ValidateProgramming.java` se compiló
inicialmente con javac --release 21 contra los fuentes generados y los tres
bundles EMF. Ejecución salida 0. Después del build Tycho se repitió usando
**el JAR producido**, mediante Java source-file mode; salida 0.
Los comandos reproducibles están en [validation/README](../mde/validation/README.md).

| Fixture | Carga / raíz Program / namespace / inspección | Validate | Guardar, descargar, reabrir y comparar |
| --- | --- | --- | --- |
| sequence-basic.programming | OK; secuencia exacta de 5 acciones | severity=0, diagnostics=[] | OK |
| variables-basic.programming | OK; contador=0, asignación=1 | severity=0, diagnostics=[] | OK; misma declaración referenciada |
| conditionals-basic.programming | OK; IfElse HAS_KEY / Move / TurnLeft | severity=0, diagnostics=[] | OK |
| loops-basic.programming | OK; Repeat 3 y While FRONT_CLEAR | severity=0, diagnostics=[] | OK |

Cada recurso se guardó bajo target/roundtrip, se descargó y se reabrió en un
ResourceSet nuevo. Sin errores/warnings de recurso ni proxies sin resolver.
EcoreUtil.equals confirmó conservación estructural. Assignment.target se
serializó como `//@statements.0`; antes y después del reload se imprimió:

```text
Assignment.target == original contained VariableDeclaration: true
```

Esto significa identidad con la declaración contenida en cada recurso cargado,
no identidad Java entre dos ResourceSets. No se duplicó la variable.
El cierre de la ejecución fue:

```text
STRUCTURAL VALIDATION SUCCESS (not semantic validation)
```

Los tipos y restricciones semánticas diferidas no se presentan como validados.
No se afirma una suite de tests de análisis ni de transformación.

## Build Tycho real

Desde mde/, con Maven 3.9.16 y Java 21.0.12.1 comprobados mediante mvn -version:

```powershell
mvn.cmd --batch-mode --no-transfer-progress clean verify
```

P2: `https://download.eclipse.org/releases/2026-09/`.
Reactor independiente: mdedu-mde (pom) y com.project.mde.programming.model
(eclipse-plugin), ambos 0.1.0-SNAPSHOT. Resultado real:

```text
Compiling 44 source files ... using Eclipse Compiler for Java(TM) 3.46.0.v20260528-0407
MDEdu MDE .......................................... SUCCESS [  0.538 s]
MDEdu Programming Model ............................ SUCCESS [ 28.375 s]
BUILD SUCCESS
Total time:  01:01 min
Finished at: 2026-10-01T12:28:24-05:00
```

Código de salida: **0**. Una ejecución del build; sin cambiar versiones.
Bundle producido:
`mde/com.project.mde.programming.model/target/com.project.mde.programming.model-0.1.0-SNAPSHOT.jar`.
Bundle-Version real: **0.1.0.202610011727**.
Inspección del JAR: **46 .class**, todas major version **65 (Java 21)**;
Build-Jdk-Spec 21 y Require-Capability JavaSE version=21.
Los 46 binarios incluyen clases adicionales generadas por el compilador;
el conteo de fuentes sigue siendo 44.

Sin líneas WARNING/ERROR en la salida del build. Aviso informativo conservado:
`10 system scoped dependencies were not mapped to maven artifacts`, durante
update-consumer-pom. No impidió resolución p2, compilación ni empaquetado.
Se informaron directorios convencionales src/main/resources y src/test/resources
inexistentes; este proyecto PDE usa build.properties. No hay suite JUnit de Fase 1.

## Revisión y cierre

Cambios autorizados: mde/**, ESTADO_PROYECTO, los dos documentos de Fase 1 y
.gitignore únicamente para `mde/**/bin/`. target/ ya estaba ignorado.
La autorización posterior añade .gitattributes exclusivamente para la política
de whitespace de fuentes generadas descrita abajo.
Se conservan src-gen, Ecore, GenModel, fixtures y metadata reproducible.
No se incluyen workspace, binarios, cachés, logs ni archivos temporales.
Backend, frontend y documentación histórica de Fase 0 no se modifican.
La revisión de los 64 archivos nuevos comprobó XML, paquetes Java, marcas de
generación, codificación y patrones de secretos, sin hallazgos pendientes.
Se corrigió una alteración de acentos en comentarios de .gitignore antes del
staging, conservando el contenido previo y añadiendo únicamente la exclusión.
Git avisó de conversión LF a CRLF conforme a la política existente; no se cambió
la normalización de texto ni EOL. La excepción posterior de whitespace es independiente.

Eclipse se cerró normalmente y el workspace externo se conserva. No quedaron
procesos Eclipse/Java ni listeners de la aplicación en 8080/5173/4173/5432.
Docker Desktop/backend no estaban ejecutándose; no se iniciaron containers,
PostgreSQL, Spring, Vite ni Playwright. Los procesos Node observados pertenecían
al runtime de herramientas, no al frontend.

El cierre autorizado usa `feat: add emf programming metamodel`, seguido de
`git push origin main`, sin force. Este documento se prepara antes de crear ese
commit: el hash propio no se inventa ni se incorpora mediante amend.
El resultado post-commit/push se registra en la respuesta de entrega con
rev-parse main, rev-parse origin/main, ls-remote, branch -vv, log -10 y status.
Solo se declara cierre definitivo después de comprobar hashes iguales y árbol limpio.

Fase 2 no iniciada. OCL, Xtext, ATL y Acceleo no utilizados.

## Incidencia de whitespace detectada y resuelta

`git diff --check` de los archivos previamente versionados pasó. Después del
staging autorizado, `git diff --cached --check` incluyó también los fuentes nuevos
y terminó con **código 2**, por trailing whitespace generado por EMF:

- src-gen/com/project/mde/programming/BooleanOperator.java:234
- src-gen/com/project/mde/programming/ComparisonOperator.java:303
- src-gen/com/project/mde/programming/SensorKind.java:349
- src-gen/com/project/mde/programming/ValueType.java:211

Son líneas que contienen una tabulación, sin código. El primer cierre se detuvo
sin commit ni push y dejó dos documentos con correcciones posteriores al índice.
El build y las validaciones EMF sí habían sido satisfactorios.

Con autorización explícita posterior se conservó el índice y se añadió únicamente
esta política documentada a .gitattributes, preservando todas las reglas existentes:

```gitattributes
# EMF-generated sources are regenerated from Ecore/GenModel.
# Preserve generator output verbatim; do not flag its whitespace.
mde/**/src-gen/** -whitespace
```

Los cuatro enums indican `whitespace: unset`, `text: auto`, `eol: unspecified`.
El Java manual del backend, la utilidad ValidateProgramming.java, docs y el POM
indican `whitespace: unspecified`: no reciben la excepción y mantienen el control
normal. No se cambió core.whitespace local/global, EOL ni tratamiento de texto.
La política preserva el output EMF sin reformatear; Ecore/GenModel siguen siendo
la fuente de verdad. Se conservaron contenido y timestamps de los 44 fuentes Java.

Después de la regla, `git diff --cached --check`: **código 0, sin errores**.
Se actualizan en el índice las versiones finales de ambos documentos junto con
.gitattributes; los otros 66 archivos preparados se conservan. Total: **69 archivos**.
Los archivos técnicos no presentan cambios respecto al índice de la validación
anterior. Se conserva el bundle producido; no se repitió Maven ni la generación.

**FASE 0: COMPLETADA Y VALIDADA. FASE 1: COMPLETADA Y VALIDADA.
FASE 2: NO INICIADA.** Siguiente paso, solo con nueva autorización:
Blockly / representación visual → modelo EMF conforme a programming.ecore.
