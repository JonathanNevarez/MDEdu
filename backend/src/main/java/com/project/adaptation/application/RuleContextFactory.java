package com.project.adaptation.application;

import com.project.adaptation.domain.RuleEvaluationContext;
import com.project.evaluation.domain.EvaluationTypes.EvaluationResult;
import com.project.mde.learning.*;
import com.project.student.domain.MasteryUpdater;
import java.math.BigDecimal;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public final class RuleContextFactory {
    /** The post-attempt snapshot and the exact Phase 6 Change for that attempt are required. */
    public RuleEvaluationContext create(StudentModel model, Attempt latest, EvaluationResult evaluation, MasteryUpdater.Change change) {
        Objects.requireNonNull(change);
        if (model.getAttempts().isEmpty() || model.getAttempts().getFirst()!=latest || !model.getAttempts().contains(latest) || latest.getStudent()!=model.getStudent() ||
            !latest.getActivity().getId().equals(evaluation.levelId()) ||
            !latest.getConcept().getId().equals(evaluation.structuralCorrectness().requiredConcept().name()) ||
            latest.isSuccessful()!=evaluation.activityPassed() || latest.isFunctionalPassed()!=evaluation.functionalCorrectness().passed())
            throw new IllegalArgumentException("Attempt/evaluation does not belong to this snapshot");
        var m=model.getConceptMasteries().stream().filter(c->c.getConcept()==latest.getConcept()).findFirst().orElseThrow();
        var s=change.state();
        if (Double.compare(m.getMasteryScore(),change.after())!=0 || m.getAttemptCount()!=s.attempts() ||
            m.getSuccessCount()!=s.successes() || m.getFailureCount()!=s.failures() || m.getConsecutiveFailures()!=s.consecutiveFailures() ||
            m.getHintCount()!=s.hints() || Double.compare(m.getAverageResolutionTime(),s.averageTime())!=0)
            throw new IllegalArgumentException("Mastery change and post-attempt snapshot disagree");
        return new RuleEvaluationContext(m.getConcept().getId(),BigDecimal.valueOf(m.getMasteryScore()),m.getAttemptCount(),
            m.getSuccessCount(),m.getFailureCount(),m.getConsecutiveFailures(),m.getHintCount(),BigDecimal.valueOf(m.getAverageResolutionTime()),
            latest.getResolutionTime(),evaluation.activityPassed(),evaluation.functionalCorrectness().passed(),
            evaluation.structuralCorrectness().requiredConceptUsed(),evaluation.patterns().stream().map(p->p.id()).distinct().toList(),
            change.reasons().contains("REPEATED_ERROR_PATTERN"));
    }
}
