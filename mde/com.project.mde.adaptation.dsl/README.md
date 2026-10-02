# AdaptationRules — Xtext headless

Xtext 2.44.0 / MWE2 2.27.0 / Java 21. Gramática y workflow en
src/main/java/com/project/mde/adaptation/dsl. Extensión .adapt.
Importa adaptation.ecore mediante su nsURI y adaptation.genmodel; sin segundo AST.

Desde mde: `mvn.cmd --batch-mode --no-transfer-progress clean install`.
generate-sources ejecuta MWE2 realmente, genera 11 Java en src/main/xtext-gen más
.xtextbin/.g/.tokens. Esas 14 salidas se versionan y no se editan manualmente.
RuntimeModule, StandaloneSetup y validator son puntos de extensión manuales.
Los stubs generator/scoping proceden de Xtext y no implementan un parser alterno.

`new HeadlessRules(new RuleCatalog(concepts, patterns)).parse(input, "file.adapt")`
retorna AdaptationRuleSet real y validado. Cada parseo tiene su ResourceSet.
El catálogo debe proceder del dominio consumidor; no se duplican los IDs en DSL.
InvalidRules expone diagnostics con línea/columna. Error sintáctico o semántico
rechaza el archivo completo. El backend lo inicializa al arranque.

Tests: 18 fixtures inválidos, parseo canónico, estructura Ecore, dos round-trips
XMI y representabilidad de Decision/Explanation. Mismo lexer/parser generado
byte a byte en build con repositorio Maven vacío, sin workspace Eclipse.
[Reglas](../../docs/REGLAS_ADAPTACION.md).
