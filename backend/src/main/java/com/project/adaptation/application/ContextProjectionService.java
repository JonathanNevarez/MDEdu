package com.project.adaptation.application;

import com.project.adaptation.domain.RuleEvaluationContext;
import com.project.evaluation.domain.EvaluationTypes.EvaluationResult;
import com.project.mde.learning.*;
import com.project.mde.context.*;
import com.project.student.domain.MasteryUpdater;
import java.math.BigDecimal;
import java.util.*;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.springframework.stereotype.Component;

@Component
public final class ContextProjectionService {
    public ContextModel create(StudentModel model, Attempt latest, EvaluationResult evaluation, MasteryUpdater.Change change) {
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
        var student=ContextFactory.eINSTANCE.createStudentContext();
        student.setStudentId(model.getStudent().getId());student.setConceptId(m.getConcept().getId());student.setActivityId(latest.getActivity().getId());
        student.setMasteryScore(BigDecimal.valueOf(m.getMasteryScore()));student.setAttemptCount(m.getAttemptCount());student.setSuccessCount(m.getSuccessCount());
        student.setFailureCount(m.getFailureCount());student.setConsecutiveFailures(m.getConsecutiveFailures());student.setHintCount(m.getHintCount());
        student.setAverageResolutionTime(BigDecimal.valueOf(m.getAverageResolutionTime()));student.setCurrentResolutionTime(latest.getResolutionTime());
        student.setActivityPassed(evaluation.activityPassed());student.setFunctionalPassed(evaluation.functionalCorrectness().passed());
        student.setRequiredConceptUsed(evaluation.structuralCorrectness().requiredConceptUsed());
        student.getDetectedPatterns().addAll(evaluation.patterns().stream().map(p->p.id()).distinct().sorted().toList());
        student.setRepeatedErrorPattern(change.reasons().contains("REPEATED_ERROR_PATTERN"));
        return root(student);
    }
    public ContextModel restore(String studentId,String activityId,RuleEvaluationContext r) {
        var f=ContextFactory.eINSTANCE;
        var s=f.createStudentContext();s.setStudentId(studentId);s.setConceptId(r.concept());s.setActivityId(activityId);
        s.setMasteryScore(r.masteryScore());s.setAttemptCount(r.attemptCount());s.setSuccessCount(r.successCount());
        s.setFailureCount(r.failureCount());s.setConsecutiveFailures(r.consecutiveFailures());s.setHintCount(r.hintCount());
        s.setAverageResolutionTime(r.averageResolutionTime());s.setCurrentResolutionTime(r.currentResolutionTime());
        s.setActivityPassed(r.activityPassed());s.setFunctionalPassed(r.functionalPassed());s.setRequiredConceptUsed(r.requiredConceptUsed());
        s.getDetectedPatterns().addAll(r.detectedPatterns().stream().distinct().sorted().toList());s.setRepeatedErrorPattern(r.repeatedErrorPattern());
        return root(s);
    }
    private ContextModel root(StudentContext s) {
        var f=ContextFactory.eINSTANCE;var root=f.createContextModel();root.setModelVersion(1);root.setStudentContext(s);
        var platform=f.createPlatformContext();platform.setPlatform("WEB");root.setPlatformContext(platform);
        var environment=f.createEnvironmentContext();environment.setLevelId(s.getActivityId());environment.setActivityId(s.getActivityId());
        environment.setConceptId(s.getConceptId());root.setEnvironmentContext(environment);
        return root;
    }
    public void validate(ContextModel root,Set<String> concepts,Set<String> patterns) {
        if(Diagnostician.INSTANCE.validate(root).getSeverity()>=4 || root.getModelVersion()<=0)
            throw new IllegalArgumentException("Invalid ContextModel structure");
        var s=root.getStudentContext();var e=root.getEnvironmentContext();
        if(s.getStudentId().isBlank() || s.getActivityId().isBlank() || !concepts.contains(s.getConceptId()) ||
           !patterns.containsAll(s.getDetectedPatterns()) || !s.getConceptId().equals(e.getConceptId()) ||
           !s.getActivityId().equals(e.getActivityId()) || !e.getActivityId().equals(e.getLevelId()) ||
           !root.getPlatformContext().getPlatform().equals("WEB")) throw new IllegalArgumentException("Invalid context references");
        toRules(root); // Typed range checks.
        if(s.getSuccessCount()+s.getFailureCount()!=s.getAttemptCount() || s.getConsecutiveFailures()>s.getFailureCount())
            throw new IllegalArgumentException("Inconsistent context counters");
    }
    public static RuleEvaluationContext toRules(ContextModel model) {
        var s=model.getStudentContext();
        return new RuleEvaluationContext(s.getConceptId(),s.getMasteryScore(),s.getAttemptCount(),s.getSuccessCount(),
            s.getFailureCount(),s.getConsecutiveFailures(),s.getHintCount(),s.getAverageResolutionTime(),s.getCurrentResolutionTime(),
            s.isActivityPassed(),s.isFunctionalPassed(),s.isRequiredConceptUsed(),s.getDetectedPatterns(),s.isRepeatedErrorPattern());
    }
}
