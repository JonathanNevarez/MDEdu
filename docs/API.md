# API — estado y contratos iniciales

## Configurado en Fase 0

El backend prepara `GET /actuator/health` en `http://localhost:8080`.
Respuesta esperada al arrancar con PostgreSQL disponible:

```json
{"status":"UP"}
```

Actuator incluye la comprobación de la conexión a BD, sin exponer detalles.
Una vez arrancado, si un indicador pasa a DOWN la respuesta de salud debe indicar
fallo (HTTP 503 por defecto). Si PostgreSQL no está disponible al arrancar,
Flyway puede impedir el inicio; no se promete que el endpoint responda en ese caso.
Su ejecución real sigue pendiente. Solo health está expuesto; no env ni beans.

## Implementado en Fase 2: POST /api/programming/models

Construye y valida un Program EMF en memoria. No guarda en PostgreSQL ni ejecuta
el programa. Contrato completo y ejemplos compartidos:
[ProgramDto V1](../contracts/programming/v1/README.md).

Request mínimo:

```json
{"contractVersion":1,"name":"Secuencia","statements":[{"kind":"move"}]}
```

HTTP 200, Content-Type application/json: `valid: true`, `diagnostics: []`,
`summary: {rootType: "Program", statementCount: 1, namespace:
"https://mdedu.espoch.edu.ec/model/programming/1.0"}` y `xmi` como string UTF-8
serializado por EMF. El resumen cuenta también instrucciones anidadas.

HTTP 400: JSON mal formado, kind desconocido, versión incompatible, campos
obligatorios ausentes, enum inválido, IDs duplicados o referencias inexistentes.
Devuelve `valid: false`, diagnostics con code/message y summary/xmi nulos, sin
stack trace. HTTP 422: diagnóstico estructural real de Diagnostician; probado con
VariableDeclaration.name nulo (atributo obligatorio Ecore). No se inventa un
error EMF para simular 422. La validación no es un intérprete ni un analizador
semántico de tipos.

## Endpoints educativos planificados — NO implementados

| Método y ruta | Entrada prevista | Salida DTO prevista / fase |
| --- | --- | --- |
| GET /api/topics | Sin cuerpo | Conceptos y prerrequisitos; F6 |
| GET /api/activities | Filtros conceptId, tipo y dificultad | Resumen de actividades; F2/F6 |
| GET /api/activities/{id} | ID interno | Objetivo, mundo, conceptos, restricciones y versión; F2/F4 |
| POST /api/sessions | Perfil de prueba y contexto técnico mínimo | sessionId, studentId interno, createdAt; F2 |
| POST /api/attempts | sessionId, activityId, activityVersion, DTO de programa versionado | attemptId, modelId, metamodelVersion, estado; F2 |
| POST /api/attempts/{id}/execute | ID de intento persistido | success, steps, finalState, trace, errors, metrics; F4 |
| POST /api/attempts/{id}/evaluate | ID y ejecución correspondiente | Correctitud funcional/estructural, eficiencia y patrones; F5 |
| GET /api/students/{id}/model | ID interno autorizado | Dominio, contadores, ritmo, ayudas y errores recientes; F6 |
| GET /api/students/{id}/progress | ID interno autorizado | Progreso y ruta disponible; F6 |
| POST /api/adaptation/decide | attemptId; estado confiable cargado en servidor | decisionId, actions, explanation, nextActivityId, uiConfiguration, audit; F8 |
| POST /api/feedback/generate | attemptId, decisionId | Texto, hintStage, source y fallbackUsed; F9 |
| GET /api/adaptation/rules | Filtro de versión/estado | Metadatos de reglas activas; F7/F12 |
| GET /api/adaptation/parameters | Versión opcional | Parámetros versionados permitidos; F8/F12 |

Ejemplo conceptual de creación de intento (forma del DTO de programa por cerrar
con Ecore en F1/F2, sin reemplazarlo por este contrato):

