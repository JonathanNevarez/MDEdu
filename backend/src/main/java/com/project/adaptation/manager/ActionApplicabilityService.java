package com.project.adaptation.manager;

import com.project.adaptation.domain.RuleEngineResult.ActionCandidate;
import com.project.adaptation.domain.RuleEvaluationContext;
import com.project.adaptation.manager.DecisionTypes.*;
import com.project.mde.adaptation.*;
import org.springframework.stereotype.Service;

@Service
public final class ActionApplicabilityService {
    public record Check(ActionDto action,String rejection) {}
    public Check check(ActionCandidate c,RuleEvaluationContext context,Resources resources,AdaptationParametersConfig.Values p) {
        String reason=null,target=null;
        switch(c.type()) {
            case INCREASE_DIFFICULTY,DECREASE_DIFFICULTY -> { if(!p.difficultyAdjustmentEnabled())reason="ACTION_DISABLED_BY_PARAMETERS"; }
            case ADVANCE_TO_NEXT_CONCEPT -> {
                if(!p.routeAdaptationEnabled())reason="ACTION_DISABLED_BY_PARAMETERS";
                else if(resources.nextConcepts().isEmpty())reason="NOT_APPLICABLE_NO_NEXT_CONCEPT";
                else if(context.masteryScore().compareTo(p.masteryThreshold())<0 || context.successCount()<p.successThreshold())reason="ACTION_NOT_APPLICABLE";
                else target=resources.nextConcepts().getFirst();
            }
            case SELECT_REINFORCEMENT_ACTIVITY -> {
                if(resources.reinforcementActivities().isEmpty())reason="NOT_APPLICABLE_NO_REINFORCEMENT_ACTIVITY";
                else if(!p.routeAdaptationEnabled())reason="ACTION_DISABLED_BY_PARAMETERS";
                else if(context.attemptCount()<p.maxAttemptsBeforeReinforcement() || context.consecutiveFailures()<p.failureThreshold())reason="ACTION_NOT_APPLICABLE";
            }
            case REPEAT_ACTIVITY -> { if(resources.activityId()==null || resources.activityId().isBlank())reason="ACTION_NOT_APPLICABLE"; }
            case SHOW_HINT -> {if(c.hintLevel()==null)reason="ACTION_NOT_APPLICABLE";else if(context.hintCount()>=p.maxHintsPerActivity())reason="ACTION_DISABLED_BY_PARAMETERS";}
            case CHANGE_HINT_LEVEL -> {if(c.hintLevel()==null)reason="ACTION_NOT_APPLICABLE";}
            case CHANGE_FEEDBACK_STYLE -> {if(c.feedbackStyle()==null)reason="ACTION_NOT_APPLICABLE";}
            case SHOW_CODE_VIEW,HIDE_CODE_VIEW -> { }
        }
        return new Check(new ActionDto(c.type(),c.hintLevel(),c.feedbackStyle(),target),reason);
    }
    public static boolean incompatible(ActionDto a,ActionDto b) {
        if(a.type()==b.type()) return !a.equals(b); // A single hint level/style per decision.
        return pair(a.type(),b.type(),ActionType.INCREASE_DIFFICULTY,ActionType.DECREASE_DIFFICULTY) ||
            pair(a.type(),b.type(),ActionType.SHOW_CODE_VIEW,ActionType.HIDE_CODE_VIEW) ||
            pair(a.type(),b.type(),ActionType.ADVANCE_TO_NEXT_CONCEPT,ActionType.REPEAT_ACTIVITY);
    }
    private static boolean pair(ActionType a,ActionType b,ActionType x,ActionType y) {return a==x&&b==y || a==y&&b==x;}
}
