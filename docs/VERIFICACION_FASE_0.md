# Verificación de Fase 0

Registro inicial: 20/09/2026. Actualizado: 23/09/2026. Distingue inspección, revisión estática y pruebas
de aplicación. No se presenta una comprobación estática como compilación o arranque.

## Eclipse Modeling Tools BASE: VALIDADO — 23/09/2026

### Alcance y estado inicial

Validación base aceptada por el usuario. Este checkpoint únicamente registra esa
evidencia y autoriza su commit/push; no repite instalaciones ni abre Eclipse.
Antes de instalar y antes del cierre documental: main/d36c77b, working tree clean,
main sincronizada con origin/main, remoto `https://github.com/JonathanNevarez/MDEdu.git`.
Java y javac: Temurin 21.0.12.1. Las comprobaciones mínimas no detectaron Eclipse previo.

### Artefacto oficial, checksum y extracción

- Archivo: `eclipse-modeling-2026-09-R-win32-x86_64.zip`, release estable 2026-09 R.
- [URL oficial del ZIP](https://download.eclipse.org/technology/epp/downloads/release/2026-09/R/eclipse-modeling-2026-09-R-win32-x86_64.zip).
- [SHA-512 oficial](https://download.eclipse.org/technology/epp/downloads/release/2026-09/R/eclipse-modeling-2026-09-R-win32-x86_64.zip.sha512).
- Tamaño anunciado por HTTP Content-Length y tamaño real: **694931027 bytes**.
- Eclipse publicó SHA-512, SHA-1 y MD5. El recurso .sha256 devolvió HTTP 404;
  no se inventó ese checksum. Se utilizó SHA-512.
- Descarga temporal fuera del repositorio:
  `C:\Users\alexxxjon\AppData\Local\Temp\mde-eclipse-2026-09\eclipse-modeling-2026-09-R-win32-x86_64.zip`.

SHA-512 oficial:

```text
0fce50604679aa668f66a28c1be0cfa56263d83157389ad5fac39c905470dcb0f277d160afa8ab3217563c98aa87d726a7d328bbd864a313ddfee0f13e6b8cf9
```

SHA-512 calculado mediante Get-FileHash -Algorithm SHA512, normalizado a minúsculas:

```text
0fce50604679aa668f66a28c1be0cfa56263d83157389ad5fac39c905470dcb0f277d160afa8ab3217563c98aa87d726a7d328bbd864a313ddfee0f13e6b8cf9
SHA512_MATCH=True
```

Coincidencia exacta comprobada antes de extraer. Se verificaron 4465 entradas del
ZIP y sus rutas de destino; 803806364 bytes sin comprimir. Extracción sin instalador
ni UAC en `C:\Users\alexxxjon\AppData\Local\Programs\Eclipse\eclipse-modeling-2026-09-R`.
Ejecutable: `C:\Users\alexxxjon\AppData\Local\Programs\Eclipse\eclipse-modeling-2026-09-R\eclipse.exe`.
Existencia confirmada de eclipse.exe, eclipse.ini, configuration/, plugins/ y features/.
Windows no expuso FileVersion/ProductVersion del ejecutable.

### Arranque, producto y runtime real

Workspace exclusivo: `C:\Users\alexxxjon\AppData\Local\EclipseWorkspaces\MDEdu-fase0`.
Se inició eclipse.exe con `-data` apuntando a esa ruta, fuera del repositorio.
Help > About Eclipse IDE confirmó:

```text
Eclipse Modeling Tools (includes Incubating components)
Version: 2026-09 (4.41.0)
Build id: 20260903-0720
```

Platform instalado: **4.41.0.v20260828-1142**. Package Modeling:
**4.41.0.20260903-0719**. El build mostrado por About y el identificador de package
se registran por separado. Log real del workspace:

```text
eclipse.buildId=4.41.0.20260903-0719
java.version=25.0.4.1
java.vendor=Eclipse Adoptium
BootLoader constants: OS=win32, ARCH=x86_64, WS=win32, NL=es_EC
Framework arguments:  -product org.eclipse.epp.package.modeling.product
```

Java embebido: **Temurin 25.0.4.1+1-LTS**. JustJ feature
**25.0.4.v20260826-1347**; runtime **25.0.4.v20260826-0822**.
El proceso Eclipse cargó jvm.dll desde
`plugins/org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_25.0.4.v20260826-0822/jre/bin/server/jvm.dll`
dentro de la instalación. Se confirmó el uso del runtime embebido, no del JDK21 global.
eclipse.ini quedó idéntico al ZIP; no se añadieron/retiraron unidades instaladas
al comparar el perfil p2 con el original del paquete.

### Componentes base presentes y pendientes

| Componente inspeccionado | Versión / resultado |
| --- | --- |
| EMF SDK | 2.47.0.v20260704-1256, presente |
| Ecore bundle | 2.43.0.v20260704-1256, presente |
| Ecore Editor bundle | 2.20.0.v20260704-1256, presente |
| Ecore feature / Editor feature | 2.44.0.v20260704-1256 / 2.21.0.v20260704-1256; versiones distintas de los bundles |
| Ecore Tools | 3.6.0.202604070657, presente |
| OCL All SDK | 5.24.0.v20260601-1413, incluido pero NO utilizado por el proyecto |
| Xtext runtime/UI/Xbase | 2.44.0.v20260824-1228, componentes parciales presentes |
| org.eclipse.xtext.sdk.feature.group | AUSENTE; no equivale a los componentes indirectos anteriores |
| ATL | AUSENTE |
| Acceleo 4 | AUSENTE |

No se instalaron plugins adicionales ni se configuró Tycho. No se creó/importó
ningún proyecto de trabajo, no se importó MDEdu ni se crearon metamodelos,
expresiones OCL o dependencias OCL del proyecto.

### Java global intacto, cierre e incidencias

PowerShell posterior, con los valores persistentes cargados únicamente en el proceso:

```text
openjdk version "21.0.12.1" 2026-08-18 LTS
OpenJDK Runtime Environment Temurin-21.0.12.1+1 (build 21.0.12.1+1-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.12.1+1 (build 21.0.12.1+1-LTS, mixed mode, sharing)
javac 21.0.12.1
JAVA_HOME: C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\
where.exe java: C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin\java.exe
where.exe javac: C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin\javac.exe
```

PATH y JAVA_HOME persistentes, de usuario y máquina, permanecieron sin cambios;
Java 25/Eclipse no se añadieron al PATH. Sin UAC ni aceptación legal interactiva obligatoria.

EGit informó **HOME no definido**: advertencia menor, no error del proyecto.
No se creó ni modificó HOME ni configuración Git. EGit generó automáticamente
metadata interna `.org.eclipse.egit.core.cmp` bajo .metadata del workspace externo,
sin cambios en el repositorio. No es un proyecto de trabajo creado por el usuario/asistente.
El diálogo de Defender desapareció antes de automatizarlo; no se añadieron
exclusiones de Defender para Eclipse. No se cambió Defender.

Cierre normal solicitado al IDE; Eclipse PID 23712 y sus siete procesos WebView
asociados terminaron. Comprobaciones finales reales:

```text
RemainingRelatedProcesses=0
ProcessesFromInstall=0
TemporaryZipDeleted=True
InstallationRetained=True
WorkspaceRetained=True
```

Logs conservados fuera del repositorio. git status --short quedó vacío tras la
validación; git status --ignored --short mostró únicamente los ignorados previos:
.env, backend/target/, frontend/dist/, frontend/node_modules/ y frontend/test-results/.
Este cierre modifica exclusivamente ESTADO_PROYECTO.md, HERRAMIENTAS.md y este
documento; commit autorizado `chore: validate eclipse modeling tools environment`
y push a main. **Eclipse base VALIDADO; Fase 1 no iniciada.**
Detenerse después de publicar y verificar; Xtext SDK requiere nueva autorización.

## Histórico: runtime manual Spring Boot contra PostgreSQL Compose: VALIDADO — 22/09/2026

### Condiciones y arranque real

Inicio: main/ab95344 limpio y sincronizado con origin/main. Docker Desktop 4.91.0,
Engine 29.8.0 operativo, contexto desktop-linux. Compose postgres Exited (0).
Puertos 5432 y 8080 libres. Se leyeron Compose, application.properties y V1,
sin modificarlos; DB/user adaptativa, DB_PORT=5432, backend 127.0.0.1:8080.
.env existe, ignorado por .gitignore:12; git ls-files -- .env vacío.
DB_PASSWORD presente, sin imprimirlo; no había variables de proceso que
sobrescribieran las variables DB o dirección/puerto del backend inspeccionadas.

Desde la raíz: `docker compose up -d --wait postgres`, código 0. Contenedor
existente educativa-adaptativa-postgres-1 running/healthy, puerto
127.0.0.1:5432->5432/tcp. No se recreó el volumen.
Consulta inicial information_schema.tables WHERE table_schema='public': **0 rows**.

Desde backend/ se mantuvo activo:

```powershell
.\mvnw.cmd --batch-mode --no-transfer-progress spring-boot:run
```

Stdout/stderr capturados en target/runtime-manual-validation.log, ignorado.
RunningAsAdministrator=False; sin contraseña en línea de comandos.
PIDs observados: PowerShell 22820 -> cmd 20304 -> Maven/java 24236 -> Spring/java
18900. Spring Boot 3.5.16, Java 21.0.12.1. El goal llegó a test-compile, con clases
actualizadas, pero no ejecutó Surefire/Failsafe ni Maven verify.

Evidencia real del log:

```text
HikariPool-1 - Added connection org.postgresql.jdbc.PgConnection@4c176ff1
HikariPool-1 - Start completed.
Database: jdbc:postgresql://localhost:5432/adaptativa (PostgreSQL 17.11)
Migrating schema "public" to version "1 - bootstrap"
Successfully applied 1 migration to schema "public", now at version v1 (execution time 00:00.012s)
Tomcat started on port 8080 (http) with context path '/'
Started AdaptativaApplication in 6.239 seconds (process running for 6.64)
```

Started registrado a las 20:03:11.095 -05:00. Get-NetTCPConnection confirmó
127.0.0.1:8080 LISTEN, OwningProcess 18900. Spring importó ../.env mediante
la configuración existente, y la conexión Hikari/PostgreSQL fue real.

### HTTP real y SQL posterior

`Invoke-WebRequest -UseBasicParsing http://localhost:8080/actuator/health`:

```text
HTTP 200
Content-Type: application/vnd.spring-boot.actuator.v3+json
Body: {"status":"UP"}
```

Petición a health con Origin: http://localhost:5173:
HTTP 200, Access-Control-Allow-Origin: http://localhost:5173.
Petición a /actuator/env: HTTP 404, Content-Type application/json, cuerpo:

```json
{"timestamp":"2026-09-23T01:04:43.454+00:00","status":404,"error":"Not Found","path":"/actuator/env"}
```

Sin trace, exception, message ni datos sensibles. Health oculta detalles DB por
configuración; la conexión se acredita además con Hikari/Flyway y SQL.

Consultas READ-ONLY mediante `docker compose exec -T postgres psql -U adaptativa
-d adaptativa -v ON_ERROR_STOP=1` y opciones -c; sin contraseña visible:

```text
SELECT 1;                  -> 1 (1 row)
SELECT current_database(); -> adaptativa
SELECT current_user;       -> adaptativa
Tablas public: public.flyway_schema_history (1 row)
```

`SELECT installed_rank, version, description, success FROM flyway_schema_history
WHERE version='1';` devolvió:

| installed_rank | version | description | success |
| --- | --- | --- | --- |
| 1 | 1 | bootstrap | t |

No hay tablas educativas. No se modificó el esquema manualmente ni se invocó
Flyway por separado; la aplicación aplicó V1 automáticamente al arrancar.

### Apagado y estado final

El runtime fue validado correctamente. Se envió Ctrl+C solo a la terminal de este
arranque; el código 1 de la sesión corresponde únicamente a esa interrupción manual.
No se afirma salida Maven 0 ni cierre graceful confirmado por log.
Se verificó ausencia de los PID 22820, 20304, 24236 y 18900 y puerto 8080 libre.
No se usó kill general ni se afectaron otros procesos Java.
Después: `docker compose stop postgres`, código 0; Compose ps -a Exited (0).
docker ps vacío; docker version código 0, Engine 29.8.0 operativo.
Volumen educativa-adaptativa_postgres_data conservado, misma creación
2026-09-22T19:52:58Z y montaje /var/lib/docker/volumes/educativa-adaptativa_postgres_data/_data.
La fila Flyway quedó en ese volumen; no se eliminó ni recreó, ni se reinició
PostgreSQL para una comprobación adicional de persistencia.

Antes de documentar, git status --short vacío. .env y backend/target/ ignorados.
No hubo cambios de fuentes, POM, pruebas, Compose, application.properties ni V1.
Solo se actualizan los tres documentos autorizados; sin staging/commit/push.

Incidencia de comprobación: PowerShell 5 devolvió el Content de health como byte[]
por su tipo MIME; la primera aserción del verificador falló al tratarlo como texto.
La respuesta HTTP ya era 200 con bytes de {"status":"UP"}. Se corrigió únicamente
la decodificación en el comando de inspección y se repitió HTTP satisfactoriamente.
No fue un error del backend ni motivó cambios de código o un nuevo arranque.
No se observaron warnings/errores de datasource o Flyway; el log incluye DEBUG
y el 404 esperado de /actuator/env. No se repitió verify ni se instaló Eclipse/MDE.
Runtime validado; servicios detenidos. Fase 1 no iniciada; esperar autorización.

## Histórico: Maven verify + Testcontainers: VALIDADO — 22/09/2026

### Ejecución y reportes reales

Inicio: main/f2cb080 sincronizada con origin/main, working tree clean. Docker
version/info correctos: Desktop 4.91.0, Engine 29.8.0, OSType linux,
desktop-linux. Compose postgres Exited (0). Imágenes previas: hello-world:latest
y postgres:17-bookworm; Ryuk ausente.

POM y pruebas inspeccionados sin cambios. Failsafe ligado a integration-test y
verify; BackendBootstrapIT coincide con el patrón predeterminado **/*IT.java.
Cuatro @Test con los nombres esperados, @Testcontainers, @SpringBootTest con
RANDOM_PORT y DynamicPropertySource para URL/usuario/contraseña del contenedor.
spring.config.import vacío en pruebas: no dependen del .env ni de Compose.
Flyway habilitado; V1 contiene SELECT 1, sin tablas educativas.

Comando exacto desde backend/:

```powershell
.\mvnw.cmd --batch-mode --no-transfer-progress verify
```

Wrapper 3.3.4 (only-script), Maven 3.9.16, Java Temurin 21.0.12.1+1-LTS x64.
Maven instalado por Wrapper en el perfil .m2; JAVA_HOME usa el JDK Temurin.
Una sola ejecución, sin skipTests/skipITs, sin cambios ni reintentos.

```text
compiler:3.14.1:compile: Nothing to compile - all classes are up to date.
compiler:3.14.1:testCompile: Nothing to compile - all classes are up to date.
Surefire 3.5.6: Tests run: 3, Failures: 0, Errors: 0, Skipped: 0
Failsafe 3.5.6: Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
failsafe:3.5.6:verify ejecutado
BUILD SUCCESS
Total time: 33.580 s
Finished at: 2026-09-22T19:46:33-05:00
MavenVerifyExitCode=0
```

Ninguna fuente recompilada en este verify incremental; no se atribuyen a esta
ejecución las 42 fuentes compiladas en la validación anterior. JAR empaquetado
y reempaquetado por Spring Boot 3.5.16.

Reportes inspeccionados, no editados, en target/surefire-reports y
target/failsafe-reports; log local target/verify-validation.log. Todos ignorados.

| Clase XML | Tests | Failures | Errors | Skipped | Tiempo |
| --- | --- | --- | --- | --- | --- |
| com.project.shared.infrastructure.config.CorsConfigurationTest | 3 | 0 | 0 | 0 | 3.510 s |
| com.project.BackendBootstrapIT | 4 | 0 | 0 | 0 | 17.093 s |

failsafe-summary.xml: completed=4, errors=0, failures=0, skipped=0, flakes=0,
timeout=false. Los cuatro testcase no contienen failure/error/skipped:

| BackendBootstrapIT | Resultado | Tiempo | Evidencia que demuestra |
| --- | --- | --- | --- |
| healthIncludesDatabaseCheckWithoutDisclosingDetails | PASS | 0.908 s | HTTP 200; status UP; cuerpo con un único campo; contribuidor db registrado |
| bootstrapMigrationRunsAgainstPostgresqlAndCreatesNoEducationalTables | PASS | 0.106 s | V1 actual, ninguna migración pendiente, success=true; public contiene exactamente flyway_schema_history |
| diagnosticEndpointsAreNotExposed | PASS | 0.036 s | /actuator/env devuelve 404; sin trace, exception ni message; no implica probar todos los endpoints posibles |
| frontendCanReadHealthThroughActuatorCors | PASS | 0.020 s | GET health HTTP 200 con Access-Control-Allow-Origin http://localhost:5173 |

### Contenedores y Flyway

Testcontainers **1.21.4** detectó npipe:////./pipe/docker_engine, Docker 29.8.0,
API 1.56. No se cambió contexto ni configuración Docker.
PostgreSQL temporal ID:
`5f0eea8223c2d5d1a56bd691810682c9519cc07a8d7583bdb9179c08aabd5245`.
JDBC observado: `jdbc:postgresql://localhost:65390/test?loggerLevel=OFF`.
Imagen existente postgres:17-bookworm, PostgreSQL 17.11; RepoDigest:
`postgres@sha256:639ab7ceb90e13123085b741fb31ef493fba25463002f6da665352e7b534b652`.
Servidor Spring de prueba: puerto HTTP aleatorio 65407.

Única imagen nueva: testcontainers/ryuk:0.12.0. RepoDigest:
`testcontainers/ryuk@sha256:dd3f023a6ed7015b3f95a49ccd65a2daf0c56e681422c12952b19a810dfa6298`.
Ryuk ID `54c6479c970ac1d943a5f57276fb71fe8bc5d9b9bf1f420785f341e2598eb7c3`;
log confirma inicio y limpieza al finalizar la JVM.

Flyway conectó al puerto 65390, validó una migración, creó public.flyway_schema_history
y registró: `Successfully applied 1 migration to schema "public", now at version v1`.
Ejecución de migración: 00:00.014s. La prueba confirma bootstrap mínimo exitoso,
sin tablas educativas; no se ejecutó Flyway manualmente.

### Limpieza, límites e incidencias

Después: docker ps vacío; docker ps -a solo muestra el contenedor Compose
750fdaa23870, educativa-adaptativa-postgres-1, Exited (0). Ningún contenedor
temporal restante. Compose conserva FinishedAt=2026-09-22T19:53:47.439762297Z,
igual que antes de verify; nunca se inició en este paso.
Volumen educativa-adaptativa_postgres_data presente, CreatedAt=2026-09-22T19:52:58Z;
red educativa-adaptativa_default conservada. La prueba usa contenedor independiente
sin montar el volumen Compose. No se borraron imágenes ni recursos persistentes.

Git tras verify y antes de documentación: status --short vacío;
status --ignored muestra .env y backend/target/ ignorados. Solo se actualizan
los tres documentos autorizados; sin cambios de código, POM, Compose, Flyway,
tests ni configuración. Sin git add/commit/push; Fase 1 no iniciada.

Warnings: Mockito se autoanexa; Byte Buddy 1.17.8 carga agente dinámico y Java
advierte sobre CDS. PowerShell presentó stderr como NativeCommandError, pero
Maven terminó con código 0; reportes sin errores. No se ocultaron avisos ni se
cambió configuración. Ryuk descargó públicamente sin credenciales Docker Hub.
rg no estaba en el PATH refrescado: inspección resuelta con Get-ChildItem.
Arranque manual Spring Boot y PostgreSQL Compose pendiente de otra autorización.

## Histórico: PostgreSQL Compose: VALIDADO y detenido — 22/09/2026

### Condiciones iniciales y configuración inspeccionada

`git status`: main sincronizada con origin/main, working tree clean, HEAD dc7ad0a.
`git log -5 --oneline` mostró los cuatro commits existentes: dc7ad0a, e1df49e,
41315c5 y 8262ec1. Docker version/info operativos, Desktop 4.91.0 (239619),
CLI/Engine 29.8.0, API 1.56, Compose 5.5.1, OSType linux, contexto desktop-linux.
PendingFileRenameOperations=False, CBS/RebootPending=False,
Windows Update/RebootRequired=False; no se requirió reinicio.

Se inspeccionaron docker-compose.yml, .env.example, .gitignore,
backend/src/main/resources/application.properties, docker/README.md y la migración
V1__bootstrap.sql. No se modificó ninguno. Configuración real:

| Campo | Valor |
| --- | --- |
| Proyecto / servicio | educativa-adaptativa / postgres |
| Imagen declarada | postgres:17-bookworm |
| Puerto host / contenedor | 127.0.0.1:5432 / 5432/tcp |
| POSTGRES_DB / POSTGRES_USER | adaptativa / adaptativa |
| Password | POSTGRES_PASSWORD desde DB_PASSWORD local, no revelado |
| Volumen lógico / destino | postgres_data / /var/lib/postgresql/data |
| Healthcheck | pg_isready -U "$POSTGRES_USER" -d "$POSTGRES_DB" |
| Interval / timeout / retries / start_period | 5s / 3s / 12 / 10s |
| Init scripts del proyecto montados | Ninguno; docker/ solo contiene README.md |
| Datasource Spring efectivo con estos valores | jdbc:postgresql://localhost:5432/adaptativa |
| Usuario / contraseña Spring | adaptativa / DB_PASSWORD externo |

Spring importa ../.env desde backend/; no se inició Spring. La migración preparada
contiene SELECT 1 y no se ejecutó en este paso. No hubo discrepancias Compose/Spring.
No existían variables DB_NAME/DB_USER/DB_PASSWORD/DB_PORT en el proceso que
sobrescribieran .env. `.env` no existía: `git check-ignore -v .env` devolvió
`.gitignore:12:.env .env`. Se creó con las cuatro variables del ejemplo y una
contraseña criptográficamente aleatoria de 64 caracteres hexadecimales.
El valor se escribió solo en .env, sin imprimirlo ni copiarlo a documentación.

`docker compose config --quiet`: **código 0**; no se imprimió configuración
interpolada. Antes de descargar: docker ps -a vacío, única imagen hello-world,
ningún volumen, redes predeterminadas bridge/host/none y puerto 5432 libre.

### Descarga, recursos y health real

`docker compose pull postgres`: código 0. Sin descargar otros servicios.

```text
Repository/tag: postgres:17-bookworm
Image ID: sha256:639ab7ceb90e13123085b741fb31ef493fba25463002f6da665352e7b534b652
RepoDigest: postgres@sha256:639ab7ceb90e13123085b741fb31ef493fba25463002f6da665352e7b534b652
Architecture: amd64
OS: linux
```

El tag se mantuvo intacto; digest observado registrado, sin editar Compose.
`docker compose up -d --wait postgres`: **código 0**. Único servicio iniciado.

```text
Container: educativa-adaptativa-postgres-1
Image: postgres:17-bookworm
Service: postgres
Status: running
Health.Status: healthy
Health.FailingStreak: 0
Health.Log: 1 comprobación registrada en la inspección inicial
Health.Log[0].ExitCode: 0
Health.Log[0].Output: /var/run/postgresql:5432 - accepting connections
Ports: 127.0.0.1:5432->5432/tcp
```

El número registrado es la cantidad de entradas observadas, no un contador total
de intentos garantizado por Docker. `docker compose ps` confirmó running/healthy.
Volumen creado `educativa-adaptativa_postgres_data`, driver local, destino
`/var/lib/postgresql/data`; red creada `educativa-adaptativa_default`, driver bridge.
Labels de ambos identifican el proyecto educativa-adaptativa y Compose 5.5.1.

### SQL real de solo lectura

Se ejecutó `docker compose exec -T postgres psql -U adaptativa -d adaptativa`
con `-v ON_ERROR_STOP=1` y las consultas siguientes mediante opciones `-c`.
Conexión local dentro del contenedor, sin pasar contraseña en argumentos.
Resultado global: **código 0**.

```sql
SELECT 1;
SELECT version();
SELECT current_database();
SELECT current_user;
SELECT table_schema, table_name, table_type
FROM information_schema.tables
WHERE table_schema = 'public' ORDER BY table_name;
```

```text
SELECT 1: 1 (1 row)
version: PostgreSQL 17.11 (Debian 17.11-1.pgdg12+2) on x86_64-pc-linux-gnu,
         compiled by gcc (Debian 12.2.0-14+deb12u1) 12.2.0, 64-bit
current_database: adaptativa (1 row)
current_user: adaptativa (1 row)
Tablas public: (0 rows)
```

Sin tablas de aplicación ni historial Flyway observado. No se crearon tablas,
no se insertaron datos ni se ejecutaron migraciones manualmente.
La conexión probada fue por socket dentro del contenedor; el puerto publicado
se comprobó mediante Compose/inspect, sin afirmar una prueba JDBC desde Windows.

### Detención, Docker posterior y límites

`docker compose stop postgres`: **código 0**. `docker compose ps -a`:

```text
NAME                              IMAGE                  SERVICE    STATUS
educativa-adaptativa-postgres-1   postgres:17-bookworm   postgres   Exited (0)
Status=exited ExitCode=0 OOMKilled=false Error=
```

Se conservaron imagen, contenedor detenido, volumen y red; hello-world no se
eliminó. No se ejecutaron down -v, volume rm, system prune ni recreaciones.
`docker version` y `docker info` después de stop: **ambos código 0**.
Desktop 4.91.0 (239619), CLI/Engine 29.8.0, API 1.56, linux/amd64,
desktop-linux; 0 contenedores ejecutándose, 1 detenido, 2 imágenes.
Docker Desktop permanece abierto y operativo.

Solo se actualizan ESTADO_PROYECTO.md, HERRAMIENTAS.md y este documento;
`.env` permanece local e ignorado. La comprobación final comparó el valor en
memoria con todos los archivos versionados sin imprimirlo: SecretInTrackedFiles=False.
`git ls-files -- .env` vacío; `git status --ignored --short` muestra `!! .env`.
`git diff --check`: código 0, sin errores; solo avisos LF a CRLF de Git.
Índice sin cambios, HEAD dc7ad0a intacto. Estado versionado final:

```text
 M docs/ESTADO_PROYECTO.md
 M docs/HERRAMIENTAS.md
 M docs/VERIFICACION_FASE_0.md
```

Maven verify, BackendBootstrapIT, Spring Boot y Eclipse/MDE no ejecutados;
Fase 1 no iniciada. Sin git add, commit ni push. PostgreSQL Compose validado;
el siguiente paso requiere autorización independiente.

## Histórico: Docker Desktop4.91.0 per-user / WSL2: VALIDADO — 22/09/2026

### Estado inicial, descarga e instalación

Git limpio en main/e1df49e, sincronizado con origin/main en
https://github.com/JonathanNevarez/MDEdu.git. Commits previos41315c5 y8262ec1
intactos. WSL2.7.14.0/kernel6.18.33.2-2/predeterminado2, consultas código0.
Los tres indicadores PendingFileRenameOperations, CBS/RebootPending y Windows
Update/RebootRequired estaban inactivos antes de descargar.

Se reconfirmó exclusivamente Docker.DockerDesktop4.91.0 x64, publisher Docker Inc.,
mediante `winget show --id Docker.DockerDesktop --exact --source winget
--version 4.91.0 --architecture x64 --disable-interactivity`. No se eligió4.92.0.

URL oficial:
`https://desktop.docker.com/win/main/amd64/239619/Docker%20Desktop%20Installer.exe`.
Instalador temporal fuera del repositorio:
`C:\Users\ALEXXX~1\AppData\Local\Temp\codex-docker-4.91.0-42d5f7c2ca174cd28f8d8c7e103f7b21\Docker Desktop Installer.exe`.
Tamaño real: **628014512 bytes**. Get-FileHash -Algorithm SHA256:

```text
Esperado:  ac405b09942701770d581b173747fc1024cf0e6047cbe60f13d1df85437311ac
Calculado: AC405B09942701770D581B173747FC1024CF0E6047CBE60F13D1DF85437311AC
Coinciden: True
```

Después de validar el hash se ejecutó Start-Process sobre ese instalador con
ArgumentList install, --user, --backend=wsl-2, como usuario normal
(LaunchingAsAdministrator=False). Sin --accept-license ni --always-run-service.
Instalador código0 y log «Installation succeeded», sin UAC observado.
No se habilitaron características Windows ni se añadió el usuario a docker-users.

Instalación confirmada mediante registro HKCU (DisplayName Docker Desktop,
DisplayVersion4.91.0, publisher Docker Inc.) y ejecutable:

```text
C:\Users\alexxxjon\AppData\Local\Programs\DockerDesktop\Docker Desktop.exe
ProductVersion: 4.91.0.239619
FileVersion: 4.91.0.239619
```

No existe Docker Desktop.exe en C:\Program Files\Docker\Docker.
Se abrió como usuario normal. Apareció Docker Subscription Service Agreement
con View Full Terms, Accept y Close; el asistente se detuvo sin aceptar. El usuario
confirmó posteriormente su aceptación manual y autorizó continuar. En la
reanudación no apareció una pantalla de login que requiriese intervención del
asistente; no se creó cuenta ni se inició sesión automáticamente.

### PowerShell nueva, rutas y versiones reales

Se refrescó el PATH del proceso desde máquina/usuario y se inició powershell.exe
-NoProfile para las verificaciones. No se editó PATH manualmente.

```text
Get-Command docker: Application
C:\Users\alexxxjon\AppData\Local\Programs\DockerDesktop\resources\bin\docker.exe

where.exe docker
C:\Users\alexxxjon\AppData\Local\Programs\DockerDesktop\resources\bin\docker
C:\Users\alexxxjon\AppData\Local\Programs\DockerDesktop\resources\bin\docker.exe

docker --version
Docker version 29.8.0, build 88096ef

docker compose version
Docker Compose version v5.5.1

docker buildx version
github.com/docker/buildx v0.37.0 ac30b249211430b85fb8f37b6e7154b5c47ba0b6

docker context show
desktop-linux
```

Todos terminaron con código0, al igual que docker version, docker info y
docker context ls. Extracto real de versiones:

| Componente | Versión / dato |
| --- | --- |
| Docker Desktop servidor | 4.91.0 (239619) |
| CLI | 29.8.0, build88096ef, windows/amd64 |
| Engine | 29.8.0, build3ce5872, linux/amd64 |
| API cliente/servidor | 1.56; mínima servidor1.40 |
| containerd | v2.3.4; db8809540e1a7a9da5d518876894933ff55692ab |
| runc | 1.4.3; v1.4.3-0-gbb14dabe |
| docker-init | 0.19.0; de40ad0 |
| Compose | v5.5.1 |
| Buildx | v0.37.0 |

Contextos de docker context ls: default con npipe:////./pipe/docker_engine y
desktop-linux (activo) con npipe:////./pipe/dockerDesktopLinuxEngine; sin errores.
where devuelve dos archivos de una misma instalación, no dos instalaciones.

### Motor y backend

docker info respondió correctamente; no se asumió readiness por la GUI.

| Campo | Valor real |
| --- | --- |
| OSType | linux |
| Operating System | Docker Desktop |
| Architecture | x86_64 |
| CPUs | 16 |
| Total Memory | 7.412 GiB; MemTotal7958327296 bytes |
| Storage Driver | overlayfs |
| driver-type | io.containerd.snapshotter.v1 |
| Cgroup Driver | cgroupfs |
| Cgroup Version | 2 |
| Kernel Version | 6.18.33.2-microsoft-standard-WSL2 |
| Docker Root Dir | /var/lib/docker |
| Default Runtime | runc |

No se cambiaron CPU/RAM/storage/cgroup ni opciones del motor. Backend WSL2
confirmado por kernel, contenedores Linux y distribución interna de Docker.
Las claves simples consultadas en settings-store.json no estaban expuestas;
la validación se apoya en los datos efectivos del motor, no en inferir esos valores.

### WSL y prueba mínima

WSL sigue en2.7.14.0 y su kernel de paquete6.18.33.2-2. wsl --status devuelve
docker-desktop como distribución predeterminada y versión predeterminada2.
El aviso de WSL1 deshabilitado sigue siendo esperado. wsl --list --verbose, código0:

```text
  NAME              STATE           VERSION
* docker-desktop    Running         2
```

Únicamente componente interno administrado por Docker. No existe distribución
personal y no se ejecutó unregister, import, install de distribución ni wsl --update.

Tras comprobar docker version/info, se ejecutó `docker run --rm hello-world`.
Antes había0 imágenes/0 contenedores. Salida relevante real:

```text
Unable to find image 'hello-world:latest' locally
latest: Pulling from library/hello-world
Digest: sha256:5e23090353324d887c48ad5e5c56d294eab81588df9605b07d1afe895f9cc8f8
Status: Downloaded newer image for hello-world:latest
Hello from Docker!
This message shows that your installation appears to be working correctly.
HelloWorldExitCode=0
```

Plataforma confirmada con docker image inspect: linux/amd64, mismo RepoDigest.
docker ps -a solo mostró cabecera; docker ps -aq --filter ancestor=hello-world
no devolvió IDs. El contenedor temporal fue eliminado por --rm. Imagen conservada.
No se siguieron las sugerencias del mensaje para ejecutar Ubuntu o crear cuenta.

### Estado final y límites

Engine volvió a responder:29.8.0, OSType linux, Containers0, Images1.
Los tres indicadores de reinicio están **false**. No se requiere reinicio.
Consulta CIM: VirtualMachinePlatform InstallState1; WSL opcional/WSL1,
Microsoft-Hyper-V-All y HypervisorPlatform InstallState2 (deshabilitados).
com.docker.service no existe, resultado aceptado para per-user/WSL2; no se instaló
ni cambió ningún servicio manualmente. Docker Desktop, backend y build permanecen
activos como procesos de la aplicación; sin errores críticos observados.

**DOCKER DESKTOP VALIDADO.** No se descargó PostgreSQL ni se ejecutó Compose del
proyecto, Maven verify o BackendBootstrapIT. No se crearon recursos del proyecto,
ni se instaló Eclipse/MDE, ni se avanzó a Fase1. Esta validación no sustituye
las pruebas de PostgreSQL/integración pendientes de autorización.

Solo se actualizan HERRAMIENTAS.md, ESTADO_PROYECTO.md y este registro.
git diff --check correcto; git status --short:

```text
 M docs/ESTADO_PROYECTO.md
 M docs/HERRAMIENTAS.md
 M docs/VERIFICACION_FASE_0.md
```

Sin staging, commit ni push. HEAD local/origin/main conservan e1df49e; el remoto
MDEdu no se modificó durante esta instalación/validación. Detenerse.

## WSL2: validación post-reinicio satisfactoria — 22/09/2026

Alcance exclusivamente de lectura del sistema y actualización de estos tres
documentos. No se instaló distribución, Docker u otra herramienta; no se ejecutó
wsl --update ni se habilitaron características. No se hicieron cambios Git.

### Reinicio y Git iniciales

PendingFileRenameOperations=false; CBS/RebootPending=false;
Windows Update/RebootRequired=false. No persiste el reinicio de la instalación.
git status --short contenía únicamente los tres documentos WSL ya modificados.
git log -3 --oneline mostró los dos commits existentes:

```text
41315c5 test: validate frontend e2e with chromium
8262ec1 chore: bootstrap validated development environment
```

git remote -v: sin salida. Sin archivos nuevos ni staging.

### Salidas reales de WSL

`wsl --version`, código0, stderr vacío:

```text
Versión de WSL: 2.7.14.0
Versión de kernel: 6.18.33.2-2
Versión de WSLg: 1.0.73.2
Versión de MSRDC: 1.2.7214
Versión de Direct3D: 1.611.1-81528511
Versión de DXCore: 10.0.26100.1-240331-1435.ge-release
Versión de Windows: 10.0.26200.9457
```

`wsl --status`, código0, stderr vacío:

```text
Versión predeterminada: 2
WSL1 no es compatible con la configuración actual del equipo.
Habilita el componente opcional "Subsistema de Windows para Linux" para usar WSL1.
```

Este aviso se refiere solo a WSL1, excluido por el usuario; no es un error del
kernel ni del hipervisor WSL2. No se siguió la sugerencia de habilitar WSL1.

`wsl --list --verbose`, código-1, stderr vacío:

```text
Subsistema de Windows para Linux no tiene distribuciones instaladas.
Para resolverlo, instale una distribución con las instrucciones siguientes:

Use 'wsl.exe --list --online' para enumerar las distribuciones disponibles
y "wsl.exe --install <Distro>" para instalar.
```

El código-1 se registra tal como ocurrió; la lista vacía es el resultado esperado
y aceptado por el usuario. No se instalaron/importaron distribuciones ni se
ejecutaron comandos Linux. No se necesita actualizar WSL2.7.14.0 para este paso.

### Características Windows y virtualización

Se ejecutaron las cuatro consultas Get-WindowsOptionalFeature -Online -FeatureName
mediante PowerShell elevado por UAC solo para lectura. No se usó Enable ni DISM
para modificar estados. Salida:

| FeatureName | State | RestartRequired |
| --- | --- | --- |
| VirtualMachinePlatform | Enabled | Possible |
| Microsoft-Windows-Subsystem-Linux | Disabled | Possible |
| Microsoft-Hyper-V-All | Disabled | Possible |
| HypervisorPlatform | Disabled | Possible |

RestartRequired=Possible describe el comportamiento posible al cambiar una
característica; no indica un reinicio pendiente actual. Los indicadores reales
consultados están inactivos. Se conserva el componente opcional WSL1 deshabilitado.

Datos CIM y systeminfo:

```text
Win32_ComputerSystem.HypervisorPresent: true
Win32_Processor.VirtualizationFirmwareEnabled: true
Win32_OperatingSystem.DataExecutionPrevention_Available: true
Win32_Processor.SecondLevelAddressTranslationExtensions: false
Win32_Processor.VMMonitorModeExtensions: false
systeminfo: Se detectó un hipervisor. No se mostrarán las características necesarias para Hyper-V.
```

Get-ComputerInfo informó HyperVisorPresent=true y requisitos HyperVRequirement*
nulos. Se conserva la discrepancia de campos SLAT/VMMonitor en lugar de ocultarla:
antes de habilitar el hipervisor ambos estaban true en el mismo Ryzen7 7840HS.
Con systeminfo indicando requisitos no expuestos y el hipervisor ahora activo,
los valores actuales no se toman como evidencia de pérdida de soporte ni como
motivo para cambiar BIOS o instalar Hyper-V completo. No se detectaron errores
WSL de kernel/hipervisor/virtualización al consultar fuera del aislamiento.

### Incidencias y resultado

Dentro del aislamiento, --status y --list devolvieron
Wsl/EnumerateDistros/Service/E_ACCESSDENIED, código-1. Se repitieron los mismos
comandos fuera del aislamiento; status pasó y list devolvió la ausencia esperada
de distribuciones. No se cambiaron permisos, ACL ni características para resolverlo.
La primera captura de consola mostró caracteres NUL; se repitió la lectura
mediante ProcessStartInfo con redirección UTF16 explícita, conservando salida,
stderr y código por separado. No fue un error del producto WSL.

**WSL2: instalado y VALIDADO** para el alcance sin distribución personal:
sin reinicio pendiente, versión/kernel disponibles, predeterminado2, VMP Enabled,
firmware activo, hipervisor detectado y ausencia de errores relevantes.
Esto no ejecuta una carga Linux ni valida Docker, fuera de esta autorización.

Documentos actualizados: HERRAMIENTAS.md, ESTADO_PROYECTO.md y este registro.
git diff --check correcto; git status --short permanece:

```text
 M docs/ESTADO_PROYECTO.md
 M docs/HERRAMIENTAS.md
 M docs/VERIFICACION_FASE_0.md
```

Sin staging, commit, publicación ni instalación adicional. **Detenerse.**

## WSL sin distribución personal: instalado, reinicio requerido — 22/09/2026

Alcance autorizado: comprobar el reinicio manual anterior e instalar únicamente
WSL2 mediante `wsl --install --no-distribution` si los indicadores estaban limpios.

### Estado previo confirmado

```text
git status
On branch main
nothing to commit, working tree clean

git log -2 --oneline
41315c5 test: validate frontend e2e with chromium
8262ec1 chore: bootstrap validated development environment

git remote -v
(sin salida)
```

| Comprobación | Resultado previo |
| --- | --- |
| PendingFileRenameOperations | Inactivo; 0 entradas no vacías |
| CBS/RebootPending | Inactivo |
| Windows Update/RebootRequired | Inactivo |
| Windows | Windows11 Pro, 10.0.26200, x64 |
| CPU | AMD Ryzen7 7840HS, 64 bits |
| Virtualización BIOS/UEFI | Habilitada |
| SLAT | Disponible |
| Hipervisor activo | No |
| RAM disponible | 8.39 GiB |
| Disco C libre | 564.22 GiB |
| VirtualMachinePlatform | Deshabilitada, CIM InstallState2 |
| Microsoft-Windows-Subsystem-Linux | Deshabilitada, CIM InstallState2 |
| Microsoft-Hyper-V-All / HypervisorPlatform | Ambas deshabilitadas, InstallState2 |

`wsl --status`, código50, y `wsl --version`, código1, informaron:
«El Subsistema de Windows para Linux no está instalado».
El reinicio pendiente anterior quedó resuelto y los requisitos permitían continuar.

### Comando y resultado reales

Se solicitó elevación mediante Start-Process -Verb RunAs; proceso PowerShell
auxiliar oculto. UAC/elevación obtenida: comprobación interna IsAdministrator=true.
El único comando de instalación ejecutado fue:

```powershell
wsl --install --no-distribution
```

No se añadieron distribución, --enable-wsl1, --update ni comandos independientes
para habilitar Hyper-V. El instalador realizó su habilitación necesaria de VMP.
Salida legible relevante:

```text
Instalando componente opcional de Windows: VirtualMachinePlatform
La operación solicitada se realizó correctamente. Los cambios se aplicarán una vez que se reinicie el sistema.
Descargando: Subsistema de Windows para Linux 2.7.14
Instalando: Subsistema de Windows para Linux 2.7.14
Se ha instalado Subsistema de Windows para Linux 2.7.14.
```

Resultado del proceso elevado:

```json
{
  "IsAdministrator": true,
  "Command": "wsl --install --no-distribution",
  "ExitCode": 0,
  "Finished": "2026-09-22T09:03:14.5219444-05:00"
}
```

**REINICIO REQUERIDO.** Código0 significa que el comando terminó correctamente;
no demuestra que WSL2 esté activo antes del reinicio solicitado.

### Registro posterior, sin validación operativa

Solo se consultaron indicadores y características para documentar el cambio:

| Comprobación | Resultado posterior |
| --- | --- |
| PendingFileRenameOperations | Inactivo |
| CBS/RebootPending | Activo |
| Windows Update/RebootRequired | Inactivo |
| VirtualMachinePlatform | Habilitada según CIM, InstallState1; requiere reinicio para aplicar |
| Microsoft-Windows-Subsystem-Linux | Deshabilitada, InstallState2 |
| Microsoft-Hyper-V-All | Deshabilitada, InstallState2 |
| HypervisorPlatform | Deshabilitada, InstallState2 |
| Distribución personal instalada por este comando | Ninguna, --no-distribution |

No se ejecutaron wsl --version, --status ni --list --verbose después de instalar:
el usuario exige detenerse si se solicita reinicio. La versión2.7.14 procede del
instalador; versión del kernel no mostrada/comprobada. Tras reiniciar manualmente,
quedan pendientes esos tres comandos y Get-WindowsOptionalFeature para VMP/WSL
con elevación de lectura. No se hará wsl --update por rutina.

Incidencias de observación: la salida nativa WSL/DISM combina codificaciones y
parte del progreso DISM se mostró ilegible en la captura; el mensaje de instalación,
la solicitud de reinicio y el resultado JSON son legibles. No se repitió la
instalación. La lectura CIM posterior dentro del aislamiento fue denegada y se
repitió fuera del aislamiento únicamente para lectura.

Se conservan install.log y result.json en la carpeta temporal local
`C:\Users\ALEXXX~1\AppData\Local\Temp\codex-wsl-install-f2936cc53436495589c0ba729f789deb`.
No se descargó instalador Docker ni se instaló ninguna otra herramienta. No se
reinició Windows automáticamente. Los dos commits permanecen intactos.
git status --short tras documentar muestra únicamente:

```text
 M docs/ESTADO_PROYECTO.md
 M docs/HERRAMIENTAS.md
 M docs/VERIFICACION_FASE_0.md
```

Sin staging ni commit. **Detenerse para reinicio manual; Fase1 no iniciada.**

## Playwright Chromium: E2E real completado — 22/09/2026

Alcance: instalar Chromium propio de Playwright, ejecutar el único escenario E2E
existente y documentar. Sin Firefox/WebKit/Chrome global, WSL, Docker, PostgreSQL,
Eclipse/MDE ni otras herramientas. No se cambió ExecutionPolicy ni se hizo staging,
commit, amend, reset, rebase, checkout del snapshot o conexión remota.

### Estado inicial y escenario inspeccionado

`git status --short`: sin salida, working tree clean.
`git log -1 --oneline`:

```text
8262ec1 chore: bootstrap validated development environment
```

HEAD completo verificado: 8262ec1f636b1928ff6f637b9d9ba983e5fcfa8d.
No existía caché ms-playwright. Procesos del proyecto/Playwright0 y listeners0
en puertos4173/5173 antes de instalar.

Se leyeron package.json, playwright.config.ts y tests/e2e/startup.spec.ts sin
modificarlos. Playwright instalado: **1.63.0**. Configuración:

- testDir: ./tests/e2e; reporter list; proyecto chromium con dispositivo Desktop Chrome.
- baseURL: http://127.0.0.1:4173; navegador Chromium, ejecución headless por defecto.
- webServer: npm run preview, misma URL, reuseExistingServer=false, timeout30000ms.
- preview: vite preview --host 127.0.0.1 --port 4173 --strictPort.
- timeout de prueba no sobrescrito: predeterminado30000ms, comprobado en el código
  instalado de Playwright. Reintentos locales0; en CI2 y forbidOnly=true.
- trace retain-on-failure; no captura de screenshots configurada.
- Comando E2E exacto: npm.cmd run test:e2e → npm run build && playwright test.

Único escenario, startup.spec.ts:3: abre /; verifica título «Lógica de programación»
y heading «Un espacio para aprender lógica de programación» visible. Abre
/pagina-inexistente, comprueba «No encontramos esta página», pulsa «Volver al inicio»,
verifica URL http://127.0.0.1:4173/ y contenido principal visible otra vez.
Recoge pageerror y exige un arreglo vacío al terminar. Demuestra renderizado React
y navegación en navegador real. No escucha console.error/console.warn por separado;
no se declara una auditoría completa de consola ni se añadió una prueba nueva.

### Instalación y caché

Desde frontend, con permisos para red/caché:

```powershell
npm.cmd run test:e2e:install
```

Script ejecutado: playwright install chromium. Salida0; componentes descargados:

```text
Chrome for Testing 153.0.8010.12 (playwright chromium v1243)
Chrome Headless Shell 153.0.8010.12 (playwright chromium-headless-shell v1243)
FFmpeg (playwright ffmpeg v1011)
Winldd (playwright winldd v1007)
```

Destino: `C:\Users\alexxxjon\AppData\Local\ms-playwright`.
Versión153.0.8010.12 también confirmada en ProductVersion de chrome.exe.

| Componente de caché | Bytes de archivos instalados |
| --- | ---: |
| chromium-1243 | 452900525 |
| chromium_headless_shell-1243 | 283217434 |
| ffmpeg-1011 | 3517342 |
| winldd-1007 | 258560 |
| .links (metadatos locales) | 84 |
| Total (610 archivos) | 739893945 |

Aproximadamente740 MB decimales / 705.62 MiB; suma de tamaños lógicos de archivos,
no espacio físico de bloques del disco. No se instalaron los demás navegadores.

### Ejecución real

`npm.cmd run test:e2e` se ejecutó fuera del aislamiento para permitir procesos de
build, servidor y navegador, autorizado por el usuario. Sin primer intento fallido
ni cambios al scaffold. Salida real resumida, código final0:

```text
> npm run build && playwright test
> tsc --project tsconfig.json && tsc --project tsconfig.node.json
vite v8.3.0 building client environment for production...
26 modules transformed.
dist/index.html                   0.60 kB | gzip: 0.35 kB
dist/assets/index-BbaKOYXq.css    1.45 kB | gzip: 0.73 kB
dist/assets/index-BggaICop.js   260.89 kB | gzip: 82.96 kB
built in 738ms
Running 1 test using 1 worker
ok 1 [chromium] tests/e2e/startup.spec.ts:3:1
el frontend compilado abre y permite recuperar una ruta inexistente (546ms)
1 passed (2.5s)
```

Resultado: 1 archivo/spec, 1 escenario, 1 aprobado, 0 fallidos, 0 omitidos.
2.5s corresponde a Playwright; no incluye toda la duración del comando npm/build.
No hubo pageerror en el recorrido: la aserción final del arreglo vacío pasó.
Único aviso observado (Node del webServer y worker, no error de navegador):

```text
Warning: The 'NO_COLOR' env is ignored due to the 'FORCE_COLOR' env being set.
```

No fue necesario modificar aplicación, configuraciones, versiones ni pruebas.

### Cierre de procesos, artefactos y Git

Al terminar, se consultaron procesos Win32 cuyo ejecutable pertenece a la caché
Playwright y procesos Node Vite/Playwright del proyecto; se consultaron listeners:

```text
PlaywrightOrProjectProcessesAfter: 0
ListenersAfter (4173/5173): 0
HTTP after E2E: connection unavailable; preview stopped
```

No fue necesario matar procesos manualmente. Chromium y preview finalizaron.
playwright-report/ no existe: reporter list. test-results/ existe y contiene
.last-run.json, con salida real:

```json
{
  "status": "passed",
  "failedTests": []
}
```

No hubo screenshot ni trace de fallo. Los resultados se conservan ignorados.
git check-ignore -v confirmó **/test-results/ y **/playwright-report/.
Antes de editar documentación, git status --short continuaba vacío;
git status --ignored --short mostraba backend/target, frontend/dist,
frontend/node_modules y frontend/test-results como ignorados.

Estado esperado y comprobado tras esta actualización:

```text
 M docs/ESTADO_PROYECTO.md
 M docs/HERRAMIENTAS.md
 M docs/VERIFICACION_FASE_0.md
```

Solo documentación versionada modificada; HEAD sigue siendo8262ec1, sin staging,
commit ni publicación. No se tocaron otras documentaciones ni archivos del scaffold.
git diff --check pasó; Git mostró avisos habituales de futura conversión LF/CRLF
en estos documentos, sin errores de whitespace ni cambios de política.

FRONTEND BUILD: **OK** (repetido por E2E). FRONTEND UNIT: **OK** (2 pruebas ya
validadas, no repetidas). FRONTEND HTTP: **OK** (validación previa).
FRONTEND E2E CHROMIUM: **OK**. La Fase0 sigue abierta por backend/DB pendientes.
**Detenerse hasta nueva autorización.**

## Snapshot inicial autorizado — preparado el 21/09/2026, retomado el 22/09/2026

El primer intento de ejecutar el commit fue rechazado por la revisión automática
al alcanzar el límite de uso del entorno; no llegó a ejecutarse. El usuario
autorizó continuar el 22/09/2026. Se confirmó main, identidad local, ausencia de
HEAD/remotos y los 98 archivos en staging. Se incorpora esta evidencia antes de
reintentar el commit, sin instalar herramientas ni modificar configuración global.

Identidad proporcionada por el usuario y aplicada exclusivamente con --local:

```text
git config --local --get user.name
JonathanNevarez
git config --local --get user.email
neva_rez00@hotmail.com
git branch --show-current
main
```

Se ejecutó `git branch -M main`. No se modificó configuración global ni se añadió
el repositorio GitHub mencionado por el usuario: continúa vigente la instrucción
de mantener este snapshot exclusivamente local, sin remotos ni publicación.

Verificaciones anteriores al staging:

- 98 candidatos, exactamente iguales a docs/PRIMER_COMMIT.md; cero diferencias.
- Todos los archivos críticos presentes: configuración, lock, Wrapper, fuentes,
  pruebas, migraciones, Compose, documentación, MDE y scripts.
- node_modules, dist y target ignorados; cero candidatos de cachés/logs/temporales
  o .env reales. .env.example incluido con contraseña vacía.
- Revisión básica de 98 archivos: cero patrones de claves reales/privadas o
  credenciales embebidas detectados. Las referencias de contraseña usan variables.
- SVG local idéntico al original y al hash registrado:
  `fcb04a1bf5bbd8bf1b5bb4ca4a40bf931f3f997918bf245a16208e9b5b23f6c9`.
- backend/mvnw: 295 LF, cero CRLF; backend/mvnw.cmd: 189 CRLF, cero LF aislados.
  Atributos coherentes. Wrapper no regenerado; mvnw.cmd -version correcto:
  Maven3.9.16 y Java21.0.12.1. No se recompiló el backend.

Documentación preparada antes del commit. `git add -A` incluyó los 98 archivos
del inventario; status/stat revisados. `git diff --cached --check`: sin salida,
código0. Revisión del contenido staged: cero patrones de secretos detectados y
cero artefactos descartables. El blob staged del SVG coincide con el original.
Wrapper: índice LF en ambos scripts; copia de trabajo LF para mvnw y CRLF para
mvnw.cmd, coherente con sus atributos. No hay modificaciones fuera de staging.
Git emitió avisos de futura conversión LF a CRLF para texto con eol no fijado;
son avisos de checkout, no errores de whitespace ni cambios globales realizados
por este paso. Esta actualización documental se vuelve a incluir y comprobar
antes de ejecutar el commit autorizado.
Mensaje exacto: `chore: bootstrap validated development environment`.

Después se verifican `git status`, `git log -1 --oneline`,
`git show --stat --oneline HEAD` y `git remote -v`. El resultado real y hash se
registran en `.git/FASE_0_SNAPSHOT.txt`, metadato local fuera del árbol versionado,
y en la respuesta al usuario. Esto permite conservar el working tree limpio:
un commit no puede incluir su propio hash en sus archivos sin cambiar ese hash.
No se hará amend, segundo commit documental, tag, push ni instalación adicional.
El hash se puede recuperar del historial incluso sin el informe local mediante
`git log --max-parents=0 --oneline`. Esta sección registra la intención previa;
la evidencia posterior determina si el snapshot quedó creado y limpio.

## Histórico Git: instalación y preparación del repositorio local — 21/09/2026

Autorización limitada a Git for Windows y preparación del primer punto de versionado.
No se instalaron navegadores, WSL, Docker, Eclipse/MDE ni otras herramientas.
No se hizo staging, commit, conexión remota, push, pull ni clone.

### Instalación y verificación

Se reconfirmó `Git.Git` 2.55.0.3 x64 en WinGet. Instalador oficial:
`https://github.com/git-for-windows/git/releases/download/v2.55.0.windows.3/Git-2.55.0.3-64-bit.exe`.
SHA256 anunciado: `af12577d0fdff74243a5988197aa49b957d5044edc17004f6ddf0768996f1dca`.

```powershell
winget show --id Git.Git --exact --source winget --version 2.55.0.3 --architecture x64 --disable-interactivity
winget install --id Git.Git --exact --source winget --version 2.55.0.3 --architecture x64 --scope machine --silent --accept-package-agreements --disable-interactivity
```

WinGet informó `Successfully verified installer hash` y `Successfully installed`,
código0. No se añadieron clientes gráficos, herramientas SSH ni gestores de
credenciales independientes del paquete oficial. No se configuraron credenciales.

Se refrescó el PATH del proceso desde máquina/usuario y se abrió una nueva
PowerShell. No hubo edición manual de PATH. Salidas reales:

```text
git --version
git version 2.55.0.windows.3

where.exe git
C:\Program Files\Git\cmd\git.exe

Get-Command git
CommandType: Application
Name: git.exe
Source: C:\Program Files\Git\cmd\git.exe

git version --build-options (extracto)
cpu: x86_64
built from commit: 52ca1113d651127f89477a8763f86ab20f645e1d
sizeof-size_t: 8
```

Única instalación prioritaria encontrada. La ruta shell-path de build-options
corresponde al entorno de compilación de Git, no a una instalación local adicional.

| Consulta global | Salida | Código |
| --- | --- | ---: |
| git config --global --get user.name | Sin salida: AUSENTE | 1 |
| git config --global --get user.email | Sin salida: AUSENTE | 1 |
| git config --global --get core.autocrlf | Sin salida: AUSENTE | 1 |

No se configuraron estos valores, credential.helper ni defaultBranch.
La ausencia de core.autocrlf global no afirma ausencia de un valor predeterminado
del instalador a nivel sistema; la consulta autorizada se limitó al ámbito global.

### Repositorio, exclusiones y finales de línea

`Test-Path .git` devolvió False antes de instalar. `git init`, código0:

```text
Initialized empty Git repository in C:/Users/alexxxjon/Desktop/Tesis/Nueva carpeta/.git/
```

Rama inicial `master`, elegida por Git; no se cambió la rama ni su configuración.
Se inspeccionó .gitignore antes de cualquier staging. Se conservaron las reglas
de productos/cachés y se añadieron .metadata/, *.tmp y *.bak. Se eliminó .vscode/
y la excepción amplia !.env.*.example; solo .env.example queda exceptuado.
No existe carpeta .vscode que deba revisarse archivo por archivo.

`git check-ignore -v` confirmó:

```text
**/node_modules/  frontend/node_modules/
**/dist/          frontend/dist/
**/target/        backend/target/
.env              .env
.env.*            .env.local
.idea/            .idea/workspace.xml
.metadata/        .metadata/test
*.tmp             *.tmp
*.bak             *.bak
```

Son rutas reales de productos y rutas de prueba para reglas; no se crearon
archivos .env, IDE ni temporales. `git check-ignore .env.example
.vscode/settings.json mde/generated/README.md` no imprimió coincidencias y
devolvió1, como corresponde a archivos no ignorados. Las cachés Maven/npm
externas están fuera del árbol del repositorio.

Se creó .gitattributes:

```gitattributes
* text=auto

*.sh text eol=lf
mvnw text eol=lf
*.cmd text eol=crlf
```

Política conservadora: Git detecta texto; scripts Unix usan LF y comandos Windows
CRLF. No se ejecutó renormalización ni se cambió core.autocrlf. `git check-attr`
confirmó text=set/eol=lf para backend/mvnw y text=set/eol=crlf para mvnw.cmd;
TypeScript mantiene text=auto y eol sin especificar.

Wrapper no editado. `bash --noprofile --norc -n backend/mvnw`: código0.
`backend/mvnw.cmd -version`: código0, Apache Maven3.9.16, Java21.0.12.1 de
Eclipse Adoptium, usando su caché existente. No se repitieron builds/tests.

Estrategia MDE documentada en mde/generated/README.md: versionar fuentes Ecore,
GenModel, Xtext, ATL, Acceleo, ejemplos relevantes y configuración reproducible.
Sin regla mde/generated/: los artefactos aún no existen y su política específica
se decidirá en Fase1 según EMF/Xtext/Tycho.

### Revisión de secretos y candidatos

Antes de cualquier staging se enumeraron los 96 archivos originales y se buscaron
claves API, tokens, contraseñas, claves privadas y URL con credenciales, excluyendo
dependencias y productos de compilación. Cero coincidencias de patrones de claves
reales/privadas/credenciales embebidas; referencias generales revisadas por contexto.
No se imprimieron valores sensibles. .env.example contiene DB_PASSWORD vacío;
Compose y application.properties usan DB_PASSWORD externa. La prueba de integración
obtiene su contraseña de Testcontainers. Referencias de CORS a credentials son
opciones, no credenciales. No hay archivos .env locales ni claves privadas candidatas.

Revisión básica: **sin secretos reales detectados**. Los 98 candidatos finales
incluyen .gitattributes y la nueva lista de revisión, además de los 96 originales.
La enumeración exacta y clasificación están en [PRIMER_COMMIT](PRIMER_COMMIT.md),
obtenidas con `git ls-files --others --exclude-standard`. No se añadió nada al índice.

`git status`: On branch master; No commits yet; nothing added to commit but
untracked files present. `git status --short`:

```text
?? .editorconfig
?? .env.example
?? .gitattributes
?? .gitignore
?? README.md
?? backend/
?? docker-compose.yml
?? docker/
?? docs/
?? frontend/
?? mde/
?? scripts/
```

`git status --ignored --short` añadió exclusivamente:

```text
!! backend/target/
!! frontend/dist/
!! frontend/node_modules/
```

`git remote -v` y `git ls-files --stage`: sin salida. .git/index no existe.
No hay remoto, staging ni commit; el estado contiene únicamente candidatos
intencionales, sin artefactos descartables listados para versionar.

### Incidencias y cierre

La consulta WinGet dentro del aislamiento no produjo salida; la consulta autorizada
fuera de él respondió correctamente. La primera invocación anidada de PowerShell
tuvo un error de comillas antes de ejecutar las verificaciones; se corrigió pasando
el script mediante EncodedCommand. Bash dentro del aislamiento falló al crear signal
pipe (Win32 error5); la misma comprobación de sintaxis fuera del aislamiento pasó.
No fueron fallos del proyecto ni requirieron cambios en los wrappers.

**Paso Git completo.** Falta identidad global y la revisión/autorización del usuario
antes del primer commit. Detenerse; no instalar otro componente ni avanzar a Fase1.

## Node.js + npm: frontend verificado realmente el 21/09/2026

Alcance autorizado: Node24 LTS x64, npm y validación frontend de Fase0. No se
instalaron gestores adicionales, Git, WSL, Docker, Eclipse/MDE ni navegadores.
Java/Maven permanecieron cerrados; no se repitieron sus pruebas.

### Inspección previa, sin cambiar versiones

Proyecto: `herramienta-educativa-frontend`, versión `0.0.0`, private=true,
type=module. `engines.node` sigue siendo `>=24.15.0 <25`;
`.npmrc`: save-exact=true y engine-strict=true. No existía package-lock.json.

| Script | Comando declarado |
| --- | --- |
| dev | vite --host 127.0.0.1 --port 5173 --strictPort |
| typecheck | tsc --project tsconfig.json && tsc --project tsconfig.node.json |
| build | npm run typecheck && vite build |
| preview | vite preview --host 127.0.0.1 --port 4173 --strictPort |
| test | vitest run |
| test:watch | vitest |
| test:e2e | npm run build && playwright test |
| test:e2e:install | playwright install chromium |

Dependencies: react19.3.0, react-dom19.3.0 y react-router-dom7.18.4.
DevDependencies originales conservadas:

| Paquete | Versión |
| --- | --- |
| @playwright/test | 1.63.0 |
| @testing-library/dom | 10.4.2 |
| @testing-library/jest-dom | 7.0.1 |
| @testing-library/react | 16.3.3 |
| @testing-library/user-event | 14.6.7 |
| @types/node | 24.0.0 |
| @types/react / @types/react-dom | 19.3.0 / 19.3.0 |
| @vitejs/plugin-react | 6.1.1 |
| jsdom | 30.1.0 |
| typescript | 5.9.3 |
| vite | 8.3.0 |
| vitest | 5.0.1 |

Pruebas existentes: `tests/unit/App.test.tsx` tiene 2 casos de inicio/navegación;
`tests/unit/setup.ts` es configuración, no un archivo de casos adicional.
`tests/e2e/startup.spec.ts` contiene 1 escenario Playwright, separado del include
de Vitest. No se alteraron pruebas ni funcionalidad.

### Instalación y entorno

Se ejecutó primero la consulta de catálogo y luego únicamente la instalación
autorizada, fuera del aislamiento:

```powershell
winget show --id OpenJS.NodeJS.LTS --exact --source winget --version 24.19.0 --architecture x64 --installer-type wix --scope machine --disable-interactivity
winget install --id OpenJS.NodeJS.LTS --exact --source winget --version 24.19.0 --architecture x64 --installer-type wix --scope machine --interactive
```

El catálogo resolvió `https://nodejs.org/dist/v24.19.0/node-v24.19.0-x64.msi`.
WinGet informó `Successfully verified installer hash` y `Successfully installed`,
salida0. Hash del instalador anunciado en el catálogo:
`f0f66c2a80c08a30a5ab5179ee9ea9e45f9b46289436a8cc87ff833b852db351`.
No se instalaron nvm/fnm/Chocolatey/Scoop/yarn/pnpm ni herramientas nativas opcionales.

Se cargó el PATH persistente de máquina/usuario en el proceso, sin modificarlo
manualmente, y se abrió una nueva instancia PowerShell. Resultados reales:

| Comando | Salida |
| --- | --- |
| node --version | v24.19.0 |
| node -p process.arch | x64 |
| npm --version en PowerShell | PSSecurityException: scripts deshabilitados para C:\Program Files\nodejs\npm.ps1 |
| npm.cmd --version | 11.17.0 |
| cmd.exe /d /c "npm --version" | 11.17.0 |
| where.exe node | C:\Program Files\nodejs\node.exe |
| where.exe npm | C:\Program Files\nodejs\npm y C:\Program Files\nodejs\npm.cmd |
| Get-Command node | Application; C:\Program Files\nodejs\node.exe |
| Get-Command npm | ExternalScript; C:\Program Files\nodejs\npm.ps1 |
| Get-Command npm.cmd | Application; C:\Program Files\nodejs\npm.cmd |

Todas las rutas pertenecen a la misma instalación. No se encontró otra versión
prioritaria en PATH. Se usó npm.cmd para las operaciones siguientes, sin cambiar
la política de ejecución de PowerShell.

### Dependencias y control de cambios

Como no existía lockfile, se ejecutó desde frontend:

```powershell
$env:PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD = '1'
npm.cmd install
```

La variable se estableció solo en el proceso de instalación. Salida real, código0:

```text
added 114 packages, and audited 115 packages in 47s
22 packages are looking for funding
found 0 vulnerabilities
New major version of npm available! 11.17.0 -> 12.0.2
```

Se generaron node_modules/ y package-lock.json. No se ejecutó ci, audit fix ni
actualización de npm/dependencias. El lock es v3, con 140 entradas de paquetes
(incluye variantes opcionales que no necesariamente se instalan en Windows).
Node JSON.parse confirmó que dependencies/devDependencies del registro raíz
coinciden con el manifiesto. SHA256:

- package.json: `192CE854D83031D1CB6EB2AB56AB0EBAF333D33396E04C6FAEFAE6397D160AA6`.
- package-lock.json: `2D3B07DDC1FEDB14BD06CC25B2160EBE6237A4A043D8C6CE18602CCEF91BE424`.

Se compararon hashes de los 17 archivos originales antes/después de instalar y
después de build/tests/HTTP: **ninguno cambió**. Se mantuvieron package.json,
tsconfigs, Vite/Vitest/Playwright, código y pruebas. Después se actualizó únicamente
documentación para reflejar resultados e instrucciones con lockfile.

### Build y bundle

`npm.cmd run build` ejecutó typecheck de aplicación/pruebas/configuraciones y Vite.
Primer intento: TypeScript pasó, Vite falló dentro del aislamiento con este error:

```text
failed to load config from ...\frontend\vite.config.ts
[plugin externalize-deps]
Error: spawn EPERM
at optimizeSafeRealPathSync (.../node_modules/vite/dist/node/chunks/node.js:2438:2)
```

No se atribuyó a una línea defectuosa del scaffold. Se repitió el mismo comando
fuera del aislamiento con autorización; no hubo cambios de versiones/configuración.
Resultado final, salida0:

```text
vite v8.3.0 building client environment for production...
26 modules transformed.
dist/index.html                   0.60 kB | gzip: 0.35 kB
dist/assets/index-BbaKOYXq.css    1.45 kB | gzip: 0.73 kB
dist/assets/index-BggaICop.js   260.89 kB | gzip: 82.96 kB
built in 446ms
```

Inventario real en disco:

| Archivo | Bytes |
| --- | ---: |
| dist/index.html | 609 |
| dist/assets/index-BbaKOYXq.css | 1459 |
| dist/assets/index-BggaICop.js | 260890 |
| Total | 262958 |

Aproximadamente263 KB sin comprimir; JavaScript aproximadamente83 KB gzip.
No se optimizó el bundle ni se introdujeron funcionalidades.

### Pruebas unitarias/componentes

`npm.cmd run test`, fuera del aislamiento para permitir procesos auxiliares:

```text
RUN v5.0.1
Test Files 1 passed (1)
Tests 2 passed (2)
Start at 18:57:22
Duration 38.73s (environment 77%, setup 16%, import 6%)
```

Salida0. Un archivo de casos, 2 pruebas aprobadas, 0 fallidas, 0 omitidas.
Comprueban el inicio y regreso desde una ruta inexistente con RTL/MemoryRouter.
No se modificaron tests para obtener este resultado.

### Arranque Vite, HTTP y cierre

Se ejecutó temporalmente `npm.cmd run dev` en sesión de terminal controlada.
Salida: `VITE v8.3.0 ready in 234 ms`, URL real `http://127.0.0.1:5173/`.
Desde otra terminal se hicieron solicitudes Invoke-WebRequest:

| Recurso | Estado | Tipo |
| --- | --- | --- |
| / | 200 | text/html |
| /src/main.tsx | 200 | text/javascript |
| /src/app/App.tsx | 200 | text/javascript |
| /src/pages/HomePage.tsx | 200 | text/javascript |
| /pagina-inexistente | 200 | text/html, fallback SPA esperado |

HTML de 766 bytes servido por Vite; se confirmaron `<div id="root"></div>`,
entrada /src/main.tsx y título Lógica de programación. La primera comparación
del título falló por decodificación de tildes en Invoke-WebRequest de PowerShell5.1;
se leyó RawContentStream como UTF8 y la comprobación pasó. No se cambió el HTML.
El renderizado real en navegador sigue siendo materia de E2E; HTTP200 no lo sustituye.

La consulta Get-NetTCPConnection fue denegada dentro del aislamiento y se repitió
fuera de él: listener127.0.0.1:5173, PID8712. Se detuvo Vite con Ctrl+C y respuesta
S al cierre del trabajo por lotes. Esa terminación voluntaria devolvió código1,
no un fallo de arranque. Verificación posterior:

```text
ViteProcessRemaining: 0
Port5173Listeners: 0
HTTP tras detener Vite: No es posible conectar con el servidor remoto
```

No quedaron servidores temporales de esta prueba.

### Playwright: sin descarga de navegadores

Se ejecutó únicamente `playwright.cmd install --dry-run chromium` desde node_modules/.bin.
La caché `%LOCALAPPDATA%\ms-playwright` no existe antes/después. Las consultas
HTTP HEAD a los ZIP oficiales devolvieron Content-Length sin descargar sus cuerpos:

| Archivo previsto | Versión/revisión | Bytes de descarga |
| --- | --- | ---: |
| Chrome for Testing win64 | 153.0.8010.12 / 1243 | 205123748 |
| Chrome Headless Shell win64 | 153.0.8010.12 / 1243 | 120200717 |
| FFmpeg win64 | 1011 | 1411741 |
| Winldd win64 | 1007 | 128684 |
| Total previsto | | 326864890 |

Aproximadamente327 MB de descarga comprimida; espacio instalado no calculado.
Script: test:e2e:install; comando futuro `npm.cmd run test:e2e:install`.
Después se usaría `npm.cmd run test:e2e`. **Ambos pendientes de autorización**:
no se instalaron navegadores ni se ejecutó el escenario E2E.

### Seguridad básica, incidencias y cierre de este paso

Se leyeron las fuentes/configuraciones y se buscaron patrones de claves, contraseñas,
tokens y claves privadas sin imprimir valores sensibles. No hay archivos .env/.env.*
en frontend, y no se encontraron secretos ni claves LLM en src/configuración.
Es una revisión básica del código propio, no una garantía universal sobre dependencias.

Incidencias resueltas sin tocar el producto: npm.ps1 bloqueado → npm.cmd;
spawn EPERM → ejecución fuera del aislamiento; título HTTP → decodificación UTF8.
ConvertFrom-Json de PowerShell5.1 tampoco pudo representar la clave vacía válida
del lockfile; Node JSON.parse lo verificó sin alterar el archivo. Los avisos de npm
se registran; no hubo incompatibilidad de engines ni actualización automática.

Se creó package-lock.json y productos node_modules/dist, se actualizó documentación,
pero no cambió código, tests, manifiesto ni configuraciones. Node y npm son las
únicas herramientas instaladas en esta autorización. **Paso frontend completado;
Fase0 sigue abierta. Detenerse hasta nueva autorización.**

## Maven: instalación de usuario, Wrapper y compilación real

Autorización actual limitada a Maven y validación del scaffold backend, conservando
Java21 previamente aceptado. No se instaló ningún otro componente.

### Revisión previa del proyecto

- Coordenadas: `com.project:adaptativa-backend:0.1.0-SNAPSHOT`; empaquetado JAR.
- Java configurado: 21. Parent: `spring-boot-starter-parent:3.5.16`.
- Plugins explícitos: Spring Boot y Failsafe, con metas integration-test/verify.
  No hay perfiles declarados. Surefire procede de la configuración heredada.
- Dependencias: Web, Validation, Data JPA, Actuator, Jackson, driver PostgreSQL,
  flyway-core, flyway-database-postgresql, starter-test, Testcontainers junit-jupiter
  y postgresql. No hay H2 ni dependencias de MDE incorporadas.
- Configuración: PostgreSQL localhost5432/adaptativa; contraseña externa;
  Flyway activo en classpath:db/migration; clean-disabled=true;
  Hibernate ddl-auto=validate; open-in-view=false. V1 contiene SELECT1 y no se ejecutó.
- Pruebas: CorsConfigurationTest es un slice MVC, 3 invocaciones sin Docker;
  BackendBootstrapIT integra Spring Boot/PostgreSQL mediante Testcontainers, 4 casos.
  No existen pruebas unitarias de dominio ni otras pruebas backend en esta entrega.

El POM era legible y no requirió correcciones. SHA256 antes y después:
`AEB4DC56C0E87719248EE393E81B1359ADE6589094DC0E5C90925F986786493B`.
No se cambiaron versiones, perfiles, dependencias, plugins ni configuración JPA/Flyway.

### Descarga e instalación

Versión elegida y confirmada antes de descargar: **Maven3.9.16**.
ZIP binario portable con scripts Windows, no archivo de fuentes ni mvnd.
Fuentes oficiales:

- [ZIP binario Apache](https://dlcdn.apache.org/maven/maven-3/3.9.16/binaries/apache-maven-3.9.16-bin.zip).
- [SHA-512 oficial](https://downloads.apache.org/maven/maven-3/3.9.16/binaries/apache-maven-3.9.16-bin.zip.sha512).

Se ejecutaron Invoke-WebRequest para ambos archivos, Get-FileHash -Algorithm SHA512,
comparación y solo después Expand-Archive, según el procedimiento documentado.
Destino real, fuera del repositorio y en el perfil del usuario:

```text
C:\Users\alexxxjon\AppData\Local\Programs\Apache\Maven\apache-maven-3.9.16
```

SHA-512 esperado y calculado coinciden (diferencia solo de mayúsculas de presentación):

```text
ed41650d42485cfc243fad22158caf9cbb5dc408ce7a09ddb94dd42a019de929ca43065bfa450612cf12bf78b5cafa3884b96c090de326ff590448c933454af3
```

Se añadió únicamente `...\apache-maven-3.9.16\bin` al PATH de usuario, preservando
las entradas previas. No se cambió JAVA_HOME ni PATH de máquina. Se refrescó el
entorno de proceso desde los valores persistentes y se abrió otra PowerShell.
No hubo instalador gráfico, reinicio ni instalación de máquina; la autorización
del entorno cubrió red/escrituras fuera del workspace y caché Maven.

`mvn -version`, salida real:

```text
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: C:\Users\alexxxjon\AppData\Local\Programs\Apache\Maven\apache-maven-3.9.16
Java version: 21.0.12.1, vendor: Eclipse Adoptium, runtime: C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot
Default locale: es_EC, platform encoding: UTF-8
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

`where.exe mvn`:

```text
C:\Users\alexxxjon\AppData\Local\Programs\Apache\Maven\apache-maven-3.9.16\bin\mvn
C:\Users\alexxxjon\AppData\Local\Programs\Apache\Maven\apache-maven-3.9.16\bin\mvn.cmd
```

`Get-Command mvn`: Application, mvn.cmd, Source igual a la segunda ruta. Las dos
coincidencias pertenecen a la misma instalación; no se detectó otro Maven prioritario.

### Wrapper

Desde backend se ejecutó:

```powershell
mvn --batch-mode --no-transfer-progress wrapper:wrapper '-Dmaven=3.9.16' '-Dtype=only-script'
```

Resultado: wrapper:3.3.4:wrapper, distribución only-script3.3.4, BUILD SUCCESS,
salida0, 10.970s. Generó:

```text
backend/mvnw
backend/mvnw.cmd
backend/.mvn/wrapper/maven-wrapper.properties
```

Se añadió la propiedad de comprobación SHA-256 calculada sobre el ZIP previamente
autenticado mediante el SHA-512 oficial. Configuración final:

```properties
wrapperVersion=3.3.4
distributionType=only-script
distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.16/apache-maven-3.9.16-bin.zip
distributionSha256Sum=5af3b743dd8b876b5c45da33b676251e5f1687712644abb4ee519ca56e1d89ce
```

`.\mvnw.cmd -version` descargó/verificó su propia distribución y terminó con salida0:

```text
Apache Maven 3.9.16 (2bdd9fddda4b155ebf8000e807eb73fd829a51d5)
Maven home: C:\Users\alexxxjon\.m2\wrapper\dists\apache-maven-3.9.16\0daed3be3ebd1c706f0e69e8b07c6b73f5cc4ea3dfce72a8d0ec2e849ca2ddb0
Java version: 21.0.12.1, vendor: Eclipse Adoptium, runtime: C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot
Default locale: es_EC, platform encoding: UTF-8
OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"
```

La caché propia del Wrapper no está en PATH. Las tareas normales del proyecto
pasan a usar mvnw.cmd/mvnw. El JDK sigue siendo requisito externo.

### Resolución y compilación

| Comando ejecutado desde backend | Resultado real |
| --- | --- |
| `.\mvnw.cmd --batch-mode --no-transfer-progress help:effective-pom '-Doutput=target/effective-pom.xml'` | help3.5.1, POM efectivo escrito, BUILD SUCCESS, salida0; 6.194s |
| `.\mvnw.cmd --batch-mode --no-transfer-progress validate` | BUILD SUCCESS, salida0; 0.424s |
| `.\mvnw.cmd --batch-mode --no-transfer-progress clean compile` | Primer intento: error de transferencia de spring-boot-actuator3.5.16, salida1 |
| `.\mvnw.cmd --batch-mode --no-transfer-progress -U clean compile` | Reintento: BUILD SUCCESS, salida0; 5.051s; 42 fuentes compiladas con release21 |
| `.\mvnw.cmd --batch-mode --no-transfer-progress test` | BUILD SUCCESS, salida0; 10.084s; 3 pruebas, 0 fallos/errores/omitidas |

El POM efectivo confirmó compiler3.14.1, Surefire/Failsafe3.5.6, JUnit Jupiter5.12.2,
Mockito5.17.0, Testcontainers1.21.4, Flyway11.7.2 y PostgreSQL JDBC42.7.11.
El archivo efectivo en target fue inspeccionado antes de que clean eliminara los
productos de compilación; puede regenerarse con el comando documentado.

Error exacto del primer intento, anterior a compilar fuentes:

```text
dependency: org.springframework.boot:spring-boot-actuator:jar:3.5.16 (compile)
Could not transfer artifact org.springframework.boot:spring-boot-actuator:jar:3.5.16 from/to central (https://repo.maven.apache.org/maven2): Connection reset
```

Se reintentó con -U, conservando artefacto y versión. No fue un error de código
ni un fallo por Docker. El reintento compiló correctamente:

```text
--- compiler:3.14.1:compile (default-compile) @ adaptativa-backend ---
Compiling 42 source files with javac [debug parameters release 21] to target\classes
BUILD SUCCESS
```

### Pruebas ejecutadas y pendientes

Surefire compiló los dos archivos de pruebas, pero ejecutó solo CorsConfigurationTest:
acepta localhost5173, acepta127.0.0.1:5173 y rechaza un origen ajeno. El contexto
Spring MVC real arrancó en el slice de prueba con MockMvc; no hubo un servidor
HTTP completo ni conexión a base de datos.

```text
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.410 s -- in com.project.shared.infrastructure.config.CorsConfigurationTest
BUILD SUCCESS
```

Reporte local: `backend/target/surefire-reports/com.project.shared.infrastructure.config.CorsConfigurationTest.txt`.
No existen pruebas unitarias puras de dominio todavía. Quedan pendientes las cuatro
pruebas de integración/PostgreSQL/Testcontainers de `BackendBootstrapIT`:

1. healthIncludesDatabaseCheckWithoutDisclosingDetails.
2. bootstrapMigrationRunsAgainstPostgresqlAndCreatesNoEducationalTables.
3. diagnosticEndpointsAreNotExposed.
4. frontendCanReadHealthThroughActuatorCors.

Dependen de Docker y de la imagen postgres:17-bookworm. Permanecen intactas,
asociadas a Failsafe integration-test/verify. No se ejecutó verify; no se omitieron
mediante flags ni se deshabilitaron. No se añadió H2 ni se desactivó Flyway.
Arranque completo y health siguen pendientes de infraestructura real.

Mockito/Byte Buddy emitieron avisos sobre carga dinámica de agentes en JDK21;
no causaron fallos. Se documentan sin cambiar el POM, dado que este paso no autoriza
ajustes no necesarios. La salida de diagnóstico Spring incluyó detalles de su
autoconfiguración; no implica que las dependencias opcionales no activadas sean errores.

```text
BACKEND BUILD: OK
BACKEND RUNTIME: pendiente de Docker/PostgreSQL
```

Modificaciones: tres archivos Wrapper (incluido hash añadido), PATH solo de usuario,
documentación solicitada, decisiones/inventario e instrucciones README para preferir Wrapper. POM, fuentes,
pruebas, Flyway y PostgreSQL no cambiaron. No se instalaron Node/npm, Git, WSL,
Docker, Eclipse ni MDE. **Paso Maven completado; Fase0 abierta. Detenerse.**
Los apartados siguientes contienen evidencia histórica anterior a esta autorización.

## Instalación autorizada y verificación real de JDK 21

Alcance autorizado: únicamente Eclipse Temurin JDK21 x64, con posible UAC e
interacción del instalador. No se instalaron otros componentes ni una JRE separada.
Antes de instalar, java/javac no estaban en PATH y JAVA_HOME estaba ausente en
Process/User/Machine. No había directorio Eclipse Adoptium detectado.

Comando ejecutado fuera del aislamiento con autorización:

```powershell
winget install --id EclipseAdoptium.Temurin.21.JDK --exact --source winget --version 21.0.12.101 --architecture x64 --scope machine --interactive
```

Salida relevante real:

```text
Found Eclipse Temurin JDK with Hotspot 21 [EclipseAdoptium.Temurin.21.JDK] Version 21.0.12.101
Successfully verified installer hash
Starting package install...
Successfully installed
```

Código final de WinGet: **0**. Instalador descargado:
`OpenJDK21U-jdk_x64_windows_hotspot_21.0.12.1_1.msi`, de la publicación Temurin
`jdk-21.0.12.1+1`. No se solicitó ni ejecutó reinicio de Windows.

### Variables persistentes y nueva instancia PowerShell

Consulta posterior mediante Environment.GetEnvironmentVariable:

```text
ProcessJavaHome: null (terminal que ya estaba abierta)
UserJavaHome: null
MachineJavaHome: C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\
MachineJavaPaths: C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin
```

El instalador dejó los valores persistentes correctos. Para no heredar el entorno
anterior, se cargó JAVA_HOME de usuario o, al estar vacío, de máquina, y se reconstruyó
el PATH del proceso con los valores persistentes de máquina y usuario. Se lanzó
una nueva instancia `powershell.exe -NoProfile -Command ...` desde ese entorno.
Esto no escribió variables de usuario/máquina ni cambió políticas de ejecución.

### Comprobaciones solicitadas, repetidas en la nueva instancia

`java -version`:

```text
openjdk version "21.0.12.1" 2026-08-18 LTS
OpenJDK Runtime Environment Temurin-21.0.12.1+1 (build 21.0.12.1+1-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.12.1+1 (build 21.0.12.1+1-LTS, mixed mode, sharing)
```

`javac -version`:

```text
javac 21.0.12.1
```

`$env:JAVA_HOME`:

```text
C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\
```

`Get-Command java` y `Get-Command javac`, mostrando campos completos:

```text
Name        : java.exe
CommandType : Application
Source      : C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin\java.exe

Name        : javac.exe
CommandType : Application
Source      : C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin\javac.exe
```

`where.exe java`:

```text
C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin\java.exe
```

`where.exe javac`:

```text
C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin\javac.exe
```

Cada where devolvió una sola coincidencia. Se comprobó que ambas rutas son
exactamente JAVA_HOME/bin, que JAVA_HOME existe y que bin figura en PATH.
Se comprobó además el módulo `jmods/jdk.compiler.jmod` y el archivo release:

```text
IMPLEMENTOR="Eclipse Adoptium"
JAVA_RUNTIME_VERSION="21.0.12.1+1-LTS"
JAVA_VERSION="21.0.12.1"
OS_ARCH="x86_64"
```

El bloque final de verificación terminó con código **0**. No hay otra instalación
Java tomando prioridad en el PATH verificado; esta afirmación no pretende
inventariar instalaciones portables fuera de las rutas inspeccionadas.

### Prueba real de compilación y ejecución

Se creó una carpeta temporal de nombre único dentro del workspace y este archivo:

```java
public class JavaEnvironmentTest {
    public static void main(String[] args) {
        System.out.println("Java environment OK");
    }
}
```

Comandos ejecutados en otra instancia PowerShell con el entorno actualizado:

```powershell
javac JavaEnvironmentTest.java
java JavaEnvironmentTest
```

Resultados reales:

```text
javac JavaEnvironmentTest.java: exit 0
Java environment OK
java JavaEnvironmentTest: exit 0
Clase compilada creada: True
Fuente temporal eliminada: True
Clase temporal eliminada: True
Carpeta temporal eliminada: True
```

Solo se eliminaron los dos archivos conocidos de la prueba y su directorio vacío,
con rutas verificadas dentro del workspace y sin borrado recursivo.

### Incidencias y alcance del resultado

La primera validación auxiliar de release produjo un falso fallo al perder
comillas en el paso del bloque a la nueva shell. java/javac ya informaban Java21.
Se corrigió el filtro auxiliar y se repitieron todas las verificaciones en una
nueva instancia, con salida 0; no se cambió ni reinstaló el JDK para resolverlo.
La terminal original también necesitó refrescar su entorno, como estaba previsto.

- [x] java y javac son 21.0.12.1, de la misma instalación x64.
- [x] JAVA_HOME existe, es persistente y apunta a la raíz real del JDK.
- [x] bin del JDK está en PATH y no hay otro Java tomando prioridad.
- [x] Compilación y ejecución reales correctas; temporales eliminados.

**Verificación Java completada. Fase 0 pendiente de los demás componentes.**
No se ejecutaron pruebas del backend ni se instaló Maven. Detenerse hasta nueva
autorización. Los apartados siguientes conservan la evidencia histórica anterior.

## Reinspección solicitada: únicamente Paso 1

No se ejecutaron instalaciones, builds ni pruebas de aplicación en esta
reinspección. Se mantuvieron arquitectura, código, PATH, JAVA_HOME, políticas
PowerShell y características de Windows. Solo se actualizaron documentos.

| Comando ejecutado / consulta real | Resultado observado |
| --- | --- |
| `Get-Command winget,choco,scoop,powershell,pwsh,git,java,javac,mvn,node,npm,npm.cmd,docker,docker-compose -ErrorAction SilentlyContinue` (consultados individualmente) | Solo winget y Windows PowerShell encontrados entre estos nombres |
| `$PSVersionTable` | PSVersion 5.1.26100.9444, PSEdition Desktop |
| `Get-ExecutionPolicy -List` y `Get-ExecutionPolicy` | Todos los ámbitos Undefined; política efectiva Restricted |
| `winget --version` dentro del aislamiento | Sin salida; código -1978335231. `--info` no llegó a ejecutarse por el control de error |
| `winget --version` repetido fuera del aislamiento con autorización | `v1.29.290`, salida 0; gestor ejecutable |
| `wsl.exe --version` | Salida 1: «El Subsistema de Windows para Linux no está instalado». No se ejecutó la instalación sugerida por el mensaje |
| `Test-Path` de wrappers en raíz/backend | mvnw, mvnw.cmd y .mvn ausentes en ambas ubicaciones |
| `Test-Path .git` | False; repositorio no inicializado |
| Consulta JAVA_HOME mediante Environment.GetEnvironmentVariable en Process/User/Machine | Ausente en los tres ámbitos |
| Revisión de ubicaciones habituales y registro de instalación | No se encontraron Git/JDK/Maven/Node/Docker/Chocolatey/Scoop instalados en los lugares revisados |
| Lectura frontend/package.json | engines.node: >=24.15.0 <25; existen scripts build y test |
| Lectura backend/pom.xml | java.version=21; no se cambió el POM |
| Registro de versión Windows y RuntimeInformation | Build 26200.9457, DisplayVersion 25H2, arquitectura x64; no se comprobó virtualización/RAM |

Consultas adicionales de solo lectura para concretar la propuesta, fuera del
aislamiento y sin aceptar acuerdos automáticamente:

```powershell
winget show --id Git.Git --exact --source winget --disable-interactivity
winget show --id Docker.DockerDesktop --exact --source winget --disable-interactivity
winget show --id EclipseAdoptium.Temurin.21.JDK --exact --source winget --version 21.0.12.101 --architecture x64 --scope machine --disable-interactivity
winget show --id OpenJS.NodeJS.LTS --exact --source winget --version 24.19.0 --architecture x64 --installer-type wix --scope machine --disable-interactivity
winget show --id Git.Git --exact --source winget --version 2.55.0.3 --architecture x64 --scope machine --disable-interactivity
winget show --id Docker.DockerDesktop --exact --source winget --version 4.91.0 --architecture x64 --scope machine --disable-interactivity
```

Resultados: Temurin paquete 21.0.12.101 / JDK21.0.12.1+1 (wix), Node24.19.0 (wix),
Git2.55.0.3 (inno), DockerDesktop4.91.0 (exe). Se resolvieron instaladores x64
para alcance machine. SHA256 informados por el catálogo, sin descargar binarios:

| Instalador | SHA256 anunciado |
| --- | --- |
| Temurin JDK21 | 454cfd334b9ca91c96dd8c2de97fcef6b9f1f98be9172ff076711f1c6b44e4e0 |
| Node24.19.0 | f0f66c2a80c08a30a5ab5179ee9ea9e45f9b46289436a8cc87ff833b852db351 |
| Git2.55.0.3 | af12577d0fdff74243a5988197aa49b957d5044edc17004f6ddf0768996f1dca |
| DockerDesktop4.91.0 | ac405b09942701770d581b173747fc1024cf0e6047cbe60f13d1df85437311ac |

Estos hashes son metadatos consultados, no hashes calculados de descargas locales.
Las consultas de catálogo no son instalaciones ni pruebas de las herramientas.
Maven3.9.16 se contrastó con la página oficial Apache; no se descargó su ZIP.

`java -version`, `javac -version`, `mvn -version`, `node --version`, `npm --version`,
`git --version`, `docker --version`, `docker compose version`, builds, tests,
Compose/PostgreSQL y health permanecen pendientes: sus ejecutables no existen en
PATH en esta sesión. No se inventan salidas para comandos no ejecutados.

## Comandos ejecutados durante la preparación

| Comando/operación | Resultado |
| --- | --- |
| `Get-ChildItem -Force -Recurse` en la raíz inicial | 0 archivos/carpetas, incluidos ocultos; raíz vacía |
| `rg --files -g AGENTS.md -g '!node_modules' -g '!.git'` | Sin coincidencias; salida 1 por búsqueda vacía |
| `Get-Content -Encoding UTF8` sobre solicitud y SVG | Solicitud leída completa; SVG analizado como XML y texto del marco |
| `Get-Command` para java, javac, mvn, node, npm, docker, git, eclipse | No disponibles en PATH |
| Consulta de ubicaciones habituales/registro y variables JAVA_HOME/MAVEN_HOME/M2_HOME | No se encontraron instalaciones/configuración correspondientes; variables vacías |
| `python --version` | Python 3.14.4 |
| `rg --version` | ripgrep 15.2.0 |
| Consultas de páginas oficiales y metadatos npm | Versiones de manifests y riesgos de compatibilidad contrastados; fuentes en README de módulos/plan |
| `apply_patch` | Creación de estructura, configuración, pruebas y documentos |
| `Copy-Item` del SVG a docs/referencias y `Get-FileHash -Algorithm SHA256` | Copia de referencia; original conservado |
| `ConvertFrom-Json` sobre package.json y tsconfigs | Parseo correcto |
| Parseo XML de backend/pom.xml en PowerShell | Correcto; no equivale a validación Maven |
| `powershell -NoProfile -File scripts/check-environment.ps1` | Bloqueado por política de ejecución de scripts; salida 1. No se cambió la política. |
| `python scripts/check-scaffold.py` | Revisión final correcta: 3 JSON, 2 XML/SVG, 118 enlaces locales, 44 correspondencias package/ruta y hash SVG |
| `System.Management.Automation.Language.Parser.ParseFile` sobre el comprobador `.ps1` | Sintaxis correcta sin ejecutar el archivo ni cambiar política |
| `rg` sobre eval, new Function, dangerouslySetInnerHTML y marcadores de pendientes en fuentes | Sin coincidencias; revisión textual, no auditoría completa |

La primera lectura del texto usó codificación predeterminada y mostró tildes
incorrectas; se repitió con UTF8 antes de trabajar. Una lectura de una ruta supuesta
de configuración Java no encontró el archivo; se enumeraron las rutas reales y se
revisó `shared/infrastructure/config/CorsConfiguration.java`. No son fallos de
pruebas de la aplicación.

Las consultas HTTPS directas al registro npm requirieron reintento autorizado
por restricciones de red; solo leyeron metadatos, sin instalar dependencias.

## Revisión estática final

Ejecutar desde la raíz `python scripts/check-scaffold.py`. El resultado de parseo
JSON/XML, enlaces Markdown locales, correspondencia de paquetes Java y hash del
SVG se escribe en [verificacion-estatica.json](verificacion-estatica.json).
Comprueba sintaxis/consistencia limitada, no semántica MDE ni runtime. Este script
usa Python disponible y no instala dependencias.

Resultado final: **correcto, sin fallos estáticos detectados**. La primera pasada
encontró enlaces al propio informe todavía no generado; se corrigió el comprobador
para reconocer su archivo de salida y se repitió con resultado satisfactorio.
El hash de la copia SVG coincide con el original:
`fcb04a1bf5bbd8bf1b5bb4ca4a40bf931f3f997918bf245a16208e9b5b23f6c9`.

## Histórico inicial: pruebas preparadas pero no ejecutadas el 20/09/2026

Esta tabla conserva el diagnóstico anterior a las instalaciones autorizadas.
Los resultados actuales de Java, Maven y frontend figuran en las secciones superiores.

| Comando pendiente | Qué debe demostrar | Impedimento actual |
| --- | --- | --- |
| `npm.cmd install` | Resolución real y package-lock | Node/npm ausentes |
| `npm.cmd run build` | TypeScript + bundle Vite | Node/npm ausentes |
| `npm.cmd test` | 2 casos de componentes/rutas | Node/npm ausentes |
| `npm.cmd run test:e2e:install` | Navegador Chromium de Playwright | Node/npm ausentes |
| `npm.cmd run test:e2e` | Smoke de frontend compilado y navegación | Node/npm/Chromium ausentes |
| `mvn verify` | Compilación, pruebas MVC, empaquetado y 4 IT PostgreSQL | JDK/Maven/Docker ausentes |
| `docker compose config --quiet` | Validación Compose e interpolación | Docker ausente; .env local por preparar |
| `docker compose up -d --wait postgres` | Arranque y health de DB | Docker ausente |
| `docker compose exec -T postgres psql -U adaptativa -d adaptativa -c "SELECT 1;"` | Consulta contra DB real | Docker ausente |
| `mvn spring-boot:run` | Inicio backend con DB | JDK/Maven/Docker ausentes |
| `Invoke-RestMethod http://localhost:8080/actuator/health` | Health real del backend y DB | Backend no iniciado |
| `npm.cmd run dev` | Inicio frontend | Node/npm ausentes |

No se ejecutaron comandos de build deliberadamente sabiendo que faltaban sus
ejecutables. No se instalaron herramientas del sistema ni se generó un lock o
digest ficticio. El Compose fue revisado como texto; no validado por Docker.

## Resultado de aceptación actualizado al 23/09/2026

**Fase 0 todavía no puede declararse completa.** Java, Maven/Wrapper y Node/npm
están verificados. Backend y frontend compilan; pasan 3 pruebas MVC y 2 unitarias
frontend. Vite respondió HTTP200 y quedó detenido. El escenario E2E Chromium
también pasó con salida0 y sus procesos terminaron. Maven verify pasó con salida 0:
3 pruebas Surefire y 4 BackendBootstrapIT correctas, sin fallos/errores/omitidas.
Arranque manual del backend también validado contra PostgreSQL Compose:
health UP, CORS correcto, /actuator/env cerrado y Flyway V1 aplicada a adaptativa.
PostgreSQL Compose fue validado: healthy, SELECT 1 = 1, DB/user adaptativa,
inicialmente sin tablas public; tras runtime contiene solo flyway_schema_history.
Spring y PostgreSQL detenidos al finalizar, volumen conservado y puerto 8080 libre.
Maven verify y runtime manual validados. Eclipse Modeling Tools 2026-09 R base
también validado: JustJ 25 embebido, Java 21 global intacto, EMF/Ecore/Ecore Tools
presentes y cierre correcto. OCL incluido pero no utilizado; Xtext SDK completo,
ATL y Acceleo 4 ausentes. Este paso solo autoriza cerrar los tres documentos en
Git y publicarlos; después esperar autorización para Xtext SDK. No avanzar a Fase 1.
