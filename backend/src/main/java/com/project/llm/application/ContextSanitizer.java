package com.project.llm.application;
import com.project.llm.domain.LlmTypes.*;
import com.project.adaptation.manager.DecisionTypes.DecisionDto;
import com.project.evaluation.catalog.PatternCatalog;
import com.project.evaluation.domain.EvaluationTypes.PatternDefinition;
import com.project.evaluation.api.EvaluatedExecution;
import com.project.mde.programming.*;
import java.math.BigDecimal;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class ContextSanitizer {
    private final PatternCatalog catalog;
    private final LlmPolicy policy;
    public ContextSanitizer(PatternCatalog catalog,LlmPolicy policy){this.catalog=catalog;this.policy=policy;}
    /** Whitelist of model types/counts: no names, values, IDs, JavaScript or trace. */
    public ExecutionSummary summarize(Program program,EvaluatedExecution result) {
        var counts=new TreeMap<String,Integer>();var sensors=new TreeSet<String>();int maxDepth=0;
        var it=program.eAllContents();
        while(it.hasNext()) {
            var o=it.next();
            if(o instanceof Statement) {counts.merge(o.eClass().getName(),1,Integer::sum);int d=0;for(var parent=o.eContainer();parent!=null;parent=parent.eContainer())if(parent instanceof Statement)d++;maxDepth=Math.max(maxDepth,d);}
            if(o instanceof SensorExpression s)sensors.add(s.getSensor().getLiteral());
        }
        return new ExecutionSummary(result.evaluation().functionalCorrectness().goalReached(),result.steps(),
            result.evaluation().functionalCorrectness().executionStatus(),counts,List.copyOf(sensors),maxDepth);
    }
    public PatternDefinition focal(List<String> ids,DecisionDto decision) {
        var related=new HashSet<String>();
        decision.ruleAudit().stream().filter(a->Objects.equals(a.ruleId(),decision.selectedRule())).flatMap(a->a.evidence().stream())
            .filter(e->e.attribute().equals("detectedPatterns") && e.satisfied()).forEach(e->related.add(e.expected().replace("\"","")));
        return catalog.patterns().stream().filter(p->ids.contains(p.id()))
            .sorted(Comparator.<PatternDefinition>comparingInt(p->related.contains(p.id())?0:1)
                .thenComparing(Comparator.comparingInt((PatternDefinition p)->p.severity().ordinal()).reversed()))
            .findFirst().orElse(null); // stream stable: catalog order is final tie breaker
    }
    public PedagogicalLlmContext sanitize(AttemptEvidence e,DecisionDto decision,HintStage stage,List<String> previous,List<String> tags) {
        var pattern=focal(e.detectedPatterns(),decision);
        // Only fixed vocabulary from accepted output is passed back; arbitrary prior provider text is omitted.
        var safePrevious=previous.stream().filter(s->Arrays.stream(HintStage.values()).anyMatch(h->h.name().equals(s)))
            .limit(policy.values().maxPreviousHints()).toList();
        var safeTags=tags.stream().filter(t->policy.tags().tags().getOrDefault(t,List.of()).contains(e.concept())).distinct().sorted().toList();
        return new PedagogicalLlmContext(e.concept(),e.activityObjective(),e.masteryScore().stripTrailingZeros(),e.attemptNumber(),e.activityPassed(),
            e.requiredConceptUsed(),pattern==null?null:pattern.id(),pattern==null?null:pattern.pedagogicalMeaning(),pattern==null?null:pattern.recommendedAction(),
            e.executionSummary(),stage,safePrevious,decision.actions().stream().map(a->a.type().getLiteral()).toList(),safeTags);
    }
}
