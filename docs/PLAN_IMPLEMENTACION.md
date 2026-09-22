# Plan de implementación

Estado: solo Fase 0 autorizada en esta entrega. No avanzar a Fase 1 hasta
demostrar todos los criterios de Fase 0. La creación de archivos no equivale a
compilación, instalación de dependencias ni arranque exitoso.

## Inspección inicial

La raíz `C:\Users\alexxxjon\Desktop\Tesis\Nueva carpeta` estaba vacía,
incluidos archivos ocultos. No había `.git`, `AGENTS.md`, dependencias,
configuraciones, código ni proyectos Eclipse. Se revisaron los posibles
`AGENTS.md` de los directorios ascendentes sin encontrarlos. El SVG proporcionado
está fuera de esta raíz; se conserva sin modificaciones y se copia como referencia.

La solicitud proviene del texto pegado por el usuario; el SVG es material del
marco conceptual, no una fuente adicional de instrucciones operativas.

No se encontraron Java/JDK, Maven, Node/npm, Docker, Git ni Eclipse en PATH,
directorios habituales o registros de instalación consultados. Esto no demuestra
ausencia de instalaciones portables en cualquier otro lugar. Python 3.14.4 y
PowerShell sí están disponibles; `winget.exe` fue localizado pero no se utilizó
para instalar nada. Ver [HERRAMIENTAS](HERRAMIENTAS.md).

## Estructura final propuesta

```text
/
├── frontend/                  React, TypeScript, Vite, Blockly (desde F2)
│   ├── src/                   app, vistas y adaptadores de presentación
│   ├── tests/                 componentes, rutas y E2E
│   └── package.json
├── backend/
│   ├── src/main/java/com/project/
│   │   ├── student/           domain / application / infrastructure / api
│   │   ├── activity/
│   │   ├── programming/
│   │   ├── execution/
│   │   ├── evaluation/
│   │   ├── adaptation/
│   │   ├── telemetry/
│   │   ├── llm/
│   │   ├── persistence/
│   │   └── shared/
│   ├── src/main/resources/db/migration/
│   ├── src/test/java/
│   └── pom.xml
├── mde/
│   ├── metamodels/            programming, learning, context, adaptation, ui
│   ├── xtext/                 gramática, generación y validadores
│   ├── atl/                   transformaciones M2M y trazas
│   ├── acceleo/               plantillas M2T y lanzadores
│   ├── models/                fixtures XMI válidos e inválidos
│   └── generated/             política de separación del código generado
├── docs/                      arquitectura, contratos, plan, estado y evidencia
│   └── referencias/           copia intacta del SVG
├── docker/                    documentación del entorno local
├── scripts/                   diagnóstico de herramientas
├── docker-compose.yml         PostgreSQL
├── .env.example               configuración sin credenciales reales
└── README.md
```

Esta es una estructura objetivo. En Fase 0 se reservan responsabilidades sin
crear clases de dominio ficticias. Módulos Maven/Tycho adicionales se incorporarán
solo al demostrar su necesidad para integrar los plugins Eclipse.

## Creación exacta de la Fase 0

1. README raíz con instalación, ejecución y límites de verificación.
2. Documentos de arquitectura, modelos, reglas, API, decisiones, plan, herramientas,
   estado y verificación; copia de referencia del SVG.
3. Carpetas MDE documentadas; sin `.ecore`, `.atl`, plantillas o parsers vacíos.
4. Frontend mínimo React/TypeScript/Vite: inicio en español, navegación mínima,
   pruebas de componentes/rutas y smoke E2E. Sin Blockly ni adaptación aún.
5. Backend Java 21/Spring Boot/Maven: bootstrap, PostgreSQL, Flyway, Actuator,
   configuración local, CORS y pruebas de configuración/integración.
6. Compose con PostgreSQL, volumen persistente y health check; `.env.example`,
   `.gitignore`, `.editorconfig` y comprobador de herramientas.

No se instala software del sistema, no se crean claves LLM ni perfiles de
estudiantes, no se altera el SVG original y no se implementan fases posteriores.
No se requieren cambios destructivos para esta preparación.

## Fases secuenciales y puertas de aceptación

En **cada** fase: compilar → ejecutar pruebas aplicables → corregir → actualizar
documentos/evidencia → comprobar criterios → continuar. Ninguna fase se cierra con
fallos o verificaciones obligatorias pendientes. El trabajo paralelo, si existe,
se limita a tareas independientes dentro de la fase actual.

