# Integración temporal de Gemini — ajuste posterior a Fase 10

Checkpoint inicial: `e549f1482e342c73bc42892704dbd2740614b969`.
Este cambio no constituye una fase. Fase 10 permanece completada; Fase 11 no iniciada.
Gemini permite desarrollo/demo cuando exista cuota disponible. OpenAI sigue soportado.

## Contrato y configuración

`LlmProvider` conserva sus dos operaciones y cuatro implementaciones: OpenAI, Gemini,
Fake y Disabled. La factory selecciona el adaptador; el orquestador solo conoce el
contrato. El ajuste autorizado en el orquestador afecta exclusivamente metadata:
`settings.successSource()` y `source.isRemote()`. No altera decisiones ni validación.

Variables del proceso o `.env` local ignorado por Git:

| Variable | Uso |
|---|---|
| `LLM_PROVIDER` | `GEMINI`, `OPENAI`, `FAKE`, `DISABLED`; ausente/desconocido → DISABLED |
| `GEMINI_API_KEY` | Credencial solo para Gemini, sin valor por defecto |
| `GEMINI_MODEL` | Modelo elegido por el usuario, sin ID hardcodeado |
| `LLM_API_KEY`, `LLM_MODEL` | Configuración independiente de OpenAI |
| `LLM_TIMEOUT_MS` | 3000 por defecto; límite 50–10000 ms por intento, incluyendo body |
| `LLM_MAX_RETRIES` | 1 por defecto; límite 0–2 retries |

Ejemplo conceptual PowerShell (sustituir marcadores localmente; nunca publicar claves):

```powershell
$env:LLM_PROVIDER="GEMINI"
$env:GEMINI_API_KEY="<tu-clave>"
$env:GEMINI_MODEL="<modelo-disponible-en-tu-cuenta>"
# Arrancar el backend desde esta misma terminal, con la DB local preparada.
```

Para volver a OpenAI basta configurar `LLM_PROVIDER=OPENAI`, `LLM_API_KEY` y `LLM_MODEL`.
No hay cambios de código. Sin clave **o** modelo Gemini, ambas operaciones devuelven
DISABLED, cero llamadas HTTP; el backend arranca y usa el fallback existente.
No se leyó ni configuró una clave real durante esta integración.

## Protocolo verificado

