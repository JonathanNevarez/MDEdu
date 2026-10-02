# Verificación Fase 10 — 02/10/2026

## Partida y alcance

HEAD inicial `3b54a0ee540343f77e2b88cfd8a127f6ca66273d`, main=origin/main,
working tree limpio; origin https://github.com/JonathanNevarez/MDEdu.git.
No se modificaron metamodelos previos, fuentes generadas anteriores, gramática,
Acceleo, reglas, policies, prompts, catálogos ni V1–V4. Se compararon 158 archivos
protegidos con baseline SHA-256 previo a la edición: cero diferencias.

V5 fue la única excepción de persistencia autorizada explícitamente por el usuario:
programa validado por intento y hash; no persistencia UI ni backfill. Se actualizaron
las aserciones de conteo de migraciones de cuatro a cinco, sin cambiar pruebas de dominio.

## Modelo y ATL reales

Java 21, UiPackage/UiFactory y **28 fuentes src-gen** generadas por EMF, sin edición manual.
Se usó el launcher headless de la instalación Eclipse existente, workspace externo
`MDEdu-fase10-generation`, heap 384 MiB, application `org.eclipse.emf.codegen.ecore.Generator`,
import del nuevo plugin, `-model -autoBuild false`. Seis EClasses, diez enums, namespace
`https://mdedu.espoch.edu.ec/model/ui/1.0`.

14 ejemplos versionados: cuatro task, cuatro abstract, cuatro concrete y dos configuraciones.
`UiPipelineTest` carga seeds reales, compila ATL mediante Atl2006Compiler, ejecuta
EMFVMLauncher y comprueba los ocho elementos y originId. También prueba flags deshabilitados.
Cada modelo hace validación Diagnostician → save → unload → reload → validación y comparación
estructural con el ejemplo versionado. La compilación y las transformaciones no usan UI interactiva.

Comandos realizados (Maven Wrapper desde raíz, PATH de sesión, MAVEN_OPTS=-Xmx384m):

```text
backend/mvnw.cmd -f mde/pom.xml -pl com.project.mde.ui.model,com.project.mde.ui.transformations -am verify
backend/mvnw.cmd -o -f mde/pom.xml clean install -DargLine=-Xmx384m
backend/mvnw.cmd --batch-mode --no-transfer-progress -o -f mde/pom.xml clean verify -DargLine=-Xmx384m
```

Reactor: programming, generator Acceleo, learning, adaptation, DSL Xtext, context, UI,
transformations. ATL 2 tests PASS; Acceleo 25 PASS; DSL/Xtext 22 PASS.

## Backend

`mvnw.cmd test -DargLine=-Xmx384m`: **239 tests, 0 failures, 0 errors, 0 skipped**.
`mvnw.cmd --batch-mode --no-transfer-progress verify -DargLine=-Xmx384m`:
**239 unit/MVC + 46 IT, BUILD SUCCESS**.

| Integración | Tests |
|---|---:|
| BackendBootstrapIT | 4 |
| LearningIT | 10 |
| AdaptationIT | 3 |
| AdaptationManagerIT | 8 |
| FeedbackIT | 13 |
| NoKeyFeedbackIT | 1 |
| OpenAIFeedbackIT (servidor HTTP local) | 1 |
| UiConfigurationIT | 6 |

La proyección tiene 12 tests unitarios (diez acciones, A/B, capacidades y destino bloqueado).
Los seis IT nuevos comprueban snapshot original, propietario, integridad, Acceleo real,
legacy sin código, pistas/repetición, refresh, **100 respuestas/fingerprints idénticos**,
acceso bloqueado, safe default sin alterar aprendizaje y dos historias independientes.
El último ajuste de etiqueta del foco ejecutó de nuevo los 12 unitarios y seis IT UI: PASS.
El feedback persistido, StudentModel y AdaptationDecision no se reescriben desde UI.

## Frontend y full-stack

`npm.cmd run typecheck`: PASS. `npm.cmd test -- --maxWorkers=1`: **53 PASS / 7 archivos**.
`npm.cmd run build`: PASS. `npm.cmd run test:e2e -- --workers=1`: **8 PASS**, Chromium,
backend DISABLED y PostgreSQL Compose real. Tras el ajuste de etiqueta y la prueba explícita
de DTO manipulado, `playwright test tests/e2e/adaptive.spec.ts --workers=1`: **3 PASS**.
Con backend reiniciado en FAKE: los tres escenarios adaptativos también **PASS**.
No se proporcionó API key, no se hicieron llamadas pagadas. Feedback observed source:
FAKE en una ejecución y FALLBACK en DISABLED; llmUsed=false en ambas.

Las pruebas cubren renderer único, DOM A/B, HintPanel, detalle del feedback, CodePanel,
Luma, navegación, Transitioner, errores, XSS y un hook que hace exactamente config→feedback→config,
sin solicitudes nuevas al rerender. Las capturas de Chromium se revisaron visualmente.
No se encontró asset Luma previo; estrella de texto original, sin chat ni textbox.

Tres E2E nuevos:

1. Dos contextos independientes, historiales reales de LOOPS manual/correcto, configuraciones
   distintas, pista/Luma/repetición, refresh y feedback persistido sin OpenAI.
2. Nueve éxitos de SEQUENCES → decisión real ADVANCE → VARIABLES. DTO de cliente alterado
   a LOOPS: mapa lo bloquea y POST /api/attempts sigue devolviendo 409.
3. Configuración controlada A/B en el mismo bundle: código oculto/visible, pista expandida,
   texto `<script>` escapado y reduced motion con animationName=none.

La regresión de Blockly real construye LOOPS manual y después un Repeat, conservando
functional/pedagogical correctness y el patrón anterior. La prueba antigua se acotó al
panel de evaluación para distinguirlo del nuevo panel de pista que puede repetir el mismo texto.

Configuraciones de los dos estudiantes (IDs omitidos; misma aplicación):

```json
{
  "ca": {
    "configuration": {
      "configurationVersion": 1,
      "activityId": "LOOPS",
      "showCodePanel": false,
      "hintPanelMode": "COMPACT",
      "feedbackDetailLevel": "STANDARD",
      "activityLayout": "STANDARD",
      "enabledAssistance": true,
      "navigationMode": "REPEAT",
      "difficultyMode": "STANDARD",
      "tutorMode": "HINT",
      "transitionMode": "SUBTLE",
      "nextActivityId": null,
      "repeatCurrentActivity": true,
      "hintStage": "CONCEPTUAL_HINT",
      "feedbackMessage": "Tu programa repite manualmente varias veces las mismas instrucciones. Prueba a representar esa repetición con un ciclo.",
      "generatedCodeVisible": false
    },
    "fingerprint": "05d1604768cd81151c01510e9b2c0eabc347a91713b26bd81dcc5524de4ce9cd",
    "source": "FALLBACK"
  },
  "cb": {
    "configuration": {
      "configurationVersion": 1,
      "activityId": "LOOPS",
      "showCodePanel": false,
      "hintPanelMode": "HIDDEN",
      "feedbackDetailLevel": "STANDARD",
      "activityLayout": "STANDARD",
      "enabledAssistance": false,
      "navigationMode": "STAY",
      "difficultyMode": "STANDARD",
      "tutorMode": "HIDDEN",
      "transitionMode": "NONE",
      "nextActivityId": null,
      "repeatCurrentActivity": false,
      "hintStage": "NONE",
      "feedbackMessage": null,
      "generatedCodeVisible": false
    },
    "fingerprint": "e782ca5e56539ff3cad0f00afedf6f5546a7c92230f8eb58d9162d823bb3afc6",
    "source": null
  }
}
```

El caso controlado B añade showCodePanel=true, generatedCodeVisible=true y EXPANDED;
el caso A mantiene false/false/HIDDEN. Las pruebas de backend verifican que SHOW_CODE_VIEW
y HIDE_CODE_VIEW producen esos valores y que el texto real proviene del programa guardado
pasado por Program EMF y el Acceleo existente. El código nunca se ejecuta.

## Hashes SHA-256

