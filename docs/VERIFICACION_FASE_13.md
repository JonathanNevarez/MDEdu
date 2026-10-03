# Verificación de Fase 13 — calidad final

Fecha: 02/10/2026 (America/Guayaquil; algunos logs corresponden al 03/10 UTC).
Checkpoint inicial verificado: `8c95a04adf880c7511b1e66f82c11163da71c191`, main=origin/main, working tree limpio. Remoto MDEdu correcto. No se cambia pedagogía, contenido ni migraciones.

## Resultados ejecutados

| Comprobación | Resultado real |
|---|---|
| MDE clean install (incluye verify) | BUILD SUCCESS; Xtext 22, Acceleo 25, ATL 2; 49 pruebas, cero fallos/errores/omitidas |
| Acceleo CLI + node --check | PASS, sequence-basic.programming genera program.js válido |
| Backend clean test | 265 unitarias/MVC, cero fallos/errores/omitidas |
| Backend verify | 265 unitarias/MVC + 87 IT, cero fallos/errores/omitidas |
| SecurityPolicyTest / SecurityIT | 4 / 6 PASS; auth real, CSRF, cookies, CORS, headers, límites, health, cuota concurrente, fail-closed y FAKE rechazado en prod |
| GeminiFeedbackIT | 17 PASS, incluyendo cuota de una llamada real y fallback sin segunda llamada HTTP |
| Frontend npm ci / test | instalación limpia, 73 tests PASS en 10 archivos, un worker |
| Typecheck y build finales | PASS; última compilación incluye ajustes de foco y tamaño de logout |
| E2E DISABLED | 14 PASS; 2 casos Gemini omitidos intencionalmente en esta ejecución |
| E2E Gemini HTTP local | 2 PASS; ejecución separada de los dos casos omitidos, cero llamadas a APIs reales |
| E2E Docker/Nginx | 4 PASS en 18.4 s; actividad, login/logout, axe y backend-down; sin errores CSP |
| npm audit | 0 vulnerabilidades (120 paquetes auditados) |
| Maven dependency:tree | BUILD SUCCESS; Spring Security 6.5.11 administrado por Boot 3.5.16; sin upgrades masivos ni duplicados conflictivos evidentes |
| scripts/verify-all.ps1 | Todas las etapas PASS; salida final `All requested verification stages PASS.` |
| scripts/docker-smoke.ps1 -Browser | PASS, tras incorporar la comprobación adicional del navegador |
| start-dev / stop-dev | backend readiness UP, frontend HTTP 200; puertos 8080/5173 liberados al detener; volumen conservado |
| Secret checker | PASS en fuentes, ejemplo de entorno, bundle y logs; no imprime valores |
| Modelos/activos protegidos | 325 archivos comparados con snapshot inicial sin diferencias |

`verify-all` ejecuta las etapas secuencialmente, con heap Java/Node de 384 MiB y un worker. La extensión final `-Browser` del smoke se comprobó adicionalmente por separado. La última comprobación frontend posterior al ajuste visual de logout volvió a pasar 73 tests, typecheck y build.

## Seguridad y errores

Spring Security protege Meta-IU, reglas/parámetros, inspección de decisiones y timelines docentes. Login `/docente/login`, sesión backend, BCrypt, CSRF en login/logout/escrituras docentes; logout invalida sesión. Credenciales exclusivamente de entorno, sin defaults. Cookie HttpOnly, SameSite Strict, Secure en prod; prueba HTTP local Docker usa override explícito false.

UUID/enum/paginación inválidos y anidamiento excesivo: 400; cuerpo mayor de 256 KiB: 413. Program mantiene límites estructurales de 100 niveles/10000 statements y ejecución de operaciones cerradas con límite de pasos. ErrorEnvelopeAdvice conserva contratos existentes mediante árbol JSON; status HTTP numérico disponible sin romper status de dominio. Respuestas sin SQL, clases Java ni stack traces.

CORS explícito; API y Nginx aplican nosniff, referrer y protección de frames. CSP de Nginx permite scripts propios y estilos inline necesarios de Blockly. E2E real contra Nginx comprobó edición/ejecución sin errores CSP. ErrorBoundary y errores HTTP centralizados preservan recuperación y cancelación. Backend no disponible se muestra como estado controlado.

Limitador LLM sincronizado y acotado por estudiante (1024 claves) más techo global; cobra cada HTTP real, incluyendo retries. N+1 usa FALLBACK/RATE_LIMITED sin tráfico adicional, comprobado con contador del servidor local y E2E. Timeout 50..10000 ms y máximo 2 retries permanecen acotados. Single-instance, sin Redis. Proveedor FAKE rechazado en prod; launchers mock excluidos del JAR productivo.

## Accesibilidad y experiencia

