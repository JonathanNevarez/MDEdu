package com.project.evaluation.detectors;
import com.project.evaluation.domain.*;
import com.project.evaluation.domain.EvaluationTypes.*;
import com.project.mde.programming.*;
import java.util.*;
final class ConditionalDetectors {
    static List<Evidence> missing(EvaluationContext c) {
        boolean exists=c.statements.stream().anyMatch(s->c.config.requiredConstructs().contains(s.eClass().getName()));
        return exists?List.of():List.of(c.evidence(null,"required conditional absent",c.config.requiredConstructs().toString()));
    }
    static List<Evidence> identical(EvaluationContext c) {
        var out=new ArrayList<Evidence>();for(var s:c.statements)if(s instanceof IfElse i && c.fingerprints.branch(i.getThenBranch()).equals(c.fingerprints.branch(i.getElseBranch())))
            out.add(c.evidence(i,"thenBranch == elseBranch","different alternatives"));return out;
    }
    static List<Evidence> constant(EvaluationContext c) {
        var out=new ArrayList<Evidence>();for(var s:c.statements)if(s instanceof If i)
            c.constants.value(i.getCondition()).filter(Boolean.class::isInstance).ifPresent(v->out.add(c.evidence(i,"constant="+v,"dynamic condition")));
        return out;
    }
    static List<Evidence> branch(EvaluationContext c) {
        if(!c.config.parameters().requireBothBranches())return List.of();
        var out=new ArrayList<Evidence>();for(var s:c.statements)if(s instanceof If i &&
            (!(i instanceof IfElse both) || i.getThenBranch().isEmpty() || both.getElseBranch().isEmpty()))
            out.add(c.evidence(i,"missing or empty alternative","nonempty thenBranch and elseBranch"));
        return out;
    }
}
