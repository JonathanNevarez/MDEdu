package com.project.llm;
import com.project.llm.provider.*;
import com.project.llm.domain.LlmTypes.*;
import org.springframework.mock.env.MockEnvironment;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class GeminiSettingsTest {
 @Test void fourProvidersAndUnknownSelectSafely(){
  var factory=new LlmProviderConfiguration();
  var classes=java.util.Map.of("GEMINI",GeminiLlmProvider.class,"OPENAI",OpenAILlmProvider.class,"FAKE",FakeLlmProvider.class,"DISABLED",DisabledLlmProvider.class,"unknown",DisabledLlmProvider.class);
  classes.forEach((name,type)->assertInstanceOf(type,factory.llmProvider(new LlmSettings(new MockEnvironment().withProperty("LLM_PROVIDER",name)))));
 }
 @Test void geminiCredentialsAreIndependentAndSafe(){
  var env=new MockEnvironment().withProperty("LLM_PROVIDER","GEMINI").withProperty("LLM_MODEL","openai-model").withProperty("LLM_API_KEY","openai-dummy");
  assertFalse(new LlmSettings(env).available());
  env.withProperty("GEMINI_MODEL","configured-gemini-model").withProperty("GEMINI_API_KEY","gemini-test-secret-never-log");
  var settings=new LlmSettings(env);assertTrue(settings.available());assertEquals("configured-gemini-model",settings.model());assertEquals(Source.GEMINI,settings.successSource());
  assertFalse(settings.toString().contains("gemini-test-secret-never-log"));assertFalse(settings.configurationKey().contains("gemini-test-secret-never-log"));
  env.withProperty("LLM_PROVIDER","OPENAI");var openai=new LlmSettings(env);assertEquals("openai-model",openai.model());assertEquals(Source.OPENAI,openai.successSource());assertNotEquals(settings.configurationKey(),openai.configurationKey());
 }
}
