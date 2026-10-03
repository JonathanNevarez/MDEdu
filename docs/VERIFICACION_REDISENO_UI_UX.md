# Verificación del rediseño visual/UX

Fecha: 02–03/10/2026 (America/Guayaquil). Checkpoint inicial: `67bebe07f02ed71ddfe808e66ca5341e41225d6d`, rama `main`, árbol limpio y sincronizado con MDEdu antes de editar.

Rediseño de presentación posterior al prototipo. Las fases 0–13 permanecen completadas; no se crea Fase 14. Diseño y decisiones: [REDISENO_UI_UX.md](REDISENO_UI_UX.md).

## Ejecuciones reales

Las tareas pesadas se ejecutaron secuencialmente en Windows con 16 GiB. Se reutilizaron los scripts existentes, con procesos Java/Node y builder Docker limitados. No se instalaron dependencias nuevas ni se usaron APIs LLM reales.

| Comprobación | Resultado |
|---|---|
| `scripts/verify-all.ps1`: MDE clean install, incluido verify | 49 pruebas: 22 Xtext, 25 Acceleo, 2 ATL; cero fallos/errores |
| Backend clean test | 265 pruebas unitarias/MVC; cero fallos/errores |
| Backend verify | 265 unitarias/MVC y 87 IT; cero fallos/errores/omitidas en los XML |
| Acceleo CLI y `node --check` | PASS, JavaScript generado válido |
| `npm ci` y `npm test` | PASS; 76 pruebas, 11 archivos |
| TypeScript y build de producción | PASS; también repetidos por el E2E final |
| `npm audit --audit-level=high` | 0 vulnerabilidades |
| E2E con LLM DISABLED | 16 PASS; 2 casos Gemini omitidos en este modo |
| E2E Gemini HTTP loopback | 2 PASS, incluida cuota y fallback; sin proveedor real |
| `scripts/docker-smoke.ps1 -Browser` | Smoke completo PASS y 4 pruebas de navegador PASS |
| `scripts/docker-smoke.ps1 -SkipBuild` | Revalidación final de las imágenes ya construidas; PASS |
| `scripts/check-secrets.ps1 -IncludeBundle -IncludeLogs` | PASS tras normalizar los nombres de las capturas |
| Comparación SHA-256 contra el checkpoint | 558 archivos protegidos intactos; 19 hashes explícitos coincidentes |
| Migraciones | V1–V7 intactas; no V8 |

El orquestador `verify-all.ps1` se detuvo inicialmente en el control de secretos porque Git entrecomillaba un nombre nuevo con tilde y el verificador no interpretaba esa ruta. No se informa una salida 0 del orquestador completo: sus verificaciones anteriores pasaron; se corrigió el nombre de evidencia, se ejecutó nuevamente el control de secretos y se completó Docker por separado. No se modificó el verificador ni la configuración de Git.

El E2E final fue `npm run test:e2e -- --workers=1`, con `UI_EVIDENCE_DIR=../docs/evidencia-ui-ux` y Gemini desactivado: **16 passed, 2 skipped**, salida 0. Los dos omitidos se ejecutaron y aprobaron por separado en modo Gemini mock. Los tests previos conservan sus comprobaciones: solo se actualizó el encabezado de inicio y se abrieron los nuevos detalles antes de verificar los datos originales.

## Regresiones preservadas

Autenticación docente, sesión, CSRF, cierre de sesión, CSP/Nginx, aislamiento entre estudiantes, StudentModel persistido, evaluación funcional y conceptual separada, navegación autorizada por servidor, adaptación A/B, código escapado, telemetría/timeline y fallback LLM: PASS en las suites existentes y el smoke real. El caso LOOPS manual llega funcionalmente a la meta pero sigue sin aprobar el concepto; el rediseño conserva ese resultado y sus patrones.

Docker construyó backend y frontend finales, creó una base fresca, aplicó V1–V7 y verificó los cuatro recorridos, UI y timeline autenticada. También probó readiness 503 al detener PostgreSQL y recuperación al restaurarlo, manteniendo liveness. Se retiraron los contenedores y redes temporales mediante el cleanup existente; no se borraron volúmenes previos.

