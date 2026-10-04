package com.project.evaluation.application;
import com.project.evaluation.catalog.PatternCatalog;
import com.project.evaluation.domain.*;
import com.project.evaluation.domain.EvaluationTypes.EvaluationResult;
import com.project.execution.domain.GameTypes.Result;
import com.project.mde.programming.Program;
import org.springframework.stereotype.Service;
@Service
public class SolutionEvaluationService {
    private final PatternCatalog catalog;
    public SolutionEvaluationService(PatternCatalog catalog){this.catalog=catalog;}
    public EvaluationResult evaluate(String levelId,Program program,Result execution) {
        var context=new EvaluationContext(program,execution,catalog.level(levelId));
        var result=new SolutionEvaluator(catalog).evaluate(context);
        if(catalog.challenge(levelId).criteria()==null)return result;
        var checks=new java.util.LinkedHashMap<>(result.structuralCorrectness().structuralConstraints());
        checks.putAll(ChallengeCriteria.check(catalog.challenge(levelId),context));
        boolean valid=checks.values().stream().allMatch(Boolean.TRUE::equals);
        var structural=result.structuralCorrectness();
        return new EvaluationResult(result.evaluationVersion(),levelId,result.activityPassed()&&valid,result.functionalCorrectness(),
            new com.project.evaluation.domain.EvaluationTypes.StructuralCorrectness(structural.requiredConcept(),structural.requiredConceptUsed(),structural.requiredConstructsSatisfied(),valid,java.util.Collections.unmodifiableMap(checks)),
            valid?result.pedagogicalEfficiency():new com.project.evaluation.domain.EvaluationTypes.PedagogicalEfficiency("NEEDS_RETRY"),result.patterns());
    }
}
