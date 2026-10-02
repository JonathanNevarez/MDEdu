package com.project.adaptation.domain;

import java.util.List;
import com.project.mde.adaptation.*;

public record RuleEngineResult(int ruleSetVersion, int rulesEvaluated, int rulesMatched,
    List<RuleMatch> matches, List<IgnoredRule> ignoredRules, List<Conflict> conflictsDetected) {
    public RuleEngineResult { matches=List.copyOf(matches); ignoredRules=List.copyOf(ignoredRules); conflictsDetected=List.copyOf(conflictsDetected); }
    public record ConditionEvidence(String attribute, String operator, String observed, String expected, boolean satisfied) {}
    public record ActionCandidate(String ruleId, int ruleVersion, int priority, int actionIndex,
                                  ActionType type, HintLevel hintLevel, FeedbackStyle feedbackStyle) {}
    public record RuleMatch(String ruleId, int version, int priority, List<ActionCandidate> actions,
                            List<ConditionEvidence> evidence) {
        public RuleMatch { actions=List.copyOf(actions); evidence=List.copyOf(evidence); }
    }
    public record IgnoredRule(String ruleId, String reason) {}
    public record Conflict(ActionType first, ActionType second, List<String> ruleIds) {
        public Conflict { ruleIds=List.copyOf(ruleIds); }
    }
}