Axe 4.13.0, dos escenarios de accesibilidad con seis estados/rutas: `/`, `/aventura`, `/aventura/SEQUENCES`, `/aventura/LOOPS` con feedback, `/docente/login`, `/docente`. Cero critical y cero serious en desarrollo y Docker. En inicio/login/Meta-IU/mapa no hubo violaciones. Actividad/feedback conservan una regla moderate `region`: canvas interno `blocklyComputeCanvas` fuera de landmarks. No se reescribe internamente Blockly por esta observación no bloqueante. Axe indica también dos comprobaciones incompletas en mapa/actividad/feedback, que requieren revisión humana.

Teclado: skip-link y foco del contenido, añadir bloques de secuencia/ejecutar con Enter, reintento, controles docentes/timeline y login por Enter en Docker. Luma y HintPanel son contenido estático, no controles interactivos. Focus visible global; estados tienen texto. Reduced motion conserva animaciones desactivadas. Pruebas PC/laptop 1440×900 y 1280×800; sin desbordamiento horizontal en actividad evaluada. No se afirma certificación WCAG ni compatibilidad universal de lector de pantalla.

## Regresión de extremo a extremo

Caso maestro PASS: estudiante nuevo, desbloqueo de cuatro conceptos, LOOPS manual tres veces, functional=true, activityPassed=false, patrón REPETITIVE_SEQUENCE_WITHOUT_LOOP, consecutiveFailures=3, masteryDelta no positivo, adaptación, fallback, UI/Luma, reintento, telemetría COMPLETE y consulta docente. Fixture de presentación PARTIAL sin cambiar evidencia persistida. Otros E2E comprueban ADVANCE real, código escapado sin ejecución, aislamiento A/B, reglas/parámetros y lecturas sin mutación.

Las regresiones F1–F12 siguen verdes: EMF, Blockly→DTO/modelo, Acceleo, GridWorld, evaluación, StudentModel, Xtext/ECA, AdaptationManager, LLM/fallback/Gemini, UI/ATL, telemetría y Meta-IU. Las pruebas previas de XSS, sanitización, PII y aislamiento permanecen incluidas; no se amplía pedagogía. Cuatro actividades principales, cero refuerzos reales y cero temas avanzados.

## Docker y persistencia

Imágenes multi-stage construidas realmente desde fuente, MDE para linux/gtk; backend UID 10001, frontend usuario no-root. Frontend runtime contiene dist, backend solo JAR y runtime. `.env`, claves, node_modules/target locales, logs y metadatos excluidos del contexto. Inspección de usuario y ausencia de `.env`/workspace en imágenes PASS. Compose anterior de PostgreSQL intacto.

Smokes aislados crean DB nueva y aplican V1→V7; los tres servicios healthy, frontend/SPA/proxy/health PASS, cuatro actividades y timeline autenticado PASS. Al detener DB, readiness=503 mientras liveness continúa UP; al volver a iniciarla, readiness recupera UP. `down` retiró contenedores/redes de prueba y conservó volúmenes. El volumen previo también arrancó con los scripts sin errores de migración. No V8.

## Incidencias y reparaciones

- La primera compilación Docker redujo la RAM libre a cerca de 1 GiB. Se detuvo de forma controlada y se liberó WSL; constructor posterior limitado a 1536 MiB/dos CPU, builds secuenciales. No se cambiaron límites globales Windows/WSL ni se borraron volúmenes.
- El contexto inicial del frontend no incluía contratos compartidos. Se corrigió el contexto raíz y COPY explícitos, sin cambiar contratos.
- Se corrigieron imports de tests al introducir seguridad, expectativas de health/CORS y tipos de pruebas de navegador.
- Una respuesta antigua Map<String,String> no serializaba status numérico añadido. ErrorEnvelopeAdvice devuelve árbol JSON; regresión LearningIT y suite completa PASS.
- La verificación final de dos procesos detectó que PowerShell trataba el array JSON de PIDs como un único elemento. Se corrigió la enumeración en stop-dev y se espera la terminación real con WaitForExit; se conserva el estado y se rechaza detener un PID cuya identidad cambió. Se repitió arranque/parada de backend + Vite.
- La normalización frontend reemplazó mensajes de red crudos por mensajes seguros, conservando AbortError y recuperación acotada.

Avisos no bloqueantes: metadata Maven p2 consultada desde consumidor JVM (artefactos resueltos y build PASS), advertencias Tycho de artefactos no OSGi, aviso Vite de chunk Blockly >500 kB, avisos de color de consola/Java de tests y observación moderate del canvas Blockly. Las imágenes base usan tags de versión, no se promete reproducción bit a bit frente a cambios futuros de registros. Sin TODO crítico, debug endpoint productivo, eval/new Function/Runtime.exec/ProcessBuilder ni inserción HTML arbitraria en fuentes productivas. La coincidencia textual FunctionalCorrectness no es ejecución dinámica.

