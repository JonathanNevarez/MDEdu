# Verificación de Fase 9 — LLM controlado

Fecha: 2026-10-02. Checkpoint inicial:
`a0afbe6306eef3eac14093fd457247b30110ade6`.
main/origin/main sincronizadas y árbol limpio antes de iniciar.

## Resultado y pruebas reales

| Validación ejecutada | Resultado |
| --- | --- |
| backend: mvnw --batch-mode --no-transfer-progress test | 227 tests, 0 fallos, 0 errores, 0 skips; BUILD SUCCESS, 17.644 s |
| backend: mvnw --batch-mode --no-transfer-progress verify | 227 unit/MVC +40 IT; BUILD SUCCESS, 56.464 s |
| BackendBootstrapIT | 4 PASS; V1–V4 en PostgreSQL limpio |
| LearningIT | 10 PASS |
| AdaptationIT | 3 PASS |
| AdaptationManagerIT | 8 PASS |
| FeedbackIT | 13 PASS |
| NoKeyFeedbackIT | 1 PASS; OPENAI sin key arranca y funciona |
| OpenAIFeedbackIT | 1 PASS; seis escenarios HTTP reales contra loopback |
| LlmDomainTest, incluido en unit | 13 PASS |
| OpenAILlmProviderTest, incluido en unit | 13 PASS |
| mde: mvn --batch-mode --no-transfer-progress clean verify | BUILD SUCCESS, 31.774 s; 25 Acceleo +22 Xtext |
| frontend: npm run typecheck | PASS |
| frontend: npm test | 44 PASS, cinco archivos |
| frontend: npm run build | PASS |
| frontend: npm run test:e2e -- --workers=1 | 5 PASS, 34.2 s |
| Acceleo CLI sequence-basic.programming | BUILD SUCCESS |
| node --check target/phase9-regression/program.js | código 0 |

No se añadieron dependencias. HttpClient/JDK21 y HttpServer local de tests.
No skips, API pagada ni modificación de frontend. Los tests de proveedores no
necesitan Internet; builds pueden resolver dependencias existentes de Maven/npm.
La regresión MDE/Acceleo se ejecutó con herramientas ya instaladas.

## Contrato y proveedor HTTP

Servidor local comprueba POST /v1/responses, modelo configurable,
Authorization Bearer ficticio (valor nunca impreso), text.format json_schema,
strict=true, required, additionalProperties=false, store=false y ausencia de tools.
Schemas de feedback y clasificación comprobados mediante HTTP real, no solo mock
de la interfaz. Contexto sin nombre/UUID/key; instrucciones separadas de los datos.

Cobertura: salida válida, JSON interno inválido, envelope inválido, incomplete,
output vacío, refusal, timeout de cuerpo, conexión rechazada, body >64 KiB, 429/500/503,
400/401/403. Transitorios hasta tres intentos; permanentes una sola petición.
Clasificación valida tags, alcance, recognized, confidence 0..1 y threshold .70.
Low confidence=.40 y tag I_INVENTED_THIS rechazados con fallback sin tags.

OpenAIFeedbackIT utiliza OpenAILlmProvider real contra servidor loopback dentro de
Spring+PostgreSQL. Valida source=OPENAI, llmUsed=true en respuesta aceptada simulada;
source=FALLBACK en JSON inválido, 429, 503, 401 y timeout. En cada caso conserva
AdaptationDecision completa y StudentModel. No hubo llamada a api.openai.com.

Fake es reproducible, sin red; modes VALID, INVALID_JSON, UNKNOWN_TAG, LOW_CONFIDENCE,
TIMEOUT, PROVIDER_ERROR, REFUSAL. Fake nunca se presenta como LLM real.
Disabled y configuración OPENAI sin clave devuelven fallback y no realizan HTTP.

## Dominio, privacidad y auditoría

- Cuatro hint stages y mínimo explícito de la decisión: PASS.
- Focal por relación con regla, severidad, orden de catálogo: PASS.
- Los 15 patrones ×cuatro etapas tienen fallback válido y coherente con metadata: PASS.
- Contextos sin variables con nombres personales, programa completo, trace ni perfil: PASS.
- Misma semántica con UUID/fecha diferentes produce prompts/hashes iguales: PASS.
- JSON con campos extra, duplicados, trailing, etapa/idioma/focus incorrectos, exceso de longitud: rechazo PASS.
- Caso LOOPS manual: functional PASS, pedagogical FAIL,
  REPETITIVE_SEQUENCE_WITHOUT_LOOP; classifier invocado cero veces: PASS con spy.
