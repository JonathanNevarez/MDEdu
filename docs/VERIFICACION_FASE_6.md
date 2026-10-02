# Verificación Fase 6 — 02/10/2026

## Checkpoint e inspección

Partida exacta: `693e40a2153d66ce5711a6ebe97a22c78498f84b`, main=origin/main,
working tree clean. Se ejecutaron status, status --short, rev-parse corto/completo,
branch -vv y log -10 antes de modificar. Se inspeccionaron reactor/plugin programming,
backend/pom, V1, ejecución/evaluación/catálogos, progreso frontend y documentación
Fases 4/5, MODELOS_MDE, ARQUITECTURA, API y ESTADO_PROYECTO.
No existían entidades educativas ni migración posterior a V1.

## Modelo real

Plugin `mde/com.project.mde.learning.model` separado; learning.ecore con 10 EClasses
y 62 features; nsURI `https://mdedu.espoch.edu.ec/model/learning/1.0`.
GenModel Java 21 y Model Plugin ID coincidente. Se ejecutó el generador EMF instalado
mediante eclipsec, application org.eclipse.emf.codegen.ecore.Generator, en workspace
externo MDEdu-fase6-generation. Salida: generación de interfaces, implementación,
Factory, Package y utilidades; ningún src-gen se escribió manualmente.
Se ajustó únicamente metadata PDE/compilación del nuevo plugin.

Inventario de los **26 Java src-gen**:

```text
com/project/mde/learning/Activity.java
com/project/mde/learning/Attempt.java
com/project/mde/learning/Concept.java
com/project/mde/learning/ConceptMastery.java
com/project/mde/learning/ErrorPattern.java
com/project/mde/learning/HintUsage.java
com/project/mde/learning/impl/ActivityImpl.java
com/project/mde/learning/impl/AttemptImpl.java
com/project/mde/learning/impl/ConceptImpl.java
com/project/mde/learning/impl/ConceptMasteryImpl.java
com/project/mde/learning/impl/ErrorPatternImpl.java
com/project/mde/learning/impl/HintUsageImpl.java
com/project/mde/learning/impl/LearningFactoryImpl.java
com/project/mde/learning/impl/LearningObjectiveImpl.java
com/project/mde/learning/impl/LearningPackageImpl.java
com/project/mde/learning/impl/ProgressImpl.java
com/project/mde/learning/impl/StudentImpl.java
com/project/mde/learning/impl/StudentModelImpl.java
com/project/mde/learning/LearningFactory.java
com/project/mde/learning/LearningObjective.java
com/project/mde/learning/LearningPackage.java
com/project/mde/learning/Progress.java
com/project/mde/learning/Student.java
com/project/mde/learning/StudentModel.java
com/project/mde/learning/util/LearningAdapterFactory.java
com/project/mde/learning/util/LearningSwitch.java
```

LearningModels (src manual) valida invariantes sin tocar src-gen. Backend consume
el artefacto Maven instalado desde el reactor con exclusiones OSGi coherentes con
programming.model. StudentModelProjectionService usa LearningFactory y valida el
StudentModel antes de crear DTOs.

## Ecore y XMI

ValidateLearning.java se ejecutó con el JAR Tycho final y Common/Ecore/XMI de la
instalación validada. Salida real:

```text
learning.ecore: severity=0; EClasses=10; namespace=https://mdedu.espoch.edu.ec/model/learning/1.0
student-a.learning: load/validate/save/unload/reload/validate PASS; attempts=1; successes=1; mastery=0.1
student-b.learning: load/validate/save/unload/reload/validate PASS; attempts=1; successes=0; mastery=0.0
```

Los dos ejemplos versionados provienen de LearningIT con PostgreSQL real desde
cero. La prueba proyecta persistencia → EMF, serializa, unload, recarga en ResourceSet
nuevo y valida referencias Student/Concept/Activity/ErrorPattern/Progress. La
utilidad adicional compara EcoreUtil.equals antes/después y rechaza referencias
externas o proxies. Student A y B tienen IDs y estados diferentes.

## Validaciones automáticas reales

