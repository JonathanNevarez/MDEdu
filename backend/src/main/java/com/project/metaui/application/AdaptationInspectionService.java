package com.project.metaui.application;

import com.project.adaptation.rules.AdaptationRuleLoader;
import com.project.adaptation.manager.AdaptationParametersConfig;
import com.project.adaptation.manager.CanonicalHashes;
import com.project.adaptation.manager.DecisionTypes.ActionDto;
import com.project.mde.adaptation.*;
import java.util.List;
import org.springframework.stereotype.Service;

/** Inspects the same immutable startup snapshots used by the decision engine. */
@Service
public class AdaptationInspectionService {
    public record Rule(String ruleId, String name, int version, int priority, String eventType,
        String conditionSummary, List<ActionDto> actions, boolean enabled) {}
    public record Rules(String source, int rulesetVersion, String rulesetHash, List<Rule> rules) {}
    public record Parameters(String source, int parametersVersion, String parametersHash, AdaptationParametersConfig.Values values) {}
    private final AdaptationRuleLoader loader;
    private final AdaptationParametersConfig parameters;
    public AdaptationInspectionService(AdaptationRuleLoader loader, AdaptationParametersConfig parameters) {
        this.loader=loader; this.parameters=parameters;
    }
    public Rules rules() {
        var snapshot=loader.snapshot();
        if(snapshot.getRules().isEmpty()) throw new IllegalStateException("RULES_UNAVAILABLE");
        return new Rules("adaptation/rules/rules-v1.adapt", snapshot.getVersion(), loader.hash(),
            snapshot.getRules().stream().map(r -> new Rule(r.getId(), r.getName(), r.getVersion(), r.getPriority(),
                r.getEvent().getType().name(), condition(r.getCondition()), r.getActions().stream().map(a -> {
                    var p=a.getParameters();
                    return new ActionDto(a.getType(), p!=null && p.isSetHintLevel()?p.getHintLevel():null,
                        p!=null && p.isSetFeedbackStyle()?p.getFeedbackStyle():null, null);
                }).toList(), r.isEnabled())).toList());
    }
    public Parameters parameters() {
        var values=parameters.values();
        return new Parameters("adaptation/adaptation-parameters.v1.json", values.version(), CanonicalHashes.hash(values), values);
    }
    static String condition(Condition condition) {
        if(condition instanceof LogicalCondition c) return "("+condition(c.getLeft())+" "+c.getOperator().name()+" "+condition(c.getRight())+")";
        if(condition instanceof NotCondition c) return "NOT ("+condition(c.getOperand())+")";
        if(condition instanceof ComparisonCondition c) {
            String value=switch(c.getValue()) {
                case IntegerValue v -> Long.toString(v.getValue());
                case DecimalValue v -> v.getValue().toPlainString();
                case StringValue v -> "\""+v.getValue()+"\"";
                case BooleanValue v -> Boolean.toString(v.isValue());
                default -> throw new IllegalStateException("RULE_VALUE_UNSUPPORTED");
            };
            String op=switch(c.getOperator()) { case EQ -> "=="; case NE -> "!="; case GT -> ">"; case GE -> ">="; case LT -> "<"; case LE -> "<="; case CONTAINS -> "CONTAINS"; };
            return c.getAttribute()+" "+op+" "+value;
        }
        throw new IllegalStateException("RULE_CONDITION_UNSUPPORTED");
    }
}
