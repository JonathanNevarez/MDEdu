# Fase 1: metamodelo de programación

## Objetivo y alcance

Representar secuencias, variables, condicionales y ciclos mediante EMF/Ecore,
independientemente de React, Blockly, backend y LLM. No contiene estudiantes,
actividades, adaptación ni feedback pedagógico. Los sensores representan
condiciones del futuro dominio, sin implementar GridWorld.
Fase 2, incluida Blockly → EMF, no está iniciada.

Proyecto: `mde/com.project.mde.programming.model`. Bundle ID:
`com.project.mde.programming.model`; nombre: `MDEdu Programming Model`;
versión fuente: `0.1.0.qualifier`; ejecución: `JavaSE-21`.
Workspace externo: `%LOCALAPPDATA%\EclipseWorkspaces\MDEdu-fase1`.

## EPackage, clases y herencia

Fuente: `model/programming.ecore`. Name y nsPrefix: `programming`.
nsURI: `https://mdedu.espoch.edu.ec/model/programming/1.0`.
No existía namespace equivalente documentado; se adoptó el autorizado.
Paquete Java: `com.project.mde.programming`.
Inventario: **17 EClasses, 2 abstractas, 4 EEnums, 24 features propias**.
Ninguna EClass está marcada como interface.

| Clase | Superclase | Features propias (tipo y multiplicidad) |
| --- | --- | --- |
| Program | — | name: EString [1]; statements: Statement [0..*] |
| Statement (abstracta) | — | — |
| Move | Statement | — |
| TurnLeft | Statement | — |
| TurnRight | Statement | — |
| VariableDeclaration | Statement | name: EString [1]; type: ValueType [1]; initialValue: Expression [0..1] |
| Assignment | Statement | target: VariableDeclaration [1]; value: Expression [1] |
| Repeat | Statement | count: Expression [1]; body: Statement [0..*] |
| While | Statement | condition: Expression [1]; body: Statement [0..*] |
| If (concreta) | Statement | condition: Expression [1]; thenBranch: Statement [0..*] |
| IfElse | If | elseBranch: Statement [0..*] |
| Expression (abstracta) | — | — |
| Literal | Expression | type: ValueType [1]; value: EString [1] |
| VariableReference | Expression | declaration: VariableDeclaration [1] |
| Comparison | Expression | operator: ComparisonOperator [1]; left/right: Expression [1] cada uno |
| BooleanExpression | Expression | operator: BooleanOperator [1]; left: Expression [1]; right: Expression [0..1] |
| SensorExpression | Expression | sensor: SensorKind [1] |

`IfElse` hereda condition y thenBranch sin duplicarlas. Program es raíz concreta.
Esta definición sustituye las propuestas preliminares de Fase 0 (por ejemplo
Repeat.times); los documentos históricos se conservan intactos.

## Containment y referencias

Son containment: `Program.statements`, `VariableDeclaration.initialValue`,
`Assignment.value`, `Repeat.count/body`, `While.condition/body`,
`If.condition/thenBranch`, `IfElse.elseBranch`, `Comparison.left/right` y
`BooleanExpression.left/right`. Las colecciones son ordenadas y únicas.

`Assignment.target` y `VariableReference.declaration` son referencias obligatorias
**no containment** a VariableDeclaration: no guardan un nombre como sustituto ni
duplican la declaración. La resolución estructural no determina el alcance léxico.

## Enumeraciones y decisiones

| EEnum | Literales |
| --- | --- |
| ValueType | INTEGER, BOOLEAN |
| ComparisonOperator | EQUAL, NOT_EQUAL, LESS_THAN, LESS_OR_EQUAL, GREATER_THAN, GREATER_OR_EQUAL |
| BooleanOperator | AND, OR, NOT |
| SensorKind | FRONT_CLEAR, LEFT_CLEAR, RIGHT_CLEAR, AT_GOAL, HAS_KEY, ON_KEY, DOOR_AHEAD, DOOR_OPEN |

Literal.value es EString léxico, interpretado según ValueType; no EJavaObject.
Repeat.count es Expression para admitir futuros literales o referencias.
EPackage, clases, features y enums tienen documentación mediante anotaciones
`http://www.eclipse.org/emf/2002/GenModel`.

## Restricciones semánticas diferidas

Ecore valida estructura, no todas las reglas de un programa. Quedan pendientes:

