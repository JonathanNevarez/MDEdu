# Verificación Fase 7 — 02/10/2026

## Checkpoint de partida

HEAD/main/origin/main: `877e4aa477948dd43c72e60940d28bf6aeb143bb`.
`877e4aa feat: add emf student learning model`.
Se ejecutaron git status, status --short, rev-parse corto/completo, branch -vv y
log -10 antes de modificar. Working tree clean. No reset/restore/clean/stash.
Inspeccionados reactor MDE, modelos previos, backend, política de mastery,
catálogos y documentos Fase 6/arquitectura. Sin AGENTS.md aplicable encontrado.

## Generación formal

- adaptation.ecore: 16 EClasses, seis enums; Diagnostician severity 0.
- GenModel Java 21, plugin ID com.project.mde.adaptation.model.
- Generador EMF instalado ejecutado realmente: 44 Java src-gen.
- Xtext 2.44.0 y MWE2 2.27.0 confirmados en plugins de Eclipse.
- Workflow Maven ejecuta Generating com.project.mde.adaptation.dsl.AdaptationRules
  y Generating common infrastructure; parser/lexer son generados.
- 11 Java Xtext más .xtextbin, .g y .tokens; sin segundo metamodelo inferido.
- Dos XMI: reforzar-ciclos.adaptation y dominio-alto.adaptation.

Salida de prueba real:

```text
reforzar-ciclos: load/validate/save/unload/reload/validate PASS
dominio-alto: load/validate/save/unload/reload/validate PASS
Tests run: 22, Failures: 0, Errors: 0, Skipped: 0
```

El test comprueba igualdad EcoreUtil.equals después de unload y recarga en otro
ResourceSet, además de Diagnostician y validación semántica. Decision/Explanation
se construyen únicamente como fixture formal; no hay manager que seleccione decisión.

## Xtext y diagnósticos

22 casos en XtextRulesTest: 18 archivos inválidos, parseo canónico, estructura Ecore,
round-trips y representabilidad formal de Decision/Explanation. El parseo válido
obtiene el EPackage AdaptationPackage.eINSTANCE y condiciones/acciones generadas.
Ejemplos de salidas reales:

```text
unknown-attribute.adapt: Unknown context attribute: consecutiveSuccesses; line=4 column=29
wrong-type.adapt: Value type incompatible with consecutiveFailures; line=4 column=52
wrong-operator.adapt: Operator incompatible with concept; line=4 column=14
unknown-action.adapt: no viable alternative at input 'RUN_SCRIPT'; line=5 column=13
unknown-concept.adapt: Unknown Concept ID: ADVANCED; line=4 column=17
unknown-pattern.adapt: Unknown Pattern ID: INVENTED; line=4 column=32
missing-action.adapt: At least one action is required; line=2 column=1
invalid-priority.adapt: Priority must be 0..100; line=6 column=10
invalid-version.adapt: Rule version must be positive; line=3 column=9
missing-parameter.adapt: Action requires hint level; line=5 column=6
```

Se rechazan también duplicate-rule-id, missing-condition, negative-priority,
invalid-ruleset-version, forbidden-parameter, boolean-operator,
decimal-for-integer y numeric-string. Mensajes/línea de todos se verifican en tests;
no hay crash ni carga parcial silenciosa.

## Criterio principal: archivo → modelo → ejecución

AdaptationRuleLoader abre el archivo real rules-v1.adapt, llama HeadlessRules,
valida y obtiene AdaptationRuleSet conforme al único adaptation.ecore.
EcaRuleEngineTest.criticalDslToCanonicalModelToCandidate comprueba:

```text
concept=LOOPS, consecutiveFailures=3
rulesEvaluated=6
match ReforzarCiclos; priority=80
candidato SHOW_HINT; hintLevel=CONCEPTUAL
2 comparaciones como evidencia
```

