# Verificación Fase 5

Fecha de validación: 01/10/2026, America/Guayaquil.
Partida comprobada: `e0d28ba6eeadffce0cea27467c991d42f3daf9a0`,
main sincronizada con origin/main, working tree limpio.

## Resultado

Evaluación determinista implementada y validada. Cuatro niveles y 15 patrones V1.
Ciclos manual alcanza la meta pero no completa; Repeat(7) con Move completa.
No cambia ProgramDto, mapper, metamodelo, generador ni laboratorio libre.
No se implementa Fase 6.

## Comandos y resultados reales

| Comprobación | Resultado final |
| --- | --- |
| backend: `.\mvnw.cmd --batch-mode --no-transfer-progress test` | BUILD SUCCESS; 139 tests, 0 failures, 0 errors, 0 skipped; 10.150 s |
| backend: `.\mvnw.cmd --batch-mode --no-transfer-progress verify` | BUILD SUCCESS; 139 unit/MVC + 4 IT; 16.182 s |
| BackendBootstrapIT | 4 tests, 0 failures, 0 errors, 0 skipped, 0 flakes; Testcontainers real |
| frontend: `npm.cmd run typecheck` | PASS |
| frontend: `npm.cmd test` | 34 tests, 4 archivos, todos PASS; 1.34 s |
| frontend: `npm.cmd run build` | PASS; warning histórico bundle >500 kB |
| frontend: `npm.cmd run test:e2e` | 4 tests Chromium PASS; 13.9 s |
| mde: `mvn.cmd --batch-mode --no-transfer-progress clean verify` | BUILD SUCCESS; 25 tests, reactor completo; 20.203 s |
| Acceleo CLI + `node --check` | BUILD SUCCESS y syntax check sin error |
| Full-stack posterior: Chromium visible, adventure + pedagogy, 1 worker | 2 escenarios E2E / 3 casos de aceptación PASS; 17.4 s |

139 = 77 tests conservados + 43 escenarios de detector + 11 de catálogo + 8 de
pipeline/API. Los 43 de detector incluyen los 30 controles de la tabla siguiente.
Las cuatro IT históricas conservan sus assertions de arranque, salud y esquema V1.

## Matriz de patrones: controles reales

Implementados en `PatternDetectorsTest.positiveAndNegativeEveryPattern`, con
fixtures EMF de `EvaluationFixtures.fixture`. Cada fila ejecuta ambos valores de
positive; las assertions exigen presencia/ausencia y evidencia no vacía.
M=Move, R=TurnRight, L=TurnLeft.

| Pattern | Positive test | Negative test | Resultado |
| --- | --- | --- | --- |
| WRONG_ORDER | M,R,M,M,M: mismo multiconjunto con otro orden | M,M,R,M,M | PASS / PASS |
| UNNECESSARY_INSTRUCTION | M,M,R,M,M,L | M,M,R,M,M | PASS / PASS |
| MISSING_ACTION | M,M,R,M | M,M,R,M,M | PASS / PASS |
| UNUSED_VARIABLE | Declaración aislada | Declarar 0 y asignar 1 | PASS / PASS |
| REDUNDANT_REASSIGNMENT | Asignar 9 y después 1 sin lectura | Inicializar 0 y asignar 1 | PASS / PASS |
| INCORRECT_UPDATE | Primera INTEGER termina en 2 | Termina en 1 tras inicializar 0 | PASS / PASS |
| MISSING_CONDITION | Cinco Move sin IfElse | Solución de llave con IfElse | PASS / PASS |
| IDENTICAL_BRANCHES | Move en ambas ramas | Move frente a TurnLeft | PASS / PASS |
| CONSTANT_CONDITION | IfElse true | Sensor FRONT_CLEAR | PASS / PASS |
| MISSING_REQUIRED_BRANCH | If sin else | IfElse con dos ramas no vacías | PASS / PASS |
| REPETITIVE_SEQUENCE_WITHOUT_LOOP | Siete Move manuales | Repeat(7) con Move | PASS / PASS |
| LOOP_NEVER_EXECUTES | Repeat(0) | Repeat(7) | PASS / PASS |
| INCORRECT_REPETITION_COUNT | Repeat(6) | Repeat(7) | PASS / PASS |
| UNNECESSARY_LOOP | Repeat(1) | Repeat(7) | PASS / PASS |
| POSSIBLE_INFINITE_LOOP | While true vacío | Repeat(Integer.MAX_VALUE) vacío: límite sin falso infinito | PASS / PASS |

