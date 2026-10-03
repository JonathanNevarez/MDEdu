# Rediseño visual y UX de MDEdu

Cambio posterior al prototipo completo, sobre `67bebe07f02ed71ddfe808e66ca5341e41225d6d`. Las fases 0–13 conservan su alcance y su historia. Este trabajo no constituye otra fase ni amplía la pedagogía.

## Problema y dirección

La interfaz anterior exponía módulos funcionales con tratamientos visuales distintos. La nueva presentación conecta inicio, aventura, laboratorio y consulta docente mediante una identidad de aventura educativa: cielo claro, paisaje suave, senderos, vegetación, agua y superficies legibles. La inspiración Frutiger Aero se limita a luminosidad y profundidad sutil, sin copiar interfaces ni activos de otros productos.

Se conserva «Mi primera programación», MDEdu y la referencia a proyecto académico de educación universitaria. No existían activos institucionales ni un retrato de Luma en el frontend: el tutor usaba una estrella provisional. El motivo de bloques del encabezado pertenece a la misma familia de iconos de navegación; no pretende sustituir una marca institucional.

## Sistema visual

`frontend/src/styles/tokens.css` centraliza los valores reutilizables. `styles.css` define la base, controles y shell; los archivos CSS de aventura, adaptación y Meta-IU conservan el alcance de sus componentes.

| Elemento | Decisión |
|---|---|
| Primario | Azul petróleo `#176480`; hover `#104f69` |
| Secundario / éxito | Verde `#236a53` / `#216747` |
| Atención | Amarillo claro con texto `#765411` |
| Error / peligro | `#a43636`, reservado para errores y limpiar el laboratorio |
| Fondo / superficie | `#eff7f8`, `#f9fcfe`, blanco elevado |
| Texto | `#173b47`; secundario `#526b73` |
| Tipografía | Tahoma, Segoe UI, sans-serif del sistema; Consolas para código |
| Escala | Display fluido, h1 36 px, h2 21.6 px, h3 16 px, auxiliares menores según jerarquía |
| Espacio | 4 / 8 / 12 / 16 / 24 / 32 / 48 / 64 px |
| Radios | 8 / 16 / 24 px; cápsula para estados |
| Elevación | Dos sombras suaves y elevación pequeña para nodos |
| Interacción | Transiciones de 160 ms; foco visible de 3 px con separación |
| Ancho | 1280 px general; 1400 px actividad, laboratorio y docente |

Botones primarios, secundarios, ghost, danger e icon comparten estados de hover, active, focus y disabled. La ejecución conserva su texto y nombre accesible. Los inputs mantienen labels, autocompletado, límites, validación y semántica. Las superficies de Blockly reciben solamente marco y tamaño: no se retoca su toolbox, serialización, adaptador ni lógica interna.

## Componentes y pantallas

- **Shell:** marca textual, navegación existente con iconos consistentes, enlace para saltar al contenido, pie institucional. Sin rutas nuevas.
- **Inicio:** mensaje corto, CTA a la aventura, entrada secundaria al laboratorio, paisaje y presentación de Luma. Las ilustraciones son SVG locales, sin fuentes o imágenes remotas.
- **Mapa:** cuatro destinos conectados por un sendero visual, con iconos de secuencia, variable, bifurcación y ciclo. Candado y texto distinguen bloqueo; borde cálido y CTA señalan disponibilidad; check y texto señalan finalización. El sendero es ilustrativo: `serverLevelStatus` continúa determinando cada estado.
- **Progreso:** el mapa muestra conceptos completados y dominio por nodo. «Mi progreso» despliega cuatro indicadores nativos `meter`, porcentaje e intentos desde `StudentProgress`. No hay una categoría pedagógica nueva ni cálculo de mastery en el cliente.
- **Actividad:** objetivo y retorno al mapa, editor amplio a la izquierda, simulación y evaluación a la derecha. Ejecutar mantiene la jerarquía principal; reiniciar y reproducir son secundarios. Se conservan coordenadas, direcciones y comportamiento del mundo.
- **Resultado:** recorrido y aplicación del concepto son dos filas separadas con iconos y texto. Alcanzar la bandera sin aplicar ciclos sigue siendo un resultado pedagógico no aprobado. No se reescriben observaciones ni recomendaciones del evaluador.
- **Ayuda:** HintPanel compacto o expandido conserva exactamente sus condiciones. Pregunta y foco están separados visualmente. El código continúa siendo solo lectura, escapado y sin ejecución.
- **Luma:** `LumaPortrait` es un SVG original y sustituible, no un chatbot. IDLE, GUIDE, HINT, FEEDBACK y SUCCESS combinan una etiqueta contextual con color y, en éxito, expresión. `HIDDEN` sigue ocultando el tutor; no se fuerza su aparición en una decisión adaptativa.
- **Transiciones:** se mantiene el anuncio accesible de cambios. Animación breve y fondo de aviso según el modo recibido, sin alterar la configuración.
- **Laboratorio:** mismos controles y modelos; cabecera «Experimenta libremente», marco de editor, acción de generar destacada y limpiar identificado como peligro.
- **Carga, error y vacío:** carga real con Luma y barra indeterminada, sin retraso artificial; errores recuperables sin trazas; 404 con navegación; vacío docente explícito.
- **Acceso docente:** composición sobria con formulario claramente etiquetado. La autenticación, CSRF, cookies y borrado del password tras envío no cambian.
- **Meta-IU:** cabecera de solo lectura, selector pseudónimo y navegación lateral; cuatro conceptos comparables, tabla de intentos, adaptaciones y timeline con eventos expandibles. Reglas separan condición y acciones. Parámetros agrupan ayuda, dificultad/ruta y umbrales. Hashes y configuración auditada quedan en detalles técnicos cerrados inicialmente, disponibles para inspección.

## Accesibilidad y escritorio

Diseño prioritario a 1440×900 y revisión a 1366×768. El mapa reduce altura y recoloca nodos en laptops; el editor y tablero ajustan su altura manteniendo controles utilizables. En anchos menores se apilan áreas sin introducir navegación alternativa.

Se mantienen navegación por teclado, skip link, nombres accesibles, regiones, anuncios, foco visible y estados con texto/icono. `prefers-reduced-motion` elimina movimientos decorativos y transiciones. Los SVG decorativos usan `aria-hidden`; la descripción accesible de GridWorld sigue informando posición y dirección. El progreso es un `meter`, no una barra con porcentajes inventados durante carga.

La evidencia de navegador verifica desbordamiento horizontal y que todas las etiquetas estén dentro del mapa. La inspección visual complementa axe y los E2E: un test funcional no detectaba el recorte inicial de nodos a 1366×768 y se añadió esa comprobación.

## Límites deliberados

No hay endpoints nuevos, librerías UI, CDN, chat abierto, nuevos niveles, reinforzos, cuentas de estudiante ni temas avanzados. Luma es un recurso vectorial provisional diseñado para reemplazarse sin tocar su lógica. Las vistas largas de reglas/timeline conservan scroll documental y detalles expandibles; no se eliminan datos para acortar la pantalla. La advertencia de tamaño de Blockly y la observación moderada de su canvas se registran en la verificación, sin ocultarlas ni alterar Blockly.

Véase [verificación y capturas](VERIFICACION_REDISENO_UI_UX.md) para resultados ejecutados y evidencia final.
