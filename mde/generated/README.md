# Código generado

Reservado para generación EMF/Xtext. No editar clases generadas manualmente.
La Fase 1 definirá rutas de salida, tareas de regeneración y política de
versionado al verificar el toolchain; se preferirán salidas en `target/` de los
módulos reales para evitar duplicar fuentes de compilación.

No se añade una exclusión global de `mde/generated/`: todavía no hay artefactos
generados y la política exacta se decidirá en Fase 1 según EMF/Xtext/Tycho.
Se versionarán las fuentes MDE (`.ecore`, `.genmodel`, gramáticas `.xtext`,
transformaciones ATL y Acceleo), modelos de ejemplo relevantes y configuraciones
reproducibles. Para cada salida generada se documentará su origen, tarea de
regeneración y decisión de versionado antes de añadir reglas específicas.