Controles adicionales: error de secuencia mixto sin clasificación inventada;
exclusividad de los tres patrones; warning no bloqueante con NEEDS_REVIEW;
lectura entre escrituras; nombres libres e identidad EObject; comparación de ramas;
constant folding booleanos/comparación/AND/OR/NOT, false, sensor y literal inválido;
rama vacía y configuración sin exigir else; unidades compuestas repetidas y exclusión
de cuerpos de ciclos; While inicialmente false, While FRONT_CLEAR válido y While
con límite atribuible; loops muertos/0/1/vacíos; paths anidados y restauración del
padre; activación inicial vacía seguida de iteración real; umbral configurable;
determinismo y ausencia de mutación del Program.

## Pipeline y contrato

EvaluationPipelineTest recorre JSON ProgramDto → mapper real → EMF → motor real
→ evaluator para SEQUENCES, VARIABLES, CONDITIONALS y LOOPS. Comprueba evaluación
presente y aprobación, igualdad de respuestas repetidas y un PROGRAM_STARTED.
También cubre LOOPS manual, runtime error, STEP_LIMIT_EXCEEDED con evidencia,
400 request inválido y 422 EMF inválido sin evaluación.
La inspección de GameExecutionService confirma una sola llamada a execute; el
evaluator recibe exactamente ese Result. No hay endpoint de segunda ejecución.

EvaluationCatalogTest rechaza metadata inválida, IDs duplicados/desconocidos,
referencias/configuración y parámetros inválidos. Se cargan exactamente cuatro
niveles y quince IDs. No hay niveles o detectores extra.

## Determinismo por HTTP real

Se enviaron dos POST idénticos por fixture al backend empaquetado en :8080.
Los bytes completos de cada par coincidieron (incluye ejecución, evaluación y
pattern evidence). Resultados HTTP 200:

| Fixture | Functional | activityPassed | SHA-256 de respuesta completa |
| --- | --- | --- | --- |
| SEQUENCES | true | true | `65dc1561ebc41e93558acd9f91273e47382b83e268a05b53956146afedae56f7` |
| VARIABLES | true | true | `899c368eee046c77d1545c52e990575832e195f6def45bccc74dae67996ca049` |
| CONDITIONALS | true | true | `c10b5520fcb13db115c57df3e8919289bc21c64c7a1c82bab39bee6420647a97` |
| LOOPS | true | true | `2960b672c031fd6d2440012d3e836ec1bc9fd5bc81a0213eac5a3351096bd4c2` |
| LOOPS_MANUAL | true | false | `7e43803c6d1c5cdedb2ccacbd6df5c78ec7a6c74124ab810aab6558b663012a8` |

LOOPS_MANUAL: goalReached=true, COMPLETED, 7 operaciones, requiredConceptUsed=false,
constraintsSatisfied=false, NEEDS_RETRY; patrón REPETITIVE_SEQUENCE_WITHOUT_LOOP,
statementPath=statements[0], observed="unitLength=1, repetitions=7".
LOOPS: Repeat(7) con Move, 16 operaciones, concepto usado=true, activityPassed=true,
OK, sin patrones. While FRONT_CLEAR con Move también aprueba en test de dominio.

## Navegador real y revisión visual

Primero pasaron startup, laboratorio, aventura y pedagogy (suite completa).
Después se ejecutó contra el JAR final y PostgreSQL Compose existente:

```powershell
npx.cmd --no-install playwright test adventure.spec.ts pedagogy.spec.ts --headed --workers=1
```

