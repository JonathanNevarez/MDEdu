# Herramientas y preparación del entorno Windows

## Estado actual: Docker Desktop instalado y validado — 22/09/2026

**Docker Desktop4.91.0**, build239619 (ejecutable4.91.0.239619), instalado per-user
en `C:\Users\alexxxjon\AppData\Local\Programs\DockerDesktop` y registrado en HKCU.
Instalador oficial x64, 628014512 bytes; SHA256 esperado y calculado coinciden:
`ac405b09942701770d581b173747fc1024cf0e6047cbe60f13d1df85437311ac`.
Comando: instalador con `install --user --backend=wsl-2`, código0, como usuario
normal y sin UAC observado. No se usaron --accept-license ni --always-run-service.
La pantalla Docker Subscription Service Agreement se dejó al usuario, que confirmó
su aceptación manual. No se inició sesión ni se creó cuenta por el asistente.

PowerShell nueva con PATH persistente refrescado: docker.exe en
`C:\Users\alexxxjon\AppData\Local\Programs\DockerDesktop\resources\bin\docker.exe`.
CLI/Engine29.8.0, API1.56, containerd v2.3.4, runc1.4.3, docker-init0.19.0,
Compose v5.5.1 y Buildx v0.37.0. Contexto desktop-linux, motor Linux/WSL2.
OSType linux, arquitectura x86_64, 16 CPU y 7.412 GiB de RAM visibles para Docker
(7958327296 bytes). Storage overlayfs, cgroupfs/v2, kernel
6.18.33.2-microsoft-standard-WSL2, raíz /var/lib/docker. No se alteraron estos valores.

`docker run --rm hello-world` descargó la imagen oficial linux/amd64 y terminó
con «Hello from Docker!», código0. Digest:
`sha256:5e23090353324d887c48ad5e5c56d294eab81588df9605b07d1afe895f9cc8f8`.
docker ps -a vacío; imagen conservada. WSL2.7.14.0 sigue operativo; únicamente
docker-desktop Running versión2, componente interno, sin distribución personal.

Los tres indicadores de reinicio están inactivos. VMP habilitada; WSL1, Hyper-V
completo y HypervisorPlatform deshabilitados. com.docker.service ausente, sin
considerarlo fallo del modo per-user. Docker Desktop/backend quedan activos.
**DOCKER VALIDADO.** PostgreSQL, Compose del proyecto y BackendBootstrapIT no
ejecutados. Solo cambian estos tres documentos; sin staging, commit o push.

## Histórico: WSL2 instalado y validado — 22/09/2026

Validación posterior al reinicio manual satisfactoria, sin instalar ni actualizar
nada. PendingFileRenameOperations, CBS/RebootPending y Windows Update/RebootRequired
están inactivos. `wsl --version` y `wsl --status` terminaron con código0:
WSL **2.7.14.0**, kernel **6.18.33.2-2**, versión predeterminada **2**.
WSLg1.0.73.2, MSRDC1.2.7214, Direct3D1.611.1-81528511,
DXCore10.0.26100.1-240331-1435.ge-release y Windows10.0.26200.9457.

Get-WindowsOptionalFeature, con UAC exclusivamente de lectura, confirmó:
VirtualMachinePlatform **Enabled**; Microsoft-Windows-Subsystem-Linux,
Microsoft-Hyper-V-All y HypervisorPlatform **Disabled**. No se cambiaron estados.
El aviso de wsl --status sobre WSL1 se conserva: WSL1 está deshabilitado por diseño.

`wsl --list --verbose` informa que no hay distribuciones instaladas; código-1
aceptado como lista vacía por el alcance solicitado. Ninguna distribución personal.
Virtualización firmware y DEP disponibles; HypervisorPresent=true, corroborado
por systeminfo. Los campos CIM SLAT/VMMonitor aparecen false con el hipervisor
activo, mientras Get-ComputerInfo no publica esos requisitos; la comprobación
previa al hipervisor registró ambos true. No hay error de kernel/virtualización.

