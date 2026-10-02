package com.project.evaluation.detectors;
import com.project.evaluation.domain.*;
import com.project.evaluation.domain.EvaluationTypes.*;
import com.project.mde.programming.*;
import java.util.*;
final class LoopDetectors {
    static List<Evidence> manual(EvaluationContext c) {
        var out=new ArrayList<Evidence>();int minimum=c.config.parameters().minimumManualRepetitions();
        for(var block:c.blocks()) {
            if(block.isEmpty() || c.insideLoop(block.getFirst()))continue;
            var fingerprints=block.stream().map(c.fingerprints::node).toList();
            var loopPrefix=new int[block.size()+1];
            for(int i=0;i<block.size();i++)loopPrefix[i+1]=loopPrefix[i]+(block.get(i) instanceof Repeat || block.get(i) instanceof While ? 1 : 0);
            // Earliest start, shortest repeating unit. Emit one maximal run, then advance.
            for(int start=0;start<block.size();start++) {
                for(int width=1;width<=(block.size()-start)/minimum;width++) {
                    if(loopPrefix[start+width]!=loopPrefix[start])continue;
                    int count=1;
                    while(start+(count+1)*width<=block.size() && sameUnit(fingerprints,start,start+count*width,width))count++;
                    if(count>=minimum) {out.add(c.evidence(block.get(start),"unitLength="+width+", repetitions="+count,"represent repetition with loop"));start+=count*width-1;break;}
                }
            }
        }
        return out;
    }
    private static boolean sameUnit(List<String> fingerprints,int first,int second,int width) {
        for(int i=0;i<width;i++)if(!fingerprints.get(first+i).equals(fingerprints.get(second+i)))return false;
        return true;
    }
    static List<Evidence> never(EvaluationContext c) {
        var out=new ArrayList<Evidence>();for(var s:c.statements) {
            if(!c.events(s,"LOOP_ITERATION").isEmpty())continue;
            if(s instanceof Repeat) c.first(s,"LOOP_COUNT_EVALUATED").filter(e->e.detail().equals("0"))
                .ifPresent(e->out.add(new Evidence(c.path(s),e.index(),"count=0","positive iterations")));
            if(s instanceof While) c.first(s,"CONDITION_EVALUATED").filter(e->e.detail().equals("false"))
                .ifPresent(e->out.add(new Evidence(c.path(s),e.index(),"initial condition=false","at least one iteration")));
        }return out;
    }
    static List<Evidence> count(EvaluationContext c) {
        var loop=c.statements.stream().filter(s->s instanceof Repeat || s instanceof While).findFirst();
        if(loop.isEmpty())return List.of();var s=loop.get();int expected=c.config.parameters().expectedIterationCount();
        var counts=c.events(s,"LOOP_COUNT_EVALUATED");
        var finished=c.events(s,"LOOP_FINISHED");
        // Only complete activations or explicitly evaluated Repeat counts: never guess after interruption.
        var evidence=s instanceof Repeat?counts:finished;
        return evidence.stream().filter(e->!e.detail().equals(Integer.toString(expected)))
            .map(e->new Evidence(c.path(s),e.index(),"iterations="+e.detail(),"iterations="+expected)).toList();
    }
    static List<Evidence> unnecessary(EvaluationContext c) {
        var out=new ArrayList<Evidence>();for(var s:c.statements)if(s instanceof Repeat)
            c.first(s,"LOOP_COUNT_EVALUATED").filter(e->e.detail().equals("1"))
                .ifPresent(e->out.add(new Evidence(c.path(s),e.index(),"count=1","avoid single-iteration loop")));
        return out;
    }
    static List<Evidence> infinite(EvaluationContext c) {
        var out=new ArrayList<Evidence>();for(var s:c.statements)if(s instanceof While w) {
            boolean constant=c.constants.value(w.getCondition()).filter(Boolean.TRUE::equals).isPresent();
            var exhausted=c.first(s,"STEP_LIMIT_EXCEEDED");
            if(constant || exhausted.isPresent())out.add(new Evidence(c.path(s),exhausted.map(e->e.index()).orElse(null),
                constant?"statically true condition":"budget exhausted in this While control","condition can terminate"));
        }return out;
    }
}
