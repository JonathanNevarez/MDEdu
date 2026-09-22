# Archivos candidatos al primer commit

Fecha: 21/09/2026. **98 archivos revisados para el snapshot inicial autorizado.**
Lista exacta obtenida con git ls-files --others --exclude-standard tras preparar
las exclusiones. Identidad local autorizada: JonathanNevarez <neva_rez00@hotmail.com>.
Rama main; sin remotos. Lista preparada antes del staging y commit. Los resultados
posteriores y el hash se registran en .git/FASE_0_SNAPSHOT.txt y en la respuesta
al usuario; ver VERIFICACION_FASE_0.md para el procedimiento de cierre.

Los scripts Maven Wrapper y package-lock.json son productos reproducibles que
sí se proponen versionar. El informe docs/verificacion-estatica.json es evidencia
histórica de la revisión inicial; no sustituye las comprobaciones actuales.
Los artefactos backend/target, frontend/dist y frontend/node_modules se excluyen.
Las cachés externas Maven/npm están fuera del repositorio.

.env.example tiene contraseña vacía; no hay archivos .env locales.
No existe .vscode; futuras configuraciones se revisarán archivo por archivo.
No se excluye mde/generated: la política de generación se decide en Fase1.

## Fuentes y scripts (59)

- `backend/mvnw`
- `backend/mvnw.cmd`
- `backend/src/main/java/com/project/activity/api/package-info.java`
- `backend/src/main/java/com/project/activity/application/package-info.java`
- `backend/src/main/java/com/project/activity/domain/package-info.java`
- `backend/src/main/java/com/project/activity/infrastructure/package-info.java`
- `backend/src/main/java/com/project/adaptation/api/package-info.java`
- `backend/src/main/java/com/project/adaptation/application/package-info.java`
- `backend/src/main/java/com/project/adaptation/domain/package-info.java`
- `backend/src/main/java/com/project/adaptation/infrastructure/package-info.java`
- `backend/src/main/java/com/project/AdaptativaApplication.java`
- `backend/src/main/java/com/project/evaluation/api/package-info.java`
- `backend/src/main/java/com/project/evaluation/application/package-info.java`
- `backend/src/main/java/com/project/evaluation/domain/package-info.java`
- `backend/src/main/java/com/project/evaluation/infrastructure/package-info.java`
- `backend/src/main/java/com/project/execution/api/package-info.java`
- `backend/src/main/java/com/project/execution/application/package-info.java`
- `backend/src/main/java/com/project/execution/domain/package-info.java`
- `backend/src/main/java/com/project/execution/infrastructure/package-info.java`
- `backend/src/main/java/com/project/llm/api/package-info.java`
- `backend/src/main/java/com/project/llm/application/package-info.java`
- `backend/src/main/java/com/project/llm/domain/package-info.java`
- `backend/src/main/java/com/project/llm/infrastructure/package-info.java`
- `backend/src/main/java/com/project/persistence/api/package-info.java`
- `backend/src/main/java/com/project/persistence/application/package-info.java`
- `backend/src/main/java/com/project/persistence/domain/package-info.java`
- `backend/src/main/java/com/project/persistence/infrastructure/package-info.java`
- `backend/src/main/java/com/project/programming/api/package-info.java`
- `backend/src/main/java/com/project/programming/application/package-info.java`
- `backend/src/main/java/com/project/programming/domain/package-info.java`
- `backend/src/main/java/com/project/programming/infrastructure/package-info.java`
- `backend/src/main/java/com/project/shared/api/package-info.java`
- `backend/src/main/java/com/project/shared/application/package-info.java`
- `backend/src/main/java/com/project/shared/domain/package-info.java`
- `backend/src/main/java/com/project/shared/infrastructure/config/CorsConfiguration.java`
- `backend/src/main/java/com/project/shared/infrastructure/config/CorsProperties.java`
- `backend/src/main/java/com/project/student/api/package-info.java`
- `backend/src/main/java/com/project/student/application/package-info.java`
- `backend/src/main/java/com/project/student/domain/package-info.java`
- `backend/src/main/java/com/project/student/infrastructure/package-info.java`
- `backend/src/main/java/com/project/telemetry/api/package-info.java`
- `backend/src/main/java/com/project/telemetry/application/package-info.java`
- `backend/src/main/java/com/project/telemetry/domain/package-info.java`
- `backend/src/main/java/com/project/telemetry/infrastructure/package-info.java`
- `backend/src/main/resources/application.properties`
- `backend/src/main/resources/db/migration/V1__bootstrap.sql`
- `backend/src/test/java/com/project/BackendBootstrapIT.java`
- `backend/src/test/java/com/project/shared/infrastructure/config/CorsConfigurationTest.java`
- `frontend/index.html`
- `frontend/src/app/App.tsx`
- `frontend/src/main.tsx`
- `frontend/src/pages/HomePage.tsx`
- `frontend/src/pages/NotFoundPage.tsx`
- `frontend/src/styles.css`
- `frontend/tests/e2e/startup.spec.ts`
- `frontend/tests/unit/App.test.tsx`
- `frontend/tests/unit/setup.ts`
- `scripts/check-environment.ps1`
- `scripts/check-scaffold.py`

## Configuración reproducible (15)

- `.editorconfig`
- `.env.example`
- `.gitattributes`
- `.gitignore`
- `backend/.mvn/wrapper/maven-wrapper.properties`
- `backend/pom.xml`
- `docker-compose.yml`
- `frontend/.npmrc`
- `frontend/package.json`
- `frontend/package-lock.json`
- `frontend/playwright.config.ts`
- `frontend/tsconfig.json`
- `frontend/tsconfig.node.json`
- `frontend/vite.config.ts`
- `frontend/vitest.config.ts`

## Documentación y referencia (24)

- `backend/README.md`
- `docker/README.md`
- `docs/API.md`
- `docs/ARQUITECTURA.md`
- `docs/DECISIONES_ARQUITECTURA.md`
- `docs/ESTADO_PROYECTO.md`
- `docs/HERRAMIENTAS.md`
- `docs/INVENTARIO_ARCHIVOS.md`
- `docs/MODELOS_MDE.md`
- `docs/PLAN_IMPLEMENTACION.md`
- `docs/PRIMER_COMMIT.md`
- `docs/referencias/marco_conceptual_iu_adaptativa_programacion.svg`
- `docs/REGLAS_ADAPTACION.md`
- `docs/VERIFICACION_FASE_0.md`
- `docs/verificacion-estatica.json`
- `frontend/README.md`
- `mde/acceleo/README.md`
- `mde/atl/README.md`
- `mde/generated/README.md`
- `mde/metamodels/README.md`
- `mde/models/README.md`
- `mde/README.md`
- `mde/xtext/README.md`
- `README.md`
