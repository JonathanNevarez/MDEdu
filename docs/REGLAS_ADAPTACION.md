# Reglas de adaptación — DSL V1

Archivo de producción: `backend/src/main/resources/adaptation/rules/rules-v1.adapt`.
Parser oficial: Xtext 2.44.0. Modelo: adaptation.ecore. Extensión textual `.adapt`;
XMI del mismo modelo: `.adaptation`. RuleSet y cada Rule requieren versión >0.
IDs de regla únicos; name opcional; enabled explícito; priority entero 0..100.
Solo se generan candidatos; no se aplican acciones.

```text
ruleset "MDEdu Base Rules" version 1
rule ReforzarCiclos name "Tres fallos en ciclos"
version 1 enabled true on ATTEMPT_EVALUATED
when concept == "LOOPS" and consecutiveFailures >= 3
then action SHOW_HINT level CONCEPTUAL
     action REPEAT_ACTIVITY
priority 80
```

Evento cerrado actual: ATTEMPT_EVALUATED. NOT > AND > OR; se admiten paréntesis.
Ejemplo: `not (activityPassed == true or requiredConceptUsed == false)`.
Una regla deshabilitada se valida pero no cuenta como evaluada ni coincidente.

| Atributo | Tipo | Fuente |
| --- | --- | --- |
| concept | CONCEPT_ID | ConceptMastery.concept.id |
| masteryScore | DECIMAL | ConceptMastery.masteryScore |
| attemptCount | INTEGER | Intentos del concepto |
| successCount | INTEGER | Éxitos pedagógicos del concepto |
| failureCount | INTEGER | Fallos del concepto |
| consecutiveFailures | INTEGER | Racha de fallos del concepto |
| hintCount | INTEGER | Pistas acumuladas del concepto |
| averageResolutionTime | DECIMAL | Promedio del concepto, ms |
| currentResolutionTime | INTEGER | Último intento, ms |
| activityPassed | BOOLEAN | EvaluationResult |
| functionalPassed | BOOLEAN | FunctionalCorrectness.passed |
| requiredConceptUsed | BOOLEAN | StructuralCorrectness |
| detectedPatterns | Colección de Pattern ID | Patrones de la evaluación actual |
| repeatedErrorPattern | BOOLEAN | Razón del MasteryUpdater.Change de Fase 6 |

INTEGER: `== != > >= < <=`, literal entero (ELong).
DECIMAL: mismos operadores, literal decimal BigDecimal o entero promovido.
BOOLEAN: `== !=`, literal true/false. CONCEPT_ID: `== !=`, literal entre comillas.
detectedPatterns: solo `contains`, literal Pattern ID entre comillas.
IDs permitidos de concepto: SEQUENCES, VARIABLES, CONDITIONALS, LOOPS.
Patrones: catálogo Fase 5 patterns.v1.json, validado por PatternCatalog.

```text
detectedPatterns contains "REPETITIVE_SEQUENCE_WITHOUT_LOOP"
masteryScore >= 0.80
```

## Acciones cerradas

| ActionType | Parámetros |
| --- | --- |
| SHOW_HINT | level obligatorio |
| CHANGE_HINT_LEVEL | level obligatorio |
| REPEAT_ACTIVITY | Ninguno |
| SELECT_REINFORCEMENT_ACTIVITY | Ninguno; no existen refuerzos reales aún |
| ADVANCE_TO_NEXT_CONCEPT | Ninguno |
| INCREASE_DIFFICULTY | Ninguno |
| DECREASE_DIFFICULTY | Ninguno |
| SHOW_CODE_VIEW | Ninguno |
| HIDE_CODE_VIEW | Ninguno |
| CHANGE_FEEDBACK_STYLE | style obligatorio |

HintLevel: CONCEPTUAL, GUIDED, DIRECT.
FeedbackStyle: CONCISE, EXPLANATORY.
AdaptationParameters usa atributos enum unsettable: permite distinguir ausencia
de un parámetro del primer valor enum. Parámetros cruzados/adicionales se rechazan.
No hay Map libre canónico.

## Semillas V1

