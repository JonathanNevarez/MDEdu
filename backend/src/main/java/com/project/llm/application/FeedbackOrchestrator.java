package com.project.llm.application;
import com.project.llm.domain.*;
import com.project.llm.domain.LlmTypes.*;
import com.project.llm.provider.LlmSettings;
import com.project.llm.prompt.*;
import com.project.llm.validation.*;
import com.project.llm.fallback.PedagogicalFallbackService;
import com.project.llm.infrastructure.*;
import com.project.adaptation.manager.*;
import com.project.adaptation.manager.DecisionTypes.DecisionDto;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class FeedbackOrchestrator {
    private final FeedbackStore store;private final FeedbackGenerationLock lock;private final AdaptationManager adaptation;
    private final ContextSanitizer sanitizer;private final HintStageResolver stages;private final LlmPolicy policy;private final LlmSettings settings;
    private final LlmProvider provider;private final PedagogicalPromptBuilder feedbackPrompt;private final UnknownCasePromptBuilder unknownPrompt;
    private final PedagogicalFeedbackValidator feedbackValidator;private final ClosedVocabularyValidator classifierValidator;private final PedagogicalFallbackService fallback;
    public FeedbackOrchestrator(FeedbackStore store,FeedbackGenerationLock lock,AdaptationManager adaptation,ContextSanitizer sanitizer,HintStageResolver stages,
        LlmPolicy policy,LlmSettings settings,LlmProvider provider,PedagogicalPromptBuilder feedbackPrompt,UnknownCasePromptBuilder unknownPrompt,
        PedagogicalFeedbackValidator feedbackValidator,ClosedVocabularyValidator classifierValidator,PedagogicalFallbackService fallback) {
        this.store=store;this.lock=lock;this.adaptation=adaptation;this.sanitizer=sanitizer;this.stages=stages;this.policy=policy;this.settings=settings;this.provider=provider;
        this.feedbackPrompt=feedbackPrompt;this.unknownPrompt=unknownPrompt;this.feedbackValidator=feedbackValidator;this.classifierValidator=classifierValidator;this.fallback=fallback;
    }
    /** No transaction spans the remote calls; decision is read, never recomputed or mutated. */
    public FeedbackDto generate(UUID student,UUID attempt) {
        var evidence=store.evidence(student,attempt);var decision=adaptation.byAttempt(attempt);
        String config=CanonicalHashes.hash(Map.of("settings",settings.configurationKey(),"policy",policy.values(),"tags",policy.tags(),"templateVersion",1));
        String key=CanonicalHashes.hash(List.of(attempt,decision.decisionId(),config));
        return lock.withLock(key,()->{
            var existing=store.existing(attempt,decision.decisionId(),policy.values().version(),config);
            if(existing!=null)return existing;
            return create(student,attempt,evidence,decision,config);
        });
    }
    private FeedbackDto create(UUID student,UUID attempt,AttemptEvidence evidence,DecisionDto decision,String config) {
        var stage=stages.resolve(evidence.hintCount(),decision.actions());
        var previous=store.previousStages(student,attempt,policy.values().maxPreviousHints());
        var context=sanitizer.sanitize(evidence,decision,stage,previous,List.of());
        Classification classification=null;String reason=null;var calls=new ArrayList<CallAudit>();
        boolean unknown=!evidence.activityPassed() && evidence.detectedPatterns().isEmpty() && evidence.analyzable();
        if(!policy.values().feedbackEnabled())reason="FEEDBACK_DISABLED";
        if(reason==null && unknown) {
            if(!policy.values().unknownCaseClassificationEnabled())reason="CLASSIFICATION_DISABLED";
            else {
                var prompt=unknownPrompt.build(context);var result=safeCall(prompt);
                String invalid=null;
                if(result.status()==Status.SUCCESS) {
                    try {classification=classifierValidator.validate(result.output(),evidence.concept());}
                    catch(IllegalArgumentException ex){invalid=ex.getMessage();reason=invalid;}
                }else reason=result.status().name();
                calls.add(audit(prompt,result,invalid));
                if(classification!=null)context=sanitizer.sanitize(evidence,decision,stage,previous,classification.errorTags());
            }
        }
        var prompt=feedbackPrompt.build(context);Feedback feedback=null;
        if(reason==null) {
            var result=safeCall(prompt);String invalid=null;
            if(result.status()==Status.SUCCESS) {
                try {feedback=feedbackValidator.validate(result.output(),context);}
                catch(IllegalArgumentException ex){invalid="INVALID_RESPONSE";reason=invalid;}
            } else reason=result.status().name();
            calls.add(audit(prompt,result,invalid));
        }
        boolean usedFallback=feedback==null;
        if(usedFallback)feedback=fallback.generate(context);
        // Fallback uses the trusted catalog; all patterns/stages are contract-tested.
        Source source=usedFallback?Source.FALLBACK:settings.provider()==Provider.OPENAI?Source.OPENAI:Source.FAKE;
        var dto=new FeedbackDto(UUID.randomUUID(),attempt,decision.decisionId(),Purpose.FEEDBACK_GENERATION,feedback.message(),feedback.question(),feedback.focus(),feedback.hintStage(),feedback.language(),
            source,settings.provider(),settings.model(),source==Source.OPENAI,reason,classification,1,policy.values().version(),prompt.promptHash(),prompt.sanitizedContextHash(),Instant.now().truncatedTo(ChronoUnit.MICROS));
        return store.save(student,config,dto,calls);
    }
    private ProviderResult safeCall(Prompt prompt) {
        try {var result=prompt.purpose()==Purpose.FEEDBACK_GENERATION?provider.generatePedagogicalFeedback(prompt):provider.classifyUncoveredCase(prompt);
            return result==null?ProviderResult.failure(Status.INVALID_RESPONSE,1):result;
        }catch(RuntimeException e){return ProviderResult.failure(Status.PROVIDER_ERROR,1);}
    }
    private CallAudit audit(Prompt p,ProviderResult r,String invalid){return new CallAudit(p.purpose(),invalid==null?r.status():Status.INVALID_RESPONSE,r.attempts(),p.promptHash(),p.sanitizedContextHash(),r.requestId(),invalid);}
}
