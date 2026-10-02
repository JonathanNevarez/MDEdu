package com.project.ui.application;

import com.project.adaptation.manager.DecisionTypes.ActionDto;
import com.project.mde.ui.*;
import com.project.llm.domain.LlmTypes.FeedbackDto;
import java.util.*;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.springframework.stereotype.Component;

/** Presentation overlay of already resolved actions. No ECA, mastery or conflict resolution. */
@Component
public class UiProjection {
    public FinalUIConfiguration safe(String activity) {
        var c=UiFactory.eINSTANCE.createFinalUIConfiguration();c.setActivityId(activity);return c;
    }
    public FinalUIConfiguration project(ConcreteUIModel base,List<ActionDto> actions,
            FeedbackDto feedback,Map<String,String> allowedNextActivities) {
        var c=safe(base.getActivityId());
        for(var a:actions) switch(a.type()) {
            case SHOW_HINT, CHANGE_HINT_LEVEL -> {
                c.setHintPanelMode(a.hintLevel()==null || a.hintLevel()==com.project.mde.adaptation.HintLevel.CONCEPTUAL ? HintPanelMode.COMPACT : HintPanelMode.EXPANDED);
                c.setHintStage(a.hintLevel()==null ? HintStage.SOCRATIC_QUESTION : switch(a.hintLevel()) {
                    case CONCEPTUAL -> HintStage.CONCEPTUAL_HINT;
                    case GUIDED -> HintStage.ANALOGOUS_EXAMPLE;
                    case DIRECT -> HintStage.PARTIAL_HELP;
                });
                c.setTutorMode(TutorMode.HINT);
            }
            case REPEAT_ACTIVITY -> {c.setNavigationMode(NavigationMode.REPEAT);c.setRepeatCurrentActivity(true);}
            case SELECT_REINFORCEMENT_ACTIVITY -> { /* No real reinforcement activities exist. */ }
            case ADVANCE_TO_NEXT_CONCEPT -> {
                var target=allowedNextActivities.get(a.targetConceptId());
                if(target==null)throw new IllegalArgumentException("Invalid navigation target");
                c.setNavigationMode(NavigationMode.ADVANCE);c.setNextActivityId(target);
            }
            case INCREASE_DIFFICULTY -> {c.setDifficultyMode(DifficultyMode.INCREASED);c.setActivityLayout(ActivityLayout.FOCUSED);}
            case DECREASE_DIFFICULTY -> {c.setDifficultyMode(DifficultyMode.REDUCED);c.setActivityLayout(ActivityLayout.ASSISTED);}
            case SHOW_CODE_VIEW -> {c.setShowCodePanel(true);c.setGeneratedCodeVisible(true);}
            case HIDE_CODE_VIEW -> {c.setShowCodePanel(false);c.setGeneratedCodeVisible(false);}
            case CHANGE_FEEDBACK_STYLE -> c.setFeedbackDetailLevel(a.feedbackStyle()==com.project.mde.adaptation.FeedbackStyle.CONCISE ? FeedbackDetailLevel.MINIMAL : FeedbackDetailLevel.DETAILED);
        }
        c.setEnabledAssistance(c.getHintPanelMode()!=HintPanelMode.HIDDEN || c.getDifficultyMode()==DifficultyMode.REDUCED);
        if(c.isEnabledAssistance() && c.getTutorMode()==TutorMode.HIDDEN)c.setTutorMode(TutorMode.GUIDE);
        if(feedback!=null) {
            c.setFeedbackMessage(feedback.message());
            if(c.getHintPanelMode()!=HintPanelMode.HIDDEN)c.setHintStage(HintStage.get(feedback.hintStage().name()));
            else c.setTutorMode(TutorMode.FEEDBACK);
        }
        c.setTransitionMode(actions.isEmpty()?TransitionMode.NONE:TransitionMode.SUBTLE);
        validate(base,c,allowedNextActivities);return c;
    }
    public void validate(ConcreteUIModel base,FinalUIConfiguration c,Map<String,String> allowed) {
        if(Diagnostician.INSTANCE.validate(c).getSeverity()!=Diagnostic.OK || c.getConfigurationVersion()!=1 || !base.getActivityId().equals(c.getActivityId()))throw new IllegalArgumentException("Invalid UI model");
        var kinds=EnumSet.noneOf(ConcreteKind.class);base.getElements().forEach(e->kinds.add(e.getKind()));
        if(c.isShowCodePanel()!=c.isGeneratedCodeVisible() || c.isShowCodePanel()&&!kinds.contains(ConcreteKind.CODE_PANEL))throw new IllegalArgumentException("Unsupported code view");
        if(c.getHintPanelMode()!=HintPanelMode.HIDDEN && (!kinds.contains(ConcreteKind.HINT_PANEL)||c.getHintStage()==HintStage.NONE||c.getTutorMode()!=TutorMode.HINT))throw new IllegalArgumentException("Invalid hint configuration");
        if(c.getTutorMode()!=TutorMode.HIDDEN&&!kinds.contains(ConcreteKind.LUMA_TUTOR))throw new IllegalArgumentException("Unsupported tutor");
        if((c.getNavigationMode()==NavigationMode.ADVANCE)!=(c.getNextActivityId()!=null) || c.getNextActivityId()!=null&&!allowed.containsValue(c.getNextActivityId()))throw new IllegalArgumentException("Invalid navigation");
        if(c.isRepeatCurrentActivity()!=(c.getNavigationMode()==NavigationMode.REPEAT))throw new IllegalArgumentException("Invalid repeat");
    }
}
