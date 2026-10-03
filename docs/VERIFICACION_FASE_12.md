# Verificación Fase 12 — 02/10/2026

Checkpoint inicial: `c5e892bad6fc72deb45402e5afde837a6d38f6cd`.
main=origin/main y working tree clean verificados antes de implementar.
Sin reset/restore/clean/stash ni alteración de commits anteriores.

## Pruebas backend y base de datos

- `backend/mvnw.cmd --batch-mode --no-transfer-progress test`: **261 PASS**.
- `backend/mvnw.cmd --batch-mode --no-transfer-progress verify`: **261 unit/MVC + 80 IT PASS**.
- Nuevas: AdaptationInspectionTest 3 y MetaUiIT 6.
- MetaUiIT: LOOPS real, igualdad StudentModel/decisión/fingerprint/timeline antes y después;
  spies sin llamadas a ejecución/manager/feedback/UI durante las consultas;
  eventos y decisiones sin mutaciones; fuente/hash runtime; separación A/B; paginación;
  pseudónimos sin displayName; 404; capabilities; PUT/PATCH 405; legacy PARTIAL;
  secuencia corrupta INCONSISTENT; ausencia de feedback/regla; vacíos y esquema.
- Fixture aislado cambia nombre/prioridad/enabled de una regla copiada: endpoint/service
  proyecta la fuente inyectada. La regla real no cambia. Fixture de Values V42 con valores
  diferentes devuelve exactamente esos valores y su hash; no hay lista hardcodeada.
- Conservados los 74 IT anteriores: AdaptationIT 3, AdaptationManagerIT 8,
  BackendBootstrapIT 4, FeedbackIT 13, GeminiFeedbackIT 16, GeminiNoKeyFeedbackIT 1,
  NoKeyFeedbackIT 1, OpenAIFeedbackIT 1, LearningIT 10, TelemetryIT 11, UiConfigurationIT 6.
- Testcontainers PostgreSQL limpio: **V1–V7**, cuatro learning_activities, cero refuerzos.
  No V8 ni cambio de migraciones. La corrupción de fixture solo ocurre en su contenedor
  de test y restaura el trigger en finally; nunca en los datos locales del usuario.

## Fuentes activas comprobadas vía HTTP

Seis reglas, rulesetVersion=1, todas enabled=true, evento ATTEMPT_EVALUATED:

| Regla | Prioridad | Condición |
|---|---:|---|
| ReforzarCiclos | 80 | concept == LOOPS AND consecutiveFailures >= 3 |
| ErrorRepetido | 85 | repeatedErrorPattern == true |
| DominioAlto | 60 | masteryScore >= 0.80 |
| PistasExcesivas | 50 | hintCount >= 5 |
| TiempoAlto | 40 | currentResolutionTime > 300000 |
| FuncionalSinConcepto | 90 | functionalPassed == true AND requiredConceptUsed == false |

Acciones parseadas: SHOW_HINT, REPEAT_ACTIVITY, SELECT_REINFORCEMENT_ACTIVITY,
ADVANCE_TO_NEXT_CONCEPT, CHANGE_HINT_LEVEL y CHANGE_FEEDBACK_STYLE, con parámetros
CONCEPTUAL/GUIDED/EXPLANATORY donde corresponden; sin añadir acciones inexistentes.

rulesetHash runtime: `7d073441a373a915454ed4c954727d50eee0ffcbba00509615cf4f5555c86c9e`.
parametersVersion=1; parametersHash runtime:
`13cac42690039160bb6fb3193f17e3efaed2a05f25750c55ea0cdccb29ad3b6d`.
Valores reales: hintLevel=CONCEPTUAL, difficultyAdjustmentEnabled=true,
routeAdaptationEnabled=true, feedbackDetail=STANDARD, failureThreshold=3,
successThreshold=1, masteryThreshold=0.80, maxHintsPerActivity=5,
maxAttemptsBeforeReinforcement=3. El hash canónico runtime no es el hash binario del archivo.

SHA-256 de archivos antes=después:

- rules-v1.adapt: `b4830f6c6e2f9cac681649c64eddccb42bc5b8903413f8d934271237c9c49f70`.
- adaptation-parameters.v1.json: `173330ef2ceb7960efb9f5c47bd6d1b5d6230f5eff1ab11b8e538585f0f88d62`.

