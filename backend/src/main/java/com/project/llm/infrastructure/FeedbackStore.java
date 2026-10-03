package com.project.llm.infrastructure;
import com.project.llm.domain.LlmTypes.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;
import java.util.*;
import java.sql.Timestamp;

@Repository
public class FeedbackStore {
    @org.springframework.beans.factory.annotation.Autowired private com.project.telemetry.application.TelemetryRecorder telemetry;
    private final JdbcTemplate jdbc;private final ObjectMapper json;
    public FeedbackStore(JdbcTemplate jdbc,ObjectMapper json){this.jdbc=jdbc;this.json=json;}
    public AttemptEvidence evidence(UUID student,UUID attempt) {
        telemetry.validateContext(student);
        var owners=jdbc.query("select student_id from attempts where id=?",(rs,i)->rs.getObject(1,UUID.class),attempt);
        if(owners.isEmpty() || !owners.getFirst().equals(student))throw new ResponseStatusException(NOT_FOUND,"ATTEMPT_NOT_FOUND");
        var inputs=jdbc.query("select evidence_json from feedback_attempt_inputs where attempt_id=?",(rs,i)->rs.getString(1),attempt);
        if(inputs.isEmpty())throw new ResponseStatusException(CONFLICT,"FEEDBACK_EVIDENCE_UNAVAILABLE");
        return read(inputs.getFirst(),AttemptEvidence.class);
    }
    public List<String> previousStages(UUID student,UUID attempt,int limit) {
        return jdbc.query("select f.response_json from feedback_records f join attempts a on a.id=f.attempt_id join attempts current on current.id=? where f.student_id=? and a.concept_id=current.concept_id and a.student_ordinal<current.student_ordinal order by a.student_ordinal desc,f.created_at desc limit ?",
            (rs,i)->read(rs.getString(1),FeedbackDto.class).hintStage().name(),attempt,student,limit);
    }
    public FeedbackDto existing(UUID attempt,UUID decision,int policy,String config) {
        return jdbc.query("select response_json from feedback_records where attempt_id=? and adaptation_decision_id=? and purpose='FEEDBACK_GENERATION' and prompt_template_version=1 and policy_version=? and configuration_hash=?",
            (rs,i)->read(rs.getString(1),FeedbackDto.class),attempt,decision,policy,config).stream().findFirst().orElse(null);
    }
    public FeedbackDto get(UUID id) {
        return jdbc.query("select response_json from feedback_records where id=?",(rs,i)->read(rs.getString(1),FeedbackDto.class),id).stream().findFirst()
            .orElseThrow(()->new ResponseStatusException(NOT_FOUND,"FEEDBACK_NOT_FOUND"));
    }
    @Transactional
    public FeedbackDto save(UUID student,String config,FeedbackDto dto,List<CallAudit> calls) {
        var c=dto.classification();
        jdbc.update("insert into feedback_records(id,student_id,attempt_id,adaptation_decision_id,purpose,source,provider,model,prompt_template_version,policy_version,configuration_hash,prompt_hash,sanitized_context_hash,status,fallback_reason,llm_used,feedback_text,recognized,confidence,created_at,response_json) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
            dto.feedbackId(),student,dto.attemptId(),dto.adaptationDecisionId(),dto.purpose().name(),dto.source().name(),dto.provider().name(),dto.model(),dto.promptTemplateVersion(),dto.policyVersion(),config,dto.promptHash(),dto.sanitizedContextHash(),dto.source()==Source.FALLBACK?"FALLBACK":"SUCCESS",dto.fallbackReason(),dto.llmUsed(),dto.message(),c==null?null:c.recognized(),c==null?null:c.confidence(),Timestamp.from(dto.createdAt()),write(dto));
        if(c!=null)for(int i=0;i<c.errorTags().size();i++)jdbc.update("insert into feedback_record_tags values (?,?,?)",dto.feedbackId(),c.errorTags().get(i),i);
        for(var a:calls)jdbc.update("insert into feedback_provider_calls values (?,?,?,?,?,?,?,?)",dto.feedbackId(),a.purpose().name(),a.status().name(),a.attempts(),a.promptHash(),a.sanitizedContextHash(),a.providerRequestId(),a.validationReason());
        telemetry.feedback(student,dto,calls);
        return dto;
    }
    private String write(Object o){try{return json.writeValueAsString(o);}catch(Exception e){throw new IllegalStateException("Cannot encode feedback");}}
    private <T>T read(String s,Class<T> t){try{return json.readValue(s,t);}catch(Exception e){throw new IllegalStateException("Invalid persisted feedback");}}
}
