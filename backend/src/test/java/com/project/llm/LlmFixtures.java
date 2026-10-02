package com.project.llm;
import com.project.llm.domain.LlmTypes.*;
import com.project.adaptation.manager.DecisionTypes.*;
import com.project.adaptation.domain.RuleEngineResult.ConditionEvidence;
import com.project.llm.application.*;
import com.project.evaluation.catalog.PatternCatalog;
import com.project.execution.application.LevelCatalog;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import java.math.BigDecimal;
import java.time.Instant;
final class LlmFixtures {
    static final ObjectMapper JSON=new ObjectMapper().findAndRegisterModules();
    static LlmPolicy policy() throws Exception{return new LlmPolicy(JSON);}
    static PatternCatalog catalog() throws Exception{return new PatternCatalog(JSON,new LevelCatalog(JSON));}
    static DecisionDto decision(List<ActionDto> actions,List<ConditionEvidence> evidence) {
        var semantic=new SemanticDecision(1,1,1,List.of("rule"),"rule",List.of(),List.of(),actions,new Explanation("rule","reason",List.of(),List.of()),
            List.of(new RuleAudit("rule",1,1,1,"ERROR",0,"SELECTED","SELECTED",evidence)),List.of(),"context","rules","params",false,"NONE");
        return DecisionDto.of(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID(),Instant.now(),semantic,"fingerprint");
    }
    static AttemptEvidence evidence(String concept,List<String> patterns) {return new AttemptEvidence(concept,"Practica el concepto para alcanzar el objetivo.",new BigDecimal("0.10"),3,0,false,false,true,patterns,new ExecutionSummary(true,4,"COMPLETED",Map.of("Move",4),List.of(),0));}
    static PedagogicalLlmContext context() throws Exception {
        return new ContextSanitizer(catalog(),policy()).sanitize(evidence("LOOPS",List.of("REPETITIVE_SEQUENCE_WITHOUT_LOOP")),decision(List.of(),List.of()),HintStage.CONCEPTUAL_HINT,List.of(),List.of());
    }
}
