package com.project.llm.validation;
import com.project.llm.domain.LlmTypes.*;
import com.project.llm.application.LlmPolicy;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.util.*;
import org.springframework.stereotype.Component;
@Component
public class PedagogicalFeedbackValidator {
    private final ObjectMapper json=JsonMapper.builder().enable(StreamReadFeature.STRICT_DUPLICATE_DETECTION).enable(DeserializationFeature.FAIL_ON_TRAILING_TOKENS).build();
    private final LlmPolicy policy;
    public PedagogicalFeedbackValidator(LlmPolicy policy){this.policy=policy;}
    public JsonNode object(String raw,Set<String> fields) {
        try {
            if(raw==null || raw.length()>policy.values().maxOutputChars())throw invalid();
            JsonNode n=json.readTree(raw);var actual=new HashSet<String>();n.fieldNames().forEachRemaining(actual::add);
            if(!n.isObject() || !actual.equals(fields))throw invalid();return n;
        }catch(java.io.IOException|NullPointerException e){throw invalid();}
    }
    public String text(JsonNode n,int max) {
        if(n==null || !n.isTextual() || n.textValue().isBlank() || n.textValue().length()>max || n.textValue().chars().anyMatch(c->c<32 && c!=10))throw invalid();
        return n.textValue();
    }
    public Feedback validate(String raw,PedagogicalLlmContext c) {
        var n=object(raw,Set.of("message","question","focus","hintStage","language"));
        String message=text(n.get("message"),policy.values().maxMessageChars());
        String question=n.get("question").isNull()?null:text(n.get("question"),policy.values().maxQuestionChars());
        String focus=text(n.get("focus"),policy.values().maxFocusChars());
        if(!n.path("language").asText().equals("es") || !n.path("hintStage").asText().equals(c.hintStage().name()) ||
            !focus.equals(c.detectedPattern()==null?c.concept():c.detectedPattern()))throw invalid();
        return new Feedback(message,question,focus,c.hintStage(),"es");
    }
    public static IllegalArgumentException invalid(){return new IllegalArgumentException("INVALID_RESPONSE");}
}
