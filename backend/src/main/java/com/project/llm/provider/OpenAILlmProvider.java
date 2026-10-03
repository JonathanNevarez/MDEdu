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

/** Responses API adapter. Production endpoint is fixed; loopback constructor supports real HTTP tests. */
public class OpenAILlmProvider implements LlmProvider {
    private final LlmSettings config;private final URI endpoint;private final ObjectMapper json=new ObjectMapper();
    private final HttpClient http;private final LlmRateLimiter quota;
    public OpenAILlmProvider(LlmSettings config){this(config,URI.create("https://api.openai.com/v1/responses"));}
    public OpenAILlmProvider(LlmSettings config,LlmRateLimiter quota){this(config,URI.create("https://api.openai.com/v1/responses"),quota);}
    public OpenAILlmProvider(LlmSettings config,URI endpoint){this(config,endpoint,new LlmRateLimiter());}
    public OpenAILlmProvider(LlmSettings config,URI endpoint,LlmRateLimiter quota) {
        this.quota=quota;
        if(!endpoint.toString().equals("https://api.openai.com/v1/responses") && !("http".equals(endpoint.getScheme()) && Set.of("127.0.0.1","localhost","[::1]").contains(endpoint.getHost())))throw new IllegalArgumentException("Unsupported provider endpoint");
        this.config=config;this.endpoint=endpoint;http=HttpClient.newBuilder().connectTimeout(Duration.ofMillis(config.timeoutMs())).followRedirects(HttpClient.Redirect.NEVER).build();
    }
    public ProviderResult generatePedagogicalFeedback(Prompt p){return request(p);}
    public ProviderResult classifyUncoveredCase(Prompt p){return request(p);}
    private ProviderResult request(Prompt p) {
        if(!config.available())return ProviderResult.failure(Status.DISABLED,0);
        final String body;
        try {body=json.writeValueAsString(Map.of("model",config.model(),"store",false,"max_output_tokens",1000,
            "instructions",p.instructions(),"input",List.of(Map.of("role","user","content",p.data())),
            "text",Map.of("format",Map.of("type","json_schema","name",p.schemaName(),"strict",true,"schema",json.readTree(p.schemaJson())))));}
        catch(Exception e){return ProviderResult.failure(Status.INVALID_RESPONSE,0);}
        for(int n=1;n<=config.maxRetries()+1;n++) {
            if(!quota.acquire())return ProviderResult.failure(Status.RATE_LIMITED,n-1);
            Status failure;boolean retry;
            CompletableFuture<HttpResponse<String>> pending=null;
            try {
                var request=HttpRequest.newBuilder(endpoint).timeout(Duration.ofMillis(config.timeoutMs()))
                    .header("Authorization","Bearer "+config.key()).header("Content-Type","application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body,StandardCharsets.UTF_8)).build();
                pending=http.sendAsync(request,info->new LimitedBody());
                var response=pending.get(config.timeoutMs(),TimeUnit.MILLISECONDS);
                int code=response.statusCode();
                if(code>=200 && code<300)return parse(response.body(),n,safeRequestId(response.headers().firstValue("x-request-id").orElse(null)));
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
    private ProviderResult parse(String body,int attempts,String id) {
        try {
            var node=json.readTree(body);if(!"completed".equals(node.path("status").asText()))return ProviderResult.failure(Status.INVALID_RESPONSE,attempts);
            String output=null;
            for(var item:node.path("output")) for(var content:item.path("content")) {
                if("refusal".equals(content.path("type").asText()))return new ProviderResult(Status.REFUSED,null,attempts,id);
                if("output_text".equals(content.path("type").asText())) {
                    if(output!=null || !content.path("text").isTextual())return ProviderResult.failure(Status.INVALID_RESPONSE,attempts);
                    output=content.path("text").textValue();
                }
            }
            if(output==null || output.isBlank() || output.length()>16000)return ProviderResult.failure(Status.INVALID_RESPONSE,attempts);
            return new ProviderResult(Status.SUCCESS,output,attempts,id);
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
