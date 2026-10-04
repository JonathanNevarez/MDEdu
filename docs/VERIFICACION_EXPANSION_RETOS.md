# Verificación de la expansión de retos

Checkpoint inicial limpio y publicado: `3424f797aa8cb6163536b109ab739ddf55a4cea2` — `feat: add persistent student authentication`. Fecha: 03/10/2026. Trabajo post-prototipo; fases 0–13 cerradas.

## Alcance implementado

18 retos principales: SEQ-01…04, VAR-01…04, COND-01…05 y LOOP-01…05. Cuatro conceptos; quince patrones. [IDs, títulos, objetivos, mundos, bloques y dificultad](CATALOGO_RETOS.md). Sin nuevo rediseño visual, dependencias, primitivas ni programación avanzada.

## Evidencia ejecutada

| Comprobación | Resultado |
|---|---|
| Backend test/verify | 291 unitarias/MVC y 98 IT; 0 fallos, errores u omitidas |
| Cada uno de los 18 retos | EMF válido, solución funcional/pedagógica correcta y error representativo con patrón comprobado |
| Pruebas específicas del catálogo | 24: 18 casos por reto + manual LOOP-01 + bloques falsificados + lectura antes de sobrescribir + eficiencia + While real sin movimiento + lectura omitida por cortocircuito |
| Progresión | SEQ-02 bloqueado antes de SEQ-01; abierto después; SEQ-03 permanece bloqueado. ConceptGraph conserva su política independiente |
| Seguridad | Intentos bloqueados 409; ejecución directa estudiantil bloqueada 403; aislamiento de cuentas y sesión/CSRF pasan |
| Autenticación multidispositivo | Modelo y progreso idénticos al iniciar sesión en navegador limpio; UUID local falsificado no cambia identidad |
| V8 → V9 con datos históricos | Cuenta/hash, Student, mastery, intentos y progreso previo idénticos; 18 nuevos progresos incompletos; cuatro actividades archivadas |
| Frontend | 80 pruebas; typecheck y build PASS |
| Chromium DISABLED | 17 PASS; 2 casos Gemini omitidos en este modo para ejecutarlos por separado |
| Xtext / Acceleo / ATL | clean install (incluye verify): 22 + 25 + 2 = 49 PASS |
| Gemini mock | 2 E2E PASS, HTTP local; feedback y cuota/fallback; sin API real |
| Docker smoke | Build completo, V1–V9 en DB nueva, API con un reto por concepto, fallback, UI/timeline y 5 E2E PASS; readiness/liveness con caída y recuperación de DB |
| Secretos / diff | check-secrets con bundle/logs PASS; git diff --check PASS |

La última modificación del feedback muestra las restricciones pendientes en lenguaje del estudiante, sin exponer nombres internos. Se comprueba con la prueba unitaria adicional y el bundle final.

## Caso central de la tesis

`LOOP-01`: siete Move manuales producen functionalCorrectness.passed=true, requiredConceptUsed=false, constraintsSatisfied=false y activityPassed=false. Patrón REPETITIVE_SEQUENCE_WITHOUT_LOOP. Repeat(7, Move) supera el reto. La API conserva sus nombres existentes: aprobación pedagógica se expresa mediante la estructura y activityPassed, no se inventa un campo de progreso local.

## Conservación y hashes

Inventario previo: **327 archivos**, todos con SHA-256 idéntico después de MDE clean install. Incluye todos los archivos versionados de MDE (no solo Ecore), reglas, parámetros, prompts, política del estudiante y migraciones V1–V8. Huella del inventario ordenado `ruta + espacio + SHA-256`, separado por LF sin LF final: `ae79deefc88fc0444fc709dbcfdc4b22bc8754cf13fd6dbf83e17c5a6e3da950`.

| Archivo protegido | SHA-256 antes = después |
|---|---|
| `backend/src/main/resources/adaptation/rules/rules-v1.adapt` | `b4830f6c6e2f9cac681649c64eddccb42bc5b8903413f8d934271237c9c49f70` |
| `backend/src/main/resources/learning/student-model-policy.v1.json` | `111ad1dc8376e8568f3671f34cb74da6c81e133518ec0d96d7849e459803c75a` |
| `backend/src/main/resources/llm/prompts/pedagogical-feedback-v1.txt` | `a6c7bf084a58569fc74c9f2587d126c88716867af7c798d284159722bae63eda` |
| `backend/src/main/resources/llm/prompts/unknown-case-v1.txt` | `2f08841297b50a00f8985c211720e4ab6bd60ab27d3277491f230ec0505ca4c4` |
| `mde/com.project.mde.adaptation.model/model/adaptation.ecore` | `ed3f63b5227e07421f696a04690becb4778758ed7e3ed7c0c39d9ddb58eb8243` |
| `mde/com.project.mde.context.model/model/context.ecore` | `4d9ccb0d4f7920c4ed1b970d7b589eb8410ae914c3a97390b5b03479efd36741` |
| `mde/com.project.mde.learning.model/model/learning.ecore` | `3b7e10dff04fc7af3ec91649986322271023afb4207dd57de3c9f13f4fa2eb45` |
| `mde/com.project.mde.programming.model/model/programming.ecore` | `6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df` |
| `mde/com.project.mde.ui.model/model/ui.ecore` | `07f35993cc78d88177ba1014ac5d00a19a2369a10094f30a145342fb6afc6be5` |
| `backend/src/main/resources/adaptation/adaptation-parameters.v1.json` | `173330ef2ceb7960efb9f5c47bd6d1b5d6230f5eff1ab11b8e538585f0f88d62` |

MasteryUpdater, ConceptGraphService y StudentModelPolicy permanecen sin diferencias respecto del checkpoint inicial. La migración nueva es exclusivamente V9; V1–V8 no se reescriben.

## Incidencias resueltas y límites

Se actualizaron las expectativas antiguas de cuatro actividades y ocho migraciones. Las pruebas que consumían IDs históricos ahora usan las soluciones reales del catálogo activo. Se preservan las pruebas independientes del intérprete histórico.

Un shell de verificación quedó esperando después de iniciar el backend; se detuvo únicamente ese shell y Chromium se ejecutó separadamente contra el backend ya operativo. No se perdió ningún dato.

Las explicaciones del catálogo de patrones INCORRECT_UPDATE e INCORRECT_REPETITION_COUNT ya no fuerzan 0→1 o siete pasos en todos los retos. IDs, severidades y detectores permanecen; también se sustituye “actividad” por “reto” en el mensaje estudiantil pertinente. Rules/parameters/prompts no se tocaron.

V9 no convierte éxitos históricos en retos completados. Los conserva como historia y mantiene los conceptos previamente desbloqueados; los nuevos retos requieren nuevos intentos. Los cuatro registros archivados siguen presentes para resolver claves foráneas y evidencia.

Recursos limitados: compilaciones y suites pesadas secuenciales, Java/Node 384 MiB según los scripts existentes, build Docker 1536 MiB/2 CPU. No se borran volúmenes ni datos del proyecto.

Advertencias no bloqueantes: tamaño del bundle y metadatos Maven/P2 en el build Docker; ambos builds terminaron correctamente. `npm audit`: 0 vulnerabilidades.

Publicación autorizada: un único commit `feat: expand guided reasoning challenges`, seguido de push de `main` sin force. El hash resultante y la coincidencia con origin/main se verifican y se entregan en el cierre; no se reescribe el checkpoint anterior.
