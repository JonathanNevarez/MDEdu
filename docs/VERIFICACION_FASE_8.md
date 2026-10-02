# Verificación de Fase 8 — Adaptation Manager

Fecha: 2026-10-02. Partida: `e976f0fea41ebf348f620077a082e163817f5250`.
Resultados obtenidos en Windows, JDK 21, PostgreSQL real mediante Testcontainers
y Compose. Los logs y respuestas completas están en TEMP y backend/target,
ignorados por Git; este documento conserva resultados y hashes reproducibles.

## Validación ejecutada

| Comando / suite | Resultado real |
| --- | --- |
| mde: mvn --batch-mode --no-transfer-progress clean verify | BUILD SUCCESS, 28.587 s; 25 Acceleo +22 Xtext; context compila 14 fuentes |
| backend: mvnw test | BUILD SUCCESS, 10.675 s; 201 tests, 0 fallos, 0 errores, 0 omitidos |
| backend: mvnw verify | BUILD SUCCESS, 43.877 s; 201 unit/MVC +25 IT, sin fallos/errores/omitidos |
| BackendBootstrapIT | 4 PASS; PostgreSQL y migraciones hasta V3 |
| LearningIT | 10 PASS; aprendizaje y grafo preservados |
| AdaptationIT (Fase 7) | 3 PASS; ECA previo preservado |
| AdaptationManagerIT | 8 PASS; decisión real, APIs, rollback, concurrencia, idempotencia |
| ManagerDomainTest (incluido en los 201) | 16 PASS; ranking, gates, hashes, XMI y validaciones |
| frontend: npm run typecheck | PASS |
| frontend: npm test | 44 PASS, cinco archivos |
| frontend: npm run build | PASS; warning histórico de chunk Blockly >500 kB |
| frontend: npm run test:e2e | 5 PASS, 15.3 s, backend/PostgreSQL reales |
| Acceleo CLI, sequence-basic.programming | Generación PASS; node --check salida 0; golden sin cambios |

E2E conserva startup, laboratorio, aventura, estudiantes y pedagogía. Frontend no
se modificó. Health del backend UP y Vite 5173 HTTP 200 durante la prueba completa.
Acceleo, ExecutionEngine, EvaluationEngine, MasteryUpdater y ECA siguen cubiertos
por las suites de regresión de Fases 5/6/7. No se repitieron builds tras el último
PASS: los cambios de cierre son documentación y ejemplos XMI ya generados/probados.

## Casos específicos

- Prioridad descendente, especificidad (átomos; AND/OR suman, NOT conserva),
  severidad ERROR > WARNING > INFO > NONE y empate por orden fuente: PASS.
- Acciones compatibles agregadas; deduplicación exacta; distintos parámetros
  no confundidos; aumento/disminución y mostrar/ocultar incompatibles: PASS.
- Sin refuerzo real, ruta/dificultad deshabilitadas, sin sucesor, límite de pistas,
  regla deshabilitada y parámetros inválidos: PASS.
- Regla alta inaplicable no bloquea regla baja aplicable: PASS.
- NO_RULE_MATCHED y NO_APPLICABLE_ACTION producen EMF válido con actions=[]: PASS.
- Cien resoluciones idénticas; cambio de contexto/reglas/parámetros cambia hashes;
  distinto pseudónimo y escala decimal equivalente no cambian contexto: PASS.
- Dos ContextModel XMI y decisiones seleccionadas/vacías sobreviven round-trip
  EMF con Diagnostician e igualdad: PASS. El caso real de tres fallos también se
  serializó/recargó como AdaptationDecision.
- POST /api/attempts ejecuta/evalúa una sola vez y añade adaptation: PASS con spies.
- Petición repetida, incluso después de ocho intentos posteriores, devuelve
  exactamente el mismo DTO, decisionId y fecha: PASS.
- Dos hilos sobre el mismo intento/versiones devuelven una sola decisión: PASS.
- Trigger PostgreSQL de prueba provoca fallo al escribir auditoría: se revierten
  intento, mastery, snapshot y decisión; trigger eliminado al terminar: PASS.
- POST decide, GET decisión y GET adaptación del intento, 400/404, propietario
  incorrecto y campos extra sin autoridad: PASS.
- Intento antiguo sin evidencia V3: 409 HISTORICAL_ADAPTATION_SNAPSHOT_UNAVAILABLE,
  sin reejecutar ni reevaluar: PASS.

## Demostración completa con datos reales

Se crearon estudiantes pseudónimos mediante API. A y C completaron prerrequisitos
reales y realizaron tres fallos consecutivos LOOPS_MANUAL. B resolvió correctamente
SEQUENCES ocho veces hasta mastery=0.80. No se falsificó mastery en PostgreSQL.

A/C: reglas coincidentes ReforzarCiclos, ErrorRepetido y FuncionalSinConcepto;
primaria FuncionalSinConcepto por prioridad DSL real. Contribuyen ErrorRepetido y
ReforzarCiclos. Acciones finales SHOW_HINT CONCEPTUAL y REPEAT_ACTIVITY.
SELECT_REINFORCEMENT_ACTIVITY se descarta con
NOT_APPLICABLE_NO_REINFORCEMENT_ACTIVITY; no se crea actividad ficticia.
No se descarta una regla completa si aporta otra acción compatible/duplicada.

