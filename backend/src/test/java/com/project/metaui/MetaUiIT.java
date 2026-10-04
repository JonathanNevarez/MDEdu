package com.project.metaui;
import com.project.metaui.application.*;
import com.project.student.application.*;
import com.project.student.api.LearningDtos.*;
import com.project.programming.api.ProgramDto;
import com.project.telemetry.application.AttemptTimelineService;
import com.project.llm.application.FeedbackOrchestrator;
import com.project.ui.application.UiConfigurationService;
import com.project.adaptation.rules.AdaptationRuleLoader;
import com.project.adaptation.manager.*;
import com.project.execution.application.GameExecutionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.*;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@org.springframework.security.test.context.support.WithMockUser(roles="TEACHER")
@Testcontainers @SpringBootTest(properties={"spring.config.import=","LLM_PROVIDER=DISABLED","LLM_API_KEY="}) @AutoConfigureMockMvc
class MetaUiIT {
 @Container static final PostgreSQLContainer<?> DB=new PostgreSQLContainer<>("postgres:17-bookworm");
 @DynamicPropertySource static void db(DynamicPropertyRegistry r){r.add("spring.datasource.url",DB::getJdbcUrl);r.add("spring.datasource.username",DB::getUsername);r.add("spring.datasource.password",DB::getPassword);}
 @Autowired LearningService learning;@Autowired StudentModelProjectionService models;@Autowired MetaUiService meta;
 @Autowired AdaptationInspectionService inspection;@Autowired AdaptationRuleLoader loader;@Autowired AdaptationParametersConfig parameters;
 @Autowired AttemptTimelineService timeline;@Autowired JdbcTemplate jdbc;@Autowired ObjectMapper json;@Autowired MockMvc mvc;
 @MockitoSpyBean GameExecutionService execution;@MockitoSpyBean AdaptationManager adaptation;
 @MockitoSpyBean FeedbackOrchestrator feedback;@MockitoSpyBean UiConfigurationService ui;
 AttemptResponse submit(UUID id,String level,String fixture)throws Exception{try(var in=getClass().getResourceAsStream("/evaluation/"+fixture+".json")){return learning.submit(new SubmitAttempt(id,level,100L,0,json.readValue(in,ProgramDto.class)));}}
 @Test void readOnlyLoopsOverviewDecisionAndTimelineAreOriginal()throws Exception {
  var student=learning.create("META_PRIVATE_SENTINEL").id();
  for(String level:List.of("SEQUENCES","VARIABLES","CONDITIONALS"))submit(student,level,level);
  var attempt=submit(student,"LOOPS","LOOPS_MANUAL");var f=feedback.generate(student,attempt.attemptId());var config=ui.byAttempt(student,attempt.attemptId());
  var beforeModel=models.dto(models.project(student));var beforeEvents=jdbc.queryForObject("select count(*) from attempt_events",Long.class);
  var decisions=jdbc.queryForList("select * from adaptation_decisions order by id");
  clearInvocations(execution,adaptation,feedback,ui);
  assertEquals(beforeModel.conceptMasteries(),meta.overview(student).conceptMasteries());
  assertEquals(attempt.adaptation(),meta.adaptations(student,0,20).items().getFirst().decision());
  assertEquals(attempt.attemptId(),meta.attempts(student,0,20).items().getFirst().attemptId());
  var trace=timeline.attempt(student,attempt.attemptId());assertEquals("COMPLETE",trace.traceStatus().name());
  assertEquals(f,trace.feedback().getFirst());assertEquals(config.fingerprint(),trace.uiConfigurations().getFirst().configurationFingerprint());
  mvc.perform(get("/api/students/{s}/attempts/{a}/timeline",student,attempt.attemptId())).andExpect(status().isOk()).andExpect(content().json(json.writeValueAsString(trace)));
  assertEquals(beforeModel,models.dto(models.project(student)));assertEquals(beforeEvents,jdbc.queryForObject("select count(*) from attempt_events",Long.class));assertEquals(decisions,jdbc.queryForList("select * from adaptation_decisions order by id"));
  verifyNoInteractions(execution,adaptation,feedback,ui);
  assertFalse(json.writeValueAsString(meta.overview(student)).contains("META_PRIVATE_SENTINEL"));
 }
 @Test void sourcesAndHashesMatchManagerInputs()throws Exception {
  var r=inspection.rules();assertEquals(loader.hash(),r.rulesetHash());assertEquals(loader.snapshot().getVersion(),r.rulesetVersion());
  assertEquals(loader.snapshot().getRules().stream().map(a->a.getId()).toList(),r.rules().stream().map(a->a.ruleId()).toList());
  for(int i=0;i<r.rules().size();i++){assertEquals(loader.snapshot().getRules().get(i).getPriority(),r.rules().get(i).priority());assertEquals(loader.snapshot().getRules().get(i).getActions().stream().map(a->a.getType()).toList(),r.rules().get(i).actions().stream().map(a->a.type()).toList());}
  var student=learning.create(null).id();var a=submit(student,"SEQUENCES","SEQUENCES");
  assertEquals(parameters.values(),inspection.parameters().values());assertEquals(a.adaptation().parametersHash(),inspection.parameters().parametersHash());assertEquals(a.adaptation().rulesetHash(),r.rulesetHash());
  mvc.perform(get("/api/adaptation/rules")).andExpect(status().isOk()).andExpect(content().json(json.writeValueAsString(r)));
  mvc.perform(get("/api/adaptation/parameters")).andExpect(status().isOk()).andExpect(content().json(json.writeValueAsString(inspection.parameters())));
 }
 @Test void paginationAndPseudonymousIsolation()throws Exception {
  var a=learning.create("PRIVATE_NAME").id();var b=learning.create(null).id();var attempt=submit(a,"SEQUENCES","SEQUENCES");
  assertEquals(0,meta.attempts(b,0,20).total());assertEquals(0,meta.adaptations(b,0,20).total());assertEquals(1,meta.attempts(a,0,1).total());
  assertTrue(meta.attempts(a,1,1).items().isEmpty());assertEquals(1,meta.students(0,1).items().size());
  assertFalse(json.writeValueAsString(meta.students(0,50)).contains("PRIVATE_NAME"));
  mvc.perform(get("/api/students/{s}/attempts/{a}/timeline",b,attempt.attemptId())).andExpect(status().isNotFound());
  mvc.perform(get("/api/meta/students").param("size","51")).andExpect(status().isBadRequest());
  mvc.perform(get("/api/meta/students").param("page","-1")).andExpect(status().isBadRequest());
  mvc.perform(get("/api/meta/students/{s}",UUID.randomUUID())).andExpect(status().isNotFound());
  mvc.perform(get("/api/meta/students/{s}",b)).andExpect(status().isOk());
 }
 @Test void capabilitiesAndNoPublicWrites()throws Exception {
  var c=meta.capabilities();assertTrue(c.canViewTrace());assertFalse(c.canMutateRules());assertFalse(c.canMutateParameters());assertFalse(c.canReviewProposals());
  mvc.perform(get("/api/meta/capabilities")).andExpect(status().isOk()).andExpect(jsonPath("$.canMutateRules").value(false));
  for(String path:List.of("/api/adaptation/rules","/api/adaptation/parameters")) {
   mvc.perform(put(path).with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf()).contentType("application/json").content("{}")).andExpect(status().isMethodNotAllowed());
   mvc.perform(patch(path).with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf()).contentType("application/json").content("{}")).andExpect(status().isMethodNotAllowed());
  }
 }
 @Test void legacyAndInconsistentRemainVisibleWithoutBackfill()throws Exception {
  var student=learning.create(null).id();var legacy=submit(student,"SEQUENCES","SEQUENCES");var broken=submit(student,"SEQUENCES","SEQUENCES");
  jdbc.execute("ALTER TABLE attempt_events DISABLE TRIGGER attempt_events_append_only");
  try {jdbc.update("delete from attempt_events where attempt_id=?",legacy.attemptId());jdbc.update("delete from attempt_events where attempt_id=? and event_type='EXECUTION_STARTED'",broken.attemptId());}
  finally{jdbc.execute("ALTER TABLE attempt_events ENABLE TRIGGER attempt_events_append_only");}
  assertEquals(2,meta.attempts(student,0,20).total());
  mvc.perform(get("/api/students/{s}/attempts/{a}/timeline",student,legacy.attemptId())).andExpect(status().isOk()).andExpect(jsonPath("$.traceStatus").value("PARTIAL"));
  mvc.perform(get("/api/students/{s}/attempts/{a}/timeline",student,broken.attemptId())).andExpect(status().isOk()).andExpect(jsonPath("$.traceStatus").value("INCONSISTENT"));
  var trace=timeline.attempt(student,legacy.attemptId());assertTrue(trace.feedback().isEmpty());assertNull(trace.adaptation().selectedRule());assertTrue(trace.events().isEmpty());
 }
 @Test void emptyStudentAndSchemaRemainUnchanged()throws Exception {
  var student=learning.create(null).id();assertTrue(meta.attempts(student,0,20).items().isEmpty());assertTrue(meta.adaptations(student,0,20).items().isEmpty());assertEquals(4,meta.overview(student).conceptMasteries().size());
  assertEquals(8,jdbc.queryForObject("select count(*) from flyway_schema_history where success=true",Integer.class));
  assertEquals(4,jdbc.queryForObject("select count(*) from learning_activities",Integer.class));assertEquals(0,jdbc.queryForObject("select count(*) from learning_activities where reinforcement",Integer.class));
 }
}
