# Fase 8 — Adaptation Manager

## Alcance y cambio autorizado

Partida: e976f0fea41ebf348f620077a082e163817f5250. Fase 8 decide, persiste y explica;
no ejecuta adaptaciones visuales ni genera feedback. llmUsed=false, llmPurpose=NONE.
Cuatro conceptos, cuatro actividades principales, cero refuerzos reales.

Se detectó y consultó el bloqueo del caso NO_ADAPTATION. El usuario autorizó
expresamente el ajuste mínimo: AdaptationDecision.ruleId 0..1, actions 0..*,
AdaptationExplanation.ruleId 0..1. Únicamente esas tres multiplicidades cambiaron en
adaptation.ecore. El generador EMF actualizó tres de sus 44 fuentes; ninguna se
editó manualmente. GenModel, gramática, parser y rules-v1.adapt permanecen intactos.
No se añade acción ficticia ni un segundo metamodelo de decisiones.

## ContextModel formal

Plugin separado mde/com.project.mde.context.model, Java 21, 14 Java generados.
Namespace https://mdedu.espoch.edu.ec/model/context/1.0. Cuatro EClasses:

| Clase | Datos |
| --- | --- |
| ContextModel | modelVersion, studentContext, platformContext, environmentContext contenidos |
| StudentContext | studentId pseudónimo, conceptId, activityId; mastery, contadores, pistas, promedio/tiempo actual; flags pedagógicos y patrones |
| PlatformContext | platform=WEB; no se inventan deviceClass ni locale |
| EnvironmentContext | levelId, conceptId, activityId; sin GridWorld ni timestamp |

ContextProjectionService usa ContextFactory.eINSTANCE. Recibe StudentModel EMF
posterior al intento, último Attempt, EvaluationResult y MasteryUpdater.Change
reales. Comprueba coherencia de identidad, actividad y estadísticas. Reutiliza
REPEATED_ERROR_PATTERN de Fase 6; no vuelve a calcular la política.

El adapter toRules es el único puente ContextModel → RuleEvaluationContext.
RuleContextFactory conserva su firma de Fase 7 pero delega a esta proyección formal.
Validación con Diagnostician, modelVersion positivo, rangos/contadores, conceptos y
patrones del catálogo real, IDs presentes y consistencia del entorno. Patrones se
normalizan por ID; los decimales siguen siendo BigDecimal.

Ejemplos XMI: context-three-failures.context y context-high-mastery.context, en el
nuevo plugin. Son fixtures explícitos de snapshot, no alumnos reales. Pruebas de
load/validate/save/unload/reload/validate e igualdad EMF. Integración adicional con
StudentModel real de PostgreSQL demuestra contexto → ECA → decisión EMF válida.

## Orquestación y captura del intento

POST /api/attempts conserva ejecución, evaluación y actualización de aprendizaje.
Después de persistir el intento y proyectar StudentModel, invoca
AdaptationManager.captureAndDecide dentro de la misma transacción. No ejecuta ni
evalúa nuevamente el programa. La respuesta añade adaptation sin quitar campos.

El manager conserva evidencia pedagógica mínima del instante del intento:
los 14 campos utilizados por ECA, actividad y sucesores/refuerzos disponibles.
No guarda StudentModel, ContextModel XMI, nombre, correo ni programa completo.
La tabla adaptation_attempt_inputs permite decidir/reconsultar un intento aunque
el estudiante haya avanzado posteriormente o salga de los 20 intentos del snapshot.
No se reconstruye el pasado usando el mastery actual.

POST /api/adaptation/decide acepta studentId/attemptId. Verifica propiedad del
intento; carga el snapshot persistido, restaura ContextModel con ContextFactory,
valida y deriva RuleEvaluationContext. Reutiliza EcaRuleEngine de Fase 7 intacto.
Las peticiones del cliente no pueden sustituir mastery, patrones, reglas ni acciones.
Los campos extra no vinculados al request se ignoran y nunca se usan como autoridad.

Los intentos anteriores a V3 no contienen toda la evidencia de evaluación necesaria.
Si no tienen decisión/snapshot, se responde 409
HISTORICAL_ADAPTATION_SNAPSHOT_UNAVAILABLE. No se inventa requiredConceptUsed ni
se vuelve a ejecutar su programa. Todos los nuevos intentos válidos capturan evidencia.