```json
{
  "sessionId": "sesion-interna",
  "activityId": "secuencias-introduccion",
  "activityVersion": "1.0.0",
  "programDto": {"schemaVersion": "1.0.0", "statements": []}
}
```

Ejemplo conceptual de respuesta:

```json
{
  "attemptId": "intento-interno",
  "modelId": "modelo-interno",
  "metamodelVersion": "1.0.0",
  "status": "VALIDATED"
}
```

Un programa vacío no se considera válido automáticamente: depende de las
restricciones formales/actividad. Estos ejemplos no son solicitudes ejecutables
en F0. Las colecciones, enums, límites exactos y OpenAPI se cerrarán en sus fases.

## Reglas del contrato futuro

- DTOs validados; no exponer objetos JPA ni permitir al cliente fijar dominio,
  reglas activas, evaluación o identidad ajena.
- IDs de correlación requestId/sessionId/attemptId; autorización y perfil de
  prueba controlado antes de permitir datos reales multiusuario.
- Contrato de idempotencia para crear/ejecutar/evaluar/decidir: repetir una misma
  solicitud no duplica intentos, puntos ni actualizaciones del dominio.
- Límites de tamaño/profundidad y rate limiting en endpoints costosos; no aceptar
  código JavaScript arbitrario, rutas de archivo o URIs XMI externas.
- Errores estructurados previstos: code, message seguro y requestId; 400 por
  entrada mal formada, 404 por recurso ausente, 409 por estado incompatible,
  422 por modelo inválido y 429 por límite. Nunca devolver stack trace ni secretos.
- LLM en backend exclusivamente; respuestas inválidas o timeout producen fallback.

La API no promete autenticación, rate limiting ni telemetría implementados en F0.

## API de juego — Fase 4 implementada

- `GET /api/game/levels`: HTTP 200, `{version:1, levels:[...]}`. Exactamente cuatro
  IDs: SEQUENCES, VARIABLES, CONDITIONALS, LOOPS. Cada Level incluye concept, title,
  description, order, prerequisiteLevelIds, worldConfig y allowedBlockGroups.
- `GET /api/game/levels/{levelId}`: HTTP 200 con el Level; desconocido 404
  `{"code":"LEVEL_NOT_FOUND","message":"Nivel no encontrado."}`.
- `POST /api/game/levels/{levelId}/execute`: ProgramDto V1 directo, mapper existente,
  validación Diagnostician y ejecución EMF con límite de operaciones.

Sin persistencia ni modificación de modelo. El progreso/desbloqueo es provisional
local y no autentica peticiones. No hay nuevo contrato DTO de programación.

HTTP: 200 ejecución normal o error runtime; 400 JSON/contrato inválido; 404 nivel
inexistente; 422 INVALID_MODEL de EMF; 500 solo inesperados, sin stacktrace.
Result contiene success, status, steps, finalState, trace, errors.
Errores tienen code/message; los mensajes no exponen texto del literal ni stacktrace.
COMPLETED no implica success: también se puede terminar sin alcanzar la meta.

Petición real capturada contra `POST /api/game/levels/SEQUENCES/execute`:

```json
{
  "contractVersion": 1,
  "name": "Primeros pasos",
  "statements": [
    {
      "kind": "move"
    },
    {
      "kind": "move"
    },
    {
      "kind": "turnRight"
    },
    {
      "kind": "move"
    },
    {
      "kind": "move"
    }
  ]
}
```

Respuesta HTTP 200 real (sin campos omitidos; formateada para lectura):

