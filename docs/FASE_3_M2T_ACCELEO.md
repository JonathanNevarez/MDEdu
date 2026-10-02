# Fase 3: M2T con Acceleo 4

Program EMF se transforma en texto JavaScript mediante **Acceleo 4.2.2 / AQL 8.1.2**.
M2T significa Model-to-Text: el modelo formal es la entrada y las plantillas `.mtl`
son la lógica de transformación. No se usa el generador JavaScript de Blockly.
La generación es independiente del backend, del frontend y de un workspace Eclipse.

## Estructura y tecnología

Proyecto `mde/com.project.mde.programming.generator/`:

- `pom.xml`: proyecto Maven jar integrado en el reactor Tycho 5.0.4.
- `src/main/resources/com/project/mde/generator/main.mtl`: módulo, templates y queries.
- `src/main/java/com/project/mde/generator/Generate.java`: carga XMI, valida EMF,
  resuelve/valida el módulo y llama a AcceleoUtil.generate.
- `GenerationGuards.java`: rechazo técnico de nodos sin despacho; no genera JavaScript.
- `src/test/java/com/project/mde/generator/GeneratorTest.java`: 25 pruebas JUnit.
- `expected/*.expected.js`: cuatro golden files revisados, UTF-8/LF.
- `target/generation-tests/` y `target/headless/`: salidas temporales ignoradas.

Se consume el plugin `com.project.mde.programming.model`, sin duplicar Ecore, con nsURI
`https://mdedu.espoch.edu.ec/model/programming/1.0`.
El módulo es `com::project::mde::generator::main`; el template público `generate(Program)`
marcado `@main` escribe `program.js`. Los templates privados se despachan por EClass;
las queries calculan identidad y escape. Se usa `post(self.trim())` en expresiones.

El launcher sigue `standaloneMain.mtl` y `generationPom.mtl` incluidos en el tooling
**4.2.2 instalado**, y la API programática de esa misma versión. El agregado Maven
oficial `org.eclipse.acceleo:acceleo:4.2.2` declara AQL 8.1.2.
EMF common/ecore/xmi quedan fijados a 2.46.0/2.43.0/2.41.0, como en Fase 2.

Fuentes oficiales consultadas:

- [Documentación Acceleo](https://help.eclipse.org/latest/topic/org.eclipse.acceleo.aql.doc/doc/index.html)
  (para la implementación se inspeccionó también el documento incluido en el bundle 4.2.2).
- [Release Acceleo 4.2.2](https://download.eclipse.org/acceleo/updates/releases/4.2/R202603201315/).
- [Maven exec: JVM independiente](https://www.mojohaus.org/exec-maven-plugin/examples/example-exec-for-java-programs.html).

## Mapping y contrato futuro

Cada archivo tiene cabecera fija y una única entrada `function runProgram(runtime)`.
No contiene imports/exports, fecha, rutas de máquina, UUID ni nombre del programa como código.

| EClass | JavaScript generado / contrato |
| --- | --- |
| Move | `runtime.move()` |
| TurnLeft | `runtime.turnLeft()` |
| TurnRight | `runtime.turnRight()` |
| VariableDeclaration | `runtime.declareVariable(key, name, type, initialValue)`; null si no hay valor |
| Assignment | `runtime.assignVariable(target.key, value)` |
| Repeat | `runtime.repeat(count, () => { body })` |
| While | `runtime.whileLoop(() => condition, () => { body })` |
| If | `runtime.ifThen(() => condition, () => { thenBranch })` |
| IfElse | `runtime.ifElse(() => condition, () => { thenBranch }, () => { elseBranch })` |
| Literal | `runtime.literal(type, lexicalValue)`; ambos strings |
| VariableReference | `runtime.getVariable(declaration.key)` |
| Comparison | `runtime.compare(operator, left, right)` |
| BooleanExpression | `runtime.boolean(operator, left, right)`; NOT solo recibe left |
| SensorExpression | `runtime.sensor(sensor)` |

Program es la raíz; Statement y Expression son abstractas. Las listas mantienen
orden, profundidad e indentación de dos espacios por bloque. No se desenrollan ciclos.
Las condiciones de While/If/IfElse se difieren mediante callbacks; count y los
operandos de expresiones se evalúan según las llamadas mostradas. El runtime futuro
será responsable de tipos, evaluación, alcance, límites de pasos y efectos.
Fase 3 no demuestra semántica de ejecución ni implementa ese runtime.

Operadores Comparison: EQUAL, NOT_EQUAL, LESS_THAN, LESS_OR_EQUAL, GREATER_THAN,
GREATER_OR_EQUAL. Boolean: AND, OR, NOT. Sensores: FRONT_CLEAR, LEFT_CLEAR,
RIGHT_CLEAR, AT_GOAL, HAS_KEY, ON_KEY, DOOR_AHEAD, DOOR_OPEN.
Se preservan los literales exactos de los EEnums y se prueba cada valor.

## Identidad, escape y determinismo

Las claves `v1`, `v2`, etc. son el índice (base 1) de la declaración referenciada
entre las VariableDeclaration contenidas en Program, en orden estructural EMF.
Assignment.target y VariableReference.declaration se usan directamente, incluso
con nombres duplicados, declaraciones anidadas y referencias adelantadas.
No se busca por texto ni se usan hashCode, aleatoriedad o timestamps.
Esto define identidad estática, no reglas futuras de alcance/ejecución.

Nombres y valores lexicales son datos entre comillas dobles. La query `jsString`
escapa primero backslash y luego comillas, CR, LF y tabulador; conserva Unicode.
No se convierte un Literal a código crudo ni el nombre de Program a identificador.
La prueba incluye comillas, `throw new Error`, comentario, backslash, CR/LF/tab y
`á漢字`: la carga permanece dentro de strings y pasa `node --check` sin ejecutarse.
La validación EMF es estructural; un literal INTEGER con texto malicioso sirve para
probar escape, no implica que sea un entero semánticamente válido.

Salida UTF-8 sin BOM, LF e indentación estable. Para cada ejemplo se comparan
bytes A/B y golden sin normalización posterior; se registran SHA-256 idénticos.
Los snapshots no se actualizan automáticamente.

## Build y generación headless

Requisitos: Java 21, Maven 3.9.16, Node 24.19.0 disponibles en PATH; red para las
dependencias declaradas. No requiere abrir Eclipse ni usar su instalación como classpath.
Desde `mde/`:

```powershell
mvn.cmd --batch-mode --no-transfer-progress clean verify
mvn.cmd --batch-mode --no-transfer-progress install
```

`verify` compila el plugin y el generador y ejecuta las 25 pruebas; `install`
publica los artefactos locales necesarios para invocar el módulo aisladamente.
Desde `mde/com.project.mde.programming.generator/`:

```powershell
mvn.cmd --batch-mode --no-transfer-progress compile exec:exec "-Dmodel=../com.project.mde.programming.model/examples/sequence-basic.programming" "-Doutput=target/headless/sequence-basic"
node --check target/headless/sequence-basic/program.js
```

Cambiar `sequence-basic` por `variables-basic`, `conditionals-basic` o `loops-basic`
reproduce los otros tres ejemplos. El launcher acepta exactamente entrada y carpeta
salida. `exec:exec` inicia una JVM independiente con el classpath Maven declarado.
Cualquier diagnóstico MTL o generación distinto de OK hace fallar el proceso.

## Validación y límites

Cuatro snapshots exactos, doble generación por ejemplo, 6 operadores comparativos,
3 booleanos, 8 sensores, referencias/identidad, strings maliciosos, programa vacío,
literal BOOLEAN y rechazo de EMF inválido: **25 tests / 29 archivos JS por suite**.
Cada JS generado pasa `node --check`; no se llama a runProgram.
Ver [evidencia real](VERIFICACION_FASE_3.md).

No se integra el launcher con Spring ni con la UI. No se ejecutan GridWorld,
PostgreSQL, ATL, Xtext, LLM, eval ni Function. Fase 4 no iniciada.