| Archivo | SHA-256 |
|---|---|
| `mde/com.project.mde.programming.model/model/programming.ecore` | `6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df` |
| `mde/com.project.mde.programming.model/model/programming.genmodel` | `b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451` |
| `mde/com.project.mde.learning.model/model/learning.ecore` | `3b7e10dff04fc7af3ec91649986322271023afb4207dd57de3c9f13f4fa2eb45` |
| `mde/com.project.mde.learning.model/model/learning.genmodel` | `1deea0b68799173dd419a227408fb9a33aecf8e49bfe20cb662de0a1926297be` |
| `mde/com.project.mde.adaptation.model/model/adaptation.ecore` | `ed3f63b5227e07421f696a04690becb4778758ed7e3ed7c0c39d9ddb58eb8243` |
| `mde/com.project.mde.adaptation.model/model/adaptation.genmodel` | `b22b13a6ce00657c86fa87d6ab7d99199b04b1f1bd291c6e32e019519d2c68ff` |
| `mde/com.project.mde.context.model/model/context.ecore` | `4d9ccb0d4f7920c4ed1b970d7b589eb8410ae914c3a97390b5b03479efd36741` |
| `mde/com.project.mde.context.model/model/context.genmodel` | `27b1191776acf0fb8f5bf19880b71210f108647bcff2d150dabb0a44e0073079` |
| `mde/com.project.mde.ui.model/model/ui.ecore` | `07f35993cc78d88177ba1014ac5d00a19a2369a10094f30a145342fb6afc6be5` |
| `mde/com.project.mde.ui.model/model/ui.genmodel` | `62f87f852ba2e193b49d83ba3ea8665ce4424a77c601b3a06641b730e669ce32` |
| `mde/com.project.mde.adaptation.dsl/src/main/java/com/project/mde/adaptation/dsl/AdaptationRules.xtext` | `df841202185952e797651068514e958fad04090ffd6b4eaf1a0dd8cd3ad4d683` |
| `mde/com.project.mde.ui.transformations/transformations/AbstractUI2ConcreteUI.atl` | `a39f7761588aa7ed5327da69fea421c10fd27d8159eeaef912ac97541a293a64` |
| `mde/com.project.mde.ui.transformations/transformations/TaskAndDomain2AbstractUI.atl` | `65679185d34feb210013c1fcb4f5d381d450f7d3826706fd0316cf092bf69d69` |
| `backend/src/main/resources/llm/llm-policy.v1.json` | `08c3986b31b2e1a1381baae8f4f1ac71a283135fb93f136f311261d274dbaf4a` |
| `backend/src/main/resources/llm/unknown-case-tags.v1.json` | `9f7e5209cbd1faae8f372d1c4c4bc3c8c83706603b08132374ef9e6df94a57e2` |
| `backend/src/main/resources/llm/prompts/pedagogical-feedback-v1.txt` | `a6c7bf084a58569fc74c9f2587d126c88716867af7c798d284159722bae63eda` |
| `backend/src/main/resources/llm/prompts/unknown-case-v1.txt` | `2f08841297b50a00f8985c211720e4ab6bd60ab27d3277491f230ec0505ca4c4` |
| `backend/src/main/resources/db/migration/V1__bootstrap.sql` | `4663db0737c03a53e4ce76b467d56492a275d786648eec80ec0ae2839b55fe62` |
| `backend/src/main/resources/db/migration/V2__learning_model.sql` | `a4d2c3d6e83156632d9d18699bfb3ba86b361b0fe96972756a82d75d7317f1ee` |
| `backend/src/main/resources/db/migration/V3__adaptation_decisions.sql` | `61a6ea358ed941a73ac798620765154f2f02dedea415f5ce7e10fa65822f4825` |
| `backend/src/main/resources/db/migration/V4__feedback_records.sql` | `0e195e93055157f33e9f7554fad4f9aa5ada598c0b2f1655bada7f7df16ebc9a` |
| `backend/src/main/resources/db/migration/V5__attempt_programs.sql` | `bf30ab6bc1d88bd6ce639c7793b0e8306b23bc2cb80abfd06edc2ea9c88db774` |

## Incidencias resueltas y límites

- El launcher eclipsec inicial no terminó; se detuvo solo ese proceso propio y se usó
  java -jar equinox launcher de la misma instalación existente, sin instalar nada.
- ATL requirió comillas simples y registrar la instancia del metamodelo usada por los modelos.
  Las pruebas detectaron ambos problemas; la cadena real quedó verde.
- Se corrigieron acentos mal codificados en el componente nuevo y el acceso a getComputedStyle
  desde el contexto DOM del test TypeScript. Se repitieron las pruebas afectadas.
- Spring traduce el error de integridad del repositorio; la prueba verifica su causa concreta.
- Se tradujo el foco técnico a etiqueta del concepto para no exponer identificadores internos.
- Persisten advertencias conocidas de Mockito/agent, colores de terminal y chunk Blockly >500 kB;
  no son fallos. No se hizo refactor global ni se modificó la configuración de Windows.
- Dificultad solo de presentación; cero variantes pedagógicas nuevas, cuatro actividades,
  cero refuerzos. Luma usa asset sencillo sustituible. Intentos antiguos carecen de código.

Pruebas pesadas secuenciales, heap Java/Node 384 MiB y Chromium un worker. No se diagnosticó
la causa de los apagados previos ni se cambiaron memoria virtual, drivers o ajustes globales.
Logs reales en TEMP/mdedu-phase10-*.log; capturas/JSON en frontend/test-results y copia
FAKE en TEMP/mdedu-phase10-fake-evidence. No se versionan logs, dumps, .env ni resultados.

Fase 11, Meta-UI y telemetría no iniciadas.

## Generación CLI y cierre operativo

`backend/mvnw.cmd -o -f mde/com.project.mde.programming.generator/pom.xml exec:exec`
con `sequence-basic.programming` y salida `target/phase10-cli`: BUILD SUCCESS,
`MTL validation: []`, `Acceleo files=1`, `Diagnostic OK`.
`node --check target/phase10-cli/program.js`: exit 0.
SHA-256 `15f3d7558138343c89e9fd69ced3921d51e52f53ef32c08bb49105fdec09bfe8`,
idéntico al conocido de fases anteriores.

Se detuvo únicamente el backend creado para estas pruebas. `docker compose stop postgres`
conservó el volumen; no quedan contenedores ejecutándose. Preview/Chromium de pruebas cerrados.
Docker Desktop se devuelve al estado detenido que tenía al comenzar. No se usó down -v.
Los puertos 8080/4173 quedaron sin listeners de la tarea.

Cierre Git autorizado: `feat: add adaptive mde user interface`, únicamente main, sin force.
La evidencia de hashes de commit/remoto se entrega después del push para evitar autorreferencias.

Revisi?n del ?ndice: se elimin? una l?nea vac?a final del MANIFEST nuevo se?alada por
`git diff --cached --check`; sin editar fuentes EMF generadas. El check final termin? con c?digo 0.
