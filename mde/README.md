# Subsistema MDE: modelo EMF y generador Acceleo

Fase 1 implementa secuencias, variables, condicionales y ciclos en el proyecto
PDE/EMF `com.project.mde.programming.model`, independiente de UI y backend.
Namespace: `https://mdedu.espoch.edu.ec/model/programming/1.0`.

`model/programming.ecore` es la fuente de verdad; `model/programming.genmodel`
configura la generación Java 21. Los **44 fuentes** de `src-gen/` se versionan
y no se editan manualmente. Para regenerar: abrir GenModel, seleccionar la raíz,
**Generate Model Code**, refrescar y **Project > Clean**. No generar Edit/Editor.
Ver [diseño y regeneración](../docs/FASE_1_METAMODELO_PROGRAMACION.md).

`examples/` contiene cuatro fixtures XMI `.programming`. La utilidad
[validation/ValidateProgramming.java](validation/ValidateProgramming.java) comprueba
estructura y round-trip mediante EMF real; [instrucciones](validation/README.md).

Desde `mde/`, con Maven 3.9.16 y Temurin 21.0.12.1:

```powershell
mvn.cmd --batch-mode --no-transfer-progress clean verify
```

El reactor independiente usa Tycho 5.0.4 y p2 SimRel 2026-09. Compila y empaqueta
el plugin y el generador, y ejecuta 25 pruebas M2T con 29 node --check.
`target/` y `bin/` se ignoran. Node 24.19.0 debe estar en PATH.
No depende de los servicios ni del build backend/frontend.
**Fase 1 no utiliza Xtext, ATL ni Acceleo**, aunque estén instalados; OCL diferido.

Los siguientes placeholders de Fase 0 se conservan. En Fase 1 las rutas reales
son `model/`, `examples/` y `src-gen/` dentro del plugin, sustituyendo las rutas
tentativas de la tabla histórica:

| Carpeta | Fuente futura | Primera fase |
| --- | --- | --- |
| `metamodels/` | Ecore, GenModel y restricciones formales | 1 |
| `models/` | XMI válido/inválido y fixtures versionados | 1 |
| `generated/` | Salidas EMF/Xtext, separadas del código manual | 1/7 |
| `acceleo/` | Plantillas M2T y pruebas de generación | 3 |
| `xtext/` | Gramática, validadores y parser del DSL | 7 |
| `atl/` | M2M de tareas/dominio → AUI → CUI y trazas | 10 |

La propuesta histórica está en [MODELOS_MDE](../docs/MODELOS_MDE.md).
La definición concreta vigente de programación está en el documento de Fase 1.

## Generador Fase 3

`com.project.mde.programming.generator` es un proyecto Maven separado con Acceleo
4.2.2 / AQL 8.1.2. `src/main/resources/com/project/mde/generator/main.mtl` contiene
la lógica M2T. Generate.java carga/valida XMI y lanza Acceleo.
Cuatro snapshots revisados en expected/*.expected.js se versionan.
Outputs de tests/CLI bajo target se ignoran.

Tras clean verify, desde mde instalar artefactos locales para la CLI:

```powershell
mvn.cmd --batch-mode --no-transfer-progress install
cd com.project.mde.programming.generator
mvn.cmd --batch-mode --no-transfer-progress compile exec:exec "-Dmodel=../com.project.mde.programming.model/examples/sequence-basic.programming" "-Doutput=target/headless/sequence-basic"
node --check target/headless/sequence-basic/program.js
```

Sustituir sequence-basic por variables-basic, conditionals-basic o loops-basic.
No abre Eclipse ni ejecuta el programa generado. Tests: snapshots, determinismo,
operadores/sensores, referencias EMF y escaping.
[Contrato](../docs/FASE_3_M2T_ACCELEO.md),
[evidencia](../docs/VERIFICACION_FASE_3.md). Fase 4 no iniciada.