Referencia consultada el 02/10/2026:
[Interactions API v1](https://ai.google.dev/api/interactions-api-v1),
[guía Interactions](https://ai.google.dev/gemini-api/docs/interactions-overview) y
[structured outputs](https://ai.google.dev/gemini-api/docs/structured-output).
Se usa la referencia estable v1; algunos ejemplos de la guía todavía muestran v1beta.

`POST https://generativelanguage.googleapis.com/v1/interactions` mediante HttpClient
JDK 21 y Jackson, sin SDK ni dependencias nuevas. La credencial se envía únicamente
en `x-goog-api-key`. El endpoint de producción es fijo, sin redirects; el constructor
alternativo admite HTTP loopback exclusivamente para pruebas.

El DTO de request incluye `model`, `store=false`, `system_instruction`, `input`,
`generation_config.max_output_tokens=1000` y
`response_format={type:"text",mime_type:"application/json",schema:...}`.
Los schemas internos se reutilizan íntegros. No se envían tools, agents, sesiones
ni previous_interaction_id. No se usa generateContent.

El parser Jackson exige `status=completed` y extrae un único texto de
`steps[type=model_output].content[type=text]`. No confunde el envelope con el JSON
pedagógico ni usa el formato OpenAI. Rechaza salida vacía, ambigua o de más de
16000 caracteres; corta cuerpos de transporte mayores de 65536 bytes.
El ID de interacción solo se conserva si cumple la whitelist y 120 caracteres.
La referencia v1 consultada no define un campo específico de refusal; respuestas
no completadas/sin texto utilizable se rechazan como INVALID_RESPONSE, sin inventar
un contrato de bloqueo.

Timeout → TIMEOUT; 429 → RATE_LIMITED; errores HTTP/red → PROVIDER_ERROR.
Retry solo para timeout/red transitoria, 429 y 5xx, con el mismo backoff acotado
50 ms × número de intento de OpenAI. 400/401/403 y estructura inválida no se reintentan.
El adaptador conserva localmente este pequeño transporte para dejar OpenAI intacto;
la política, configuración, prompts, validadores y persistencia continúan compartidos.

## Pedagogía, privacidad y persistencia

Se reutilizan sin cambios ContextSanitizer, ambos prompt builders, templates, schemas,
HintStageResolver, validadores y PedagogicalFallbackService. Threshold 0.70 y vocabulario
cerrado intactos. No se envían nombre, email, UUID de estudiante, código, Blockly, XMI
ni trazas completas. `store=false` desactiva el almacenamiento de interacciones; no
sustituye los términos de tratamiento de datos del proveedor. No se afirma retención
cero universal ni se garantiza una cuota/modelo gratuito específico.

Provider GEMINI + source GEMINI implica `llmUsed=true` solo si se acepta la salida.
Ante fallo, provider GEMINI + source FALLBACK + `llmUsed=false`; modelo desde GEMINI_MODEL.
StudentModel y AdaptationDecision no son modificados. La UI mantiene la misma configuración
para feedback semánticamente equivalente. No se añaden cambios funcionales frontend.

V6 amplía exclusivamente tres CHECKs de `feedback_records` para GEMINI, incluida la
restricción llm_used/source. Sin columnas ni tablas nuevas; V1–V5 intactas.
Los CHECKs de V4 impedían persistir Gemini. Se conserva el lock y la clave de idempotencia
por configuración; cambiar provider crea un registro distinto sin sobrescribir el anterior.

## Evidencia y reproducción

Pruebas HTTP reales locales: feedback y clasificación válidos, request/schema/header,
ausencia de clave en body, falta de clave/modelo, errores 400/401/403/429/500/503,
timeout, conexión rechazada, salida inválida/vacía y cuerpo excesivo.
La clave ficticia `gemini-test-secret-never-log` no aparece en logs del adaptador ni
el log de verificación completa. No se almacenan cuerpos HTTP ni prompts completos.

GeminiFeedbackIT prueba LOOPS conocido (clasificador 0, feedback 1), unknown con
clasificación y feedback, confidence 0.40, MADE_UP_TAG, violación de hintStage,
idempotencia, concurrencia, metadata y conservación de aprendizaje/decisión/UI.
GeminiNoKeyFeedbackIT verifica arranque y fallback sin clave.

- Backend `test`: 255 unit/MVC, cero fallos.
- Backend `verify`: 255 unit/MVC + 62 IT, cero fallos; una prueba adicional posterior
  cubre conexión rechazada hasta fallback. Verificación focalizada final: 16 unit tests
  y 16 GeminiFeedbackIT PASS. Total actual: 255 unit/MVC + 63 IT distintos.
- MDE `clean verify`: PASS; Acceleo 25, Xtext 22, ATL 2.
- Frontend: typecheck PASS y 53 tests PASS, un worker.
- Bundle build PASS. Chromium con backend GEMINI sin clave: 8 E2E existentes PASS;
  el test opt-in Gemini se omite deliberadamente en este recorrido.
- Chromium con GeminiMockApplication: 1 E2E PASS (3.4 s; suite 5 s). Student → Attempt
  → Adaptation → Gemini HTTP local → feedback GEMINI/llmUsed=true → configuración
  → Luma visible y pista del proveedor, incluso después de refrescar. Captura revisada.
- Generación CLI Acceleo y `node --check`: PASS; SHA-256
  `15f3d7558138343c89e9fd69ced3921d51e52f53ef32c08bb49105fdec09bfe8`.
- 335 archivos protegidos comparados con baseline previo: cero diferencias. Incluyen
  todo MDE versionado, V1–V5, LlmProvider, OpenAI/Fake/Disabled, templates, schemas,
  política, tags, sanitizer, builders, validadores, fallback y HintStageResolver.

Todas las tareas pesadas se ejecutan secuencialmente, heaps Java/Node 384 MiB.
No se modifican ajustes de Windows. Logs locales `TEMP/mdedu-gemini-*.log`, nunca versionados.
La API real no se utilizó: live smoke SKIP. No hace falta una clave para validar esta entrega.
No se añade una ejecución live automática; cualquier prueba real futura debe ser explícita
con GEMINI_LIVE_TEST=true, GEMINI_API_KEY y GEMINI_MODEL, usando contexto sintético.

El launcher `GeminiMockApplication` vive solo en src/test y no se empaqueta en el JAR.
Para pruebas de navegador se compila test-compile y dependency:build-classpath con scope test,
y se ejecuta la clase con target/classes, target/test-classes y ese classpath, configurando
GEMINI con credencial ficticia y modelo de prueba. El bean local apunta a loopback.
Luego, desde frontend: `GEMINI_MOCK_E2E=true` y
`npx playwright test gemini.spec.ts --workers=1` sobre el bundle construido.
La prueba es opt-in; la suite normal no requiere un servidor Gemini simulado.

Cierre operativo: se detuvo únicamente el backend de pruebas y PostgreSQL con
`docker compose stop postgres`, conservando su volumen. Docker Desktop vuelve al estado
detenido inicial. Preview y Chromium cerrados; puertos 8080/4173 sin listeners de la tarea.
Cero llamadas a Gemini real y cero claves reales utilizadas. Escaneo previo al staging:
AIza 0, GEMINI_API_KEY= 4, x-goog-api-key 3, sk- 2 y LLM_API_KEY= 6 coincidencias;
corresponden a identificadores, marcadores y fixtures; cero patrones de claves reales.
`git diff --check` terminó con código 0. Sin incidencias funcionales;
advertencias conocidas de Mockito, NO_COLOR/FORCE_COLOR y tamaño de chunk Blockly.
No se diagnostican los apagados anteriores ni se cambia configuración global.

Commit separado autorizado: `feat: add gemini llm provider`. Los hashes Git definitivos
se comunican después del push para evitar autorreferencias.

## Hashes protegidos comprobados

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
