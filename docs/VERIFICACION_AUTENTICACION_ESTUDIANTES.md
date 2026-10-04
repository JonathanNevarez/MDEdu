# Verificación de autenticación de estudiantes

Fecha: 03/10/2026, America/Guayaquil.

## Punto de partida y alcance

La solicitud esperaba `67bebe07f02ed71ddfe808e66ca5341e41225d6d`. Se detectó HEAD limpio `c8219382101070373088082355571f7036fcf7c3` (`style: redesign educational interface`), sincronizado con origin/main. Se detuvo el trabajo y el usuario autorizó explícitamente continuar desde ese HEAD conservando el rediseño. No se hizo reset, restore, clean de Git, stash, amend ni rebase.

Autenticación post-prototipo: cuenta pseudónima, provisión docente, sesión backend y autorización por propietario. Se mantienen cuatro actividades, políticas pedagógicas y fases 0–13. Sin Fase 14, nuevos retos ni rediseño adicional. [Diseño y operación](AUTENTICACION_ESTUDIANTES.md).

## Resultados ejecutados

| Comprobación | Resultado |
|---|---|
| Backend `test`, integrado también en `verify` | 267 pruebas unitarias/MVC; 0 fallos, errores u omisiones |
| Backend `verify` | 95 IT; 0 fallos, errores u omisiones |
| StudentAuthenticationIT | 8 PASS con PostgreSQL real/Testcontainers |
| Concurrencia del limitador | PASS; 20 solicitudes simultáneas admiten solo 5 cupos; éxito no borra fallos previos |
| Frontend `npm test -- --maxWorkers=1` | 78 PASS en 12 archivos |
| Frontend `npm run typecheck` | PASS |
| Frontend `npm run build` | PASS |
| Playwright, proveedor DISABLED | 17 PASS; 2 casos exclusivos de Gemini omitidos en este modo |
| Playwright Gemini mock loopback | 2 PASS por separado; sin proveedor externo real |
| MDE `clean verify` | 49 PASS: Xtext 22, Acceleo 25, ATL 2 |
| Docker smoke y navegador | PASS, salida 0; 5 E2E sobre la imagen final, incluido multidispositivo |
| Control de secretos en fuentes, bundle y logs | PASS, salida 0; sin claves reales detectadas |
| SHA-256 protegido | 372 archivos idénticos al checkpoint autorizado |

Los conteos de backend/MDE se obtuvieron de los XML de Surefire/Failsafe, no solamente de mensajes de consola. Las tareas pesadas se ejecutaron secuencialmente con los límites Java/Node/builder del proyecto. La ejecución normal conserva LLM_PROVIDER=DISABLED; el mock loopback solo pertenece a pruebas aisladas.

Los DTOs de login y entrega de clave enmascaran sus secretos en `toString`; una prueba específica impide su representación accidental en logs de diagnóstico.

## Seguridad e identidad comprobadas

`StudentAuthenticationIT` prueba login normalizado y rotación de ID de sesión; mismo StudentModel/progreso/historial después de logout y login con una sesión nueva; rechazo de lecturas y escrituras sobre otro estudiante; provisión docente y BCrypt; claves excluidas de los listados; reset que revoca dos dispositivos; disable/re-enable; CSRF; logout/invalidation; respuesta idéntica para código inexistente y clave incorrecta; rate limit 429; seis creaciones concurrentes con códigos únicos y un Student por cuenta.

El nuevo E2E provisiona desde **Participantes**, oculta la clave de única presentación, inicia sesión por el formulario, completa Secuencias, cierra sesión y abre otro contexto de navegador sin cookies/localStorage/sessionStorage. Compara exactamente UUID, StudentModel y progreso. Comprueba aislamiento A/B, manipulación del UUID en localStorage, consulta docente de ambos, revocación por reset y rechazo de la clave anterior. Las pruebas previas de telemetría, adaptación, Meta-IU y evaluación permanecen activas con cuentas provisionadas por API real; no hay un bypass de autenticación para pruebas en producción.

