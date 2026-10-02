package com.project.adaptation.application;

import com.project.adaptation.domain.RuleEvaluationContext;
import com.project.evaluation.domain.EvaluationTypes.EvaluationResult;
import com.project.mde.learning.*;
import com.project.student.domain.MasteryUpdater;
import org.springframework.stereotype.Component;

/** Compatibility entry point; a single formal ContextModel projection owns the mapping. */
@Component
public final class RuleContextFactory {
    private final ContextProjectionService contexts;
    public RuleContextFactory(ContextProjectionService contexts) { this.contexts=contexts; }
    public RuleEvaluationContext create(StudentModel model,Attempt latest,EvaluationResult evaluation,MasteryUpdater.Change change) {
        return ContextProjectionService.toRules(contexts.create(model,latest,evaluation,change));
    }
}
