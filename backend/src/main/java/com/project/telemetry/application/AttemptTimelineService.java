package com.project.telemetry.application;
import com.project.telemetry.domain.TelemetryTypes.*;
import com.project.telemetry.infrastructure.*;
import com.project.adaptation.manager.DecisionTypes.*;
import com.project.llm.domain.LlmTypes.FeedbackDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Instant;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;
@Service
public class AttemptTimelineService {
 public record AttemptSummary(String activityId,String conceptId,boolean functionalPassed,boolean activityPassed,double masteryBefore,double masteryDelta,double masteryAfter,int policyVersion,List<String> patterns,String programHash){}
 public record AttemptReconstruction(UUID studentId,UUID attemptId,UUID sessionId,Instant startedAt,Instant completedAt,TraceStatus traceStatus,List<String> gaps,AttemptSummary attempt,List<Event> events,List<Event> activityOpenEvents,com.project.adaptation.manager.DecisionTypes.DecisionDto adaptation,List<FeedbackDto> feedback,List<UiPayload> uiConfigurations){}
 private final JdbcTemplate jdbc;private final TelemetryRepository events;private final AttemptTraceConsistencyChecker checker;private final ObjectMapper json;private final SessionService sessions;
 public AttemptTimelineService(JdbcTemplate jdbc,TelemetryRepository events,AttemptTraceConsistencyChecker checker,ObjectMapper json,SessionService sessions){this.jdbc=jdbc;this.events=events;this.checker=checker;this.json=json;this.sessions=sessions;}
 @Transactional(readOnly=true) public AttemptReconstruction attempt(UUID student,UUID attempt){
  sessions.current(student);
  var rows=jdbc.queryForList("select * from attempts where id=? and student_id=?",attempt,student);if(rows.isEmpty())throw new ResponseStatusException(NOT_FOUND,"ATTEMPT_NOT_FOUND");var row=rows.getFirst();
  var history=events.byAttempt(student,attempt);
  var program=jdbc.query("select program_hash from attempt_programs where attempt_id=?",(rs,i)->rs.getString(1),attempt).stream().findFirst().orElse(null);
  var decision=jdbc.query("select * from adaptation_decisions where attempt_id=? and student_id=? order by created_at desc,id desc limit 1",(rs,i)->{
   try{return DecisionDto.of(rs.getObject("id",UUID.class),student,attempt,rs.getTimestamp("created_at").toInstant(),json.readValue(rs.getString("semantic_json"),SemanticDecision.class),rs.getString("decision_fingerprint"));}
   catch(Exception e){throw new IllegalStateException("DECISION_REFERENCE_INVALID");}
  },attempt,student).stream().findFirst().orElse(null);
  var feedback=jdbc.query("select response_json from feedback_records where attempt_id=? and student_id=? order by created_at,id",(rs,i)->{try{return json.readValue(rs.getString(1),FeedbackDto.class);}catch(Exception e){throw new IllegalStateException("FEEDBACK_REFERENCE_INVALID");}},attempt,student);
  var checked=checker.check(student,attempt,history,decision!=null,!feedback.isEmpty(),program);
  var gaps=new ArrayList<>(checked.gaps());var state=checked.status();
  if(jdbc.queryForObject("select count(*) from feedback_records where attempt_id=? and student_id<>?",Integer.class,attempt,student)>0){state=TraceStatus.INCONSISTENT;gaps.add("FOREIGN_FEEDBACK_REFERENCE");}
  for(var event:history)if(event.payload() instanceof AdaptationPayload p && (decision==null || !decision.decisionId().equals(p.decisionId()) || !decision.decisionFingerprint().equals(p.decisionFingerprint()))){state=TraceStatus.INCONSISTENT;gaps.add("DECISION_REFERENCE_MISMATCH");}
  var session=history.stream().filter(e->e.type()==Type.ATTEMPT_CREATED).map(Event::sessionId).filter(Objects::nonNull).findFirst().orElse(null);
  var opened=session==null?List.<Event>of():events.bySession(student,session).stream().filter(e->e.type()==Type.ACTIVITY_OPENED && e.payload() instanceof ActivityPayload p && p.activityId().equals(row.get("activity_id")) && !e.occurredAt().isAfter(history.getFirst().occurredAt())).toList();
  var patterns=jdbc.query("select pattern_id from attempt_error_patterns where attempt_id=? order by pattern_order",(rs,i)->rs.getString(1),attempt);
  var summary=new AttemptSummary((String)row.get("activity_id"),(String)row.get("concept_id"),(Boolean)row.get("functional_passed"),(Boolean)row.get("successful"),((Number)row.get("mastery_before")).doubleValue(),((Number)row.get("mastery_delta")).doubleValue(),((Number)row.get("mastery_after")).doubleValue(),((Number)row.get("policy_version")).intValue(),patterns,program);
  Instant at=((java.sql.Timestamp)row.get("submitted_at")).toInstant();
  return new AttemptReconstruction(student,attempt,session,history.isEmpty()?at:history.getFirst().occurredAt(),history.stream().map(Event::occurredAt).max(Comparator.naturalOrder()).orElse(at),state,List.copyOf(gaps),summary,history,opened,decision,feedback,history.stream().filter(e->e.payload() instanceof UiPayload).map(e->(UiPayload)e.payload()).toList());
 }
 public List<Event> session(UUID student,UUID session){sessions.get(student,session);return events.bySession(student,session);}
}
