# Subsistema MDE: metamodelo de programación EMF

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
el plugin; no constituye una suite semántica. `target/` y `bin/` se ignoran.
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
