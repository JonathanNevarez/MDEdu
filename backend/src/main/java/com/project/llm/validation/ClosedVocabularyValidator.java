package com.project.llm.validation;
import com.project.llm.domain.LlmTypes.*;
import com.project.llm.application.LlmPolicy;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;
@Component
public class ClosedVocabularyValidator {
    private final PedagogicalFeedbackValidator schema;private final LlmPolicy policy;
    public ClosedVocabularyValidator(PedagogicalFeedbackValidator schema,LlmPolicy policy){this.schema=schema;this.policy=policy;}
    public Classification validate(String raw,String concept) {
        var n=schema.object(raw,Set.of("recognized","errorTags","explanation","confidence"));
        if(!n.path("recognized").isBoolean() || !n.path("errorTags").isArray() || !n.path("confidence").isNumber())throw PedagogicalFeedbackValidator.invalid();
        var tags=new ArrayList<String>();
        for(var t:n.get("errorTags")) {String tag=schema.text(t,100);if(!policy.tags().tags().containsKey(tag))throw new IllegalArgumentException("UNKNOWN_TAG");
            if(!policy.tags().tags().get(tag).contains(concept) || tags.contains(tag))throw PedagogicalFeedbackValidator.invalid();tags.add(tag);}
        var confidence=n.get("confidence").decimalValue();
        if(confidence.signum()<0 || confidence.compareTo(BigDecimal.ONE)>0 || tags.size()>5)throw PedagogicalFeedbackValidator.invalid();
        String explanation=schema.text(n.get("explanation"),600);
        if(!n.get("recognized").booleanValue() || tags.isEmpty() || tags.contains("UNCLASSIFIED"))throw new IllegalArgumentException("UNCLASSIFIED");
        if(confidence.compareTo(policy.values().classificationConfidenceThreshold())<0)throw new IllegalArgumentException("LOW_CONFIDENCE");
        return new Classification(true,tags,explanation,confidence);
    }
}
