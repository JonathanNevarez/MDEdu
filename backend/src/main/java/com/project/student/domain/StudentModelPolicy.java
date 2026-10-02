package com.project.student.domain;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
@Component
public class StudentModelPolicy {
    public record Values(int version,double initialMastery,double successWithoutHintDelta,double successWithHintDelta,
                         double failureDelta,double repeatedErrorDelta,int recentErrorWindow,int maxRecentErrorPatterns) {}
    private final Values values;
    public StudentModelPolicy(ObjectMapper mapper) throws java.io.IOException {
        try(var in=new ClassPathResource("learning/student-model-policy.v1.json").getInputStream()) { values=mapper.readValue(in,Values.class); }
        if(values.version()!=1 || values.initialMastery()<0 || values.initialMastery()>1 || values.recentErrorWindow()<1 || values.maxRecentErrorPatterns()<1 ||
           !Double.isFinite(values.initialMastery()) || !Double.isFinite(values.successWithoutHintDelta()) || !Double.isFinite(values.successWithHintDelta()) ||
           !Double.isFinite(values.failureDelta()) || !Double.isFinite(values.repeatedErrorDelta())) throw new IllegalArgumentException("Invalid mastery policy");
    }
    public Values values() { return values; }
}
