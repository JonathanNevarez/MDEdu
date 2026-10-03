package com.project.telemetry.application;
import com.project.telemetry.domain.TelemetryTypes.*;
import com.project.telemetry.infrastructure.*;
import com.project.llm.domain.LlmTypes.*;
import com.project.ui.application.UiConfigurationService.Response;
import java.util.*;
import java.time.Instant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.slf4j.MDC;
@Service
public class TelemetryRecorder {
 public String hashObject(Object value){try{return codec.hash(json.writeValueAsString(value));}catch(Exception e){throw new IllegalStateException("AUDIT_HASH_FAILURE");}}
 @org.springframework.beans.factory.annotation.Autowired private com.fasterxml.jackson.databind.ObjectMapper json;
 @org.springframework.beans.factory.annotation.Autowired private PayloadCodec codec;
 public void model(Type type,org.eclipse.emf.ecore.EObject model,Object dto,Boolean valid){
  if(active.get()==null)return;int count=0;var all=model.eAllContents();while(all.hasNext())if(all.next() instanceof com.project.mde.programming.Statement)count++;
  emit(type,new ModelPayload(hashObject(dto),model.eClass().getEPackage().getNsURI(),count,valid),"");
 }
 private record Pending(Type type,Payload payload,String discriminator,Instant at){}
 private record Buffer(UUID student,UUID attempt,UUID session,UUID request,List<Pending> events){}
 private final ThreadLocal<Buffer> active=new ThreadLocal<>();
 private final TelemetryRepository repository;private final SessionService sessions;private final JdbcTemplate jdbc;
 public TelemetryRecorder(TelemetryRepository repository,SessionService sessions,JdbcTemplate jdbc){this.repository=repository;this.sessions=sessions;this.jdbc=jdbc;}
 public void validateContext(UUID student){sessions.current(student);}
 public Scope begin(UUID student,UUID attempt,String activity,String concept){
  if(active.get()!=null)throw new IllegalStateException("NESTED_ATTEMPT_AUDIT");
  var previous=MDC.getCopyOfContextMap();active.set(new Buffer(student,attempt,sessions.current(student),CorrelationContext.requestId(),new ArrayList<>()));
  MDC.put("attemptId",attempt.toString());emit(Type.ATTEMPT_CREATED,new ActivityPayload(activity,concept),"");return new Scope(previous);
 }
 public final class Scope implements AutoCloseable {private final Map<String,String> previous;private Scope(Map<String,String> previous){this.previous=previous;}public void close(){active.remove();MDC.clear();if(previous!=null)MDC.setContextMap(previous);}}
 public void emit(Type type,Payload payload,String discriminator){var buffer=active.get();if(buffer!=null)buffer.events().add(new Pending(type,payload,discriminator,Instant.now()));}
 @Transactional(propagation=Propagation.MANDATORY) public void flush(){var b=active.get();if(b==null)return;for(var e:b.events())repository.append(b.student(),b.attempt(),b.session(),b.request(),e.type(),e.payload(),b.attempt()+":"+e.type()+":"+e.discriminator(),e.at());b.events().clear();}
 private UUID attemptSession(UUID attempt){return jdbc.query("select session_id from attempt_events where attempt_id=? and event_type='ATTEMPT_CREATED'",(rs,i)->rs.getObject(1,UUID.class),attempt).stream().filter(Objects::nonNull).findFirst().orElse(null);}
 @Transactional(propagation=Propagation.MANDATORY) public void feedback(UUID student,FeedbackDto dto,List<CallAudit> calls){
  sessions.current(student);var session=attemptSession(dto.attemptId());var request=CorrelationContext.requestId();var at=Instant.now();
  var payload=new FeedbackPayload(dto.feedbackId(),dto.provider().name(),dto.model(),dto.source().name(),dto.llmUsed(),dto.fallbackReason(),dto.purpose().name(),dto.promptTemplateVersion(),dto.policyVersion(),dto.promptHash(),dto.sanitizedContextHash(),calls.stream().mapToInt(CallAudit::attempts).sum());
  repository.append(student,dto.attemptId(),session,request,Type.FEEDBACK_REQUESTED,payload,"feedback:requested:"+dto.feedbackId(),at);
  for(var call:calls)if(call.purpose()==Purpose.UNKNOWN_CASE_CLASSIFICATION)repository.append(student,dto.attemptId(),session,request,Type.FEEDBACK_CLASSIFICATION_COMPLETED,new ClassificationPayload(dto.feedbackId(),call.status().name(),call.attempts(),call.validationReason()),"feedback:classification:"+dto.feedbackId(),at);
  var type=dto.source()==com.project.llm.domain.LlmTypes.Source.FALLBACK?Type.FEEDBACK_FALLBACK_USED:Type.FEEDBACK_GENERATED;
  repository.append(student,dto.attemptId(),session,request,type,payload,"feedback:result:"+dto.feedbackId(),at);
 }
 @Transactional(propagation=Propagation.REQUIRES_NEW) public void ui(UUID student,UUID attempt,Response value){
  sessions.current(student);var c=value.configuration();var payload=new UiPayload(c.configurationVersion(),value.fingerprint(),c.hintPanelMode().name(),c.navigationMode().name(),c.difficultyMode().name(),c.tutorMode().name(),c.showCodePanel(),value.safeDefault());
  repository.append(student,attempt,attemptSession(attempt),CorrelationContext.requestId(),Type.UI_CONFIGURATION_CREATED,payload,"ui:"+attempt+":"+value.fingerprint()+":"+value.safeDefault(),Instant.now());
 }
}
