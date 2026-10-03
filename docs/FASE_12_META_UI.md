# Fase 12 — Meta-IU docente de consulta

Checkpoint inicial: `c5e892bad6fc72deb45402e5afde837a6d38f6cd`.
Ruta `/docente`, separada de la aventura. El criterio es ver reglas, parámetros y
trazabilidad junto al modelo del estudiante, sin ejecutar nuevas decisiones.

## Autoridad y composición

`metaui/application/MetaUiService` compone DTOs cerrados. Usa
`StudentModelProjectionService` para el modelo y progreso existentes; elimina el
nombre de presentación del contrato docente. Sus consultas JDBC de lectura recuperan
listas paginadas y decisiones persistidas, sin llamar a AdaptationManager.decide,
ejecución, evaluación, feedback ni proyección UI.

`AdaptationInspectionService` inspecciona `AdaptationRuleLoader.snapshot()` y
`AdaptationParametersConfig.values()`: las mismas instancias de Spring que consume
el motor. Las condiciones se recorren desde el árbol EMF validado y se presentan
como comparaciones/AND/OR/NOT; no existe catálogo paralelo de reglas. Acciones,
prioridades, enabled y versiones proceden del modelo. El hash de reglas es el del
loader; el de parámetros usa `CanonicalHashes.hash(values)`, igual que el manager.
La severidad no es un atributo de regla en este metamodelo: se muestra en los patrones
y la auditoría histórica donde sí existe. Un fallo de carga no se presenta como cero
reglas válidas. No se modifican los archivos DSL/JSON.

## Pantallas y lecturas

- Selector de estudiantes pseudónimos (últimos ocho caracteres del UUID). No nombres,
  correos ni perfiles nuevos. Incluye paginación, número de intentos e identidad completa.
- Modelo: valores reales de mastery 0..1 y porcentaje puramente visual, intentos,
  éxitos, fallos, fallos consecutivos, tiempo medio, pistas, errores recientes y fecha.
  Progreso usa locked/unlocked/completed del servicio existente; muestra prerrequisitos
  y umbral de cada concepto sin recalcular desbloqueos.
- Intentos: actividad/concepto, fecha, resultados funcional/pedagógico, patrones y enlace
  a la timeline. El estado se obtiene del checker al abrir el intento, se muestra en su
  fila y en el detalle. Antes dice «Pendiente de consulta», sin adivinar COMPLETE.
- Adaptaciones: decisiones ya persistidas con regla, acciones, coincidencias,
  contribuyentes, descartes, evidencia y versiones/hashes. NO_ADAPTATION se presenta
  como «No se activó ninguna regla aplicable»; ausencia de fila es otro estado.
- Reglas y parámetros: valores activos, origen, versión y hashes; sin controles de edición.

Los listados aceptan `page>=0` hasta 100000 y `size` de 1 a 50, por defecto 20.
Orden estable por fecha/UUID o student_ordinal. Hay conteos agregados y consultas
acotadas; no se cargan eventos de todos los estudiantes para presentar una lista.
El modelo reutiliza su proyección acotada existente (cuatro conceptos). El listado de
adaptaciones lee semantic_json tipado directamente, sin recalcular ni limitarse a la
versión actual del motor. completedConceptCount cuenta conceptos con actividad completada
persistida (actualmente una actividad principal por concepto).

## Timeline de Fase 11

Se consume exclusivamente `/api/students/{studentId}/attempts/{attemptId}/timeline`.
No existe otro generador de timeline ni endpoint duplicado. La UI ordena por `sequence`,
expande payloads tipados y muestra source/fecha/hash. Se reconocen ejecución, evaluación,
patrones, deltas del StudentModel, adaptación, feedback y UI según eventos presentes.
COMPLETE/PARTIAL/INCONSISTENT y gaps permanecen explícitos; legacy sin eventos informa
trazabilidad parcial y no recibe backfill. Consultar no añade attempt_events.

El detalle incluye el feedback persistido como texto escapado: provider/source, modelo,
llmUsed, fallbackReason, purpose, mensaje, pregunta, versiones y hashes. FAKE se etiqueta
como proveedor de prueba; DISABLED/FALLBACK no se atribuye a una API remota. No se solicita
feedback desde la Meta-IU. Sin feedback: «No solicitado».

La UI histórica se toma de `UiPayload`, con versión/fingerprint, showCodePanel y modos
hint/navigation/difficulty/tutor, además de safeDefault. **feedbackDetailLevel no quedó
registrado en el contrato V1 de telemetría**: se indica «no registrado». No se deduce ni
se llama a UiConfigurationService para fabricarlo, ya que eso proyectaría y auditaría
una configuración nueva al consultar. El metamodelo y la proyección quedan intactos.

## Frontera de seguridad y capacidades

No hay Spring Security, autenticación ni roles administrativos existentes.
**Control de acceso robusto pendiente de hardening final.** Los UUID son pseudónimos,
no credenciales. Ocultar un enlace no protege el acceso. Esta pantalla es de prototipo
local y de solo lectura; no afirma resolver autorización para un despliegue compartido.

`MetaUiCapabilities` declara vistas disponibles y `canMutateRules=false`,
`canMutateParameters=false`, `canReviewProposals=false`. No se exponen PUT/PATCH,
formularios simulados, toggles ni propuestas ficticias. Futuras capas
RuleGovernanceService/ParameterGovernanceService/ProposalReviewService necesitarán
autorización y auditoría propias; no se implementan como servicios vacíos ahora.
La comprobación studentId/attemptId del endpoint de timeline sigue rechazando cruces.

No se exponen raw prompts, respuestas crudas, cabeceras ni claves. DTOs explícitos,
renderizado React escapado y errores de UI controlados; no entidades JPA ni HTML dinámico.
El cliente usa fetch, como los clientes existentes, sin inicializar student/session ni
heredar los headers de la sesión del alumno. La vista docente emite exclusivamente GET.
AbortController y claves por estudiante/sección descartan respuestas tardías al cambiar.

## Persistencia, calidad y límites

No hay V8: se consultan V1–V7. Sin tablas duplicadas, cambios pedagógicos, metamodelos,
providers, prompts, reglas, parámetros ni UI projection. Se mantienen cuatro actividades,
cero refuerzos y cero temas avanzados. Sin analytics, cohortes, exportaciones o administración
completa. No se inicia Fase 13.

Diseño para 1440×900, Tahoma/paleta existente, navegación por teclado, labels, headers de
tabla, foco visible y estados que no dependen solo del color. Detalles técnicos expandibles,
loading, errores y vacíos explícitos. Las suites pesadas se ejecutan secuencialmente con
heaps limitados y un worker, por los 16 GB disponibles.

Evidencia y conteos finales: [VERIFICACION_FASE_12.md](VERIFICACION_FASE_12.md).
