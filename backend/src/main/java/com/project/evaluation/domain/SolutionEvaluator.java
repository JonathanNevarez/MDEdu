package com.project.evaluation.domain;

import com.project.evaluation.catalog.PatternCatalog;
import com.project.evaluation.detectors.DetectorRegistry;
import com.project.evaluation.domain.EvaluationTypes.*;
import java.util.*;

/** Combines independent dimensions. Never executes or mutates the Program. */
public final class SolutionEvaluator {
    private final PatternCatalog catalog;
    private final DetectorRegistry detectors=new DetectorRegistry();
    public SolutionEvaluator(PatternCatalog catalog){this.catalog=catalog;}
    public EvaluationResult evaluate(EvaluationContext c) {
        var patterns=new ArrayList<PatternDetection>();
        for(var definition:catalog.patterns()) if(definition.concept()==c.config.requiredConcept())
            for(var evidence:detectors.get(definition.detector()).detect(c))patterns.add(new PatternDetection(definition.id(),definition.concept(),definition.severity(),definition.pedagogicalMeaning(),definition.recommendedAction(),definition.defaultHintLevel(),evidence));
        var execution=c.execution;
        var functional=new FunctionalCorrectness(execution.success(),execution.finalState().atGoal(),execution.status(),!execution.errors().isEmpty(),execution.steps());
        var structural=new StructuralAnalyzer().analyze(c);
        boolean blocking=patterns.stream().anyMatch(p->c.config.blockingPatternIds().contains(p.id()));
        boolean passed=functional.passed() && structural.constraintsSatisfied() && !blocking;
        String efficiency=!passed?"NEEDS_RETRY":patterns.isEmpty()?"OK":"NEEDS_REVIEW";
        return new EvaluationResult(1,c.config.levelId(),passed,functional,structural,new PedagogicalEfficiency(efficiency),List.copyOf(patterns));
    }
}
