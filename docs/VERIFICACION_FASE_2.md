# Fase 2: verificación y cronología — 01/10/2026

Estado técnico final: **COMPLETADA Y VALIDADA. Fase 3 NO INICIADA.**
Typecheck, 19 tests frontend, build, 2 E2E, 25 pruebas backend, 4 IT,
MDE y validación visual full-stack superados. El cierre técnico y la revisión
Git se detallan al final. Se conserva íntegra la cronología de bloqueos.

## Checkpoint e inspección

HEAD inicial: `44600d55fad10b58af812a35032807ad1b08dba4`.
Git inicial: main, sincronizada con origin/main, working tree clean.
Revisados status, hashes, branch -vv y log -10.
Modelo confirmado: 17 EClasses, 4 EEnums, 44 fuentes Java, GenModel compliance 21.0,
nsURI `https://mdedu.espoch.edu.ec/model/programming/1.0`.
No se regeneraron ni modificaron Ecore, GenModel o src-gen.

Frontend existente: React Router con inicio y catch-all, AppLayout compartido,
HomePage/NotFoundPage y styles.css global. Vitest/Testing Library conserva dos
pruebas de navegación; Playwright startup.spec prueba el frontend compilado en
4173. No había Blockly. No se reestructuró React ni se modificaron sus archivos.

## Build e instalación del modelo

Desde mde/: `mvn.cmd --batch-mode --no-transfer-progress clean install`.
Tycho 5.0.4, Maven 3.9.16, Java 21.0.12.1; **BUILD SUCCESS**, salida 0,
8.687 s; final 2026-10-01T12:56:29-05:00.
Compilados 44 fuentes. Bundle-Version 0.1.0.202610011756.
GAV real instalado: `com.project.mde:com.project.mde.programming.model:0.1.0-SNAPSHOT`.
El consumer POM generado por Tycho es Maven convencional y sin parent Tycho.
Conservó cinco dependencias transitivas OSGi/Felix no necesarias para este consumidor.
El aviso de diez dependencias p2 no mapeadas no impidió el build.

## Probe externo real

Directorio: `%TEMP%\mdedu-emf-consumer-probe`, conservado para inspección.
POM Java 21 con dependencia del GAV instalado, excluyendo sus transitivas,
y runtimes oficiales de Maven Central:

- org.eclipse.emf:org.eclipse.emf.common:2.46.0
- org.eclipse.emf:org.eclipse.emf.ecore:2.43.0
- org.eclipse.emf:org.eclipse.emf.ecore.xmi:2.41.0

