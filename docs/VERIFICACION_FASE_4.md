# Verificación Fase 4 — GridWorld y cuatro niveles

Fecha de ejecución: 01/10/2026 (America/Guayaquil).
Checkpoint de partida confirmado con status, status --short, rev-parse, branch -vv
 y log -10: `5b67fd6fa1ca7f791ef7580289228df3cdc39dec`, main=origin/main,
working tree clean. No reset/restore/clean/stash ni cambios sobre el checkpoint.

## Alcance y archivos

Nuevos: seis clases production en backend/execution, levels.v1.json, dos suites
backend; ocho archivos frontend/features/game; game.test.tsx y adventure.spec.ts;
diseño y verificación Fase 4. Modificados: App router, HomePage, su test de inicio,
ARQUITECTURA, API y ESTADO_PROYECTO. Inventario final comprobado con Git antes de staging.
No dependencias nuevas, versiones modificadas, tablas, migraciones ni cambios MDE.

## Pruebas y builds reales

| Validación | Resultado |
| --- | --- |
| backend mvnw test final | BUILD SUCCESS, salida 0; 77 tests, 0 failures/errors/skipped; 13.246 s |
| backend mvnw verify final | BUILD SUCCESS, salida 0; 77 unit/MVC + 4 IT, 0 failures/errors/skipped; 26.926 s |
| GridWorldExecutionEngineTest | 45 pruebas PASS |
| GameControllerTest | 7 pruebas PASS |
| Pruebas backend históricas | 25 unit/MVC + 4 BackendBootstrapIT PASS |
| npm run typecheck | PASS, salida 0 |
| npm test | 26 PASS, 3 suites, salida 0 |
| npm run build | PASS, salida 0 |
| npm run test:e2e final | 3 Chromium PASS, 12.8 s, salida 0 |
| mde clean verify | BUILD SUCCESS, 27.954 s, 25 tests Acceleo PASS |
| Regresión Acceleo CLI sequence-basic | BUILD SUCCESS y node --check salida 0 |

Sin skips. Backend verify ejecuta las cuatro BackendBootstrapIT históricas con
Testcontainers real. Comandos y requisitos reproducibles en el documento de diseño.

Motor: movimiento y giros en cuatro direcciones; límites/obstáculos; sensores de
paso con límites/obstáculos/puertas; llave y puerta con/sin llave; ocho sensores;
identidad EObject, defaults INTEGER/BOOLEAN, inicialización, assignment, referencia,
errores de tipo y referencia adelantada; seis comparaciones y tres operadores booleanos;
cortocircuito; If/IfElse true/false; Repeat 0/1/N/negativo/enorme; While false/múltiple/
infinito. Éxito solo en meta final y sin error. Configuración exactamente cuatro niveles,
prerequisites sin ciclos y soluciones funcionales a los cuatro mundos.

Frontend: progreso inicial, desbloqueo por todos los requisitos (sin depender de order),
persistencia/reload, almacenamiento corrupto, replay de snapshots exactos, toolbox
progresiva, clic bloqueado con explicación y protección de ruta directa.

## Traza, determinismo y límite real

Secuencias: 5 operaciones, 8 eventos ordenados:
PROGRAM_STARTED, MOVE, MOVE, TURN_RIGHT, MOVE, MOVE, GOAL_REACHED, PROGRAM_FINISHED.
Final: posición (3,3), SOUTH, sin llave, sin variables, atGoal=true;
success=true, status=COMPLETED. Índices consecutivos desde cero.
Snapshots anteriores permanecen inmutables ante recogida de llave/apertura de puerta.
Dos ejecuciones del mismo Program/World dan Result exactamente igual.

Además, dos POST reales idénticos devolvieron bytes idénticos, SHA-256:
`a159380d651a47f3ea7f3737c356c567cec3204b64ba641abae2b2d0944ea071`.
Request/response íntegros en API.md.

while true con cuerpo vacío: HTTP 200, success=false, STEP_LIMIT_EXCEEDED,
steps=200, 201 eventos. Terminación por presupuesto semántico, sin timeout del motor.
La suite también comprobó límite 17 y Repeat vacío con Integer.MAX_VALUE/límite 10.

## Full-stack y revisión visual

