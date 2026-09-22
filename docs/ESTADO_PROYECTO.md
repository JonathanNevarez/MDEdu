# Estado del proyecto

Actualización documental: 22/09/2026. Maven verificado el 20/09/2026; frontend el 21/09/2026.

## Fase actual

**Fase 0: preparación escrita, cierre pendiente de herramientas y verificación.**
Fases 1–13 no iniciadas. La raíz estaba vacía y no era un repositorio Git.
El SVG original se conserva intacto.

**Git2.55.0.windows.3 x64 instalado y repositorio local inicializado.** Ruta:
`C:\Program Files\Git\cmd\git.exe`. Identidad global y core.autocrlf global ausentes;
no se modificó configuración global. Rama normalizada a **main** e identidad
configurada únicamente aquí: **JonathanNevarez <neva_rez00@hotmail.com>**.
.gitignore y .gitattributes verificados; Wrapper sigue funcionando. Sin remotos.
Los 98 archivos revisados están listados en [PRIMER_COMMIT](PRIMER_COMMIT.md).
Se prepara el commit autorizado `chore: bootstrap validated development environment`.
Esta documentación se escribe antes del commit para que forme parte del snapshot.
Su creación, hash exacto y estado limpio se comprobarán después y se registrarán
en `.git/FASE_0_SNAPSHOT.txt` y en la respuesta al usuario, sin modificar archivos
versionados. El hash del primer snapshot se consulta también con
`git log --max-parents=0 --oneline`. La política MDE generada se decidirá en Fase1.

**Node.js24.19.0 LTS x64 y npm11.17.0 instalados y verificados.** WinGet instaló
el paquete oficial en `C:\Program Files\nodejs\`; no se editó PATH manualmente.
Cumple `engines.node >=24.15.0 <25`. `npm.cmd install` instaló 114 paquetes,
auditó 115 y reportó 0 vulnerabilidades; generó `frontend/package-lock.json`.
TypeScript y Vite8.3.0 compilan; Vitest5.0.1 ejecutó 1 archivo con 2 pruebas
correctas, 0 fallidas y 0 omitidas. Vite respondió HTTP200 en
`http://127.0.0.1:5173`, con HTML y raíz React comprobados. El servidor quedó
detenido y el puerto libre. `dist/` contiene HTML, CSS y JS (262958 bytes).
La revisión básica de fuentes/configuración no encontró secretos; no existen
archivos `.env*` en frontend. No cambió código, configuración ni versiones.

FRONTEND BUILD/UNIT/HTTP: **OK**. FRONTEND E2E: **pendiente de autorización**.
Playwright requiere Chromium y auxiliares (~327 MB de descarga); solo se hizo
consulta dry-run y HEAD, sin descargar navegadores. No se verificó renderizado
en navegador real. Fase 0 permanece abierta.

**Maven3.9.16 instalado en el perfil de usuario y Wrapper3.3.4 preparado.** Ambos
usan Temurin21.0.12.1. Se verificaron SHA-512 de instalación, rutas, POM efectivo,
validate, clean compile y test. Compilación: 42 fuentes release21. Surefire: 3
pruebas MVC, 0 fallos, 0 errores, 0 omitidas. Solo se añadió Maven/bin al PATH del
usuario; JAVA_HOME y PATH de máquina permanecen intactos. El POM conserva su hash.

BACKEND BUILD: **OK**. BACKEND RUNTIME: **pendiente de Docker/PostgreSQL**.
El Wrapper fija versión y hash y es la vía preferida para tareas normales.
WSL, Docker, Eclipse y componentes MDE siguen sin instalarse.

