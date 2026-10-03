package com.project.metaui.dto;

import java.time.Instant;
import java.util.*;
import com.project.student.api.LearningDtos.*;
import com.project.adaptation.manager.DecisionTypes.DecisionDto;

public final class MetaUiDtos {
    private MetaUiDtos() {}
    public record Page<T>(List<T> items, int page, int size, long total) {}
    public record Capabilities(boolean canViewStudentModel, boolean canViewRules, boolean canViewParameters,
        boolean canViewTrace, boolean canMutateRules, boolean canMutateParameters, boolean canReviewProposals) {}
    public record StudentSummary(UUID studentId, Instant createdAt, Instant lastInteraction, long attemptCount, long completedConceptCount) {}
    public record ConceptInfo(String conceptId, String name, double masteryThreshold, List<String> prerequisites) {}
    public record Overview(UUID studentId, int modelVersion, Instant lastUpdated, List<MasteryDto> conceptMasteries,
        ProgressDto progress, List<ConceptInfo> concepts) {}
    public record AttemptSummary(UUID attemptId, String activityId, String conceptId, Instant submittedAt,
        boolean functionalPassed, boolean activityPassed, List<String> patterns, String timelineUrl) {}
    public record AdaptationSummary(String activityId, String conceptId, DecisionDto decision) {}
}