## Hashes protegidos

- `backend/src/main/resources/adaptation/adaptation-parameters.v1.json`: `173330ef2ceb7960efb9f5c47bd6d1b5d6230f5eff1ab11b8e538585f0f88d62`
- `backend/src/main/resources/adaptation/rules/rules-v1.adapt`: `b4830f6c6e2f9cac681649c64eddccb42bc5b8903413f8d934271237c9c49f70`
- `backend/src/main/resources/llm/llm-policy.v1.json`: `08c3986b31b2e1a1381baae8f4f1ac71a283135fb93f136f311261d274dbaf4a`
- `backend/src/main/resources/llm/prompts/pedagogical-feedback-v1.txt`: `a6c7bf084a58569fc74c9f2587d126c88716867af7c798d284159722bae63eda`
- `backend/src/main/resources/llm/prompts/unknown-case-v1.txt`: `2f08841297b50a00f8985c211720e4ab6bd60ab27d3277491f230ec0505ca4c4`
- `backend/src/main/resources/llm/unknown-case-tags.v1.json`: `9f7e5209cbd1faae8f372d1c4c4bc3c8c83706603b08132374ef9e6df94a57e2`
- `mde/com.project.mde.adaptation.dsl/src/main/java/com/project/mde/adaptation/dsl/AdaptationRules.xtext`: `df841202185952e797651068514e958fad04090ffd6b4eaf1a0dd8cd3ad4d683`
- `mde/com.project.mde.adaptation.model/model/adaptation.ecore`: `ed3f63b5227e07421f696a04690becb4778758ed7e3ed7c0c39d9ddb58eb8243`
- `mde/com.project.mde.adaptation.model/model/adaptation.genmodel`: `b22b13a6ce00657c86fa87d6ab7d99199b04b1f1bd291c6e32e019519d2c68ff`
- `mde/com.project.mde.context.model/model/context.ecore`: `4d9ccb0d4f7920c4ed1b970d7b589eb8410ae914c3a97390b5b03479efd36741`
- `mde/com.project.mde.context.model/model/context.genmodel`: `27b1191776acf0fb8f5bf19880b71210f108647bcff2d150dabb0a44e0073079`
- `mde/com.project.mde.learning.model/model/learning.ecore`: `3b7e10dff04fc7af3ec91649986322271023afb4207dd57de3c9f13f4fa2eb45`
- `mde/com.project.mde.learning.model/model/learning.genmodel`: `1deea0b68799173dd419a227408fb9a33aecf8e49bfe20cb662de0a1926297be`
- `mde/com.project.mde.programming.model/model/programming.ecore`: `6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df`
- `mde/com.project.mde.programming.model/model/programming.genmodel`: `b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451`
- `mde/com.project.mde.ui.model/model/ui.ecore`: `07f35993cc78d88177ba1014ac5d00a19a2369a10094f30a145342fb6afc6be5`
- `mde/com.project.mde.ui.model/model/ui.genmodel`: `62f87f852ba2e193b49d83ba3ea8665ce4424a77c601b3a06641b730e669ce32`
- `mde/com.project.mde.ui.transformations/transformations/AbstractUI2ConcreteUI.atl`: `a39f7761588aa7ed5327da69fea421c10fd27d8159eeaef912ac97541a293a64`
- `mde/com.project.mde.ui.transformations/transformations/TaskAndDomain2AbstractUI.atl`: `65679185d34feb210013c1fcb4f5d381d450f7d3826706fd0316cf092bf69d69`

Ruleset semántico: `7d073441a373a915454ed4c954727d50eee0ffcbba00509615cf4f5555c86c9e`.
Parameters semántico: `13cac42690039160bb6fb3193f17e3efaed2a05f25750c55ea0cdccb29ad3b6d`.
Las consultas reales de reglas/parámetros permanecen cubiertas por regresiones; archivos y semántica no modificados. V1–V7 intactas.

## Evidencia local y cierre Git

Logs de ejecución fuera de Git, en TEMP: `mdedu-phase13-verify-all.log`, `mdedu-phase13-docker-browser.log`, `mdedu-phase13-final-frontend.log`, `mdedu-phase13-dependencies.log`; logs de runtime en `.quality/`. JSON axe y capturas en `frontend/test-results/`. No se versionan artefactos, credenciales, capturas ni volúmenes.

Commit autorizado de cierre: `chore: finalize prototype quality`. El hash definitivo y la sincronización main/origin/main/remoto se verifican después del commit y push; no se incrusta un hash autorreferente en este archivo. Sin tags/releases ni fase adicional.
