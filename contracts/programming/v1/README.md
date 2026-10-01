# ProgramDto V1

Contrato de transporte browser → JVM, no modelo canónico. `Program` EMF es la
fuente formal; Blockly conserva únicamente el estado del editor. Los cuatro
JSON son fixtures compartidos por frontend y backend.

Raíz: `contractVersion: 1`, `name: string`, `statements: StatementDto[]`.
Las listas mantienen el orden y pueden estar vacías. No se transportan posiciones,
colores, conexiones Blockly ni objetos EMF.

| kind de instrucción | Campos adicionales |
| --- | --- |
| move, turnLeft, turnRight | Ninguno |
| variableDeclaration | declarationId, name, valueType, initialValue opcional |
| assignment | targetDeclarationId, value |
| repeat | count, body |
| while | condition, body |
| if | condition, thenBranch |
| ifElse | condition, thenBranch, elseBranch |

| kind de expresión | Campos adicionales |
| --- | --- |
| literal | valueType, value (string) |
| variableReference | declarationId |
| comparison | operator, left, right |
| booleanExpression | operator, left, right (ausente para NOT) |
| sensorExpression | sensor |

`valueType`: INTEGER o BOOLEAN. Comparison: EQUAL, NOT_EQUAL, LESS_THAN,
LESS_OR_EQUAL, GREATER_THAN, GREATER_OR_EQUAL. Boolean: AND, OR, NOT.
Sensores: FRONT_CLEAR, LEFT_CLEAR, RIGHT_CLEAR, AT_GOAL, HAS_KEY, ON_KEY,
DOOR_AHEAD, DOOR_OPEN. No se ejecuta su semántica en esta fase.

`declarationId` es el ID estable del bloque de declaración. Puede haber nombres
iguales, pero no IDs duplicados. Las referencias usan IDs, nunca el nombre.
El mapper registra primero todas las declaraciones, incluidas las anidadas,
y luego conecta `Assignment.target` y `VariableReference.declaration` a esas
mismas instancias EObject. Se admiten referencias adelantadas dentro del programa;
no se añade una semántica de ámbitos no definida por el metamodelo.
Los IDs de transporte no son atributos nuevos de Ecore ni del XMI.

Fixtures: `sequence.json`, `variables.json`, `conditionals.json`, `loops.json`.
Frontend compara el DTO producido con estos fixtures; backend los deserializa,
construye EMF, valida y serializa. El test XMI roundtrip prueba también identidad
de ambas referencias tras recargar.

POST `/api/programming/models`: 200 con valid/diagnostics/summary/xmi;
400 para JSON/contrato/referencias inválidos; 422 para diagnóstico estructural EMF
real (por ejemplo una declaración sin su atributo obligatorio name). No hay
persistencia PostgreSQL, ejecución, evaluación ni generación de JavaScript.