```json
{
  "success": true,
  "status": "COMPLETED",
  "steps": 5,
  "finalState": {
    "playerPosition": {
      "x": 3,
      "y": 3
    },
    "playerDirection": "SOUTH",
    "hasKey": false,
    "keys": [],
    "doors": [],
    "variables": [],
    "atGoal": true
  },
  "trace": [
    {
      "index": 0,
      "type": "PROGRAM_STARTED",
      "detail": "Inicio",
      "state": {
        "playerPosition": {
          "x": 1,
          "y": 1
        },
        "playerDirection": "EAST",
        "hasKey": false,
        "keys": [],
        "doors": [],
        "variables": [],
        "atGoal": false
      }
    },
    {
      "index": 1,
      "type": "MOVE",
      "detail": "Avanzar una celda",
      "state": {
        "playerPosition": {
          "x": 2,
          "y": 1
        },
        "playerDirection": "EAST",
        "hasKey": false,
        "keys": [],
        "doors": [],
        "variables": [],
        "atGoal": false
      }
    },
    {
      "index": 2,
      "type": "MOVE",
      "detail": "Avanzar una celda",
      "state": {
        "playerPosition": {
          "x": 3,
          "y": 1
        },
        "playerDirection": "EAST",
        "hasKey": false,
        "keys": [],
        "doors": [],
        "variables": [],
        "atGoal": false
      }
    },
    {
      "index": 3,
      "type": "TURN_RIGHT",
      "detail": "Giro a la derecha",
      "state": {
        "playerPosition": {
          "x": 3,
          "y": 1
        },
        "playerDirection": "SOUTH",
        "hasKey": false,
        "keys": [],
        "doors": [],
        "variables": [],
        "atGoal": false
      }
    },
    {
      "index": 4,
      "type": "MOVE",
      "detail": "Avanzar una celda",
      "state": {
        "playerPosition": {
          "x": 3,
          "y": 2
        },
        "playerDirection": "SOUTH",
        "hasKey": false,
        "keys": [],
        "doors": [],
        "variables": [],
        "atGoal": false
      }
    },
    {
      "index": 5,
      "type": "MOVE",
      "detail": "Avanzar una celda",
      "state": {
        "playerPosition": {
          "x": 3,
          "y": 3
        },
        "playerDirection": "SOUTH",
        "hasKey": false,
        "keys": [],
        "doors": [],
        "variables": [],
        "atGoal": true
      }
    },
    {
      "index": 6,
      "type": "GOAL_REACHED",
      "detail": "Meta alcanzada",
      "state": {
        "playerPosition": {
          "x": 3,
          "y": 3
        },
        "playerDirection": "SOUTH",
        "hasKey": false,
        "keys": [],
        "doors": [],
        "variables": [],
        "atGoal": true
      }
    },
    {
      "index": 7,
      "type": "PROGRAM_FINISHED",
      "detail": "Programa terminado",
      "state": {
        "playerPosition": {
          "x": 3,
          "y": 3
        },
        "playerDirection": "SOUTH",
        "hasKey": false,
        "keys": [],
        "doors": [],
        "variables": [],
        "atGoal": true
      }
    }
  ],
  "errors": []
}
```

El mismo request produjo bytes de respuesta idénticos en dos llamadas.
`while true` con cuerpo vacío devolvió HTTP 200, success=false,
status=STEP_LIMIT_EXCEEDED, steps=200 y 201 eventos; error:
`{"code":"STEP_LIMIT_EXCEEDED","message":"Se alcanzó el límite de operaciones."}`.
La última snapshot conserva el estado al detenerse. Sin timestamps en la respuesta.
Ver [semántica completa](FASE_4_GRIDWORLD_JUEGO.md).


## Evaluación pedagógica: ampliación Fase 5

Los ejemplos de ejecución anteriores documentan el contrato histórico de Fase 4.
Ahora POST `/api/game/levels/{levelId}/execute` agrega `evaluation` a esos mismos
campos; no requiere una segunda petición ni cambia ProgramDto V1. Los eventos
incluyen `statementPath` (null si no aplica). Repeat agrega LOOP_COUNT_EVALUATED;
Repeat/While agregan LOOP_FINISHED sin consumir operaciones adicionales.

HTTP 200 conserva evaluación ante runtime error y STEP_LIMIT_EXCEEDED:
functionalCorrectness.passed será false. HTTP 400 rechaza request inválido y HTTP
422 de modelo EMF inválido devuelve evaluation null. No se evalúa un EMF inválido.

Ejemplo real: siete Move manuales en LOOPS. Se muestran campos seleccionados del
resultado; finalState y trace siguen presentes en la respuesta completa.