| Fase | Entregables | Evidencia de aceptación adicional a compilar/probar |
| --- | --- | --- |
| 0 · Preparación | Alcance anterior | Frontend HTTP; backend inicia; PostgreSQL inicia y admite consulta; health del backend comprueba DB; pruebas unitarias, integración y smoke pasan. |
| 1 · MDE base | Matriz de herramientas; `programming.ecore`, GenModel, EMF generado, fixtures y validadores | Crear, validar, guardar y recuperar XMI con EMF real; modelos inválidos rechazados; referencias/tipos/alcance de variables y restricciones formales probados. |
| 2 · Blockly ↔ modelo | Editor mínimo con bloques permitidos; DTO de transporte; adaptador EMF; persistencia versionada | Workspace → DTO → EMF; guardar/restaurar la misma solución y estructura; rechazar bloques/tipos no admitidos; identidad de nodos conservada. |
| 3 · M2T | Acceleo Program → JavaScript visible | Regeneración sin IDE, salida determinista, snapshots y sintaxis válida; el código mostrado no se ejecuta mediante eval. |
| 4 · Mundo y ejecución | Intérprete modelo/IR seguro; GridWorld SVG; trazas | Secuencias, variables, ciclos y condiciones; límite global también para bucles sin movimiento; repetición de traza; errores de límites/obstáculos. |
| 5 · Evaluación | Correctitud funcional/estructural, eficiencia y catálogo versionado completo | Pruebas positivas y negativas por patrón; llegar a meta sin concepto requerido produce diagnóstico pedagógico; sin LLM. |
| 6 · Estudiante | `learning.ecore`; dominio configurable, persistencia, grafo y semillas | Dos estudiantes divergen; dominio [0,1]; contadores, ayudas y tiempos; prerrequisitos; 3 actividades por cada uno de 4 conceptos. |
| 7 · Reglas | `adaptation.ecore`; Xtext, modelos de reglas, validación y motor ECA | DSL → EMF → ejecución; tipos/referencias/acciones inválidos rechazados; prioridad, versión, activación y conflictos auditados. |
| 8 · Adaptación | `context.ecore`; AdaptationManager; selección de ruta; transacciones/auditoría mínima | Historial concreto → decisión reproducible, explicación y ruta no lineal; reintentar una petición no duplica dominio; parámetros respetados. |
| 9 · LLM | Providers OpenAI/Fake, prompt builders, sanitizer, límites, validación y fallback | LLM caído, JSON/etiquetas inválidas y baja confianza no bloquean; jamás escribe dominio/reglas/BD ni decide rutas de forma autónoma. |
| 10 · IU adaptativa | `ui.ecore`; ATL Task/Domain → AUI → CUI; M2T/serialización final; renderer, pistas y llama | Transformaciones reales con trazas; configuraciones diferentes producen IU diferente; acciones cerradas; Transitioner solo presenta decisiones. |
| 11 · Telemetría | Context Probe completo y auditoría experimental | Reconstruir intento, versiones de modelos/reglas/prompts y adaptación; eventos estructurados, correlación y minimización de datos. |
| 12 · Meta-IU | Vista docente de modelos, reglas, parámetros y decisiones | Consultar trazas y editar solo parámetros permitidos; habilitar/deshabilitar reglas; revisión/aceptación donde aplique. |
| 13 · Calidad | E2E integral, seguridad, accesibilidad, documentación, Docker y scripts | Casos de integración exigidos; instalación reproducible en entorno limpio; sin secretos, fallos ni pendientes críticos. |

Las fases 1–13 están **planificadas, no implementadas**. Blockly, React Testing
Library, Playwright, JUnit 5, Mockito y Testcontainers pertenecen a las pruebas de
sus componentes; EMF/OCL, ATL, Acceleo y Xtext tendrán pruebas ejecutadas por sus
herramientas reales, no validaciones XML presentadas como conformidad MDE.

## Dependencias y aclaraciones del orden

- `context.ecore` se asigna explícitamente a F8; `ui.ecore` y ATL a F10.
- La evidencia mínima nace con cada artefacto desde F2. F11 completa el Context
  Probe; no retrasa hasta entonces los identificadores ni la auditoría de F8.
