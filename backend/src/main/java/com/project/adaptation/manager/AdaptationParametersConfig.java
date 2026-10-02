package com.project.adaptation.manager;

import java.math.BigDecimal;
import java.util.Objects;
import com.project.mde.adaptation.HintLevel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.core.io.ClassPathResource;

@Component
public final class AdaptationParametersConfig {
    public enum FeedbackDetail { STANDARD, CONCISE, DETAILED }
    public record Values(int version, HintLevel hintLevel, boolean difficultyAdjustmentEnabled,
        boolean routeAdaptationEnabled, FeedbackDetail feedbackDetail, int failureThreshold,
        int successThreshold, BigDecimal masteryThreshold, int maxHintsPerActivity, int maxAttemptsBeforeReinforcement) {
        public Values {
            Objects.requireNonNull(hintLevel);Objects.requireNonNull(feedbackDetail);Objects.requireNonNull(masteryThreshold);
            if(version<=0 || failureThreshold<=0 || successThreshold<=0 || masteryThreshold.signum()<0 ||
               masteryThreshold.compareTo(BigDecimal.ONE)>0 || maxHintsPerActivity<0 || maxAttemptsBeforeReinforcement<=0)
                throw new IllegalArgumentException("Invalid adaptation parameters");
        }
    }
    private final Values values;
    public AdaptationParametersConfig(ObjectMapper mapper) throws java.io.IOException {
        try(var in=new ClassPathResource("adaptation/adaptation-parameters.v1.json").getInputStream()) { values=mapper.readValue(in,Values.class); }
    }
    public Values values() { return values; }
}
