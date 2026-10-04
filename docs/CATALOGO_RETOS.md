# Catálogo de retos de razonamiento

Evolución post-prototipo desde `3424f797aa8cb6163536b109ab739ddf55a4cea2`. Fases 0–13 permanecen cerradas; no existe Fase 14. Exactamente 18 retos principales y cuatro conceptos.

## Primitivas inspeccionadas

`programming.ecore`, SensorKind, GridWorld y GridWorldExecutionEngine: Move, TurnLeft, TurnRight; declaración/asignación/referencia; INTEGER/BOOLEAN; seis comparaciones; AND/OR/NOT; If/IfElse/Repeat/While. Sensores FRONT_CLEAR, LEFT_CLEAR, RIGHT_CLEAR, AT_GOAL, HAS_KEY, ON_KEY, DOOR_AHEAD y DOOR_OPEN. No hay aritmética ni OPEN_DOOR. Las llaves se recogen al pisar la celda y las puertas se abren al avanzar teniendo llave; no se consume la llave. ON_KEY resulta falso después de la recogida automática. No se añaden waypoints al motor: SEQ-02 usa llaves ordenadas en la traza.

## Objetivos y bloques

| ID | Concepto | Título | Objetivo | Bloques | Dificultad | Patrones relevantes |
|---|---|---|---|---|---|---|
| SEQ-01 | SEQUENCES | El primer sendero | Ordena dos avances, un giro a la derecha y dos avances hasta la bandera. | Movimiento | INTRODUCCION | WRONG_ORDER, MISSING_ACTION, UNNECESSARY_INSTRUCTION |
| SEQ-02 | SEQUENCES | Entrega en el campamento | Visita las dos llaves en orden de izquierda a derecha antes de llegar al campamento. Se recogen al avanzar. | Movimiento | APLICACION | WRONG_ORDER, MISSING_ACTION, UNNECESSARY_INSTRUCTION |
| SEQ-03 | SEQUENCES | Cruza el puente | Planifica una secuencia de movimientos y giros que rodee los obstáculos del puente hasta la bandera. Sin condiciones ni ciclos. | Movimiento | RAZONAMIENTO | WRONG_ORDER, MISSING_ACTION, UNNECESSARY_INSTRUCTION |
| SEQ-04 | SEQUENCES | La ruta eficiente | Planifica un recorrido de ocho acciones hasta la bandera, rodeando la barrera central. Revisa los giros y desvíos adicionales sin perder la meta. | Movimiento | INTEGRACION | WRONG_ORDER, MISSING_ACTION, UNNECESSARY_INSTRUCTION |
| VAR-01 | VARIABLES | Guarda el código | Declara codigo = 0, asigna 7 y lee codigo al declarar copia. Conserva ese valor y avanza cuatro casillas. | Movimiento, Variables, Valores | INTRODUCCION | UNUSED_VARIABLE, REDUNDANT_REASSIGNMENT, INCORRECT_UPDATE |
| VAR-02 | VARIABLES | Contador de objetos | Declara contador = 0. Al recoger cada llave asigna 1 y luego 2. Después de cada cambio guarda una lectura en primera y segunda. Avanza hasta la bandera. | Movimiento, Variables, Valores | APLICACION | UNUSED_VARIABLE, REDUNDANT_REASSIGNMENT, INCORRECT_UPDATE |
| VAR-03 | VARIABLES | Energía del explorador | Declara energia = 3. Tras cada avance asigna 2, 1 y 0; guarda cada lectura en una variable distinta. La energía es un registro lógico, no cambia el movimiento. | Movimiento, Variables, Valores | RAZONAMIENTO | UNUSED_VARIABLE, REDUNDANT_REASSIGNMENT, INCORRECT_UPDATE |
| VAR-04 | VARIABLES | Inventario de llaves | Declara llaves = 0; registra 1 y luego 2 al recoger las llaves y guarda ambas lecturas. La puerta abre al avanzar y no consume la llave. | Movimiento, Variables, Valores | INTEGRACION | UNUSED_VARIABLE, REDUNDANT_REASSIGNMENT, INCORRECT_UPDATE |
| COND-01 | CONDITIONALS | Dos caminos | Usa Si con el sensor Frente libre para decidir si avanzar por el camino hacia la bandera. La decisión debe controlar los movimientos. | Movimiento, Variables, Condicionales, Condiciones, Valores | INTRODUCCION | MISSING_CONDITION, CONSTANT_CONDITION, IDENTICAL_BRANCHES, MISSING_REQUIRED_BRANCH |
| COND-02 | CONDITIONALS | Camino bloqueado | Usa Si / Si no con Frente libre. Si hay un obstáculo, elige un recorrido alternativo por las celdas libres hasta la bandera. | Movimiento, Variables, Condicionales, Condiciones, Valores | APLICACION | MISSING_CONDITION, CONSTANT_CONDITION, IDENTICAL_BRANCHES, MISSING_REQUIRED_BRANCH |
| COND-03 | CONDITIONALS | La puerta y la llave | Recoge la llave y usa Si / Si no con una condición del mundo y dos alternativas distintas para llegar a la meta. | Movimiento, Variables, Condicionales, Condiciones, Valores | RAZONAMIENTO | MISSING_CONDITION, CONSTANT_CONDITION, IDENTICAL_BRANCHES, MISSING_REQUIRED_BRANCH |
| COND-04 | CONDITIONALS | Energía suficiente | Declara energia = 1. Si energia >= 2, toma el camino directo; si no, rodea por la fila inferior y regresa hasta la bandera. Usa dos alternativas distintas. | Movimiento, Variables, Condicionales, Condiciones, Valores | INTEGRACION | MISSING_CONDITION, CONSTANT_CONDITION, IDENTICAL_BRANCHES, MISSING_REQUIRED_BRANCH |
| COND-05 | CONDITIONALS | El laberinto de decisiones | Toma al menos dos decisiones que controlen movimientos: observa el frente y, después, el lado izquierdo para encontrar la salida entre los obstáculos. | Movimiento, Variables, Condicionales, Condiciones, Valores | INTEGRACION_FINAL | MISSING_CONDITION, CONSTANT_CONDITION, IDENTICAL_BRANCHES, MISSING_REQUIRED_BRANCH |
| LOOP-01 | LOOPS | Pasos repetidos | Avanza siete casillas usando Repetir. Siete avances escritos a mano llegan a la meta pero no superan el objetivo de repetición. | Movimiento, Variables, Condicionales, Ciclos, Condiciones, Valores | INTRODUCCION | REPETITIVE_SEQUENCE_WITHOUT_LOOP, LOOP_NEVER_EXECUTES, INCORRECT_REPETITION_COUNT, UNNECESSARY_LOOP, POSSIBLE_INFINITE_LOOP |
| LOOP-02 | LOOPS | El corredor largo | Recorre ocho casillas repitiendo una pareja de avances. Calcula cuántas veces debe ejecutarse ese patrón. | Movimiento, Variables, Condicionales, Ciclos, Condiciones, Valores | APLICACION | REPETITIVE_SEQUENCE_WITHOUT_LOOP, LOOP_NEVER_EXECUTES, INCORRECT_REPETITION_COUNT, UNNECESSARY_LOOP, POSSIBLE_INFINITE_LOOP |
| LOOP-03 | LOOPS | Patrulla el sendero | Patrulla tres lados del sendero repitiendo el patrón de dos avances y un giro a la derecha. Calcula las repeticiones hasta la bandera. | Movimiento, Variables, Condicionales, Ciclos, Condiciones, Valores | RAZONAMIENTO | REPETITIVE_SEQUENCE_WITHOUT_LOOP, LOOP_NEVER_EXECUTES, INCORRECT_REPETITION_COUNT, UNNECESSARY_LOOP, POSSIBLE_INFINITE_LOOP |
| LOOP-04 | LOOPS | Hasta encontrar la salida | Mientras Frente libre, avanza. La bandera está al final del corredor: el borde hace falsa la condición. | Movimiento, Variables, Condicionales, Ciclos, Condiciones, Valores | INTEGRACION | REPETITIVE_SEQUENCE_WITHOUT_LOOP, LOOP_NEVER_EXECUTES, INCORRECT_REPETITION_COUNT, UNNECESSARY_LOOP, POSSIBLE_INFINITE_LOOP |
| LOOP-05 | LOOPS | El desafío del explorador | Declara pasos = 4. Repite pasos veces: si el frente está libre avanza; si no gira a la derecha. Recoge la llave y cruza la puerta hasta la bandera. | Movimiento, Variables, Condicionales, Ciclos, Condiciones, Valores | INTEGRACION_FINAL | REPETITIVE_SEQUENCE_WITHOUT_LOOP, LOOP_NEVER_EXECUTES, INCORRECT_REPETITION_COUNT, UNNECESSARY_LOOP, POSSIBLE_INFINITE_LOOP |

