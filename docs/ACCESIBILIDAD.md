# Accesibilidad

Objetivo del prototipo: WCAG 2.2 AA, mediante evaluación automatizada y casos manuales principales reproducidos con navegador/teclado. No se afirma certificación formal, conformidad universal ni equivalencia con una evaluación de usuarios de tecnologías de asistencia.

`@axe-core/playwright` 4.13.0 audita inicio, mapa, actividad con Blockly, feedback/Luma, login docente y Meta-IU. `quality.spec.ts` adjunta resultados JSON de axe a cada prueba. El criterio de cierre es cero violaciones serious/critical; resultados y limitaciones observados se registran en [VERIFICACION_FASE_13](VERIFICACION_FASE_13.md). La misma suite se puede ejecutar contra Nginx con `scripts/docker-smoke.ps1 -Browser`.

Se conserva enlace para saltar al contenido, landmarks, encabezados, labels explícitos, controles nativos y foco visible. La actividad dispone de botones para construir secuencias sin arrastre; se comprueban activación por Enter y ejecución, recuperación/reintento, navegación y controles docentes. Los paneles de pista y Luma comunican texto; los estados bloqueado, completado, error y trazabilidad no dependen solo del color. Los detalles de timeline se abren bajo demanda con controles de teclado.

`prefers-reduced-motion` elimina transiciones de GridWorld y animación de la UI adaptativa. Las regresiones cubren pantallas PC/laptop de 1440×900 y 1280×800. No se rediseña el producto como aplicación móvil.

Blockly conserva una superficie SVG compleja y no se ha reescrito. Las operaciones avanzadas de edición por arrastre, navegación interna del editor, foco tras todos los cambios dinámicos y compatibilidad con combinaciones de lector de pantalla/navegador requieren evaluación humana adicional. Los resultados sin errores graves de axe no demuestran por sí solos que toda la experiencia se pueda completar con cualquier tecnología de asistencia. Se mantienen los controles accesibles alrededor del editor y el alcance pedagógico de cuatro conceptos.

## Resultado observado de cierre

Seis estados auditados: cero critical/serious. Inicio, login, Meta-IU y mapa sin violaciones. En actividad y feedback queda `region` moderate sobre el canvas decorativo interno de medición `blocklyComputeCanvas`, insertado fuera de los landmarks. Se conserva la biblioteca sin parches internos. Axe deja dos comprobaciones incompletas en mapa/actividad/feedback; requieren revisión humana. Estas limitaciones no se ocultan mediante exclusiones del escaneo.


## Rediseño visual final (03/10/2026)

La navegación autenticada contiene Aprender, Laboratorio y Progreso, más el menú del código de estudiante. Se conservan foco visible, enlace de salto, etiquetas, controles de teclado, texto de estado y reduced motion. El modelo EMF se muestra bajo demanda en un diálogo nativo con título accesible y cierre por Escape. El PNG inicialmente solicitado para Luma se sustituyó por la conservación del SVG anterior por instrucción posterior del usuario.

`final-interface.spec.ts` recorre diez estados reales en cada viewport **1440×900 y 1366×768**: ingreso, mapa, submapa, COND-03 antes/después, LOOP-01 pedagógico, progreso, laboratorio y las dos vistas docentes. Resultado: **0 serious / 0 critical** en los veinte estados; sin overflow horizontal. Comprueba además Tahoma en los encabezados, montaje de un condicional por drag/drop, modelo real, login desde otro contexto y progreso idéntico.

Se mantiene el hallazgo moderate `region` del canvas interno de Blockly fuera de landmarks, sin exclusiones de axe ni parches a la biblioteca. No se afirma accesibilidad universal de toda la edición avanzada de Blockly. [Auditoría 1440](evidencia-rediseno-final/1440-audits.json) · [Auditoría 1366](evidencia-rediseno-final/1366-audits.json).
