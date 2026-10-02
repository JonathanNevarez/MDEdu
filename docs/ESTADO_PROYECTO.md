# Estado del proyecto

Actualización documental: 02/10/2026.

## Estado actual

**FASE 0 COMPLETADA. FASE 1 COMPLETADA. FASE 2 COMPLETADA.
FASE 3 COMPLETADA. FASE 4 COMPLETADA. FASE 5 COMPLETADA.
FASE 6 COMPLETADA. FASE 7 COMPLETADA Y VALIDADA. FASE 8 NO INICIADA.**

Partida: `877e4aa477948dd43c72e60940d28bf6aeb143bb`.
adaptation.ecore canónico, plugin EMF Java 21 con 44 fuentes generadas,
Xtext 2.44.0/MWE2 2.27.0, parser headless que crea directamente EObjects.
DSL versionado con seis reglas semilla, catálogo tipado y diez acciones cerradas.
RuleEvaluationContext inmutable desde StudentModel/Attempt/EvaluationResult reales;
error repetido reutiliza MasteryUpdater.Change. Motor ECA determinista en orden DSL:
solo reglas coincidentes y candidatos, sin aplicar acciones ni elegir conflictos.

Validación: MDE clean verify +build externo con Maven vacío; 22 tests Xtext/EMF y
25 Acceleo. Backend: 185 unit/MVC +17 IT (4 Bootstrap,10 Learning,3 Adaptation).
Frontend: typecheck,44 tests,build,5 E2E PASS. A mastery .80 → DominioAlto;
B tres fallos → ReforzarCiclos/ErrorRepetido/FuncionalSinConcepto, sin efectos.
100 evaluaciones JSON idénticas; conflicto aumento/disminución conservado sin ganador.

Programming Ecore/GenModel/44 src-gen, learning Ecore/GenModel/26 src-gen,
Acceleo/goldens, ProgramDto, frontend y V1/V2 intactos. Sin V3, endpoint nuevo,
context.ecore, ui.ecore, LLM, temas avanzados ni AdaptationManager.
Cuatro conceptos/actividades, cero refuerzos reales. Warning de bundle histórico.
Servicios de prueba detenidos; volumen PostgreSQL conservado.

[Diseño](FASE_7_DSL_REGLAS_ADAPTACION.md) · [Reglas](REGLAS_ADAPTACION.md) ·
[Evidencia](VERIFICACION_FASE_7.md).
Cierre Git autorizado: `feat: add xtext adaptation rule engine`, solo main.
Siguiente: **FASE 8 — Adaptation Manager y resolución de decisiones**.
No iniciada; requiere nueva autorización.

## Histórico: cierre de Fase 6

**FASE 0 COMPLETADA. FASE 1 COMPLETADA. FASE 2 COMPLETADA.
FASE 3 COMPLETADA. FASE 4 COMPLETADA. FASE 5 COMPLETADA.
FASE 6 COMPLETADA Y VALIDADA. FASE 7 NO INICIADA.**

Partida: `693e40a2153d66ce5711a6ebe97a22c78498f84b`.
Plugin learning independiente: 10 EClasses, 26 Java generados, GenModel Java 21 y
dos XMI autocontenidos validados. PostgreSQL normalizado con Flyway V2, proyección
JPA → LearningFactory → StudentModel EMF validado → DTO. Política JSON V1,
contadores, historial reciente y grafo persistido con threshold .05.
Transacciones y lock por estudiante; completion/unlock históricos no retroceden.

Aventura usa UUID pseudónimo local y progreso backend; no acredita progreso antiguo.
Cuatro conceptos/actividades, cero refuerzos. Endpoint stateless Fase 5 intacto.
Dos estudiantes en contextos separados demuestran independencia y persistencia
tras reload: A éxito .10/Variables unlocked; B fallo .00/Variables locked.

Validaciones: 147 pruebas backend + 14 IT (4 históricas y 10 learning), 44 pruebas
frontend, typecheck/build y 5 E2E PASS. Reactor MDE completo BUILD SUCCESS; Acceleo
con 25 pruebas, CLI y node --check PASS. ValidateLearning: Ecore válido y dos
round-trips PASS. Full-stack posterior en Chromium visible: 2 escenarios PASS.
Programming Ecore/GenModel/44 src-gen, Acceleo/golden y ProgramDto conservados.

Backend/preview/PostgreSQL de prueba y Docker Desktop detenidos al cierre;
volumen PostgreSQL conservado con V1/V2 y datos pseudónimos de validación.
Sin adaptation.ecore, context.ecore, Xtext, ECA, LLM ni programación avanzada.
Warning histórico de bundle Blockly >500 kB conservado.

[Diseño](FASE_6_MODELO_ESTUDIANTE.md) · [Evidencia](VERIFICACION_FASE_6.md).
Cierre Git autorizado: `feat: add emf student learning model`, solo main.
Siguiente: **FASE 7 — adaptation.ecore + Xtext + DSL ECA**, pendiente de nueva
autorización. No iniciada.

## Histórico: cierre de Fase 5

