package com.project.llm;
import com.project.llm.domain.*;
import com.project.llm.domain.LlmTypes.*;
import com.project.llm.provider.*;
import com.project.llm.application.*;
import com.project.adaptation.manager.AdaptationManager;
import com.project.student.application.*;
import com.project.student.api.LearningDtos.SubmitAttempt;
import com.project.programming.api.ProgramDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpServer;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;
import org.springframework.test.context.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers @SpringBootTest(properties={"spring.config.import=","LLM_PROVIDER=OPENAI","LLM_API_KEY=dummy-local-test-key","LLM_MODEL=configured-test-model","LLM_TIMEOUT_MS=200","LLM_MAX_RETRIES=1"})
@Import(OpenAIFeedbackIT.LocalProvider.class)
class OpenAIFeedbackIT {
    static final ObjectMapper JSON=new ObjectMapper();static final HttpServer SERVER=create();static volatile int status=200,delay=0;static volatile boolean invalid=false;
    static HttpServer create(){try {var s=HttpServer.create(new InetSocketAddress("127.0.0.1",0),0);s.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        s.createContext("/v1/responses",exchange->{try {
            var body=JSON.readTree(exchange.getRequestBody());String kind=body.at("/text/format/name").asText();
            var prompt=new Prompt(kind.equals("feedback")?Purpose.FEEDBACK_GENERATION:Purpose.UNKNOWN_CASE_CLASSIFICATION,1,body.path("instructions").asText(),body.at("/input/0/content").asText(),kind,"{}","hash","hash");
            var fake=new FakeLlmProvider();String output=invalid?"invalid":(kind.equals("feedback")?fake.generatePedagogicalFeedback(prompt):fake.classifyUncoveredCase(prompt)).output();
            String response=JSON.writeValueAsString(Map.of("status","completed","output",List.of(Map.of("type","message","content",List.of(Map.of("type","output_text","text",output))))));
            if(delay>0)Thread.sleep(delay);byte[] bytes=response.getBytes(java.nio.charset.StandardCharsets.UTF_8);exchange.sendResponseHeaders(status,bytes.length);exchange.getResponseBody().write(bytes);
        }catch(InterruptedException e){Thread.currentThread().interrupt();}catch(Exception ignored){}finally{exchange.close();}});s.start();return s;
    }catch(Exception e){throw new ExceptionInInitializerError(e);}}
    @TestConfiguration static class LocalProvider {
        @Bean @Primary LlmProvider localLlmProvider(LlmSettings settings){return new OpenAILlmProvider(settings,URI.create("http://127.0.0.1:"+SERVER.getAddress().getPort()+"/v1/responses"));}
    }
    @Container static final PostgreSQLContainer<?> DB=new PostgreSQLContainer<>("postgres:17-bookworm");
    @DynamicPropertySource static void db(DynamicPropertyRegistry r){r.add("spring.datasource.url",DB::getJdbcUrl);r.add("spring.datasource.username",DB::getUsername);r.add("spring.datasource.password",DB::getPassword);}
    @Autowired LearningService learning;@Autowired FeedbackOrchestrator feedback;@Autowired AdaptationManager adaptation;@Autowired StudentModelProjectionService projection;@Autowired ObjectMapper json;
    @AfterAll static void stop(){SERVER.stop(0);}
    @Test void realAdapterPreservesDecisionAndEveryProviderFailureFallsBack() throws Exception {
        ProgramDto program;try(var in=getClass().getResourceAsStream("/evaluation/SEQUENCES.json")){program=json.readValue(in,ProgramDto.class);}
        for(String mode:List.of("VALID","INVALID","429","503","401","TIMEOUT")) {
            status=mode.matches("[0-9]+")?Integer.parseInt(mode):200;delay=mode.equals("TIMEOUT")?500:0;invalid=mode.equals("INVALID");
            var id=learning.create(null).id();var attempt=learning.submit(new SubmitAttempt(id,"SEQUENCES",100L,0,program));
            String before=json.writeValueAsString(projection.dto(projection.project(id)));var result=feedback.generate(id,attempt.attemptId());
            assertEquals(mode.equals("VALID")?Source.OPENAI:Source.FALLBACK,result.source(),mode);assertEquals(mode.equals("VALID"),result.llmUsed());
            assertEquals(attempt.adaptation(),adaptation.byAttempt(attempt.attemptId()));assertEquals(before,json.writeValueAsString(projection.dto(projection.project(id))));
            assertEquals(result,feedback.generate(id,attempt.attemptId()));
        }
    }
}
