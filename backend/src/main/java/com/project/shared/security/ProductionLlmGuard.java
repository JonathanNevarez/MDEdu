package com.project.shared.security;

import com.project.llm.provider.LlmSettings;
import com.project.llm.domain.LlmTypes.Provider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/** Test-only deterministic provider must never be selected in a production deployment. */
@Configuration(proxyBeanMethods = false)
@Profile("prod")
public class ProductionLlmGuard {
    public ProductionLlmGuard(LlmSettings settings) {
        if (settings.provider() == Provider.FAKE) {
            throw new IllegalStateException("FAKE provider is restricted to non-production validation.");
        }
    }
}