## Mundos reales

Coordenadas (x,y), origen superior izquierdo. Todos comienzan mirando EAST. Los metadatos de player, goal, obstáculos, llaves y puertas se envían en worldConfig y WorldBoard dibuja esos mismos datos.

| Reto | Tablero | Inicio → meta | Obstáculos | Llaves en orden | Puertas |
|---|---|---|---|---|---|
| SEQ-01 | 5 × 5 | (1,1) → (3,3) | (2,2) | Ninguna | Ninguna |
| SEQ-02 | 5 × 3 | (0,1) → (4,1) | Ninguno | (1,1), (3,1) | Ninguna |
| SEQ-03 | 6 × 4 | (0,0) → (4,2) | (1,1), (3,1) | Ninguna | Ninguna |
| SEQ-04 | 7 × 5 | (0,1) → (5,3) | (2,2), (3,2), (4,2), (5,0) | Ninguna | Ninguna |
| VAR-01 | 5 × 3 | (0,1) → (4,1) | (2,0), (2,2) | Ninguna | Ninguna |
| VAR-02 | 4 × 3 | (0,1) → (3,1) | Ninguno | (1,1), (2,1) | Ninguna |
| VAR-03 | 4 × 3 | (0,1) → (3,1) | Ninguno | Ninguna | Ninguna |
| VAR-04 | 5 × 3 | (0,1) → (4,1) | Ninguno | (1,1), (3,1) | (2,1) |
| COND-01 | 3 × 3 | (0,1) → (2,1) | (1,0) | Ninguna | Ninguna |
| COND-02 | 3 × 3 | (0,0) → (2,2) | (1,0) | Ninguna | Ninguna |
| COND-03 | 6 × 4 | (0,2) → (5,2) | (2,1), (3,1) | (1,2) | (3,2) |
| COND-04 | 3 × 3 | (0,0) → (2,0) | Ninguno | Ninguna | Ninguna |
| COND-05 | 3 × 3 | (0,0) → (2,2) | (1,0) | Ninguna | Ninguna |
| LOOP-01 | 8 × 3 | (0,1) → (7,1) | (0,0), (0,2), (1,0), (1,2), (2,0), (2,2), (3,0), (3,2), (4,0), (4,2), (5,0), (5,2), (6,0), (6,2), (7,0), (7,2) | Ninguna | Ninguna |
| LOOP-02 | 9 × 3 | (0,1) → (8,1) | (0,0), (0,2), (1,0), (1,2), (2,0), (2,2), (3,0), (3,2), (4,0), (4,2), (5,0), (5,2), (6,0), (6,2), (7,0), (7,2), (8,0), (8,2) | Ninguna | Ninguna |
| LOOP-03 | 5 × 5 | (1,1) → (1,3) | (2,2) | Ninguna | Ninguna |
| LOOP-04 | 6 × 3 | (0,1) → (5,1) | (0,0), (0,2), (1,0), (1,2), (2,0), (2,2), (3,0), (3,2), (4,0), (4,2), (5,0), (5,2) | Ninguna | Ninguna |
| LOOP-05 | 5 × 3 | (0,1) → (4,1) | Ninguno | (1,1) | (3,1) |

