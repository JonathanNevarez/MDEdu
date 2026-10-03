package com.project.llm.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

/** Immutable, provider-neutral contracts. No entities or executable student text. */
public final class LlmTypes {
    private LlmTypes() {}
    public enum Provider { DISABLED, FAKE, OPENAI, GEMINI }
    public enum Purpose { FEEDBACK_GENERATION, UNKNOWN_CASE_CLASSIFICATION }
    public enum Status { SUCCESS, TIMEOUT, RATE_LIMITED, PROVIDER_ERROR, INVALID_RESPONSE, REFUSED, DISABLED }
    public enum Source { OPENAI, GEMINI, FAKE, FALLBACK;
        public boolean isRemote(){return this==OPENAI || this==GEMINI;}
    }
    public enum HintStage { SOCRATIC_QUESTION, CONCEPTUAL_HINT, ANALOGOUS_EXAMPLE, PARTIAL_HELP }
    public record ExecutionSummary(boolean goalReached, int steps, String executionStatus,
        Map<String,Integer> statementCounts, List<String> sensors, int nestingDepth) {
        public ExecutionSummary { statementCounts=Collections.unmodifiableMap(new TreeMap<>(statementCounts)); sensors=List.copyOf(sensors); }
    }
    public record AttemptEvidence(String concept, String activityObjective, BigDecimal masteryScore,
        int attemptNumber, int hintCount, boolean activityPassed, boolean requiredConceptUsed,
        boolean analyzable, List<String> detectedPatterns, ExecutionSummary executionSummary) {
        public AttemptEvidence { detectedPatterns=List.copyOf(detectedPatterns); }
    }
    public record PedagogicalLlmContext(String concept, String activityObjective, BigDecimal masteryScore,
        int attemptNumber, boolean activityPassed, boolean requiredConceptUsed, String detectedPattern,
        String patternMeaning, String recommendedAction, ExecutionSummary executionSummary,
        HintStage hintStage, List<String> previousHints, List<String> decisionActions, List<String> complementaryTags) {
        public PedagogicalLlmContext { previousHints=List.copyOf(previousHints); decisionActions=List.copyOf(decisionActions); complementaryTags=List.copyOf(complementaryTags); }
    }
    public record Prompt(Purpose purpose, int templateVersion, String instructions, String data,
        String schemaName, String schemaJson, String promptHash, String sanitizedContextHash) {}
    public record ProviderResult(Status status, String output, int attempts, String requestId) {
        public static ProviderResult failure(Status s,int attempts) {return new ProviderResult(s,null,attempts,null);}
        @Override public String toString() {return "ProviderResult[status="+status+", attempts="+attempts+"]";}
    }
    public record Feedback(String message, String question, String focus, HintStage hintStage, String language) {}
    public record Classification(boolean recognized, List<String> errorTags, String explanation, BigDecimal confidence) {
        public Classification { errorTags=List.copyOf(errorTags); }
    }
    public record CallAudit(Purpose purpose, Status status, int attempts, String promptHash,
        String sanitizedContextHash, String providerRequestId, String validationReason) {}
    public record FeedbackDto(UUID feedbackId, UUID attemptId, UUID adaptationDecisionId, Purpose purpose,
        String message, String question, String focus, HintStage hintStage, String language,
        Source source, Provider provider, String model, boolean llmUsed, String fallbackReason,
        Classification classification, int promptTemplateVersion, int policyVersion, String promptHash,
        String sanitizedContextHash, Instant createdAt) {}
}
