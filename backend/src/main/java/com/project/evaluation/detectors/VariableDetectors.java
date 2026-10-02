package com.project.evaluation.detectors;
import com.project.evaluation.domain.*;
import com.project.evaluation.domain.EvaluationTypes.*;
import com.project.mde.programming.*;
import java.util.*;
final class VariableDetectors {
    static List<Evidence> unused(EvaluationContext c) {
        var out=new ArrayList<Evidence>();
        for(var d:c.declarations) {
            boolean read=c.statements.stream().anyMatch(s->c.reads(s,d));
            int position=c.statements.indexOf(d);
            boolean assigned=c.statements.stream().anyMatch(s->s instanceof Assignment a && a.getTarget()==d && c.statements.indexOf(s)>position);
            if(!read && !assigned)out.add(c.evidence(d,"v"+c.fingerprints.ordinal(d)+": no reference or later assignment","reference or later assignment"));
        }
        return out;
    }
    static List<Evidence> redundant(EvaluationContext c) {
        var out=new ArrayList<Evidence>();
        for(var block:c.blocks()) {
            var pending=new IdentityHashMap<VariableDeclaration,Assignment>();
            for(var s:block) {
                pending.keySet().removeIf(d->c.reads(s,d));
                if(s instanceof Assignment a) {
                    var prior=pending.put(a.getTarget(),a);
                    if(prior!=null)out.add(c.evidence(prior,"overwritten at "+c.path(a),"read before next assignment"));
                } else if(!(s instanceof Move || s instanceof TurnLeft || s instanceof TurnRight))pending.clear();
            }
        }
        return out;
    }
    static List<Evidence> update(EvaluationContext c) {
        var p=c.config.parameters();int index=p.declarationOrdinal()-1;
        if(index<0 || index>=c.declarations.size())return List.of();
        var d=c.declarations.get(index);String id="v"+(index+1);
        var initial=c.first(d,"VARIABLE_DECLARED");
        var end=c.execution.finalState().variables().stream().filter(v->v.id().equals(id)).findFirst();
        var start=initial.flatMap(e->e.state().variables().stream().filter(v->v.id().equals(id)).findFirst());
        if(start.isPresent() && end.isPresent() && p.variableType().equals(end.get().type()) &&
           Objects.equals(start.get().value(),p.expectedInitialValue()) && Objects.equals(end.get().value(),p.expectedFinalValue()))return List.of();
        return List.of(new Evidence(c.path(d),initial.map(e->e.index()).orElse(null),
            id+": "+start.map(v->String.valueOf(v.value())).orElse("not executed")+" -> "+end.map(v->String.valueOf(v.value())).orElse("unavailable"),
            p.variableType()+" "+p.expectedInitialValue()+" -> "+p.expectedFinalValue()));
    }
}
