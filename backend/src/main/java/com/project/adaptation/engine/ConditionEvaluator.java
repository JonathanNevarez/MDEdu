package com.project.adaptation.engine;

import com.project.adaptation.domain.RuleEvaluationContext;
import com.project.adaptation.domain.RuleEngineResult.ConditionEvidence;
import com.project.mde.adaptation.*;
import com.project.mde.adaptation.validation.ContextAttribute;
import java.math.BigDecimal;
import java.util.*;

public final class ConditionEvaluator {
    public boolean evaluate(Condition condition, RuleEvaluationContext context, List<ConditionEvidence> evidence) {
        if (condition instanceof LogicalCondition c) {
            // Evaluate both sides to provide stable, complete evidence, even when one decides the result.
            boolean left=evaluate(c.getLeft(),context,evidence), right=evaluate(c.getRight(),context,evidence);
            return c.getOperator()==LogicalOperator.AND ? left && right : left || right;
        }
        if (condition instanceof NotCondition c) return !evaluate(c.getOperand(),context,evidence);
        if (!(condition instanceof ComparisonCondition c)) throw new IllegalArgumentException("Unsupported condition");
        Object observed=context.value(ContextAttribute.find(c.getAttribute()).orElseThrow());
        Object expected=switch (c.getValue()) {
            case IntegerValue v -> BigDecimal.valueOf(v.getValue());
            case DecimalValue v -> v.getValue();
            case StringValue v -> v.getValue();
            case BooleanValue v -> v.isValue();
            default -> throw new IllegalArgumentException("Unsupported value");
        };
        boolean result;
        if (observed instanceof BigDecimal n && expected instanceof BigDecimal v) {
            int cmp=n.compareTo(v);
            result=switch(c.getOperator()) {
                case EQ -> cmp==0; case NE -> cmp!=0; case GT -> cmp>0; case GE -> cmp>=0;
                case LT -> cmp<0; case LE -> cmp<=0;
                default -> throw new IllegalArgumentException("Invalid numeric operator");
            };
        } else {
            result=switch(c.getOperator()) {
                case EQ -> observed.equals(expected); case NE -> !observed.equals(expected);
                case CONTAINS -> ((List<?>)observed).contains(expected);
                default -> throw new IllegalArgumentException("Invalid operator");
            };
        }
        evidence.add(new ConditionEvidence(c.getAttribute(),c.getOperator().name(),text(observed),text(expected),result));
        return result;
    }
    private static String text(Object value) {
        return value instanceof BigDecimal n ? n.stripTrailingZeros().toPlainString() : value.toString();
    }
}
