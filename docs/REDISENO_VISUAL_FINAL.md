# Rediseño visual final de MDEdu

Base: `6697077 feat: expand guided reasoning challenges`. Trabajo posterior al prototipo, sin abrir Fase 14.

## Referencias y decisión sobre Luma

La imagen de interfaz suministrada guía la composición: cielo luminoso, cabecera, banner del reto, bloques/programación, simulación y tutora. No se incorpora la captura como fondo ni se crean controles ficticios.

El usuario pidió posteriormente conservar **Luma como estaba antes, en SVG**. Esta decisión reemplaza el requisito original de copiar el PNG. Se conserva la ilustración anterior en `frontend/src/shared/Visuals.tsx`, con sus estados y proporciones. No se genera otra mascota ni se añade una imagen raster de Luma.

## Interfaz

- Shell de estudiante autenticado: marca → `/aprender`, Aprender, Laboratorio, Progreso, código real EST-XXX y menú personal con progreso/cierre de sesión.
- `/ingresar`: código y clave reales, sin registro ni email.
- `/aprender`: mapa de cuatro conceptos, submapas con los 18 retos y estados derivados del backend. Las URLs antiguas `/aventura` redirigen a las nuevas.
- Retos: banner con objetivo y posición dentro del concepto; cuatro zonas visuales en CSS Grid. Blockly ocupa las dos primeras, manteniendo la geometría nativa de su toolbox y workspace.
- El resultado breve aparece en Simulación; el feedback pedagógico y las pistas acompañan a Luma. Sin consola inferior ni progreso del concepto duplicado.
- Ver modelo abre un diálogo nativo con el XMI real generado por la API existente. El código adaptativo permanece en un detalle cerrado.
- `/progreso`: cuatro conceptos, retos completados, mastery e intentos del backend.
- Meta-UI: identidad visual compartida, densidad profesional y navegación Participantes, Modelo, Intentos, Adaptaciones, Reglas y Parámetros.

## Blockly y renderer

Tema central en `frontend/src/features/programming/blockly/theme.ts`: colores por familia, fuente Tahoma/Arial, escala inicial 1.05, padding por renderer y cuadrícula suave. Se usan las APIs de [Theme](https://docs.blockly.com/reference/classes/Theme/) y [rendererOverrides](https://docs.blockly.com/reference/blockly.blocklyoptions_interface.rendereroverrides_propertysignature/). IDs, adapter y serialización permanecen intactos. Los grupos permitidos siguen procediendo del catálogo.

El GridWorld continúa mostrando posiciones, orientación, obstáculos, llaves, puertas y meta reales. Se evoluciona el SVG original y se reproduce exclusivamente la traza recibida. Las acciones de la referencia que no existen (abrir puerta, imprimir mensajes, aritmética) no se incorporan como bloques ficticios.

## Simulación libre: excepción mínima al objetivo de cero cambios backend

Antes, el laboratorio solo construía modelos; no existía una API para ejecutarlos libremente. Se añade `LaboratoryController`, con un mundo fijo consultable y ejecución aislada mediante el **mismo** `ProgrammingModelMapper`, `Diagnostician` y `GridWorldExecutionEngine`.

- `GET /api/laboratory/world`: configuración del entorno de experimentación.
- `POST /api/laboratory/execute`: ProgramDto existente → traza existente.
- Sesión y CSRF protegidos por la configuración existente.
- Sin evaluación pedagógica, registros de intentos, cambios de mastery, desbloqueos ni escrituras de progreso.
- Límite de operaciones existente. Sin migraciones, modificación del motor, metamodelos, reglas o parámetros.

## Accesibilidad y recursos

Assets y fuentes locales. Foco visible, etiquetas, disclosures y diálogo de modelo con Escape y restauración de foco nativos. Reduced motion conservado. Verificación de escritorio y evidencias en el documento de validación. Pruebas pesadas secuenciales, heap Java/Node limitado y un worker Playwright por la memoria disponible del equipo.
