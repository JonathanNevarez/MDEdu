# Fase 2 — Blockly a modelo EMF

El laboratorio `/laboratorio` permite construir, guardar y restaurar un programa
visual y obtener su representación EMF validada y serializada en XMI. El modelo
formal de Fase 1 permanece intacto.

## Flujo y responsabilidades

```mermaid
flowchart LR
  B[Workspace Blockly] --> A[Adaptador toProgramDto]
  A --> D[ProgramDto V1]
  D --> H[Cliente HTTP separado]
  H --> C[Spring Controller]
  C --> M[Mapper de dos pasadas]
  M --> E[Program EMF]
  E --> V[Diagnostician]
  V --> X[ResourceSet / XMI]
```

Frontend separa `blockly`, `dto`, `persistence`, `api` y `pages` dentro de
`src/features/programming`. El adaptador no hace fetch. Backend separa DTO,
controller, mapper y servicio EMF. Spring sigue siendo una aplicación JVM
convencional; no se convirtió en aplicación Eclipse/OSGi.

## Editor

Blockly 13.3.0, jsdom 29.1.1 para tests. Dieciséis bloques: Inicio (solo UI),
Avanzar, Giros izquierdo/derecho, Declaración, Asignación, Referencia de variable,
Repeat, While, If, IfElse, Literal entero, Literal booleano, Comparison,
BooleanExpression y SensorExpression. Las conexiones distinguen instrucciones
y expresiones. NOT tiene un operando; AND/OR dos. La toolbox agrupa Movimiento,
Variables, Control, Condiciones y Valores. Cuatro ejemplos provienen de los
fixtures compartidos.

El locale español usa namespace import y object rest para excluir el default
sintético de TypeScript. Se importan core y mensajes, ningún generador de código.
Los medios de Blockly se sirven localmente desde la dependencia instalada y se
emiten en el build; no se necesita un CDN en runtime.

## Contrato y referencias

[ProgramDto V1](../contracts/programming/v1/README.md) define `contractVersion: 1`.
Inicio no se transporta. El recorrido de next y cuerpos anidados conserva orden.
Las declaraciones usan block.id; asignaciones y expresiones referencian ese ID,
independientemente de su nombre visible. El dropdown conserva IDs durante la
carga aunque la declaración aparezca después.

El mapper crea objetos únicamente con `ProgrammingFactory.eINSTANCE`. Primera
pasada: registrar declaraciones y rechazar IDs duplicados. Segunda: poblar
objetos y resolver referencias reales. Límites locales: profundidad 100 y
10 000 instrucciones. No se introducen atributos de identidad en el metamodelo.

## Guardar y restaurar

`Blockly.serialization.workspaces.save/load` conserva bloques, IDs, campos y
conexiones. localStorage usa `mdedu.blockly.workspace.v1`, con envelope version 1,
programName y workspace. El DTO y XMI no sustituyen el estado visual guardado.
Restore comprueba formato y carga primero en un workspace aislado; si falla la
carga visible, restaura el estado previo. No hay autosave ni persistencia DB.

E2E interactúa con botones reales, comprueba desaparición/reaparición de bloques,
recarga y compara el JSON guardado antes/después. Unit tests verifican además
equivalencia del DTO y los IDs declarationId/targetDeclarationId, incluidas
referencias adelantadas.

## API y XMI

`POST /api/programming/models` usa `Diagnostician.INSTANCE.validate(program)`.
Si el diagnóstico no es OK, devuelve 422 y mensajes reales, sin XMI. La validación
es estructural EMF; no demuestra corrección semántica de tipos ni del algoritmo.
JSON/contrato/referencias inválidos devuelven 400 controlado. Un resultado válido
devuelve 200, summary con rootType/statementCount/namespace y XMI UTF-8.
El conteo incluye instrucciones anidadas y excluye expresiones e Inicio.

La serialización usa ResourceSetImpl, registro local de ProgrammingPackage y
XMIResourceFactoryImpl, un Resource en memoria y unload al finalizar. No se
construye XML manualmente. El test de recarga verifica que las referencias siguen
apuntando a la misma declaración cargada. La UI muestra diagnósticos, resumen y
una vista XMI colapsable de solo lectura.

## Build reproducible

1. Desde `mde`: `mvn.cmd --batch-mode --no-transfer-progress clean install`.
2. Desde `backend`: `mvnw.cmd --batch-mode --no-transfer-progress verify`, con Docker operativo.
3. Desde `frontend`: `npm.cmd ci`, `npm.cmd run typecheck`, `npm.cmd test`,
   `npm.cmd run test:e2e` (Chromium previamente instalado).

Backend consume `com.project.mde:com.project.mde.programming.model:0.1.0-SNAPSHOT`
del repositorio Maven local. Excluye transitivas OSGi y declara runtimes EMF
Common 2.46.0, Ecore 2.43.0 y Ecore XMI 2.41.0. No systemPath ni copia de JAR.
El probe Maven independiente de Spring demostró el consumo antes del cambio al POM.

Para runtime, usar PostgreSQL Compose existente, backend desde su directorio y
frontend `npm.cmd run dev` en 5173; se conserva la lista CORS explícita existente.
No publicar `.env` ni credenciales. Detener los tres servicios al terminar.

## Límites y deuda

No hay ejecución de instrucciones, personaje, evaluación pedagógica, LLM, nuevas
entidades JPA/tablas/migraciones, ATL, Xtext o Acceleo. No se genera JavaScript.
El chunk de Blockly supera 500 kB: deuda de optimización documentada, sin alterar
umbrales ni controles. Evidencia y cronología: [VERIFICACION_FASE_2](VERIFICACION_FASE_2.md).
