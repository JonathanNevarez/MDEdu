# Fase 9 — Integración LLM controlada

Partida: `a0afbe6306eef3eac14093fd457247b30110ade6`.
El LLM explica; la decisión pedagógica sigue siendo determinista. No se modifican
metamodelos, gramática, reglas, política mastery, contenidos ni interfaz React.

## Flujo y límites de autoridad

POST attempts mantiene ejecución, evaluación, aprendizaje y AdaptationDecision.
Únicamente añade captura local de evidencia mínima para feedback, dentro de su
transacción. No llama a un proveedor ni vuelve a ejecutar/evaluar el programa.
La proyección estructural recorre una instancia Program EMF mapeada desde el mismo
DTO ya validado; descarta nombres, valores, código generado y trace.

POST /api/feedback/generate recibe solo studentId/attemptId. Verifica la propiedad,
carga evidencia del intento y consulta la AdaptationDecision persistida mediante
byAttempt; nunca invoca decide ni reevalúa ECA. El controller solo valida/delega.

FeedbackOrchestrator distingue:

- Patrón conocido: selección determinista del focal, cero llamadas classifier,
  prompt pedagógico y validación del feedback.
- Fallo sin patrones y analizable: clasificación complementaria, validación de
  vocabulario/concepto/confianza y después feedback. Un fallo de clasificación
  produce fallback genérico, sin llamar al generador.
- Éxito o fallo no analizable: no classifier. El feedback no inventa un diagnóstico.

Ninguna salida del proveedor escribe StudentModel, recentErrorPatterns, mastery,
progreso, acciones o fingerprint. Etiquetas LLM son hipótesis complementarias
persistidas exclusivamente en tablas de feedback. No se reejecutan reglas con ellas.
La reproducibilidad de Fase 8 es independiente de contenido generativo.

## Proveedores y configuración

Paquete `com.project.llm`: domain, application, provider, prompt, validation,
fallback, api e infrastructure. LlmProvider utiliza Prompt y ProviderResult
inmutables, sin entidades JPA ni peticiones servlet. Operaciones:
generatePedagogicalFeedback y classifyUncoveredCase.

| Variable | Comportamiento |
| --- | --- |
| LLM_PROVIDER | DISABLED por defecto; FAKE u OPENAI explícitos; valor desconocido deshabilita |
| LLM_API_KEY | Solo backend; ausente/vacía permite arrancar y genera fallback |
| LLM_MODEL | Sin ID hardcodeado; requerido para disponibilidad OPENAI |
| LLM_TIMEOUT_MS | 3000 por defecto; acotado entre 50 y 10000 ms por intento HTTP |
| LLM_MAX_RETRIES | 1 por defecto; acotado entre 0 y 2 reintentos |

LlmSettings no imprime secretos en toString. OPENAI incompleto emite un warning
seguro y devuelve DISABLED, sin tumbar Spring ni intentar red. Configuración se lee
de Spring Environment (variables de proceso o configuración local no versionada).
Cambios requieren reiniciar backend. No se guardó ninguna clave real.

FakeLlmProvider es determinista, sin red y con modos de fallo accesibles solamente
por constructor de tests. Fake produce feedback coherente con catálogo/etapa y
clasificaciones por concepto; no se presenta como LLM real: source=FAKE, llmUsed=false.
DisabledLlmProvider siempre conduce al fallback disponible localmente.

## OpenAI Responses API

OpenAILlmProvider utiliza HttpClient del JDK 21, sin SDK ni dependencia HTTP nueva.
Endpoint fijo de producción: POST https://api.openai.com/v1/responses. Constructor
alternativo permite exclusivamente HTTP loopback para tests. No configuración de
URL remota arbitraria ni redirecciones HTTP.