| Regla | Condición | Candidatos | Prioridad |
| --- | --- | --- | --- |
| ReforzarCiclos | concept LOOPS y consecutiveFailures >=3 | SHOW_HINT CONCEPTUAL, REPEAT_ACTIVITY, SELECT_REINFORCEMENT_ACTIVITY | 80 |
| ErrorRepetido | repeatedErrorPattern == true | SHOW_HINT GUIDED, REPEAT_ACTIVITY | 85 |
| DominioAlto | masteryScore >=0.80 | ADVANCE_TO_NEXT_CONCEPT | 60 |
| PistasExcesivas | hintCount >=5 | CHANGE_HINT_LEVEL GUIDED, CHANGE_FEEDBACK_STYLE EXPLANATORY | 50 |
| TiempoAlto | currentResolutionTime >300000 ms | SHOW_HINT CONCEPTUAL | 40 |
| FuncionalSinConcepto | functionalPassed true y requiredConceptUsed false | SHOW_HINT CONCEPTUAL, REPEAT_ACTIVITY | 90 |

TiempoAlto usa un umbral absoluto explícito de cinco minutos; no divide por el
promedio. Los umbrales son semillas versionadas, no valores calibrados con alumnos.
La prioridad se informa; no ordena ni selecciona un ganador. El orden de salida
es el orden del archivo. Sin adaptación automática en Fase 7.

## Rechazos demostrados

Fixtures en `mde/com.project.mde.adaptation.dsl/src/test/resources/invalid/`:
unknown-attribute, wrong-type, wrong-operator, unknown-action, unknown-concept,
unknown-pattern, missing-action, missing-condition, duplicate-rule-id,
invalid-priority, negative-priority, invalid-version, invalid-ruleset-version,
missing-parameter, forbidden-parameter, boolean-operator, decimal-for-integer,
numeric-string. Todos tienen diagnóstico con línea/columna.

Ejemplos inválidos: `masteryScore == "LOOPS"`, `consecutiveFailures == true`,
`activityPassed > false`, `concept == "ADVANCED"`,
`action REPEAT_ACTIVITY level DIRECT`, `action SHOW_HINT` sin level.
Un error impide cargar el archivo completo; no se omiten reglas silenciosamente.

## Resolución Fase 8

Sintaxis, parser, reglas y prioridades V1 intactos. El manager añade después del ECA:
priority descendente → specificity descendente → severidad de patrones explícitos
descendente → incorporación compatible/aplicable → desempate por orden fuente/ID.
AND/OR suman átomos; NOT conserva. Severidad ERROR > WARNING > INFO > NONE.

Primera regla con contribución aceptada es primary; otras pueden contribuir.
Duplicados consideran parámetros y preservan procedencia en auditoría. Diferentes
niveles para SHOW_HINT se resuelven por ranking, no se deduplican. También se
excluyen pares aumentar/disminuir, mostrar/ocultar código y avanzar/repetir.
Acciones inaplicables no bloquean alternativas compatibles.

Para tres fallos LOOPS manuales: FuncionalSinConcepto priority90 es primary,
ErrorRepetido priority85 y ReforzarCiclos priority80 contribuyen. SHOW_HINT CONCEPTUAL
+ REPEAT_ACTIVITY son acciones finales; GUIDED pierde frente al nivel de la primaria;
SELECT_REINFORCEMENT_ACTIVITY se audita como no aplicable. No se fuerza otra regla.

Códigos de descarte: LOWER_PRIORITY_CONFLICT, LESS_SPECIFIC_CONFLICT,
LOWER_PEDAGOGICAL_SEVERITY, INCOMPATIBLE_ACTION_SOURCE_ORDER, DUPLICATE_ACTION,
ACTION_DISABLED_BY_PARAMETERS, ACTION_NOT_APPLICABLE,
NOT_APPLICABLE_NO_REINFORCEMENT_ACTIVITY y NOT_APPLICABLE_NO_NEXT_CONCEPT.
No coincidencias: NO_RULE_MATCHED. Sin acción aplicable: NO_APPLICABLE_ACTION.
Detalles y límites de parámetros: [Fase 8](FASE_8_ADAPTATION_MANAGER.md).