**FASE 0 COMPLETADA. FASE 1 COMPLETADA. FASE 2 COMPLETADA.
FASE 3 COMPLETADA. FASE 4 COMPLETADA.
FASE 5 COMPLETADA Y VALIDADA. FASE 6 NO INICIADA.**

Partida Fase 5: `e0d28ba6eeadffce0cea27467c991d42f3daf9a0`.
Evaluador separado del motor sobre Program EMF y la misma ejecución/traza;
cuatro dimensiones, activityPassed, 15 patrones y cuatro configuraciones V1.
Feedback determinista de catálogo; el mapa completa solo por activityPassed
sin retirar niveles ya completados. Laboratorio y diseño del mapa conservados.

Validación: 139 tests backend, verify con 4 BackendBootstrapIT; 34 tests frontend,
typecheck/build y 4 E2E Chromium PASS. MDE clean verify (25 tests), Acceleo CLI y
node --check PASS. Full-stack posterior en Chromium visible: Secuencias completa,
Ciclos manual llega sin completar y Repetir(7) con Avanzar completa.
Respuestas HTTP repetidas idénticas para cinco fixtures.
Ecore/GenModel/44 src-gen y los 79 archivos MDE verificados permanecen intactos.
Sin DB/Flyway nuevos, StudentModel, LLM, reglas ECA ni materia avanzada.
Se conserva warning histórico de bundle >500 kB.

Backend, preview y PostgreSQL de prueba detenidos; volumen existente conservado.
Docker Desktop, arrancado para esta validación, detenido al cierre. Puertos
8080/4173/5173/5432 sin listeners al finalizar las pruebas.

[Diseño](FASE_5_EVALUACION_PEDAGOGICA.md) · [Evidencia](VERIFICACION_FASE_5.md).
Cierre Git autorizado: `feat: add deterministic pedagogical evaluation`, solo main.
Siguiente: **FASE 6 — learning.ecore + StudentModel + progreso pedagógico**,
pendiente de autorización independiente. No iniciada.

## Histórico: cierre de Fase 4

**FASES 0, 1, 2 y 3: COMPLETADAS.
FASE 4: COMPLETADA Y VALIDADA. FASE 5: NO INICIADA.**

Partida: `5b67fd6fa1ca7f791ef7580289228df3cdc39dec`.
Motor GridWorld Java sobre Program EMF, catálogo declarativo de cuatro niveles,
API, mapa, toolbox progresiva, trazas deterministas, replay y progreso local provisional.
77 tests backend + 4 BackendBootstrapIT; 26 tests frontend; typecheck/build PASS;
3 E2E Chromium PASS, incluyendo recorrido contra API real, replay sin nueva petición,
reinicio sin borrar bloques, desbloqueo y persistencia tras recarga.
MDE clean verify: BUILD SUCCESS (25 tests); generación Acceleo conocida + node --check PASS.
Ecore/GenModel/44 src-gen/Acceleo intactos; no cambios DB/Flyway, StudentModel ni LLM.
Bundle Blockly conserva warning >500 kB. Servicios de validación detenidos al cierre.

[Diseño](FASE_4_GRIDWORLD_JUEGO.md) · [Evidencia](VERIFICACION_FASE_4.md).
Cierre Git autorizado: `feat: add gridworld levels and execution`, solo main.
Siguiente: **FASE 5 — Evaluación de soluciones y patrones pedagógicos**, pendiente
exclusivamente de nueva autorización.

## Histórico: cierre de Fase 3

**FASE 0: COMPLETADA. FASE 1: COMPLETADA. FASE 2: COMPLETADA.
FASE 3: COMPLETADA Y VALIDADA. FASE 4: NO INICIADA.**

Partida Fase 3: `0e3b6b8105a766ed967cc56e5e3e60dee565e4da`.
Generador separado Acceleo 4.2.2 / AQL 8.1.2: Program EMF → JavaScript controlado.
Lógica en main.mtl; launcher headless, cuatro golden files, 25 pruebas y 29 syntax
checks por suite. Cuatro generaciones A/B idénticas; reactor MDE y copia limpia
con repositorio Maven nuevo: BUILD SUCCESS. Ecore/GenModel/44 src-gen intactos.
Backend/frontend sin modificaciones. Sin ejecución del código ni servicios.
[Diseño](FASE_3_M2T_ACCELEO.md) y [evidencia](VERIFICACION_FASE_3.md).
Cierre Git: `feat: add acceleo javascript generator`, solo main.
Siguiente paso, pendiente de autorización independiente:
**FASE 4 — GridWorld y ejecución determinista**, no iniciada.

## Histórico: cierre de Fase 2

**FASE 0: COMPLETADA Y VALIDADA. FASE 1: COMPLETADA Y VALIDADA.
FASE 2: COMPLETADA Y VALIDADA. FASE 3: NO INICIADA.**

Partida de Fase 2: `44600d55fad10b58af812a35032807ad1b08dba4`.
Implementado el laboratorio `/laboratorio`, Blockly 13.3.0, ProgramDto V1,
fixtures compartidos, persistencia visual local y API DTO → EMF → XMI.
El modelo formal se consume como artefacto Maven y permanece intacto.

