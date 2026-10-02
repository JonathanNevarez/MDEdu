# Fase 4 — GridWorld y juego de cuatro niveles

El núcleo jugable interpreta Program EMF directamente. La rama Acceleo sigue
produciendo JavaScript como evidencia M2T; ese texto **no se ejecuta**.
La aplicación enseña únicamente secuencias, variables simples, condicionales y ciclos.
No hay evaluación pedagógica profunda, StudentModel, adaptación ni LLM.

## Arquitectura y archivos

Flujo: Blockly → ProgramDto V1 → ProgrammingModelMapper existente → Program EMF
→ Diagnostician → GridWorldExecutionEngine → ExecutionTrace → replay visual.
No se duplicó ni modificó el mapper/DTO. No cambia ninguna API de Fase 2.

En `backend/src/main/java/com/project/execution/`:

- `domain/GameTypes.java`: records de catálogo, mundo, estados, eventos y resultados;
  enum Direction cerrado.
- `domain/GridWorld.java`: mundo mutable privado de una ejecución, con snapshots inmutables.
- `domain/GridWorldExecutionEngine.java`: intérprete Java de Statement/Expression EMF.
- `application/LevelCatalog.java`: carga y valida el JSON versionado.
- `application/GameExecutionService.java`: reutiliza mapper, valida EMF y llama al motor.
- `api/GameController.java`: endpoints de catálogo y ejecución.

En `frontend/src/features/game/`: AdventurePage (mapa/actividad), GameEditor
(Blockly existente y toolbox filtrada), WorldBoard (SVG propio), api, progress,
replay y game.css. `/laboratorio` continúa sin restricciones ni modificaciones.
El inicio ahora ofrece acceso a la aventura. No se añadieron dependencias.

## Semántica del mundo

Origen (0,0) arriba a la izquierda; x crece al este, y al sur.
NORTH=(0,-1), EAST=(1,0), SOUTH=(0,1), WEST=(-1,0).
TurnLeft: NORTH→WEST→SOUTH→EAST→NORTH; TurnRight es el inverso.
Move intenta exactamente una celda y rechaza límites, obstáculos o puerta cerrada
sin llave. No cambia posición si falla. Puerta cerrada con llave se abre antes de
mover: DOOR_OPENED y luego MOVE. La llave no se consume y las puertas permanecen
abiertas durante esa ejecución. Una ejecución nueva siempre restaura el mundo.

Al entrar en una celda con KEY: MOVE y luego ITEM_COLLECTED; se retira la llave del
mapa y hasKey=true. No se recoge dos veces. El catálogo no coloca objetos sobre
el inicio ni superpone elementos. En un mundo construido directamente con llave
en el inicio, ON_KEY detecta la llave hasta que se entre en esa celda.

| Sensor | Semántica |
| --- | --- |
| FRONT_CLEAR | Celda delantera dentro del mapa, sin obstáculo, sin puerta cerrada inaccesible |
| LEFT_CLEAR | La misma comprobación a la izquierda, sin girar |
| RIGHT_CLEAR | La misma comprobación a la derecha, sin girar |
| AT_GOAL | Posición actual igual a la meta |
| HAS_KEY | Llave en inventario |
| ON_KEY | Llave aún presente en la celda actual; tras recogida automática es false |
| DOOR_AHEAD | Hay puerta en la celda delantera, abierta o cerrada |
| DOOR_OPEN | Hay puerta delantera y está abierta; false si no hay puerta |

FRONT_CLEAR devuelve true ante una puerta cerrada si hasKey=true, porque el
siguiente Move puede abrirla. Sensores no mueven ni abren nada; emiten SENSOR_READ.

## Variables y expresiones

La identidad es el EObject VariableDeclaration, mediante IdentityHashMap, nunca el
nombre. Para serializar se asignan v1, v2… en orden estructural; los nombres iguales
no colisionan. VariableReference.declaration y Assignment.target resuelven el mismo
EObject. Referenciar antes de ejecutar la declaración produce UNDECLARED_VARIABLE,
aunque el mapper haya resuelto correctamente una referencia adelantada.

