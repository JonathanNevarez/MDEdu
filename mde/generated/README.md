# Código generado

Placeholder conservado desde Fase 0. La preferencia inicial por salidas en
`target/` era provisional. La política autorizada de Fase 1 establece que los
fuentes EMF de `../com.project.mde.programming.model/src-gen/` **se versionan**.
Se regeneran desde `.ecore` / `.genmodel` mediante **Generate Model Code**;
no editar clases generadas manualmente. Binarios y temporales permanecen en
`bin/` y `target/`, ignorados. Esta carpeta no duplica los fuentes del plugin.

No se añade una exclusión global de `mde/generated/`.
Se versionarán las fuentes MDE (`.ecore`, `.genmodel`, gramáticas `.xtext`,
transformaciones ATL y Acceleo), modelos de ejemplo relevantes y configuraciones
reproducibles. Para cada salida generada se documentará su origen, tarea de
regeneración y decisión de versionado antes de añadir reglas específicas.