Validaciones finales: typecheck y build PASS; 19 pruebas frontend en 2 suites;
2 E2E Chromium; backend test/verify BUILD SUCCESS (25 unit/MVC + 4 IT históricas);
MDE clean verify BUILD SUCCESS. Revisión visual full-stack en Opera: variables,
guardar/limpiar/restaurar, POST HTTP 200, valid=true, 2 instrucciones y XMI visible.
Frontend, backend y PostgreSQL Compose quedaron detenidos. Sin nueva persistencia
DB ni Flyway V2. Docker Desktop permanece operativo; no quedan contenedores en
 ejecución. El warning de bundle >500 kB queda como deuda de optimización.

Documentación: [diseño Fase 2](FASE_2_BLOCKLY_MODELO_EMF.md),
[evidencia y cronología](VERIFICACION_FASE_2.md),
[contrato V1](../contracts/programming/v1/README.md).
Cierre Git autorizado: `feat: connect blockly editor to emf model`, solo main.
Siguiente fase, pendiente de autorización: Fase 3 — M2T con Acceleo,
Program EMF → JavaScript. No se ha iniciado.

## Histórico: cierre de Fase 1

**FASE 0: COMPLETADA Y VALIDADA. FASE 1: COMPLETADA Y VALIDADA.
FASE 2: NO INICIADA.**

El metamodelo de programación EMF está implementado en
`mde/com.project.mde.programming.model`: 17 EClasses, 2 abstractas, 4 EEnums,
GenModel Java 21 y 44 fuentes generados. Ecore Validate y Eclipse Problems:
0 errores; Problems final sin warnings. Los cuatro XMI cargan, validan y
conservan estructura y referencias en round-trip con EMF real.

Build independiente Tycho 5.0.4 / Maven 3.9.16 / Java 21.0.12.1:
**BUILD SUCCESS**, salida 0. No se usaron Xtext, ATL, Acceleo u OCL.
Backend/frontend intactos; Eclipse cerrado y workspace Fase 1 externo conservado.
Servicios de la aplicación no iniciados; Java global intacto.

Diseño: [FASE_1_METAMODELO_PROGRAMACION](FASE_1_METAMODELO_PROGRAMACION.md).
Evidencia: [VERIFICACION_FASE_1](VERIFICACION_FASE_1.md).

Siguiente paso: **FASE 2 — Blockly / representación visual → modelo EMF
conforme a programming.ecore**, únicamente tras nueva autorización.
El cierre Git autorizado usa `feat: add emf programming metamodel` y push a main;
el bloqueo inicial de cuatro líneas con whitespace en enums EMF quedó resuelto
mediante la regla específica `mde/**/src-gen/** -whitespace` en .gitattributes.
`git diff --cached --check` pasó con código 0. Los fuentes generados se conservan
sin edición manual; el control sigue activo para archivos no generados.

## Histórico: cierre de Fase 0 del 01/10/2026

Los apartados siguientes conservan la evidencia y previsiones anteriores a Fase 1;
sus menciones a pendientes o ausencia de metamodelos no describen el estado actual.

**FASE 0: COMPLETADA Y VALIDADA. Fase 1 NO iniciada.**
El entorno está preparado; el producto educativo todavía no está implementado.
No se crearon metamodelos, DSL, transformaciones ATL, templates Acceleo ni código generado.

Eclipse Modeling Tools 2026-09 R conserva Platform 4.41.0.v20260828-1142,
EMF SDK 2.47.0.v20260704-1256, Ecore 2.43.0.v20260704-1256 y Ecore Tools
3.6.0.202604070657. Xtext SDK 2.44.0.v20260824-1228, ATL
4.12.0.v202505101449 y Acceleo 4.2.2 con AQL 8.1.2 quedaron instalados y
validados desde SimRel 2026-09. Ninguna unidad preexistente fue reemplazada o eliminada.
Los wizards se comprobaron y cancelaron sin crear proyectos. Acceleo 3 no está instalado.

Tycho `org.eclipse.tycho:tycho-maven-plugin:5.0.4` resolvió desde Maven Central:
`help:describe`, salida 0, BUILD SUCCESS y descriptor accesible. Maven 3.9.16 y
Java 21.0.12.1 cumplen los mínimos 3.9.9/21 del descriptor. Esta comprobación se
hizo fuera del repositorio y no constituye un build real de plugins Eclipse.
La carpeta temporal se eliminó; la caché Maven permanece.

