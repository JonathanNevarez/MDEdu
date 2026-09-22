# Subsistema MDE clásico — estructura reservada

Esta carpeta contiene únicamente documentación en Fase 0. No hay metamodelos,
modelos generados ni transformaciones ejecutables todavía. Los contratos REST
JSON no serán sustitutos de EMF/Ecore.

| Carpeta | Fuente futura | Primera fase |
| --- | --- | --- |
| `metamodels/` | Ecore, GenModel y restricciones formales | 1 |
| `models/` | XMI válido/inválido y fixtures versionados | 1 |
| `generated/` | Salidas EMF/Xtext, separadas del código manual | 1/7 |
| `acceleo/` | Plantillas M2T y pruebas de generación | 3 |
| `xtext/` | Gramática, validadores y parser del DSL | 7 |
| `atl/` | M2M de tareas/dominio → AUI → CUI y trazas | 10 |

Antes de agregar dependencias se debe verificar la matriz EMF, Xtext, MWE2,
ATL, Acceleo y Java 21. Agregar módulos Maven/Tycho separados únicamente cuando
la integración lo exija. Ver [MODELOS_MDE](../docs/MODELOS_MDE.md).
