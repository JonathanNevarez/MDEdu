package com.project.llm.prompt;
import com.project.llm.domain.LlmTypes.*;
import org.springframework.stereotype.Component;
@Component
public class UnknownCasePromptBuilder {
    private final PromptSupport support;
    public UnknownCasePromptBuilder(PromptSupport support){this.support=support;}
    public Prompt build(PedagogicalLlmContext context){return support.build(Purpose.UNKNOWN_CASE_CLASSIFICATION,context);}
}