INTEGER usa enteros Java de 32 bits; BOOLEAN usa true/false estrictos.
Declaraciones sin initialValue: 0/false. Declaraciones ejecutadas de nuevo dentro
de un ciclo reinicializan la misma variable. No hay ámbitos léxicos adicionales:
la variable permanece disponible durante la ejecución después de declararla.
Un valor inicial o assignment incompatible produce TYPE_MISMATCH.

Literal se parsea como datos, sin ejecutar texto. INTEGER acepta signo opcional y
dígitos, rechaza espacios, decimales y overflow. BOOLEAN acepta solo true/false.
Los errores retornan INVALID_LITERAL con mensaje seguro, sin reflejar código.
EQUAL/NOT_EQUAL requieren tipos iguales; los cuatro relacionales requieren INTEGER.
AND/OR/NOT requieren BOOLEAN. AND/OR usan cortocircuito; NOT es unario.

If evalúa la condición una vez y ejecuta then si true. IfElse evalúa una sola vez
y elige una rama. Repeat evalúa count una vez, requiere INTEGER no negativo;
0 no ejecuta cuerpo. While evalúa antes de cada iteración, incluida la comprobación
final false. El orden de las listas EMF se conserva. No se desenrollan ciclos.

## Presupuesto, éxito y errores

Propiedad backend `mdedu.game.operation-limit`, default **200** (máximo técnico
10000). Cada Statement, cada Expression evaluada y cada iteración consume una
operación. No se cobra por evento visual. El control While reevalúa expresiones,
por lo que también un cuerpo vacío agota presupuesto. Repeat vacío enorme también
termina por límite. El contador no excede el límite: al intentar una operación
adicional se devuelve STEP_LIMIT_EXCEEDED; no se usa timeout para cortar el motor.
El mapper existente limita profundidad (100) y tamaño de entrada (10000 statements).

status COMPLETED significa terminación normal; success es true únicamente si la
posición final está en la meta. Tocar la meta y alejarse no completa el reto.
RUNTIME_ERROR y STEP_LIMIT_EXCEEDED siempre tienen success=false, incluso si el
personaje había alcanzado la meta. No se evalúa eficiencia ni uso pedagógico de
bloques: una solución manual al nivel Ciclos también puede completar el reto.

## Traza y replay

Cada evento tiene index consecutivo desde 0, type, detail y snapshot state después
del evento. Snapshots incluyen posición, dirección, hasKey, llaves restantes,
puertas, variables declaradas y atGoal. No timestamps, UUID ni datos del reloj.
Eventos: PROGRAM_STARTED, MOVE, TURN_LEFT, TURN_RIGHT, VARIABLE_DECLARED,
VARIABLE_ASSIGNED, SENSOR_READ, CONDITION_EVALUATED, LOOP_ITERATION,
ITEM_COLLECTED, DOOR_OPENED, GOAL_REACHED, PROGRAM_FINISHED, RUNTIME_ERROR,
STEP_LIMIT_EXCEEDED. En fallo, el último evento es el error (sin PROGRAM_FINISHED).

Resultado: success, status, steps, finalState, trace, errors. La UI reproduce los
snapshots recibidos cada 220 ms; la transición SVG dura 180 ms y respeta reduced-motion.
Reproducir reutiliza la misma trace sin HTTP. Reiniciar cancela la petición/animación,
restaura el mundo y limpia resultado; no borra bloques. Cambiar de ruta cancela
trabajo pendiente. Ejecutar envía siempre un DTO nuevo del workspace actual.
El botón Ejecutar se desactiva mientras hay petición o replay activo.

## Catálogo y progreso

Fuente única: `backend/src/main/resources/game/levels.v1.json`, version=1.
Se validan exactamente cuatro niveles, IDs únicos, prerequisites conocidas/sin
ciclos, dimensiones/celdas válidas y ausencia de superposición de elementos.
Los IDs concretos de las fichas del usuario prevalecen sobre los nombres conceptuales
LEVEL_SEQUENCE/LEVEL_VARIABLES del mapa propuesto.