Primera consulta WSL al servicio denegada por aislamiento; repetir en el entorno
normal resolvió E_ACCESSDENIED, sin cambiar permisos o configuración. Se corrigió
la lectura UTF16 de las salidas. No se ejecutaron comandos Linux, wsl --update,
instalaciones, habilitaciones o Docker. Solo se actualizan los tres documentos
autorizados; no se hace staging ni commit. **WSL2 VALIDADO; detenerse.**

## Histórico: WSL instalado; reinicio requerido antes de validación — 22/09/2026

Después del reinicio manual del usuario, PendingFileRenameOperations,
CBS/RebootPending y Windows Update/RebootRequired estaban inactivos. Windows11
Pro x64, virtualización de firmware y SLAT disponibles; 8.39 GiB de RAM libre y
564.22 GiB libres en C:. Git limpio en main con 41315c5 y 8262ec1, sin remotos.

Se ejecutó únicamente `wsl --install --no-distribution` en PowerShell elevado
mediante RunAs/UAC, sin cambiar ExecutionPolicy. El proceso confirmó permisos
de administrador y terminó con código0 el 22/09/2026 a las09:03:14 (-05:00).
El instalador informó **Subsistema de Windows para Linux 2.7.14 instalado** y
habilitación de VirtualMachinePlatform. **REINICIO REQUERIDO**: los cambios se
aplican después del reinicio; CBS/RebootPending está ahora activo.

Consulta CIM posterior: VirtualMachinePlatform habilitada (InstallState1),
Microsoft-Windows-Subsystem-Linux, Hyper-V completo y HypervisorPlatform
deshabilitados (InstallState2). No se habilitó WSL1 ni se instaló distribución
Linux personal. WSL2 aún no se declara operativo antes de reiniciar.

No se ejecutaron wsl --update ni validaciones operativas posteriores. Tras el
reinicio manual quedarán pendientes wsl --version, --status, --list --verbose
y consultas de características elevadas; versión del kernel aún no comprobada.
No se descargó ni instaló Docker u otra herramienta. Solo se actualizan los tres
documentos autorizados, sin staging ni commit. **Detenerse para reinicio manual.**

## Histórico: Playwright Chromium y E2E verificados — 22/09/2026

Playwright **1.63.0**, ya declarado e instalado como dependencia, ejecutó
`npm.cmd run test:e2e:install` (playwright install chromium), código0.
Se instalaron exclusivamente Chrome for Testing y Headless Shell **153.0.8010.12**,
revisión **1243**, más FFmpeg1011 y Winldd1007 en
`C:\Users\alexxxjon\AppData\Local\ms-playwright`.
Tamaño de archivos instalado: **739893945 bytes**, aproximadamente740 MB / 705.62 MiB.
No se instalaron Firefox, WebKit, Chrome global ni herramientas adicionales.
Se mantiene npm.cmd sin modificar ExecutionPolicy.

`npm.cmd run test:e2e`: TypeScript y Vite8.3.0 compilaron; Playwright ejecutó
1 archivo/escenario con Chromium, **1 aprobado, 0 fallidos, 0 omitidos**, salida0.
Duración Playwright: 2.5s; escenario: 546ms; build Vite: 738ms.
La prueba comprobó React, título, contenido, ruta inexistente y regreso al inicio;
el arreglo de pageerror permaneció vacío. No captura console.error por separado.
El preview temporal en 127.0.0.1:4173 y el navegador terminaron; procesos0,
listeners0 en 4173/5173. Solo aviso menor NO_COLOR/FORCE_COLOR en procesos Node.

FRONTEND BUILD: **OK**. FRONTEND UNIT: **OK** (verificación previa, no repetida).
FRONTEND HTTP: **OK** (verificación previa). FRONTEND E2E CHROMIUM: **OK**.
test-results/.last-run.json registra passed y está ignorado; playwright-report no
se creó porque se usa reporter list. No hubo cambios en código, pruebas o versiones.

Snapshot inicial **8262ec1** intacto. En este paso solo se modifican
HERRAMIENTAS.md, VERIFICACION_FASE_0.md y ESTADO_PROYECTO.md, sin staging/commit.
**Detenerse hasta nueva autorización; Fase0 sigue abierta.**

## Histórico: snapshot local autorizado — 21/09/2026

