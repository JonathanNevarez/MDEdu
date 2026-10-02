package com.project.evaluation.domain;

import java.util.List;
import java.util.Map;

public final class EvaluationTypes {
    private EvaluationTypes() {}
    public enum Concept { SEQUENCES, VARIABLES, CONDITIONALS, LOOPS }
    public enum Severity { INFO, WARNING, ERROR }
    public record PatternDefinition(String id, int version, Concept concept, Severity severity,
        String detector, String pedagogicalMeaning, String recommendedAction, int defaultHintLevel) {}
    public record PatternCatalogData(int version, List<PatternDefinition> patterns) {}
    public record Parameters(int minimumManualRepetitions, int minimumLoopIterations, int expectedIterationCount,
        int declarationOrdinal, String variableType, int expectedInitialValue, int expectedFinalValue,
        boolean requireDynamicCondition, boolean requireBothBranches) {}
    public record LevelEvaluationConfig(String levelId, Concept requiredConcept, List<String> requiredConstructs,
        List<String> blockingPatternIds, List<String> referenceActions, Parameters parameters) {}
    public record LevelEvaluationData(int version, List<LevelEvaluationConfig> levels) {}
    public record Evidence(String statementPath, Integer traceIndex, String observed, String expected) {}
    public record PatternDetection(String id, Concept concept, Severity severity, String pedagogicalMeaning,
        String recommendedAction, int defaultHintLevel, Evidence evidence) {}
    public record FunctionalCorrectness(boolean passed, boolean goalReached, String executionStatus,
        boolean runtimeError, int steps) {}
    public record StructuralCorrectness(Concept requiredConcept, boolean requiredConceptUsed,
        boolean requiredConstructsSatisfied, boolean constraintsSatisfied, Map<String, Boolean> structuralConstraints) {}
    public record PedagogicalEfficiency(String status) {}
    public record EvaluationResult(int evaluationVersion, String levelId, boolean activityPassed,
        FunctionalCorrectness functionalCorrectness, StructuralCorrectness structuralCorrectness,
        PedagogicalEfficiency pedagogicalEfficiency, List<PatternDetection> patterns) {}
}