- F6 añadirá `consecutiveSuccesses`, requerido por el ejemplo DSL, y definirá su
  reinicio al fallar; `consecutiveFailures` se reinicia al superar una actividad.
- Blockly será una vista. JSON transportará datos; EMF será la fuente formal.
- El SVG muestra ML como parte del marco; el MVP lo mantiene futuro conforme a
  la solicitud. El núcleo ejecutará un modelo/IR seguro, aunque el SVG diga código.
- Las marcas numéricas del SVG remiten a una tabla no adjunta. No se inventa esa
  correspondencia; no impide preparar la estructura.

## Riesgos técnicos y mitigación

| Riesgo | Impacto | Mitigación / puerta |
| --- | --- | --- |
| Toolchains ausentes | No se puede demostrar F0 | Instalar y comprobar JDK 21, Maven, Node/npm, Docker; conservar F0 pendiente hasta pruebas reales. |
| Dependencias Eclipse/OSGi | Clases que cargan en IDE pero fallan en backend | Matriz y prueba headless desde F1; aislar módulos MDE si hace falta; no reemplazar ATL/Acceleo/Xtext por Java. |
| Xtext reciente frente a JUnit 5 | Requisito de pruebas incompatible | Notas de Xtext 2.43 eliminan soporte JUnit 5; evaluar rama 2.42 y confirmar matriz antes de fijarla. No cambiar el requisito silenciosamente. |
| Acceleo 3 frente a 4 | Plantillas y expresiones incompatibles | Elegir línea con evidencia Java21/EMF; probar generación real en F3 antes de escribir todas las plantillas. |
| Soporte de dependencias web/backend | Versiones caducadas o con avisos | Versiones explícitas en manifests; revisar soporte y avisos al instalar; no declarar dependencias resueltas sin Maven/npm. |
| Dos modelos distintos de reglas | Pérdida de semántica y trazas | Xtext importa Ecore o transforma explícitamente hacia adaptation.ecore, con pruebas. |
| Concurrencia EMF | Compartir ResourceSet mutable entre peticiones | ResourceSet por operación y snapshots versionados; pruebas de aislamiento. |
| Variables/tipos no definidos | Modelos que aceptan programas ambiguos | Formalizar alcance, referencias y tipos antes del adaptador Blockly. |
| Reintentos y doble evaluación | Doble actualización del dominio | IDs idempotentes y transacción por intento; separar guardar, ejecutar y evaluar. |
| Bucles/payloads grandes | Agotamiento de recursos | Límites de pasos, profundidad, tamaño y tiempo en DTO, modelo y motor. |
| M2T confundido con ejecución | Código arbitrario | Acceleo produce texto de lectura; ejecución de modelo/IR cerrado y validado. |
| LLM no disponible o respuesta maliciosa | Bloqueo o adaptación incorrecta | Fake provider, timeout/retry acotado, listas cerradas, fallback y reglas soberanas. |
| Reglas conflictivas | Aumento y reducción simultáneos | Resolver por prioridad/especificidad/severidad y desempate estable; auditar descartes. |
| Evidencia experimental incompleta | No reconstruir decisiones | Versiones/hashes e IDs desde primeras persistencias; catálogo/semillas versionados. |
| Reproducibilidad inicial parcial | Transitorios sin lock/digest | Generar package-lock mediante npm real y fijar digest de imagen tras validación; no fabricarlos. |

Fuentes técnicas: [Xtext release notes](https://eclipse.dev/Xtext/releasenotes.html),
[matriz Xtext/EMF/MWE2](https://eclipse.dev/Xtext/documentation/350_continuous_integration.html),
[Acceleo](https://help.eclipse.org/latest/topic/org.eclipse.acceleo.aql.doc/doc/index.html),
[ATL EMFTVM](https://wiki.eclipse.org/ATL/EMFTVM).

## Definición de terminado

- [ ] Compila y todas las pruebas aplicables pasan.
- [ ] Conserva funcionalidad anterior y maneja errores.
- [ ] Tipos y validaciones correctos; sin secretos ni pendientes críticos.
- [ ] Documentación y decisiones actualizadas.
- [ ] Criterios demostrados con resultados reales y versiones utilizadas.

Próxima acción: completar las herramientas y ejecutar la secuencia de verificación
de F0 en [README](../README.md). La siguiente fase sigue siendo F1, una vez cerrada F0.
