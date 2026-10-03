package com.project.telemetry.infrastructure;
import com.project.telemetry.domain.TelemetryTypes.*;
import java.util.*;
import java.time.Instant;
import java.sql.Timestamp;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;
@Repository
public class TelemetryRepository {
 private final JdbcTemplate jdbc;private final PayloadCodec codec;
 public TelemetryRepository(JdbcTemplate jdbc,PayloadCodec codec){this.jdbc=jdbc;this.codec=codec;}
 @Transactional(propagation=Propagation.MANDATORY)
 public void append(UUID student,UUID attempt,UUID session,UUID request,Type type,Payload payload,String key,Instant occurred){
  String encoded=codec.encode(type,payload),hash=codec.hash(encoded);
  if(key==null || key.length()>200)throw new IllegalArgumentException("EVENT_KEY_INVALID");
  if(attempt!=null && !Boolean.TRUE.equals(jdbc.queryForObject("select exists(select 1 from attempts where id=? and student_id=?)",Boolean.class,attempt,student)))throw new ResponseStatusException(NOT_FOUND,"ATTEMPT_NOT_FOUND");
  if(session!=null && !Boolean.TRUE.equals(jdbc.queryForObject("select exists(select 1 from sessions where id=? and student_id=?)",Boolean.class,session,student)))throw new ResponseStatusException(NOT_FOUND,"SESSION_NOT_FOUND");
  // Student-level xact lock also covers dedup keys across two attempts/session requests.
  jdbc.queryForList("select pg_advisory_xact_lock(hashtextextended(?,0))","telemetry:"+student);
  var old=jdbc.query("select payload_hash from attempt_events where student_id=? and event_key=?",(rs,i)->rs.getString(1),student,key);
  if(!old.isEmpty()){if(!old.getFirst().equals(hash))throw new IllegalStateException("EVENT_KEY_CONFLICT");return;}
  Long next=attempt!=null?jdbc.queryForObject("select coalesce(max(sequence_number),0)+1 from attempt_events where attempt_id=?",Long.class,attempt):jdbc.queryForObject("select coalesce(max(sequence_number),0)+1 from attempt_events where session_id=? and attempt_id is null",Long.class,session);
  jdbc.update("insert into attempt_events(id,attempt_id,session_id,student_id,request_id,sequence_number,event_type,event_version,source,occurred_at,payload,payload_hash,event_key) values (?,?,?,?,?,?,?,1,?,?,?::jsonb,?,?)",
   UUID.randomUUID(),attempt,session,student,request,next,type.name(),type.source.name(),Timestamp.from(occurred),encoded,hash,key);
  try{org.slf4j.LoggerFactory.getLogger(getClass()).info("event={} status=STAGED requestId={} sessionId={} attemptId={}",type,request,session,attempt);}catch(RuntimeException ignored){/* Logging never changes the transaction. */}
 }
 public List<Event> byAttempt(UUID student,UUID attempt){return jdbc.query("select * from attempt_events where student_id=? and attempt_id=? order by sequence_number",this::read,student,attempt);}
 public List<Event> bySession(UUID student,UUID session){return jdbc.query("select * from attempt_events where student_id=? and session_id=? order by occurred_at,attempt_id nulls first,sequence_number limit 500",this::read,student,session);}
 private Payload readPayload(Type type,String value){try{return codec.decode(type,value);}catch(IllegalArgumentException ex){return null;}}
 private Event read(java.sql.ResultSet rs,int row)throws java.sql.SQLException {
  var type=Type.valueOf(rs.getString("event_type"));return new Event(rs.getObject("id",UUID.class),rs.getObject("attempt_id",UUID.class),rs.getObject("session_id",UUID.class),rs.getObject("student_id",UUID.class),rs.getObject("request_id",UUID.class),rs.getLong("sequence_number"),type,Source.valueOf(rs.getString("source")),rs.getInt("event_version"),rs.getTimestamp("occurred_at").toInstant(),readPayload(type,rs.getString("payload")),rs.getString("payload_hash"));
 }
}
