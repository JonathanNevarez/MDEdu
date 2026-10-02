package com.project.adaptation.manager;

import java.util.*;
import java.time.Instant;
import com.project.adaptation.domain.RuleEvaluationContext;
import com.project.adaptation.domain.RuleEngineResult.ConditionEvidence;
import com.project.mde.adaptation.*;

public final class DecisionTypes {
    private DecisionTypes() {}
    public record Resources(String activityId,List<String> nextConcepts,List<String> reinforcementActivities) {
        public Resources { nextConcepts=List.copyOf(nextConcepts);reinforcementActivities=List.copyOf(reinforcementActivities); }
    }
    public record Snapshot(RuleEvaluationContext context,Resources resources) {}
    public record ActionDto(ActionType type,HintLevel hintLevel,FeedbackStyle feedbackStyle,String targetConceptId) {}
    public record RuleAudit(String ruleId,int version,int priority,int specificity,String severity,int sourceOrder,
        String status,String reasonCode,List<ConditionEvidence> evidence) {
        public RuleAudit { evidence=List.copyOf(evidence); }
    }
    public record ActionAudit(String ruleId,int sourceActionOrder,ActionDto action,String status,String reasonCode) {}
    public record Explanation(String selectedRule,String reason,List<String> reasonCodes,List<String> evidence) {
        public Explanation { reasonCodes=List.copyOf(reasonCodes);evidence=List.copyOf(evidence); }
    }
    public record SemanticDecision(int rulesetVersion,int parametersVersion,int rulesEvaluated,List<String> rulesMatched,
        String selectedRule,List<String> contributingRules,List<RuleAudit> discardedRules,List<ActionDto> actions,
        Explanation explanation,List<RuleAudit> ruleAudit,List<ActionAudit> actionAudit,
        String contextHash,String rulesetHash,String parametersHash,boolean llmUsed,String llmPurpose) {
        public SemanticDecision {
            rulesMatched=List.copyOf(rulesMatched);contributingRules=List.copyOf(contributingRules);discardedRules=List.copyOf(discardedRules);
            actions=List.copyOf(actions);ruleAudit=List.copyOf(ruleAudit);actionAudit=List.copyOf(actionAudit);
        }
    }
    public record DecisionDto(UUID decisionId,UUID studentId,UUID attemptId,Instant createdAt,
        int rulesetVersion,int parametersVersion,int rulesEvaluated,List<String> rulesMatched,String selectedRule,
        List<String> contributingRules,List<RuleAudit> discardedRules,List<ActionDto> actions,Explanation explanation,
        List<RuleAudit> ruleAudit,List<ActionAudit> actionAudit,String contextHash,String rulesetHash,String parametersHash,
        String decisionFingerprint,boolean llmUsed,String llmPurpose) {
        public static DecisionDto of(UUID id,UUID student,UUID attempt,Instant at,SemanticDecision s,String fingerprint) {
            return new DecisionDto(id,student,attempt,at,s.rulesetVersion(),s.parametersVersion(),s.rulesEvaluated(),s.rulesMatched(),
                s.selectedRule(),s.contributingRules(),s.discardedRules(),s.actions(),s.explanation(),s.ruleAudit(),s.actionAudit(),
                s.contextHash(),s.rulesetHash(),s.parametersHash(),fingerprint,s.llmUsed(),s.llmPurpose());
        }
    }
}
