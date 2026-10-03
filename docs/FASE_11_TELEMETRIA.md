# Fase 11 — telemetría, sesiones y reconstrucción

Checkpoint de partida: `6215bf87c821bc3b446d801e3d28d913eff359cc`.
La telemetría observa el flujo de Fases 0–10 y Gemini; no decide pedagogía.
No introduce Meta-UI, docentes, analytics, nuevas actividades ni metamodelos.

## Sesiones y correlación

`SessionService` crea sesiones pseudónimas independientes de studentId. `sessions`
contiene id, student_id, started_at, ended_at nullable, status ACTIVE/ENDED y created_at.
No conserva displayName, email, IP, user-agent ni clientSessionId.
El cierre explícito es idempotente; cerrar una pestaña no garantiza cerrar la sesión.

El frontend conserva `{id,studentId,status}` en `sessionStorage`, clave
`mdedu.session.v1`; la identidad del estudiante sigue en `localStorage` con su clave
anterior. La inicialización concurrente comparte una promesa. Una sesión desconocida,
malformada o terminada se reemplaza una vez; no hay bucle infinito. Si caduca mientras
la pestaña está abierta, el cliente comprueba la sesión antes de reintentar una petición
rechazada y hace como máximo un retry. No reintenta conflictos de negocio ni errores
ambiguos de red. El backend valida la sesión antes de ejecutar un intento o llamar LLM.

`studentFetch` centraliza `X-Session-Id` y captura `X-Request-Id` seguro para diagnóstico.
`CorrelationIdFilter` acepta solo UUID canónico en X-Request-Id o genera uno nuevo;
rechaza X-Session-Id malformado. MDC se limpia/restaura al terminar. Los logs key-value
usan requestId, sessionId, attemptId, event y status; errores HTTP usan un código fijo,
sin copiar mensajes externos, cuerpos, prompts ni claves. CORS permite los headers
nuevos y expone X-Request-Id. No se añade un sistema externo de logging.

El control de propiedad corresponde al prototipo pseudónimo existente: se valida
studentId/attemptId/sessionId, no se añade autenticación ni se presenta el UUID como
una credencial de producción. La API no entrega un intento ajeno al estudiante indicado.

## Persistencia append-only

V7 añade exclusivamente `sessions` y `attempt_events`; V1–V6 quedan intactas.
`attempt_events` contiene:

- id, attempt_id nullable para eventos de sesión, session_id nullable para clientes históricos;
- student_id, request_id, sequence_number, event_type, event_version=1, source;
- occurred_at UTC del servidor, payload JSONB, payload_hash SHA-256, event_key, created_at.

Un trigger rechaza UPDATE y DELETE de eventos. No hay endpoints ni repositorios públicos
para mutar historia. No se implementa borrado automático ni backfill; la retención se
resolverá en una etapa posterior.

La transacción toma un advisory lock por estudiante. Asigna max(sequence_number)+1 en
cada intento; los eventos solo de sesión tienen su propia secuencia. UNIQUE protege
attempt/sequence y student/event_key; un índice parcial protege session/sequence para
los eventos sin intento. Los índices de sesión, estudiante y occurred_at cubren consultas.
El cierre de sesión respeta el mismo orden de locks. Una clave de negocio repetida con
payload distinto se rechaza; repetir el mismo hecho no añade una fila.

Los números de secuencia son la autoridad del orden, no los timestamps. La vista de
sesión agrupa por hora/attempt/secuencia y está limitada a 500 eventos; no pretende ser
una secuencia global entre todos los intentos. Cada timeline de intento conserva su orden.

## Contratos de evento

`TelemetryTypes` define enums cerrados de tipos y fuentes y records de payload por
familia. `PayloadCodec` valida tipo ↔ record, limita strings a 200 caracteres sin controles,
listas a 32 elementos y JSON UTF-8 a 4096 bytes; PostgreSQL aplica un límite adicional.
Canonicaliza claves de objetos antes de calcular SHA-256. No acepta mapas arbitrarios
provenientes del cliente. JSONB no se usa para guardar blobs de modelos.

| Familia | Tipos | Evidencia mínima |
|---|---|---|
| Sesión | SESSION_STARTED, SESSION_ENDED | estado |
| Actividad | ACTIVITY_OPENED, ATTEMPT_CREATED, ACTIVITY_COMPLETED | actividad/concepto |
| Modelo | PROGRAM_MODEL_CREATED, PROGRAM_MODEL_VALIDATED | hash, namespace/version real, cantidad de statements; validación desconocida hasta validar |
| Ejecución | EXECUTION_STARTED, EXECUTION_COMPLETED | estado, pasos, objetivo, errores, hash de trace |
| Evaluación | EVALUATION_COMPLETED, ERROR_PATTERN_DETECTED | criterios, versión, patrón/severidad/concepto |
| Aprendizaje | STUDENT_MODEL_UPDATED | mastery antes/delta/después y contadores |
| Adaptación | ADAPTATION_STARTED, ADAPTATION_COMPLETED | decisionId, fingerprint, versiones/hashes, regla y acciones |
| Feedback | FEEDBACK_REQUESTED, FEEDBACK_CLASSIFICATION_COMPLETED, FEEDBACK_GENERATED, FEEDBACK_FALLBACK_USED | referencia, provider/source, resultado y hashes/versiones |
| UI | UI_CONFIGURATION_CREATED, NAVIGATION_PRESENTED | versión/fingerprint y modos; safeDefault explícito |
| Error técnico | TECHNICAL_ERROR | código/componente cerrado en emisores; errores HTTP usan logs seguros |

