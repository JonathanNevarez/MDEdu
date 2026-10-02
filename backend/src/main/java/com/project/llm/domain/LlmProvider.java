package com.project.llm.domain;
import com.project.llm.domain.LlmTypes.*;
public interface LlmProvider {
    ProviderResult generatePedagogicalFeedback(Prompt prompt);
    ProviderResult classifyUncoveredCase(Prompt prompt);
}
