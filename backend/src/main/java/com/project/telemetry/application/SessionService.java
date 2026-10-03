package com.project.telemetry.application;
import com.project.telemetry.domain.TelemetryTypes.*;
import com.project.telemetry.infrastructure.TelemetryRepository;
import java.util.*;
import java.time.Instant;
import java.sql.Timestamp;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;
@Service
public class SessionService {
 private final JdbcTemplate jdbc;private final TelemetryRepository events;
 public SessionService(JdbcTemplate jdbc,TelemetryRepository events){this.jdbc=jdbc;this.events=events;}
 @Transactional public Session create(UUID student){
  if(!Boolean.TRUE.equals(jdbc.queryForObject("select exists(select 1 from students where id=?)",Boolean.class,student)))throw new ResponseStatusException(NOT_FOUND,"STUDENT_NOT_FOUND");
  var now=Instant.now();var id=UUID.randomUUID();jdbc.update("insert into sessions(id,student_id,started_at,status) values (?,?,?,'ACTIVE')",id,student,Timestamp.from(now));
  events.append(student,null,id,CorrelationContext.requestId(),Type.SESSION_STARTED,new SessionPayload("ACTIVE"),"session:start:"+id,now);return get(student,id);
 }
 public Session get(UUID student,UUID id){return jdbc.query("select * from sessions where id=? and student_id=?",(rs,i)->new Session(id,student,rs.getTimestamp("started_at").toInstant(),rs.getTimestamp("ended_at")==null?null:rs.getTimestamp("ended_at").toInstant(),rs.getString("status")),id,student).stream().findFirst().orElseThrow(()->new ResponseStatusException(NOT_FOUND,"SESSION_NOT_FOUND"));}
 public UUID current(UUID student){var id=CorrelationContext.sessionId();if(id!=null && !get(student,id).status().equals("ACTIVE"))throw new ResponseStatusException(CONFLICT,"SESSION_ENDED");return id;}
 @Transactional public Session end(UUID student,UUID id){
  get(student,id);jdbc.queryForList("select pg_advisory_xact_lock(hashtextextended(?,0))","telemetry:"+student);jdbc.queryForList("select id from sessions where id=? for update",id);var session=get(student,id);
  if(session.status().equals("ACTIVE")){var now=Instant.now();jdbc.update("update sessions set status='ENDED',ended_at=? where id=?",Timestamp.from(now),id);events.append(student,null,id,CorrelationContext.requestId(),Type.SESSION_ENDED,new SessionPayload("ENDED"),"session:end:"+id,now);}return get(student,id);
 }
}