Fuentes: BACKEND, EXECUTION, EVALUATION, LEARNING, ADAPTATION, LLM, UI, SYSTEM.
No todos los tipos deben aparecer en cada intento: un caso conocido no se clasifica;
DISABLED no realiza llamadas de red; no solicitar feedback es válido.
El conteo providerCallCount suma intentos del adaptador: en FAKE son invocaciones
locales, no llamadas HTTP; provider y llmUsed distinguen ambos casos.

## Integración y transacciones

`TelemetryRecorder` es la abstracción central de emisión. LearningService reserva el
UUID del intento y abre un scope antes de la ejecución. Los hitos reales de mapping,
validación, ejecución y evaluación se recogen en memoria en ese scope; no se reejecuta
nada. La creación es lógica dentro de la misma transacción: si falla la validación o el
commit, ni el intento ni sus eventos quedan persistidos. El flush de eventos ocurre
después de guardar el intento, programa, mastery y adaptación, en la transacción existente.

Un error de auditoría crítica hace rollback de todo ese cambio educativo. Los logs son
best effort; no pueden abortar la transacción. Su status STAGED significa insertado dentro
de una transacción, no confirma un commit; la BD es la evidencia autoritativa.

FeedbackStore guarda feedback/tags/call audit y sus eventos en una misma transacción.
Sus eventos se materializan juntos cuando el resultado se acepta/persiste, usando la
correlación de la petición; sus timestamps no son mediciones de duración de red.
El retorno idempotente de un feedback previo no duplica FEEDBACK_GENERATED.
No se modifica LlmProvider, orquestador, prompts, providers ni validadores.

La UI derivada se registra en una transacción independiente después de calcular su
resultado, incluso safeDefault. Un error de auditoría UI se propaga sin reemplazar el
resultado por otra configuración; no revierte el intento previo. Repetir el mismo
fingerprint no genera otra fila. La UI puede consultarse antes de solicitar feedback;
la timeline refleja ese orden real y después la nueva configuración, sin inventar fases.

El frontend emite solo ACTIVITY_OPENED y NAVIGATION_PRESENTED. El backend rechaza eventos
educativos enviados por cliente. Una apertura tiene un UUID estable entre los efectos
duplicados de StrictMode; una nueva apertura real tiene otro. La navegación se registra
cuando sus controles se presentan y referencia un fingerprint ya auditado del intento.
No se capturan clicks genéricos, teclas, movimientos ni operaciones individuales del juego.

## Timeline y reconstrucción

`AttemptTimelineService` devuelve `AttemptReconstruction`: resumen persistido del intento,
eventos ordenados, aperturas previas reales de su actividad/sesión, decisión persistida,
feedback persistido y resúmenes de configuraciones UI auditadas. No llama al ejecutor,
evaluador, policy, ECA, AdaptationManager de decisión ni provider. Lee incluso versiones
históricas de decisión directamente de las tablas. startedAt procede del primer evento;
completedAt indica el último instante auditado disponible, no una duración de red.

La evidencia detallada permanece en las tablas existentes:

- detected_errors conceptual → `attempt_error_patterns` real;
- reglas y parámetros → auditoría de decisión, rulesetVersion/hash y parametersVersion/hash;
- programa → `attempt_programs` V5, se expone hash; no se duplica ProgramDto/Blockly/XMI;
- feedback → `feedback_records`, tags y provider_calls; el evento no duplica su texto;
- modelos de aprendizaje/contexto → deltas y hashes, sin snapshots completos.

`AttemptTraceConsistencyChecker` verifica secuencia, propietario, source, hash, orden de
hitos críticos y referencias. La reconstrucción comprueba fingerprint de decisión y no
expone feedback asociado a otro estudiante.

- COMPLETE: están los hitos críticos de un intento nuevo; feedback/UI son opcionales si
  todavía no se solicitaron. NO_ADAPTATION es una decisión válida, sin regla inventada.
- PARTIAL: legacy sin eventos o evidencia crítica ausente; referencias históricas se
  muestran separadas, nunca como eventos sintetizados.
- INCONSISTENT: secuencia/orden/hash/propiedad o referencias contradictorias.

No se infiere una llamada LLM por la mera existencia de un feedback. El resultado de
clasificación y las llamadas siguen auditadas en feedback_provider_calls. Un JSON de
timeline puede exportarse directamente; no hay nuevo endpoint de CSV ni dashboard.

[Contrato HTTP](API.md) · [Validación real](VERIFICACION_FASE_11.md).
Fase 12 y Fase 13 quedan fuera de este cambio.
