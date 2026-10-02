package com.project.llm.provider;
import com.project.llm.domain.*;
import com.project.llm.domain.LlmTypes.*;
public class DisabledLlmProvider implements LlmProvider {
    public ProviderResult generatePedagogicalFeedback(Prompt p){return ProviderResult.failure(Status.DISABLED,0);}
    public ProviderResult classifyUncoveredCase(Prompt p){return ProviderResult.failure(Status.DISABLED,0);}
}
