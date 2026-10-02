package com.project.llm.prompt;
import com.project.llm.domain.LlmTypes.*;
import org.springframework.stereotype.Component;
@Component
public class PedagogicalPromptBuilder {
    private final PromptSupport support;
    public PedagogicalPromptBuilder(PromptSupport support){this.support=support;}
    public Prompt build(PedagogicalLlmContext context){return support.build(Purpose.FEEDBACK_GENERATION,context);}
}
