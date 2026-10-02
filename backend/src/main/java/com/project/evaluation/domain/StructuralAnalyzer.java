package com.project.evaluation.domain;

import com.project.evaluation.domain.EvaluationTypes.*;
import com.project.mde.programming.*;
import java.util.*;

public final class StructuralAnalyzer {
    public StructuralCorrectness analyze(EvaluationContext c) {
        var required=c.config.requiredConstructs();
        boolean present=c.config.requiredConcept()==Concept.LOOPS
            ? c.statements.stream().anyMatch(s->required.contains(s.eClass().getName()))
            : required.stream().allMatch(type->c.statements.stream().anyMatch(s->type.equals(s.eClass().getName())));
        boolean meaningful=switch(c.config.requiredConcept()) {
            case SEQUENCES -> !c.program.getStatements().isEmpty() && c.program.getStatements().stream().allMatch(s->s instanceof Move || s instanceof TurnLeft || s instanceof TurnRight);
            case VARIABLES -> variableUsed(c);
            case CONDITIONALS -> c.statements.stream().anyMatch(s->s instanceof If i && required.contains(s.eClass().getName()) &&
                (!c.config.parameters().requireDynamicCondition() || c.constants.value(i.getCondition()).isEmpty()) &&
                !c.events(s,"CONDITION_EVALUATED").isEmpty() && !i.getThenBranch().isEmpty() &&
                (!c.config.parameters().requireBothBranches() || i instanceof IfElse both && !both.getElseBranch().isEmpty()));
            case LOOPS -> c.statements.stream().anyMatch(s->(s instanceof Repeat || s instanceof While) &&
                c.events(s,"LOOP_ITERATION").stream().anyMatch(e->Integer.parseInt(e.detail())>=c.config.parameters().minimumLoopIterations()) &&
                c.execution.trace().stream().anyMatch(e->e.statementPath()!=null && e.statementPath().startsWith(c.path(s)+".body[") &&
                    Set.of("MOVE","TURN_LEFT","TURN_RIGHT","VARIABLE_ASSIGNED","VARIABLE_DECLARED").contains(e.type())));
        };
        var constraints=new LinkedHashMap<String,Boolean>();constraints.put("requiredConstructs",present);constraints.put("meaningfulUse",meaningful);
        return new StructuralCorrectness(c.config.requiredConcept(),meaningful,present,present&&meaningful,Collections.unmodifiableMap(constraints));
    }
    private boolean variableUsed(EvaluationContext c) {
        int index=c.config.parameters().declarationOrdinal()-1;if(index>=c.declarations.size())return false;
        var d=c.declarations.get(index);if(!d.getType().getName().equals(c.config.parameters().variableType()))return false;
        var initial=c.first(d,"VARIABLE_DECLARED");if(initial.isEmpty())return false;
        String id="v"+(index+1);var start=initial.get().state().variables().stream().filter(v->v.id().equals(id)).findFirst();
        return start.isPresent() && c.statements.stream().anyMatch(s->s instanceof Assignment a && a.getTarget()==d &&
            c.events(s,"VARIABLE_ASSIGNED").stream().anyMatch(e->e.state().variables().stream().anyMatch(v->v.id().equals(id)&&!Objects.equals(v.value(),start.get().value()))));
    }
}
