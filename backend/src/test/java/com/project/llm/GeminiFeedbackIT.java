package com.project.llm;

import com.fasterxml.jackson.databind.*;
import com.project.llm.domain.*;
import com.project.llm.domain.LlmTypes.*;
import com.project.llm.provider.*;
import com.project.llm.application.*;
import com.project.adaptation.manager.AdaptationManager;
import com.project.student.application.*;
import com.project.student.api.LearningDtos.*;
import com.project.programming.api.ProgramDto;
import com.project.ui.application.UiConfigurationService;
import com.sun.net.httpserver.HttpServer;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;
import org.springframework.test.context.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@SpringBootTest(properties={"spring.config.import=","LLM_PROVIDER=GEMINI","GEMINI_API_KEY=gemini-test-secret-never-log","GEMINI_MODEL=configured-test-model","LLM_TIMEOUT_MS=1000","LLM_MAX_RETRIES=1"})
@Import(GeminiFeedbackIT.LocalProvider.class)
class GeminiFeedbackIT {
    static final ObjectMapper JSON=new ObjectMapper();
    static final AtomicInteger classifierCalls=new AtomicInteger(),feedbackCalls=new AtomicInteger();
    static final List<String> bodies=new CopyOnWriteArrayList<>();
    static volatile String mode="VALID";
    static final ExecutorService EXECUTOR=Executors.newVirtualThreadPerTaskExecutor();
    static final HttpServer SERVER=create();
    static HttpServer create(){try {
        var s=HttpServer.create(new InetSocketAddress("127.0.0.1",0),0);s.setExecutor(EXECUTOR);
        s.createContext("/v1/interactions",exchange->{try {
            var body=JSON.readTree(exchange.getRequestBody());bodies.add(body.toString());
            boolean classification=body.at("/response_format/schema/properties/recognized").isObject();
            (classification?classifierCalls:feedbackCalls).incrementAndGet();
            var prompt=new Prompt(classification?Purpose.UNKNOWN_CASE_CLASSIFICATION:Purpose.FEEDBACK_GENERATION,1,
                body.path("system_instruction").asText(),body.path("input").asText(),classification?"classification":"feedback","{}","hash","hash");
            String scenario=mode;
            var fake=new FakeLlmProvider(scenario.equals("LOW_CONFIDENCE")?FakeLlmProvider.Mode.LOW_CONFIDENCE:FakeLlmProvider.Mode.VALID);
            String output=(classification?fake.classifyUncoveredCase(prompt):fake.generatePedagogicalFeedback(prompt)).output();
            if(scenario.equals("UNKNOWN_TAG"))output=output.replace("LOGIC_FLOW_ISSUE","MADE_UP_TAG");
            if(scenario.equals("INVALID"))output="invalid-json";
            if(scenario.equals("STAGE"))output=output.replace("SOCRATIC_QUESTION","PARTIAL_HELP");
            if(scenario.equals("EMPTY"))output="";
            if(scenario.equals("TIMEOUT"))Thread.sleep(1500);
            int status=scenario.matches("[0-9]+")?Integer.parseInt(scenario):200;
            String response=status==200?JSON.writeValueAsString(Map.of("id","local_interaction_123","status","completed","steps",List.of(Map.of("type","model_output","content",List.of(Map.of("type","text","text",output)))))):"gemini-test-secret-never-log";
            byte[] bytes=response.getBytes(java.nio.charset.StandardCharsets.UTF_8);exchange.sendResponseHeaders(status,bytes.length);exchange.getResponseBody().write(bytes);
        }catch(InterruptedException e){Thread.currentThread().interrupt();}catch(java.io.IOException ignored){}finally{exchange.close();}});
        s.start();return s;
    }catch(Exception e){throw new ExceptionInInitializerError(e);}}
    @TestConfiguration static class LocalProvider {
        @Bean @Primary LlmProvider localLlmProvider(LlmSettings settings,LlmRateLimiter quota){return new GeminiLlmProvider(settings,URI.create("http://127.0.0.1:"+SERVER.getAddress().getPort()+"/v1/interactions"),quota);}
    }
    @Container static final PostgreSQLContainer<?> DB=new PostgreSQLContainer<>("postgres:17-bookworm");
    @DynamicPropertySource static void db(DynamicPropertyRegistry r){r.add("spring.datasource.url",DB::getJdbcUrl);r.add("spring.datasource.username",DB::getUsername);r.add("spring.datasource.password",DB::getPassword);}
    @Autowired LearningService learning;
    @Autowired FeedbackOrchestrator feedback;
    @Autowired AdaptationManager adaptation;
    @Autowired StudentModelProjectionService projection;
    @Autowired UiConfigurationService ui;
    @Autowired JdbcTemplate jdbc;
    @Autowired ObjectMapper json;
    @Autowired com.project.telemetry.application.AttemptTimelineService timeline;
    @Autowired org.springframework.context.ApplicationContext beans;
    @BeforeEach void reset(){mode="VALID";classifierCalls.set(0);feedbackCalls.set(0);bodies.clear();}
    @AfterAll static void stop(){SERVER.stop(0);EXECUTOR.shutdownNow();}
    AttemptResponse submit(UUID id,String level,String fixture)throws Exception {
        try(var in=getClass().getResourceAsStream("/challenges/"+fixture+".json")){
            return learning.submit(new SubmitAttempt(id,level,100L,0,json.readValue(in,ProgramDto.class)));
        }
    }
    void unknown(UUID attempt)throws Exception {
        var node=(com.fasterxml.jackson.databind.node.ObjectNode)json.readTree(jdbc.queryForObject("select evidence_json from feedback_attempt_inputs where attempt_id=?",String.class,attempt));
        node.put("activityPassed",false);node.put("analyzable",true);node.putArray("detectedPatterns");
        jdbc.update("update feedback_attempt_inputs set evidence_json=? where attempt_id=?",node.toString(),attempt);
    }
    String studentState(UUID id)throws Exception{return json.writeValueAsString(projection.dto(projection.project(id)));}
    @Test void internalQuotaStopsActualHttpAndUsesPedagogicalFallback()throws Exception {
        var student=learning.create(null).id();var first=submit(student,"SEQ-01","SEQ-01");var second=submit(student,"SEQ-01","SEQ-01");
        var limited=new LlmRateLimiter(1,60,1,10,java.time.Clock.systemUTC());
        var fixture=new java.util.Properties();
        try(var input=getClass().getResourceAsStream("/teacher-test.properties")){fixture.load(input);}
        var settings=new LlmSettings(Provider.GEMINI,fixture.getProperty("GEMINI_MODEL"),fixture.getProperty("GEMINI_API_KEY"),1000,1);
        var local=orchestrator(settings,new GeminiLlmProvider(settings,URI.create("http://127.0.0.1:"+SERVER.getAddress().getPort()+"/v1/interactions"),limited));
        assertEquals(Source.GEMINI,local.generate(student,first.attemptId()).source());
        int before=feedbackCalls.get();var fallback=local.generate(student,second.attemptId());
        assertEquals(Source.FALLBACK,fallback.source());assertEquals("RATE_LIMITED",fallback.fallbackReason());assertFalse(fallback.llmUsed());assertEquals(before,feedbackCalls.get());assertEquals(1,before);
    }
    @Test void knownLoopsPrivacyPersistenceIdempotencyAndUiSemantics()throws Exception {
        var id=learning.create("Private Gemini Student private.gemini@example.com").id();
        for(String level:List.of("SEQ-01","VAR-01","COND-01"))submit(id,level,level);
        var attempt=submit(id,"LOOP-01","LOOP-01_MANUAL");String before=studentState(id);
        var result=feedback.generate(id,attempt.attemptId());
        assertEquals(Source.GEMINI,result.source());assertEquals(Provider.GEMINI,result.provider());assertTrue(result.llmUsed());
        assertEquals("configured-test-model",result.model());assertEquals("REPETITIVE_SEQUENCE_WITHOUT_LOOP",result.focus());
        assertEquals(HintStage.CONCEPTUAL_HINT,result.hintStage());assertEquals(0,classifierCalls.get());assertEquals(1,feedbackCalls.get());
        assertEquals(result,feedback.generate(id,attempt.attemptId()));assertEquals(1,feedbackCalls.get());
        assertEquals(before,studentState(id));assertEquals(attempt.adaptation(),adaptation.byAttempt(attempt.attemptId()));
        var uiAfter=ui.byAttempt(id,attempt.attemptId());assertFalse(uiAfter.safeDefault());assertEquals(result.message(),uiAfter.feedback().message());
        // Separate provider fixture, same sanitized semantic response. Production factory remains unchanged.
        var other=orchestrator(new LlmSettings(Provider.OPENAI,"configured-test-model","dummy-local-only",1000,1),new FakeLlmProvider());
        var openai=other.generate(id,attempt.attemptId());
        assertEquals(Source.OPENAI,openai.source());assertNotEquals(result.feedbackId(),openai.feedbackId());
        assertEquals(result.message(),openai.message());assertEquals(2,jdbc.queryForObject("select count(*) from feedback_records where attempt_id=?",Integer.class,attempt.attemptId()));
        assertEquals(uiAfter.configuration(),ui.byAttempt(id,attempt.attemptId()).configuration());
        assertEquals(result,feedback.generate(id,attempt.attemptId()));
        String captured=String.join("",bodies);for(String forbidden:List.of("Private Gemini Student","private.gemini@example.com",id.toString(),"gemini-test-secret-never-log"))assertFalse(captured.contains(forbidden));
        assertTrue(captured.contains("REPETITIVE_SEQUENCE_WITHOUT_LOOP"));
        var trace=timeline.attempt(id,attempt.attemptId());
        assertEquals(com.project.telemetry.domain.TelemetryTypes.TraceStatus.COMPLETE,trace.traceStatus());
        assertTrue(trace.events().stream().anyMatch(e->e.payload() instanceof com.project.telemetry.domain.TelemetryTypes.FeedbackPayload p && p.provider().equals("GEMINI") && p.llmUsed()));
        assertFalse(json.writeValueAsString(trace).contains("gemini-test-secret-never-log"));
        String row=jdbc.queryForObject("select response_json from feedback_records where id=?",String.class,result.feedbackId());
        assertFalse(row.contains("gemini-test-secret-never-log"));assertFalse(row.contains("Private Gemini"));
        assertEquals("GEMINI",jdbc.queryForObject("select source from feedback_records where id=?",String.class,result.feedbackId()));
    }
    @Test void unknownUsesClassifierThenFeedbackWithoutChangingLearning()throws Exception {
        var id=learning.create(null).id();var attempt=submit(id,"SEQ-01","SEQ-01");unknown(attempt.attemptId());String before=studentState(id);
        var result=feedback.generate(id,attempt.attemptId());assertEquals(Source.GEMINI,result.source());assertEquals(List.of("LOGIC_FLOW_ISSUE"),result.classification().errorTags());
        assertEquals(1,classifierCalls.get());assertEquals(1,feedbackCalls.get());assertTrue(bodies.get(1).contains("LOGIC_FLOW_ISSUE"));
        assertEquals(before,studentState(id));assertEquals(attempt.adaptation(),adaptation.byAttempt(attempt.attemptId()));
    }
    @ParameterizedTest @ValueSource(strings={"LOW_CONFIDENCE","UNKNOWN_TAG"})
    void classificationRejectedUsesFallback(String scenario)throws Exception {
        mode=scenario;var id=learning.create(null).id();var attempt=submit(id,"SEQ-01","SEQ-01");unknown(attempt.attemptId());
        var result=feedback.generate(id,attempt.attemptId());assertEquals(Source.FALLBACK,result.source());assertEquals(scenario,result.fallbackReason());assertFalse(result.llmUsed());assertNull(result.classification());
        assertEquals(1,classifierCalls.get());assertEquals(0,feedbackCalls.get());
    }
    @ParameterizedTest @ValueSource(strings={"INVALID","EMPTY","STAGE","400","401","403","429","500","503","TIMEOUT"})
    void failuresPreserveLearningAndFallBack(String scenario)throws Exception {
        mode=scenario;var id=learning.create(null).id();var attempt=submit(id,"SEQ-01","SEQ-01");String before=studentState(id);
        var result=feedback.generate(id,attempt.attemptId());assertEquals(Source.FALLBACK,result.source());assertEquals(Provider.GEMINI,result.provider());assertFalse(result.llmUsed());assertNotNull(result.fallbackReason());
        assertEquals(before,studentState(id));assertEquals(attempt.adaptation(),adaptation.byAttempt(attempt.attemptId()));
        assertEquals(Set.of("429","500","503","TIMEOUT").contains(scenario)?2:1,feedbackCalls.get());
        assertEquals(result,feedback.generate(id,attempt.attemptId()));
    }
    FeedbackOrchestrator orchestrator(LlmSettings settings,LlmProvider provider)throws Exception {
        var ctor=FeedbackOrchestrator.class.getConstructors()[0];
        Object[] args=Arrays.stream(ctor.getParameterTypes()).map(type->type==LlmSettings.class?settings:type==LlmProvider.class?provider:beans.getBean(type)).toArray();
        return (FeedbackOrchestrator)ctor.newInstance(args);
    }
    @Test void connectionFailureFallsBackWithoutMutatingDecision()throws Exception {
        int port;try(var socket=new java.net.ServerSocket(0,0,java.net.InetAddress.getLoopbackAddress())){port=socket.getLocalPort();}
        var settings=new LlmSettings(Provider.GEMINI,"configured-test-model","gemini-test-secret-never-log",200,1);
        var adapter=new GeminiLlmProvider(settings,URI.create("http://127.0.0.1:"+port+"/v1/interactions"));
        var id=learning.create(null).id();var attempt=submit(id,"SEQ-01","SEQ-01");String before=studentState(id);
        var result=orchestrator(settings,adapter).generate(id,attempt.attemptId());
        assertEquals(Source.FALLBACK,result.source());assertEquals("PROVIDER_ERROR",result.fallbackReason());assertFalse(result.llmUsed());
        assertEquals(before,studentState(id));assertEquals(attempt.adaptation(),adaptation.byAttempt(attempt.attemptId()));
    }
    @Test void concurrentRequestsPersistOnce()throws Exception {
        var id=learning.create(null).id();var attempt=submit(id,"SEQ-01","SEQ-01");
        var ready=new CountDownLatch(2);var go=new CountDownLatch(1);
        try(var pool=Executors.newFixedThreadPool(2)){
            Callable<FeedbackDto> task=()->{ready.countDown();go.await();return feedback.generate(id,attempt.attemptId());};
            var a=pool.submit(task);var b=pool.submit(task);assertTrue(ready.await(5,TimeUnit.SECONDS));go.countDown();assertEquals(a.get(15,TimeUnit.SECONDS),b.get(15,TimeUnit.SECONDS));
        }
        assertEquals(1,feedbackCalls.get());assertEquals(1,jdbc.queryForObject("select count(*) from feedback_records where attempt_id=?",Integer.class,attempt.attemptId()));
    }
}