Java global sigue en Temurin 21.0.12.1; JAVA_HOME:
`C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\`.
Eclipse mantiene su Temurin/JustJ 25.0.4.1+1-LTS interno. No se modificaron
JAVA_HOME, PATH persistente ni eclipse.ini; Java 25 no se añadió al PATH.

Eclipse quedó cerrado, sin procesos asociados. Se conservan instalación y workspace
externos `C:\Users\alexxxjon\AppData\Local\EclipseWorkspaces\MDEdu-fase0`.
No hay servicios MDEdu ejecutándose ni listeners en 8080/5173/4173/5432.
Docker no se inició en este bloque; PostgreSQL continúa detenido.

## Criterios finales de Fase 0

Las pruebas previamente aceptadas conservan su evidencia histórica; no se repitieron
builds o servicios de la aplicación durante el cierre MDE.

| Criterio obligatorio | Resultado |
| --- | --- |
| JDK 21 | OK: Temurin 21.0.12.1, javac y compilación mínima |
| Maven | OK: 3.9.16 con Java 21 |
| Maven Wrapper | OK: 3.3.4 |
| Node/npm | OK: 24.19.0 / 11.17.0 |
| Frontend build | OK: TypeScript/Vite |
| Frontend unit | OK: 2/2 |
| Frontend HTTP | OK: HTTP 200 |
| Frontend E2E | OK: Chromium, 1/1 |
| Git | OK: main, origin MDEdu; limpio antes de documentar |
| WSL2 | OK: 2.7.14.0, predeterminado 2 |
| Docker | OK: Desktop 4.91.0, Engine 29.8.0 |
| PostgreSQL | OK: Compose, 17.11, SELECT 1; detenido |
| Backend compile/test | OK: compilación Java 21, 3/3 MVC |
| BackendBootstrapIT | OK: 4/4, Maven verify y Testcontainers |
| Spring runtime | OK: Spring Boot 3.5.16, PostgreSQL/Flyway/health/CORS |
| Eclipse Modeling Tools | OK: 2026-09 R / Platform 4.41 |
| EMF/Ecore | OK: SDK 2.47.0 / Ecore 2.43.0 |
| Ecore Tools | OK: 3.6.0.202604070657 |
| Xtext SDK | OK: 2.44.0.v20260824-1228, wizard funcional |
| ATL | OK: 4.12.0.v202505101449, editor/engine/EMF/launcher/wizard |
| Acceleo 4 | OK: 4.2.2, AQL 8.1.2, IDE/launcher/wizard |
| Tycho resoluble/headless tooling | OK: 5.0.4, descriptor resuelto; sin build de plugin real |
| Documentación | OK: estado, herramientas y evidencia final actualizados |

OCL: **DISPONIBLE / DIFERIDO**, incluido por Modeling Tools y no utilizado.
No queda ningún criterio obligatorio del entorno Fase 0 pendiente.

## Asignación tecnológica y separación

| Función | Tecnología |
| --- | --- |
| Metamodelado | EMF / Ecore |
| Serialización | XMI / EMF |
| Futuro DSL de adaptación ECA | Xtext 2.44 |
| M2M | ATL 4.12 |
| M2T | Acceleo 4.2.2 |
| Futuros builds Eclipse/OSGi headless | Tycho 5.0.4 |
| OCL | Disponible, uso diferido |
| backend/ | Spring Boot normal, separado de Eclipse |
| frontend/ | React/Vite separado |
| mde/ | Reservado para el futuro ecosistema Eclipse/EMF/Xtext/ATL/Acceleo/Tycho |

No se añadieron dependencias Eclipse/Tycho al backend ni se modificaron frontend/ o mde/.
La advertencia EGit sobre HOME no definido no impide la validación; no se cambió HOME.

## Siguiente paso

**Fase 1 — metamodelo de programación / EMF-Ecore**, únicamente tras nueva autorización.
El cierre actual se limita a los tres documentos, commit
`chore: complete phase 0 development environment` y publicación en main.

## Historial conservado

Todo lo que sigue describe checkpoints anteriores al cierre del 01/10/2026.
Las menciones a ausencias, pendientes y autorizaciones son históricas, no el estado actual.

Actualización documental: 23/09/2026. Eclipse base validado el 23/09; los apartados históricos conservan sus fechas.

## Histórico: estado al 23/09/2026

**Fase 0: preparación escrita, cierre pendiente de herramientas y verificación.**
Fases 1–13 no iniciadas. La raíz estaba vacía y no era un repositorio Git.
El SVG original se conserva intacto.

**Eclipse Modeling Tools BASE VALIDADO — 23/09/2026.** Producto 2026-09
(4.41.0), build About 20260903-0720; Platform 4.41.0.v20260828-1142,
Windows x86_64. ZIP oficial de 694931027 bytes, SHA-512 oficial/calculado
coincidente; evidencia completa en [VERIFICACION_FASE_0](VERIFICACION_FASE_0.md).
Extraído sin UAC en
`C:\Users\alexxxjon\AppData\Local\Programs\Eclipse\eclipse-modeling-2026-09-R`.
Eclipse usa exclusivamente Temurin/JustJ 25.0.4.1+1-LTS embebido; JustJ feature
25.0.4.v20260826-1347 y runtime 25.0.4.v20260826-0822. Java/javac globales siguen
en Temurin 21.0.12.1 y JAVA_HOME en
`C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\`.
PATH, JAVA_HOME y eclipse.ini no fueron modificados.

EMF SDK 2.47.0.v20260704-1256, Ecore bundle 2.43.0.v20260704-1256,
Ecore Editor 2.20.0.v20260704-1256 y Ecore Tools 3.6.0.202604070657 presentes.
OCL incluido pero **NO utilizado**. Xtext runtime/UI/Xbase 2.44.0 parcialmente
presentes; **SDK completo AUSENTE**, al igual que ATL y Acceleo 4.
No se añadieron plugins ni se configuró Tycho.

Workspace externo: `C:\Users\alexxxjon\AppData\Local\EclipseWorkspaces\MDEdu-fase0`.
Eclipse y sus procesos asociados cerrados normalmente; instalación/workspace
conservados y ZIP temporal eliminado tras validar. No se creó/importó ningún
proyecto de trabajo ni metamodelo. EGit generó automáticamente metadata interna
`.org.eclipse.egit.core.cmp` fuera del repositorio y avisó que HOME no está definido:
advertencia menor, sin cambiar HOME ni configuración Git y sin cambios Git.
El diálogo Defender desapareció antes de automatizarlo; no se añadieron exclusiones
para Eclipse. Git quedó limpio en d36c77b antes de este cierre documental.
Solo se autoriza documentar, crear el commit de este checkpoint y publicarlo.
**Fase 1 no iniciada; Xtext SDK requiere una autorización posterior.**

**Runtime manual Spring Boot + PostgreSQL Compose VALIDADO — 22/09/2026.**
Desde backend/: `.\mvnw.cmd --batch-mode --no-transfer-progress spring-boot:run`.
Spring Boot 3.5.16 iniciado como usuario normal en 127.0.0.1:8080; Maven PID 24236,
Spring PID 18900, Java Temurin 21.0.12.1. Hikari conectó a localhost:5432/adaptativa con credenciales del
.env ignorado, sin revelar contraseña. Started AdaptativaApplication en 6.239 s.
Antes: public sin tablas. Flyway aplicó V1 bootstrap; después solo existe
flyway_schema_history, fila 1 / 1 / bootstrap / success=true.
SELECT 1=1; current_database/current_user=adaptativa. Health HTTP 200,
{"status":"UP"}; CORS permite http://localhost:5173; /actuator/env HTTP 404
sin trace, exception ni datos sensibles.
Maven/Spring detenidos, puerto 8080 libre; PostgreSQL detenido Exited (0),
docker ps vacío y volumen educativa-adaptativa_postgres_data conservado.
El código 1 de la sesión corresponde únicamente a Ctrl+C para detener el servidor;
el runtime fue validado correctamente. No se afirma cierre graceful por log.
Sin repetir verify ni cambiar código/configuración. Solo documentación pendiente
de cierre Git; sin staging/commit/push. Fase 1 no iniciada.

**Maven verify + Testcontainers VALIDADO — 22/09/2026.** Desde backend/:
`.\mvnw.cmd --batch-mode --no-transfer-progress verify`, salida 0, BUILD SUCCESS,
33.580 s. Wrapper 3.3.4 / Maven 3.9.16 / Temurin 21.0.12.1.
Surefire: CorsConfigurationTest 3/3; Failsafe: BackendBootstrapIT 4/4;
0 failures/errors/skipped, corroborados con los XML. Compilación incremental:
clases actualizadas, ninguna fuente recompilada en esta ejecución.
Testcontainers 1.21.4 usó PostgreSQL propio (postgres:17-bookworm, localhost:65390)
y Ryuk 0.12.0. Flyway aplicó V1; public contiene solo flyway_schema_history.
Health UP con contribuidor DB y sin detalles, /actuator/env cerrado y CORS health
para localhost:5173 comprobados. Contenedores temporales eliminados automáticamente.
Compose permaneció detenido, con volumen/red conservados. Spring Boot se inició
solo dentro de esas pruebas; el arranque manual posterior se validó arriba.
Sin cambios de código/configuración ni staging/commit/push en este paso.

**PostgreSQL Compose VALIDADO — 22/09/2026.** Servicio `postgres`, imagen
`postgres:17-bookworm`, PostgreSQL 17.11 (Debian 17.11-1.pgdg12+2), linux/amd64.
Digest descargado: `sha256:639ab7ceb90e13123085b741fb31ef493fba25463002f6da665352e7b534b652`.
Compose config --quiet terminó con código 0. `.env` local ignorado, contraseña
aleatoria externa no revelada ni versionada; database/user `adaptativa`.
Puerto publicado `127.0.0.1:5432 -> 5432/tcp`, coherente con Spring.
El contenedor `educativa-adaptativa-postgres-1` alcanzó running/healthy;
SELECT 1 devolvió 1 y current_database/current_user devolvieron adaptativa.
Schema public sin tablas; no se ejecutaron migraciones ni cambios manuales.
Al finalizar quedó **detenido, Exited (0)**. Volumen
`educativa-adaptativa_postgres_data` y red `educativa-adaptativa_default`
conservados, junto con ambas imágenes PostgreSQL y hello-world.
Docker sigue operativo. Maven verify y BackendBootstrapIT validados posteriormente;
Spring Boot manual también validado posteriormente; Fase 1 no iniciada.

**Docker Desktop4.91.0 instalado y VALIDADO**, per-user, backend WSL2 y contenedores
Linux. Ruta AppData/Local/Programs/DockerDesktop; hash oficial del instalador
comprobado. Usuario aceptó manualmente los términos; no se inició sesión por el
asistente. CLI/Engine29.8.0, API1.56, Compose5.5.1 y Buildx0.37.0 responden con
código0. Contexto desktop-linux, x86_64, 16 CPU, RAM7.412 GiB, overlayfs,
cgroupfs/v2 y kernel6.18.33.2-microsoft-standard-WSL2.
hello-world oficial linux/amd64: código0, contenedor eliminado mediante --rm;
docker ps -a vacío, imagen conservada. Sin reinicio pendiente ni errores críticos
observados. Ese paso no ejecutó PostgreSQL ni Compose; su validación posterior
figura arriba, junto con el verify posterior.

**WSL2: instalado y validado tras el reinicio manual.** Los tres indicadores de
reinicio están inactivos. wsl --version: **2.7.14.0**, kernel **6.18.33.2-2**,
código0. wsl --status: versión predeterminada **2**, código0; su aviso sobre WSL1
no requiere habilitarlo. Antes de Docker, wsl --list --verbose devolvió lista vacía;
ahora muestra únicamente docker-desktop Running versión2, distribución interna
administrada por Docker, sin distribución Linux personal. Get-WindowsOptionalFeature confirma VirtualMachinePlatform
Enabled y Microsoft-Windows-Subsystem-Linux, Hyper-V completo y HypervisorPlatform
Disabled. Firmware virtualizado y DEP habilitados; hipervisor activo confirmado
por CIM/systeminfo. SLAT estaba disponible antes de activar el hipervisor; sus
campos de capacidad actuales no se interpretan como un fallo de WSL2.
Validación WSL conservada sin actualización ni cambios manuales de características.
La instalación posterior de Docker usó ese backend, sin instalar distribución personal.

**Git2.55.0.windows.3 x64 instalado y repositorio local inicializado.** Ruta:
`C:\Program Files\Git\cmd\git.exe`. Identidad global y core.autocrlf global ausentes;
no se modificó configuración global. Rama normalizada a **main** e identidad
configurada únicamente aquí: **JonathanNevarez <neva_rez00@hotmail.com>**.
.gitignore y .gitattributes verificados; Wrapper sigue funcionando.
origin apunta a https://github.com/JonathanNevarez/MDEdu.git.
Los 98 archivos revisados están listados en [PRIMER_COMMIT](PRIMER_COMMIT.md).
Snapshot inicial creado y aceptado: **8262ec1**,
`chore: bootstrap validated development environment`; hash completo
`8262ec1f636b1928ff6f637b9d9ba983e5fcfa8d`. Se verificó working tree clean antes
del paso Playwright. Su evidencia quedó en el segundo commit **41315c5**,
`test: validate frontend e2e with chromium`. La validación WSL quedó en **e1df49e**,
`chore: validate wsl2 environment`. Docker quedó publicado en **dc7ad0a**,
`chore: validate docker desktop environment`. Antes de validar PostgreSQL,
main/dc7ad0a estaba limpio y sincronizado con origin/main en MDEdu.
PostgreSQL quedó publicado en f2cb080, punto de partida limpio del verify.
Verify quedó publicado en ab95344, punto de partida limpio del runtime manual.
Runtime quedó publicado en d36c77b, punto de partida limpio y sincronizado del
checkpoint Eclipse base. Este cierre autoriza únicamente los tres documentos
de Eclipse; `.env` y target/ siguen ignorados. Evidencia del cierre inicial en
`.git/FASE_0_SNAPSHOT.txt`. La política MDE generada se decidirá en Fase1.

**Node.js24.19.0 LTS x64 y npm11.17.0 instalados y verificados.** WinGet instaló
el paquete oficial en `C:\Program Files\nodejs\`; no se editó PATH manualmente.
Cumple `engines.node >=24.15.0 <25`. `npm.cmd install` instaló 114 paquetes,
auditó 115 y reportó 0 vulnerabilidades; generó `frontend/package-lock.json`.
TypeScript y Vite8.3.0 compilan; Vitest5.0.1 ejecutó 1 archivo con 2 pruebas
correctas, 0 fallidas y 0 omitidas. Vite respondió HTTP200 en
`http://127.0.0.1:5173`, con HTML y raíz React comprobados. El servidor quedó
detenido y el puerto libre. `dist/` contiene HTML, CSS y JS (262958 bytes).
La revisión básica de fuentes/configuración no encontró secretos; no existen
archivos `.env*` en frontend. No cambió código, configuración ni versiones.

