package com.project.mde.adaptation.validation;

import com.project.mde.adaptation.*;
import java.util.*;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.common.util.Diagnostic;

public final class AdaptationModels {
    private AdaptationModels() {}
    public record Problem(EObject object, EStructuralFeature feature, String message) {}
    private static void add(List<Problem> out, EObject o, String feature, String message) {
        out.add(new Problem(o, o.eClass().getEStructuralFeature(feature), message));
    }
    public static List<Problem> problems(AdaptationRuleSet root, RuleCatalog catalog) {
        var out = new ArrayList<Problem>();
        if (root.getVersion() <= 0) add(out, root, "version", "RuleSet version must be positive");
        if (root.getName() == null || root.getName().isBlank()) add(out, root, "name", "RuleSet name is required");
        if (root.getRules().isEmpty()) add(out, root, "rules", "RuleSet needs a rule");
        var ids = new HashSet<String>();
        for (var rule : root.getRules()) {
            if (rule.getId() == null || rule.getId().isBlank()) add(out, rule, "id", "Rule id is required");
            else if (!ids.add(rule.getId())) add(out, rule, "id", "Duplicate rule id: " + rule.getId());
            if (rule.getVersion() <= 0) add(out, rule, "version", "Rule version must be positive");
            if (rule.getPriority() < 0 || rule.getPriority() > 100) add(out, rule, "priority", "Priority must be 0..100");
            if (rule.getEvent() == null) add(out, rule, "event", "Event is required");
            if (rule.getCondition() == null) add(out, rule, "condition", "Condition is required");
            if (rule.getActions().isEmpty()) add(out, rule, "actions", "At least one action is required");
        }
        root.eAllContents().forEachRemaining(o -> {
            if (o instanceof ComparisonCondition c) comparison(c, catalog, out);
            if (o instanceof Action a) action(a, out);
        });
        return List.copyOf(out);
    }
    private static void comparison(ComparisonCondition c, RuleCatalog catalog, List<Problem> out) {
        var attribute = ContextAttribute.find(c.getAttribute());
        if (attribute.isEmpty()) { add(out, c, "attribute", "Unknown context attribute: " + c.getAttribute()); return; }
        var kind = attribute.get().kind(); var v = c.getValue(); var op = c.getOperator();
        boolean numeric = kind == ContextAttribute.Kind.INTEGER || kind == ContextAttribute.Kind.DECIMAL;
        boolean validOperator = numeric ? op != ComparisonOperator.CONTAINS :
            kind == ContextAttribute.Kind.PATTERNS ? op == ComparisonOperator.CONTAINS :
            op == ComparisonOperator.EQ || op == ComparisonOperator.NE;
        if (!validOperator) add(out, c, "operator", "Operator incompatible with " + c.getAttribute());
        boolean validType = switch (kind) {
            case INTEGER -> v instanceof IntegerValue;
            case DECIMAL -> v instanceof DecimalValue || v instanceof IntegerValue;
            case BOOLEAN -> v instanceof BooleanValue;
            case CONCEPT, PATTERNS -> v instanceof StringValue;
        };
        if (!validType) add(out, c, "value", "Value type incompatible with " + c.getAttribute());
        if (v instanceof StringValue s && kind == ContextAttribute.Kind.CONCEPT && !catalog.concepts().contains(s.getValue()))
            add(out, c, "value", "Unknown Concept ID: " + s.getValue());
        if (v instanceof StringValue s && kind == ContextAttribute.Kind.PATTERNS && !catalog.patterns().contains(s.getValue()))
            add(out, c, "value", "Unknown Pattern ID: " + s.getValue());
    }
    private static void action(Action a, List<Problem> out) {
        var p = a.getParameters();
        boolean hint = a.getType() == ActionType.SHOW_HINT || a.getType() == ActionType.CHANGE_HINT_LEVEL;
        boolean style = a.getType() == ActionType.CHANGE_FEEDBACK_STYLE;
        if (hint && (p == null || !p.isSetHintLevel())) add(out, a, "parameters", "Action requires hint level");
        if (style && (p == null || !p.isSetFeedbackStyle())) add(out, a, "parameters", "Action requires feedback style");
        if (p != null && ((!hint && p.isSetHintLevel()) || (!style && p.isSetFeedbackStyle()) || (!hint && !style)))
            add(out, a, "parameters", "Parameters not allowed for " + a.getType());
    }
    public static void validate(AdaptationRuleSet root, RuleCatalog catalog) {
        var structural = Diagnostician.INSTANCE.validate(root);
        if (structural.getSeverity() >= Diagnostic.ERROR) throw new IllegalArgumentException(structural.toString());
        var errors = problems(root, catalog);
        if (!errors.isEmpty()) throw new IllegalArgumentException(errors.stream().map(Problem::message).toList().toString());
        root.eAllContents().forEachRemaining(o -> {
            if (o.eIsProxy() || o.eCrossReferences().stream().anyMatch(EObject::eIsProxy))
                throw new IllegalArgumentException("Unresolved adaptation reference");
        });
    }
}