```json
{
  "success": true,
  "status": "COMPLETED",
  "steps": 7,
  "errors": [],
  "evaluation": {
    "evaluationVersion": 1,
    "levelId": "LOOPS",
    "activityPassed": false,
    "functionalCorrectness": {
      "passed": true,
      "goalReached": true,
      "executionStatus": "COMPLETED",
      "runtimeError": false,
      "steps": 7
    },
    "structuralCorrectness": {
      "requiredConcept": "LOOPS",
      "requiredConceptUsed": false,
      "requiredConstructsSatisfied": false,
      "constraintsSatisfied": false,
      "structuralConstraints": {
        "requiredConstructs": false,
        "meaningfulUse": false
      }
    },
    "pedagogicalEfficiency": {
      "status": "NEEDS_RETRY"
    },
    "patterns": [
      {
        "id": "REPETITIVE_SEQUENCE_WITHOUT_LOOP",
        "concept": "LOOPS",
        "severity": "ERROR",
        "pedagogicalMeaning": "Tu programa repite manualmente varias veces las mismas instrucciones.",
        "recommendedAction": "Prueba a representar esa repetición con un ciclo.",
        "defaultHintLevel": 1,
        "evidence": {
          "statementPath": "statements[0]",
          "traceIndex": null,
          "observed": "unitLength=1, repetitions=7",
          "expected": "represent repetition with loop"
        }
      }
    ]
  }
}
```

Repeat(7) con Move en su cuerpo produce activityPassed=true, concepto usado=true,
constraintsSatisfied=true, pedagogicalEfficiency.status=OK y patterns=[].
La aprobación usa resultado funcional real, restricciones significativas y ausencia
de patrones bloqueantes configurados. Severity no sustituye blockingPatternIds.
El frontend usa exclusivamente activityPassed para completar niveles; un intento
posterior fallido no elimina progreso previo.

Cada patrón incluye id, concept, severity, pedagogicalMeaning, recommendedAction,
defaultHintLevel y evidence (statementPath, traceIndex opcional, observed, expected).
Orden estable de catálogo y recorrido estructural; sin timestamps. Catálogo y
configuración versión 1. [Semántica](FASE_5_EVALUACION_PEDAGOGICA.md) y
[pruebas/determinismo](VERIFICACION_FASE_5.md).


## Aprendizaje persistente — Fase 6

Identidad pseudónima de prototipo, sin autenticación. No se envían email/contraseña.
Los endpoints devuelven DTOs construidos a partir de StudentModel EMF validado.
POST attempts usa una transacción y una sola ejecución/evaluación de Fase 5.

| Método y ruta | Contrato |
| --- | --- |
| POST /api/students | {} o displayName opcional (máximo 80); 201 con UUID, nombre y createdAt UTC; inicializa cuatro masteries en cero. |
| GET /api/students/{id}/model | 200: student, modelVersion, cuatro conceptMasteries, hasta 20 recentAttempts, lastUpdated. |
| GET /api/students/{id}/progress | 200: levels, exactamente cuatro entradas actuales con levelId/conceptId/completed/unlocked/masteryScore/attemptCount. |
| POST /api/attempts | 201: attemptId, execution (incluye evaluation), studentModel, progress y masteryUpdate. |

404 para estudiante/nivel inexistente, 400 para JSON/ProgramDto inválido o rangos,
422 para EMF inválido y 409 para actividad rastreada bloqueada. Esos rechazos no
persisten intentos ni cambios de mastery. Runtime error o fallo pedagógico de un
programa válido sí se registra y devuelve 201 con activityPassed=false.

resolutionTimeMs es entero 0..86400000; hintCount entero 0..100. Timestamps vienen
del servidor. masteryUpdate.delta es el delta de política antes de clamp; after
está siempre en 0..1. Razones explican éxito, fallo, repetición y clamp.
La UI envía hintCount=0; no se fabrican filas HintUsage a partir del conteo.
El endpoint original /api/game/levels/{levelId}/execute sigue aceptando ProgramDto
sin Student y no recibe timestamps/identidad en execution/evaluation.

