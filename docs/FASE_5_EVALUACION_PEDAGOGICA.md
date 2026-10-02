# Fase 5: evaluación pedagógica determinista

## Alcance y flujo

Se evalúa un intento de los cuatro niveles existentes: Secuencias, Variables,
Condicionales y Ciclos. Program EMF es la representación formal; no se compara
JavaScript ni la serialización Blockly. No se añade persistencia del estudiante.

`Blockly → ProgramDto V1 → mapper existente → Program EMF → GridWorldExecutionEngine
→ ExecutionResult + trace → SolutionEvaluationService → SolutionEvaluator
→ PatternDetector → EvaluationResult → React`.

GameExecutionService valida y mapea una vez, ejecuta una vez y entrega el mismo
Program y Result al evaluator. El motor no conoce detectores. Acceleo sigue en su
rama independiente `Program EMF → Acceleo → JavaScript`, sin ejecutar ese texto.

## Organización

`backend/src/main/java/com/project/evaluation/`:

- `domain`: EvaluationTypes, EvaluationContext, StructuralAnalyzer,
  ProgramFingerprintService, ConstantFolder y SolutionEvaluator puros.
- `detectors`: interfaz PatternDetector, registro cerrado y cuatro grupos por concepto.
- `catalog`: PatternCatalog carga/valida datos y configuraciones al iniciar.
- `application`: SolutionEvaluationService conecta catálogo, Program y ejecución.
- `api`: EvaluatedExecution conserva los campos de ejecución y agrega evaluation.

## Cuatro dimensiones y aprobación

FunctionalCorrectness contiene passed, goalReached, executionStatus, runtimeError
y steps, derivados de la ejecución real. StructuralCorrectness expone
requiredConcept, requiredConceptUsed, requiredConstructsSatisfied,
constraintsSatisfied y structuralConstraints (requiredConstructs, meaningfulUse).
PedagogicalEfficiency usa OK, NEEDS_REVIEW o NEEDS_RETRY, sin nota ni puntuación.
La cuarta dimensión es la lista patterns, con metadata y evidencia por detección.

`activityPassed = functional.passed && structural.constraintsSatisfied
&& ningún patrón incluido en blockingPatternIds`.

Una ejecución fallida produce NEEDS_RETRY; una aprobación con recomendaciones,
NEEDS_REVIEW; una aprobación sin patrones, OK. WARNING no significa automáticamente
que un patrón no bloquee: esa decisión está en la configuración de cada actividad.
No hay condicionales por levelId en SolutionEvaluator.

## Datos versionados

`backend/src/main/resources/evaluation/patterns.v1.json`: versión 1, exactamente
15 IDs. Cada entrada tiene version, concept, severity (INFO/WARNING/ERROR), detector,
pedagogicalMeaning, recommendedAction y defaultHintLevel (1..3).
`levels-evaluation.v1.json`: versión 1, exactamente cuatro configuraciones.
Se validan unicidad, IDs exactos, detector registrado, enums, metadata no vacía,
parámetros y referencias a niveles/patrones del mismo concepto.
Solo se reportan patrones del concepto de la actividad actual; el feedback de
Secuencias no introduce ciclos como materia futura.

| Nivel | Exigencia declarativa | Patrones bloqueantes |
| --- | --- | --- |
| SEQUENCES | Programa plano de movimientos/giros; plan real MOVE, MOVE, TURN_RIGHT, MOVE, MOVE | WRONG_ORDER, MISSING_ACTION |
| VARIABLES | Primera declaración estructural INTEGER, inicial 0, cambio real y valor final 1; nombre libre | INCORRECT_UPDATE |
| CONDITIONALS | IfElse ejecutado, condición dinámica y dos ramas no vacías diferentes | Los cuatro de condicionales |
| LOOPS | Repeat o While con al menos 2 iteraciones reales y acción/operación de variable en el cuerpo; expectativa 7 iteraciones | Los cinco de ciclos |

La puerta y la llave ahora exige explícitamente dos alternativas para demostrar
una decisión. Es un ajuste **pedagógico** de Fase 5: se documenta en la descripción
del reto y en requireBothBranches/requiredConstructs. No cambian puerta, llave,
mapa, sensores, prerequisites ni toolbox. Variables explicita 0 → 1 sin imponer
nombre. Ciclos explicita las siete casillas del mapa existente. Son las únicas
modificaciones a las descripciones del catálogo de juego.

## Semántica de los 15 detectores

