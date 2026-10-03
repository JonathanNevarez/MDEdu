package com.project.llm.provider;
import com.project.llm.domain.LlmProvider;
import org.springframework.context.annotation.*;
@Configuration
public class LlmProviderConfiguration {
    @Bean public LlmProvider llmProvider(LlmSettings settings,LlmRateLimiter quota) {
        if(settings.provider()==com.project.llm.domain.LlmTypes.Provider.OPENAI && !settings.available())
            org.slf4j.LoggerFactory.getLogger(getClass()).warn("OpenAI feedback unavailable: configuration incomplete; deterministic fallback enabled.");
        return switch(settings.provider()){case FAKE->new FakeLlmProvider();case OPENAI->new OpenAILlmProvider(settings,quota);case GEMINI->new GeminiLlmProvider(settings,quota);case DISABLED->new DisabledLlmProvider();};
    }
}
