# Reglas de adaptación — contrato propuesto

Estado: documentación de Fase 0; no existe parser, catálogo ejecutable ni motor.
La sintaxis final debe compilarse y validarse con Xtext en F7.

## Sintaxis propuesta

El ejemplo amplía el pedido con versión y activación explícitas; su gramática
queda por implementar y validar, no se presenta como un archivo ejecutable.

```text
rule ReforzarCiclos version "1.0.0" enabled true
when
    concept == "LOOPS"
    and consecutiveFailures >= 3
then
    action SHOW_HINT level CONCEPTUAL
    action SELECT_REINFORCEMENT_ACTIVITY
priority 80
```

```text
rule AvanzarCondicionales version "1.0.0" enabled true
when
    concept == "CONDITIONALS"
    and masteryScore >= 0.80
    and consecutiveSuccesses >= 2
then
    action INCREASE_DIFFICULTY
    action ADVANCE_TO_NEXT_CONCEPT
priority 60
```

Validar conceptos referenciados, propiedades disponibles, tipos de operandos,
operadores comparativos, niveles de pistas y argumentos de acciones. El motor
consume instancias EMF; no evalúa directamente cadenas del DSL.

## Vocabulario cerrado de acciones

SHOW_HINT, CHANGE_HINT_LEVEL, REPEAT_ACTIVITY,
SELECT_REINFORCEMENT_ACTIVITY, ADVANCE_TO_NEXT_CONCEPT,
INCREASE_DIFFICULTY, DECREASE_DIFFICULTY, SHOW_CODE_VIEW, HIDE_CODE_VIEW,
CHANGE_FEEDBACK_STYLE.

Se rechaza cualquier acción desconocida. Las pistas progresan por pregunta
socrática, pista conceptual, ejemplo análogo y ayuda parcial. Las reglas deciden
el nivel; el LLM solo redacta. No entregar la solución completa como primera ayuda.

## Resolución de conflictos propuesta

Orden determinista: prioridad descendente → especificidad (condiciones
satisfechas) → severidad pedagógica → identificador estable para desempatar.
El catálogo define incompatibilidades: INCREASE/DECREASE_DIFFICULTY,
SHOW/HIDE_CODE_VIEW y acciones de ruta mutuamente excluyentes. La F7 probará
composición de acciones compatibles y rechazo de contradictorias; la estrategia
final y su versión deberán quedar registradas.

Cada decisión registra rulesEvaluated, rulesMatched, ruleSelected,
discardedRules y reason. AdaptationManager combina entradas del estudiante,
contexto, actividad, intento, evaluación, patrones y parámetros; los controladores
REST y el Transitioner no ejecutan decisiones pedagógicas.

## Parámetros versionados

hintLevel, difficultyAdjustmentEnabled, routeAdaptationEnabled, feedbackDetail,
failureThreshold, successThreshold, masteryThreshold, maxHintsPerActivity y
maxAttemptsBeforeReinforcement. Añadir ajustes transparentes del dominio y
umbrales de ritmo sin distribuirlos como números mágicos por el código.

Reglas semilla exigidas en F7/F8: tres fallos consecutivos, error repetido,
dominio alto, uso excesivo de pistas, tiempo muy superior al esperado y solución
correcta sin el concepto requerido.

## Catálogo pedagógico previsto para F5

| Concepto | Patrones |
| --- | --- |
| Secuencias | WRONG_ORDER, UNNECESSARY_INSTRUCTION, MISSING_ACTION |
| Variables | UNUSED_VARIABLE, REDUNDANT_REASSIGNMENT, INCORRECT_UPDATE |
| Condicionales | MISSING_CONDITION, IDENTICAL_BRANCHES, CONSTANT_CONDITION, MISSING_REQUIRED_BRANCH |
| Ciclos | REPETITIVE_SEQUENCE_WITHOUT_LOOP, LOOP_NEVER_EXECUTES, INCORRECT_REPETITION_COUNT, UNNECESSARY_LOOP, POSSIBLE_INFINITE_LOOP |

Cada registro: id, version, concept, severity, detector, pedagogicalMeaning,
recommendedAction y defaultHintLevel. Detectores deterministas con pruebas por
patrón. Casos no cubiertos pueden recibir etiquetas complementarias validadas del
LLM desde F9; estas no cambian por sí solas reglas, dominio o ruta.
