package com.project.adaptation.engine;

import com.project.adaptation.domain.*;
import com.project.adaptation.domain.RuleEngineResult.*;
import com.project.mde.adaptation.*;
import com.project.mde.adaptation.validation.*;
import java.util.*;

/** Pure ECA evaluation in source order. Priority is evidence, never a selection policy. */
public final class EcaRuleEngine {
    private final RuleCatalog catalog;
    private final ConditionEvaluator evaluator=new ConditionEvaluator();
    public EcaRuleEngine(RuleCatalog catalog) { this.catalog=catalog; }
    public RuleEngineResult evaluate(AdaptationRuleSet rules, RuleEvaluationContext context, String event) {
        AdaptationModels.validate(rules,catalog);
        var matches=new ArrayList<RuleMatch>(); var ignored=new ArrayList<IgnoredRule>(); int evaluated=0;
        for (var rule:rules.getRules()) {
            if (!rule.isEnabled()) { ignored.add(new IgnoredRule(rule.getId(),"DISABLED")); continue; }
            if (!rule.getEvent().getType().name().equals(event)) { ignored.add(new IgnoredRule(rule.getId(),"EVENT_MISMATCH")); continue; }
            evaluated++;
            var evidence=new ArrayList<ConditionEvidence>();
            if (!evaluator.evaluate(rule.getCondition(),context,evidence)) continue;
            var candidates=new ArrayList<ActionCandidate>();
            for (int i=0;i<rule.getActions().size();i++) {
                var a=rule.getActions().get(i); var p=a.getParameters();
                candidates.add(new ActionCandidate(rule.getId(),rule.getVersion(),rule.getPriority(),i,a.getType(),
                    p!=null && p.isSetHintLevel()?p.getHintLevel():null,
                    p!=null && p.isSetFeedbackStyle()?p.getFeedbackStyle():null));
            }
            matches.add(new RuleMatch(rule.getId(),rule.getVersion(),rule.getPriority(),candidates,evidence));
        }
        var actions=matches.stream().flatMap(m->m.actions().stream()).toList();
        var conflicts=new ArrayList<Conflict>();
        if(actions.stream().anyMatch(a->a.type()==ActionType.INCREASE_DIFFICULTY) &&
           actions.stream().anyMatch(a->a.type()==ActionType.DECREASE_DIFFICULTY))
            conflicts.add(new Conflict(ActionType.INCREASE_DIFFICULTY,ActionType.DECREASE_DIFFICULTY,
                actions.stream().filter(a->a.type()==ActionType.INCREASE_DIFFICULTY || a.type()==ActionType.DECREASE_DIFFICULTY)
                    .map(ActionCandidate::ruleId).distinct().toList()));
        return new RuleEngineResult(rules.getVersion(),evaluated,matches.size(),matches,ignored,conflicts);
    }
    public RuleEngineResult evaluate(AdaptationRuleSet rules, RuleEvaluationContext context, EventType event) {
        return evaluate(rules,context,event.name());
    }
}