Con consecutiveFailures=2, la regla se evalúa pero no coincide ni emite acciones.
Las seis semillas tienen cada una positivo y negativo. Se prueban 12 combinaciones
numéricas enteras y 12 decimales, AND/OR/NOT/precedencia, todos los atributos,
contains presente/ausente, booleanos, diez acciones/parámetros, enabled false
**parseado desde DSL**, evento diferente y rechazo de modelo alterado inválido.

100 evaluaciones del mismo RuleSet/contexto producen JSON semántico idéntico,
con orden de definición ReforzarCiclos, ErrorRepetido, DominioAlto, PistasExcesivas,
TiempoAlto, FuncionalSinConcepto. Sin mutaciones del RuleSet. Artefacto de ejecución
ignorado: backend/target/adaptation-evidence/deterministic-result.json.

Fixture conflicto: Up priority=0 produce INCREASE_DIFFICULTY; Down priority=100
produce DECREASE_DIFFICULTY. Ambas reglas y ambos candidatos permanecen, un conflicto
se reporta con IDs [Up, Down]. No se selecciona ganador.

## PostgreSQL / Fases 5 y 6

AdaptationIT usa Testcontainers PostgreSQL real y servicios existentes, sin mocks
del contexto. A y B crean identidad, completan prerrequisitos y llegan a LOOPS.
A realiza ocho intentos correctos; B tres manuales. Se proyectan StudentModel EMF y
último Attempt y se reutilizan EvaluationResult y MasteryUpdater.Change reales.

```text
A: mastery=0.80; rulesEvaluated=6; matches=[DominioAlto]
B: mastery=0.00; rulesEvaluated=6;
   matches=[ReforzarCiclos, ErrorRepetido, FuncionalSinConcepto]
```

Se verifica igualdad EMF antes/después de evaluar y frente a nueva proyección desde
PostgreSQL: no se aplica adaptación ni se altera aprendizaje. Archivo de evidencia
ignorado: backend/target/adaptation-evidence/students-ab.json.

Ciclos manual: functionalPassed=true, requiredConceptUsed=false,
REPETITIVE_SEQUENCE_WITHOUT_LOOP, activityPassed=false. El primer intento dispara
FuncionalSinConcepto sin error repetido; el segundo incorpora ErrorRepetido según
la razón real de Fase 6. Repeat correcto conserva activityPassed=true.
Se rechaza mezclar otro estudiante o un snapshot/Change desactualizado.
Flyway sigue con dos migraciones exitosas y cero tablas adaptation.

## Comandos y resultados reales

| Comando | Resultado |
| --- | --- |
| mde: mvn.cmd --batch-mode --no-transfer-progress clean verify | BUILD SUCCESS, 31.485 s; 25 tests Acceleo +22 Xtext |
| mde: install | BUILD SUCCESS; artefactos normales consumidos por backend |
| copia externa mde, clean verify con -Dmaven.repo.local vacío | BUILD SUCCESS, 2 min 25 s; reactor completo y 47 tests |
| backend: mvnw.cmd --batch-mode --no-transfer-progress test | 185 PASS, 0 fallos/errores/skips, 14.041 s |
| backend: mvnw.cmd --batch-mode --no-transfer-progress verify | BUILD SUCCESS, 42.548 s; 185 unit/MVC +17 IT |
| BackendBootstrapIT | 4 PASS |
| LearningIT | 10 PASS |
| AdaptationIT | 3 PASS |
| EcaRuleEngineTest | 38 PASS (incluidos parametrizados) |
| frontend: npm.cmd run typecheck | PASS |
| frontend: npm.cmd test | 44 PASS, cinco archivos |
| frontend: npm.cmd run build | PASS |
| frontend: npm.cmd run test:e2e | 5 PASS, 15.7 s, Chromium contra backend y PostgreSQL reales |
| Acceleo: compile exec:exec, sequence-basic.programming | BUILD SUCCESS |
| node --check target/phase7-regression/program.js | salida 0 |

El build aislado se ejecutó bajo TEMP/mdedu-phase7-clean-build, con caché Maven/P2
nueva y sin workspace Eclipse. Sus 14 salidas Xtext coinciden byte a byte con el
build principal. No depende accidentalmente de JAR copiado o plugin del IDE.