Sin systemPath, copias de src-gen, repositorios terceros o dependencia directa de target/JAR.
Las versiones coinciden con el runtime EMF de Fase 1 y se verificaron en los
metadatos de Maven Central. Tycho genera el POM de consumo mediante
[update-consumer-pom](https://tycho.eclipseprojects.io/doc/latest/tycho-packaging-plugin/update-consumer-pom-mojo.html).

El primer comando tuvo un error de parsing de PowerShell: Unknown lifecycle
phase `.outputFile=classpath.txt`. Se corrigió entrecomillando el argumento:

```powershell
mvn.cmd --batch-mode --no-transfer-progress compile dependency:build-classpath '-Dmdep.outputFile=classpath.txt'
```

Resultado: **BUILD SUCCESS**, 10.800 s. Después se ejecutó Probe con java y el
classpath de dependencias resueltas por Maven: **salida 0**.
ProgrammingFactory creó Program y Move; Diagnostician devolvió severity=0;
XMIResourceFactoryImpl serializó el Program con el namespace correcto y Move.
Salida final: `EMF CONSUMER PROBE OK; diagnostic severity=0`.
Solo después de este éxito se cambió backend/pom.xml.

## Instalación Blockly bloqueada

Desde frontend/: `npm.cmd install --save-exact blockly@13.3.0`.
Salida **1**, ERESOLVE:

```text
Found: jsdom@30.1.0
Could not resolve dependency:
peer jsdom@">=27.4.0 <30.0.0" from blockly@13.3.0
```

No se utilizaron --force ni --legacy-peer-deps, no se cambió jsdom ni se sustituyó
Blockly por otra versión. package.json y package-lock.json permanecen intactos.
El conflicto requiere decidir una combinación compatible antes de continuar.

## Trabajo parcial conservado, NO validado

Durante la instalación se prepararon backend/pom.xml y cinco clases:
ProgramDto, ProgrammingModelController, ContractException,
ProgrammingModelMapper y ProgrammingModelService. Es trabajo pendiente de revisión
y pruebas, no funcionalidad declarada validada.
El borrador incluye contrato V1 con records Jackson, mapper en dos pasos,
ProgrammingFactory, Diagnostician y serialización XMI UTF-8; endpoint propuesto
POST /api/programming/models con respuestas 200/400/422. No se ha comprobado
compilación, endpoint, identidad de variables ni round-trip de este nuevo código.

No hay bloques, editor, persistencia, fixtures compartidos o pruebas de Fase 2
implementados. No se ejecutaron frontend test/build/E2E, backend test/verify,
Testcontainers ni runtime full-stack. No se añadieron tablas, JPA o Flyway.
No se inició Docker, PostgreSQL, Spring, frontend o Eclipse.

## Estado de cierre parcial

Comparación con HEAD: Ecore, GenModel y los 44 src-gen sin cambios; configuración
Git de whitespace intacta. git diff --check sin errores en cambios versionados.
No staging, commit ni push; HEAD sigue en 44600d5. Working tree con el borrador
backend y documentación del bloqueo. Se detuvo el trabajo al conocer ERESOLVE,
conforme a la condición de la solicitud. No se declara Fase 2 completada.

## Continuación autorizada: resolución npm y nuevo bloqueo

Se conservaron los ocho archivos iniciales y el índice vacío. HEAD continuó en
44600d5, diff --check pasó. Node v24.19.0 y npm 11.17.0 confirmados.
Metadata real obtenida mediante npm view:

```json
{"version":"13.3.0","peerDependencies":{"jsdom":">=27.4.0 <30.0.0"},"engines":{"node":">=22"}}
{"version":"29.1.1","engines":{"node":"^20.19.0 || ^22.13.0 || >=24.0.0"}}
```

`npm.cmd install --save-dev --save-exact jsdom@29.1.1`: código 0; 3 paquetes
añadidos, 1 eliminado, 10 cambiados; 117 auditados; 22 funding; 0 vulnerabilidades.
jsdom permaneció en devDependencies. npm ls jsdom y npm ls --depth=0: salida 0,
sin invalid/extraneous. No se cambiaron las otras versiones directas autorizadas.

Regresión ANTES de Blockly: npm.cmd test (2/2, 1 archivo, 30.86 s),
npm.cmd run typecheck y npm.cmd run build, todos salida 0. Build Vite 420 ms,
JS 260.89 kB (gzip 82.96), CSS 1.45 kB (gzip 0.73).

`npm.cmd install --save-exact blockly@13.3.0`: código 0; 1 paquete añadido,
118 auditados; 22 funding; 0 vulnerabilidades. Blockly en dependencies,
jsdom en devDependencies. npm ls/explain confirmó Blockly directo 13.3.0,
jsdom directo dev 29.1.1 y peer satisfecho; Vitest acepta jsdom como peerOptional.
No se usó --force, --legacy-peer-deps o npm audit fix.

Regresión inmediatamente DESPUÉS de instalar Blockly: test 2/2 (1.21 s),
typecheck y build, todos salida 0; build Vite 136 ms con tamaños idénticos.
package.json y package-lock.json reflejan las dos versiones exactas.
Incidencia npm: **detectada → analizada → resuelta**.

Se implementaron los 16 bloques autorizados, DTO V1 TypeScript, extracción pura
con diagnósticos, fixtures JSON compartidos, guardado/restauración oficial Blockly,
cliente HTTP y página /laboratorio. Key: mdedu.blockly.workspace.v1; envelope
version/programName/workspace. Los IDs de las declaraciones se preservan.
No se importó un generador JavaScript. Se preparó un escenario Playwright sin
eliminar startup.spec. Assets Blockly se sirven localmente desde el paquete.

El borrador backend anterior se conservó; se añadieron pruebas de mapper,
referencias reales, XMI y controller. El test CORS existente restringe su slice
al ProbeController para aislarlo del nuevo controller. Estos cambios backend
siguen **SIN COMPILAR NI PROBAR** y no se declaran válidos.

Validación frontend de la implementación: `npm.cmd test`, salida 0,
**19/19 pruebas, 2 archivos**, 1.22 s, 13:18:27. Incluye registro de bloques,
los cuatro fixtures, expresiones/sensores, errores controlados, referencias a
declaraciones posteriores y save/clear/load con DTO equivalente e IDs estables.

Después, `npm.cmd run typecheck`: **salida 2**, cuatro errores:

```text
blocks.ts(30,21): TS2345: import de blockly/msg/es incompatible con setLocale;
  la propiedad default no satisface el índice string.
blocks.ts(52,63): TS2345: DeclarationField incompatible con Field<string | undefined>.
blocks.ts(56,18): TS2345: DeclarationField incompatible con Field<string | undefined>.
blocks.ts(74,21): TS2683: this tiene tipo any implícito en el validador.
```

Se detuvo el trabajo conforme a la instrucción explícita. No se corrigieron ni
ocultaron estos errores, no se cambió strict y no se ejecutó el build posterior.
Build final, E2E, backend test/verify, BackendBootstrapIT, build MDE final y
full-stack quedan pendientes. El éxito del baseline no es un build del editor.
Ecore, GenModel y src-gen intactos; sin DB/Flyway nuevo, sin servicios iniciados.
Sin staging/commit/push; Fase 2 incompleta y Fase 3 no iniciada.

## Intento autorizado de corrección TypeScript: regresión fallida

Se reprodujeron los cuatro diagnósticos completos antes de editar:

- blocks.ts(30,21), TS2345: actual `typeof import("blockly/msg/es")`, esperado
  `{ [key: string]: string }`; la propiedad default del namespace es un objeto,
  no string.
- blocks.ts(52,63), TS2345: actual DeclarationField, esperado
  `string | Field<string | undefined>`; validator_ tiene
  `FieldValidator<string> | null`, incompatible con
  `FieldValidator<string | undefined> | null`.
- blocks.ts(56,18), TS2345: mismo conflicto de DeclarationField/FieldValidator.
- blocks.ts(74,21), TS2683: this implícitamente any dentro del validator.

Se inspeccionaron las declaraciones instaladas de Blockly 13.3.0:
core/msg.d.ts (setLocale recibe diccionario string), core/field.d.ts
(FieldValidator<T> recibe T y devuelve T|null|undefined), field_dropdown.d.ts
(FieldDropdown extends Field<string>, MenuGeneratorFunction con this FieldDropdown,
MenuOption como tupla o separator), inputs/input.d.ts (appendField<T>) y msg/es.d.ts.
También se leyó es.mjs: exporta mensajes nombrados; es.js contiene el objeto CommonJS.

Correcciones aplicadas solo en blocks.ts:

- Locale: se intentó import por defecto. TypeScript lo acepta como diccionario,
  pero el runtime de Vitest no lo proporciona. **Esta corrección es incorrecta
  en runtime y permanece pendiente de resolver.**
- DeclarationField: se conservaron las dos sobrecargas de doClassValidation_
  de FieldDropdown (string obligatorio y string opcional). Esto evita que la
  inferencia de appendField amplíe el tipo de valor a string|undefined.
- Opciones construidas como Blockly.MenuOption[]; comprobación de separator;
  el acceso al subtipo usa instanceof, eliminando el cast anterior.
- Validator con `this: Blockly.Field<string>` y `next: string`, retorno string|null.

Sin any añadido, ts-ignore, doble cast, relajación strict, cambios tsconfig
o versiones de paquetes. Se conservaron las decisiones del contrato y los IDs.
Como blocks.ts todavía no está versionado, git diff ordinario no muestra su
contenido; la copia anterior al fix se conserva fuera del repo en
%TEMP%/mdedu-blocks-before-typefix.ts para comparar el cambio.

`npm.cmd run typecheck`: **código 0**.
Luego `npm.cmd test`: **código 1**, 1 suite fallida y 1 pasada, 2 pruebas pasadas,
1.16 s, inicio 13:29:31. programming.test.ts no ejecutó sus casos:

```text
TypeError: Cannot convert undefined or null to object
at keys node_modules/blockly/core/msg.ts:24:10
at registerBlocks src/features/programming/blockly/blocks.ts:31:11
Blockly.setLocale(es)
```

El import por defecto entrega undefined en este runtime. No se describe la
corrección como validada ni se conservan los 19 PASS previos como estado actual.
Se detuvo inmediatamente el desarrollo por la condición del usuario; no se
ejecutó build, backend ni E2E. No se alteraron tests para ocultar el fallo.
No staging/commit/push; Fase 2 incompleta, Fase 3 no iniciada.

## Namespace locale autorizado: runtime correcto, tipado pendiente

Se preservó todo el working tree y se confirmó HEAD 44600d55fad10b58af812a35032807ad1b08dba4.
El import previo era `import es from 'blockly/msg/es'`. Se inspeccionó package.json
de Blockly 13.3.0: exports ./msg/* contiene types ./msg/*.d.ts,
import ./msg/*.mjs y default ./msg/*.js. es.d.ts reexporta ./msg;
es.mjs importa internamente es.js y exporta mensajes nombrados, sin default ESM.

Se cambiaron exclusivamente estas dos líneas de producto:

```typescript
import * as Es from 'blockly/msg/es';
Blockly.setLocale(Es);
```

DeclarationField y validator no fueron modificados. No casts, normalización,
Object.assign, spread, cambios de versiones o tsconfig.

Primero `npm.cmd test`: **código 0, 2 suites PASS, 19 tests PASS, 0 fallidos,
0 omitidos**, 1.18 s, inicio 13:34:11. El fallo runtime del import por defecto
quedó resuelto y los 19 PASS son nuevamente evidencia actual.

Después `npm.cmd run typecheck`: **código 2**, un único diagnóstico:

```text
src/features/programming/blockly/blocks.ts(31,21): error TS2345:
Argument of type 'typeof import("blockly/msg/es")' is not assignable
to parameter of type '{ [key: string]: string; }'.
Property 'default' is incompatible with index signature.
Type 'typeof import("blockly/msg/es")' is not assignable to type 'string'.
```

La representación TypeScript del namespace sigue incluyendo default de tipo
objeto, pese a que los exports ESM de mensajes funcionan en runtime. No se
ocultó el error ni se probaron imports alternativos tras fallar la validación.
Se detuvo el trabajo: sin build, E2E, backend test/verify ni MDE build final.
Los anteriores errores DeclarationField/validator no reaparecieron.
Sin staging/commit/push; Fase 2 no completada, Fase 3 no iniciada.

## Object rest autorizado: locale resuelto; E2E bloqueado

Se conservó `import * as Es from 'blockly/msg/es'` y se añadió exclusivamente:

```typescript
const { default: defaultExport, ...esMessages } = Es;
void defaultExport;
Blockly.setLocale(esMessages);
```

Este ajuste de interoperabilidad excluye la propiedad default del namespace
tipado y conserva los mensajes ESM. Sin casts, mocks, Object.assign, cambios de
idioma, versiones o tsconfig. DeclarationField y validator no se modificaron.

Validaciones reales, en el orden autorizado:

- npm.cmd run typecheck: **salida 0**.
- npm.cmd test: **salida 0, 2 suites, 19 PASS, 0 fallidas/omitidas**,
  1.18 s, inicio 13:41:12. No hubo excepción de setLocale.
- npm.cmd run build: **salida 0**, Vite 8.3.0, 41 módulos, 253 ms.
  JS principal 261.38 kB (gzip 83.17), LaboratoryPage 727.34 kB (gzip 200.11),
  CSS 2.00 kB (gzip 0.91). Assets locales Blockly incluidos en dist ignorado.
  Warning real: chunk mayor de 500 kB; no se ocultó ni se optimizó en este paso.

Luego `npm.cmd run test:e2e` reconstruyó correctamente (230 ms) y ejecutó
Chromium: **1 PASS, 1 FAIL, salida 1**, 35.5 s. startup.spec pasó (3.2 s).
laboratory.spec confirmó heading, Blockly SVG y toolbox visibles, pero falló:

```text
Test timeout of 30000ms exceeded.
locator.selectOption: waiting for getByLabel('Ejemplo', { exact: true })
tests/e2e/laboratory.spec.ts:9:53
```

No se alcanzaron las comprobaciones E2E de guardar, limpiar y restaurar;
no se declara ese flujo validado en navegador. El runner conservó error-context.md
y trace.zip bajo test-results ignorado. Avisos NO_COLOR/FORCE_COLOR observados.
Se detuvo el trabajo en este fallo, sin editar el test ni repetirlo, y sin iniciar
backend test/verify, MDE build final o full-stack. Sin staging, commit ni push.
Fase 2 incompleta; Fase 3 no iniciada.

## Continuación: reparación controlada y cierre técnico

El usuario autorizó continuar ante errores locales reparables, preservando los
límites de metamodelo, arquitectura, versiones y persistencia. No se hizo reset,
stash ni eliminación de cambios. `git diff --check` inicial: salida 0.

### Causa reproducida del timeout

Primero se ejecutó solo `npm.cmd run test:e2e -- laboratory.spec.ts`, sin cambios:
salida 1, mismo timeout de 30 s en selectOption. Se inspeccionó el React real y el
DOM renderizado durante esa ejecución. El control era un select nativo, sin id,
name, htmlFor, aria-label ni aria-labelledby; estaba correctamente asociado por
estar dentro del label. Opciones: sequence, variables, conditionals, loops.
La opción `value="variables"` existía.

Mediciones reales de locators:

```text
getByLabel('Ejemplo', {exact:true}).count() = 0
getByLabel('Ejemplo').count() = 1
getByRole('combobox', {name:'Ejemplo', exact:true}).count() = 1
select.labels[0].textContent = 'Ejemplo SecuenciaVariablesCondicionalesCiclos'
```

El label envolvente incluye texto de las opciones para getByLabel; el nombre
accesible del combobox es Ejemplo. Era un problema de selector exacto, no de
label sin asociación, opción ausente ni carga lenta. Se cambió únicamente el
test `frontend/tests/e2e/laboratory.spec.ts` a:

```typescript
page.getByRole('combobox', { name: 'Ejemplo', exact: true })
    .selectOption('variables');
```

La siguiente ejecución llegó a restore y reveló una strict mode violation:
`getByRole('status')` encontraba el anunciador accesible de Blockly y el mensaje
de la aplicación. Se delimitó por el texto Workspace restaurado. La comprobación
antigua por clases internas de Blockly se reemplazó por figuras accesibles
`/^Declarar, contador,/` y `/^Asignar a, contador,/`, observadas en el snapshot.
También se comprueba que ambas existen al cargar, desaparecen al limpiar y
reaparecen al restaurar. No timeout aumentado, waitForTimeout, force, nth ni XPath.
La UI productiva no necesitó modificación para estas correcciones.

### Regresión frontend final

| Ejecución | Resultado real |
| --- | --- |
| laboratory.spec focalizado | 1 archivo, 1 PASS, 0 FAIL/skipped; test 3.4 s, total 5.6 s; salida 0 |
| npm.cmd run test:e2e | 2 archivos, 2 PASS, 0 FAIL/skipped; startup 2.8 s, laboratorio 3.5 s, total 5.6 s; salida 0 |
| npm.cmd run typecheck posterior | PASS, salida 0 |
| npm.cmd test posterior | 2 suites, 19 PASS, 0 FAIL/skipped; 1.46 s, inicio 13:54:15; salida 0 |
| npm.cmd run build posterior | PASS, Vite 8.3.0, 41 módulos, 220 ms; salida 0 |

El E2E usa botones de UI para guardar/limpiar/restaurar, lee localStorage solo
para comparar (no lo simula ni inyecta), recarga la página y compara el envelope
completo antes/después. Sin pageerror. Los unit tests prueban declarationId y
targetDeclarationId estables y DTO equivalente, también con referencias adelantadas.
Bundle final: principal 261.38 kB / gzip 83.17; laboratorio 727.34 kB / gzip 200.11;
CSS 2.00 kB / gzip 0.91. Warning >500 kB conservado como deuda futura; sin cambios
de bundler, manualChunks, umbral o dependencias. Avisos NO_COLOR/FORCE_COLOR no
bloqueantes. DeclarationField y validator de la corrección previa intactos.

### Backend existente y validaciones reales

Se revisó el borrador, sin recrearlo: ProgramDto, controller, mapper y servicio
separados. Factory real, mapper de dos pasadas, registro por request,
Diagnostician real y serialización XMI mediante ResourceSet/Resource. No se
necesitó corregir el código backend en esta continuación.

`mvnw.cmd --batch-mode --no-transfer-progress test`: **BUILD SUCCESS**, salida 0,
25 pruebas, 0 failures/errors/skipped, 10.164 s; final 13:54:48.

- ProgrammingModelTest: 15 PASS. Cuatro fixtures, orden de secuencia,
  Assignment.target assertSame, VariableReference.declaration assertSame,
  referencias adelantadas, roundtrip XMI y ambas referencias tras reload;
  Repeat/While/If/IfElse/Comparison/BooleanExpression/SensorExpression;
  IDs duplicados, referencias ausentes, contrato incompleto, aridad y EMF real.
- ProgrammingModelControllerTest: 7 PASS. 200 JSON/valid=true/XMI/resumen;
  cinco 400 (vacío, JSON roto, versión 2, kind desconocido, destino ausente);
  un 422 legítimo por VariableDeclaration.name nulo, requerido por Ecore.
- CorsConfigurationTest histórico: 3 PASS. Su slice se limita explícitamente
  al ProbeController para no cargar el nuevo controller con dependencias ajenas.
  No se debilitó CORS ni se cambió su lista de orígenes.

Primer `verify`: **BUILD FAILURE**, salida 1, 8.584 s (13:55:20).
Docker estaba apagado; docker info no encontraba dockerDesktopLinuxEngine.
Failsafe registró 1 error de preparación, no cuatro pruebas ejecutadas.
Se inició el Docker Desktop ya instalado mediante `docker desktop start` y se
confirmó Engine 29.8.0. No instalación ni cambio de configuración.

Focalizado `-Dit.test=BackendBootstrapIT failsafe:integration-test failsafe:verify`:
**BUILD SUCCESS**, 4 PASS, 0 fallos/errores/omitidas, 15.024 s (13:56:08).
Después `mvnw.cmd --batch-mode --no-transfer-progress verify`, sin skips:
**BUILD SUCCESS**, salida 0, 19.545 s, final 13:56:51.
Surefire 25 PASS; Failsafe 4 BackendBootstrapIT PASS, 11.223 s.
Los cuatro métodos históricos se conservaron y usaron Testcontainers real.
Warnings Mockito/Byte Buddy por auto-attach y CDS observados; no se ocultaron.
Logs completos conservados fuera del repo en TEMP (mdedu-phase2-verify.log,
mdedu-phase2-it.log y mdedu-phase2-verify-final.log); reports bajo target ignorado.

### MDE final e inmutabilidad

Desde mde: `mvn.cmd --batch-mode --no-transfer-progress clean verify`:
**BUILD SUCCESS**, salida 0, 13.122 s, final 13:58:20. Reactor de dos proyectos
SUCCESS; compilados 44 fuentes. Aviso conocido de 10 dependencias p2 no mapeadas
a Maven; no afecta al consumidor probado. Log mdedu-phase2-mde.log en TEMP.

`git diff --exit-code HEAD -- .../model .../src-gen`: salida 0.
Comparación de bytes con `git cat-file --filters HEAD:ruta`: **46/46 idénticos**
(Ecore, GenModel y 44 fuentes). La comparación inicial con blobs crudos de Git
difería por LF frente a CRLF del checkout; se comprobó con los filtros existentes,
sin modificar atributos ni archivos. SHA-256 del checkout idéntico al HEAD filtrado:

```text
programming.ecore
6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df
programming.genmodel
b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451
```

### Full-stack visual real

Se reutilizó el PostgreSQL Compose existente, antes Exited (0), con
`docker compose up -d --wait`: Healthy. Backend arrancado desde backend con
el JAR construido por verify; frontend Vite local en 127.0.0.1:5173. Health UP.
El navegador integrado no estaba disponible; se usó Opera existente mediante
computer-use, sin instalar herramientas ni iniciar sesión.

En `/laboratorio`: editor y toolbox visibles, selección Variables, Cargar ejemplo,
declaración contador/INTEGER/0 y asignación contador/1 visibles. Guardar workspace,
Limpiar (canvas vacío), Restaurar workspace (bloques reaparecen), Generar modelo EMF.
El log del backend registra OPTIONS 200 y POST `/api/programming/models` a las
14:03:05, DTO V1 recibido, Result valid=true, diagnostics=[], statementCount=2,
Completed 200 OK. UI: Modelo válido, Instrucciones: 2. Ver XMI expandido muestra
XML UTF-8, Program, VariableDeclaration contador y Assignment con
`target="//@statements.0"`, valores 0 y 1. No XML construido manualmente.

Se detuvieron únicamente los procesos frontend/backend iniciados para esta
prueba y `docker compose stop postgres`. Estado final: PostgreSQL Exited (0),
docker ps sin contenedores, sin listeners en 5173/4173/8080/5432. Volumen preservado.
Docker Desktop continúa operativo. No Flyway V2, cambios de esquema ni JPA nuevo.

### Documentación y revisión para cierre Git

Creados FASE_2_BLOCKLY_MODELO_EMF.md, este registro y README del contrato V1;
actualizados API.md, ARQUITECTURA.md y ESTADO_PROYECTO.md. Se conserva toda la
cronología de incidentes. Sin Acceleo/MTL, ATL, Xtext, ejecución ni Fase 3.
Revisados los 34 archivos nuevos/modificados de Fase 2: ningún artefacto prohibido
ni patrón de credencial detectado; no se imprimieron secretos. package-lock
conserva instalación exacta sin force/legacy peer deps.