Cuatro actividades, cero refuerzos y cero temas avanzados. Backend, contratos, Ecore, Xtext, ATL, Acceleo, políticas, prompts, reglas y parámetros no reciben cambios. La Meta-IU continúa en solo lectura.

## Accesibilidad y revisión visual

Dos recorridos adicionales reales cubren 1440×900 y 1366×768: mapa y cuatro actividades, evaluación, progreso, laboratorio, acceso y consulta docente, reglas, parámetros, timeline, carga, error y 404. Se comprueban ausencia de overflow horizontal y etiquetas dentro del mapa. Las capturas son de contenido real con estudiantes sintéticos; los estados de carga/error usan solamente interceptación del transporte.

Se revisaron visualmente las 42 capturas finales y el panel de código de la prueba adaptativa. Algunas son full-page o recortes del componente y tienen altura superior al viewport; las resoluciones indicadas corresponden al viewport de ejecución. La revisión detectó y corrigió un recorte inicial del mapa de 1366×768 antes del cierre.

Cada resolución incorpora 10 auditorías axe: **0 critical y 0 serious**. Se mantienen visibles las observaciones moderadas `region` del canvas añadido por Blockly y los checks incompletos; no se excluyeron selectores para ocultarlos. Los E2E existentes verifican teclado, skip link, foco, login, código escapado y reduced motion.

| Par de colores de tokens | Contraste calculado |
|---|---|
| Texto / fondo | 11.03:1 |
| Texto secundario / superficie | 5.50:1 |
| Blanco / primario | 6.62:1 |
| Éxito / fondo suave | 5.87:1 |
| Aviso / fondo suave | 6.22:1 |
| Error / fondo suave | 6.00:1 |

No se afirma una certificación universal de accesibilidad. La validación cubre las rutas, estados y resoluciones descritos; el teclado interno de Blockly conserva sus capacidades previas. Los estados no dependen únicamente del color.

## Incidencias resueltas y advertencias

- Actualizadas las pruebas de parámetros para abrir sus detalles nativos, conservando todas las aserciones de valores.
- Una nueva aserción de éxito suponía tutor visible; se corrigió para respetar `HIDDEN` del backend y comprobar el resultado real.
- Corregido el recorte del mapa en laptop y añadida una comprobación geométrica.
- Normalizados los nombres de capturas a ASCII para el verificador de secretos existente.
- La redirección PowerShell del primer smoke presentó código externo 1 aunque el log terminó con PASS; se repitió el smoke sin rebuild propagando explícitamente el código del proceso.
- Persiste la advertencia del chunk Blockly de 720.18 kB (197.97 kB gzip), ya separado; no se añadieron librerías. Main JS: 273.78 kB (87.33 kB gzip).
- Maven conserva avisos de metadata EMF/p2 en caché; los builds y pruebas finalizaron correctamente. Playwright avisa que `FORCE_COLOR` prevalece sobre `NO_COLOR`. Git avisa normalización LF/CRLF; `diff --check` no encuentra errores.
- Luma es un SVG original provisional y sustituible. No es un chatbot y no añade estados pedagógicos.

## Evidencia visual y auditorías

[Auditorías 1440](evidencia-ui-ux/1440-audits.json) · [Auditorías 1366](evidencia-ui-ux/1366-audits.json).