Endpoints y DTOs: [API](API.md#fase-12--meta-iu-de-solo-lectura-implementada).
No nombres/correos, raw prompts, raw responses ni claves en el contrato docente.
No auth/roles: read-only y limitación de acceso explícita, sin seguridad ficticia.

## Frontend y navegador

`npm.cmd test -- --maxWorkers=1`: **67 PASS**, nueve archivos.
Typecheck PASS. Build Vite PASS; MetaUiPage cargada como chunk independiente.
Se cubren valores de reglas/parámetros, respuestas tardías al cambiar A/B, vacíos,
loading/error seguro, secuencia y expansión, PARTIAL/INCONSISTENT y providers
OPENAI/GEMINI/FAKE/DISABLED con texto escapado. No switch ni Guardar ficticios.

El E2E docente a 1440×900 crea dos estudiantes, completa prerrequisitos y envía LOOPS
manual: functional=true, activityPassed=false, REPETITIVE_SEQUENCE_WITHOUT_LOOP,
FuncionalSinConcepto, SHOW_HINT/REPEAT_ACTIVITY, FALLBACK, UI fingerprint y COMPLETE.
Recorre modelo/intentos/adaptaciones/reglas/parámetros. Cambiar a B muestra vacío.
Captura peticiones del navegador: cero mutaciones; modelo y timeline iguales antes/después.
Consultar timeline B/A devuelve 404. Capturas modelo/timeline/reglas/parámetros y JSON
se guardan en resultados ignorados, con copia en TEMP al terminar.

La timeline se carga bajo demanda; traceStatus se muestra en fila después de consultar,
y antes figura Pendiente de consulta. No se cargan eventos de todos para listar.
feedbackDetailLevel histórico se identifica explícitamente como no registrado: consultar
no vuelve a proyectar UI ni inventa el dato ausente de UiPayload V1.

Validación final Chromium: **10 E2E DISABLED PASS (49.4 s)**; Gemini omitido solo en
esa suite por ser opt-in. Suite separada **1 Gemini HTTP local PASS (8.6 s)**:
recorre aventura y luego `/docente`, mostrando GEMINI / GEMINI y llmUsed=true.
No llamadas remotas reales. Las 67 pruebas frontend volvieron a pasar después del
ajuste de la región accesible y la suite completa DISABLED se repitió sin fallos.
Capturas revisadas visualmente: modelo, timeline; reglas/parámetros guardados por E2E.

## MDE y regresiones

`mvn.cmd --batch-mode --no-transfer-progress clean verify` en mde: PASS (30.278 s).
Xtext **22**, Acceleo **25**, ATL **2** PASS.
CLI `exec:exec` sequence-basic.programming → target/phase12-cli: Diagnostic OK,
Acceleo files=1; `node --check` exit 0. JS SHA-256:
`15f3d7558138343c89e9fd69ced3921d51e52f53ef32c08bb49105fdec09bfe8`.

Fases 5/6/8/9/Gemini/10/11 regresan mediante todas sus suites existentes: PASS.
Sin modificaciones pedagógicas, de providers, templates, modelos o gramática.

**474 archivos existentes** bajo mde/ y backend/src/main/ idénticos al baseline.
También se validaron los 17 hashes explícitos MDE/LLM y seis hashes históricos V1–V6
contra evidencia anterior; V7 coincide con baseline. Cero diferencias.

| Archivo | SHA-256 conservado |
|---|---|
| `mde/com.project.mde.programming.model/model/programming.ecore` | `6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df` |
| `mde/com.project.mde.programming.model/model/programming.genmodel` | `b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451` |
| `mde/com.project.mde.learning.model/model/learning.ecore` | `3b7e10dff04fc7af3ec91649986322271023afb4207dd57de3c9f13f4fa2eb45` |
| `mde/com.project.mde.learning.model/model/learning.genmodel` | `1deea0b68799173dd419a227408fb9a33aecf8e49bfe20cb662de0a1926297be` |
| `mde/com.project.mde.adaptation.model/model/adaptation.ecore` | `ed3f63b5227e07421f696a04690becb4778758ed7e3ed7c0c39d9ddb58eb8243` |
| `mde/com.project.mde.adaptation.model/model/adaptation.genmodel` | `b22b13a6ce00657c86fa87d6ab7d99199b04b1f1bd291c6e32e019519d2c68ff` |
| `mde/com.project.mde.context.model/model/context.ecore` | `4d9ccb0d4f7920c4ed1b970d7b589eb8410ae914c3a97390b5b03479efd36741` |
| `mde/com.project.mde.context.model/model/context.genmodel` | `27b1191776acf0fb8f5bf19880b71210f108647bcff2d150dabb0a44e0073079` |
| `mde/com.project.mde.ui.model/model/ui.ecore` | `07f35993cc78d88177ba1014ac5d00a19a2369a10094f30a145342fb6afc6be5` |
| `mde/com.project.mde.ui.model/model/ui.genmodel` | `62f87f852ba2e193b49d83ba3ea8665ce4424a77c601b3a06641b730e669ce32` |
| `mde/com.project.mde.adaptation.dsl/src/main/java/com/project/mde/adaptation/dsl/AdaptationRules.xtext` | `df841202185952e797651068514e958fad04090ffd6b4eaf1a0dd8cd3ad4d683` |
| `mde/com.project.mde.ui.transformations/transformations/AbstractUI2ConcreteUI.atl` | `a39f7761588aa7ed5327da69fea421c10fd27d8159eeaef912ac97541a293a64` |
| `mde/com.project.mde.ui.transformations/transformations/TaskAndDomain2AbstractUI.atl` | `65679185d34feb210013c1fcb4f5d381d450f7d3826706fd0316cf092bf69d69` |
| `backend/src/main/resources/llm/prompts/pedagogical-feedback-v1.txt` | `a6c7bf084a58569fc74c9f2587d126c88716867af7c798d284159722bae63eda` |
| `backend/src/main/resources/llm/prompts/unknown-case-v1.txt` | `2f08841297b50a00f8985c211720e4ab6bd60ab27d3277491f230ec0505ca4c4` |
| `backend/src/main/resources/llm/llm-policy.v1.json` | `08c3986b31b2e1a1381baae8f4f1ac71a283135fb93f136f311261d274dbaf4a` |
| `backend/src/main/resources/llm/unknown-case-tags.v1.json` | `9f7e5209cbd1faae8f372d1c4c4bc3c8c83706603b08132374ef9e6df94a57e2` |

## Incidencias reparadas y límites

Se corrigieron dependencias de construcción del fixture de regla y un BOM introducido
por PowerShell en ese test, además de un atributo de locator de Testing Library no válido.
El primer E2E docente detectó un selector ambiguo de fingerprint (evento y resumen UI);
se añadió región accesible de configuraciones UI y el selector se acotó a esa región.
La repetición focalizada pasó. Sin cambios pedagógicos para resolver las pruebas.
Advertencias preexistentes: Mockito/agent, NO_COLOR/FORCE_COLOR y tamaño de chunk Blockly.

Control de acceso robusto pendiente de hardening final. No se inicia Fase 13.
No se utilizaron APIs LLM reales ni se instalaron componentes nuevos.

## Evidencia local

TEMP/mdedu-phase12-backend-test.log, backend-verify.log, frontend-test.log,
typecheck.log, disabled-e2e.log, metaui-e2e.log, gemini-e2e.log, mde.log, cli.log;
baseline.json, protected-check.json y secret-scan.json. Logs/JSON/capturas fuera de Git.
Heaps Java/Maven/Node 384 MiB, un worker Vitest/Playwright y suites pesadas secuenciales.
No se cambiaron ajustes globales ni se atribuyó una causa a apagados históricos del equipo.

Publicación autorizada: `feat: add teacher meta ui`, únicamente main, sin force/amend/rebase.
Los hashes definitivos de commit/remoto se informan al finalizar, sin autorreferencias.

## Cierre operativo y revisión

Escaneo de archivos versionados/nuevos: cero claves reales detectadas; los marcadores
pertenecen a nombres de configuración, valores vacíos y fixtures. Cero apariciones de
sentinels de PII, programa, raw prompt o clave dummy en verify y ambos runtimes.
`git diff --check` exit 0. No se versionan .env, target, dist, node_modules, logs,
capturas temporales, test-results ni playwright-report.
Backend de prueba detenido, PostgreSQL detenido con volumen conservado y Docker Desktop
cerrado; no reinicio ni cambios globales. Copias de evidencia en
TEMP/mdedu-phase12-disabled-evidence y TEMP/mdedu-phase12-gemini-evidence.

FASE 12 COMPLETADA Y VALIDADA. FASE 13 NO INICIADA.
