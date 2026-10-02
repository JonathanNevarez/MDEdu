# Fase 10 — UI adaptativa MDE

La aplicación React conserva el editor, mapa, simulador e identidad visual. Interpreta
una configuración formal producida por el backend; no ejecuta reglas ECA ni calcula mastery.

## Cadena formal

`TaskAndDomainModel → TaskAndDomain2AbstractUI.atl → AbstractUIModel →
AbstractUI2ConcreteUI.atl → ConcreteUIModel → UiProjection → FinalUIConfiguration → DTO → React`.

El plugin `com.project.mde.ui.model` contiene seis EClasses y diez enums. El namespace es
`https://mdedu.espoch.edu.ec/model/ui/1.0`. Su GenModel genera Java 21 con el generador EMF
real. Los cuatro seeds corresponden a SEQUENCES, VARIABLES, CONDITIONALS y LOOPS.
AbstractElement y ConcreteElement conservan originId; la primera transformación filtra
capacidades y la segunda relaciona elementos abstractos con tipos concretos.

`com.project.mde.ui.transformations` es un módulo Tycho de pruebas headless. Compila ATL
con Atl2006Compiler y ejecuta EMFVMLauncher; no sustituye ATL con Java. Sus pruebas cargan
los seeds, validan, guardan, descargan y recargan XMI. Comparan los resultados reales con
los modelos versionados en `ui.model/examples`, empaquetados para el consumidor backend.
Cambiar las transformaciones exige regenerar y revisar sus ejemplos; el build detecta divergencia.

## Proyección y autoridad

UiConfigurationService carga ConcreteUIModel, la decisión persistida, el último feedback
si existe y los destinos permitidos por progreso y prerrequisitos. UiProjection crea
FinalUIConfiguration mediante UiFactory, aplica las acciones ya resueltas y valida el modelo.
No vuelve a evaluar ECA, no resuelve conflictos y no modifica StudentModel.

| Acción | Efecto de presentación |
|---|---|
| SHOW_HINT / CHANGE_HINT_LEVEL | CONCEPTUAL → COMPACT / CONCEPTUAL_HINT; GUIDED → EXPANDED / ANALOGOUS_EXAMPLE; DIRECT → EXPANDED / PARTIAL_HELP |
| REPEAT_ACTIVITY | REPEAT, botón explícito; nunca reejecución automática |
| ADVANCE_TO_NEXT_CONCEPT | ADVANCE solo con destino del backend permitido |
| SELECT_REINFORCEMENT_ACTIVITY | Sin enlace: continúan existiendo cero refuerzos |
| INCREASE_DIFFICULTY | INCREASED / FOCUSED |
| DECREASE_DIFFICULTY | REDUCED / ASSISTED |
| SHOW_CODE_VIEW / HIDE_CODE_VIEW | Visibilidad de código de lectura |
| CHANGE_FEEDBACK_STYLE | CONCISE → MINIMAL; EXPLANATORY → DETAILED |

El HintStage de FeedbackRecord, cuando existe, es la autoridad sobre la pista presentada.
La dificultad solo cambia presentación y ayudas: no crea variantes, objetivos ni reglas nuevas.
La versión del contrato es 1. El fingerprint SHA-256 representa únicamente la configuración
semántica, sin UUID ni fecha. La UI no se persiste; se reconstruye al refrescar.

Ante un error técnico se registra su clase y se devuelve configuración segura: sin código,
sin navegación siguiente, layout/dificultad/feedback STANDARD y STAY. El intento ya guardado
no depende de que esta proyección posterior tenga éxito. Acceso a intento ajeno retorna 404;
actividad bloqueada, 409. La identidad continúa siendo la del prototipo, sin añadir autenticación.

## Excepción V5 autorizada

El usuario autorizó explícitamente V5 tras explicar que V1–V4 solo conservaban resultados y
resúmenes sanitizados, no ProgramDto. `attempt_programs` guarda el DTO validado y su SHA-256,
con attempt_id como PK/FK, en la transacción existente. No se crea persistencia de UI ni
se modifica V1–V4. Los intentos anteriores no reciben programas inventados ni backfill.

La ruta de lectura comprueba propietario e integridad, reconstruye Program EMF y reutiliza
`Generate.generate` del Acceleo existente en un directorio temporal privado. Devuelve program.js
como texto, elimina exclusivamente los archivos de esa generación y nunca ejecuta JavaScript.
Un intento antiguo informa PROGRAM_SNAPSHOT_UNAVAILABLE. El programa no se incorpora a prompts.

## React y Luma

`useAdaptiveUi` carga configuración al abrir la actividad y después de un intento. Si requiere
asistencia y no hay feedback, hace como máximo un POST al servicio idempotente de Fase 9 y
una recarga de configuración. No hay efecto dependiente de la respuesta ni bucle de generación.
Loading es neutral; un fallo conserva la posibilidad de resolver el reto con safe default.

AdaptiveRenderer interpreta enums cerrados. HintPanel recibe solo etapa, mensaje, pregunta y
foco. El DTO de presentaci?n traduce identificadores t?cnicos del foco al nombre del
concepto existente, sin cambiar FeedbackRecord ni su texto. CodePanel usa pre/code de lectura, con scroll; feedback y código se renderizan como texto
escapado. No hay eval, Function ni HTML generativo. REPEAT conserva bloques e historial;
ADVANCE usa el activityId retornado por backend. El mapa conserva progreso backend como autoridad.

Luma es un tutor visual acotado, sin textbox, chat abierto ni acceso propio a Internet.
No se encontró un asset previo: se usa una estrella original de texto sobre círculo, sustituible.
Sus estados visuales son IDLE, GUIDE, HINT, FEEDBACK y SUCCESS, derivados de configuración/resultado.
Transitioner compara únicamente configuración previa/nueva, anuncia cambios mediante aria-live
polite y respeta prefers-reduced-motion. No consulta patrones, mastery ni reglas.

No se introducen telemetría, dashboard, Meta-UI, editor docente ni Fase 11.
