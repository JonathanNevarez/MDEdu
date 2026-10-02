package com.project.llm.application;
import java.math.BigDecimal;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class LlmPolicy {
    public record Values(int version, BigDecimal classificationConfidenceThreshold, boolean feedbackEnabled,
        boolean unknownCaseClassificationEnabled, int maxPromptChars, int maxOutputChars, int maxPreviousHints,
        int maxMessageChars, int maxQuestionChars, int maxFocusChars) {
        public Values {
            if(version<1 || classificationConfidenceThreshold==null || classificationConfidenceThreshold.signum()<0 || classificationConfidenceThreshold.compareTo(BigDecimal.ONE)>0 ||
                maxPromptChars<1000 || maxPromptChars>50000 || maxOutputChars<100 || maxOutputChars>16000 || maxPreviousHints<0 || maxPreviousHints>10 ||
                maxMessageChars<1 || maxMessageChars>600 || maxQuestionChars<1 || maxQuestionChars>300 || maxFocusChars<1 || maxFocusChars>100) throw new IllegalArgumentException("Invalid LLM policy");
        }
    }
    public record Tags(int version, Map<String,List<String>> tags) {}
    private final Values values;
    private final Tags tags;
    public LlmPolicy(ObjectMapper json) throws java.io.IOException {
        try(var in=new ClassPathResource("llm/llm-policy.v1.json").getInputStream()){values=json.readValue(in,Values.class);}
        try(var in=new ClassPathResource("llm/unknown-case-tags.v1.json").getInputStream()){tags=json.readValue(in,Tags.class);}
        if(tags.version()<1 || tags.tags().isEmpty() || !tags.tags().containsKey("UNCLASSIFIED") || tags.tags().values().stream().anyMatch(v->v.isEmpty() || !Set.of("SEQUENCES","VARIABLES","CONDITIONALS","LOOPS").containsAll(v))) throw new IllegalArgumentException("Invalid tags");
    }
    public Values values(){return values;}
    public Tags tags(){return tags;}
}
