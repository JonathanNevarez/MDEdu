package com.project.adaptation.domain;

import java.math.BigDecimal;
import java.util.*;
import com.project.mde.adaptation.validation.ContextAttribute;

public record RuleEvaluationContext(String concept, BigDecimal masteryScore, int attemptCount,
    int successCount, int failureCount, int consecutiveFailures, int hintCount,
    BigDecimal averageResolutionTime, long currentResolutionTime, boolean activityPassed,
    boolean functionalPassed, boolean requiredConceptUsed, List<String> detectedPatterns,
    boolean repeatedErrorPattern) {
    public RuleEvaluationContext {
        Objects.requireNonNull(concept); Objects.requireNonNull(masteryScore); Objects.requireNonNull(averageResolutionTime);
        detectedPatterns = List.copyOf(detectedPatterns);
        if (masteryScore.signum() < 0 || masteryScore.compareTo(BigDecimal.ONE) > 0 ||
            attemptCount < 0 || successCount < 0 || failureCount < 0 || consecutiveFailures < 0 || hintCount < 0 ||
            averageResolutionTime.signum() < 0 || currentResolutionTime < 0)
            throw new IllegalArgumentException("Invalid rule context");
    }
    public Object value(ContextAttribute attribute) {
        return switch (attribute) {
            case concept -> concept;
            case masteryScore -> masteryScore;
            case attemptCount -> BigDecimal.valueOf(attemptCount);
            case successCount -> BigDecimal.valueOf(successCount);
            case failureCount -> BigDecimal.valueOf(failureCount);
            case consecutiveFailures -> BigDecimal.valueOf(consecutiveFailures);
            case hintCount -> BigDecimal.valueOf(hintCount);
            case averageResolutionTime -> averageResolutionTime;
            case currentResolutionTime -> BigDecimal.valueOf(currentResolutionTime);
            case activityPassed -> activityPassed;
            case functionalPassed -> functionalPassed;
            case requiredConceptUsed -> requiredConceptUsed;
            case detectedPatterns -> detectedPatterns;
            case repeatedErrorPattern -> repeatedErrorPattern;
        };
    }
}
