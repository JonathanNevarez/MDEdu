package com.project.ui;
import com.project.ui.application.*;
import com.project.ui.infrastructure.AttemptProgramStore;
import com.project.student.application.LearningService;
import com.project.student.api.LearningDtos.*;
import com.project.programming.api.ProgramDto;
import com.project.llm.application.FeedbackOrchestrator;
import com.project.mde.ui.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@Testcontainers @SpringBootTest(properties={"spring.config.import=","LLM_PROVIDER=DISABLED","LLM_API_KEY="}) @AutoConfigureMockMvc
class UiConfigurationIT {
 @Container static final PostgreSQLContainer<?> DB=new PostgreSQLContainer<>("postgres:17-bookworm");
 @DynamicPropertySource static void database(DynamicPropertyRegistry r){r.add("spring.datasource.url",DB::getJdbcUrl);r.add("spring.datasource.username",DB::getUsername);r.add("spring.datasource.password",DB::getPassword);}
 @Autowired com.project.telemetry.application.AttemptTimelineService timeline;
 @Autowired LearningService learning;@Autowired ObjectMapper json;@Autowired JdbcTemplate jdbc;@Autowired MockMvc mvc;
 @org.springframework.test.context.bean.override.mockito.MockitoSpyBean UiProjection uiProjection;
 @Autowired UiConfigurationService ui;@Autowired AttemptProgramStore programs;@Autowired AttemptCodeService codes;@Autowired FeedbackOrchestrator feedback;
 ProgramDto fixture(String name)throws Exception{try(var in=getClass().getResourceAsStream("/evaluation/"+name+".json")){return json.readValue(in,ProgramDto.class);}}
 AttemptResponse submit(UUID id,String level,String name)throws Exception{return learning.submit(new SubmitAttempt(id,level,1000L,0,fixture(name)));}
 @Test void persistedProgramsOwnershipCodeAndLegacyUnavailable()throws Exception{
  var id=learning.create(null).id();var attempt=submit(id,"SEQUENCES","SEQUENCES");
  assertEquals(fixture("SEQUENCES"),programs.find(id,attempt.attemptId()).orElseThrow());
  assertTrue(programs.find(UUID.randomUUID(),attempt.attemptId()).isEmpty());
  var code=codes.generate(id,attempt.attemptId());assertTrue(code.available(),code.reason());assertTrue(code.text().contains("move"));
  jdbc.update("delete from attempt_programs where attempt_id=?",attempt.attemptId());assertEquals("PROGRAM_SNAPSHOT_UNAVAILABLE",codes.generate(id,attempt.attemptId()).reason());
 }
 @Test void loopsHintsRepeatRefreshAndOneHundredDeterministicConfigurations()throws Exception{
  var id=learning.create(null).id();for(String level:List.of("SEQUENCES","VARIABLES","CONDITIONALS"))submit(id,level,level);
  var attempt=submit(id,"LOOPS","LOOPS_MANUAL");var f=feedback.generate(id,attempt.attemptId());assertFalse(f.llmUsed());
  var first=ui.byAttempt(id,attempt.attemptId());assertFalse(first.safeDefault(),first.reason());assertFalse(first.feedback().focus().contains("_"));
  assertNotEquals(HintPanelMode.HIDDEN,first.configuration().hintPanelMode());assertEquals(NavigationMode.REPEAT,first.configuration().navigationMode());
  assertEquals(first.fingerprint(),ui.latest(id,"LOOPS").fingerprint());
  for(int i=0;i<100;i++)assertEquals(first,ui.byAttempt(id,attempt.attemptId()));
  mvc.perform(get("/api/students/{s}/attempts/{a}/ui-configuration",UUID.randomUUID(),attempt.attemptId())).andExpect(status().isNotFound());
 }
 @Test void lockedActivityAndInitialSafeConfiguration()throws Exception{
  var id=learning.create(null).id();assertEquals(NavigationMode.STAY,ui.latest(id,"SEQUENCES").configuration().navigationMode());
  mvc.perform(get("/api/students/{s}/activities/LOOPS/ui-configuration",id)).andExpect(status().isConflict());
 }
 @Test void corruptSnapshotCannotProduceCode()throws Exception{
  var id=learning.create(null).id();var attempt=submit(id,"SEQUENCES","SEQUENCES");
  jdbc.update("update attempt_programs set program_dto_json='{}' where attempt_id=?",attempt.attemptId());
  assertEquals("Attempt program integrity failure",assertThrows(org.springframework.dao.InvalidDataAccessApiUsageException.class,()->programs.find(id,attempt.attemptId())).getMostSpecificCause().getMessage());
 }
 @Test void uiFailureFallsBackWithoutMutatingAttemptsOrLearning()throws Exception{
  var id=learning.create(null).id();var attempt=submit(id,"SEQUENCES","SEQUENCES");
  var before=jdbc.queryForList("select * from student_concept_mastery where student_id=? order by concept_id",id);
  org.mockito.Mockito.doThrow(new IllegalArgumentException("test invalid UI")).when(uiProjection).project(org.mockito.ArgumentMatchers.any(),org.mockito.ArgumentMatchers.anyList(),org.mockito.ArgumentMatchers.any(),org.mockito.ArgumentMatchers.anyMap());
  var safe=ui.byAttempt(id,attempt.attemptId());assertTrue(safe.safeDefault());assertFalse(safe.configuration().showCodePanel());assertEquals(NavigationMode.STAY,safe.configuration().navigationMode());assertNull(safe.configuration().nextActivityId());
  assertTrue(timeline.attempt(id,attempt.attemptId()).uiConfigurations().getFirst().safeDefault());
  assertEquals(before,jdbc.queryForList("select * from student_concept_mastery where student_id=? order by concept_id",id));
  assertEquals(1,jdbc.queryForObject("select count(*) from attempts where student_id=?",Integer.class,id));
 }
 @Test void actualSuccessAdvancesAndDifferentHistoriesStayIndependent()throws Exception{
  var a=learning.create(null).id();var b=learning.create(null).id();AttemptResponse last=null;
  for(int i=0;i<9;i++)last=submit(a,"SEQUENCES","SEQUENCES");
  var config=ui.byAttempt(a,last.attemptId());assertFalse(config.safeDefault());assertEquals(NavigationMode.ADVANCE,config.configuration().navigationMode());assertEquals("VARIABLES",config.configuration().nextActivityId());
  var other=ui.latest(b,"SEQUENCES");assertNotEquals(config.fingerprint(),other.fingerprint());assertEquals(NavigationMode.STAY,other.configuration().navigationMode());
 }

}
