package com.project.evaluation.domain;

import com.project.mde.programming.*;
import java.util.Optional;

/** Conservative pure folding of well-typed closed EMF expressions. Dynamic leaves remain unknown. */
public final class ConstantFolder {
    public Optional<Object> value(Expression expression) {
        try {
            if (expression instanceof Literal l) {
                if (l.getType() == ValueType.BOOLEAN && ("true".equals(l.getValue()) || "false".equals(l.getValue()))) return Optional.of(Boolean.valueOf(l.getValue()));
                if (l.getType() == ValueType.INTEGER && l.getValue() != null && l.getValue().matches("[+-]?[0-9]+")) return Optional.of(Integer.valueOf(l.getValue()));
            }
            if (expression instanceof Comparison c) {
                var a = value(c.getLeft()); var b = value(c.getRight());
                if (a.isEmpty() || b.isEmpty() || a.get().getClass() != b.get().getClass()) return Optional.empty();
                return switch (c.getOperator()) {
                    case EQUAL -> Optional.of(a.get().equals(b.get()));
                    case NOT_EQUAL -> Optional.of(!a.get().equals(b.get()));
                    default -> {
                        if (!(a.get() instanceof Integer x) || !(b.get() instanceof Integer y)) yield Optional.empty();
                        yield Optional.of(switch (c.getOperator()) { case LESS_THAN -> x < y; case LESS_OR_EQUAL -> x <= y;
                            case GREATER_THAN -> x > y; case GREATER_OR_EQUAL -> x >= y; default -> false; });
                    }
                };
            }
            if (expression instanceof BooleanExpression b) {
                var left = value(b.getLeft());
                if (left.isEmpty() || !(left.get() instanceof Boolean x)) return Optional.empty();
                if (b.getOperator() == BooleanOperator.NOT) return Optional.of(!x);
                var right = value(b.getRight());
                if (right.isEmpty() || !(right.get() instanceof Boolean y)) return Optional.empty();
                return Optional.of(b.getOperator() == BooleanOperator.AND ? x && y : x || y);
            }
        } catch (NumberFormatException ignored) { /* Invalid literals are runtime errors, not constant evidence. */ }
        return Optional.empty();
    }
}
