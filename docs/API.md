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