## Evaluación y restricciones

Configuración de mundo/objetivos: `backend/src/main/resources/game/challenges.v1.json`. Configuración de evaluación: `evaluation/challenges-evaluation.v1.json`. Soluciones comprobadas: `backend/src/test/resources/challenges/`. Se conservan los quince IDs y detectores; las explicaciones de valores y conteos se generalizan para no recomendar 0→1 o siete pasos en todos los retos.

Cada reto exige meta funcional, concepto/constructos relevantes, grupos de bloques permitidos y ausencia de patrones bloqueantes. Los criterios adicionales comprueban recogidas ordenadas, asignaciones en posiciones concretas, lecturas antes de sobrescribir y decisiones ejecutadas con movimiento. No hay comparación de texto de código ni ejecución especial por ID. Los criterios consumen el Program EMF y la traza del intérprete común. VARIABLE_READ registra una lectura efectivamente ejecutada, respetando el cortocircuito; no es una instrucción nueva ni cambia valores o pasos.

VAR-01 conserva 0→7 y lee codigo al declarar copia; VAR-02 registra 0→1→2 en las recogidas; VAR-03 registra 3→2→1→0 al avanzar; VAR-04 registra 0→1→2 sin consumir llaves al cruzar la puerta. Las lecturas se guardan en variables auxiliares: no se anticipan If ni Repeat para usar una variable.

