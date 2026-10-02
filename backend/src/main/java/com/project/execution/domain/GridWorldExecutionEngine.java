package com.project.execution.domain;

import com.project.execution.domain.GameTypes.*;
import com.project.mde.programming.*;
import java.util.*;

/** Direct EMF interpreter. No source-code execution, clocks, random state or external effects. */
public final class GridWorldExecutionEngine {
    public Result execute(Program program, WorldConfig world, int limit) {
        if (limit < 1 || limit > 10000) throw new IllegalArgumentException("Operation limit must be 1..10000");
        return new Execution(program, world, limit).run();
    }
    private record Value(ValueType type, Object raw) {}
    private static final class Halt extends RuntimeException {
        final String code;
        Halt(String code, String message) { super(message, null, false, false); this.code = code; }
    }
    private static Halt error(String code, String message) { return new Halt(code, message); }
    private static final class Execution {
        final Program program;
        final GridWorld world;
        final int limit;
        int steps;
        final IdentityHashMap<VariableDeclaration, Value> values = new IdentityHashMap<>();
        final List<VariableDeclaration> declarations = new ArrayList<>();
        final List<Event> trace = new ArrayList<>();
        Execution(Program p, WorldConfig config, int limit) {
            program = p; world = new GridWorld(config); this.limit = limit;
            p.eAllContents().forEachRemaining(e -> { if (e instanceof VariableDeclaration d) declarations.add(d); });
        }
        State state() {
            var variables = new ArrayList<Variable>();
            for (int i = 0; i < declarations.size(); i++) {
                var d = declarations.get(i); var v = values.get(d);
                if (v != null) variables.add(new Variable("v" + (i + 1), d.getName(), v.type().getName(), v.raw()));
            }
            return world.snapshot(variables);
        }
        void event(String type, String detail) { trace.add(new Event(trace.size(), type, detail, state())); }
        void tick() { if (steps == limit) throw error("STEP_LIMIT_EXCEEDED", "Se alcanzó el límite de operaciones."); steps++; }
        Result run() {
            event("PROGRAM_STARTED", "Inicio");
            try {
                statements(program.getStatements());
                event("PROGRAM_FINISHED", "Programa terminado");
                return new Result(world.atGoal(), "COMPLETED", steps, state(), List.copyOf(trace), List.of());
            } catch (Halt failure) {
                var status = failure.code.equals("STEP_LIMIT_EXCEEDED") ? failure.code : "RUNTIME_ERROR";
                event(status, failure.getMessage());
                return new Result(false, status, steps, state(), List.copyOf(trace),
                    List.of(new Failure(failure.code, failure.getMessage())));
            }
        }
        void statements(List<Statement> statements) { for (var s : statements) statement(s); }
        void statement(Statement s) {
            tick();
            if (s instanceof Move) move();
            else if (s instanceof TurnLeft) { world.direction = world.direction.left(); event("TURN_LEFT", "Giro a la izquierda"); }
            else if (s instanceof TurnRight) { world.direction = world.direction.right(); event("TURN_RIGHT", "Giro a la derecha"); }
            else if (s instanceof VariableDeclaration d) {
                var v = d.getInitialValue() == null ? new Value(d.getType(), d.getType() == ValueType.INTEGER ? 0 : false) : expression(d.getInitialValue());
                compatible(d, v); values.put(d, v); event("VARIABLE_DECLARED", d.getName());
            } else if (s instanceof Assignment a) {
                declared(a.getTarget()); var v = expression(a.getValue()); compatible(a.getTarget(), v);
                values.put(a.getTarget(), v); event("VARIABLE_ASSIGNED", a.getTarget().getName());
            } else if (s instanceof Repeat r) {
                int count = integer(expression(r.getCount()));
                if (count < 0) throw error("NEGATIVE_REPEAT", "Repetir requiere un entero no negativo.");
                for (int i = 0; i < count; i++) { tick(); event("LOOP_ITERATION", Integer.toString(i + 1)); statements(r.getBody()); }
            } else if (s instanceof While w) {
                int iteration = 0;
                while (condition(w.getCondition())) { tick(); event("LOOP_ITERATION", Integer.toString(++iteration)); statements(w.getBody()); }
            } else if (s instanceof IfElse i) {
                statements(condition(i.getCondition()) ? i.getThenBranch() : i.getElseBranch());
            } else if (s instanceof If i) { if (condition(i.getCondition())) statements(i.getThenBranch()); }
            else throw error("UNSUPPORTED_STATEMENT", "Instrucción no admitida.");
        }
        void compatible(VariableDeclaration d, Value v) {
            if (d.getType() != v.type()) throw error("TYPE_MISMATCH", "El valor no coincide con el tipo de la variable.");
        }
        Value declared(VariableDeclaration d) {
            var v = values.get(d);
            if (v == null) throw error("UNDECLARED_VARIABLE", "La variable todavía no fue declarada en esta ejecución.");
            return v;
        }
        boolean condition(Expression e) { boolean result = bool(expression(e)); event("CONDITION_EVALUATED", Boolean.toString(result)); return result; }
        boolean bool(Value v) { if (v.type() != ValueType.BOOLEAN) throw error("TYPE_MISMATCH", "Se requiere un valor booleano."); return (Boolean) v.raw(); }
        int integer(Value v) { if (v.type() != ValueType.INTEGER) throw error("TYPE_MISMATCH", "Se requiere un valor entero."); return (Integer) v.raw(); }
        Value expression(Expression e) {
            tick();
            if (e instanceof Literal l) {
                if (l.getType() == ValueType.INTEGER) {
                    try {
                        if (l.getValue() == null || !l.getValue().matches("[+-]?[0-9]+")) throw new NumberFormatException();
                        return new Value(ValueType.INTEGER, Integer.parseInt(l.getValue()));
                    } catch (NumberFormatException ex) { throw error("INVALID_LITERAL", "Entero inválido o fuera del rango de 32 bits."); }
                }
                if (!"true".equals(l.getValue()) && !"false".equals(l.getValue())) throw error("INVALID_LITERAL", "Booleano inválido: usa true o false.");
                return new Value(ValueType.BOOLEAN, Boolean.parseBoolean(l.getValue()));
            }
            if (e instanceof VariableReference r) return declared(r.getDeclaration());
            if (e instanceof SensorExpression s) {
                boolean result = switch (s.getSensor()) {
                    case FRONT_CLEAR -> world.clear(world.direction);
                    case LEFT_CLEAR -> world.clear(world.direction.left());
                    case RIGHT_CLEAR -> world.clear(world.direction.right());
                    case AT_GOAL -> world.atGoal();
                    case HAS_KEY -> world.hasKey;
                    case ON_KEY -> world.keys.contains(world.position);
                    case DOOR_AHEAD -> world.doors.containsKey(world.direction.ahead(world.position));
                    case DOOR_OPEN -> Boolean.TRUE.equals(world.doors.get(world.direction.ahead(world.position)));
                };
                event("SENSOR_READ", s.getSensor().getName() + "=" + result); return new Value(ValueType.BOOLEAN, result);
            }
            if (e instanceof Comparison c) {
                var left = expression(c.getLeft()); var right = expression(c.getRight());
                if (left.type() != right.type()) throw error("TYPE_MISMATCH", "La comparación requiere tipos compatibles.");
                boolean result = switch (c.getOperator()) {
                    case EQUAL -> left.raw().equals(right.raw());
                    case NOT_EQUAL -> !left.raw().equals(right.raw());
                    case LESS_THAN -> integer(left) < integer(right);
                    case LESS_OR_EQUAL -> integer(left) <= integer(right);
                    case GREATER_THAN -> integer(left) > integer(right);
                    case GREATER_OR_EQUAL -> integer(left) >= integer(right);
                };
                return new Value(ValueType.BOOLEAN, result);
            }
            if (e instanceof BooleanExpression b) {
                boolean left = bool(expression(b.getLeft()));
                // Explicit short-circuit semantics, matching basic boolean logic.
                boolean result = switch (b.getOperator()) {
                    case AND -> left && bool(expression(b.getRight()));
                    case OR -> left || bool(expression(b.getRight()));
                    case NOT -> !left;
                };
                return new Value(ValueType.BOOLEAN, result);
            }
            throw error("UNSUPPORTED_EXPRESSION", "Expresión no admitida.");
        }
        void move() {
            var next = world.direction.ahead(world.position);
            if (!world.inBounds(next)) throw error("OUT_OF_BOUNDS", "El personaje no puede salir del tablero.");
            if (world.config.obstacles().contains(next)) throw error("OBSTACLE", "Hay un obstáculo en el camino.");
            if (Boolean.FALSE.equals(world.doors.get(next))) {
                if (!world.hasKey) throw error("DOOR_LOCKED", "Necesitas la llave para abrir la puerta.");
                world.doors.put(next, true); event("DOOR_OPENED", "Puerta abierta");
            }
            world.position = next; event("MOVE", "Avanzar una celda");
            if (world.keys.remove(next)) { world.hasKey = true; event("ITEM_COLLECTED", "KEY"); }
            if (world.atGoal()) event("GOAL_REACHED", "Meta alcanzada");
        }
    }
}
