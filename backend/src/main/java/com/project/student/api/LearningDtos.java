package com.project.student.api;
import com.project.programming.api.ProgramDto;
import com.project.evaluation.api.EvaluatedExecution;
import com.project.student.domain.MasteryUpdater;
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.*;
public final class LearningDtos {
    private LearningDtos() {}
    public record CreateStudent(@Size(max=80) String displayName) {}
    public record StudentDto(UUID id,String displayName,Instant createdAt) {}
    public record SubmitAttempt(@NotNull UUID studentId,@NotBlank String levelId,
        @NotNull @Min(0) @Max(86400000) Long resolutionTimeMs,@NotNull @Min(0) @Max(100) Integer hintCount,@NotNull ProgramDto program) {}
    public record MasteryDto(String conceptId,double masteryScore,int attemptCount,int successCount,int failureCount,
        int consecutiveFailures,double averageResolutionTime,int hintCount,List<String> recentErrorPatterns,Instant lastUpdated) {}
    public record AttemptDto(String id,String levelId,String conceptId,boolean successful,boolean functionalPassed,long resolutionTimeMs,int hintCount,Instant submittedAt,List<String> errorPatterns) {}
    public record ModelDto(StudentDto student,int modelVersion,List<MasteryDto> conceptMasteries,List<AttemptDto> recentAttempts,Instant lastUpdated) {}
    public record ProgressEntry(String levelId,String conceptId,boolean completed,boolean unlocked,double masteryScore,int attemptCount) {}
    public record ProgressDto(List<ProgressEntry> levels) {}
    public record AttemptResponse(UUID attemptId,EvaluatedExecution execution,ModelDto studentModel,ProgressDto progress,MasteryUpdater.Change masteryUpdate,com.project.adaptation.manager.DecisionTypes.DecisionDto adaptation) {}
}
