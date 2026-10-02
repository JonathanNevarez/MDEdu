package com.project.mde.adaptation.validation;

import java.util.Arrays;
import java.util.Optional;

/** The single typed vocabulary shared by validation and the runtime projection. */
public enum ContextAttribute {
    concept(Kind.CONCEPT), masteryScore(Kind.DECIMAL), attemptCount(Kind.INTEGER),
    successCount(Kind.INTEGER), failureCount(Kind.INTEGER), consecutiveFailures(Kind.INTEGER),
    hintCount(Kind.INTEGER), averageResolutionTime(Kind.DECIMAL), currentResolutionTime(Kind.INTEGER),
    activityPassed(Kind.BOOLEAN), functionalPassed(Kind.BOOLEAN), requiredConceptUsed(Kind.BOOLEAN),
    detectedPatterns(Kind.PATTERNS), repeatedErrorPattern(Kind.BOOLEAN);

    public enum Kind { CONCEPT, DECIMAL, INTEGER, BOOLEAN, PATTERNS }
    private final Kind kind;
    ContextAttribute(Kind kind) { this.kind = kind; }
    public Kind kind() { return kind; }
    public static Optional<ContextAttribute> find(String name) {
        return Arrays.stream(values()).filter(a -> a.name().equals(name)).findFirst();
    }
}