**JDK21 instalado y verificado con autorización exclusiva para Java.** WinGet
instaló Temurin x64 (paquete 21.0.12.101); java y javac devuelven **21.0.12.1**.
JAVA_HOME persistente apunta a
`C:\Program Files\Eclipse Adoptium\jdk-21.0.12.101-hotspot\` y su bin está en PATH.
Get-Command y where.exe coinciden en una única ruta por ejecutable. Las comprobaciones
se hicieron en una nueva instancia PowerShell tras refrescar el entorno del proceso.
El instalador configuró variables; no hubo modificaciones globales manuales.

Prueba real: `javac JavaEnvironmentTest.java` y `java JavaEnvironmentTest`, ambos
con código 0 y salida `Java environment OK`. Fuente, clase y carpeta temporal
eliminadas. Esa autorización de Java quedó aceptada antes de preparar Maven.

Antecedente, antes de autorizar Java: **Paso 1 completado como diagnóstico**, sin instalar
herramientas ni iniciar servicios. WinGet funcional: 1.29.290; Windows PowerShell:
5.1.26100.9444. Chocolatey/Scoop no encontrados. Git, Java/javac, Maven/Wrapper,
Node/npm y Docker/Compose estaban no disponibles. WSL informó que no estaba instalado.
JAVA_HOME estaba ausente y `.git` no existía. El catálogo WinGet resolvió versiones
concretas de instaladores; eso no implica que estén instalados o probados.

La propuesta de las herramientas todavía pendientes está en
[HERRAMIENTAS](HERRAMIENTAS.md). No hubo cambios de arquitectura.

## Entregables terminados como archivos

- Inspección de raíz, herramientas, configuraciones y marco conceptual.
- Plan secuencial con riesgos, criterios y alcance exacto de F0.
- Arquitectura modular con MDE clásico, contratos y decisiones documentadas.
- Configuración frontend/backend y pruebas iniciales escritas.
- Compose PostgreSQL, variables sin secretos, migración de bootstrap y health.
- Carpetas MDE reservadas; referencia SVG preservada mediante copia idéntica.
- Maven Wrapper con versión/hash fijados; backend compilado y pruebas MVC verificadas.
- Node/npm verificados, lock generado; frontend compilado, pruebas unitarias y HTTP verificados.
- Git instalado; repositorio local, exclusiones, atributos y lista del primer commit preparados.

Estos entregables no son funcionalidades educativas terminadas. No hay modelo
EMF generado, Blockly, intérprete, reglas, LLM ni IU adaptativa aún.

## Pruebas y comprobaciones

Ver [VERIFICACION_FASE_0](VERIFICACION_FASE_0.md) para comandos y resultados.
La instalación Java cuenta ahora con comprobación real de versión, rutas, variables,
compilación y ejecución. La prueba mínima del JDK no sustituye las pruebas de la app.
Las revisiones JSON/XML y coherencia de archivos se separan de la ejecución real.
Revisión estática de la entrega inicial superada: 3 JSON, 2 XML/SVG, 118 enlaces locales y 44
correspondencias de paquetes Java/rutas; copia SVG idéntica al original y sintaxis
del comprobador PowerShell correcta. Informe sin fallos estáticos detectados.
Pruebas preparadas: 2 casos de frontend con RTL, 1 smoke Playwright, 3 invocaciones
MVC de CORS y 4 pruebas de integración PostgreSQL/Actuator/Flyway.
**Las 3 pruebas MVC y las 2 pruebas unitarias frontend se ejecutaron correctamente.**
E2E y las 4 pruebas de integración siguen pendientes. BackendBootstrapIT se compiló pero no ejecutó:
requiere Docker y se invoca mediante Failsafe en verify. No se deshabilitó.

| Criterio obligatorio de F0 | Estado |
| --- | --- |
| JDK21/javac, JAVA_HOME, PATH y compilación mínima | Completado: Temurin21.0.12.1 x64; salida Java environment OK |
| Maven/Wrapper, POM efectivo y validate | Completado: Maven3.9.16, Java21.0.12.1, salidas0 |
| Git y preparación del repositorio local | Verificado; identidad local y main configuradas; snapshot autorizado según registro de verificación |
| Frontend compila y pruebas unitarias pasan | Completado: TypeScript/Vite; 1 archivo, 2 pruebas correctas |
| Frontend inicia y responde HTTP | Completado: HTTP200 en 127.0.0.1:5173; servidor detenido |
| Frontend E2E en Chromium | Pendiente de autorización para descargar navegadores |
| Backend compila y pruebas sin Docker pasan | Completado: 42 fuentes; 3 pruebas MVC, 0 fallos/errores/omitidas |
| Backend: 4 pruebas de integración | Pendiente: Docker/PostgreSQL |
| PostgreSQL inicia y acepta consulta | Pendiente: Docker ausente |
| Backend inicia y health comprueba DB | Pendiente |
| Documentación y estructura creadas | Completado |

## Problemas conocidos

1. Docker/WSL y Eclipse/MDE siguen pendientes. JDK21, Maven/Wrapper, Node/npm y Git ya verificados.
2. La política local de PowerShell bloquea scripts `.ps1`; el script opcional de
   inspección no se pudo ejecutar. La inspección mediante comandos directos sí
   se realizó. `npm.ps1` también está bloqueado; se usa `npm.cmd`, sin cambiar la política.
3. Package-lock generado por npm y validado. El digest de imagen sigue pendiente.
4. Compatibilidad runtime de DB/LLM/MDE y E2E del frontend todavía por verificar.
5. La matriz Eclipse/MDE está pendiente; Xtext reciente requiere revisar el
   requisito JUnit 5, y Acceleo 3/4 tienen diferencias que deben resolverse.
6. Spring Boot 3.5.16 tiene límite de soporte OSS documentado; revisar antes de
   desplegar y mantener el requisito de pruebas del proyecto.
7. Connection reset al descargar Actuator3.5.16, resuelto al reintentar sin cambiar
   versión. Avisos Mockito/Byte Buddy sobre carga dinámica en Java21, sin fallos;
   no se alteró el POM para ocultarlos.
8. El primer build frontend falló por `spawn EPERM` del aislamiento. El mismo
   comando con permisos de ejecución pasó, sin correcciones al scaffold.
   npm avisó de 22 paquetes con financiación y de una nueva versión mayor;
   no se actualizó npm ni se ejecutó audit fix.

## Siguiente paso

**Crear y verificar únicamente el snapshot inicial autorizado; después detenerse.
No instalar otro componente ni conectar remotos hasta recibir nueva autorización
del usuario.** La propuesta de herramientas
restantes está documentada, pero no se ejecuta. La estrategia Eclipse/MDE sigue
pendiente de revisión específica antes de instalar plugins; no se crea ningún metamodelo.
**No avanzar a Fase 1 hasta cerrar Fase 0.** No hay cambios destructivos propuestos.