E2E conserva laboratorio, mapa, replay, progreso tras reload, Ciclos manual/correcto
y estudiantes independientes con PostgreSQL. Backend empaquetado inició y
/actuator/health devolvió {"status":"UP"}; loader de reglas válido en startup.
No se modifica frontend funcional ni contrato REST.

## Preservación y hashes

Comparación SHA-256 contra baseline tomada antes de editar: 204 archivos
previos preservados sin diferencias (modelos, Acceleo/goldens, frontend,
ProgramDto y migraciones). Programming: 44 Java src-gen intactos.
Learning: 26 Java src-gen intactos. Adaptation: 44 nuevos Java src-gen.

| Archivo | SHA-256 |
| --- | --- |
| programming.ecore | `6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df` |
| programming.genmodel | `b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451` |
| learning.ecore | `3b7e10dff04fc7af3ec91649986322271023afb4207dd57de3c9f13f4fa2eb45` |
| learning.genmodel | `1deea0b68799173dd419a227408fb9a33aecf8e49bfe20cb662de0a1926297be` |
| adaptation.ecore | `ad0a01ceccc09903d845a51236e8035da393f0edbf314f0a1f470bdb4d4d0a3d` |
| adaptation.genmodel | `b22b13a6ce00657c86fa87d6ab7d99199b04b1f1bd291c6e32e019519d2c68ff` |
| AdaptationRules.xtext | `df841202185952e797651068514e958fad04090ffd6b4eaf1a0dd8cd3ad4d683` |

JavaScript Acceleo conocido:
`15f3d7558138343c89e9fd69ced3921d51e52f53ef32c08bb49105fdec09bfe8`.
V1/V2 intactas; sin V3. Cuatro conceptos, cuatro actividades, cero refuerzos.
Sin context.ecore, ui.ecore, AdaptationManager, resolución final, LLM o temas avanzados.

## Incidencias resueltas y límites

- MWE2 interpretaba la ruta Windows absoluta como un esquema URI; el POM usa
  project.baseUri para el archivo workflow. Verificado también con rutas distintas.
- La generación inicial usó enums compatibles con constantes antiguas. Se fijó
  typeSafeEnumCompatible=false en GenModel; el merge conservó constantes obsoletas.
  Se regeneró en carpeta vacía, guardando la salida previa fuera del repositorio.
  La revisión automática había bloqueado borrar esa carpeta generada; moverla a un
  respaldo TEMP permitió proceder sin eliminar datos. No se editaron src-gen.
- La aplicación headless EMF emitió avisos de metadata/PDE del workspace; las
  salidas se verificaron con compilación Tycho, Diagnostician y round-trips.
- Una invocación auxiliar de edición usó cwd backend con prefijo backend y no
  encontró el archivo; se corrigió la ruta. No afectó archivos ni validaciones finales.
- Se conservan avisos históricos de bundle frontend >500 kB y color de Playwright.

No quedan bloqueos técnicos. No hay endpoint nuevo; API.md permanece intacto.
La evaluación diagnóstica está disponible como servicio backend y probada con datos
reales. Todavía no se invoca para aplicar acciones durante POST /api/attempts.

## Documentos y alcance Git

FASE_7_DSL_REGLAS_ADAPTACION, VERIFICACION_FASE_7, REGLAS_ADAPTACION,
MODELOS_MDE, ARQUITECTURA, DECISIONES_ARQUITECTURA y ESTADO_PROYECTO actualizados.
READMEs de ambos proyectos explican regeneración y directorios versionados.
Cierre autorizado: feat: add xtext adaptation rule engine; solo main, sin force.
Los hashes del commit/publicación se reportan después de ejecutar Git, para evitar
una referencia autorreferencial dentro de su propio commit.

Servicios de prueba backend/PostgreSQL y Docker Desktop detenidos al cierre; volumen
PostgreSQL conservado. No se eliminan componentes internos de Docker/WSL.