### Ejemplos reales de validación

POST /api/students, request:

```json
{"displayName":"Validación Fase 6"}
```

Respuesta 201:

```json
{
  "id": "8849dedf-c003-437d-87d2-9609bb9580ac",
  "displayName": "Validación Fase 6",
  "createdAt": "2026-10-02T05:23:33.390466800Z"
}
```

POST /api/attempts, request real (éxito con una pista declarada para probar la política):

```json
{
  "studentId": "8849dedf-c003-437d-87d2-9609bb9580ac",
  "levelId": "SEQUENCES",
  "resolutionTimeMs": 15000,
  "hintCount": 1,
  "program": {
    "contractVersion": 1,
    "name": "SEQUENCES",
    "statements": [
      {
        "kind": "move"
      },
      {
        "kind": "move"
      },
      {
        "kind": "turnRight"
      },
      {
        "kind": "move"
      },
      {
        "kind": "move"
      }
    ]
  }
}
```

Respuesta 201, extracto exacto de attemptId, masteryUpdate y progress. La respuesta
completa también contiene execution con evaluation y studentModel:

```json
{
  "attemptId": "b3e0ad43-54ff-438a-a19b-d1a889abfd79",
  "masteryUpdate": {
    "state": {
      "score": 0.05,
      "attempts": 1,
      "successes": 1,
      "failures": 0,
      "consecutiveFailures": 0,
      "averageTime": 15000.0,
      "hints": 1
    },
    "before": 0.0,
    "delta": 0.05,
    "after": 0.05,
    "reasons": [
      "ACTIVITY_SUCCESS_WITH_HINT"
    ]
  },
  "progress": {
    "levels": [
      {
        "levelId": "SEQUENCES",
        "conceptId": "SEQUENCES",
        "completed": true,
        "unlocked": true,
        "masteryScore": 0.05,
        "attemptCount": 1
      },
      {
        "levelId": "VARIABLES",
        "conceptId": "VARIABLES",
        "completed": false,
        "unlocked": true,
        "masteryScore": 0.0,
        "attemptCount": 0
      },
      {
        "levelId": "CONDITIONALS",
        "conceptId": "CONDITIONALS",
        "completed": false,
        "unlocked": false,
        "masteryScore": 0.0,
        "attemptCount": 0
      },
      {
        "levelId": "LOOPS",
        "conceptId": "LOOPS",
        "completed": false,
        "unlocked": false,
        "masteryScore": 0.0,
        "attemptCount": 0
      }
    ]
  }
}
```

Ese mismo ProgressDto es el contrato de GET /api/students/{id}/progress.
No hay cálculo de unlocked definitivo en el frontend.

GET /api/students/39c25c38-4da9-43b7-84fa-9ec7669d43ca/model, respuesta 200:
extracto real con student, modelVersion, la primera conceptMastery y lastUpdated
(las otras tres masteries están presentes en la respuesta completa):

```json
{
  "student": {
    "id": "39c25c38-4da9-43b7-84fa-9ec7669d43ca",
    "displayName": null,
    "createdAt": "2026-10-02T05:23:36.724Z"
  },
  "modelVersion": 1,
  "conceptMasteries": [
    {
      "conceptId": "SEQUENCES",
      "masteryScore": 0.1,
      "attemptCount": 1,
      "successCount": 1,
      "failureCount": 0,
      "consecutiveFailures": 0,
      "averageResolutionTime": 216,
      "hintCount": 0,
      "recentErrorPatterns": [],
      "lastUpdated": "2026-10-02T05:23:37.141Z"
    }
  ],
  "lastUpdated": "2026-10-02T05:23:37.141Z"
}
```

En el contexto B independiente, GET /model devolvió Secuencias con attemptCount=1,
successCount=0, failureCount=1, masteryScore=0 y MISSING_ACTION reciente. Variables
permaneció bloqueado. El contexto A conservó Variables disponible tras recargar.
[Política, grafo y límites](FASE_6_MODELO_ESTUDIANTE.md) ·
[verificación](VERIFICACION_FASE_6.md).

