# Herramienta educativa adaptativa: MDE + LLM

Proyecto académico para enseñar lógica de programación a estudiantes universitarios
de nivelación mediante bloques, modelos formales y adaptación explicable.

**Estado: Fase 0 en curso. JDK21, Maven/Wrapper, Node/npm y Git verificados.** El backend
compila y sus 3 pruebas MVC pasan. El frontend compila, sus 2 pruebas unitarias
pasan y Vite respondió HTTP200; el servidor temporal quedó detenido. Siguen
pendientes E2E, arranque completo del backend, 4 pruebas de integración y
PostgreSQL. No se avanza a F1.

Repositorio Git local en main, con identidad local autorizada y sin remotos.
El snapshot inicial incluye [los 98 archivos revisados](docs/PRIMER_COMMIT.md).
La documentación se prepara antes del commit; el cierre se comprueba mediante
el historial Git y el informe local .git/FASE_0_SNAPSHOT.txt.

## Alcance actual

- `frontend/`: React, TypeScript, Vite y React Router; inicio en español y pruebas
  Vitest/Testing Library/Playwright preparadas.
- `backend/`: Java 21, Spring Boot, Maven, PostgreSQL, Flyway, JPA, configuración
  CORS y health check; pruebas JUnit 5/Testcontainers preparadas.
- `mde/`: carpetas reservadas y documentadas para EMF/Ecore/XMI, ATL, Acceleo y Xtext.
- `docker-compose.yml`: PostgreSQL local con persistencia y comprobación de salud.
- `docs/`: arquitectura, contratos, plan por fases, decisiones y evidencia.

Todavía no hay editor Blockly, metamodelos ejecutables, actividades, adaptación,
LLM ni flujo educativo. El código inicial corresponde únicamente a preparación.

## Preparar herramientas

Seguir [HERRAMIENTAS](docs/HERRAMIENTAS.md): **JDK 21, Maven Wrapper 3.9.16, Node 24 LTS
(>=24.15.0, <25) con npm, Docker Desktop con contenedores Linux y Compose v2**.
Git es recomendado. La matriz Eclipse/MDE se valida desde F1.

Abrir PowerShell en esta carpeta después de instalar:

```powershell
java -version
javac -version
.\backend\mvnw.cmd -version
node --version
npm.cmd --version
docker version
docker compose version
```

Las instrucciones siguientes incluyen pasos ya verificados y pasos pendientes;
consultar el registro de verificación para distinguirlos. Solo ejecutar instalaciones
adicionales con autorización. Las primeras descargas de dependencias requieren red.

## PostgreSQL y variables locales

Desde la raíz, crear `.env` solo si no existe y editar `DB_PASSWORD` con una
contraseña local propia, no vacía. El archivo está ignorado por Git. Usar formato
`CLAVE=valor` sin comillas ni interpolaciones; una contraseña alfanumérica aleatoria
permite compartirlo entre Compose y la configuración Java.

```powershell
if (-not (Test-Path -LiteralPath .env)) {
  Copy-Item -LiteralPath .env.example -Destination .env
}
```

Una vez editado `.env`:

```powershell
docker compose config --quiet
docker compose up -d --wait postgres
docker compose ps
docker compose exec -T postgres psql -U adaptativa -d adaptativa -c "SELECT 1;"
```

La consulta usa los nombres predeterminados; ajustarlos si cambias DB_USER/DB_NAME.
El puerto publicado es `127.0.0.1:5432`, configurable por DB_PORT. No imprimir
configuración resuelta con credenciales en informes. Ver [docker/README](docker/README.md)
para persistencia, contraseña del volumen y pendiente de fijar digest.

## Backend

Compilación y pruebas sin Docker, desde la raíz (ya verificadas):

```powershell
Set-Location backend
.\mvnw.cmd clean compile
.\mvnw.cmd test
```

El Wrapper fija Maven3.9.16 y su hash, descargándolo a la caché del usuario;
las tareas normales no requieren Maven global. JDK21 debe estar disponible.

Cuando Docker/PostgreSQL estén preparados y autorizados, desde backend:

```powershell
.\mvnw.cmd verify
.\mvnw.cmd spring-boot:run
```

`mvnw.cmd verify` compila, ejecuta pruebas MVC y pruebas de integración con PostgreSQL
aislado mediante Testcontainers. Docker debe estar activo. Las pruebas no usan
credenciales de `.env`; el arranque local sí importa `../.env`.

Desde otra terminal:

```powershell
Invoke-RestMethod http://localhost:8080/actuator/health
```

Resultado esperado: `status: UP`, con conexión a BD comprobada y sin detalles
internos. El backend puede fallar al iniciar si Flyway no conecta con PostgreSQL.
Ver [backend/README](backend/README.md) para variables y límites.

## Frontend

En otra terminal desde la raíz:

```powershell
Set-Location frontend
npm.cmd ci
npm.cmd run build
npm.cmd test
npm.cmd run dev
```

Abrir `http://127.0.0.1:5173`. La página de preparación funciona sin backend.
Conservar y versionar `package-lock.json`, generado por la primera instalación
validada. En PowerShell, `npm.cmd` funciona sin cambiar la política de scripts.
El smoke E2E queda pendiente de autorización: requiere descargar Chromium y
auxiliares (~327 MB). Después de autorizarlo, ejecutar desde frontend:

```powershell
npm.cmd run test:e2e:install
npm.cmd run test:e2e
```

El smoke E2E compila y levanta su propio preview en 4173.

Para finalizar los servidores locales, usar Ctrl+C en sus terminales. Detener
PostgreSQL con `docker compose stop` desde la raíz conserva sus datos.

## Cierre de Fase 0

Faltan E2E del frontend, pruebas backend de integración,
arranque del backend, consulta PostgreSQL y health. Registrar resultados en
[VERIFICACION_FASE_0](docs/VERIFICACION_FASE_0.md) y actualizar
[ESTADO_PROYECTO](docs/ESTADO_PROYECTO.md). Solo entonces cerrar F0 y comenzar F1.

Java, TypeScript, pruebas MVC/unitarias y HTTP frontend se validaron con ejecución
real. Compose, integración con DB, navegador real y compatibilidad MDE siguen
pendientes. La base usa Spring Boot 3.5.16 para conservar la línea de
JUnit 5; su soporte debe revisarse antes de desplegar, según el
[registro de decisiones](docs/DECISIONES_ARQUITECTURA.md).

## Documentación

- [Plan y riesgos](docs/PLAN_IMPLEMENTACION.md)
- [Arquitectura y diagramas](docs/ARQUITECTURA.md)
- [Metamodelos y transformaciones previstos](docs/MODELOS_MDE.md)
- [Reglas de adaptación](docs/REGLAS_ADAPTACION.md)
- [API configurada y contratos futuros](docs/API.md)
- [Decisiones de arquitectura](docs/DECISIONES_ARQUITECTURA.md)
- [Estado y verificaciones pendientes](docs/ESTADO_PROYECTO.md)
- [Inventario de entrega](docs/INVENTARIO_ARCHIVOS.md)

MDE representa y transforma; el motor determinista ejecuta y evalúa; el modelo
del estudiante mantiene estado; las reglas deciden; el manager coordina;
el LLM explica y complementa; la IU presenta; la telemetría aporta evidencia.