FRONTEND BUILD: **OK**. FRONTEND UNIT: **OK**. FRONTEND HTTP: **OK**.
FRONTEND E2E CHROMIUM: **OK**, verificado el 22/09/2026.
Playwright1.63.0 instaló Chromium/Headless Shell153.0.8010.12, revisión1243,
FFmpeg1011 y Winldd1007 en la caché local ms-playwright (739893945 bytes).
`npm.cmd run test:e2e` terminó con salida0: 1 archivo, 1 prueba aprobada,
0 fallos/omitidas, 2.5s total Playwright (546ms escenario). React y recuperación
de ruta se verificaron en navegador real; pageerror vacío. console.error no se
captura por separado. TypeScript/Vite se recompilaron; unitarias no se repitieron.
Procesos de Chromium/Playwright/Vite cerrados y puertos4173/5173 libres.
test-results/.last-run.json ignorado, estado passed; sin reporte HTML ni trace
de fallo. Fase0 permanece abierta por las verificaciones backend/DB pendientes.

**Maven3.9.16 instalado en el perfil de usuario y Wrapper3.3.4 preparado.** Ambos
usan Temurin21.0.12.1. Se verificaron SHA-512 de instalación, rutas, POM efectivo,
validate, clean compile y test. Compilación: 42 fuentes release21. Surefire: 3
pruebas MVC, 0 fallos, 0 errores, 0 omitidas. Solo se añadió Maven/bin al PATH del
usuario; JAVA_HOME y PATH de máquina permanecen intactos. El POM conserva su hash.

