package com.project.llm.provider;
import com.project.llm.domain.*;
import com.project.llm.domain.LlmTypes.*;
import com.fasterxml.jackson.databind.*;
import java.net.*;
import java.net.http.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.Flow;

/** Stateless Gemini Interactions v1 adapter. Production endpoint is fixed; loopback constructor supports real HTTP tests. */
public class GeminiLlmProvider implements LlmProvider {
    private final LlmSettings config;private final URI endpoint;private final ObjectMapper json=new ObjectMapper();
    private final HttpClient http;
    public GeminiLlmProvider(LlmSettings config){this(config,URI.create("https://generativelanguage.googleapis.com/v1/interactions"));}
    public GeminiLlmProvider(LlmSettings config,URI endpoint) {
        if(!endpoint.toString().equals("https://generativelanguage.googleapis.com/v1/interactions") && !("http".equals(endpoint.getScheme()) && Set.of("127.0.0.1","localhost","[::1]").contains(endpoint.getHost())))throw new IllegalArgumentException("Unsupported provider endpoint");
        this.config=config;this.endpoint=endpoint;http=HttpClient.newBuilder().connectTimeout(Duration.ofMillis(config.timeoutMs())).followRedirects(HttpClient.Redirect.NEVER).build();
    }
    public ProviderResult generatePedagogicalFeedback(Prompt p){return request(p);}
    public ProviderResult classifyUncoveredCase(Prompt p){return request(p);}
    private ProviderResult request(Prompt p) {
        if(!config.available())return ProviderResult.failure(Status.DISABLED,0);
        final String body;
        try {body=json.writeValueAsString(new InteractionRequest(config.model(),false,p.instructions(),p.data(),
            new ResponseFormat("text","application/json",json.readTree(p.schemaJson())),new GenerationConfig(1000)));}
        catch(Exception e){return ProviderResult.failure(Status.INVALID_RESPONSE,0);}
        for(int n=1;n<=config.maxRetries()+1;n++) {
            Status failure;boolean retry;
            CompletableFuture<HttpResponse<String>> pending=null;
            try {
                var request=HttpRequest.newBuilder(endpoint).timeout(Duration.ofMillis(config.timeoutMs()))
                    .header("x-goog-api-key",config.key()).header("Content-Type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body,StandardCharsets.UTF_8)).build();
                pending=http.sendAsync(request,info->new LimitedBody());
                var response=pending.get(config.timeoutMs(),TimeUnit.MILLISECONDS);
                int code=response.statusCode();
                if(code>=200 && code<300)return parse(response.body(),n);
                failure=code==429?Status.RATE_LIMITED:Status.PROVIDER_ERROR;retry=code==429 || code>=500;
            }catch(TimeoutException e){if(pending!=null)pending.cancel(true);failure=Status.TIMEOUT;retry=true;}
            catch(InterruptedException e){if(pending!=null)pending.cancel(true);Thread.currentThread().interrupt();return ProviderResult.failure(Status.PROVIDER_ERROR,n);}
            catch(ExecutionException e){failure=e.getCause() instanceof HttpTimeoutException?Status.TIMEOUT:Status.PROVIDER_ERROR;retry=e.getCause() instanceof java.io.IOException;}
            catch(RuntimeException e){return ProviderResult.failure(Status.PROVIDER_ERROR,n);}
            if(!retry || n>config.maxRetries())return ProviderResult.failure(failure,n);
            try {Thread.sleep(50L*n);}catch(InterruptedException e){Thread.currentThread().interrupt();return ProviderResult.failure(Status.PROVIDER_ERROR,n);}
        }
        return ProviderResult.failure(Status.PROVIDER_ERROR,config.maxRetries()+1);
    }
    private record InteractionRequest(String model,boolean store,String system_instruction,String input,
        ResponseFormat response_format,GenerationConfig generation_config) {}
    private record ResponseFormat(String type,String mime_type,JsonNode schema) {}
    private record GenerationConfig(int max_output_tokens) {}
    private ProviderResult parse(String body,int attempts) {
        try {
            var node=json.readTree(body);
            if(node==null || !"completed".equals(node.path("status").asText()) || !node.path("steps").isArray())
                return ProviderResult.failure(Status.INVALID_RESPONSE,attempts);
            String output=null;
            for(var step:node.path("steps")) {
                if(!"model_output".equals(step.path("type").asText()))continue;
                if(!step.path("content").isArray())return ProviderResult.failure(Status.INVALID_RESPONSE,attempts);
                for(var content:step.path("content")) {
                    if(!"text".equals(content.path("type").asText()))return ProviderResult.failure(Status.INVALID_RESPONSE,attempts);
                    if(output!=null || !content.path("text").isTextual())return ProviderResult.failure(Status.INVALID_RESPONSE,attempts);
                    output=content.path("text").textValue();
                }
            }
            if(output==null || output.isBlank() || output.length()>16000)return ProviderResult.failure(Status.INVALID_RESPONSE,attempts);
            return new ProviderResult(Status.SUCCESS,output,attempts,safeRequestId(node.path("id").asText(null)));
        }catch(Exception e){return ProviderResult.failure(Status.INVALID_RESPONSE,attempts);}
    }
    private String safeRequestId(String id){return id!=null && id.matches("[A-Za-z0-9_-]{1,120}")?id:null;}
    /** Cancel oversized transport responses without buffering unbounded provider data. */
    private static final class LimitedBody implements HttpResponse.BodySubscriber<String> {
        private final CompletableFuture<String> done=new CompletableFuture<>();private final ByteArrayOutputStream bytes=new ByteArrayOutputStream();private Flow.Subscription subscription;
        public CompletionStage<String> getBody(){return done;}
        public void onSubscribe(Flow.Subscription s){subscription=s;s.request(1);}
        public void onNext(List<ByteBuffer> items){for(var b:items){if(bytes.size()+b.remaining()>65536){subscription.cancel();done.completeExceptionally(new IllegalArgumentException("PROVIDER_BODY_LIMIT"));return;}byte[] data=new byte[b.remaining()];b.get(data);bytes.writeBytes(data);}subscription.request(1);}
        public void onError(Throwable t){done.completeExceptionally(t);}
        public void onComplete(){done.complete(bytes.toString(StandardCharsets.UTF_8));}
    }
}