Contrato comprobado en la documentación oficial de OpenAI el 2026-10-02:
[Structured Outputs](https://developers.openai.com/api/docs/guides/structured-outputs).
Se usa text.format con type=json_schema, strict=true, schema con todos sus campos
required y additionalProperties=false. Entrada separada entre instrucciones del
servidor y JSON delimitado como datos. store=false, max_output_tokens=1000;
no tools, Threads, Conversations ni Assistants. Modelo exclusivamente configurable.

Authorization Bearer solo en header. El cuerpo nunca contiene la clave ni IDs de
estudiantes. Se exige status=completed y una salida output_text; refusal, salida
vacía, truncada o inválida conducen a fallback. x-request-id se conserva solo si
cumple un formato reducido; vive en auditoría, no como contenido pedagógico.

El timeout cubre también la recepción del body. Subscriber cancela respuestas
mayores de 64 KiB. JSON interno limitado a 16000 caracteres en adaptador y 4000 en
policy. Timeout/red transitoria, 429, 5xx tienen retry acotado y espera de 50/100 ms.
400/401/403, refusal y errores de validación no se reintentan. No se propagan cuerpos
de error, mensajes del proveedor ni stacktraces al estudiante.

Estados cerrados: SUCCESS, TIMEOUT, RATE_LIMITED, PROVIDER_ERROR, INVALID_RESPONSE,
REFUSED, DISABLED. Dos operaciones remotas en unknown tienen como cota nominal dos
veces (timeout × intentos + backoff). El límite evita esperas indefinidas.

No se llamó a una API real ni se consumieron créditos. El proyecto no añade runner
de smoke live automático; LLM_LIVE_TEST no inicia nada por sí mismo. Una prueba
pagada futura requiere habilitación expresa, key/model y ejecución deliberada.

## Privacidad y prompts

ContextSanitizer crea PedagogicalLlmContext con lista permitida de campos:
concepto, objetivo del catálogo, mastery, número del intento, activityPassed,
requiredConceptUsed, patrón focal/metadata Fase 5, resumen de ejecución, etapa,
etapas previas y tipos de acciones ya decididas; tags complementarios validados.
Se omite studentLevel para no inventar nivel académico.

ExecutionSummary contiene goalReached, steps, executionStatus, conteos por tipo,
sensores enum y profundidad. No incluye nombres de variables; por ello no requiere
renombrarlos v1/v2. No hay nombre/email/UUID del alumno, IP, tokens, DB keys, programa,
JavaScript, XMI, trazas completas, stacks ni perfiles en el contexto remitido.

Focal: patrón mencionado por evidencia satisfecha de la regla primaria cuando
exista; luego severidad descendente; empate por orden estable del catálogo.

PedagogicalPromptBuilder y UnknownCasePromptBuilder centralizan prompts. Templates:
`llm/prompts/pedagogical-feedback-v1.txt`, `llm/prompts/unknown-case-v1.txt`.
Ambos versión 1. Ordenan español breve, fundamentos, tono amable, sin juicios sobre
capacidad y sin solución exacta completa. Contexto delimitado es datos, nunca
instrucciones; no se acepta prompt desde el cliente.

PreviousHints conserva solamente los identificadores de etapas de hasta tres
feedbacks anteriores del mismo concepto y anteriores al intento actual; no reenvía
texto libre del modelo. La lista es versionada por policy. No se envía todo el
historial. maxPromptChars limita instrucciones+datos: primero se retiran etapas
previas y después resumen; diagnóstico y objetivo se conservan. Si ni así cabe,
PromptSupport rechaza el contexto excesivo. Los catálogos/estructuras actuales
acotados caben en el presupuesto; cambios de catálogo deben validar este límite.

## Etapas y salidas

HintStageResolver usa hintCount del intento: 0→SOCRATIC_QUESTION,
1→CONCEPTUAL_HINT, 2→ANALOGOUS_EXAMPLE, 3+→PARTIAL_HELP. HintLevel explícito de acciones
fija mínimo: CONCEPTUAL→segunda, GUIDED→tercera, DIRECT→cuarta. El LLM no elige etapa.
No se incrementa hintCount educativo al generar feedback ni existe regenerate.

Feedback schema: message, question nullable, focus, hintStage, language. Servidor valida
campos exactos, tipos, texto no vacío, longitudes 600/300/100, language=es, etapa exacta
y focus igual al patrón focal o concepto. Rechaza JSON duplicado/trailing y campos
extra. Feedback es TEXTO; no se ejecuta ni renderiza HTML. No hay campos de acciones.

Classification schema: recognized boolean, errorTags array, explanation, confidence.
Vocabulario V1: LOGIC_FLOW_ISSUE (fundamentos), STATE_UPDATE_ISSUE (VARIABLES),
CONDITION_LOGIC_ISSUE (CONDITIONALS), REPETITION_LOGIC_ISSUE (LOOPS), UNCLASSIFIED.
ClosedVocabularyValidator comprueba alcance, unicidad, tamaño, confidence 0..1 y
threshold configurable .70. recognized=false, UNCLASSIFIED, baja confianza o etiqueta
inválida → fallback sin etiquetas aceptadas. No son nuevos Pattern IDs.

Policy `llm/llm-policy.v1.json`: versión1; feedbackEnabled=true;
unknownCaseClassificationEnabled=true; classificationConfidenceThreshold=.70;
maxPromptChars=12000;maxOutputChars=4000;maxPreviousHints=3;
maxMessageChars=600;maxQuestionChars=300;maxFocusChars=100.

PedagogicalFallbackService reutiliza pedagogicalMeaning+recommendedAction de los
15 patrones y añade una pregunta según etapa. Sin patrón, mensaje seguro por
concepto; en éxito reconoce el logro. Fake/fallback no ofrecen secuencia completa.
El contrato/prompt no puede garantizar al 100% que un proveedor real nunca entregue
una solución ni que toda frase española sea pedagógicamente correcta; requerirá
revisión/evaluación de contenido antes de uso real amplio.

## Persistencia e idempotencia

V4__feedback_records.sql, sin editar V1–V3:

- feedback_attempt_inputs: snapshot mínimo sanitizado para nuevos intentos.
- feedback_records: relación alumno/intento/decisión, purpose, source, provider/model,
  versiones, hashes, status, fallback, llmUsed, texto final, clasificación aceptada, fecha UTC
  y DTO final validado para recuperación exacta.
- feedback_record_tags: etiquetas complementarias ordenadas, sin CSV.
- feedback_provider_calls: purpose, status, intentos, prompt/context hashes, request ID
  opcional y motivo de rechazo; no raw provider body.

Persistencia final de registro, tags y auditoría es una transacción. Si tags falla,
revierte todo el feedback, conservando el intento educativo ya confirmado.
No se guardan prompts completos ni respuesta OpenAI bruta. response_json contiene
exclusivamente el DTO aceptado/fallback. Timestamps truncados a microsegundos.

Idempotencia: attempt+adaptationDecision+purpose+templateVersion+policyVersion+
configurationHash. Config hash incluye proveedor/model, timeout, retries, disponibilidad,
policy y catálogo de tags, nunca la clave. Cambiar key manteniendo disponibilidad
no invalida una respuesta. Cambio semántico de template requiere incrementar versión.

FeedbackGenerationLock usa advisory lock PostgreSQL de sesión sobre la clave de
idempotencia. No mantiene transacción ni lock de estudiante durante HTTP. Funciona
entre instancias y se libera en finally. Conserva una conexión mientras genera;
no es un sistema de colas. Espera de lock acotada a 65 s, conflicto controlado si
sigue ocupado. UNIQUE es defensa adicional. Dos llamadas simultáneas devuelven un
registro con una llamada efectiva bajo funcionamiento normal. Un fallo DB/crash
tras consumir API y antes de commit puede exigir repetir la llamada en el futuro;
no se promete exactly-once frente a fallos externos.

SHA-256: promptHash representa propósito, versión, instrucciones, JSON realmente
preparado y schema. sanitizedContextHash excluye UUID/fechas y representa los datos
sanitizados. Son independientes del fingerprint pedagógico de Fase 8.

## API y límites históricos

POST /api/feedback/generate {studentId, attemptId} →200 con source, llmUsed,
fallbackReason, texto, etapa y clasificación complementaria opcional. GET
/api/feedback/{feedbackId} recupera el registro. Campos extra del request no tienen
autoridad; se ignoran. UUID ausentes/incorrectos→400; alumno/intento ajeno o
inexistente→404; decisión ausente→404; evidencia anterior a V4 no disponible→409.
No se reejecuta un intento histórico para inventar esa evidencia.

Se mantiene el acceso pseudónimo de prototipo de Fase 6, no autenticación multiusuario
de producción. No endpoints de prompts, creación de reglas ni elección de etapa.
Sin panel tutor, Transitioner, ui.ecore, Luma completa, telemetría general ni nuevos niveles.
Cuatro conceptos/actividades, cero refuerzos. Fase 10 no iniciada.

[Verificación](VERIFICACION_FASE_9.md) · [API](API.md).

## Proveedor adicional posterior: Gemini

Ajuste independiente posterior a Fase 10, desde e549f14, en commit separado
`feat: add gemini llm provider`. La evidencia histórica anterior se conserva.
GeminiLlmProvider implementa el mismo contrato mediante Interactions v1, stateless,
con schemas, sanitizer, vocabulario y fallback existentes. OpenAI permanece soportado.
V6 amplía solo CHECKs de provider/source; V1–V5 no cambian. El orquestador únicamente
ajusta metadata genérica, con autorización expresa; no cambia pedagogía.
[Configuración y validación](INTEGRACION_GEMINI_TEMPORAL.md). Fase 11 no iniciada.