Git instalado y verificado según el registro inferior. Se configuró exclusivamente
la identidad local JonathanNevarez <neva_rez00@hotmail.com> y se renombró la rama
a main. Los 98 archivos revisados se preparan para el primer commit autorizado.
No se modifica configuración global ni se conecta remoto. El hash y comprobación
de estado limpio posteriores al commit se registran en .git/FASE_0_SNAPSHOT.txt
y en la respuesta al usuario, sin editar de nuevo el árbol versionado.

## Histórico: Git instalado y repositorio local preparado — 21/09/2026

Git for Windows instalado mediante WinGet `Git.Git`, paquete **2.55.0.3 x64**.
`git --version`: **git version 2.55.0.windows.3**. `where.exe git` y Get-Command
resuelven únicamente `C:\Program Files\Git\cmd\git.exe`; build-options confirma
cpu x86_64. Se refrescó PATH desde sus valores persistentes y se verificó en una
nueva PowerShell; no se editó PATH manualmente.

Configuración global consultada: `user.name`, `user.email` y `core.autocrlf`
**AUSENTES** (sin salida, código1). No se configuró identidad, autocrlf,
credential.helper ni defaultBranch. El paquete oficial incluye sus componentes
habituales; no se instalaron gestores de credenciales, clientes ni SSH por separado.

`git init` creó el repositorio local con rama inicial `master`, elegida por Git
sin cambiar la configuración. No hay índice/staging, commits ni remotos.
Los **98 archivos candidatos** están enumerados en [PRIMER_COMMIT](PRIMER_COMMIT.md).
Falta revisión/autorización del usuario e identidad antes de hacer el primer commit.

`.gitignore` excluye node_modules, dist, target, resultados/cachés de pruebas,
logs, .env y variantes salvo .env.example, .idea, .metadata, temporales y archivos
del sistema. Se retiró la exclusión global .vscode; esa carpeta no existe hoy.
No se ignora mde/generated: fuentes MDE se versionarán y la política de artefactos
generados se resolverá en Fase1 según EMF/Xtext/Tycho, como explica
[mde/generated](../mde/generated/README.md).

`.gitattributes` usa `* text=auto`, LF para .sh/mvnw y CRLF para .cmd.
No se reescribieron los wrappers: sintaxis de mvnw correcta y mvnw.cmd -version
devuelve Maven3.9.16 sobre Java21.0.12.1. Revisión básica sin secretos detectados;
.env.example tiene DB_PASSWORD vacío y las configuraciones reciben la variable.

**Paso Git completado; detenerse.** No hacer commit, instalar otros componentes
ni avanzar a Fase1. Ver salidas en [VERIFICACION_FASE_0](VERIFICACION_FASE_0.md).

## Estado anterior: Node/npm y frontend verificados — 21/09/2026

Instalación autorizada exclusivamente de **Node.js 24.19.0 LTS x64** y **npm
11.17.0**, mediante WinGet `OpenJS.NodeJS.LTS`, MSI oficial de nodejs.org.
Ruta común: `C:\Program Files\nodejs`. Node cumple `>=24.15.0 <25`.
El instalador configuró PATH; no se modificó manualmente. Se refrescó el entorno
del proceso y se comprobó desde una nueva instancia PowerShell.

`where.exe node` devuelve `C:\Program Files\nodejs\node.exe`.
`where.exe npm` devuelve `...\npm` y `...\npm.cmd`, ambos de la misma instalación.
Get-Command npm resuelve `...\npm.ps1`, bloqueado por la política Restricted.
Usar **npm.cmd** en PowerShell; funciona sin cambiar políticas. También se verificó
`npm --version` mediante cmd.exe: 11.17.0. No hay otra versión prioritaria en PATH.

Resultados reales:

- `npm.cmd install`: 114 paquetes añadidos, 115 auditados, 0 vulnerabilidades
  reportadas; generó `frontend/package-lock.json` v3 sin alterar package.json.
- `npm.cmd run build`: TypeScript y Vite 8.3.0 correctos; bundle generado en dist.
- `npm.cmd run test`: 1 archivo, 2 pruebas aprobadas, 0 fallidas/omitidas.
- Vite en `http://127.0.0.1:5173/`: HTTP200, HTML, título y elemento raíz confirmados;
  módulos principales también HTTP200. Servidor detenido y puerto libre comprobado.