BACKEND BUILD: **OK**. BACKEND RUNTIME: **OK**, health/CORS/Flyway contra DB Compose real; servicios detenidos.
El Wrapper fija versión y hash y es la vía preferida para tareas normales.
WSL2 y Docker validados. Eclipse base validado; SDK Xtext, ATL y Acceleo 4 pendientes.

**JDK21 instalado y verificado con autorización exclusiva para Java.** WinGet
instaló Temurin x64 (paquete 21.0.12.101); java y javac devuelven **21.0.12.1**.
JAVA_HOME persistente apunta a
`C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\` y su bin está en PATH.
Get-Command y where.exe coinciden en una única ruta por ejecutable. Las comprobaciones
se hicieron en una nueva instancia PowerShell tras refrescar el entorno del proceso.
El instalador configuró variables; no hubo modificaciones globales manuales.

Prueba real: `javac JavaEnvironmentTest.java` y `java JavaEnvironmentTest`, ambos
con código 0 y salida `Java environment OK`. Fuente, clase y carpeta temporal
eliminadas. Esa autorización de Java quedó aceptada antes de preparar Maven.

Antecedente, antes de autorizar Java: **Paso 1 completado como diagnóstico**, sin instalar
herramientas ni iniciar servicios. WinGet funcional: 1.29.290; Windows PowerShell:
5.1.26100.9444. Chocolatey/Scoop no encontrados. Git, Java/javac, Maven/Wrapper,
Node/npm y Docker/Compose estaban no disponibles. WSL informó que no estaba instalado.
JAVA_HOME estaba ausente y `.git` no existía. El catálogo WinGet resolvió versiones
concretas de instaladores; eso no implica que estén instalados o probados.

La propuesta de las herramientas todavía pendientes está en
[HERRAMIENTAS](HERRAMIENTAS.md). No hubo cambios de arquitectura.

## Histórico: entregables registrados

- Inspección de raíz, herramientas, configuraciones y marco conceptual.
- Plan secuencial con riesgos, criterios y alcance exacto de F0.
- Arquitectura modular con MDE clásico, contratos y decisiones documentadas.
- Configuración frontend/backend y pruebas iniciales escritas.
- Compose PostgreSQL, variables sin secretos, migración de bootstrap y health.
- Carpetas MDE reservadas; referencia SVG preservada mediante copia idéntica.
- Maven Wrapper con versión/hash fijados; backend compilado y pruebas MVC verificadas.
- Node/npm verificados, lock generado; frontend compilado, pruebas unitarias y HTTP verificados.
- Git instalado; repositorio local, exclusiones, atributos y lista del primer commit preparados.
- Snapshot Git 8262ec1 aceptado; E2E Chromium ejecutado sin cambios al scaffold.

Estos entregables no son funcionalidades educativas terminadas. No hay modelo
EMF generado, Blockly, intérprete, reglas, LLM ni IU adaptativa aún.

## Histórico: pruebas y comprobaciones al 23/09/2026

Ver [VERIFICACION_FASE_0](VERIFICACION_FASE_0.md) para comandos y resultados.
La instalación Java cuenta ahora con comprobación real de versión, rutas, variables,
compilación y ejecución. La prueba mínima del JDK no sustituye las pruebas de la app.
Las revisiones JSON/XML y coherencia de archivos se separan de la ejecución real.
Revisión estática de la entrega inicial superada: 3 JSON, 2 XML/SVG, 118 enlaces locales y 44
correspondencias de paquetes Java/rutas; copia SVG idéntica al original y sintaxis
del comprobador PowerShell correcta. Informe sin fallos estáticos detectados.
Pruebas preparadas: 2 casos de frontend con RTL, 1 smoke Playwright, 3 invocaciones
MVC de CORS y 4 pruebas de integración PostgreSQL/Actuator/Flyway.
**Las 3 pruebas MVC, las 2 unitarias frontend y el escenario E2E Chromium pasaron.**
Las 4 pruebas de integración BackendBootstrapIT pasaron durante Maven verify,
contra PostgreSQL temporal de Testcontainers. PostgreSQL Compose permaneció
detenido durante verify. No se deshabilitaron pruebas. El runtime manual posterior
también pasó, con arranque y apagado controlados de Spring y Compose.

| Criterio obligatorio de F0 | Estado |
| --- | --- |
| JDK21/javac, JAVA_HOME, PATH y compilación mínima | Completado: Temurin21.0.12.1 x64; salida Java environment OK |
| Maven/Wrapper, POM efectivo y validate | Completado: Maven3.9.16, Java21.0.12.1, salidas0 |
| Git y repositorio | Verificado al inicio del runtime: main/ab95344 publicado en origin MDEdu; identidad local |
| WSL2 sin distribución personal | Validado: WSL2.7.14.0, kernel6.18.33.2-2, predeterminado2; VMP Enabled e hipervisor activo; sin reinicio pendiente |
| Docker Desktop per-user / WSL2 | Validado: Desktop4.91.0, Engine29.8.0, Compose5.5.1, hello-world salida0 |
| Frontend compila y pruebas unitarias pasan | Completado: TypeScript/Vite; 1 archivo, 2 pruebas correctas |
| Frontend inicia y responde HTTP | Completado: HTTP200 en 127.0.0.1:5173; servidor detenido |
| Frontend E2E en Chromium | Completado: Playwright1.63.0, Chromium153.0.8010.12/r1243; 1 aprobado, salida0 |
| Backend compila y pruebas sin Docker pasan | Completado: 42 fuentes; 3 pruebas MVC, 0 fallos/errores/omitidas |
| Backend: 4 pruebas de integración | Validado: Maven verify BUILD SUCCESS; BackendBootstrapIT 4/4, sin fallos/errores/omitidas |
| PostgreSQL inicia y acepta consulta | Validado: 17.11, healthy, SELECT 1 = 1; detenido al finalizar, volumen conservado |
| Backend inicia y health comprueba DB | Validado: runtime manual 8080, health UP, Hikari/PostgreSQL, Flyway V1 y CORS; servicios detenidos |
| Documentación y estructura creadas | Completado |
| Eclipse Modeling Tools base | Validado: 2026-09 R / Platform 4.41, JustJ 25 embebido, Java 21 global intacto, EMF/Ecore/Ecore Tools presentes; IDE cerrado |
| Features MDE adicionales | Xtext SDK completo, ATL y Acceleo 4 ausentes; OCL incluido pero no utilizado; Tycho sin configurar |

## Histórico: problemas conocidos al 23/09/2026

1. WSL2, Docker, PostgreSQL Compose, BackendBootstrapIT y runtime manual validados;
   Eclipse base también validado; Xtext SDK, ATL y Acceleo 4 siguen pendientes.
   JDK21, Maven/Wrapper, Node/npm y Git ya verificados.
2. La política local de PowerShell bloquea scripts `.ps1`; el script opcional de
   inspección no se pudo ejecutar. La inspección mediante comandos directos sí
   se realizó. `npm.ps1` también está bloqueado; se usa `npm.cmd`, sin cambiar la política.
3. Package-lock generado por npm y validado. Digest PostgreSQL descargado registrado;
   el tag y Compose se conservan sin modificaciones.
4. Integración backend/DB comprobada en pruebas y runtime manual; LLM/MDE pendientes. E2E frontend completado.
5. Matriz MDE aceptada: Eclipse 2026-09 R, Xtext 2.44.0, ATL 4.12.0.v202505101449,
   Acceleo 4.2.2 y Tycho 5.0.4 futuro. Solo Eclipse base validado; las validaciones
   de features adicionales siguen pendientes. OCL incluido, uso diferido.
6. Spring Boot 3.5.16 tiene límite de soporte OSS documentado; revisar antes de
   desplegar y mantener el requisito de pruebas del proyecto.
7. Connection reset al descargar Actuator3.5.16, resuelto al reintentar sin cambiar
   versión. Avisos Mockito/Byte Buddy sobre carga dinámica en Java21, sin fallos;
   no se alteró el POM para ocultarlos.
8. El primer build frontend falló por `spawn EPERM` del aislamiento. El mismo
   comando con permisos de ejecución pasó, sin correcciones al scaffold.
   npm avisó de 22 paquetes con financiación y de una nueva versión mayor;
   no se actualizó npm ni se ejecutó audit fix.
9. E2E emitió aviso NO_COLOR/FORCE_COLOR de Node, sin fallo. La prueba captura
   pageerror, pero no console.error por separado; no se amplió su alcance.
10. EGit avisó HOME no definido y creó metadata interna fuera del repositorio;
    no es un error del proyecto ni requiere cambiar variables en este checkpoint.

## Histórico: siguiente paso previsto el 23/09/2026

**Eclipse Modeling Tools base validado. Este paso cierra únicamente su documentación
en Git con el commit `chore: validate eclipse modeling tools environment` y push a main.**
Después, detenerse y esperar autorización independiente para Xtext SDK.
No instalar Xtext, ATL ni Acceleo; no configurar Tycho/OCL, crear proyectos o
metamodelos ni reiniciar servicios en este cierre.
**No avanzar a Fase 1 hasta cerrar Fase 0.** No hay cambios destructivos propuestos.
