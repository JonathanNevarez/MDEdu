# Verificación Fase 11 — evidencia del 02/10/2026

Checkpoint inicial: `6215bf8 feat: add gemini llm provider`, hash completo
`6215bf87c821bc3b446d801e3d28d913eff359cc`. main=origin/main y working tree limpio
antes de empezar. No reset/restore/clean/stash ni cambios de identidad/remoto.

## Validaciones backend y DB

- `backend/mvnw.cmd --batch-mode --no-transfer-progress test`: 258 pruebas PASS.
- `backend/mvnw.cmd --batch-mode --no-transfer-progress verify`: 258 unit/MVC + 74 IT PASS.
- `TelemetryDomainTest`: 3 pruebas (roundtrip/hash, límites/tipo, request ID seguro).
- `TelemetryIT`: 11 pruebas (flujo LOOPS, éxito/repetición/NO_ADAPTATION, sesiones múltiples,
  append-only/idempotencia, concurrencia, rollback, legacy/gap, logs/sentinels,
  sesión inválida antes de ejecución, referencia de feedback ajena, API/correlación).
- Regresiones existentes: BackendBootstrapIT 4, AdaptationIT 3, AdaptationManagerIT 8,
  FeedbackIT 13, GeminiFeedbackIT 16, GeminiNoKeyFeedbackIT 1, NoKeyFeedbackIT 1,
  OpenAIFeedbackIT 1, LearningIT 10 y UiConfigurationIT 6: PASS.
- PostgreSQL limpio Testcontainers ejecutó V1–V7; assert de tablas/versiones PASS.

V7 `V7__telemetry_and_sessions.sql`: dos tablas nuevas (sessions, attempt_events),
FKs a entidades existentes, CHECKs de tipos/source/version/tamaño/hash, UNIQUE
attempt/sequence y student/event_key; trigger attempt_events_append_only rechaza
UPDATE/DELETE. Índices nuevos explícitos: sessions_student, events_session_sequence
(parcial), events_session, events_student, events_occurred. PK/UNIQUE también crean
sus índices. V1–V6 permanecen idénticas.

## Evidencia funcional y coherencia

LOOPS manual conserva functional=true, activityPassed=false y
REPETITIVE_SEQUENCE_WITHOUT_LOOP. La reconstrucción contiene core ordenado,
mastery delta, decisión original, fallback DISABLED (providerCallCount=0), UI y navegación.
El caso correcto no inventa selectedRule cuando NO_ADAPTATION; el segundo fallo conserva
su propio mastery delta y patrones repetidos, sin mezclar eventos.

COMPLETE: todos los hitos críticos nuevos presentes. Sin feedback solicitado sigue
siendo válido y devuelve feedback vacío. Legacy: PARTIAL, eventos vacíos y referencias
persistidas separadas. Fixture con hueco de secuencia: INCONSISTENT sin 500. Referencia
feedback de otro estudiante: INCONSISTENT y texto ajeno excluido. No hay backfill.

La reconstrucción no llama GameExecutionService ni AdaptationManager: spies sin
interacciones durante lectura. No invoca provider ni genera código. La decisión y sus
versiones/hashes se leen de filas persistidas; fingerprints iguales al resultado original.
La UI safeDefault también queda identificada explícitamente en un evento.

Mismo feedback/fingerprint UI repetido: un evento final. Dos inserciones simultáneas:
secuencias diferentes y ordenables; retry con misma event_key/payload no crea otra fila.
Se forzó un fallo INSERT de EVALUATION_COMPLETED en un trigger de fixture: rollback
completo, cero attempts y StudentModel previo idéntico. El trigger de fixture se retiró.
Los triggers de inmutabilidad solo se desactivan temporalmente en el test explícito de
corrupción, nunca en la aplicación ni en datos de usuario.

Sesiones distintas del mismo estudiante agrupan intentos correctamente. Sesión ajena o
inexistente se rechaza antes de ejecutar. Consultas de attempt/session ajeno: 404; el
cliente no puede afirmar EVALUATION_COMPLETED. Correlación HTTP devuelve X-Request-Id
válido, reemplaza valores arbitrarios y limpia/restaura MDC.

Logs capturados contienen requestId/sessionId/attemptId. Cero apariciones de sentinels
de nombre, programa, prompt y clave ficticia. Los payloads no contienen programas,
workspaces, traces completas, prompts, respuestas LLM crudas ni snapshots MDE.
FAKE está identificado como FAKE/llmUsed=false; Gemini HTTP local como GEMINI/true.
OpenAI y Gemini sin clave conservan fallback. Los providers permanecen intactos.

## Recursos y comandos

Tareas pesadas secuenciales, MAVEN_OPTS=-Xmx384m y argLine=-Xmx384m; Node 384 MiB,
Vitest/Chromium un worker. No ajustes globales Windows, RAM, WSL, Docker ni instalaciones.
Logs reales en TEMP/mdedu-phase11-*.log, no versionados. Los resultados de navegador
se guardan fuera del historial en frontend/test-results y TEMP.