| Patrón | Evidencia y alcance conservador |
| --- | --- |
| WRONG_ORDER | Programa plano: igual longitud y multiconjunto que la referencia, orden distinto. |
| UNNECESSARY_INSTRUCTION | Referencia como subsecuencia ordenada estricta del programa plano. WARNING; permite aprobar si llega. |
| MISSING_ACTION | Programa plano como subsecuencia ordenada estrictamente más corta de la referencia. |
| UNUSED_VARIABLE | Declaración sin VariableReference ni Assignment posterior dirigido al mismo EObject. |
| REDUNDANT_REASSIGNMENT | Dos asignaciones al mismo EObject en un bloque, sin lectura entre ellas; controles/declaraciones son barreras conservadoras. La inicialización no cuenta como primera asignación. |
| INCORRECT_UPDATE | Declaración seleccionada por ordinal/tipo configurados: traza inicial y estado final no cumplen 0 → 1. Ausencia de declaración la cubre el requisito estructural. |
| MISSING_CONDITION | No existe el constructo requerido por la configuración (actualmente IfElse). |
| IDENTICAL_BRANCHES | Fingerprints de thenBranch y elseBranch iguales. |
| CONSTANT_CONDITION | If/IfElse con expresión booleana resoluble estáticamente sin sensores/referencias. |
| MISSING_REQUIRED_BRANCH | Cuando se requieren dos alternativas: If sin else o rama exigida vacía. |
| REPETITIVE_SEQUENCE_WITHOUT_LOOP | Unidades adyacentes iguales fuera de Repeat/While; umbral configurable 3. Detecta Move repetido y unidades compuestas. |
| LOOP_NEVER_EXECUTES | Repeat evaluado con count 0 o While con primera condición false, sin ninguna iteración de ese statement en toda la traza. |
| INCORRECT_REPETITION_COUNT | Primer Repeat/While en orden estructural: count evaluado de Repeat o iteraciones de activación finalizada de While distintos de la expectativa 7. |
| UNNECESSARY_LOOP | Repeat cuyo primer count evaluado es 1. No generaliza a cualquier loop corto. |
| POSSIBLE_INFINITE_LOOP | While estáticamente true, o STEP_LIMIT_EXCEEDED atribuido exactamente al control de ese While. Un Repeat grande no dispara este patrón. |

Los tres patrones de secuencia son excluyentes por longitud/orden. Un error mixto
no se fuerza a una clasificación. La búsqueda de repetición manual emite una
racha máxima por inicio, elige la unidad menor y continúa después de la racha.
No inspecciona como repetición manual los cuerpos ya contenidos en ciclos.

## Uso significativo y límites

Un Repeat muerto, de 0 o 1 iteración, o vacío no acredita repetición significativa.
Una condición constante no acredita decisión dinámica. Una declaración aislada
no acredita cambio de valor. Se requiere evidencia del intento ejecutado, además
de la presencia sintáctica del constructo.

La selección del primer ciclo para expectedIterationCount es una política explícita
de este prototipo con un reto por concepto; no pretende evaluar toda estrategia de
ciclos anidados o soluciones matemáticamente equivalentes. While interrumpido no
recibe un conteo de finalización inventado. LOOP_NEVER_EXECUTES evita marcar un
statement que, tras una activación vacía, sí tuvo iteraciones en otra activación.
Los análisis de ramas/escrituras son conservadores y no prueban equivalencia
semántica general. Sin patrón conocido no se inventa un diagnóstico.

## Determinismo y trazabilidad

StatementPaths recorre containment EMF y produce rutas como
`statements[0].body[0]`. Los eventos agregan statementPath opcional (null para
inicio/fin global). Se agregan LOOP_COUNT_EVALUATED y LOOP_FINISHED para evidencia,
sin ticks, movimientos ni evaluaciones adicionales: presupuesto y semántica se
conservan. El Halt retiene el path de origen, incluso al salir de anidamientos.

Fingerprints usan tipo, operador, estructura y literales con longitudes prefijadas.
Declaraciones se normalizan v1, v2... por orden estructural y las referencias usan
identidad EObject. Cambiar nombres no cambia el fingerprint; dos declaraciones con
igual nombre siguen siendo distintas. No usa XMI completo, hashCode ni toString EMF.
ConstantFolder resuelve booleanos, comparaciones tipadas y AND/OR/NOT de operandos
constantes. SensorExpression y VariableReference devuelven desconocido; también
`true OR sensor` se conserva como desconocido. Literales inválidos no se adivinan.

La evidencia contiene statementPath, traceIndex opcional, observed y expected.
Orden: catálogo, después recorrido estructural y orden de eventos. Sin timestamps,
UUID ni direcciones de memoria. El evaluator no muta Program ni ejecuta otra vez.

## API y frontend

POST `/api/game/levels/{levelId}/execute` conserva campos existentes y agrega
`evaluation`. HTTP 200 incluye evaluación incluso ante runtime error o step limit.
HTTP 400 rechaza request inválido; HTTP 422 de EMF inválido no evalúa (evaluation null).
ProgramDto V1, mapper y laboratorio libre permanecen intactos.

React presenta tres estados: no llegó; llegó pero falta aplicar el concepto;
actividad completada. Muestra pedagogicalMeaning/recommendedAction estáticos del
catálogo y conserva el panel de variables y replay de snapshots.
`applyEvaluation` agrega completedLevelIds solo si activityPassed es true; un
intento fallido o una respuesta antigua sin evaluation no agrega ni borra niveles.
Los niveles ya completados permanecen completados. No se rediseña el mapa.

No hay StudentModel, DB/Flyway nuevos, learning.ecore, ECA, Xtext, LLM, eval,
generador JavaScript Blockly, puntuaciones ni materia avanzada. Fase 6 no iniciada.

Ver [verificación real](VERIFICACION_FASE_5.md) y [contrato API](API.md).
