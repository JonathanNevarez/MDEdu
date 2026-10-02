# Verificación Fase 3 — Acceleo M2T

Fecha: 01/10/2026. Checkpoint inicial verificado:
`0e3b6b8105a766ed967cc56e5e3e60dee565e4da` (`feat: connect blockly editor to emf model`).
main y origin/main coincidentes; working tree limpio antes de editar.

## Entorno y prueba técnica mínima

Instalación inspeccionada: Eclipse Modeling Tools 2026-09, Acceleo 4.2.2,
AQL 8.1.2; Java global Temurin 21.0.12.1+1, Maven 3.9.16,
Tycho 5.0.4, Node v24.19.0. Sin actualizaciones de plugins.
Se inspeccionaron el documento y los launchers incluidos en los bundles Acceleo 4.2.2.

Antes de completar templates, sequence-basic.programming produjo un program.js
no vacío con `// Statements: 5` y `function runProgram(runtime)`.
La prueba demostró carga del metamodelo/XMI, template principal y file generation:
`MTL validation: []`, `Acceleo files=1, diagnostic=Diagnostic OK`, BUILD SUCCESS,
salida 0 (21:08:03 -05:00). Se sustituyó después por el generador completo.

Estructura, módulo y comandos reproducibles en [diseño](FASE_3_M2T_ACCELEO.md).

## Validación automática real

`mvn.cmd --batch-mode --no-transfer-progress clean verify`, desde mde:
BUILD SUCCESS, salida 0, 27.873 s, 21:14:09 -05:00.
Reactor: MDEdu MDE SUCCESS; Programming Model SUCCESS; Acceleo Generator SUCCESS.
JUnit: **25 tests, 0 failures, 0 errors, 0 skipped**.
`install` posterior también BUILD SUCCESS, 19.136 s, 21:17:35 -05:00.

AcceleoValidator: lista de mensajes vacía en todas las generaciones válidas:
**0 errors, 0 warnings, 0 infos**. Acceleo generation diagnostic: OK.
Sin líneas WARNING en el log del clean verify principal.

| Fixture existente Fase 1 | Resultado snapshot |
| --- | --- |
| sequence-basic.programming | PASS: move, move, turnLeft, move, turnRight |
| variables-basic.programming | PASS: contador, inicial INTEGER 0, assignment desde target real |
| conditionals-basic.programming | PASS: IfElse HAS_KEY, Move / TurnLeft |
| loops-basic.programming | PASS: Repeat 3 (Move, TurnRight), While FRONT_CLEAR (Move) |

Golden files en `mde/com.project.mde.programming.generator/expected/`.
Cada salida A y B tiene exactamente los mismos bytes que su golden (sin normalizar):

| Golden / salida canónica A y B | SHA-256 idéntico |
| --- | --- |
| `conditionals-basic.expected.js` | `a3158b1841dd6b516f211f126c9b5a214e8d92d72cbc0197b513c3d211d65da9` |
| `loops-basic.expected.js` | `6f7b147a8649f861fc577c095c8f28fbf576877c5754bb563e3ac86c10e574d9` |
| `sequence-basic.expected.js` | `15f3d7558138343c89e9fd69ced3921d51e52f53ef32c08bb49105fdec09bfe8` |
| `variables-basic.expected.js` | `5904b09883dd6ecc2efc3bebceb5f133208c1e5409e14ae6462bdd2c93b8dc9b` |

Cobertura: las 14 EClasses concretas Statement/Expression, Program raíz,
6/6 ComparisonOperator, 3/3 BooleanOperator, 8/8 SensorKind.
Pruebas adicionales: referencias reales con nombres iguales/anidadas/adelantadas,
null inicial, literal BOOLEAN, programa vacío, rechazo de EMF inválido antes de generar.
Escaping malicioso PASS: comillas/backslash/CR/LF/tab/Unicode; permanece como datos.
29 salidas de la suite pasan node --check, exit 0; no se ejecutó runProgram.
Salidas temporales en target/generation-tests, nunca versionadas.

## Incidencias corregidas localmente

1. Primer comando desde directorio incorrecto: ruta relativa XMI no encontrada;
   corrección del directorio de trabajo, sin cambiar el fixture.
2. Parser Acceleo 4 exige indentación del cuerpo respecto de template/file;
   se ajustó la indentación del MTL.
3. Sintaxis `post(self.trim())` sin espacio y firmas para los tipos abstractos
   corrigieron diagnósticos estáticos; el despacho concreto se probó con ciclos.
   El fallback técnico rechaza tipos no cubiertos y no emite JavaScript.
