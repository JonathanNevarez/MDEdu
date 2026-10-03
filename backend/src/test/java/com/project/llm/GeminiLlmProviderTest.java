package com.project.llm;
import com.project.llm.domain.LlmTypes.*;
import com.project.llm.provider.*;
import com.project.llm.prompt.*;
import com.project.llm.validation.*;
import com.sun.net.httpserver.HttpServer;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import static com.project.llm.LlmFixtures.*;

@org.junit.jupiter.api.extension.ExtendWith(org.springframework.boot.test.system.OutputCaptureExtension.class)
class GeminiLlmProviderTest {
    private HttpServer server;private ExecutorService pool;private URI endpoint;
    private final AtomicInteger requests=new AtomicInteger();private final AtomicReference<com.fasterxml.jackson.databind.JsonNode> request=new AtomicReference<>();
    private final AtomicBoolean auth=new AtomicBoolean();private String response;private int code=200,delay=0;
    @BeforeEach void start() throws Exception {
        server=HttpServer.create(new InetSocketAddress("127.0.0.1",0),0);pool=Executors.newCachedThreadPool();server.setExecutor(pool);
        server.createContext("/v1/interactions",exchange->{
            requests.incrementAndGet();auth.set("gemini-test-secret-never-log".equals(exchange.getRequestHeaders().getFirst("x-goog-api-key")));
            request.set(JSON.readTree(exchange.getRequestBody()));
            try {if(delay>0)Thread.sleep(delay);byte[] bytes=response.getBytes(StandardCharsets.UTF_8);exchange.getResponseHeaders().add("x-request-id","req_local_123");exchange.sendResponseHeaders(code,bytes.length);exchange.getResponseBody().write(bytes);}
            catch(InterruptedException e){Thread.currentThread().interrupt();}catch(java.io.IOException ignored){}finally{exchange.close();}
        });server.start();endpoint=URI.create("http://127.0.0.1:"+server.getAddress().getPort()+"/v1/interactions");
    }
    @AfterEach void stop(){server.stop(0);pool.shutdownNow();}
    GeminiLlmProvider provider(int timeout,int retries){return new GeminiLlmProvider(new LlmSettings(Provider.GEMINI,"configured-test-model","gemini-test-secret-never-log",timeout,retries),endpoint);}
    Prompt feedback() throws Exception{return new PedagogicalPromptBuilder(new PromptSupport(policy())).build(context());}
    String envelope(String text) throws Exception{return JSON.writeValueAsString(Map.of("id","req_local_123","status","completed","steps",List.of(Map.of("type","model_output","content",List.of(Map.of("type","text","text",text))))));}
    @Test void actualHttpFeedbackRequestAndStructuredOutput() throws Exception {
        response=envelope(new FakeLlmProvider().generatePedagogicalFeedback(feedback()).output());
        var result=provider(2000,0).generatePedagogicalFeedback(feedback());
        assertEquals(Status.SUCCESS,result.status());assertEquals("req_local_123",result.requestId());assertTrue(auth.get());
        var body=request.get();assertEquals("configured-test-model",body.path("model").asText());assertFalse(body.path("store").asBoolean());assertFalse(body.has("tools"));
        assertEquals("text",body.at("/response_format/type").asText());assertEquals("application/json",body.at("/response_format/mime_type").asText());assertEquals(JSON.readTree(feedback().schemaJson()),body.at("/response_format/schema"));
        assertEquals(feedback().instructions(),body.path("system_instruction").asText());assertEquals(feedback().data(),body.path("input").asText());assertFalse(body.has("previous_interaction_id"));assertFalse(body.has("agent"));assertFalse(body.toString().contains("gemini-test-secret-never-log"));assertFalse(body.toString().contains("studentId"));
        assertEquals(HintStage.CONCEPTUAL_HINT,new PedagogicalFeedbackValidator(policy()).validate(result.output(),context()).hintStage());
    }
    @Test void actualHttpClassificationSchemaAndValidation() throws Exception {
        var prompt=new UnknownCasePromptBuilder(new PromptSupport(policy())).build(context());response=envelope(new FakeLlmProvider().classifyUncoveredCase(prompt).output());
        var result=provider(2000,0).classifyUncoveredCase(prompt);assertEquals(Status.SUCCESS,result.status());assertTrue(auth.get());
        assertEquals(JSON.readTree(prompt.schemaJson()),request.get().at("/response_format/schema"));
        assertTrue(request.get().at("/response_format/schema/properties/errorTags/items/enum").toString().contains("REPETITION_LOGIC_ISSUE"));
        assertTrue(new ClosedVocabularyValidator(new PedagogicalFeedbackValidator(policy()),policy()).validate(result.output(),"LOOPS").recognized());
    }
    @ParameterizedTest @ValueSource(ints={429,500,503}) void boundedRetryTransientErrors(int status) throws Exception {
        code=status;response="{\"error\":\"gemini-test-secret-never-log\"}";var result=provider(1000,2).generatePedagogicalFeedback(feedback());
        assertEquals(status==429?Status.RATE_LIMITED:Status.PROVIDER_ERROR,result.status());assertEquals(3,result.attempts());assertEquals(3,requests.get());assertNull(result.output());assertFalse(result.toString().contains("gemini-test-secret-never-log"));
    }
    @ParameterizedTest @ValueSource(ints={400,401,403}) void noRetryForPermanentErrors(int status) throws Exception {
        code=status;response="gemini-test-secret-never-log";var result=provider(1000,2).generatePedagogicalFeedback(feedback());assertEquals(Status.PROVIDER_ERROR,result.status());assertEquals(1,requests.get());assertNull(result.output());
    }
    @Test void timeoutIncludesBodyAndIsBounded() throws Exception {
        response=envelope("{}");delay=400;long start=System.nanoTime();var result=provider(100,1).generatePedagogicalFeedback(feedback());
        assertEquals(Status.TIMEOUT,result.status());assertEquals(2,result.attempts());assertTrue(System.nanoTime()-start<TimeUnit.SECONDS.toNanos(3));
    }
    @Test void emptyIncompleteInvalidResponses() throws Exception {
        for(String body:List.of("{}","not-json","{\"status\":\"incomplete\"}","{\"status\":\"completed\",\"output\":[]}")) {
            response=body;assertEquals(Status.INVALID_RESPONSE,provider(1000,0).generatePedagogicalFeedback(feedback()).status());
        }
        // v1 documents no dedicated refusal field: noncompleted/empty output fails closed.
        for(String text:List.of("", " ", "x".repeat(16001))) {
            response=envelope(text);assertEquals(Status.INVALID_RESPONSE,provider(1000,0).generatePedagogicalFeedback(feedback()).status());
        }
    }
    @Test void invalidInnerJsonRejectedByServerValidator() throws Exception {
        response=envelope("broken");var result=provider(1000,0).generatePedagogicalFeedback(feedback());assertEquals(Status.SUCCESS,result.status());
        assertThrows(IllegalArgumentException.class,()->new PedagogicalFeedbackValidator(policy()).validate(result.output(),context()));
    }
    @Test void noKeyNoCallAndNoSecretInSettings() throws Exception {
        var settings=new LlmSettings(Provider.GEMINI,"configured-test-model","",100,0);
        assertEquals(Status.DISABLED,new GeminiLlmProvider(settings,endpoint).generatePedagogicalFeedback(feedback()).status());assertEquals(0,requests.get());
        assertEquals(Status.DISABLED,new GeminiLlmProvider(new LlmSettings(Provider.GEMINI,"","gemini-test-secret-never-log",100,0),endpoint).generatePedagogicalFeedback(feedback()).status());assertEquals(0,requests.get());
        assertFalse(new LlmSettings(Provider.GEMINI,"model","gemini-test-secret-never-log",100,0).toString().contains("gemini-test-secret-never-log"));
    }
    @Test void errorBodiesNeverLeakToLogs(org.springframework.boot.test.system.CapturedOutput logs) throws Exception {
        code=401;response="gemini-test-secret-never-log";
        var result=provider(1000,0).generatePedagogicalFeedback(feedback());
        assertNull(result.output());assertFalse(result.toString().contains(response));assertFalse(logs.getAll().contains(response));
    }
    @Test void connectionErrorAndOversizeBodyAreControlled() throws Exception {
        response="x".repeat(70000);assertNotEquals(Status.SUCCESS,provider(1000,0).generatePedagogicalFeedback(feedback()).status());
        server.stop(0);assertEquals(Status.PROVIDER_ERROR,provider(300,0).generatePedagogicalFeedback(feedback()).status());
    }
}