- Fixture integrado unknown: failure sin patrones, analizable →classifier Fake →
  LOGIC_FLOW_ISSUE .82 →feedback →dos auditorías y etiqueta hija: PASS.
  El control modifica solo evidencia de feedback del test; no catálogo, ECA ni StudentModel.
- Éxito o fallo no analizable no clasifica: PASS.
- POST repetido devuelve registro/ID idéntico sin segunda llamada: PASS con spy.
- Dos requests simultáneos producen una llamada efectiva y un registro: PASS.
- Trigger de fallo al insertar tags revierte registro/tags/auditoría, sin revertir
  el intento educativo. Trigger retirado; siguiente generación funciona: PASS.
- APIs:200, GET, 400, 404, propiedad incorrecta, decisión ausente, campo cliente sin autoridad: PASS.
- V4:feedback_attempt_inputs, feedback_records, feedback_record_tags, feedback_provider_calls.
  No raw prompts/responses en tablas; solo evidencia mínima, DTO validado y hashes.

Se revisaron logs de test, verify y ambos runtimes: cero apariciones de la clave
ficticia usada en el servidor local. ProviderResult/LlmSettings no revelan secreto
en toString. Model output no se ejecuta ni se inserta como HTML.

## Full-stack real sin clave

PostgreSQL Compose +backend JAR con --LLM_PROVIDER=DISABLED --LLM_API_KEY= --LLM_MODEL=
+Vite5173; health UP y frontend HTTP200. Se creó estudiante pseudónimo, se cumplieron
prerrequisitos y se envió LOOPS_MANUAL. Resultado:

- source=FALLBACK, llmUsed=false, fallbackReason=DISABLED.
- focus=REPETITIVE_SEQUENCE_WITHOUT_LOOP, hintStage=CONCEPTUAL_HINT.
- POST repetido y GET devolvieron DTO idéntico.
- StudentModel y AdaptationDecision iguales antes/después; sin500 ni bloqueo.

## Full-stack real Fake

Se reinició solo backend con --LLM_PROVIDER=FAKE y key/model vacíos. Nuevo estudiante
con la misma historia real. source=FAKE, llmUsed=false, fallbackReason=null;
focus y hintStage correctos; feedback persistido, POST repetido y GET idénticos.
Fingerprint de adaptación igual al estudiante del runtime DISABLED.
El caso unknown se verificó como fixture controlado integrado en FeedbackIT,
sin añadir endpoint especial ni modificar datos pedagógicos reales de Compose.

En ambos runtimes conocidos:

- promptHash: `a2896fbf8175054d67a416654e5e3e46f4a4511ac9a9dcb7a7f2050add49ff72`.
- sanitizedContextHash: `ac8e55dadc58afa9ac054ead663cc3bbfd7c75fbf52e4e6ad2845c828d8edb23`.

Respuestas locales ignoradas por Git en backend/target/phase9-evidence/no-key.json
 y fake.json. Logs en TEMP/mdedu-phase9-*.log; no se versionan dumps.

## Hashes de Fase 9

SHA-256 de bytes de recursos versionados:

| Recurso bajo llm/ | SHA-256 |
| --- | --- |
| `llm-policy.v1.json` | `08c3986b31b2e1a1381baae8f4f1ac71a283135fb93f136f311261d274dbaf4a` |
| `prompts/pedagogical-feedback-v1.txt` | `a6c7bf084a58569fc74c9f2587d126c88716867af7c798d284159722bae63eda` |
| `prompts/unknown-case-v1.txt` | `2f08841297b50a00f8985c211720e4ab6bd60ab27d3277491f230ec0505ca4c4` |
| `schemas/classification-v1.json` | `9f096e6f7b9015b0d153cdf7b334edf6b191b97a98bfd169e24dc53cad24a71e` |
| `schemas/feedback-v1.json` | `03f0777d828bd53d5d429ab2f7d01d570112d7d69a05ce271d65f31c61448bb5` |
| `unknown-case-tags.v1.json` | `9f7e5209cbd1faae8f372d1c4c4bc3c8c83706603b08132374ef9e6df94a57e2` |

