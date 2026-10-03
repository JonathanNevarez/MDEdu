package com.project.llm;

import com.project.AdaptativaApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/** Test-classpath-only launcher for browser validation; never packaged in the application JAR. */
public final class GeminiMockApplication {
    private GeminiMockApplication() {}
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(()->{
            GeminiFeedbackIT.SERVER.stop(0);
            GeminiFeedbackIT.EXECUTOR.shutdownNow();
        }));
        new SpringApplicationBuilder(AdaptativaApplication.class,GeminiFeedbackIT.LocalProvider.class).run(args);
    }
}