| ID | Concepto / título | Prerequisites | Toolbox |
| --- | --- | --- | --- |
| SEQUENCES | Secuencias / Primeros pasos | [] | Movimiento |
| VARIABLES | Variables / Guarda y cambia | SEQUENCES | Movimiento, Variables, Valores |
| CONDITIONALS | Condicionales / La puerta y la llave | VARIABLES | Anteriores + Condicionales, Condiciones |
| LOOPS | Ciclos / Repite el camino | CONDITIONALS | Anteriores + Ciclos |

Secuencias: camino corto con giro; solución Move, Move, TurnRight, Move, Move.
Variables: recorrido recto; permite observar declaraciones y cambios en Valores
guardados durante replay. Condicionales: llave, puerta y meta con sensores.
Ciclos: siete avances en un pasillo que hace visible la repetición.
No hay niveles extra, subniveles, puntuaciones, monedas ni temas avanzados.

`/aventura` muestra cuatro nodos originales CSS/SVG, camino, candados, progreso y
nivel disponible destacado. `/aventura/:levelId` abre la actividad. Botones con
aria-disabled explican el bloqueo sin navegar; también se protege la URL directa.
No hay bloqueo por currentLevel+1: se comprueban todos los prerequisiteLevelIds.

Progreso provisional: localStorage `mdedu.game.progress.v1`:
`{"version":1,"completedLevelIds":["SEQUENCES"]}`. Al terminar visualmente un
resultado exitoso se marca completado. El bloqueo es una función de UX local, no
un mecanismo de autorización de servidor; el endpoint puede ejecutar cualquier
nivel conocido. Sin cuentas, DB, StudentModel ni masteryScore. Almacenamiento
corrupto/incompatible inicia vacío; si no está disponible se conserva la sesión.
No existe asset Luma reutilizable en el frontend inspeccionado; no se añadió uno.

## API, build y reproducción de pruebas

GET /api/game/levels devuelve catálogo; GET /{id} devuelve nivel;
POST /{id}/execute recibe ProgramDto V1 directamente. HTTP 200 para ejecución
(incluso errores runtime), 400 contrato/JSON inválido, 404 nivel inexistente,
422 fallo de validación EMF, 500 solo inesperados. No stacktrace al cliente.
[Requests/responses reales](API.md). No endpoints nuevos en programming/models.

```powershell
# backend/
.\mvnw.cmd --batch-mode --no-transfer-progress test
.\mvnw.cmd --batch-mode --no-transfer-progress verify
# frontend/
npm.cmd run typecheck
npm.cmd test
npm.cmd run build
# mde/
mvn.cmd --batch-mode --no-transfer-progress clean verify
```

Backend verify requiere Docker operativo por las BackendBootstrapIT históricas.
Para E2E completo, iniciar el PostgreSQL existente (sin borrar volumen), backend
compilado y permitir el origen preview únicamente en el proceso de validación:

```powershell
# raíz, con .env existente
docker compose up -d --wait postgres
# backend/, en terminal dedicada
$env:CORS_ALLOWED_ORIGINS='http://localhost:5173,http://127.0.0.1:5173,http://127.0.0.1:4173'
java -jar target/adaptativa-backend-0.1.0-SNAPSHOT.jar
# frontend/, en otra terminal
npm.cmd run test:e2e
```

Playwright inicia y detiene preview :4173. El E2E aventura usa backend REAL :8080,
sin mock de respuestas de ejecución. Para desarrollo usar npm.cmd run dev (:5173).
Tras validar, detener Java y `docker compose stop postgres`; no borrar volumen.
La regresión Acceleo usa el comando headless de Fase 3 y node --check.

## Límites

Sin PostgreSQL para progreso, cambios DB/Flyway, metamodelos nuevos, ATL, Xtext,
LLM, evaluación pedagógica, StudentModel ni Fase 5. Ecore, GenModel, los 44 src-gen
y plantillas/golden Acceleo siguen intactos. El motor usa solo fundamentos.
