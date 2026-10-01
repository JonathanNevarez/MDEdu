package com.project.programming.application;

import com.project.programming.api.ProgramDto;
import com.project.programming.api.ProgramDto.*;
import com.project.mde.programming.*;
import java.util.*;
import org.springframework.stereotype.Component;

/** Two passes per request; DTO identities never become model attributes. */
@Component
public class ProgrammingModelMapper {
    private static final ProgrammingFactory F = ProgrammingFactory.eINSTANCE;

    public Program map(ProgramDto dto) {
        if (dto == null || !Integer.valueOf(1).equals(dto.contractVersion()) || dto.name() == null)
            throw bad("INVALID_CONTRACT", "Se requiere contractVersion 1 y nombre del programa.");
        var context = new Mapping();
        context.discover(dto.statements(), 0);
        var program = F.createProgram(); program.setName(dto.name());
        program.getStatements().addAll(context.statements(dto.statements(), 0));
        return program;
    }
    private static ContractException bad(String code, String message) { return new ContractException(code, message); }
    private static void required(Object value) {
        if (value == null) throw bad("MISSING_INPUT", "Falta un campo o input obligatorio.");
    }
    private static String id(String value) {
        if (value == null || value.isBlank()) throw bad("INVALID_DECLARATION_ID", "Identificador de declaración ausente.");
        return value;
    }
    private static <E extends Enum<E>> E choice(Class<E> type, String value) {
        try { return Enum.valueOf(type, Objects.requireNonNull(value)); }
        catch (IllegalArgumentException | NullPointerException e) { throw bad("INVALID_ENUM", "Valor de enumeración inválido."); }
    }
    private static void depth(int depth) {
        if (depth > 100) throw bad("MODEL_TOO_DEEP", "El modelo supera 100 niveles de anidación.");
    }
    private static final class Mapping {
        final Map<String, VariableDeclaration> declarations = new HashMap<>();
        int count;
        void discover(List<StatementDto> list, int level) {
            depth(level); required(list);
            for (var s : list) {
                required(s);
                if (++count > 10000) throw bad("MODEL_TOO_LARGE", "Demasiadas instrucciones.");
                switch (s) {
                    case DeclarationDto d -> {
                        if (declarations.putIfAbsent(id(d.declarationId()), F.createVariableDeclaration()) != null)
                            throw bad("DUPLICATE_DECLARATION_ID", "Identificador de declaración duplicado.");
                    }
                    case RepeatDto r -> discover(r.body(), level + 1);
                    case WhileDto w -> discover(w.body(), level + 1);
                    case IfDto i -> discover(i.thenBranch(), level + 1);
                    case IfElseDto i -> { discover(i.thenBranch(), level + 1); discover(i.elseBranch(), level + 1); }
                    default -> { }
                }
            }
        }
        VariableDeclaration reference(String value) {
            var result = declarations.get(id(value));
            if (result == null) throw bad("MISSING_VARIABLE_DECLARATION", "La declaración referenciada no existe.");
            return result;
        }
        List<Statement> statements(List<StatementDto> list, int level) {
            depth(level); required(list);
            return list.stream().map(s -> statement(s, level + 1)).toList();
        }
        Statement statement(StatementDto s, int level) {
            depth(level); required(s);
            return switch (s) {
                case MoveDto ignored -> F.createMove();
                case TurnLeftDto ignored -> F.createTurnLeft();
                case TurnRightDto ignored -> F.createTurnRight();
                case DeclarationDto d -> {
                    var v = reference(d.declarationId()); v.setName(d.name());
                    v.setType(choice(ValueType.class, d.valueType()));
                    if (d.initialValue() != null) v.setInitialValue(expression(d.initialValue(), level + 1));
                    yield v;
                }
                case AssignmentDto a -> {
                    var v = F.createAssignment(); v.setTarget(reference(a.targetDeclarationId()));
                    v.setValue(expression(a.value(), level + 1)); yield v;
                }
                case RepeatDto r -> {
                    var v = F.createRepeat(); v.setCount(expression(r.count(), level + 1));
                    v.getBody().addAll(statements(r.body(), level + 1)); yield v;
                }
                case WhileDto w -> {
                    var v = F.createWhile(); v.setCondition(expression(w.condition(), level + 1));
                    v.getBody().addAll(statements(w.body(), level + 1)); yield v;
                }
                case IfDto i -> {
                    var v = F.createIf(); v.setCondition(expression(i.condition(), level + 1));
                    v.getThenBranch().addAll(statements(i.thenBranch(), level + 1)); yield v;
                }
                case IfElseDto i -> {
                    var v = F.createIfElse(); v.setCondition(expression(i.condition(), level + 1));
                    v.getThenBranch().addAll(statements(i.thenBranch(), level + 1));
                    v.getElseBranch().addAll(statements(i.elseBranch(), level + 1)); yield v;
                }
            };
        }
        Expression expression(ExpressionDto e, int level) {
            depth(level); required(e);
            return switch (e) {
                case LiteralDto l -> {
                    var v = F.createLiteral(); v.setType(choice(ValueType.class, l.valueType()));
                    v.setValue(l.value()); yield v;
                }
                case ReferenceDto r -> {
                    var v = F.createVariableReference(); v.setDeclaration(reference(r.declarationId())); yield v;
                }
                case SensorDto s -> {
                    var v = F.createSensorExpression(); v.setSensor(choice(SensorKind.class, s.sensor())); yield v;
                }
                case ComparisonDto c -> {
                    var v = F.createComparison(); v.setOperator(choice(ComparisonOperator.class, c.operator()));
                    v.setLeft(expression(c.left(), level + 1)); v.setRight(expression(c.right(), level + 1)); yield v;
                }
                case BooleanDto b -> {
                    var v = F.createBooleanExpression(); v.setOperator(choice(BooleanOperator.class, b.operator()));
                    v.setLeft(expression(b.left(), level + 1));
                    if (v.getOperator() == BooleanOperator.NOT) {
                        if (b.right() != null) throw bad("INVALID_ARITY", "NOT acepta un solo operando.");
                    } else v.setRight(expression(b.right(), level + 1));
                    yield v;
                }
            };
        }
    }
}