## Integridad preservada

308 archivos protegidos comparados con SHA-256 inicial: cero diferencias.
Incluye todo mde/, todo frontend/, V1–V3, catálogos de evaluación, reglas DSL, políticas
mastery/adaptación. src-gen: programming44, learning26, adaptation44, context14 intactos.
Sin nuevos metamodelos ni edición manual de generados.

| Modelo/gramática | SHA-256 final, igual al inicial |
| --- | --- |
| `mde/com.project.mde.adaptation.dsl/src/main/java/com/project/mde/adaptation/dsl/AdaptationRules.xtext` | `df841202185952e797651068514e958fad04090ffd6b4eaf1a0dd8cd3ad4d683` |
| `mde/com.project.mde.adaptation.model/model/adaptation.ecore` | `ed3f63b5227e07421f696a04690becb4778758ed7e3ed7c0c39d9ddb58eb8243` |
| `mde/com.project.mde.adaptation.model/model/adaptation.genmodel` | `b22b13a6ce00657c86fa87d6ab7d99199b04b1f1bd291c6e32e019519d2c68ff` |
| `mde/com.project.mde.context.model/model/context.ecore` | `4d9ccb0d4f7920c4ed1b970d7b589eb8410ae914c3a97390b5b03479efd36741` |
| `mde/com.project.mde.context.model/model/context.genmodel` | `27b1191776acf0fb8f5bf19880b71210f108647bcff2d150dabb0a44e0073079` |
| `mde/com.project.mde.learning.model/model/learning.ecore` | `3b7e10dff04fc7af3ec91649986322271023afb4207dd57de3c9f13f4fa2eb45` |
| `mde/com.project.mde.learning.model/model/learning.genmodel` | `1deea0b68799173dd419a227408fb9a33aecf8e49bfe20cb662de0a1926297be` |
| `mde/com.project.mde.programming.model/model/programming.ecore` | `6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df` |
| `mde/com.project.mde.programming.model/model/programming.genmodel` | `b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451` |

Acceleo program.js: `15f3d7558138343c89e9fd69ced3921d51e52f53ef32c08bb49105fdec09bfe8`,
igual al checkpoint. Regresiones Fases5, 6, 7, 8 PASS, incluyendo independencia de alumnos,
LOOPS manual y fingerprint/acciones inmutables después de feedback OpenAI simulado.

## Incidencias y recursos

El usuario reportó apagados durante la sesión. Tras reinicio se recuperaron archivos
y logs: test/verify/MDE/frontend habían finalizado correctamente. Windows registró
bugchecks 0x7F y0xD1; eso no establece por sí solo falta de RAM ni identifica una
causa. No se analizaron dumps ni se cambiaron drivers, Windows, WSL o límites globales.

Se continuó secuencialmente. JAR con -Xmx384m, Node con límite384 MiB y Playwright un
worker; ajustes solo del proceso. RAM libre medida:9.11 GiB al retomar, 5.78/5.84 GiB
con stack activo. No se repitieron suites ya aprobadas. Se ejecutaron las pruebas
full-stack/E2E restantes y generación Acceleo. Comentario de fallback aclarado
posteriormente sin cambio de comportamiento.

Warnings no bloqueantes: chunk Blockly >500kB, autoattach Mockito, JVM CDS y metadata
p2 Maven. Un comando de búsqueda rg no estuvo disponible tras refrescar PATH;
no afectó fuentes ni resultados; se preservó PATH en comandos posteriores.

Backend, Vite, PostgreSQL y Docker de esta tarea detenidos al cierre; volumen conservado.
Sin API real, créditos, key real, ui.ecore, Luma completa, Transitioner ni telemetría general.
Cuatro niveles/conceptos, cuatro actividades, cero refuerzos, ningún tema avanzado.

Límites documentados: contenido generativo no tiene garantía semántica absoluta;
legacy sin evidencia V4 →409; acceso pseudónimo de prototipo; lock conserva una
conexión y no sustituye una cola; crash entre API/commit puede repetir llamada;
texto libre previo omitido; políticas/templates requieren versionado coherente.

[Diseño](FASE_9_LLM.md) · [API](API.md). Fase 10 no iniciada.