## Ranking y compatibilidad

AdaptationConflictResolver, separado del ECA, ordena coincidencias por:

1. prioridad descendente del DSL;
2. número de predicados atómicos descendente (AND/OR suman; NOT conserva);
3. severidad máxima de patrones detectados referenciados explícitamente por la
   condición: ERROR > WARNING > INFO > NONE;
4. incorporación de acciones compatibles/aplicables;
5. empate estable por orden fuente y ruleId.

Se recorre el ranking y se comprueba aplicabilidad antes de que una acción bloquee
a otra. Así una regla alta sin recursos no impide una regla baja aplicable.
La primera regla que aporta una acción aceptada es primaria. Las demás pueden
contribuir acciones adicionales o respaldar una acción idéntica ya aceptada.
Cada acción descartada/no aplicable queda auditada, aunque su regla contribuya
mediante otra acción. Una regla sin ninguna contribución queda descartada con razón.

Incompatibilidades: INCREASE/DECREASE_DIFFICULTY, SHOW/HIDE_CODE_VIEW,
ADVANCE_TO_NEXT_CONCEPT/REPEAT_ACTIVITY y diferentes parámetros para un mismo tipo
de acción de configuración. Se conserva el candidato mejor clasificado.
La deduplicación compara tipo y parámetros completos; SHOW_HINT CONCEPTUAL y
SHOW_HINT DIRECT se resuelven como conflicto de nivel, nunca como duplicados.

Rule audit incluye id, versión, prioridad, specificity, severity, sourceOrder,
status, reasonCode y evidencia. También registra reglas no coincidentes/deshabilitadas.
Action audit conserva orden, regla origen, parámetros, estado y causa de descarte.

## Parámetros y aplicabilidad

Archivo adaptation/adaptation-parameters.v1.json, loader tipado y validado al startup:

| Parámetro | Valor V1 / uso |
| --- | --- |
| version | 1 |
| hintLevel | CONCEPTUAL; configuración base, los niveles explícitos del DSL prevalecen |
| difficultyAdjustmentEnabled | true; habilita intenciones INCREASE/DECREASE |
| routeAdaptationEnabled | true; permite propuestas de ruta |
| feedbackDetail | STANDARD; metadata versionada, no genera texto ni sustituye estilo explícito DSL |
| failureThreshold | 3; elegibilidad de refuerzo si existiera |
| successThreshold | 1; elegibilidad de avance |
| masteryThreshold | 0.80; elegibilidad de avance |
| maxHintsPerActivity | 5; límite para SHOW_HINT |
| maxAttemptsBeforeReinforcement | 3; elegibilidad de refuerzo si existiera |

Las reglas de Fase 7 no se editaron. hintCount es acumulado por concepto; en el
catálogo actual hay una única actividad por concepto. Ese alcance coincide con
maxHintsPerActivity únicamente bajo esa restricción actual y deberá revisarse si
se añaden actividades. CHANGE_HINT_LEVEL puede seguir proponiéndose al alcanzar
el límite de pistas; no implica emitir otra pista.

ActionApplicabilityService descarta refuerzo con
NOT_APPLICABLE_NO_REINFORCEMENT_ACTIVITY: no hay recursos de refuerzo.
ADVANCE requiere routeAdaptationEnabled, mastery/éxitos mínimos y sucesor real
permitido por ConceptGraphService. Se usan prerequisites y desbloqueos históricos,
no level+1. Si hay varios, el destino se elige establemente por ID ordenado; si no
hay ninguno, NOT_APPLICABLE_NO_NEXT_CONCEPT. Ningún destino se aplica a la UI.

REPEAT requiere actividad actual. Las acciones de dificultad requieren su flag.
SHOW_HINT/CHANGE_HINT_LEVEL/CHANGE_FEEDBACK_STYLE exigen parámetros tipados válidos.
Los diez ActionTypes originales se conservan. No se modifica mastery ni progreso.

## Decisión formal y explicación

La resolución produce selección y auditoría; AdaptationFactory.eINSTANCE construye
AdaptationDecision y AdaptationExplanation reales. Se valida Diagnostician y la
proyección API/persistencia lee ruleId, acciones, parámetros y explicación del EMF.
Versiones, contributing/discardedRules, hashes y auditoría son metadata DTO/JPA,
no un segundo modelo formal. targetConceptId es metadata del destino aplicable.