MDE clean verify: PASS (39.247 s); Xtext 22, Acceleo 25, ATL 2. Ningún módulo MDE cambiado.
Frontend final: typecheck PASS; 61 pruebas en 8 archivos PASS; build PASS.
Chromium DISABLED: 9 E2E PASS en 50.7 s; el test opt-in Gemini se omite deliberadamente
hasta arrancar su mock. Chromium Gemini local: 1 E2E PASS en 7.5 s, con sesión real,
feedback GEMINI/llmUsed=true, timeline COMPLETE y UI/Luma operativa. Sin API real.
Acceleo CLI: BUILD SUCCESS, Diagnostic OK; node --check exit 0. SHA-256 generado:
`15f3d7558138343c89e9fd69ced3921d51e52f53ef32c08bb49105fdec09bfe8`, intacto.

El E2E DISABLED crea un intento LOOPS desde el editor real y captura la respuesta:
X-Session-Id coincide con sessionStorage y X-Request-Id es UUID. Tras terminar la sesión
por API y recargar, obtiene una sesión nueva para el mismo estudiante. La consulta
repetida de timeline es idéntica y el acceso desde otro estudiante retorna 404.

La timeline real DISABLED contiene 15 eventos, en este orden:

1. ATTEMPT_CREATED
2. PROGRAM_MODEL_CREATED
3. PROGRAM_MODEL_VALIDATED
4. EXECUTION_STARTED
5. EXECUTION_COMPLETED
6. EVALUATION_COMPLETED
7. ERROR_PATTERN_DETECTED
8. STUDENT_MODEL_UPDATED
9. ADAPTATION_STARTED
10. ADAPTATION_COMPLETED
11. UI_CONFIGURATION_CREATED
12. FEEDBACK_REQUESTED
13. FEEDBACK_FALLBACK_USED
14. UI_CONFIGURATION_CREATED
15. NAVIGATION_PRESENTED

Este orden refleja la aplicación real: consulta UI, solicita feedback y vuelve a consultar.
Ambas configuraciones tienen versión 1. El feedback identifica DISABLED/FALLBACK y cero
intentos de provider. La apertura previa real está vinculada a la misma sesión.
JSON/capturas reales: TEMP/mdedu-phase11-disabled-evidence y
TEMP/mdedu-phase11-gemini-evidence, fuera de Git.

Cierre operativo: backend propio detenido; PostgreSQL detenido con compose stop,
volumen conservado; preview/Chromium cerrados; Docker Desktop detenido. No down -v ni
eliminación de componentes WSL. No hubo llamadas a APIs LLM reales ni claves reales.

Incidencias: sin bloqueos ni fallos funcionales en la validación final. Se contemplaron
la sesión obsoleta en pestaña abierta, el orden de locks al cerrar sesión y la lectura
de decisiones históricas sin consultar reglas actuales. Persisten advertencias conocidas
de Mockito/agent, NO_COLOR/FORCE_COLOR y chunk Blockly; no impidieron pruebas/build.
No se investigan ni se atribuyen causas a los apagados históricos del equipo.

## Hashes protegidos

360 archivos protegidos comparados con baseline previo: cero diferencias. Incluye todo
MDE versionado, reglas/manager/parameters de adaptación, StudentModel policy, providers
LLM, recursos LLM y migraciones V1–V6. Los siguientes hashes coinciden además con los
valores explícitos de la solicitud.

| Archivo | SHA-256 |
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

Migraciones históricas (sin cambios):

| Archivo | SHA-256 |
|---|---|
| `V1__bootstrap.sql` | `4663db0737c03a53e4ce76b467d56492a275d786648eec80ec0ae2839b55fe62` |
| `V2__learning_model.sql` | `a4d2c3d6e83156632d9d18699bfb3ba86b361b0fe96972756a82d75d7317f1ee` |
| `V3__adaptation_decisions.sql` | `61a6ea358ed941a73ac798620765154f2f02dedea415f5ce7e10fa65822f4825` |
| `V4__feedback_records.sql` | `0e195e93055157f33e9f7554fad4f9aa5ada598c0b2f1655bada7f7df16ebc9a` |
| `V5__attempt_programs.sql` | `bf30ab6bc1d88bd6ce639c7793b0e8306b23bc2cb80abfd06edc2ea9c88db774` |
| `V6__gemini_feedback_provider.sql` | `5012bf4bb4105649438b25b123277071e2b0a13c10f537639e257f93f81ea8cb` |

## Revisión de publicación

Escaneo de archivos versionados/nuevos: cero claves reales con formato de secreto.
Se revisaron marcadores de Gemini/OpenAI y Authorization: solo identificadores, valores
vacíos, ejemplos y claves ficticias de pruebas. Los logs de verificación y ambos runtimes
no contienen las claves/sentinels de privacidad. `git diff --check`: exit 0.
No se incluyen target, dist, node_modules, .env, logs, raw traces ni resultados Playwright.

## Alcance conservado

Cuatro actividades principales: SEQUENCES, VARIABLES, CONDITIONALS, LOOPS.
Cero actividades reales de refuerzo. Cero temas avanzados. Sin cambios de pedagogía,
metamodelos, gramática, ATL, Acceleo, prompts, policies o providers. Fase 12 no iniciada.

Commit autorizado al cerrar: `feat: add attempt telemetry and audit timeline`.
Solo main, sin amend/rebase/force. Hashes definitivos se reportan después del push.
