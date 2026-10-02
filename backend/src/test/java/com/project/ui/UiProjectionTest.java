package com.project.ui;
import com.project.ui.application.UiProjection;
import com.project.mde.ui.*;
import com.project.mde.adaptation.ActionType;
import com.project.mde.adaptation.HintLevel;
import com.project.mde.adaptation.FeedbackStyle;
import com.project.adaptation.manager.DecisionTypes.ActionDto;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import static org.junit.jupiter.api.Assertions.*;
class UiProjectionTest {
    final UiProjection service=new UiProjection();
    ConcreteUIModel base(){var b=UiFactory.eINSTANCE.createConcreteUIModel();b.setActivityId("LOOPS");b.setConceptId("LOOPS");b.setOriginId("LOOPS");for(var kind:ConcreteKind.values()){var e=UiFactory.eINSTANCE.createConcreteElement();e.setKind(kind);e.setOriginId("LOOPS:"+kind);b.getElements().add(e);}return b;}
    @ParameterizedTest @EnumSource(ActionType.class) void allActionsHaveClosedMapping(ActionType type){
        var c=service.project(base(),List.of(new ActionDto(type,HintLevel.GUIDED,FeedbackStyle.EXPLANATORY,"NEXT")),null,Map.of("NEXT","NEXT_ACTIVITY"));
        switch(type){
            case SHOW_HINT,CHANGE_HINT_LEVEL -> {assertEquals(HintPanelMode.EXPANDED,c.getHintPanelMode());assertEquals(HintStage.ANALOGOUS_EXAMPLE,c.getHintStage());}
            case REPEAT_ACTIVITY -> {assertEquals(NavigationMode.REPEAT,c.getNavigationMode());assertTrue(c.isRepeatCurrentActivity());}
            case SELECT_REINFORCEMENT_ACTIVITY -> {assertEquals(NavigationMode.STAY,c.getNavigationMode());assertNull(c.getNextActivityId());}
            case ADVANCE_TO_NEXT_CONCEPT -> assertEquals("NEXT_ACTIVITY",c.getNextActivityId());
            case INCREASE_DIFFICULTY -> assertEquals(DifficultyMode.INCREASED,c.getDifficultyMode());
            case DECREASE_DIFFICULTY -> assertEquals(DifficultyMode.REDUCED,c.getDifficultyMode());
            case SHOW_CODE_VIEW -> assertTrue(c.isShowCodePanel());
            case HIDE_CODE_VIEW -> assertFalse(c.isShowCodePanel());
            case CHANGE_FEEDBACK_STYLE -> assertEquals(FeedbackDetailLevel.DETAILED,c.getFeedbackDetailLevel());
        }
    }
    @Test void rejectsUnsupportedCodeAndLockedNavigation(){
        var b=base();b.getElements().removeIf(e->e.getKind()==ConcreteKind.CODE_PANEL);
        assertThrows(IllegalArgumentException.class,()->service.project(b,List.of(new ActionDto(ActionType.SHOW_CODE_VIEW,null,null,null)),null,Map.of()));
        assertThrows(IllegalArgumentException.class,()->service.project(base(),List.of(new ActionDto(ActionType.ADVANCE_TO_NEXT_CONCEPT,null,null,"LOCKED")),null,Map.of()));
    }
    @Test void safeDefaultAndTwoConfigurationsDiffer(){
        var a=service.safe("LOOPS");var b=service.project(base(),List.of(new ActionDto(ActionType.SHOW_CODE_VIEW,null,null,null),new ActionDto(ActionType.CHANGE_HINT_LEVEL,HintLevel.DIRECT,null,null)),null,Map.of());
        assertFalse(a.isShowCodePanel());assertEquals(HintPanelMode.HIDDEN,a.getHintPanelMode());assertEquals(NavigationMode.STAY,a.getNavigationMode());assertNull(a.getNextActivityId());
        assertTrue(b.isShowCodePanel());assertEquals(HintPanelMode.EXPANDED,b.getHintPanelMode());
    }
}
