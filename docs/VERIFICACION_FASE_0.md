# Verificación de Fase 0

Registro inicial: 20/09/2026. Actualizado: 22/09/2026. Distingue inspección, revisión estática y pruebas
de aplicación. No se presenta una comprobación estática como compilación o arranque.

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

## Resultado de aceptación actualizado al 22/09/2026

**Fase 0 todavía no puede declararse completa.** Java, Maven/Wrapper y Node/npm
están verificados. Backend y frontend compilan; pasan 3 pruebas MVC y 2 unitarias
frontend. Vite respondió HTTP200 y quedó detenido. El escenario E2E Chromium
también pasó con salida0 y sus procesos terminaron. Permanecen pendientes las
4 pruebas de integración backend, PostgreSQL y arranque/health del backend.
El paso autorizado Playwright Chromium/E2E está completo; detenerse sin hacer commit.