| Comando/componente | Resultado |
| --- | --- |
| backend `.\mvnw.cmd --batch-mode --no-transfer-progress test` | BUILD SUCCESS; 147 tests, 0 failures, 0 errors, 0 skipped; 6.676 s |
| backend `.\mvnw.cmd --batch-mode --no-transfer-progress verify` | BUILD SUCCESS; 147 unit/MVC + 14 IT, sin omisiones; 25.373 s |
| BackendBootstrapIT históricas | 4 PASS; se actualizó la expectativa de tablas/V2 para la persistencia ahora autorizada |
| LearningIT | 10 PASS sobre PostgreSQL 17 Testcontainers limpio |
| frontend `npm.cmd run typecheck` | PASS |
| frontend `npm.cmd test` | 44 PASS, 5 archivos |
| frontend `npm.cmd run build` (también invocado por test:e2e) | PASS; conserva warning Blockly >500 kB |
| frontend `npm.cmd run test:e2e` | 5 Chromium PASS; 15.1 s |
| mde `mvn.cmd --batch-mode --no-transfer-progress clean verify` | BUILD SUCCESS; programming, Acceleo y learning; 16.549 s |
| Regresión Acceleo | 25 tests existentes PASS, generación CLI y node --check PASS |
| ValidateLearning sobre JAR final | Ecore y dos round-trips PASS |

147 = 139 pruebas existentes + 8 nuevas de dominio/política/grafo.
14 IT = 4 históricas + 10 nuevas. Sin skips ni sustitución de PostgreSQL por H2.
El reactor MDE compila learning; sus verificaciones semánticas se reportan por
separado mediante ValidateLearning y las pruebas backend, sin inflar el conteo MDE.

LearningDomainTest cubre deltas .10/.05/-.03, penalización repetida -.02 una vez,
ventana, clamp ambos extremos, contadores, fail/fail/success/fail → 1/2/0/1,
promedio 1000/3000 → 2000 y tercer intento, orden/límite configurable de patrones,
grafo alternativo bifurcado, threshold, unlock histórico, ciclos/referencias inválidas.

LearningIT cubre creación y cuatro masteries iniciales, seeds, hints vacías,
persistencia de intentos/patrones, A/B independientes, XMI real, éxito con pistas,
completion/unlock monotónicos después de bajar mastery, contadores/promedio,
separación por concepto, grafo lineal, regresión manual/Repeat, rollback completo,
dos solicitudes concurrentes sin lost updates, invariantes, HTTP 201/200/400/404/409/422,
y endpoint stateless existente sin Student.

Frontend prueba creación, reutilización, 404 con una recuperación, ausencia de
bucle, creación compartida, error de red sin reemplazar identidad, ignorar progreso
local antiguo, estados backend y envío/resultado de éxito/fallo. E2E conserva
laboratorio, replay sin otra petición y progreso tras reload.

## PostgreSQL normal y limpio

Testcontainers aplicó V1 + V2 desde DB vacía y probó seeds/repositorios/proyección.
En el volumen de desarrollo existente, PostgreSQL Compose quedó healthy y Flyway
aplicó V2 sin editar V1 ni borrar datos/volumen. Consulta SQL real:

```text
version | success
1       | t
2       | t

SEQUENCES    | 0.05
VARIABLES    | 0.05
CONDITIONALS | 0.05
LOOPS        | 0.05

activities | reinforcement
4          | 0
```

Tablas nuevas: students, concepts, concept_prerequisites, learning_activities,
error_patterns, student_concept_mastery, attempts, attempt_error_patterns,
student_activity_progress, hint_usages. Sin tablas de adaptación o LLM.

## Full-stack posterior y evidencia A/B

Después de toda la automatización se ejecutó Chromium visible contra el JAR final
con la base persistente:

```powershell
npx.cmd --no-install playwright test students.spec.ts pedagogy.spec.ts --headed --workers=1
```

Resultado: **2 PASS, 17.6 s**. Se verificó visualmente student-a-persisted.png y
student-b-independent.png. Mismo mapa de cuatro juegos: A muestra 1/4 y Variables
disponible tras reload; B 0/4 y Variables bloqueado tras su fallo y reload.
Las imágenes y JSON completos están en frontend/test-results (ignorados por Git).
GET /model retornó:

| Estudiante | UUID real de prueba | Secuencias: attempts / successes / failures | mastery | Variables |
| --- | --- | --- | --- | --- |
| A | `39c25c38-4da9-43b7-84fa-9ec7669d43ca` | 1 / 1 / 0 | 0.1 | unlocked |
| B | `fd1c1c9d-aade-46a1-8d55-fe7c191ce723` | 1 / 0 / 1 | 0 | locked |

Los contextos no comparten localStorage. Se conserva solo studentId y se elimina
la clave provisional de progreso; GET progress proporciona el estado tras reload.
No se copiaron masteries entre estudiantes. Todos los intentos de esta validación
corresponden a identidades pseudónimas de prueba.

Caso adicional HTTP real con hintCount=1 y resolutionTimeMs=15000: 201,
mastery before=0, delta=.05, after=.05, ACTIVITY_SUCCESS_WITH_HINT; Variables unlocked.
No se insertó una pista ficticia en hint_usages.

La regresión visible Ciclos manual sigue functional=true/activityPassed=false y
REPETITIVE_SEQUENCE_WITHOUT_LOOP; Repeat(7)+Move sigue activityPassed=true.
El endpoint stateless se llamó dos veces con el mismo fixture manual: bytes iguales,
SHA-256 `7e43803c6d1c5cdedb2ccacbd6df5c78ec7a6c74124ab810aab6558b663012a8`,
idéntico a la evidencia de Fase 5. No timestamps/Student IDs en esa respuesta.

## Hashes e inmutabilidad

- programming.ecore: `6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df`.
- programming.genmodel: `b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451`.
- learning.ecore: `3b7e10dff04fc7af3ec91649986322271023afb4207dd57de3c9f13f4fa2eb45`.
- learning.genmodel: `1deea0b68799173dd419a227408fb9a33aecf8e49bfe20cb662de0a1926297be`.

44 programming src-gen, plugin programming completo, Acceleo/golden, contratos
ProgramDto V1 y V1 SQL conservan sus SHA-256 de partida. Del inventario MDE previo,
solo mde/pom.xml cambia para incorporar el nuevo módulo.
Acceleo generó sequence-basic en target/phase6-regression; node --check salió 0.
program.js: `15f3d7558138343c89e9fd69ced3921d51e52f53ef32c08bb49105fdec09bfe8`.

## Incidencias y alcance

- La metadata inicial del nuevo bundle contenía qualifier duplicado; se corrigió
  a 0.1.0.qualifier y el reactor completo pasó. No se modificó fuente generada.
- Un mock frontend reutilizaba un Response cuyo body ya había sido consumido;
  se ajustó para producir una respuesta por llamada. 44 pruebas finales PASS.
- La assertion histórica de BootstrapIT que exigía ausencia de tablas educativas
  se actualizó a las tablas autorizadas de V2. Se mantienen las cuatro pruebas y V1.
- Warning histórico de Blockly >500 kB y aviso NO_COLOR/FORCE_COLOR conservados,
  sin optimización o ampliación de timeouts.

No adaptation.ecore/context.ecore, Xtext, ECA, LLM ni cambios de dificultad.
Exactamente cuatro actividades principales, cero refuerzos y ningún tema avanzado.
Solo se añade la dependencia del artefacto learning; ninguna versión tecnológica cambia. Motor/evaluator, DTO y generador permanecen intactos.
No se versionan credenciales, .env, targets, dist, logs, capturas ni reportes.

Cierre autorizado: `feat: add emf student learning model`, solo main, sin amend,
rebase ni force. La evidencia de hashes Git finales se comunica después de publicar.


Servicios detenidos tras validar: Java del backend, preview de Playwright,
PostgreSQL Compose (sin eliminar volumen) y Docker Desktop, iniciado para las
pruebas. Puertos 8080/4173/5173/5432 sin listeners al cerrar. Se preservan datos de
prueba pseudónimos del volumen de desarrollo; Testcontainers administra su DB efímera.
