package com.project.llm.provider;
import com.project.llm.domain.*;
import com.project.llm.domain.LlmTypes.*;
import com.project.llm.fallback.PedagogicalFallbackService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import java.math.BigDecimal;

/** Deterministic offline provider; fault modes are constructor-only test fixtures. */
public class FakeLlmProvider implements LlmProvider {
    public enum Mode { VALID, INVALID_JSON, UNKNOWN_TAG, LOW_CONFIDENCE, TIMEOUT, PROVIDER_ERROR, REFUSAL }
    private final Mode mode;private final ObjectMapper json=new ObjectMapper();
    public FakeLlmProvider(){this(Mode.VALID);}
    public FakeLlmProvider(Mode mode){this.mode=mode;}
    public ProviderResult generatePedagogicalFeedback(Prompt p){return respond(p,false);}
    public ProviderResult classifyUncoveredCase(Prompt p){return respond(p,true);}
    private ProviderResult respond(Prompt p,boolean classification) {
        if(mode==Mode.TIMEOUT)return ProviderResult.failure(Status.TIMEOUT,1);
        if(mode==Mode.PROVIDER_ERROR)return ProviderResult.failure(Status.PROVIDER_ERROR,1);
        if(mode==Mode.REFUSAL)return ProviderResult.failure(Status.REFUSED,1);
        if(mode==Mode.INVALID_JSON)return new ProviderResult(Status.SUCCESS,"invalid-json",1,null);
        try {
            String data=p.data().substring(p.data().indexOf('>')+1,p.data().lastIndexOf('<')).trim();
            var c=json.readValue(data,PedagogicalLlmContext.class);Object output;
            if(classification) {
                String tag=mode==Mode.UNKNOWN_TAG?"I_INVENTED_THIS":switch(c.concept()) {case "LOOPS"->"REPETITION_LOGIC_ISSUE";case "VARIABLES"->"STATE_UPDATE_ISSUE";case "CONDITIONALS"->"CONDITION_LOGIC_ISSUE";default->"LOGIC_FLOW_ISSUE";};
                output=new Classification(true,List.of(tag),"Podría ser útil revisar la estructura asociada al concepto.",new BigDecimal(mode==Mode.LOW_CONFIDENCE?"0.40":"0.82"));
            } else {
                var f=new PedagogicalFallbackService().generate(c);
                output=new Feedback(f.message()+(c.complementaryTags().isEmpty()?"":" Como orientación complementaria, revisa la estructura que expresa tu intención."),f.question(),f.focus(),f.hintStage(),f.language());
            }
            return new ProviderResult(Status.SUCCESS,json.writeValueAsString(output),1,"fake-local");
        }catch(Exception e){return ProviderResult.failure(Status.INVALID_RESPONSE,1);}
    }
}