4. exec:java en JVM Maven imprimía InterruptedException del hilo de limpieza EMF
   al terminar, aunque salía 0. Se cambió a exec:exec (JVM independiente) para el
   comando final; no se suprimieron diagnósticos ni warnings.

## Inmutabilidad: inventario SHA-256 antes = después

46 archivos comparados byte a byte mediante SHA-256: Ecore, GenModel y 44 src-gen.
Los hashes finales coinciden con el inventario inicial; ninguno se editó.

| Archivo | SHA-256 inicial y final |
| --- | --- |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/Assignment.java` | `ab004eae2d00b54055591fbdc31d48d8dc5d7910306b91f3fe23acd342670994` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/BooleanExpression.java` | `fd5517197976c8b2191ac107798c0ec33076645eb2dd8cd963db6c4a84f37c4c` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/BooleanOperator.java` | `00b1b1dfc0ef3dd7f7f9048e92356e139ac35fd234db35fa5453d882df7be95d` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/Comparison.java` | `011fa7c7aaaf042967df66cae6a2f7f6f8ccc21c8fae284e3272c07f3df5acae` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/ComparisonOperator.java` | `19f351e27ce5da3a43b347091590f3704186c2ccc2af6a3df2ad98888b8a9b5e` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/Expression.java` | `e714a58ac08ed742739b8bf4b268bcf98281c6d6cc35e6cc1c0e1c3a7be73db2` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/If.java` | `f38a8b49ae823140c3789525e60c1755981a8051bf707ce0f0bcfdfce886c012` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/IfElse.java` | `f9465b6d8803451932c7b1d25c08a7d9d18fa29018356147dc352f7f17ca1629` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/Literal.java` | `c1c64cc90bb9817d9be60ff2c6b6e2cbe49ec18734f763aa877aa6c81618a0b8` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/Move.java` | `c5db814ea69fc35a75c72a287d97334096864fe5f2b900af473014e4901a9fe6` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/Program.java` | `2efeba11b42ab9ac3b95ef354abd83a83b2803a6a4a4ef4e0ea008ea3eca04e0` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/ProgrammingFactory.java` | `55c12427b10f4bbd4e4c5d7db7562b06a89792c351eb176604949a652ed9d67c` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/ProgrammingPackage.java` | `323906ab7328c11fb051e18d5558f0a44b49bbe16984d58bdec19fba388995fc` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/Repeat.java` | `aaf5bd05aa04bd6438eaaa9f017a8a1332bae551b036b8a2eca84c7e3ce1db06` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/SensorExpression.java` | `474808acab84f1472bd93ff19f6318bf6f0eb17ac9aee79fba86fb576d7147e7` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/SensorKind.java` | `1d607911e35c3fbc5d70d8c3e4b282e2daf6c848cb886b53b12e3efbd2f8e425` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/Statement.java` | `cc7c9878f0df90b2e2dae255e5a267ae91a568cbc1dcc7e80d61739e01eeebd3` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/TurnLeft.java` | `5de12f770b344baa1fce0e9808c0b64df1411fb9e0457bc49ab4487c51f9a644` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/TurnRight.java` | `1d7683ea9c41724e92b446972241ffd461cdabcb9b81eecc3141d58a7cab2969` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/ValueType.java` | `9fadf49007ef8cf2c018da778cf7e0ae249d8752bc6afeb8ae8a46bd730ee776` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/VariableDeclaration.java` | `ba0e1327cb3971b56b856617d60fbb32acbd070e273e046f7fb6dbb48a7e3a03` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/VariableReference.java` | `975e25311c8fea2132da82f0307b412cf314ef57e15ca975fdf3ef4691cdcd4c` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/While.java` | `60c1a8bb226985d1f9a26ec159195f9e2c72c743e244e78c6f7781e0460ee381` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/AssignmentImpl.java` | `41155cf6ba343a13fbe0b17dfd8d846a8c462011b54563ac041ca590d37e1900` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/BooleanExpressionImpl.java` | `9ec2910a7c9960dacc14553647dede89c35c1f21adc30080e4b92f2a3004e0f9` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/ComparisonImpl.java` | `8dd4937c3e0bad80e880bee415325793108463603185ba9f8c9ed3b064ebb6ad` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/ExpressionImpl.java` | `d9f47acdf4c04f159f470fd2c6aa94ceeddd5306548da3a654e0f1b5c6d0364d` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/IfElseImpl.java` | `34cc9b665eda42b9152820d67e23684bdb43c9e183a6481c8369f76948ae5e99` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/IfImpl.java` | `bbb8bab0f0985f4fbe2012b0f9bf9e00a0438c942277b72cf7135fd5cbf42248` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/LiteralImpl.java` | `ab3d5b5a22c94d98252d285db6d620036a8708130e59fe81690397239a38ab41` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/MoveImpl.java` | `c8de2f77ff71cc9867af0b6c727a445d212505a8161fceb59df535053655e632` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/ProgramImpl.java` | `274f21af284ccea48939e1344f07c73b8faf2cc626d59d65d86aa3a2c4565cd5` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/ProgrammingFactoryImpl.java` | `510c273ad072e5266d3ef800389c0af2577c2ee71a2b6968f1bd3844fa20d737` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/ProgrammingPackageImpl.java` | `d1579ae893416201ed8ae7a466417424555b3a48908db75145714bec30d04ac8` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/RepeatImpl.java` | `25e341aef1901803e5b310886c4d727c27187a6d5db4b6d4d6327959e62b0f7c` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/SensorExpressionImpl.java` | `3495668b3271bb50b5a5f44531ec4376797bb21509bf912d9f1bab7aa91f7dd6` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/StatementImpl.java` | `8a5133fa8b924011dc34061926eb9ce7b2ea5c159086c514900973c092f92da5` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/TurnLeftImpl.java` | `883ce59031726ea681f9b61fa762c5ee9b269111e97058fbcc33fd256e66c198` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/TurnRightImpl.java` | `2200f9cfe5e5a278e0c0e58677744026ba7d0f5f151c4fa749f37b6cddfb5b7f` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/VariableDeclarationImpl.java` | `4dacc7d9f5c5d3973dcbc403e6a012e4d59bb1b94a8be3fc5b7668a31cebbe49` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/VariableReferenceImpl.java` | `406cab6f58e427efdf04b4f83ce1afd55b93b2362ae40fa0402852cd72de2d89` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/impl/WhileImpl.java` | `9598296bd6683398e76522c153d2f34f006aeb50d7e5e5736f431e9ae271c274` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/util/ProgrammingAdapterFactory.java` | `54bc2069da830d4adb7d5cad13a26388d4917aa5ac1b8875e5c4a13e9c2d800b` |
| `mde/com.project.mde.programming.model/src-gen/com/project/mde/programming/util/ProgrammingSwitch.java` | `df90eaa9cae2c3709b8949a45efde967de43f8fffb598c4ceb9264dbd92e1887` |
| `mde/com.project.mde.programming.model/model/programming.ecore` | `6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df` |
| `mde/com.project.mde.programming.model/model/programming.genmodel` | `b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451` |

## Headless, copia limpia y cierre técnico

Los cuatro comandos compile exec:exec documentados: BUILD SUCCESS, salida 0;
node --check de cada program.js: salida 0. Bytes/hashes coinciden con los golden.
No reapareció InterruptedException.

Prueba de fuentes limpias: copia externa de 79 archivos fuente de mde (versionados
más los nuevos fuentes de Fase 3), sin target, bin, workspace ni caché.
Comando ejecutado con rutas temporales externas:

```powershell
mvn.cmd --batch-mode --no-transfer-progress -f <copia>/mde/pom.xml "-Dmaven.repo.local=<repositorio-Maven-nuevo>" clean verify
```

BUILD SUCCESS, salida 0, 01:58 min, 21:19:25 -05:00;
25 tests, 0 failures/errors/skipped, 29 syntax checks. Sin WARNING.
Dependencias descargadas desde repositorios declarados; sin copiar caché p2/Maven
ni artefactos MDE instalados. Outputs A/B iguales a los golden byte a byte.

Búsqueda estática en fuentes y outputs: 0 coincidencias de eval(, new Function,
Function(, javascriptGenerator o blockly/javascript.
Sin ejecución de código generado, backend, frontend, GridWorld, PostgreSQL ni
Maven verify del backend. DTO/metamodelo/src-gen e históricos Fase 0/1/2 intactos.
Una lectura auxiliar de logs con UTF-8 falló porque PowerShell los escribió UTF-16;
se leyó su encoding real sin cambiar fuentes ni resultados.
FASE 3 COMPLETADA Y VALIDADA técnicamente; FASE 4 NO INICIADA.
El commit y la sincronización remota se verifican tras guardar esta evidencia,
sin insertar aquí el hash del propio commit.

Git diff --check: salida 0. Git avisa conversión LF→CRLF según configuración
existente para documentos/POM/Java; no es un error de whitespace. MTL y golden
quedan fijados a LF mediante .gitattributes local del generador.
