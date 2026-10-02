package com.project.ui.application;
import com.project.mde.ui.*;
import com.project.adaptation.manager.AdaptationManager;
import com.project.llm.domain.LlmTypes.FeedbackDto;
import com.project.student.infrastructure.LearningStore;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import java.util.*;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

@Service
public class UiConfigurationService {
    public record Configuration(int configurationVersion,String activityId,boolean showCodePanel,
        HintPanelMode hintPanelMode,FeedbackDetailLevel feedbackDetailLevel,ActivityLayout activityLayout,
        boolean enabledAssistance,NavigationMode navigationMode,DifficultyMode difficultyMode,TutorMode tutorMode,
        TransitionMode transitionMode,String nextActivityId,boolean repeatCurrentActivity,HintStage hintStage,
        String feedbackMessage,boolean generatedCodeVisible) {}
    public record UiFeedback(String message,String question,String focus,HintStage hintStage,
        com.project.llm.domain.LlmTypes.Source source,boolean llmUsed) {}
    public record Response(Configuration configuration,UUID attemptId,UiFeedback feedback,AttemptCodeService.Code code,
        String fingerprint,boolean safeDefault,String reason) {}
    private final JdbcTemplate jdbc;private final ObjectMapper json;private final LearningStore learning;
    private final AdaptationManager adaptation;private final UiProjection projection;private final AttemptCodeService codes;
    public UiConfigurationService(JdbcTemplate jdbc,ObjectMapper json,LearningStore learning,AdaptationManager adaptation,UiProjection projection,AttemptCodeService codes){this.jdbc=jdbc;this.json=json;this.learning=learning;this.adaptation=adaptation;this.projection=projection;this.codes=codes;}
    @Transactional(readOnly=true)
    public Response latest(UUID student,String activity) {
        checkAccess(student,activity);
        var ids=jdbc.query("select id from attempts where student_id=? and activity_id=? order by student_ordinal desc limit 1",(rs,i)->rs.getObject(1,UUID.class),student,activity);
        return ids.isEmpty()?response(projection.safe(activity),null,null,null,false,null):byAttempt(student,ids.getFirst());
    }
    private void checkAccess(UUID student,String activity) {
        var progress=learning.progress(student).stream().filter(p->p.activityId.equals(activity)).findFirst().orElseThrow(()->new ResponseStatusException(NOT_FOUND,"ACTIVITY_NOT_FOUND"));
        if(progress.unlockedAt==null)throw new ResponseStatusException(CONFLICT,"ACTIVITY_LOCKED");
    }
    @Transactional(readOnly=true)
    public Response byAttempt(UUID student,UUID attempt) {
        var activities=jdbc.query("select activity_id from attempts where student_id=? and id=?",(rs,i)->rs.getString(1),student,attempt);
        if(activities.isEmpty())throw new ResponseStatusException(NOT_FOUND,"ATTEMPT_NOT_FOUND");
        String activity=activities.getFirst();checkAccess(student,activity);
        try {
            if(jdbc.queryForObject("select count(*) from adaptation_decisions where attempt_id=?",Integer.class,attempt)==0)throw new IllegalStateException("Decision unavailable");
            var base=base(activity);var decision=adaptation.byAttempt(attempt);
            var texts=jdbc.query("select response_json from feedback_records where attempt_id=? order by created_at desc,id desc limit 1",(rs,i)->rs.getString(1),attempt);
            var feedback=texts.isEmpty()?null:json.readValue(texts.getFirst(),FeedbackDto.class);
            var allowed=new TreeMap<String,String>();
            var current=learning.activities().stream().filter(a->a.id.equals(activity)).findFirst().orElseThrow();
            for(var concept:learning.concepts())if(concept.prerequisites.contains(current.conceptId))
                for(var next:learning.activities())if(!next.reinforcement&&next.conceptId.equals(concept.id)&&learning.progress(student).stream().anyMatch(p->p.activityId.equals(next.id)&&p.unlockedAt!=null))allowed.put(concept.id,next.id);
            var config=projection.project(base,decision.actions(),feedback,allowed);
            var code=config.isShowCodePanel()?codes.generate(student,attempt):null;
            return response(config,attempt,feedback,code,false,null);
        } catch(Exception ex) {
            org.slf4j.LoggerFactory.getLogger(getClass()).warn("UI projection unavailable: {}",ex.getClass().getSimpleName());
            return response(projection.safe(activity),attempt,null,null,true,"UI_CONFIGURATION_UNAVAILABLE");
        }
    }
    private ConcreteUIModel base(String activity) throws Exception {
        if(!Set.of("SEQUENCES","VARIABLES","CONDITIONALS","LOOPS").contains(activity))throw new IllegalArgumentException("Unknown activity");
        var rs=new ResourceSetImpl();rs.getPackageRegistry().put(UiPackage.eNS_URI,UiPackage.eINSTANCE);
        rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ui",new XMIResourceFactoryImpl());
        var resource=rs.createResource(URI.createURI("memory:/base.ui"));
        try(var in=UiPackage.class.getResourceAsStream("/examples/concrete-"+activity.toLowerCase(Locale.ROOT)+".ui")) {
            if(in==null)throw new IllegalStateException("Missing ATL output");resource.load(in,Map.of());
        }
        var model=(ConcreteUIModel)resource.getContents().getFirst();
        if(Diagnostician.INSTANCE.validate(model).getSeverity()!=Diagnostic.OK)throw new IllegalArgumentException("Invalid ATL base");
        return model;
    }
    private Response response(FinalUIConfiguration c,UUID attempt,FeedbackDto feedback,AttemptCodeService.Code code,boolean safe,String reason) {
        var dto=new Configuration(c.getConfigurationVersion(),c.getActivityId(),c.isShowCodePanel(),c.getHintPanelMode(),c.getFeedbackDetailLevel(),c.getActivityLayout(),c.isEnabledAssistance(),c.getNavigationMode(),c.getDifficultyMode(),c.getTutorMode(),c.getTransitionMode(),c.getNextActivityId(),c.isRepeatCurrentActivity(),c.getHintStage(),c.getFeedbackMessage(),c.isGeneratedCodeVisible());
        try {
            var fingerprint=HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(json.writeValueAsString(dto).getBytes(StandardCharsets.UTF_8)));
            UiFeedback display=null;
            if(feedback!=null) {
                String concept=learning.patterns().stream().filter(p->p.id.equals(feedback.focus())).map(p->p.conceptId).findFirst().orElse(feedback.focus());
                String focus=learning.concepts().stream().filter(cn->cn.id.equals(concept)).map(cn->cn.name).findFirst().orElse(feedback.focus());
                display=new UiFeedback(feedback.message(),feedback.question(),focus,HintStage.get(feedback.hintStage().name()),feedback.source(),feedback.llmUsed());
            }
            return new Response(dto,attempt,display,code,fingerprint,safe,reason);
        }catch(Exception ex){throw new IllegalStateException("Cannot serialize UI configuration",ex);}
    }
}