- Revisión básica de secretos: ninguno detectado en src/configuración; no existen
  archivos `.env*` en frontend.

El build inicial encontró `spawn EPERM` dentro del aislamiento; repetir fuera de
él solucionó el bloqueo sin cambios de código. npm anunció una versión nueva
12.0.2 y financiación de paquetes; no se hicieron upgrades ni audit fix.

Desde frontend, para reinstalaciones posteriores con el lock existente:

```powershell
npm.cmd ci
npm.cmd run build
npm.cmd run test
npm.cmd run dev
```

La instalación realizada en este paso fue `npm.cmd install`, no ci. La evidencia
detallada se encuentra en [VERIFICACION_FASE_0](VERIFICACION_FASE_0.md).

**Playwright/E2E: pendiente de autorización.** El paquete de pruebas declarado se
resolvió con npm; no se descargó Chromium. El script `test:e2e:install` ejecuta
`playwright install chromium`. Su dry-run identifica Chrome for Testing y Headless
Shell 153.0.8010.12, FFmpeg y Winldd. Las cabeceras HTTP HEAD suman 326864890 bytes
(aprox. 327 MB de descarga; el espacio extraído será mayor). No existe caché local
ms-playwright. Comandos futuros, NO ejecutados:

```powershell
npm.cmd run test:e2e:install
npm.cmd run test:e2e
```

Paso Node/npm completado. **Detenerse:** no instalar Git, WSL, Docker, Eclipse/MDE
ni navegadores sin nueva autorización. La Fase 0 continúa abierta.

## Estado anterior: Maven y Wrapper verificados con Java 21

Se completó exclusivamente la autorización de Maven. Instalación de usuario:
`C:\Users\alexxxjon\AppData\Local\Programs\Apache\Maven\apache-maven-3.9.16`.
Versión: **Apache Maven 3.9.16**, Java **21.0.12.1** de Eclipse Adoptium,
Windows11/amd64. El ZIP binario se descargó de Apache y coincidió con el SHA-512
oficial antes de extraerlo. No se requirió instalar herramientas adicionales.

Solo se añadió la carpeta `bin` de Maven al PATH del usuario actual. JAVA_HOME y
PATH de máquina permanecieron intactos. Una nueva instancia PowerShell con las
variables persistentes refrescadas confirmó `mvn -version`, `where.exe mvn` y
`Get-Command mvn`. Las dos coincidencias de where son los archivos `mvn` y
`mvn.cmd` del mismo directorio; PowerShell resuelve `mvn.cmd`. No son dos
instalaciones compitiendo en PATH.

El backend incluye ahora Wrapper **3.3.4**, tipo `only-script`, con Maven **3.9.16**
y SHA-256 de distribución fijado. Archivos: `backend/mvnw`, `backend/mvnw.cmd` y
`backend/.mvn/wrapper/maven-wrapper.properties`. No requiere un JAR de Wrapper.
La distribución del Wrapper se almacena en `.m2/wrapper/dists` del usuario;
esa caché no se añade al PATH y no depende del Maven instalado globalmente.

**Comandos normales desde backend: usar Wrapper**, no `mvn`:

```powershell
.\mvnw.cmd -version
.\mvnw.cmd help:effective-pom
.\mvnw.cmd validate
.\mvnw.cmd clean compile
.\mvnw.cmd test
```

Los cinco pasos se verificaron realmente. Compilación: 42 fuentes con release21.
Pruebas MVC: 3, sin fallos, errores ni omisiones. `verify`, el arranque completo,
Flyway y las 4 pruebas de BackendBootstrapIT siguen pendientes de Docker/PostgreSQL.
El corte de red al descargar Actuator se resolvió reintentando la misma versión;
no se cambió el POM ni código de aplicación. Ver evidencia completa en
[VERIFICACION_FASE_0](VERIFICACION_FASE_0.md).

Estado de esta autorización: **Maven completado; detenerse**. No se instalaron
Node/npm, Git, WSL, Docker, Eclipse ni componentes MDE. Fase 0 continúa abierta.
Las secciones siguientes conservan estados y propuestas anteriores.

