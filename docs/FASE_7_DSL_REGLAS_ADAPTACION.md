# Fase 7 — DSL de reglas de adaptación

## Representación y generación

`mde/com.project.mde.adaptation.model/model/adaptation.ecore` es la única
representación canónica de reglas. Namespace:
`https://mdedu.espoch.edu.ec/model/adaptation/1.0`.
El plugin EMF separado genera 44 fuentes Java con el GenModel Java 21.
La opción `typeSafeEnumCompatible=false` produce enums Java convencionales.
Las 16 EClasses y seis enums están descritos en [MODELOS_MDE](MODELOS_MDE.md).

`mde/com.project.mde.adaptation.dsl` es un artefacto Maven headless separado.
La gramática `src/main/java/com/project/mde/adaptation/dsl/AdaptationRules.xtext`
**importa** el namespace; no contiene `generate` ni crea un AST intermedio.
Las reglas del parser retornan las EClasses del metamodelo importado.
Xtext 2.44.0 y MWE2 2.27.0 coinciden con la instalación Eclipse inspeccionada.
Maven ejecuta GenerateAdaptationRules.mwe2 en generate-sources y regenera lexer,
parser, acceso a gramática, validación base, serializador y registro standalone.

La generación importa adaptation.genmodel mediante URI platform:/resource relativa
al reactor. El workflow se entrega a MWE2 como URI de archivo para admitir rutas
Windows con espacios. No necesita Eclipse UI ni un workspace previamente abierto.
El backend consume el JAR DSL y el modelo por dependencias Maven normales; no hay
systemPath, rutas absolutas locales ni copia de JAR/src-gen al backend.

Se versionan `adaptation.model/src-gen` y `adaptation.dsl/src/main/xtext-gen`,
incluidos `.xtextbin`, `.g` y `.tokens`, con la convención de fuentes generadas del
repositorio. No se editaron lexer/parser ni clases EMF manualmente.
Los stubs de extensión de Xtext residen en src/main/java y la validación manual
EMF en adaptation.model/src/.../validation.

## Validación

`HeadlessRules` crea un injector Xtext con el catálogo de IDs de la aplicación y
un ResourceSet nuevo por parseo. Usa `IResourceValidator` con `CheckMode.ALL`;
rechaza errores sintácticos/semánticos con mensaje, línea y columna. Devuelve un
AdaptationRuleSet generado real, no un DTO ni texto reinterpretado.

`AdaptationRulesValidator` usa @Check y la política compartida `AdaptationModels`.
Esta comprueba IDs únicos/no vacíos, versiones positivas, prioridad 0..100,
evento/condición/acciones obligatorios, atributos y operadores compatibles,
valores tipados, conceptos/patrones existentes y parámetros permitidos/requeridos.
Diagnostician añade restricciones estructurales y multiplicidades EMF. Los enums
cierran eventos, operadores y acciones: nombres desconocidos producen errores del
parser. Las comparaciones usan ELong, EBigDecimal, EBoolean o EString según Value.

Los Concept IDs provienen del enum Concept de evaluación; Pattern IDs del
PatternCatalog real cargado desde patterns.v1.json. No se introduce otro catálogo
pedagógico. ContextAttribute es el único catálogo de atributos/tipos, compartido
por el validador y la proyección runtime.

Los ejemplos `reforzar-ciclos.adaptation` y `dominio-alto.adaptation` son XMI:
load → validate → save → unload → reload → validate, con igualdad EMF comprobada.
AdaptationDecision/AdaptationExplanation existen formalmente y se prueban como
objetos representables, pero no se producen decisiones finales.

## Contexto real

`RuleContextFactory.create` recibe StudentModel EMF posterior al intento, el último
Attempt de ese snapshot, EvaluationResult Fase 5 y el **mismo MasteryUpdater.Change**
de Fase 6. Rechaza estudiante/intento/evaluación incompatibles y estado desactualizado.
No recibe entidades JPA ni calcula otra política de aprendizaje.

