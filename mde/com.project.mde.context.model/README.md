# ContextModel EMF

Modelo de contexto de Fase 8, Java 21, nsURI
`https://mdedu.espoch.edu.ec/model/context/1.0`.

`model/context.ecore` es la fuente formal; `model/context.genmodel` configura el
generador EMF. `src-gen` contiene 14 fuentes Java generadas, versionadas y compiladas
como módulo Maven/PDE independiente. No editar manualmente `src-gen`.

Para regenerar, importar este plugin en Eclipse Modeling Tools, abrir el GenModel
y ejecutar Generate Model Code; guardar ambos archivos de modelo antes de generar.
La generación de esta fase se hizo con la aplicación headless
`org.eclipse.emf.codegen.ecore.Generator` de Eclipse y un workspace externo al repo.
El build normal no requiere Eclipse: desde `mde`, ejecutar `mvn clean verify`.
Desde el backend, `mvnw test` ejecuta `ManagerDomainTest`, incluidos los round-trips.

Cuatro EClasses: ContextModel, StudentContext, PlatformContext, EnvironmentContext.
ContextFactory crea las instancias. Los ejemplos `examples/*.context` son snapshots
fixture explícitos (tres fallos / dominio alto), serializados por EMF y comprobados
con carga, validación, guardado, descarga y recarga. No contienen alumnos reales.

La proyección de los datos reales vive en ContextProjectionService del backend.
Los IDs pseudónimos no intervienen en el fingerprint de decisiones.
Ver `docs/FASE_8_ADAPTATION_MANAGER.md` y `docs/VERIFICACION_FASE_8.md`.
