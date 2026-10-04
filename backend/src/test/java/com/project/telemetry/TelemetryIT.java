package com.project.telemetry;
import com.project.telemetry.domain.TelemetryTypes.*;
import com.project.telemetry.application.*;
import com.project.telemetry.infrastructure.*;
import com.project.student.application.*;
import com.project.student.api.LearningDtos.*;
import com.project.programming.api.ProgramDto;
import com.project.llm.application.FeedbackOrchestrator;
import com.project.ui.application.UiConfigurationService;
import com.project.execution.application.GameExecutionService;
import com.project.adaptation.manager.AdaptationManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import java.util.concurrent.*;
import java.time.Instant;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.*;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import org.slf4j.MDC;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@org.junit.jupiter.api.extension.ExtendWith(org.springframework.boot.test.system.OutputCaptureExtension.class)
@org.springframework.security.test.context.support.WithMockUser(roles="TEACHER")
@Testcontainers @SpringBootTest(properties={"spring.config.import=","LLM_PROVIDER=DISABLED","LLM_API_KEY="}) @AutoConfigureMockMvc
class TelemetryIT {
    private static org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder post(String path,Object... args){return org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(path,args).with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf());}
 @Container static final PostgreSQLContainer<?> DB=new PostgreSQLContainer<>("postgres:17-bookworm");
 @DynamicPropertySource static void db(DynamicPropertyRegistry r){r.add("spring.datasource.url",DB::getJdbcUrl);r.add("spring.datasource.username",DB::getUsername);r.add("spring.datasource.password",DB::getPassword);}
 @Autowired LearningService learning;@Autowired SessionService sessions;@Autowired TelemetryRepository events;@Autowired AttemptTimelineService timeline;@Autowired ClientTelemetryService client;
 @Autowired FeedbackOrchestrator feedback;@Autowired UiConfigurationService ui;@Autowired JdbcTemplate jdbc;@Autowired ObjectMapper json;@Autowired MockMvc mvc;@Autowired PlatformTransactionManager transactions;
 @MockitoSpyBean com.project.llm.prompt.PedagogicalPromptBuilder prompts;
 @MockitoSpyBean GameExecutionService execution;@MockitoSpyBean AdaptationManager adaptation;
 @AfterEach void clear(){MDC.clear();}
 AttemptResponse submit(UUID id,String level,String fixture)throws Exception{try(var in=getClass().getResourceAsStream("/evaluation/"+fixture+".json")){return learning.submit(new SubmitAttempt(id,level,100L,0,json.readValue(in,ProgramDto.class)));}}
 UUID session(UUID student){var s=sessions.create(student);MDC.put("sessionId",s.id().toString());MDC.put("requestId",UUID.randomUUID().toString());return s.id();}
 @Test void completeKnownLoopsIsPrivateAndDoesNotReexecute()throws Exception {
  var student=learning.create("PRIVATE_SENTINEL private@example.com").id();var session=session(student);
  for(String level:List.of("SEQUENCES","VARIABLES","CONDITIONALS"))submit(student,level,level);
  client.record(student,null,"LOOPS",Type.ACTIVITY_OPENED,UUID.randomUUID(),null);
  var attempt=submit(student,"LOOPS","LOOPS_MANUAL");var f=feedback.generate(student,attempt.attemptId());var config=ui.byAttempt(student,attempt.attemptId());
  client.record(student,attempt.attemptId(),"LOOPS",Type.NAVIGATION_PRESENTED,UUID.randomUUID(),config.fingerprint());
  clearInvocations(execution,adaptation);var trace=timeline.attempt(student,attempt.attemptId());
  assertEquals(TraceStatus.COMPLETE,trace.traceStatus(),trace.gaps().toString());assertEquals(session,trace.sessionId());assertTrue(trace.attempt().functionalPassed());assertFalse(trace.attempt().activityPassed());assertEquals(List.of("REPETITIVE_SEQUENCE_WITHOUT_LOOP"),trace.attempt().patterns());
  assertEquals(attempt.adaptation(),trace.adaptation());assertEquals(f,trace.feedback().getFirst());assertEquals(config.fingerprint(),trace.uiConfigurations().getFirst().configurationFingerprint());assertEquals(1,trace.activityOpenEvents().size());
  assertTrue(trace.events().stream().anyMatch(e->e.type()==Type.FEEDBACK_FALLBACK_USED));assertTrue(trace.events().stream().filter(e->e.payload() instanceof FeedbackPayload).allMatch(e->((FeedbackPayload)e.payload()).providerCallCount()==0));
  verifyNoInteractions(execution);verifyNoInteractions(adaptation);
  String encoded=json.writeValueAsString(trace);for(String forbidden:List.of("PRIVATE_SENTINEL","private@example.com","gemini-test-secret-never-log","system_instruction","program_dto_json"))assertFalse(encoded.contains(forbidden));
  assertEquals(64,trace.attempt().programHash().length());assertFalse(trace.events().toString().contains("pedagogicalMeaning"));
 }
 @Test void successfulAndRepeatedAttemptsHaveIndependentAudit()throws Exception {
  var student=learning.create(null).id();session(student);
  var first=submit(student,"SEQUENCES","SEQUENCES");assertTrue(timeline.attempt(student,first.attemptId()).attempt().activityPassed());assertNull(timeline.attempt(student,first.attemptId()).adaptation().selectedRule());assertTrue(timeline.attempt(student,first.attemptId()).adaptation().actions().isEmpty());
  for(String level:List.of("VARIABLES","CONDITIONALS"))submit(student,level,level);
  var a=submit(student,"LOOPS","LOOPS_MANUAL");var b=submit(student,"LOOPS","LOOPS_MANUAL");
  var trace=timeline.attempt(student,b.attemptId());assertEquals(TraceStatus.COMPLETE,trace.traceStatus());assertEquals(b.masteryUpdate().delta(),trace.attempt().masteryDelta());assertTrue(b.masteryUpdate().reasons().contains("REPEATED_ERROR_PATTERN"));assertTrue(trace.events().stream().allMatch(e->e.attemptId().equals(b.attemptId())));assertNotEquals(a.attemptId(),b.attemptId());assertTrue(trace.feedback().isEmpty());
 }
 @Test void sessionsEndOwnershipAndMultipleSessions()throws Exception {
  var a=learning.create(null).id();var b=learning.create(null).id();var first=session(a);var x=submit(a,"SEQUENCES","SEQUENCES");var second=session(a);var y=submit(a,"SEQUENCES","SEQUENCES");
  assertEquals(first,timeline.attempt(a,x.attemptId()).sessionId());assertEquals(second,timeline.attempt(a,y.attemptId()).sessionId());assertThrows(org.springframework.web.server.ResponseStatusException.class,()->sessions.get(b,first));assertThrows(org.springframework.web.server.ResponseStatusException.class,()->timeline.attempt(b,x.attemptId()));
  assertEquals("ENDED",sessions.end(a,first).status());assertEquals(sessions.end(a,first),sessions.get(a,first));assertEquals(1,timeline.session(a,first).stream().filter(e->e.type()==Type.SESSION_ENDED).count());
  assertThrows(org.springframework.web.server.ResponseStatusException.class,()->sessions.create(UUID.randomUUID()));
 }
 @Test void appendOnlyAndIdempotentFeedbackUi()throws Exception {
  var student=learning.create(null).id();session(student);var attempt=submit(student,"SEQUENCES","SEQUENCES");
  var a=feedback.generate(student,attempt.attemptId());assertEquals(a,feedback.generate(student,attempt.attemptId()));ui.byAttempt(student,attempt.attemptId());ui.byAttempt(student,attempt.attemptId());
  var history=events.byAttempt(student,attempt.attemptId());assertEquals(1,history.stream().filter(e->e.type()==Type.FEEDBACK_FALLBACK_USED).count());assertEquals(1,history.stream().filter(e->e.type()==Type.UI_CONFIGURATION_CREATED).count());
  assertThrows(org.springframework.dao.DataAccessException.class,()->jdbc.update("update attempt_events set event_version=1 where id=?",history.getFirst().id()));
  assertThrows(org.springframework.dao.DataAccessException.class,()->jdbc.update("delete from attempt_events where id=?",history.getFirst().id()));
 }
 @Test void concurrentAppendAndRetryHaveUniqueSequences()throws Exception {
  var student=learning.create(null).id();var session=session(student);var attempt=submit(student,"SEQUENCES","SEQUENCES");var tx=new TransactionTemplate(transactions);
  var ready=new CountDownLatch(2);var go=new CountDownLatch(1);
  try(var pool=Executors.newFixedThreadPool(2)){var jobs=new ArrayList<Future<?>>();for(int i=0;i<2;i++){int index=i;jobs.add(pool.submit(()->{ready.countDown();try{go.await();}catch(InterruptedException e){throw new RuntimeException(e);}tx.executeWithoutResult(s->events.append(student,attempt.attemptId(),session,UUID.randomUUID(),Type.TECHNICAL_ERROR,new ErrorPayload("TEST","fixture"),"concurrent:"+attempt.attemptId()+":"+index,Instant.now()));}));}assertTrue(ready.await(5,TimeUnit.SECONDS));go.countDown();for(var job:jobs)job.get(10,TimeUnit.SECONDS);}
  tx.executeWithoutResult(s->events.append(student,attempt.attemptId(),session,UUID.randomUUID(),Type.TECHNICAL_ERROR,new ErrorPayload("TEST","fixture"),"concurrent:"+attempt.attemptId()+":0",Instant.now()));
  var rows=events.byAttempt(student,attempt.attemptId());assertEquals(2,rows.stream().filter(e->e.type()==Type.TECHNICAL_ERROR).count());assertEquals(rows.size(),rows.stream().map(Event::sequence).distinct().count());assertEquals(TraceStatus.COMPLETE,timeline.attempt(student,attempt.attemptId()).traceStatus());
 }
 @Test void criticalInsertFailureRollsBackEducationalState()throws Exception {
  var student=learning.create(null).id();session(student);var before=jdbc.queryForList("select * from student_concept_mastery where student_id=? order by concept_id",student);
  jdbc.execute("CREATE FUNCTION test_fail_audit() RETURNS trigger LANGUAGE plpgsql AS $$ BEGIN IF NEW.event_type='EVALUATION_COMPLETED' THEN RAISE EXCEPTION 'TEST_AUDIT_FAILURE'; END IF; RETURN NEW; END $$");jdbc.execute("CREATE TRIGGER test_audit_failure BEFORE INSERT ON attempt_events FOR EACH ROW EXECUTE FUNCTION test_fail_audit()");
  try{assertThrows(RuntimeException.class,()->submit(student,"SEQUENCES","SEQUENCES"));}finally{jdbc.execute("DROP TRIGGER test_audit_failure ON attempt_events");jdbc.execute("DROP FUNCTION test_fail_audit()");}
  assertEquals(0,jdbc.queryForObject("select count(*) from attempts where student_id=?",Integer.class,student));assertEquals(before,jdbc.queryForList("select * from student_concept_mastery where student_id=? order by concept_id",student));assertNull(MDC.get("attemptId"));
 }
 @Test void legacyPartialAndCorruptGapNeverInventEvents()throws Exception {
  var student=learning.create(null).id();session(student);var attempt=submit(student,"SEQUENCES","SEQUENCES");
  jdbc.execute("ALTER TABLE attempt_events DISABLE TRIGGER attempt_events_append_only");
  try{jdbc.update("delete from attempt_events where attempt_id=? and event_type='EXECUTION_STARTED'",attempt.attemptId());}finally{jdbc.execute("ALTER TABLE attempt_events ENABLE TRIGGER attempt_events_append_only");}
  assertEquals(TraceStatus.INCONSISTENT,timeline.attempt(student,attempt.attemptId()).traceStatus());
  var legacy=submit(student,"SEQUENCES","SEQUENCES");jdbc.execute("ALTER TABLE attempt_events DISABLE TRIGGER attempt_events_append_only");try{jdbc.update("delete from attempt_events where attempt_id=?",legacy.attemptId());}finally{jdbc.execute("ALTER TABLE attempt_events ENABLE TRIGGER attempt_events_append_only");}
  var trace=timeline.attempt(student,legacy.attemptId());assertEquals(TraceStatus.PARTIAL,trace.traceStatus());assertTrue(trace.events().isEmpty());assertNotNull(trace.adaptation());
 }
 @Test void structuredLogsAndProgramSentinelStaySafe(org.springframework.boot.test.system.CapturedOutput logs)throws Exception {
  var student=learning.create("LOG_NAME_SENTINEL").id();var session=session(student);var request=MDC.get("requestId");
  ProgramDto program;try(var in=getClass().getResourceAsStream("/evaluation/SEQUENCES.json")){var node=(com.fasterxml.jackson.databind.node.ObjectNode)json.readTree(in);node.put("name","PROGRAM_SENTINEL_NEVER_AUDIT");program=json.treeToValue(node,ProgramDto.class);}
  doAnswer(inv->{var p=(com.project.llm.domain.LlmTypes.Prompt)inv.callRealMethod();return new com.project.llm.domain.LlmTypes.Prompt(p.purpose(),p.templateVersion(),"RAW_PROMPT_SENTINEL_NEVER_AUDIT",p.data(),p.schemaName(),p.schemaJson(),p.promptHash(),p.sanitizedContextHash());}).when(prompts).build(any());
  var attempt=learning.submit(new SubmitAttempt(student,"SEQUENCES",100L,0,program));feedback.generate(student,attempt.attemptId());
  String audit=json.writeValueAsString(timeline.attempt(student,attempt.attemptId()));
  assertFalse(audit.contains("PROGRAM_SENTINEL_NEVER_AUDIT"));assertFalse(audit.contains("LOG_NAME_SENTINEL"));assertFalse(audit.contains("RAW_PROMPT_SENTINEL_NEVER_AUDIT"));
  assertTrue(logs.getAll().contains("requestId="+request));assertTrue(logs.getAll().contains("sessionId="+session));assertTrue(logs.getAll().contains("attemptId="+attempt.attemptId()));
  for(String secret:List.of("PROGRAM_SENTINEL_NEVER_AUDIT","RAW_PROMPT_SENTINEL_NEVER_AUDIT","LOG_NAME_SENTINEL","gemini-test-secret-never-log"))assertFalse(logs.getAll().contains(secret));
 }
 @Test void invalidSessionRejectedBeforeExecution()throws Exception {
  var student=learning.create(null).id();MDC.put("sessionId",UUID.randomUUID().toString());clearInvocations(execution);
  assertThrows(org.springframework.web.server.ResponseStatusException.class,()->submit(student,"SEQUENCES","SEQUENCES"));verifyNoInteractions(execution);
  assertEquals(0,jdbc.queryForObject("select count(*) from attempts where student_id=?",Integer.class,student));
 }
 @Test void inconsistentForeignFeedbackIsFlaggedWithoutExposingItsText()throws Exception {
  var a=learning.create(null).id();var b=learning.create(null).id();session(a);var attempt=submit(a,"SEQUENCES","SEQUENCES");var f=feedback.generate(a,attempt.attemptId());
  jdbc.update("update feedback_records set student_id=? where id=?",b,f.feedbackId());
  var trace=timeline.attempt(a,attempt.attemptId());assertEquals(TraceStatus.INCONSISTENT,trace.traceStatus());assertTrue(trace.feedback().isEmpty());assertTrue(trace.gaps().contains("FOREIGN_FEEDBACK_REFERENCE"));
 }
 @Test void apiCorrelationAllowlistAndIsolation()throws Exception {
  var student=learning.create(null).id();var session=session(student);var request=UUID.randomUUID().toString();
  mvc.perform(get("/api/sessions/{id}",session).param("studentId",student.toString()).header("X-Request-Id",request)).andExpect(status().isOk()).andExpect(header().string("X-Request-Id",request));
  mvc.perform(get("/api/sessions/{id}",session).param("studentId",student.toString()).header("X-Request-Id","bad-value")).andExpect(status().isOk()).andExpect(header().exists("X-Request-Id"));
  mvc.perform(post("/api/telemetry/events").header("X-Session-Id",session).contentType("application/json").content(json.writeValueAsString(Map.of("studentId",student,"activityId","SEQUENCES","type","EVALUATION_COMPLETED","clientEventId",UUID.randomUUID())))).andExpect(status().isBadRequest());
  var attempt=submit(student,"SEQUENCES","SEQUENCES");mvc.perform(get("/api/students/{s}/attempts/{a}/timeline",student,attempt.attemptId())).andExpect(status().isOk()).andExpect(jsonPath("$.traceStatus").value("COMPLETE"));
  mvc.perform(get("/api/students/{s}/attempts/{a}/timeline",UUID.randomUUID(),attempt.attemptId())).andExpect(status().isNotFound());mvc.perform(get("/api/students/{s}/attempts/{a}/timeline",student,UUID.randomUUID())).andExpect(status().isNotFound());
 }
}