COND-03 conserva la puerta y la llave originales. LOOP-04 usa FRONT_CLEAR y un corredor cuyo final coincide con el borde. LOOP-05 usa una variable para el conteo y una decisión real dentro del ciclo. SEQ-04 señala acciones adicionales como NEEDS_REVIEW: no exige una única solución óptima.

LOOP-01: la solución manual de siete avances alcanza la meta (functionalPassed=true), pero falla el concepto y la aprobación pedagógica (activityPassed=false), con REPETITIVE_SEQUENCE_WITHOUT_LOOP.

## Progreso, historial y compatibilidad

El backend conserva ConceptGraph y su política de dominio: un éxito y el umbral de prerrequisitos pueden abrir el siguiente concepto aunque aún queden retos del anterior. Dentro de cada concepto, el reto n solo se desbloquea al superar n−1. No se sustituye ConceptGraph por un índice. Los incrementos +0.10/+0.05 y penalizaciones −0.03/−0.02 permanecen intactos.

V9 es necesaria porque Activity y Progress tienen claves foráneas persistidas. Añade las 18 actividades y marca las cuatro antiguas archived; no renombra ni elimina intentos, evidencia, cuentas, dominio o progreso histórico. No acredita los nuevos retos por haber superado un reto antiguo. Los estudiantes existentes conservan los conceptos previamente abiertos y reciben los nuevos progresos incompletos. El catálogo guiado contiene exactamente 18; las cuatro filas archivadas se conservan para resolver historia, no son retos adicionales.

No se aceptan nuevos intentos guiados en actividades archivadas. Sus configuraciones originales quedan disponibles para compatibilidad y pruebas del intérprete; la ejecución directa estudiantil también exige un reto activo desbloqueado. El laboratorio conserva su flujo libre. Student autenticado es la autoridad de identidad en cualquier dispositivo, sin autoridad de localStorage.

Aprender agrupa Concepto → Retos sobre el diseño existente. Progreso expone orden, current, unlocked, completed, totales y completados por concepto. Meta-IU consume la lista completa dinámicamente. Las bases ATL por concepto se cargan intactas y se vinculan en memoria al ID del reto; no cambian modelos, gramática ni transformaciones.