## Fase 8 — decisiones de adaptación

POST /api/attempts conserva status 201 y todos sus campos anteriores; añade
`adaptation` con DecisionDto después de la actualización del estudiante. El programa
se ejecuta y evalúa una sola vez. Si falla persistencia de decisión/auditoría,
revierte la transacción completa. Endpoint stateless game/levels/{id}/execute intacto.

### POST /api/adaptation/decide

Request: `{"studentId":"UUID","attemptId":"UUID"}`. Status 200, tanto para crear
como para recuperar. Solo los IDs vinculan la petición; contexto, acciones, mastery
o reglas adicionales enviados por el cliente se ignoran y nunca son fuente de verdad.

Respuesta: decisionId/studentId/attemptId/createdAt UTC, rulesetVersion,
parametersVersion, rulesEvaluated, rulesMatched, selectedRule, contributingRules,
discardedRules, actions, explanation, ruleAudit, actionAudit, contextHash, rulesetHash,
parametersHash, decisionFingerprint, llmUsed=false y llmPurpose=NONE.
Acción: type, hintLevel/feedbackStyle opcionales, targetConceptId para avance.
Sin coincidencias: selectedRule=null, actions=[], explicación NO_RULE_MATCHED.
Sin candidatos aplicables: NO_APPLICABLE_ACTION. Son respuestas válidas 200.

Repetir intento/versiones devuelve el mismo decisionId y contenido persistido,
incluso después de nuevos intentos. 400 para IDs inválidos/ausentes; 404 para alumno
inexistente, intento inexistente o perteneciente a otro alumno. 409 para un intento
histórico sin snapshot (HISTORICAL_ADAPTATION_SNAPSHOT_UNAVAILABLE) o reutilización
de versión con contenido de reglas/parámetros distinto (VERSION_CONTENT_MISMATCH).

### Consultas

- GET /api/adaptation/decisions/{decisionId}: DecisionDto, 200 o 404.
- GET /api/attempts/{attemptId}/adaptation: decisión de las versiones activas, 200 o 404.

No se añadió GET /api/adaptation/rules. No endpoints LLM ni edición de reglas.
Identidades pseudónimas por UUID siguen el prototipo Fase 6: estos GET no incorporan
un sistema de autenticación nuevo. El contenido auditado no incluye displayName.

## Fase 9 — Feedback posterior a la decisión

POST `/api/feedback/generate` recibe `{ "studentId": "UUID", "attemptId": "UUID" }`.
200 tanto para proveedor válido como fallback. Backend deriva diagnóstico, etapa y
contexto; no acepta prompt, mastery, pattern, tag o decisión del cliente como autoridad.
Campos extra se ignoran. No se llama al proveedor dentro de POST attempts.

Respuesta: feedbackId, attemptId, adaptationDecisionId, purpose=FEEDBACK_GENERATION,
message, question nullable, focus, hintStage, language=es, source, provider, model, llmUsed,
fallbackReason nullable, classification nullable, versiones, promptHash,
sanitizedContextHash y createdAt UTC. Source OPENAI/FAKE/FALLBACK; Fake tiene
llmUsed=false. Classification contiene recognized, errorTags, explanation, confidence;
es complementaria y nunca reemplaza los patrones de evaluación.

GET `/api/feedback/{feedbackId}` recupera el DTO persistido, sin generar nuevamente.
400:request/UUID inválido.404:alumno/intento ajeno o inexistente, decisión/feedback
ausente.409:evidencia histórica anterior aV4 no disponible o generación aún ocupada.
Idempotencia por intento/decisión/propósito/versiones/configuración; no regenerate.
No endpoints de prompts. Feedback se trata como texto, sin render HTML.
El modelo de acceso sigue siendo pseudónimo de prototipo, no auth de producción.

[Diseño](FASE_9_LLM.md) · [Evidencia](VERIFICACION_FASE_9.md).