| Estado | 1440 x 900 | 1366 x 768 |
|---|---|---|
| 404 | [captura](evidencia-ui-ux/1440-404.jpg) | [captura](evidencia-ui-ux/1366-404.jpg) |
| carga | [captura](evidencia-ui-ux/1440-carga.jpg) | [captura](evidencia-ui-ux/1366-carga.jpg) |
| ciclos | [captura](evidencia-ui-ux/1440-ciclos.jpg) | [captura](evidencia-ui-ux/1366-ciclos.jpg) |
| conditionals | [captura](evidencia-ui-ux/1440-conditionals.jpg) | [captura](evidencia-ui-ux/1366-conditionals.jpg) |
| docente-adaptaciones | [captura](evidencia-ui-ux/1440-docente-adaptaciones.jpg) | [captura](evidencia-ui-ux/1366-docente-adaptaciones.jpg) |
| docente-intentos | [captura](evidencia-ui-ux/1440-docente-intentos.jpg) | [captura](evidencia-ui-ux/1366-docente-intentos.jpg) |
| docente-login | [captura](evidencia-ui-ux/1440-docente-login.jpg) | [captura](evidencia-ui-ux/1366-docente-login.jpg) |
| docente-modelo | [captura](evidencia-ui-ux/1440-docente-modelo.jpg) | [captura](evidencia-ui-ux/1366-docente-modelo.jpg) |
| docente-parametros | [captura](evidencia-ui-ux/1440-docente-parametros.jpg) | [captura](evidencia-ui-ux/1366-docente-parametros.jpg) |
| docente-reglas | [captura](evidencia-ui-ux/1440-docente-reglas.jpg) | [captura](evidencia-ui-ux/1366-docente-reglas.jpg) |
| docente-timeline | [captura](evidencia-ui-ux/1440-docente-timeline.jpg) | [captura](evidencia-ui-ux/1366-docente-timeline.jpg) |
| docente-vacio | [captura](evidencia-ui-ux/1440-docente-vacio.jpg) | [captura](evidencia-ui-ux/1366-docente-vacio.jpg) |
| error | [captura](evidencia-ui-ux/1440-error.jpg) | [captura](evidencia-ui-ux/1366-error.jpg) |
| feedback-exito | [captura](evidencia-ui-ux/1440-feedback-exito.jpg) | [captura](evidencia-ui-ux/1366-feedback-exito.jpg) |
| feedback-pedagogico | [captura](evidencia-ui-ux/1440-feedback-pedagogico.jpg) | [captura](evidencia-ui-ux/1366-feedback-pedagogico.jpg) |
| inicio | [captura](evidencia-ui-ux/1440-inicio.jpg) | [captura](evidencia-ui-ux/1366-inicio.jpg) |
| laboratorio | [captura](evidencia-ui-ux/1440-laboratorio.jpg) | [captura](evidencia-ui-ux/1366-laboratorio.jpg) |
| mapa | [captura](evidencia-ui-ux/1440-mapa.jpg) | [captura](evidencia-ui-ux/1366-mapa.jpg) |
| progreso | [captura](evidencia-ui-ux/1440-progreso.jpg) | [captura](evidencia-ui-ux/1366-progreso.jpg) |
| secuencias | [captura](evidencia-ui-ux/1440-secuencias.jpg) | [captura](evidencia-ui-ux/1366-secuencias.jpg) |
| variables | [captura](evidencia-ui-ux/1440-variables.jpg) | [captura](evidencia-ui-ux/1366-variables.jpg) |

## Hashes protegidos verificados

SHA-256 de archivos, idénticos al checkpoint inicial. No confundir con hashes semánticos de las respuestas API.

