# Demo en Render + Neon

Una URL HTTPS sirve React, `/api/**` y `/actuator/**` desde Spring Boot. Neon conserva PostgreSQL. La imagen Render ejecuta solo Java, sin Nginx. El desarrollo y Docker local existentes se conservan.

## A. Neon

1. En [Neon Console](https://console.neon.tech), crea una cuenta y un proyecto Free. Revisa los límites antes de confirmar.
2. Selecciona PostgreSQL **17**, probado por el proyecto, y una región cercana al servicio Render. Utiliza una base nueva para la demo.
3. Abre **Connect**, selecciona rama/base y rol. Guarda privadamente host, base, usuario y contraseña.
4. Selecciona conexión **directa**, desactivando Connection pooling. Esta instancia usa Hikari con máximo cinco conexiones y Flyway sobre la misma conexión. No necesitas Neon Auth ni Data API.
5. Selecciona Java si aparece. La URL esperada, sin credenciales dentro, es `jdbc:postgresql://<host-directo>:5432/<base>?sslmode=require`.
6. Si recibes `postgresql://<usuario>:<clave>@<host>/<base>?...`, separa usuario y clave en las variables de la tabla; cambia el esquema a `jdbc:postgresql://` y elimina las credenciales y `@` de la URL. Si recibes JDBC con `user=` y `password=`, retíralos. Conserva `sslmode=require`; puedes conservar `channelBinding=require` si lo suministra la opción Java.
7. El rol debe poder crear el esquema. Flyway aplica V1–V9 automáticamente: no ejecutes SQL manual, no importes el volumen local ni modifiques migraciones.
8. Guarda los datos solo en **Environment** de Render, nunca en Git, capturas, chat ni Docker build arguments.

La [guía Java/JDBC de Neon](https://neon.com/docs/guides/java) describe Connect y TLS. Separamos credenciales de URL para evitar incluirlas en logs.

## B. Render

1. En [Render Dashboard](https://dashboard.render.com), conecta GitHub y autoriza `JonathanNevarez/MDEdu`.
2. Selecciona **New → Blueprint**, ese repositorio, rama `main` y `render.yaml` de la raíz.
3. Confirma un solo Web Service Docker, `mdedu-demo`, plan **Free**. No crees Render Postgres, Static Site, otro frontend ni disco.
4. Completa los cinco valores `sync: false` de la tabla. Elige tu usuario y clave docente: no hay cuenta predeterminada.
5. Confirma el despliegue. Render usa `Dockerfile.render`, contexto `.`. No añadas comandos Build, Start o Docker adicionales.
6. Espera disponibilidad y guarda la URL HTTPS real asignada.
7. Abre `/actuator/health/readiness`: debe devolver `{"status":"UP"}`. Luego `/ingresar` y `/docente/login`.

Alternativa sin Blueprint: **New → Web Service → GitHub → MDEdu**, rama `main`, runtime Docker, Free, Dockerfile `./Dockerfile.render`, contexto `.`, health `/actuator/health/readiness`, mismas variables.

El [esquema Blueprint oficial](https://render.com/docs/blueprint-spec) define Docker, Free, `dockerfilePath`, `healthCheckPath` y secretos `sync: false`.

## Variables reales

| Variable | Secreta | Obligatoria | Ejemplo seguro | Descripción |
| --- | --- | --- | --- | --- |
| `SPRING_PROFILES_ACTIVE` | No | Sí | `prod,render` | Seguridad de producción y SPA |
| `SPRING_DATASOURCE_URL` | Tratar como privada | Sí | `jdbc:postgresql://<host>/<base>?sslmode=require` | JDBC directo, sin clave en URL |
| `SPRING_DATASOURCE_USERNAME` | Sí | Sí | `<rol-neon>` | Rol con permisos Flyway |
| `SPRING_DATASOURCE_PASSWORD` | Sí | Sí | `<secreto>` | Clave Neon |
| `META_UI_USERNAME` | Sí | Sí | `<usuario-docente>` | Usuario de `/docente/login` |
| `META_UI_PASSWORD` | Sí | Sí | `<secreto-personal>` | Mínimo 12 caracteres, máximo 72 bytes UTF-8, diferente del usuario |
| `LLM_PROVIDER` | No | Sí para esta demo | `DISABLED` | Fallback sin API externa |
| `PORT` | No | Automática | Asignado por Render | Escucha en `0.0.0.0`; fallback 8080 |
| `RENDER_EXTERNAL_URL` | No | Automática | URL HTTPS asignada | Origen exacto CORS |
| `CORS_ALLOWED_ORIGINS` | No | No | Origen HTTPS exacto | Para dominio propio; normalmente omitir |
| `JAVA_TOOL_OPTIONS` | No | No | Definido en imagen | Heap 256 MiB y límites nativos |

No uses `DATABASE_URL`, `TEACHER_USERNAME` o `TEACHER_PASSWORD`. Las variables `DB_*` existentes siguen en desarrollo; Render usa `SPRING_DATASOURCE_*` como reemplazo. `.env.production.example` es documental y no se carga automáticamente.

Sin credenciales docentes válidas, Spring no crea el usuario. Corrige ambos secretos y redepliega. No uses la cuenta de pruebas en una demo pública.

## C. Primer estudiante

1. Entra en `/docente/login` usando los valores de `META_UI_USERNAME` y `META_UI_PASSWORD`.
2. En **Participantes**, pulsa **Crear participante**.
3. Guarda el código `EST-XXX` y la clave temporal; compártelos solo con ese estudiante y oculta la clave.
4. Abre `/ingresar` en otro navegador e introduce esos datos.
5. Completa un reto y verifica progreso desde otra sesión. Un reinicio exige login nuevamente, pero conserva PostgreSQL.

## Empaquetado y seguridad

Node 24 ejecuta `npm ci` y Vite. El build Maven/Java 21 incorpora `dist` en recursos `static`. El JAR pasa al runtime Temurin JRE 21, UID/GID 10001. No se versionan `dist` ni `target`; fuentes, cachés y secretos quedan fuera del runtime.

`prod,render` usa cookies HttpOnly, Secure, SameSite Strict y headers forwarded. El shell conserva la CSP de Nginx local: scripts propios, estilos inline necesarios para Blockly, sin eval. Las API mantienen roles, aislamiento, CSRF, límites y BCrypt. Assets Vite: caché immutable; índice: no-store.

El fallback enumera rutas React, incluidos retos. Nunca convierte errores API, actuator o assets ausentes en HTML. Hikari mantiene máximo 5 conexiones, mínimo 0 y renueva conexiones. Readiness comprueba DB y app; liveness solo app. Logs stdout/stderr sin cuerpos HTTP ni contraseñas.

Las navegaciones HTML a rutas desconocidas sin extensión conservan la página accesible de React con HTTP 404. Los errores JSON y espacios reservados API/actuator/assets quedan excluidos. `VITE_API_BASE_URL` se fija vacío durante el build Docker: las llamadas usan `/api` relativo. Los valores localhost del código se conservan exclusivamente como defaults del flujo local; no aparecen en el bundle Render.

## Validación

Desde PowerShell en la raíz: `./scripts/verify-production.ps1`.

Ejecuta MDE, backend, frontend, auditoría, build y Chromium con límite 512 MiB. Crea recursos `mdedu-production-test-*`, base vacía y certificado de prueba. Conserva volúmenes; no modifica datos locales existentes. El proxy HTTPS de prueba vive fuera de la app y no se despliega. `-SkipSuites` reutiliza suites ya ejecutadas; `-SkipBuild` reutiliza la imagen.

Con URL real: `./scripts/check-public-deployment.ps1 -BaseUrl "https://<servicio-real>.onrender.com"`. Solo consulta páginas y health. Comprueba manualmente docente, estudiante, drag/drop Blockly, intento y persistencia con un participante de demo. El smoke local no debe apuntar a producción.

## Cold start y límites

Render Free puede suspenderse por inactividad y mostrar su página de arranque. Espera disponibilidad; ante error de conexión usa el reintento existente. No hay retries infinitos. El progreso vive en Neon, no en disco efímero. Consulta [límites Free](https://render.com/docs/free) y las cuotas Neon de tu dashboard.

La prueba local no garantiza tiempos cloud idénticos: [Render Free](https://render.com/docs/compute-plans) ofrece 512 MB y CPU reducida. Revisa consumo y logs; no cambies automáticamente a un plan de pago.

## Gemini opcional

La demo inicial usa `LLM_PROVIDER=DISABLED`, sin clave. Para activarlo después: `LLM_PROVIDER=GEMINI`, `GEMINI_MODEL` habilitado en tu cuenta y `GEMINI_API_KEY` como secreto Render. Revisa `LLM_RATE_LIMIT_REQUESTS`, `LLM_RATE_LIMIT_WINDOW_SECONDS`, `LLM_RATE_LIMIT_GLOBAL_REQUESTS`, timeout y retries; redepliega. El guard prohíbe `FAKE` en producción. Este trabajo no llama Gemini real.

## Estado cloud

Sin sesiones autorizadas Render/Neon, se entrega el repositorio preparado y evidencia local. El usuario crea recursos y secretos en los dashboards. No hay URL pública confirmada.
