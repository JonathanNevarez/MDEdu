package com.project.telemetry.domain;
import java.time.Instant;
import java.util.*;
public final class TelemetryTypes {
 private TelemetryTypes(){}
 public enum Source { BACKEND,EXECUTION,EVALUATION,LEARNING,ADAPTATION,LLM,UI,SYSTEM }
 public enum Type {
  SESSION_STARTED(SessionPayload.class,Source.SYSTEM),SESSION_ENDED(SessionPayload.class,Source.SYSTEM),
  ACTIVITY_OPENED(ActivityPayload.class,Source.UI),NAVIGATION_PRESENTED(NavigationPayload.class,Source.UI),
  ATTEMPT_CREATED(ActivityPayload.class,Source.BACKEND),PROGRAM_MODEL_CREATED(ModelPayload.class,Source.EXECUTION),
  PROGRAM_MODEL_VALIDATED(ModelPayload.class,Source.EXECUTION),EXECUTION_STARTED(MarkerPayload.class,Source.EXECUTION),
  EXECUTION_COMPLETED(ExecutionPayload.class,Source.EXECUTION),EVALUATION_COMPLETED(EvaluationPayload.class,Source.EVALUATION),
  ERROR_PATTERN_DETECTED(PatternPayload.class,Source.EVALUATION),STUDENT_MODEL_UPDATED(LearningPayload.class,Source.LEARNING),
  ADAPTATION_STARTED(MarkerPayload.class,Source.ADAPTATION),ADAPTATION_COMPLETED(AdaptationPayload.class,Source.ADAPTATION),
  FEEDBACK_REQUESTED(FeedbackPayload.class,Source.LLM),FEEDBACK_CLASSIFICATION_COMPLETED(ClassificationPayload.class,Source.LLM),
  FEEDBACK_GENERATED(FeedbackPayload.class,Source.LLM),FEEDBACK_FALLBACK_USED(FeedbackPayload.class,Source.LLM),
  UI_CONFIGURATION_CREATED(UiPayload.class,Source.UI),ACTIVITY_COMPLETED(ActivityPayload.class,Source.LEARNING),
  TECHNICAL_ERROR(ErrorPayload.class,Source.SYSTEM);
  public final Class<? extends Payload> payloadClass;public final Source source;
  Type(Class<? extends Payload> p,Source s){payloadClass=p;source=s;}
 }
 public sealed interface Payload permits SessionPayload,ActivityPayload,NavigationPayload,ModelPayload,MarkerPayload,ExecutionPayload,EvaluationPayload,PatternPayload,LearningPayload,AdaptationPayload,FeedbackPayload,ClassificationPayload,UiPayload,ErrorPayload {}
 public record SessionPayload(String status) implements Payload {}
 public record ActivityPayload(String activityId,String conceptId) implements Payload {}
 public record NavigationPayload(String activityId,String navigationMode,String configurationFingerprint) implements Payload {}
 public record ModelPayload(String programHash,String metamodelVersion,int statementCount,Boolean valid) implements Payload {}
 public record MarkerPayload(String status) implements Payload {}
 public record ExecutionPayload(boolean success,String status,int steps,boolean goalReached,int errorCount,String traceHash) implements Payload {}
 public record EvaluationPayload(int evaluationVersion,boolean functionalPassed,boolean structuralPassed,boolean activityPassed,boolean requiredConceptUsed,int patternCount) implements Payload {}
 public record PatternPayload(String patternId,String severity,String conceptId) implements Payload {}
 public record LearningPayload(String conceptId,double masteryBefore,double masteryDelta,double masteryAfter,int attemptCount,int successCount,int failureCount,int consecutiveFailures,int policyVersion) implements Payload {}
 public record AdaptationPayload(UUID decisionId,String decisionFingerprint,int rulesetVersion,String rulesetHash,int parametersVersion,String parametersHash,String selectedRule,int contributingRuleCount,int discardedRuleCount,List<String> actionTypes,String contextHash) implements Payload { public AdaptationPayload { actionTypes=List.copyOf(actionTypes); } }
 public record FeedbackPayload(UUID feedbackId,String provider,String model,String source,boolean llmUsed,String fallbackReason,String purpose,int promptTemplateVersion,int policyVersion,String promptHash,String sanitizedContextHash,int providerCallCount) implements Payload {}
 public record ClassificationPayload(UUID feedbackId,String status,int attempts,String validationReason) implements Payload {}
 public record UiPayload(int configurationVersion,String configurationFingerprint,String hintPanelMode,String navigationMode,String difficultyMode,String tutorMode,boolean showCodePanel,boolean safeDefault) implements Payload {}
 public record ErrorPayload(String errorCode,String component) implements Payload {}
 public record Event(UUID id,UUID attemptId,UUID sessionId,UUID studentId,UUID requestId,long sequence,Type type,Source source,int eventVersion,Instant occurredAt,Payload payload,String payloadHash) {}
 public record Session(UUID id,UUID studentId,Instant startedAt,Instant endedAt,String status) {}
 public enum TraceStatus { COMPLETE,PARTIAL,INCONSISTENT }
}
