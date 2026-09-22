# Decisiones de arquitectura

Cada decisión diferencia lo configurado en F0 de lo previsto para otras fases.

## ADR-001 · Alcance secuencial

Aprobado por la solicitud: implementar únicamente F0. La raíz estaba vacía; se
crea scaffolding, documentación y configuración. Las herramientas ausentes impiden
cerrar el criterio de arranque. No se avanza ni se anuncia una aplicación funcional.

## ADR-002 · MDE clásico como fuente formal

Se mantienen EMF/Ecore, XMI, ATL, Acceleo y Xtext. JSON/TypeScript sirven al
transporte y la presentación; no reemplazan modelos formales. Las carpetas
reservadas contienen README, no metamodelos vacíos. La generación y sus versiones
se definirán mediante pruebas headless desde F1.

## ADR-003 · Java 21 y separación modular

Java 21/Spring Boot/Maven en backend por cercanía al ecosistema Eclipse.
Dependencias Spring se gestionan desde un parent exacto. No se añaden ahora
coordenadas MDE sin comprobar su resolución. Recursos EMF mutables se aislarán
por operación. Módulos Maven/Tycho adicionales necesitan una razón comprobada.

Versión inicial: Spring Boot 3.5.16, publicada y con línea de pruebas JUnit 5.
Su publicación marca el fin del soporte OSS de la rama 3.5. La selección permite
preparar la base pedida, pero debe revisarse antes de despliegue; no se declara
soporte activo ni migración a Boot 4/JUnit 6 aprobada.
[Publicación oficial](https://spring.io/blog/2026/06/25/spring-boot-3-5-16-available-now/).

## ADR-004 · Configuración inicial local

PostgreSQL 17 con Compose y volumen persistente; DB_PASSWORD es externa y
obligatoria. Puertos de aplicación/BD limitados a loopback en desarrollo.
Flyway gestiona evolución; Hibernate no crea el esquema educativo. Actuator
comprueba salud sin revelar detalles. CORS permite únicamente orígenes locales
configurados. La imagen fija versión mayor/distribución; el digest se fijará al
descargar y validar. No se afirma reproducibilidad binaria completa todavía.

## ADR-005 · Frontend sencillo

React/TypeScript/Vite y React Router; Context + reducer cuando haya estado
compartido real. F0 solo contiene el inicio y base de pruebas. Blockly comienza
en F2 y GridWorld SVG en F4. La UI principal estará en español y orientada a PC.

## ADR-006 · Ejecución determinista y código visible

El backend ejecutará modelo/IR cerrado, con límites; Acceleo generará JavaScript
para lectura. No se usa eval ni se ejecuta texto libre. El evaluador distinguirá
correctitud funcional, estructura, eficiencia y patrones pedagógicos.

## ADR-007 · Reglas soberanas y LLM auxiliar

AdaptationManager orquesta; modelos y reglas determinan acciones de vocabulario
cerrado. El LLM redacta y propone etiquetas validadas. ContextSanitizer minimiza
datos; FakeLlmProvider y fallback evitan dependencia operativa de la API.
No hay implementación ni credenciales LLM en F0.

## ADR-008 · Completar asignación de fases

`context.ecore` se entrega en F8; `ui.ecore` y ATL en F10. IDs/versiones se
diseñan desde F0 y se registran al implementar cada persistencia; F11 amplía
telemetría. `consecutiveSuccesses` se incorpora en F6 para sostener el DSL pedido.
Esto completa obligaciones existentes sin introducir nuevas funcionalidades.

## ADR-009 · Selección de versiones MDE pendiente de evidencia

No se elige automáticamente el Xtext más nuevo: la versión 2.43 elimina soporte
JUnit 5, exigido por el proyecto. Se evaluará una línea previa compatible, por
ejemplo 2.42, junto con EMF/MWE2/Java21. Es un candidato, no una compatibilidad
certificada. Elegir Acceleo 3/4 requiere prueba y documentación. Si no puede
satisfacerse la matriz exigida, explicar el conflicto antes de cambiar herramientas.
Fuentes: [Xtext](https://eclipse.dev/Xtext/releasenotes.html),
[Acceleo](https://help.eclipse.org/latest/topic/org.eclipse.acceleo.aql.doc/doc/index.html).

## ADR-010 · Dependencias y pruebas sin resultados ficticios

Los manifests contienen versiones explícitas; un package-lock se generará con npm,
no manualmente. En la preparación inicial no existía Maven Wrapper (resuelto en
ADR-012 tras autorización). Las pruebas unitarias/E2E y
Testcontainers se entregan como pruebas pendientes hasta contar con runtimes.
Una revisión de JSON/XML no demuestra compilación Java/TypeScript ni integración.

## ADR-011 · Diagnóstico e instalación por pasos con autorización

Nueva instrucción del usuario: realizar ahora solo diagnóstico y propuesta.
No instalar software, habilitar WSL, abrir instaladores ni modificar variables
del sistema durante este paso. Se verificó WinGet1.29.290, PowerShell5.1 y ausencia
de toolchains; WSL informa que no está instalado.

Propuesta: usar WinGet con paquetes exactos de JDK21, Node24 LTS, Git y Docker;
Maven3.9.16 desde ZIP oficial con SHA-512 comprobado, en carpeta de usuario y sin
PATH global, seguido de Wrapper. Las modalidades machine propuestas se revisan
antes de autorizar UAC/interacción. Docker y WSL se tratan por separado dado el
posible reinicio; no reiniciar automáticamente. No instalar otro gestor.

Los metadatos Winget solo demuestran instaladores disponibles. La aprobación de
consultas fuera del aislamiento no autoriza instalaciones. No se sustituye ninguna
tecnología. Eclipse/MDE tendrá una propuesta independiente para revisión del usuario
antes de instalar plugins; las fases MDE continúan sin iniciar.

## ADR-012 · Maven de usuario y Wrapper como entrada de construcción

Autorizado únicamente Maven. Se instala Maven3.9.16 en AppData/Local/Programs del
usuario usando ZIP oficial y SHA-512 comprobado. Solo se añade su bin al PATH de
usuario; no se cambia JAVA_HOME ni PATH de máquina. Wrapper3.3.4 only-script fija
Maven3.9.16 y el SHA-256 del ZIP ya verificado; sus archivos se deben versionar.
Las tareas normales usan mvnw.cmd/mvnw y su caché propia, sin Maven global.

El POM efectivo, validate, compilación release21 y 3 pruebas MVC se verificaron
sin cambiar POM/código. Las 4 pruebas de integración permanecen en Failsafe y se
ejecutarán cuando exista Docker/PostgreSQL. No se añade H2 ni se desactiva Flyway.
Un Connection reset al descargar Actuator se resolvió reintentando la misma versión.
Esta decisión no cierra Fase0 ni autoriza instalar otras herramientas.