## Estado anterior: JDK 21 instalado y verificado

El usuario autorizó exclusivamente Eclipse Temurin JDK21 x64. WinGet terminó
con `Successfully installed` y código 0 para el paquete `21.0.12.101`.
No se instaló una JRE separada ni otra versión de Java. No se instalaron Maven,
Node, Git, WSL, Docker, Eclipse ni componentes MDE.

| Componente / variable | Resultado real |
| --- | --- |
| java | OpenJDK 21.0.12.1 LTS, Temurin-21.0.12.1+1 |
| javac | 21.0.12.1 |
| Arquitectura | x86_64; VM de 64 bits |
| JAVA_HOME persistente, ámbito máquina | `C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\` |
| java.exe | `C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin\java.exe` |
| javac.exe | `C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin\javac.exe` |
| PATH persistente | Incluye `C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\bin` |
| Prueba de compilación/ejecución | javac y java terminaron con código 0; salida `Java environment OK` |

El instalador configuró JAVA_HOME y PATH. JAVA_HOME apunta a la raíz real del
JDK, no a bin; existe `jmods/jdk.compiler.jmod`. No hubo cambios manuales de
variables globales. El proceso de terminal anterior conservaba el entorno previo:
se refrescaron solo sus variables de proceso desde los valores persistentes de
Windows y se abrió una nueva instancia PowerShell `-NoProfile`. Allí se repitieron
todas las comprobaciones pedidas, con resultado satisfactorio.

`where.exe` devolvió exactamente una ruta para cada ejecutable, coincidente con
JAVA_HOME y Get-Command. No se encontró otra instalación Java tomando prioridad
en ese PATH. El archivo `.java`, el `.class` y su carpeta temporal fueron eliminados.

Estado de esta autorización: **Java completado; detenerse**. Maven y todos los
demás componentes permanecen pendientes de autorización. La Fase 0 no está cerrada.
La evidencia completa está en [VERIFICACION_FASE_0](VERIFICACION_FASE_0.md).

## Diagnóstico anterior — Paso 1, antes de instalar Java

Reinspección del 20/09/2026 solicitada para cerrar F0 mediante evidencia real.
Solo se ejecutaron consultas de diagnóstico y del catálogo; ningún instalador,
descarga de toolchain, cambio de PATH/JAVA_HOME, habilitación WSL o cambio de
política de ejecución. Las instalaciones siguientes son propuestas pendientes.

| Herramienta | Estado actual | Versión encontrada | Requerida / propuesta | Acción |
| --- | --- | --- | --- | --- |
| WinGet | Ejecutable funcional fuera del aislamiento | 1.29.290 | Gestor disponible | Utilizar tras autorización |
| Chocolatey | No encontrado | — | Opcional | No instalar otro gestor |
| Scoop | No encontrado | — | Opcional | No instalar otro gestor |
| Windows PowerShell | Disponible | 5.1.26100.9444 | Suficiente para diagnóstico | Conservar |
| PowerShell 7 | No encontrado (`pwsh`) | — | No obligatorio | Ninguna |
| Git | No encontrado | — | 2.55.0.3 disponible en catálogo | Instalar; inicializar repo después |
| Java / javac | No encontrados | — | JDK 21; Temurin 21.0.12.1+1 propuesto | Instalar y verificar ambos binarios |
| Maven / Wrapper | No encontrados | — | Maven 3.9.16 propuesto | ZIP de usuario; después Wrapper |
| Node | No encontrado | — | Node 24.19.0 propuesto; proyecto >=24.15.0 <25 | Instalar LTS compatible |
| npm | No encontrado | — | Incluido con Node; versión por verificar | Instalar junto a Node |
| Docker | No encontrado | — | Desktop 4.91.0 disponible en catálogo | Preparar WSL e instalar |
| Docker Compose | No disponible; tampoco docker-compose independiente | — | Plugin incluido en Docker Desktop | Verificar después |
| WSL | `wsl.exe` existe, pero informa que WSL no está instalado | Sin versión | WSL2 compatible con Docker | Instalación separada con autorización |

JAVA_HOME está ausente en proceso, usuario y máquina. No existen `mvnw`, `mvnw.cmd`
ni `.mvn` en la raíz o backend; tampoco `.git`. La política efectiva PowerShell
es `Restricted`; se conservará y se usará `npm.cmd` cuando Node esté instalado.
Arquitectura de Windows/proceso: x64. Build de Windows leído: 26200.9457, 25H2.

WinGet devolvió inicialmente -1978335231 dentro del aislamiento; la repetición
autorizada de `winget --version` fuera de él devolvió `v1.29.290`, salida 0.
Las consultas `winget show` también se realizaron fuera del aislamiento. Esto
no demuestra que las aplicaciones consultadas estén instaladas.

## Propuesta concreta para revisar antes de instalar

Preferencia: WinGet para JDK/Node/Git/Docker, con versiones comprobadas en el
catálogo local. No instalar Chocolatey/Scoop. Para Maven, ZIP oficial en carpeta
de usuario, hash SHA-512 verificado, sin modificar PATH global; después generar
Wrapper para que el proyecto fije la versión de Maven.

| Herramienta y versión | Motivo | Administrador en modalidad propuesta | Reinicio previsto |
| --- | --- | --- | --- |
| Temurin JDK 21.0.12.1+1 (paquete 21.0.12.101) | Compilar/ejecutar backend Java21 | Sí: instalación de máquina, UAC | Normalmente no; reabrir terminal para PATH/JAVA_HOME |
| Maven 3.9.16, ZIP usuario | Construir backend y generar Wrapper | No; escribir en perfil requiere autorización del entorno | No |
| Node 24.19.0 LTS + npm incluido | Construir/probar frontend; cumple engines | Sí: MSI de máquina, UAC | Normalmente no; reabrir terminal |
| Git 2.55.0.3 | Versionar e inicializar repositorio | Sí en modalidad machine propuesta | Normalmente no; reabrir terminal |
| WSL2 | Motor Linux de Docker | Sí para habilitar características | Puede requerir reiniciar Windows |
| Docker Desktop 4.91.0 + Compose | PostgreSQL y Testcontainers | Sí en modalidad machine propuesta; interacción inicial | Posible por WSL/virtualización/características Windows |

No reiniciar automáticamente. Los instaladores pueden solicitar acciones
adicionales: comunicar y detenerse antes de aceptar reinicios o cambios no
contemplados. Revisar RAM y virtualización antes de Docker; no se verificaron
en este diagnóstico. La [guía de Docker](https://docs.docker.com/desktop/setup/install/windows-install/)
describe requisitos y modalidades por usuario/máquina; aquí se propone la modalidad
de máquina que el catálogo local pudo resolver, sin prometer instalación sin UAC.

**Comandos propuestos, NO ejecutados.** Se ejecutarán por separado, con
verificación entre pasos y solo después de la autorización correspondiente:

```powershell
winget install --id EclipseAdoptium.Temurin.21.JDK --exact --source winget --version 21.0.12.101 --architecture x64 --scope machine --interactive
winget install --id OpenJS.NodeJS.LTS --exact --source winget --version 24.19.0 --architecture x64 --installer-type wix --scope machine --interactive
winget install --id Git.Git --exact --source winget --version 2.55.0.3 --architecture x64 --scope machine --interactive
```

Java va primero. Después comprobar `java -version`, `javac -version` y
`$env:JAVA_HOME`; no seguir si no utiliza JDK21. Maven se prepara a continuación.
Node y Git solo se instalan al llegar a su paso autorizado. Los comandos
`--interactive` pueden abrir una interfaz gráfica, comunicada en esta propuesta.

Maven, después de validar Java y autorizar su descarga/escritura en el perfil:

```powershell
$mavenSetupRoot = Join-Path $env:LOCALAPPDATA 'Programs\Apache\Maven'
$mavenVersionRoot = Join-Path $mavenSetupRoot 'apache-maven-3.9.16'
if (Test-Path -LiteralPath $mavenVersionRoot) { throw 'La carpeta destino ya existe; revisar antes de continuar.' }
New-Item -ItemType Directory -Path $mavenSetupRoot -Force | Out-Null
$mavenZipPath = Join-Path $mavenSetupRoot 'apache-maven-3.9.16-bin.zip'
$mavenHashPath = "$mavenZipPath.sha512"
if ((Test-Path -LiteralPath $mavenZipPath) -or (Test-Path -LiteralPath $mavenHashPath)) { throw 'Ya hay archivos de descarga; revisar antes de continuar.' }
Invoke-WebRequest -UseBasicParsing -Uri 'https://dlcdn.apache.org/maven/maven-3/3.9.16/binaries/apache-maven-3.9.16-bin.zip' -OutFile $mavenZipPath
Invoke-WebRequest -UseBasicParsing -Uri 'https://downloads.apache.org/maven/maven-3/3.9.16/binaries/apache-maven-3.9.16-bin.zip.sha512' -OutFile $mavenHashPath
$mavenExpectedHash = ((Get-Content -LiteralPath $mavenHashPath -Raw).Trim() -split '\s+')[0]
if ($mavenExpectedHash -notmatch '^[a-fA-F0-9]{128}$') { throw 'Formato de SHA-512 no reconocido.' }
if ((Get-FileHash -LiteralPath $mavenZipPath -Algorithm SHA512).Hash -ne $mavenExpectedHash) { throw 'SHA-512 no coincide; no extraer.' }
Expand-Archive -LiteralPath $mavenZipPath -DestinationPath $mavenSetupRoot
& (Join-Path $mavenVersionRoot 'bin\mvn.cmd') -version
```

La última salida deberá identificar Java21. Luego, desde `backend/`, proponer
generación del [Maven Wrapper oficial](https://maven.apache.org/tools/wrapper/):

```powershell
& (Join-Path $mavenVersionRoot 'bin\mvn.cmd') wrapper:wrapper '-Dmaven=3.9.16' '-Dtype=only-script'
.\mvnw.cmd -version
```

Este bloque creará archivos del proyecto; todavía no se ha ejecutado. Fuente de
versión y descargas: [Apache Maven](https://maven.apache.org/download.cgi).

WSL/Docker constituyen otro paso con aprobación explícita, dada la posibilidad
de cambios de Windows, reinicio e interacción gráfica:

```powershell
wsl --install --no-distribution
```

Esperar resultado y, si lo solicita, coordinar el reinicio; después verificar
WSL/virtualización antes de instalar Docker. No ejecutar ambos bloques a ciegas.
La opción evita añadir una distribución de usuario innecesaria.
[Comandos oficiales WSL](https://learn.microsoft.com/en-us/windows/wsl/basic-commands).

```powershell
winget install --id Docker.DockerDesktop --exact --source winget --version 4.91.0 --architecture x64 --scope machine --interactive
```

Después de la configuración inicial de Docker verificar CLI, Compose y motor
con `docker --version`, `docker compose version` y `docker info`. Eclipse/MDE
queda para su propuesta independiente, sin instalaciones ni metamodelos todavía.

Las consultas de catálogo son evidencia de disponibilidad del instalador, no de
compatibilidad runtime de las aplicaciones. Las instrucciones generales originales
que siguen se conservan como referencia; esta propuesta concreta tiene prioridad.

Inspección realizada el 20/09/2026, en PowerShell. No se instalaron runtimes,
paquetes npm/Maven, imágenes Docker ni plugins Eclipse.

| Herramienta | Resultado de inspección | Requisito del proyecto |
| --- | --- | --- |
| java / javac | No disponibles en PATH; no encontrados en ubicaciones/registro revisados | JDK 21 completo |
| mvn | No disponible; sin MAVEN_HOME/M2_HOME ni .m2 en ruta habitual | Maven 3.9.x recomendado |
| node / npm | No disponibles; sin instalación habitual detectada | Node 24 LTS, mínimo 24.15.0 para el conjunto frontend seleccionado |
| docker | No disponible; no instalación habitual detectada | Docker Desktop, motor Linux y Compose v2 |
| eclipse / EMF / ATL / Acceleo / Xtext | No ejecutable, proyecto ni configuración detectados | Preparar y verificar matriz desde F1 |
| git | No disponible; no repositorio .git | Recomendado para versionado |
| PowerShell | Disponible | Terminal utilizada |
| Python | 3.14.4 disponible | Solo revisión estática; no forma parte del stack de la app |
| winget | Ejecutable disponible | Opcional, no utilizado para instalar |

Get-Command comprueba PATH, no la totalidad del disco. También se revisaron
carpetas habituales de Program Files, AppData/Local/Programs, Eclipse, .p2/.m2,
Scoop/Chocolatey y registros Windows de desinstalación. Instalaciones portables
en otras rutas siguen siendo posibles. JAVA_HOME, MAVEN_HOME y M2_HOME estaban vacíos.

## Instalación recomendada, pendiente de realizar

1. **JDK 21**: descargar Temurin 21 JDK para Windows x64 e instalar el MSI;
   habilitar PATH y JAVA_HOME. No basta un JRE. Abrir una terminal nueva y comprobar
   `java -version` y `javac -version` (ambos 21). [Guía oficial Temurin](https://adoptium.net/installation/windows/).
2. **Maven**: descargar el ZIP binario Maven 3.9.x de Apache; extraer a una ruta
   propia, añadir su carpeta `bin` al PATH y ejecutar `mvn -version`, comprobando
   que utiliza JDK 21. [Instalación oficial](https://maven.apache.org/install.html).
3. **Node/npm**: instalar Node 24 LTS para Windows desde el sitio oficial, con
   versión >=24.15.0; conservar npm y PATH del instalador. Reabrir terminal y usar
   `node --version`, `npm.cmd --version`. Si PowerShell bloquea npm.ps1, usar
   `npm.cmd` y `npx.cmd`; no es necesario cambiar la política global.
   [Descarga oficial Node.js](https://nodejs.org/en/download).
4. **Docker**: seguir los requisitos Windows/virtualización/WSL2 de Docker Desktop,
   instalarlo y activar el motor de contenedores Linux. Puede requerir reinicio.
   Comprobar `docker version`, `docker info` y `docker compose version`; un CLI
   presente no demuestra un daemon activo. [Guía oficial Windows](https://docs.docker.com/desktop/setup/install/windows-install/).
5. **Git** (recomendado): instalar Git for Windows desde su sitio oficial y comprobar
   `git --version`. Inicializar/versionar el proyecto será una acción posterior.
6. **Eclipse/MDE** (antes de F1): obtener Eclipse Modeling Tools desde
   [Eclipse Packages](https://www.eclipse.org/downloads/packages/). Verificar EMF
   Ecore/GenModel y herramientas de generación; instalar ATL, Acceleo, OCL y Xtext
   desde sus sitios oficiales según la matriz elegida. Registrar versiones y
   update sites y comprobar generación headless. No instalar automáticamente el
   Xtext más nuevo: [2.43 cambió soporte JUnit](https://eclipse.dev/Xtext/releasenotes.html).

Eclipse IDE facilita autoría, pero la construcción final deberá poder reproducirse
sin abrir el IDE. Las versiones concretas de MDE y sus coordenadas se fijarán con
pruebas reales; no se inventa un comando de integración de plugins todavía.

## Comprobación posterior

Desde la raíz:

```powershell
powershell -NoProfile -File scripts/check-environment.ps1
java -version
javac -version
mvn -version
node --version
npm.cmd --version
docker version
docker compose version
```

El script termina con código 1 si faltan ejecutables requeridos. Encontrarlos no
certifica versiones ni cierre de F0. Continuar con compilación, pruebas y arranque
del [README](../README.md), registrando resultados en [VERIFICACION_FASE_0](VERIFICACION_FASE_0.md).

En esta máquina la política de PowerShell bloqueó la ejecución del archivo `.ps1`.
Se conserva como comprobador opcional para entornos que admitan scripts; no se
cambió la política global. Para inspeccionar manualmente en una terminal:

```powershell
Get-Command java,javac,mvn,node,npm,docker,git,eclipse -ErrorAction SilentlyContinue
```

La falta de salida para un ejecutable significa que no se encontró en PATH.
Los comandos individuales del README no requieren ejecutar este script.
