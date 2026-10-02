package com.project.student;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.student.domain.*;
import java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class LearningDomainTest {
    final StudentModelPolicy.Values policy;
    LearningDomainTest() throws Exception {policy=new StudentModelPolicy(new ObjectMapper()).values();}
    MasteryUpdater.State zero(){return new MasteryUpdater.State(0,0,0,0,0,0,0);}
    @Test void transparentDeltasAndSuccessWithHints() {
        assertEquals(.1,MasteryUpdater.update(zero(),true,0,1000,List.of(),List.of(),policy).after());
        assertEquals(.05,MasteryUpdater.update(zero(),true,1,1000,List.of(),List.of(),policy).after());
        assertEquals(-.03,MasteryUpdater.update(zero(),false,0,1000,List.of(),List.of(),policy).delta());
    }
    @Test void repeatedErrorAppliesOnlyOnceAndWithinWindow() {
        var c=MasteryUpdater.update(zero(),false,0,1,List.of("P","Q"),List.of(List.of("P","Q"),List.of("P")),policy);
        assertEquals(-.05,c.delta());assertEquals(List.of("ACTIVITY_FAILURE","REPEATED_ERROR_PATTERN","MASTERY_CLAMPED"),c.reasons());
        assertEquals(-.03,MasteryUpdater.update(zero(),false,0,1,List.of("P"),List.of(List.of(),List.of(),List.of(),List.of(),List.of(),List.of("P")),policy).delta());
    }
    @Test void clampBothEnds() {
        var state=zero();for(int i=0;i<30;i++)state=MasteryUpdater.update(state,true,0,1,List.of(),List.of(),policy).state();assertEquals(1,state.score());
        for(int i=0;i<100;i++)state=MasteryUpdater.update(state,false,0,1,List.of(),List.of(),policy).state();assertEquals(0,state.score());assertEquals(130,state.attempts());
    }
    @Test void countersAndConsecutiveFailures() {
        var s=zero();int[] expected={1,2,0,1};boolean[] pass={false,false,true,false};
        for(int i=0;i<4;i++){s=MasteryUpdater.update(s,pass[i],0,0,List.of(),List.of(),policy).state();assertEquals(expected[i],s.consecutiveFailures());}
        assertEquals(4,s.attempts());assertEquals(1,s.successes());assertEquals(3,s.failures());
    }
    @Test void incrementalAverage() {
        var s=MasteryUpdater.update(zero(),true,0,1000,List.of(),List.of(),policy).state();
        s=MasteryUpdater.update(s,true,2,3000,List.of(),List.of(),policy).state();assertEquals(2000,s.averageTime());
        s=MasteryUpdater.update(s,false,0,5000,List.of(),List.of(),policy).state();assertEquals(3000,s.averageTime());assertEquals(2,s.hints());
    }
    @Test void recentDistinctOrderedLimitedAndConfigurable() {
        var custom=new StudentModelPolicy.Values(1,0,.1,.05,-.03,-.02,2,3);
        assertEquals(List.of("Q","P","R"),MasteryUpdater.recentPatterns(List.of(List.of("Q","P"),List.of("P","R","S"),List.of("T")),custom));
    }
    @Test void alternativeGraphWithoutCodeChanges() {
        var graph=new ConceptGraphService();var nodes=List.of(new ConceptGraphService.Node("A",.05,Set.of()),new ConceptGraphService.Node("B",.05,Set.of("A")),new ConceptGraphService.Node("C",.05,Set.of("A")));
        assertEquals(Set.of("A"),graph.unlocked(nodes,Map.of(),Set.of()));
        assertEquals(Set.of("A","B","C"),graph.unlocked(nodes,Map.of("A",new ConceptGraphService.Achievement(1,.05)),Set.of()));
        assertEquals(Set.of("A"),graph.unlocked(nodes,Map.of("A",new ConceptGraphService.Achievement(0,1)),Set.of()));
        assertEquals(Set.of("A","B"),graph.unlocked(nodes,Map.of(),Set.of("B")));
    }
    @Test void graphRejectsCycleAndMissingReference() {
        var g=new ConceptGraphService();assertThrows(IllegalArgumentException.class,()->g.unlocked(List.of(new ConceptGraphService.Node("A",.05,Set.of("B"))),Map.of(),Set.of()));
        assertThrows(IllegalArgumentException.class,()->g.unlocked(List.of(new ConceptGraphService.Node("A",.05,Set.of("A"))),Map.of(),Set.of()));
    }
}
