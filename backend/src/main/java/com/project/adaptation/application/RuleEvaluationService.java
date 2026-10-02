package com.project.adaptation.application;

import com.project.adaptation.domain.RuleEngineResult;
import com.project.adaptation.engine.EcaRuleEngine;
import com.project.adaptation.rules.AdaptationRuleLoader;
import com.project.evaluation.domain.EvaluationTypes.EvaluationResult;
import com.project.mde.adaptation.EventType;
import com.project.mde.learning.*;
import com.project.student.domain.MasteryUpdater;
import org.springframework.stereotype.Service;

/** Explicit diagnostic evaluation. Does not modify learning, persistence, or the current API. */
@Service
public final class RuleEvaluationService {
    private final AdaptationRuleLoader loader;
    private final RuleContextFactory contexts;
    public RuleEvaluationService(AdaptationRuleLoader loader,RuleContextFactory contexts) { this.loader=loader;this.contexts=contexts; }
    public RuleEngineResult evaluate(StudentModel model,Attempt latest,EvaluationResult evaluation,MasteryUpdater.Change change) {
        return new EcaRuleEngine(loader.catalog()).evaluate(loader.snapshot(),contexts.create(model,latest,evaluation,change),EventType.ATTEMPT_EVALUATED);
    }
}
