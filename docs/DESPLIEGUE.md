# Desarrollo y despliegue

Requisitos de desarrollo: Windows PowerShell, JDK 21, Node 24 y Docker Desktop con Linux containers/WSL2. Maven Wrapper está versionado. No instalar PostgreSQL local. Ejecutar comandos desde la raíz del repositorio.

1. Copiar `.env.example` a `.env`; configurar `DB_PASSWORD`, `META_UI_USERNAME` y una contraseña docente propia de al menos 12 caracteres. Usar valores compatibles con Java properties/Compose, sin publicar el archivo.
2. Compilar MDE: `backend\mvnw.cmd -f mde/pom.xml clean install`.
3. Compilar backend: `backend\mvnw.cmd -f backend/pom.xml verify` (Docker debe estar activo).
4. Frontend: `cd frontend`, `npm.cmd ci`, regresar a la raíz.
5. `powershell -NoProfile -ExecutionPolicy Bypass -File scripts/start-dev.ps1`.

El script inicia el PostgreSQL existente, backend :8080 y Vite :5173, con LLM desactivado. Rechaza puertos ocupados; guarda exclusivamente PID, nombre y hora de inicio en `.quality/processes.json`. Los logs quedan en `.quality/`, fuera de Git. `scripts/stop-dev.ps1` verifica identidad de los procesos antes de detenerlos y conserva el volumen. No cambia variables globales de Windows. Las opciones `-TestCredentials` y `-GeminiMock` son exclusivamente para validación local y cargan fixtures de test; el launcher mock no se empaqueta en producción.

## Docker completo

`docker-compose.yml` conserva el PostgreSQL de desarrollo. `docker-compose.app.yml` define otro proyecto y volumen independiente: PostgreSQL → backend saludable → Nginx saludable. No publica el puerto de la base de datos ni del backend; frontend solo se enlaza a loopback :8088. Backend y frontend se construyen por etapas y se ejecutan sin root.

En `.env` definir `LLM_PROVIDER=DISABLED`, `CORS_ALLOWED_ORIGINS=http://127.0.0.1:8088`; para esta prueba HTTP local, `SESSION_COOKIE_SECURE=false`. En HTTPS productivo conservar `true` y configurar el origen real. Ejecutar:

```powershell
docker compose -f docker-compose.app.yml build backend
docker compose -f docker-compose.app.yml build frontend
docker compose -f docker-compose.app.yml up -d --wait
docker compose -f docker-compose.app.yml ps
docker compose -f docker-compose.app.yml down
```

`down` conserva los datos; no añadir `-v` sin decidir explícitamente borrar el volumen. Antes de cambios de entorno, hacer respaldo con `pg_dump` y comprobar la restauración en una base separada. El repositorio no contiene datos personales ni backups.

En equipos de 16 GB se recomienda `scripts/docker-smoke.ps1`: usa constructor `mdedu-quality` limitado a 1536 MiB y dos CPU; compila secuencialmente y lo detiene antes de arrancar servicios. Ejecuta un proyecto aislado con base nueva, credenciales ficticias de test, sin claves LLM; preserva su volumen al terminar. No altera el volumen de desarrollo. [Opciones del constructor Docker](https://docs.docker.com/build/builders/drivers/docker-container/).

Nginx resuelve rutas SPA, proxy `/api` y health sin exponer Actuator completo. `/health` comprueba servidor estático; `/actuator/health/liveness` comprueba aplicación y `/actuator/health/readiness` incluye DB. Health público no muestra detalles. Si DB cae, readiness deja de estar UP. Revisar `docker compose ... logs` sin copiar secretos a incidencias.

Gemini opcional: `LLM_PROVIDER=GEMINI`, `GEMINI_API_KEY`, `GEMINI_MODEL`. OpenAI: `LLM_PROVIDER=OPENAI`, `LLM_API_KEY`, `LLM_MODEL`. Las claves se inyectan solo al backend. Ninguna prueba automatizada exige proveedor real. Timeout, retries y cuotas se configuran según `.env.example`; `FAKE` se reserva para pruebas y se rechaza en perfil prod.

`scripts/docker-smoke.ps1 -Browser` añade Chromium contra Nginx/CSP; requiere las dependencias frontend y Chromium instalados.

La verificación completa se ejecuta con `scripts/verify-all.ps1`. Requiere Docker activo, `.env` configurado, Chromium Playwright instalado (`cd frontend; npx.cmd playwright install chromium`) y red para resolver dependencias. `-SkipDockerSmoke` omite únicamente la reconstrucción Docker; no constituye por sí solo el cierre completo. Las etapas fallan con código distinto de cero y las pruebas pesadas son secuenciales.