1. Secuencias: cinco acciones reales, meta, «¡Nivel completado!», Variables disponible;
   replay sin segundo POST, reinicio mantiene bloques y progreso persiste tras recarga.
2. Ciclos manual: precondiciones del mapa preparadas en localStorage; siete bloques
   Avanzar creados en UI. POST real success=true/activityPassed=false, patrón esperado,
   recomendación visible, LOOPS ausente de completedLevelIds y aún disponible al volver.
3. Ciclos correcto: Repetir, Entero 7 y Avanzar conectados con interacción nativa de
   Blockly; POST real success=true/activityPassed=true, «¡Nivel completado!» y progreso
   contiene LOOPS. No se inyectó una respuesta API ni un programa en el editor.

Se revisaron visualmente completed.png, manual-pedagogical-failure.png y
loop-pedagogical-pass.png: mapa/concepto/mensajes legibles y sin solapamientos.
Capturas en frontend/test-results (artefactos locales ignorados, no versionados).
No errores pageerror. Sin force, sleeps arbitrarios ni aumento de timeouts.
Los tests unitarios verifican además que un fallo posterior no borra niveles completados.

## Regresión MDE y Acceleo

79 archivos MDE rastreados comparados contra SHA-256 anterior al trabajo: 0 cambios,
incluyendo 44 src-gen, plantillas y golden files.

- programming.ecore: `6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df`.
- programming.genmodel: `b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451`.

Desde mde/com.project.mde.programming.generator:

```powershell
mvn.cmd --batch-mode --no-transfer-progress compile exec:exec "-Dmodel=../com.project.mde.programming.model/examples/sequence-basic.programming" "-Doutput=target/phase5-regression"
node --check target/phase5-regression/program.js
```

program.js generado: SHA-256
`15f3d7558138343c89e9fd69ced3921d51e52f53ef32c08bb49105fdec09bfe8`,
igual al golden existente; sin modificación de golden.

## Incidencias resueltas y límites

- La prueba E2E inicial localizaba figuras de flyout como bloques de workspace.
  Se ajustaron roles reales (option/figure), edición accesible del campo numérico
  y coordenadas relativas del arrastre; se verifica una única pila conectada antes
  del POST. La suite final y el recorrido visible pasan sin ampliar timeouts.
- Windows impidió reemplazar el JAR mientras estaba activo en la validación.
  Se detuvo esa instancia de prueba, se repitió verify con BUILD SUCCESS y se arrancó
  el artefacto final para API/E2E. Sin cambio de dependencias/configuración.
- Se conserva warning histórico de Blockly >500 kB y aviso NO_COLOR/FORCE_COLOR de
  herramientas. No afectan el resultado; no se optimiza bundle en esta fase.
- Política deliberadamente acotada al reto: expectedIterationCount usa el primer
  ciclo estructural; análisis conservador, sin equivalencia general ni diagnóstico
  inventado cuando no hay evidencia. Detalle en documento de diseño.

## Alcance y cierre

No cambios DB/Flyway, contratos DTO, mapper, laboratorio, dependencias, Ecore,
GenModel, src-gen o generador. No StudentModel, learning.ecore, persistencia del
estudiante, LLM, ECA ni programación avanzada. Solo los cuatro conceptos básicos.
La revisión funcional no encuentra eval, new Function ni generador JS de Blockly.

Documentación: diseño y verificación Fase 5, API, ARQUITECTURA y ESTADO_PROYECTO.
Los documentos históricos de verificación permanecen intactos.
Cierre Git autorizado: `feat: add deterministic pedagogical evaluation`, solo main,
sin amend/rebase/force. Se excluyen target, dist, node_modules, reportes, capturas,
logs, .env, .class y .metadata. Identidad Git y remoto no cambian.


Servicios cerrados tras la validación: backend de prueba detenido, preview finalizado,
PostgreSQL Compose detenido sin borrar volumen y Docker Desktop detenido (había sido
iniciado para estas comprobaciones). Puertos 8080/4173/5173/5432 sin listeners.
