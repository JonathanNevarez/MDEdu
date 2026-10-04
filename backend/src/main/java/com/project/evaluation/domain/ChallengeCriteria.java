package com.project.evaluation.domain;

import com.project.execution.domain.GameTypes.Level;
import com.project.mde.programming.*;
import java.util.*;

/** Configuration-driven checks over the real EMF model and execution trace. */
public final class ChallengeCriteria {
    private ChallengeCriteria() {}
    public static Map<String,Boolean> check(Level level, EvaluationContext c) {
        var rules=level.criteria();
        if(rules==null)return Map.of();
        var checks=new LinkedHashMap<String,Boolean>();
        var groups=level.allowedBlockGroups();
        var objects=new ArrayList<org.eclipse.emf.ecore.EObject>();c.program.eAllContents().forEachRemaining(objects::add);
        checks.put("allowedBlocks",objects.stream().allMatch(o->groups.contains(
            o instanceof Move || o instanceof TurnLeft || o instanceof TurnRight ? "Movimiento" :
            o instanceof VariableDeclaration || o instanceof Assignment || o instanceof VariableReference ? "Variables" :
            o instanceof Repeat || o instanceof While ? "Ciclos" :
            o instanceof If ? "Condicionales" : o instanceof Literal ? "Valores" : "Condiciones")));
        var collected=c.execution.trace().stream().filter(e->e.type().equals("ITEM_COLLECTED")).map(e->e.state().playerPosition()).toList();
        checks.put("orderedItems",collected.equals(rules.orderedItems()));
        int cursor=-1;boolean milestones=true;
        for(var point:rules.variableCheckpoints()) {
            final int after=cursor;
            var event=c.execution.trace().stream().filter(e->e.index()>after &&
                Set.of("VARIABLE_ASSIGNED","VARIABLE_DECLARED").contains(e.type()) && e.detail().equals(point.variable()) &&
                e.state().playerPosition().equals(point.position()) && e.state().variables().stream().anyMatch(v->
                    v.name().equals(point.variable()) && Objects.equals(v.value(),point.value()))).findFirst();
            if(event.isEmpty()){milestones=false;break;}cursor=event.get().index();
            if(rules.readVariables().contains(point.variable())) {
                final int assignedAt=cursor;
                int overwrittenAt=c.execution.trace().stream().filter(e->e.index()>assignedAt && e.type().equals("VARIABLE_ASSIGNED") && e.detail().equals(point.variable()))
                    .mapToInt(e->e.index()).min().orElse(Integer.MAX_VALUE);
                boolean consumed=c.execution.trace().stream().anyMatch(e->e.index()>assignedAt && e.index()<overwrittenAt &&
                    e.type().equals("VARIABLE_READ") && e.detail().equals(point.variable()));
                if(!consumed){milestones=false;break;}
            }
        }
        checks.put("variableCheckpoints",milestones);
        checks.put("executedVariableReads",rules.readVariables().stream().allMatch(name->c.execution.trace().stream().anyMatch(e->
            e.type().equals("VARIABLE_READ") && e.detail().equals(name))));
        checks.put("meaningfulDecisions",c.statements.stream().filter(s->s instanceof If && !c.events(s,"CONDITION_EVALUATED").isEmpty() &&
            c.execution.trace().stream().anyMatch(e->e.statementPath()!=null && e.statementPath().startsWith(c.path(s)+".") &&
                Set.of("MOVE","TURN_LEFT","TURN_RIGHT").contains(e.type()))).count()>=rules.minimumDecisions());
        checks.put("integrationConstructs",rules.requiredStatements().stream().allMatch(type->c.statements.stream().anyMatch(s->s.eClass().getName().equals(type))));
        return checks;
    }
}
