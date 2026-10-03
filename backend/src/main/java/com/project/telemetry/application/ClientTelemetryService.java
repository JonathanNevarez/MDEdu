package com.project.telemetry.application;
import com.project.telemetry.domain.TelemetryTypes.*;
import com.project.telemetry.infrastructure.*;
import java.util.*;
import java.time.Instant;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;
@Service
public class ClientTelemetryService {
 private final SessionService sessions;private final TelemetryRepository events;private final JdbcTemplate jdbc;private final PayloadCodec codec;
 public ClientTelemetryService(SessionService sessions,TelemetryRepository events,JdbcTemplate jdbc,PayloadCodec codec){this.sessions=sessions;this.events=events;this.jdbc=jdbc;this.codec=codec;}
 @Transactional public void record(UUID student,UUID attempt,String activity,Type type,UUID clientEvent,String fingerprint){
  if(type!=Type.ACTIVITY_OPENED && type!=Type.NAVIGATION_PRESENTED)throw new ResponseStatusException(BAD_REQUEST,"CLIENT_EVENT_NOT_ALLOWED");
  var session=sessions.current(student);if(session==null)throw new ResponseStatusException(BAD_REQUEST,"SESSION_REQUIRED");
  if(!Boolean.TRUE.equals(jdbc.queryForObject("select exists(select 1 from student_activity_progress where student_id=? and activity_id=? and unlocked_at is not null)",Boolean.class,student,activity)))throw new ResponseStatusException(CONFLICT,"ACTIVITY_LOCKED");
  Payload payload;
  if(type==Type.ACTIVITY_OPENED){if(attempt!=null || fingerprint!=null)throw new ResponseStatusException(BAD_REQUEST,"ACTIVITY_EVENT_INVALID");payload=new ActivityPayload(activity,jdbc.queryForObject("select concept_id from learning_activities where id=?",String.class,activity));}
  else {
   if(attempt==null || fingerprint==null || !fingerprint.matches("[a-f0-9]{64}"))throw new ResponseStatusException(BAD_REQUEST,"NAVIGATION_EVENT_INVALID");
   var found=events.byAttempt(student,attempt).stream().filter(e->e.type()==Type.UI_CONFIGURATION_CREATED).map(e->(UiPayload)e.payload()).filter(p->p.configurationFingerprint().equals(fingerprint)).findFirst().orElseThrow(()->new ResponseStatusException(NOT_FOUND,"UI_AUDIT_NOT_FOUND"));
   if(!Boolean.TRUE.equals(jdbc.queryForObject("select exists(select 1 from attempts where id=? and student_id=? and activity_id=?)",Boolean.class,attempt,student,activity)))throw new ResponseStatusException(NOT_FOUND,"ATTEMPT_NOT_FOUND");
   payload=new NavigationPayload(activity,found.navigationMode(),fingerprint);
  }
  events.append(student,attempt,session,CorrelationContext.requestId(),type,payload,"client:"+session+":"+clientEvent,Instant.now());
 }
}