Docker Desktop existente arrancado para Testcontainers. PostgreSQL Compose existente
se inició únicamente para el boot normal Spring. Sin modificar .env, configuración
DB o volumen. Health real HTTP 200 {status:UP}. Backend real :8080; frontend
compilado servido por preview :4173 gestionado por Playwright. Origen CORS :4173
habilitado solo en el proceso Java de validación.

Chromium real (sin mock de ejecución): mapa con cuatro nodos, Secuencias disponible,
otros bloqueados; mensaje accesible de bloqueo, apertura del nivel, Blockly y tablero;
construcción mediante botones que añaden bloques reales al workspace; POST real;
movimiento y nivel completado; replay vuelve al inicio y a meta con una sola petición;
reinicio conserva los cuatro Move; regreso al mapa, Variables desbloqueado;
recarga conserva progreso. Cero pageerror. Laboratorio y navegación histórica PASS.
No sleeps arbitrarios, force clicks ni aumento de timeouts.

Capturas de mapa/tablero completado en test-results ignorado revisadas visualmente:
layout escritorio 1280 px, nodos legibles, tablero, orientación y resultado visibles.
Candados sustituidos por SVG propio para evitar ambigüedad del glifo tipográfico;
E2E repetido correctamente. Sin assets externos ni nuevo Luma.

## Inmutabilidad y seguridad

Inventario antes/después: 47 hashes iguales (Ecore, GenModel, 44 src-gen y main.mtl).
Ecore: `6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df`.
GenModel: `b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451`.
Todo mde/, DTO/mapper, contracts, Blockly/laboratorio, migraciones y verificaciones
históricas sin diff respecto del checkpoint. Regresión JS Acceleo SHA-256:
`15f3d7558138343c89e9fd69ced3921d51e52f53ef32c08bb49105fdec09bfe8`.

Búsqueda en cambios funcionales de eval(, new Function, Function(,
blockly/javascript y javascriptGenerator: cero coincidencias.
El texto malicioso se rechaza como literal inválido, nunca se ejecuta.
Exactamente cuatro niveles; solo fundamentos, sin programación avanzada.
No Fase 5, patrones pedagógicos, StudentModel, learning.ecore, DB/Flyway nuevos o LLM.

## Incidencias y warnings

- Una invocación de mvnw desde raíz no encontró el wrapper; se ejecutó desde backend.
- El primer verify falló porque Docker estaba detenido. Se inició el Desktop existente
  y verify se repitió sin skips: todas las IT pasaron.
- Una escritura grande mediante shell fue rechazada por la herramienta; se aplicaron
  los archivos frontend en parches pequeños, sin cambiar permisos ni dependencias.
- Warning histórico de chunk >500 kB: toDto/Blockly 720.18 kB minificado,
  197.97 kB gzip. Build correcto; no se cambió el límite ni se ocultó el aviso.
- JDK/Mockito informa carga dinámica de agente y limitación CDS en tests; no falla.
- Playwright/Node avisa NO_COLOR ignorado por FORCE_COLOR; no afecta pruebas.
- Git puede avisar conversión LF/CRLF según configuración existente; el diff check
  debe quedar en salida 0. No se cambió configuración global.

## Cierre

Todas las validaciones técnicas superadas. Detención de servicios y comprobación
final del diff documentadas abajo tras ejecutarlas. El commit autorizado es
`feat: add gridworld levels and execution`; solo main, sin amend/rebase/force.
La coincidencia main/origin/main/remoto y working tree limpio se comprueban después
del push; no se inserta aquí un hash autorreferencial del propio commit.


Backend Java detenido verificando PID/command line del proceso de esta tarea.
Playwright detuvo preview; no listeners en 8080, 4173, 5173 o 5432.
PostgreSQL Compose detenido sin eliminar contenedor/volumen; docker ps vacío.
Docker Desktop se detuvo también porque esta tarea lo había iniciado.
Git diff --check: salida 0; revisión de inventario: solo fuentes/tests/documentación
Fase 4, sin target/dist/node_modules/logs/.env/reportes/cachés.
Hashes protegidos nuevamente iguales antes de staging.
**FASE 4 COMPLETADA Y VALIDADA. FASE 5 NO INICIADA.**