Sin coincidencias: ruleId=null, actions=[], reason=NO_RULE_MATCHED.
Con candidatos todos inaplicables: reason=NO_APPLICABLE_ACTION.
Ambos casos son válidos conforme a las multiplicidades autorizadas.

La explicación es texto técnico determinista con códigos estables y evidencia:
prioridad, especificidad, severidad, valores observados, acciones seleccionadas y
conflictos/descartes. No es feedback del tutor ni sustituye al feedback de Fase 5.
Principales códigos: HIGHEST_RANKED_APPLICABLE, COMPATIBLE_ADDITIONAL_RULE,
LOWER_PRIORITY_CONFLICT, LESS_SPECIFIC_CONFLICT, LOWER_PEDAGOGICAL_SEVERITY,
INCOMPATIBLE_ACTION_SOURCE_ORDER, DUPLICATE_ACTION, ACTION_DISABLED_BY_PARAMETERS,
ACTION_NOT_APPLICABLE, NOT_APPLICABLE_NO_REINFORCEMENT_ACTIVITY,
NOT_APPLICABLE_NO_NEXT_CONCEPT, NO_RULE_MATCHED y NO_APPLICABLE_ACTION.

## Hashes y reproducibilidad

CanonicalHashes serializa propiedades/mapas ordenados y normaliza números a su
representación decimal sin ceros redundantes. Las listas mantienen orden semántico
estable; patrones y recursos se normalizan antes. SHA-256 UTF-8:

- contextHash: RuleEvaluationContext + actividad y recursos de aplicabilidad.
  Excluye UUID/studentId, timestamp y PlatformContext, que no interviene en decisiones.
- rulesetHash: bytes UTF-8 de rules-v1.adapt normalizando CRLF/CR a LF. Incluye
  comentarios/espacios del archivo; no pretende equivalencia entre textos distintos.
- parametersHash: todos los campos de la configuración tipada.
- decisionFingerprint: selección, contribuciones, descartes, acciones, razones,
  evidencia, auditoría, versiones y hashes; sin decisionId, studentId, attemptId o fecha.

Misma historia semántica de estudiantes diferentes produce el mismo fingerprint.
Distinta historia puede producir distinta decisión. La fecha persistida UTC se
trunca a microsegundos, precisión de la columna PostgreSQL, para devolver el mismo
DTO completo antes/después de recargar. La fecha nunca entra en el fingerprint.

## Persistencia, atomicidad e idempotencia

V3__adaptation_decisions.sql añade cuatro tablas:
adaptation_attempt_inputs, adaptation_decisions, adaptation_decision_rules,
adaptation_decision_actions. V1/V2 intactas. No tablas LLM.

Decision contiene identidad pseudónima, versiones, selección, hashes, fingerprint,
fecha y llm_used con CHECK false. semantic_json conserva el resultado auditable
inmutable para recuperación exacta; rules/actions están además normalizados en
filas ordenadas. No contiene el perfil del alumno ni XMI. Las referencias FK,
clave de acción/orden de regla y UNIQUE(attempt_id,ruleset_version,parameters_version)
impiden decisiones duplicadas.

Decidir toma el mismo lock pesimista por estudiante utilizado por LearningService.
Dos solicitudes se serializan; la segunda devuelve la fila ya persistida.
La constraint es defensa adicional. Persistencia principal y auditoría JDBC
participan de la transacción Spring/JPA; fallo en cualquier parte revierte todo.
En POST attempts también revierte intento/mastery/progreso de ese intento.

Repetir intento/versiones devuelve mismo decisionId y DTO. Si se cambia el contenido
de reglas/parámetros sin cambiar sus versiones, el manager detecta hashes diferentes
y devuelve 409 VERSION_CONTENT_MISMATCH, sin sobrescribir una decisión anterior.

## Frontera

No interfaz docente, Transitioner, ui.ecore, LLM ni nuevas actividades. Se preservan
programming/learning, Acceleo, DSL, política mastery y frontend. GET por UUID mantiene
el modelo pseudónimo de prototipo de Fase 6; no introduce autenticación ni control
multiusuario de producción. Fase 9 y aplicación visual de Fase 10 no están iniciadas.

[Verificación](VERIFICACION_FASE_8.md) · [API](API.md).
