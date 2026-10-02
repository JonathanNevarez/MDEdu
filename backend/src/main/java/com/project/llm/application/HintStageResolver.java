package com.project.llm.application;
import com.project.llm.domain.LlmTypes.HintStage;
import com.project.adaptation.manager.DecisionTypes.ActionDto;
import java.util.List;
import org.springframework.stereotype.Component;
@Component
public class HintStageResolver {
    public HintStage resolve(int hintCount,List<ActionDto> actions) {
        int stage=Math.min(3,Math.max(0,hintCount));
        for(var a:actions) if(a.hintLevel()!=null) stage=Math.max(stage,switch(a.hintLevel()) {
            case CONCEPTUAL -> 1; case GUIDED -> 2; case DIRECT -> 3;
        });
        return HintStage.values()[stage];
    }
}