Se conserva la prueba de cookie real HttpOnly/Secure/SameSite Strict en perfil de producción. La expiración HTTP sigue siendo 30 minutos por configuración del contenedor; se probó invalidación explícita, no una espera de 30 minutos. No se afirma una auditoría externa ni una prueba de carga distribuida.

## Persistencia y compatibilidad

V8 crea `student_accounts` y `student_code_sequence`. V1–V7 permanecen byte a byte. El contador puede dejar huecos, pero no duplicar códigos en concurrencia. La transacción de provisión enlaza la cuenta a Student y su modelo actual. No se añaden datos de identidad civil ni registro público.

Los registros históricos anónimos permanecen. No se asignan cuentas usando UUID aportados por el navegador. La caché local del UUID solo se escribe desde `/me`, nunca se lee para autorizar. Las sesiones de telemetría y autenticación conservan propósitos distintos; el código EST no se añadió a los eventos. No se borraron volúmenes ni datos previos. Los participantes creados durante E2E son fixtures sintéticos.

## Incidencias resueltas y límites

- Las pruebas antiguas suponían APIs anónimas y siete migraciones. Se actualizaron sus preparaciones con rol/CSRF o provisión/login real y V8, conservando las comprobaciones pedagógicas.
- Las pruebas unitarias de creación automática de identidad se reemplazaron por identidad autenticada, ausencia de creación anónima, UUID manipulado y recuperación sin apropiación de históricos. Se añadieron pruebas de guard y transporte CSRF.
- El control de secretos debe ejecutarse una vez cerrados los logs: una ejecución durante Docker encontró el archivo de log ocupado; no fue un hallazgo de credenciales.
- Persisten los avisos de metadata Maven EMF/p2, tamaño del chunk Blockly y FORCE_COLOR/NO_COLOR. Las observaciones axe moderadas de Blockly conservan su documentación previa; no se cambiaron sus selectores para ocultarlas.
- El limitador usa el peer real de conexión, no cabeceras reenviadas; tras un proxy los usuarios comparten ese límite. Sus ventanas y las sesiones HTTP son por instancia. Reiniciar exige nuevo login, pero conserva el progreso PostgreSQL.

## Evidencia de integridad

La comparación SHA-256 incluye todos los archivos MDE y contratos versionados, políticas learning/adaptation/LLM, prompts, migraciones anteriores y lógica de evaluación, mastery, AdaptationManager y proyección UI. Cambian fronteras API de autorización, no sus algoritmos pedagógicos.