- Nombres válidos y únicos en su alcance; definición del alcance de variables.
- Referencias solo a declaraciones visibles y declaradas según las reglas futuras.
- Compatibilidad de tipos en Assignment y Comparison.
- Condiciones booleanas de If, IfElse y While.
- Repeat con expresión INTEGER y conteo válido/no negativo cuando aplique.
- Compatibilidad léxica entre Literal.value y ValueType.
- NOT con un operando; AND/OR con dos.
- Análisis de posibles ciclos infinitos.

No se implementó OCL ni un validador semántico. No se usaron Xtext, ATL o Acceleo.

## GenModel y regeneración

`model/programming.genmodel` se creó con **EMF Generator Model → Ecore model**,
cargando `platform:/resource/com.project.mde.programming.model/model/programming.ecore`.

| Propiedad | Valor |
| --- | --- |
| Model Plugin ID | com.project.mde.programming.model |
| Base Package del GenPackage | com.project.mde |
| Model Directory | /com.project.mde.programming.model/src-gen |
| Compliance Level | 21.0 |

Importar el proyecto existente sin copiarlo al workspace. Abrir el GenModel,
seleccionar la raíz y ejecutar **Generate Model Code**. Si cambia Ecore,
recargar primero el GenModel desde ese Ecore. No generar Edit/Editor.
Refrescar, revisar metadata PDE, JavaSE-21, compilador 21, UTF-8 y versión del
bundle; ejecutar **Project > Clean**, revisar Problems y el diff.

Ecore/GenModel son fuente de verdad. Los **44 fuentes** de `src-gen/` se versionan:
interfaces, enums, factory/package, implementaciones, switch y adapter factory.
No fueron editados manualmente. Binarios, `bin/`, `target/` y workspace se ignoran.

El manifest conserva dependencias generadas `org.eclipse.core.runtime` y
`org.eclipse.emf.ecore` (reexport); Common se resuelve transitivamente.
El modelo generado no importa XMI. La utilidad de validación añade Common, Ecore
y Ecore XMI a su classpath. No hay dependencias Maven ordinarias EMF ni bundles
Xtext/ATL/Acceleo añadidos.

## Cuatro fixtures XMI

En `examples/`, con extensión `.programming`:

| Archivo | Contenido |
| --- | --- |
| sequence-basic.programming | Move, Move, TurnLeft, Move, TurnRight |
| variables-basic.programming | contador INTEGER inicial 0; Assignment a esa declaración con Literal 1 |
| conditionals-basic.programming | IfElse HAS_KEY; then Move; else TurnLeft |
| loops-basic.programming | Repeat INTEGER 3 con Move/TurnRight; While FRONT_CLEAR con Move |

Se crearon con ProgrammingFactory y se serializaron con EMF XMI.
Assignment.target se guarda como `//@statements.0`. Tras reabrir apunta a la
declaración contenida del nuevo ResourceSet, sin duplicarla. INTEGER es el valor
enum predeterminado: EMF puede omitirlo en XML y recuperarlo al cargar.

La utilidad `mde/validation/ValidateProgramming.java` carga, inspecciona, valida,
guarda, descarga y reabre cada recurso con EMF. Compara estructura y referencias;
las copias quedan bajo `target/roundtrip/`. Los fixtures originales no se modifican
al verificar. Ver [instrucciones reproducibles](../mde/validation/README.md).

## Build headless

Desde `mde/`, Maven 3.9.16 y Temurin 21.0.12.1:

```powershell
mvn.cmd --batch-mode --no-transfer-progress clean verify
```

Parent `mde/pom.xml`: Tycho **5.0.4**, p2
`https://download.eclipse.org/releases/2026-09/`, win32/win32/x86_64, JavaSE-21.
El módulo usa packaging eclipse-plugin; `0.1.0-SNAPSHOT` corresponde a
`0.1.0.qualifier`. El IDE usa Java 25 embebido; no se cambió JAVA_HOME global.
No se modifican backend, frontend ni sus builds o wrappers.

Producto: `com.project.mde.programming.model/target/com.project.mde.programming.model-0.1.0-SNAPSHOT.jar`.
El qualifier se calcula al construir; no se promete identidad binaria entre builds.
Ver [evidencia real](VERIFICACION_FASE_1.md).