| Archivo | SHA-256 |
|---|---|
| `backend/src/main/resources/adaptation/adaptation-parameters.v1.json` | `173330ef2ceb7960efb9f5c47bd6d1b5d6230f5eff1ab11b8e538585f0f88d62` |
| `backend/src/main/resources/adaptation/rules/rules-v1.adapt` | `b4830f6c6e2f9cac681649c64eddccb42bc5b8903413f8d934271237c9c49f70` |
| `backend/src/main/resources/llm/llm-policy.v1.json` | `08c3986b31b2e1a1381baae8f4f1ac71a283135fb93f136f311261d274dbaf4a` |
| `backend/src/main/resources/llm/prompts/pedagogical-feedback-v1.txt` | `a6c7bf084a58569fc74c9f2587d126c88716867af7c798d284159722bae63eda` |
| `backend/src/main/resources/llm/prompts/unknown-case-v1.txt` | `2f08841297b50a00f8985c211720e4ab6bd60ab27d3277491f230ec0505ca4c4` |
| `backend/src/main/resources/llm/unknown-case-tags.v1.json` | `9f7e5209cbd1faae8f372d1c4c4bc3c8c83706603b08132374ef9e6df94a57e2` |
| `mde/com.project.mde.adaptation.dsl/src/main/java/com/project/mde/adaptation/dsl/AdaptationRules.xtext` | `df841202185952e797651068514e958fad04090ffd6b4eaf1a0dd8cd3ad4d683` |
| `mde/com.project.mde.adaptation.model/model/adaptation.ecore` | `ed3f63b5227e07421f696a04690becb4778758ed7e3ed7c0c39d9ddb58eb8243` |
| `mde/com.project.mde.adaptation.model/model/adaptation.genmodel` | `b22b13a6ce00657c86fa87d6ab7d99199b04b1f1bd291c6e32e019519d2c68ff` |
| `mde/com.project.mde.context.model/model/context.ecore` | `4d9ccb0d4f7920c4ed1b970d7b589eb8410ae914c3a97390b5b03479efd36741` |
| `mde/com.project.mde.context.model/model/context.genmodel` | `27b1191776acf0fb8f5bf19880b71210f108647bcff2d150dabb0a44e0073079` |
| `mde/com.project.mde.learning.model/model/learning.ecore` | `3b7e10dff04fc7af3ec91649986322271023afb4207dd57de3c9f13f4fa2eb45` |
| `mde/com.project.mde.learning.model/model/learning.genmodel` | `1deea0b68799173dd419a227408fb9a33aecf8e49bfe20cb662de0a1926297be` |
| `mde/com.project.mde.programming.model/model/programming.ecore` | `6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df` |
| `mde/com.project.mde.programming.model/model/programming.genmodel` | `b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451` |
| `mde/com.project.mde.ui.model/model/ui.ecore` | `07f35993cc78d88177ba1014ac5d00a19a2369a10094f30a145342fb6afc6be5` |
| `mde/com.project.mde.ui.model/model/ui.genmodel` | `62f87f852ba2e193b49d83ba3ea8665ce4424a77c601b3a06641b730e669ce32` |
| `mde/com.project.mde.ui.transformations/transformations/AbstractUI2ConcreteUI.atl` | `a39f7761588aa7ed5327da69fea421c10fd27d8159eeaef912ac97541a293a64` |
| `mde/com.project.mde.ui.transformations/transformations/TaskAndDomain2AbstractUI.atl` | `65679185d34feb210013c1fcb4f5d381d450f7d3826706fd0316cf092bf69d69` |

### Migraciones preservadas

| Archivo | SHA-256 |
|---|---|
| `backend/src/main/resources/db/migration/V1__bootstrap.sql` | `4663db0737c03a53e4ce76b467d56492a275d786648eec80ec0ae2839b55fe62` |
| `backend/src/main/resources/db/migration/V2__learning_model.sql` | `a4d2c3d6e83156632d9d18699bfb3ba86b361b0fe96972756a82d75d7317f1ee` |
| `backend/src/main/resources/db/migration/V3__adaptation_decisions.sql` | `61a6ea358ed941a73ac798620765154f2f02dedea415f5ce7e10fa65822f4825` |
| `backend/src/main/resources/db/migration/V4__feedback_records.sql` | `0e195e93055157f33e9f7554fad4f9aa5ada598c0b2f1655bada7f7df16ebc9a` |
| `backend/src/main/resources/db/migration/V5__attempt_programs.sql` | `bf30ab6bc1d88bd6ce639c7793b0e8306b23bc2cb80abfd06edc2ea9c88db774` |
| `backend/src/main/resources/db/migration/V6__gemini_feedback_provider.sql` | `5012bf4bb4105649438b25b123277071e2b0a13c10f537639e257f93f81ea8cb` |
| `backend/src/main/resources/db/migration/V7__telemetry_and_sessions.sql` | `a259891d024644a0b89d8185c4a32d9f66901200cc0627afa0386a249efcae9e` |

Los hashes semánticos de referencia de reglas (`7d073441a373a915454ed4c954727d50eee0ffcbba00509615cf4f5555c86c9e`) y parámetros (`13cac42690039160bb6fb3193f17e3efaed2a05f25750c55ea0cdccb29ad3b6d`) pertenecen al checkpoint previo. En este cierre se compararon los archivos SHA-256 y se ejecutaron las regresiones de lectura; no se recalcularon esos dos hashes semánticos de referencia.