B: DominioAlto, ADVANCE_TO_NEXT_CONCEPT hacia VARIABLES,
sucesor real obtenido mediante ConceptGraphService. La decisión no aplica cambios
visuales ni modifica directamente mastery/progreso.

Repetir POST decide y consultar los dos GET devolvió el DTO idéntico. A y C tienen
identidades diferentes, la misma historia y el mismo fingerprint. B tiene historia,
contexto, acciones y fingerprint diferentes. llmUsed=false, llmPurpose=NONE.

| Estudiante | Regla primaria | contextHash | decisionFingerprint |
| --- | --- | --- | --- |
| A | FuncionalSinConcepto | `2cf7dbc2747dd45f126d24df3345f586f77685a305526060aa575a33f61afdc9` | `0ca909cd7c7e87248b0eef62f99c90c0ae7fcea1a182e77521c2d3a3f7808e1e` |
| C | FuncionalSinConcepto | `2cf7dbc2747dd45f126d24df3345f586f77685a305526060aa575a33f61afdc9` | `0ca909cd7c7e87248b0eef62f99c90c0ae7fcea1a182e77521c2d3a3f7808e1e` |
| B | DominioAlto | `e6e52563563029f31af0b0bfd1172f3a4eee7d0ece3d95da195f074f22c4a749` | `06f581a32d23c2cad05ec26619d1019c9f54f94eb60fd13f57251770f40192de` |

rulesetHash: `7d073441a373a915454ed4c954727d50eee0ffcbba00509615cf4f5555c86c9e`.
parametersHash: `13cac42690039160bb6fb3193f17e3efaed2a05f25750c55ea0cdccb29ad3b6d`.

## Integridad de modelos y componentes previos

Comparación SHA-256 contra snapshot de todos los archivos versionados al inicio:
programming completo, learning completo, generador Acceleo completo, plugin DSL
completo (incluido parser regenerado), frontend completo, rules-v1.adapt, V1 y V2
sin cambios. Única excepción formal autorizada: tres lowerBounds de adaptación
pasan de 1 a 0; se regeneraron tres fuentes EMF, sin edición manual.

| Artefacto | SHA-256 final / resultado |
| --- | --- |
| programming.ecore | 6fad03ac3e94f8cb2fb24a16632ce92c65e9a934bb6a6181a62c5cfc4a68e3df |
| programming.genmodel | b6211d326a49701b4691d8493165fb63572f3e2ee39c9fe533dbca74d9dc1451 |
| programming src-gen | 44 Java; todos intactos |
| learning.ecore | 3b7e10dff04fc7af3ec91649986322271023afb4207dd57de3c9f13f4fa2eb45 |
| learning.genmodel | 1deea0b68799173dd419a227408fb9a33aecf8e49bfe20cb662de0a1926297be |
| learning src-gen | 26 Java; todos intactos |
| adaptation.ecore | ed3f63b5227e07421f696a04690becb4778758ed7e3ed7c0c39d9ddb58eb8243 |
| adaptation.genmodel | b22b13a6ce00657c86fa87d6ab7d99199b04b1f1bd291c6e32e019519d2c68ff |
| adaptation src-gen | 44 Java; 41 intactos, tres regenerados por excepción autorizada |
| Adaptation.xtext | df841202185952e797651068514e958fad04090ffd6b4eaf1a0dd8cd3ad4d683 |
| context.ecore | 4d9ccb0d4f7920c4ed1b970d7b589eb8410ae914c3a97390b5b03479efd36741 |
| context.genmodel | 27b1191776acf0fb8f5bf19880b71210f108647bcff2d150dabb0a44e0073079 |
| context src-gen | 14 Java nuevos, generación EMF auténtica |
| sequence-basic.js generado | 15f3d7558138343c89e9fd69ced3921d51e52f53ef32c08bb49105fdec09bfe8 |

Cambios generados de adaptación: AdaptationDecision.java,
AdaptationExplanation.java y impl/AdaptationPackageImpl.java. GenModel intacto.
XMI nuevos versionados: context-three-failures.context y context-high-mastery.context.

## Incidencias y límites

La primera integración reveló diferencia entre nanosegundos Java y microsegundos
PostgreSQL en createdAt. Se corrigió truncando antes de persistir, sin relajar las
aserciones; ocho IT focalizados y después verify completo pasaron. Dos comandos de
edición iniciales usaron un prefijo backend redundante y fallaron sin escribir;
se corrigió el directorio. No se alteraron archivos ajenos al alcance.

Los intentos previos a V3 sin snapshot no se reconstruyen artificialmente: 409.
hintCount tiene alcance por concepto, equivalente a actividad solo bajo el catálogo
actual de una actividad por concepto. hintLevel y feedbackDetail son configuración
base versionada; prevalecen parámetros explícitos del DSL. No se introdujo auth de
producción: se mantiene el acceso pseudónimo de Fase 6. Warning de bundle histórico.
No se aplican decisiones a UI; no hay LLM, ui.ecore, refuerzos ficticios ni temas
avanzados. Cuatro niveles, cuatro actividades, cero refuerzos. Fase 9 no iniciada.

[Diseño y parámetros](FASE_8_ADAPTATION_MANAGER.md) · [API](API.md).

## Cierre de servicios

Backend y Vite de esta tarea detenidos; PostgreSQL detenido mediante Compose,
Docker Desktop detenido. Sin listeners en 8080/4173/5173/5432. Volumen conservado;
no se eliminaron datos ni componentes WSL.