| Activo protegido | SHA-256 sin cambios |
|---|---|
| `backend/src/main/resources/adaptation/adaptation-parameters.v1.json` | `173330ef2ceb7960efb9f5c47bd6d1b5d6230f5eff1ab11b8e538585f0f88d62` |
| `backend/src/main/resources/adaptation/rules/rules-v1.adapt` | `b4830f6c6e2f9cac681649c64eddccb42bc5b8903413f8d934271237c9c49f70` |
| `backend/src/main/resources/db/migration/V1__bootstrap.sql` | `4663db0737c03a53e4ce76b467d56492a275d786648eec80ec0ae2839b55fe62` |
| `backend/src/main/resources/db/migration/V2__learning_model.sql` | `a4d2c3d6e83156632d9d18699bfb3ba86b361b0fe96972756a82d75d7317f1ee` |
| `backend/src/main/resources/db/migration/V3__adaptation_decisions.sql` | `61a6ea358ed941a73ac798620765154f2f02dedea415f5ce7e10fa65822f4825` |
| `backend/src/main/resources/db/migration/V4__feedback_records.sql` | `0e195e93055157f33e9f7554fad4f9aa5ada598c0b2f1655bada7f7df16ebc9a` |
| `backend/src/main/resources/db/migration/V5__attempt_programs.sql` | `bf30ab6bc1d88bd6ce639c7793b0e8306b23bc2cb80abfd06edc2ea9c88db774` |
| `backend/src/main/resources/db/migration/V6__gemini_feedback_provider.sql` | `5012bf4bb4105649438b25b123277071e2b0a13c10f537639e257f93f81ea8cb` |
| `backend/src/main/resources/db/migration/V7__telemetry_and_sessions.sql` | `a259891d024644a0b89d8185c4a32d9f66901200cc0627afa0386a249efcae9e` |
| `backend/src/main/resources/llm/llm-policy.v1.json` | `08c3986b31b2e1a1381baae8f4f1ac71a283135fb93f136f311261d274dbaf4a` |
| `backend/src/main/resources/llm/prompts/pedagogical-feedback-v1.txt` | `a6c7bf084a58569fc74c9f2587d126c88716867af7c798d284159722bae63eda` |
| `backend/src/main/resources/llm/prompts/unknown-case-v1.txt` | `2f08841297b50a00f8985c211720e4ab6bd60ab27d3277491f230ec0505ca4c4` |
| `backend/src/main/resources/llm/schemas/classification-v1.json` | `9f096e6f7b9015b0d153cdf7b334edf6b191b97a98bfd169e24dc53cad24a71e` |
| `backend/src/main/resources/llm/schemas/feedback-v1.json` | `03f0777d828bd53d5d429ab2f7d01d570112d7d69a05ce271d65f31c61448bb5` |
| `backend/src/main/resources/llm/unknown-case-tags.v1.json` | `9f7e5209cbd1faae8f372d1c4c4bc3c8c83706603b08132374ef9e6df94a57e2` |
| `mde/com.project.mde.adaptation.dsl/src/main/java/com/project/mde/adaptation/dsl/AdaptationRules.xtext` | `df841202185952e797651068514e958fad04090ffd6b4eaf1a0dd8cd3ad4d683` |
| `mde/com.project.mde.adaptation.model/model/adaptation.ecore` | `ed3f63b5227e07421f696a04690becb4778758ed7e3ed7c0c39d9ddb58eb8243` |
| `mde/com.project.mde.adaptation.model/model/adaptation.genmodel` | `b22b13a6ce00657c86fa87d6ab7d99199b04b1f1bd291c6e32e019519d2c68ff` |
| `mde/com.project.mde.context.model/model/context.ecore` | `4d9ccb0d4f7920c4ed1b970d7b589eb8410ae914c3a97390b5b03479efd36741` |
| `mde/com.project.mde.context.model/model/context.genmodel` | `27b1191776acf0fb8f5bf19880b71210f108647bcff2d150dabb0a44e0073079` |
| `mde/com.project.mde.learning.model/model/learning.ecore` | `3b7e10dff04fc7af3ec91649986322271023afb4207dd57de3c9f13f4fa2eb45` |
| `mde/com.project.mde.learning.model/model/learning.genmodel` | `1deea0b68799173dd419a227408fb9a33aecf8e49bfe20cb662de0a1926297be` |
| `mde/com.project.mde.programming.generator/src/main/resources/com/project/mde/generator/main.mtl` | `d2f944488b2b7fac5e0e38276d09e49b842f08c533e4cadac14c8acc129d7d70` |
| `mde/com.project.mde.programming.model/model/programming.ecore` | `6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df` |
| `mde/com.project.mde.programming.model/model/programming.genmodel` | `b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451` |
| `mde/com.project.mde.ui.model/model/ui.ecore` | `07f35993cc78d88177ba1014ac5d00a19a2369a10094f30a145342fb6afc6be5` |
| `mde/com.project.mde.ui.model/model/ui.genmodel` | `62f87f852ba2e193b49d83ba3ea8665ce4424a77c601b3a06641b730e669ce32` |
| `mde/com.project.mde.ui.transformations/transformations/AbstractUI2ConcreteUI.atl` | `a39f7761588aa7ed5327da69fea421c10fd27d8159eeaef912ac97541a293a64` |
| `mde/com.project.mde.ui.transformations/transformations/TaskAndDomain2AbstractUI.atl` | `65679185d34feb210013c1fcb4f5d381d450f7d3826706fd0316cf092bf69d69` |

El smoke final construyó las imágenes, aplicó V1–V8 en una base nueva, verificó los cuatro retos con sesión de estudiante y fallback DISABLED, consultó timeline como docente y comprobó readiness 503 al detener la base y recuperación posterior, con liveness disponible. Retiró contenedores/red temporal conservando volúmenes. `git diff --check` y la revisión de alcance no encontraron cambios en modelos ni lógica protegida.