`RuleEvaluationContext` es un record inmutable de 14 campos. Toma los contadores,
mastery, pistas acumuladas y promedio de ConceptMastery del concepto actual;
currentResolutionTime del último Attempt; flags y patrones de EvaluationResult.
Tiempos en milisegundos. Convierte double existente mediante BigDecimal.valueOf;
los literales DSL son BigDecimal y se comparan con compareTo, sin igualdad binaria.

`repeatedErrorPattern` procede exclusivamente de la razón REPEATED_ERROR_PATTERN
de MasteryUpdater.Change. Fase 6 ya comparó los patrones contra los cinco intentos
previos del mismo concepto con la política V1. No se calcula mediante recentErrorPatterns
del snapshot posterior, pues esa lista incluye el intento actual y daría falsos
positivos. No se cambia learning.ecore ni se inventa consecutiveSuccesses.

## Evaluación ECA

`AdaptationRuleLoader` carga y valida rules-v1.adapt al inicializar Spring. Un
archivo inválido hace fallar explícitamente el arranque. Mantiene el modelo validado
y entrega copias EMF para evitar modificaciones accidentales del catálogo cargado.
`RuleEvaluationService` ofrece evaluación diagnóstica explícita con contexto real;
no se añade endpoint ni se cambia el contrato de POST /api/attempts.

`EcaRuleEngine` recibe RuleSet, contexto y evento. Revalida el modelo antes de
consumirlo. Las reglas habilitadas del evento solicitado se evalúan en orden de
definición. Una notificación con nombre distinto de ATTEMPT_EVALUATED se ignora;
no se añade ese nombre al vocabulario DSL. Las deshabilitadas son válidas pero no
se evalúan. Los motivos DISABLED y EVENT_MISMATCH quedan en ignoredRules.

ConditionEvaluator recorre el árbol EMF: AND, OR, NOT, paréntesis, comparaciones y
contains. Precedencia NOT > AND > OR. Evalúa ambos operandos lógicos para conservar
evidencia completa y ordenada; las hojas bajo NOT conservan su valor observado.
No usa reflexión para leer propiedades arbitrarias ni parser alternativo.

RuleEngineResult contiene ruleSetVersion, rulesEvaluated, rulesMatched, matches,
ignoredRules y conflictsDetected. rulesEvaluated cuenta reglas habilitadas del
evento, incluso las que no coinciden. Cada RuleMatch incluye id/version/priority,
evidencia de comparaciones y ActionCandidate con ruleId/ruleVersion/priority,
índice de acción, ActionType y parámetros tipados opcionales. Las listas son
inmutables. El resultado no contiene timestamps, UUID aleatorios ni entidades JPA.

Se detecta INCREASE_DIFFICULTY frente a DECREASE_DIFFICULTY. Ambos candidatos y sus
reglas permanecen en el resultado, aunque sus prioridades sean diferentes.
No hay ganador, desempate ni eliminación por prioridad.

## Reglas y límites

Las seis semillas y todos sus umbrales viven en el DSL versionado:
[REGLAS_ADAPTACION](REGLAS_ADAPTACION.md). Se prueba un caso positivo y uno negativo
por regla, los operadores, disabled, evento distinto, conflictos y 100 resultados
semánticos idénticos. AdaptationIT conecta PostgreSQL → StudentModel/Attempt real
+ evaluación → contexto → DSL parseado → motor → candidatos.

El motor no modifica mastery, progreso, UI, actividad ni dificultad. Tampoco guarda
candidatos. SELECT_REINFORCEMENT_ACTIVITY representa una intención; siguen existiendo
cero actividades de refuerzo. No se añade migración ni tabla de adaptación.
Cuatro conceptos y cuatro actividades. Sin context.ecore, ui.ecore, LLM, temas
avanzados ni nuevos bloques. AdaptationManager y resolución/aplicación de decisiones
pertenecen a Fase 8 y no están implementados.

[Verificación real](VERIFICACION_FASE_7.md).
