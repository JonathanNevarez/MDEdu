# Verificación: despliegue Render + Neon

Fecha local: 2026-10-03 (America/Guayaquil).

## Base y alcance

La inspección inicial encontró el rediseño sin versionar sobre `6697077`. Se detuvo el despliegue y, tras autorización del usuario, se completó su evidencia y publicación independiente como `5f2fe565b9229838e391a1dbc195f8da43bf0e4f` (`style: implement final educational interface`).

**HEAD inicial de este despliegue: `5f2fe565b9229838e391a1dbc195f8da43bf0e4f`.** Antes de implementar se comprobó `main`, working tree limpio y coincidencia con `origin/main` y el hash remoto real.

Sin nueva fase, pedagogía, identidad, diseño, Luma ni retos modificados. Las migraciones V1–V9 permanecen intactas: cero nuevas migraciones. 389 archivos protegidos (MDE, recursos backend, frontend y contratos) conservan SHA-256. Agregado ordenado del manifiesto: `2c52244e6b5b3b25c8f72b7700f7bf5e08aa6818bc5831273e7840d622ba54ff`.

## Arquitectura comprobada

`Dockerfile.render`: Node 24/npm ci/Vite → Maven 3.9.16 + MDE/Java 21 → React incluido en el JAR → Temurin JRE 21, usuario 10001:10001, Java PID 1. Sin Nginx ni credenciales build-time. `render.yaml` declara un Web Service Free, sin DB Render; cinco entradas privadas `sync: false`.

Perfil aditivo `prod,render`: escucha `0.0.0.0:${PORT}` con fallback 8080; forwarded framework, cookies Secure/HttpOnly/Strict, máximo cinco conexiones Hikari, compresión. Readiness `/actuator/health/readiness` incluye DB; liveness `/actuator/health/liveness` no depende de DB.

Rutas React conocidas reciben índice; navegación HTML desconocida conserva 404 y la UI existente. API, actuator, assets y errores JSON no se convierten en SPA. CSP UI conserva restricciones Nginx; CSP API, CSRF, roles y aislamiento permanecen. El JAR inicial inspeccionado contiene 28 entradas estáticas, cinco chunks JS, sin localhost:8080, shell test ni valores secretos de prueba.

La conexión utiliza `SPRING_DATASOURCE_URL/USERNAME/PASSWORD`; URL JDBC con `sslmode=require`. El smoke emplea PostgreSQL 17 vacío con TLS, no una cuenta Neon. Docente usa `META_UI_USERNAME/PASSWORD`. `LLM_PROVIDER=DISABLED`, sin Gemini real.

## Pruebas

| Comprobación | Evidencia |
| --- | --- |
| Backend | 295 unitarias/MVC + 105 IT, cero fallos/errores/omitidas; clean verify final PASS |
| MDE | 49 PASS: Xtext 22, Acceleo 25, ATL 2 |
| Frontend | 80 PASS; typecheck/build PASS |
| npm audit | Cero vulnerabilidades |
| PowerShell y proxy Node | Sintaxis PASS |
| Chromium HTTPS producción | 21 PASS, dos Gemini omitidas intencionalmente con DISABLED; UI, identidad, Blockly, 404, progreso y seguridad |
| Gemini HTTP local | 2 PASS en ejecución separada, sin API real |
| Producción Docker | PASS: nueve migraciones en DB vacía, dos conexiones TLS, Java PID 1, non-root, reinicio y recuperación DB; dos pruebas focalizadas repetidas tras corregir el script |
| Secret scan final | PASS con bundle y logs, procesos detenidos; sin valores secretos impresos |
| Integridad y whitespace | 389 SHA-256 iguales al baseline; git diff --check PASS |

Logs locales ignorados: `.quality/deploy-backend-final.log`, `deploy-routing-final.log`, `deploy-mde.log`, `deploy-frontend-final.log`, `deploy-build-final.log`, `deploy-audit.log`, `deploy-production-pass.log`, `deploy-smoke-recovery.log`. No se cuentan dos veces las repeticiones focalizadas.

La imagen final es `sha256:3f009d76c63ab5ba420a2f996a36c917c8ce802083fe913083834dfdfbc6d4a3`, tamaño local 549895590 bytes (incluye JRE). Se reinspeccionó su JAR final: sin clases obsoletas, secretos test ni localhost API en los cinco chunks JS. Límite runtime: 512 MiB, swap limitado a lo mismo. Memoria observada al terminar los 21 casos: 332.3 MiB; repetición focalizada: 300.2 MiB. Sin OOM. Son mediciones del smoke, no garantía de carga ilimitada ni de rendimiento cloud.

## Incidencias y límites

Se corrigió la negociación de contenido de los 404: el manejador JSON global capturaba la cabecera Accept real del navegador. Una advice condicional sirve el índice exclusivamente para navegación HTML fuera de espacios reservados y conserva HTTP 404. Se añadió regresión con la cabecera real de Chromium; no se excluyeron pruebas.

Se corrigió también la recolección de logs en PowerShell para que stderr nativo no interrumpa la limpieza, y una expresión shell de comprobación PID se sustituyó por lectura directa de `/proc/1/comm`. La suite de 21 casos ya había pasado; se repitieron los dos casos de producción y todo el control de memoria/TLS/Flyway/DB. Los volúmenes de prueba se conservan; no se eliminan datos del proyecto. Advertencias existentes de chunk Vite, colores de consola y metadata Maven/p2 no impiden el build.

La prueba local usa 512 MiB y una CPU; no reproduce la CPU reducida ni latencia de Render Free. No prueba cuotas, DNS, cuentas Neon ni recursos cloud reales. HTTPS local usa un certificado exclusivamente de prueba; el navegador lo acepta solo bajo `PRODUCTION_SMOKE=true`. No se relaja TLS de la aplicación ni del script público.

## Archivos

Creados: `Dockerfile.render`, `render.yaml`, `.env.production.example`, `application-render.properties`; `SpaConfiguration`, `SpaNotFoundAdvice`, `SpaSecurityConfiguration`; `SingleOriginDeploymentIT`, su índice test; `production.spec.ts`; scripts `verify-production.ps1`, `production-test-proxy.cjs`, `check-public-deployment.ps1`; esta evidencia y `DESPLIEGUE_RENDER_NEON.md`.

Modificados: `.dockerignore`, `.gitignore`, `frontend/playwright.config.ts`, README y estado documental del proyecto. No cambia código frontend, configuración local existente, dependencias ni lockfiles.

Total: 15 archivos nuevos y cinco modificados. La configuración Blueprint fue revisada contra la documentación oficial; no se utilizó un validador autenticado de Render. El script público se comprobó sintácticamente; no se presenta como ejecutado contra una URL cloud inexistente.

## Cloud

No hay sesión/integración Render o Neon proporcionada explícitamente para crear recursos. No se creó servicio, base cloud ni URL pública. Los pasos exactos y secretos a configurar están en [la guía](DESPLIEGUE_RENDER_NEON.md). La validación local no se presenta como validación de un deploy remoto.
