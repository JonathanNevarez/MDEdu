package com.project.adaptation;

import com.fasterxml.jackson.databind.*;
import com.project.adaptation.manager.*;
import com.project.adaptation.manager.DecisionTypes.*;
import com.project.student.application.*;
import com.project.student.api.LearningDtos.*;
import com.project.programming.api.ProgramDto;
import com.project.execution.application.GameExecutionService;
import com.project.evaluation.application.SolutionEvaluationService;
import java.util.*;
import java.util.concurrent.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.*;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@org.springframework.security.test.context.support.WithMockUser(roles="TEACHER")
@Testcontainers @SpringBootTest(properties="spring.config.import=") @AutoConfigureMockMvc
class AdaptationManagerIT {
    @Container static final PostgreSQLContainer<?> DB=new PostgreSQLContainer<>("postgres:17-bookworm");
    @DynamicPropertySource static void database(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url",DB::getJdbcUrl);r.add("spring.datasource.username",DB::getUsername);r.add("spring.datasource.password",DB::getPassword);
    }
    @Autowired LearningService learning;
    @Autowired StudentModelProjectionService projection;
    @Autowired AdaptationManager manager;
    @Autowired ObjectMapper json;
    @Autowired MockMvc mvc;
    @Autowired JdbcTemplate jdbc;
    @MockitoSpyBean GameExecutionService execution;
    @MockitoSpyBean SolutionEvaluationService evaluation;
    ProgramDto fixture(String name) throws Exception {try(var in=getClass().getResourceAsStream("/evaluation/"+name+".json")){return json.readValue(in,ProgramDto.class);}}
    AttemptResponse submit(UUID id,String level,String file) throws Exception {return learning.submit(new SubmitAttempt(id,level,1000L,0,fixture(file)));}
    void openLoops(UUID id) throws Exception {for(String level:List.of("SEQUENCES","VARIABLES","CONDITIONALS"))submit(id,level,level);}
    @Test void actualThreeFailuresDecisionAuditAndNoDoubleExecution() throws Exception {
        var id=learning.create("Do not persist this display name in audit").id();openLoops(id);
        submit(id,"LOOPS","LOOPS_MANUAL");submit(id,"LOOPS","LOOPS_MANUAL");clearInvocations(execution,evaluation);
        var response=submit(id,"LOOPS","LOOPS_MANUAL");var decision=response.adaptation();
        verify(execution,times(1)).execute(any(),any());verify(evaluation,times(1)).evaluate(any(),any(),any());
        assertEquals("FuncionalSinConcepto",decision.selectedRule());assertEquals(2,decision.actions().size());assertFalse(decision.llmUsed());
        assertTrue(decision.actionAudit().stream().anyMatch(a->a.reasonCode().equals("NOT_APPLICABLE_NO_REINFORCEMENT_ACTIVITY")));
        assertEquals(List.of("ErrorRepetido","ReforzarCiclos"),decision.contributingRules());
        assertTrue(decision.explanation().evidence().stream().anyMatch(e->e.contains("specificity=2")));
        assertEquals(6,jdbc.queryForObject("select count(*) from adaptation_decision_rules where decision_id=?",Integer.class,decision.decisionId()));
        assertEquals(decision.actionAudit().size(),jdbc.queryForObject("select count(*) from adaptation_decision_actions where decision_id=?",Integer.class,decision.decisionId()));
        var stored=jdbc.queryForObject("select semantic_json from adaptation_decisions where id=?",String.class,decision.decisionId());
        assertFalse(stored.contains("Do not persist"));assertFalse(stored.contains("displayName"));
        assertEquals(decision,manager.decide(id,response.attemptId()));
        var semantic=json.readValue(stored,SemanticDecision.class);
        ManagerDomainTest.roundTripDecision(semantic,"three-failures-real");
        var formal=AdaptationConflictResolver.toModel(semantic);
        assertEquals(0,org.eclipse.emf.ecore.util.Diagnostician.INSTANCE.validate(formal).getSeverity());
        java.nio.file.Files.createDirectories(java.nio.file.Path.of("target/manager-evidence"));
        java.nio.file.Files.writeString(java.nio.file.Path.of("target/manager-evidence/three-failures.json"),json.writerWithDefaultPrettyPrinter().writeValueAsString(decision));
    }
    @Test void highMasteryHasRealGraphSuccessorAndDifferentStudentDecision() throws Exception {
        var a=learning.create(null).id();var b=learning.create(null).id();AttemptResponse high=null;
        for(int i=0;i<8;i++)high=submit(a,"SEQUENCES","SEQUENCES");
        var no=submit(b,"SEQUENCES","SEQUENCES");
        assertEquals("DominioAlto",high.adaptation().selectedRule());assertEquals("VARIABLES",high.adaptation().actions().getFirst().targetConceptId());
        assertNull(no.adaptation().selectedRule());assertEquals("NO_RULE_MATCHED",no.adaptation().explanation().reason());
        assertNotEquals(high.adaptation().decisionFingerprint(),no.adaptation().decisionFingerprint());
    }
    @Test void idempotencyAfterNewAttemptsUsesOriginalSnapshot() throws Exception {
        var id=learning.create(null).id();var first=submit(id,"SEQUENCES","SEQUENCES");
        for(int i=0;i<8;i++)submit(id,"SEQUENCES","SEQUENCES");
        assertEquals(first.adaptation(),manager.decide(id,first.attemptId()));
        assertEquals(1,jdbc.queryForObject("select count(*) from adaptation_decisions where attempt_id=?",Integer.class,first.attemptId()));
    }
    @Test void concurrentCreationHasSingleDecision() throws Exception {
        var id=learning.create(null).id();var attempt=submit(id,"SEQUENCES","SEQUENCES").attemptId();
        // Only this test's decision is removed; immutable input remains to exercise concurrent creation.
        jdbc.update("delete from adaptation_decisions where attempt_id=?",attempt);
        var ready=new CountDownLatch(2);var start=new CountDownLatch(1);
        try(var pool=Executors.newFixedThreadPool(2)) {
            Callable<DecisionDto> task=()->{ready.countDown();start.await();return manager.decide(id,attempt);};
            var one=pool.submit(task);var two=pool.submit(task);assertTrue(ready.await(10,TimeUnit.SECONDS));start.countDown();
            assertEquals(one.get(20,TimeUnit.SECONDS),two.get(20,TimeUnit.SECONDS));
        }
        assertEquals(1,jdbc.queryForObject("select count(*) from adaptation_decisions where attempt_id=?",Integer.class,attempt));
    }
    @Test void auditPersistenceFailureRollsBackEntireAttempt() throws Exception {
        var id=learning.create(null).id();
        jdbc.execute("CREATE FUNCTION fail_adaptation_audit() RETURNS trigger LANGUAGE plpgsql AS $$ BEGIN RAISE EXCEPTION 'test audit failure'; END $$");
        jdbc.execute("CREATE TRIGGER fail_audit BEFORE INSERT ON adaptation_decision_rules FOR EACH ROW EXECUTE FUNCTION fail_adaptation_audit()");
        try {assertThrows(RuntimeException.class,()->submit(id,"SEQUENCES","SEQUENCES"));}
        finally {jdbc.execute("DROP TRIGGER fail_audit ON adaptation_decision_rules");jdbc.execute("DROP FUNCTION fail_adaptation_audit()");}
        assertEquals(0,jdbc.queryForObject("select count(*) from adaptation_decisions where student_id=?",Integer.class,id));
        assertEquals(0,jdbc.queryForObject("select count(*) from adaptation_attempt_inputs where student_id=?",Integer.class,id));
        assertEquals(0,jdbc.queryForObject("select count(*) from attempts where student_id=?",Integer.class,id));
        assertEquals(0,projection.project(id).getConceptMasteries().getFirst().getAttemptCount());
    }
    @Test void endpointsAndClientCannotOverridePedagogy() throws Exception {
        var id=learning.create(null).id();var other=learning.create(null).id();var response=submit(id,"SEQUENCES","SEQUENCES");
        String body=json.writeValueAsString(Map.of("studentId",id,"attemptId",response.attemptId(),"masteryScore",1,"selectedRule","FORGED"));
        mvc.perform(post("/api/adaptation/decide").with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf()).contentType("application/json").content(body)).andExpect(status().isOk())
            .andExpect(jsonPath("$.decisionId").value(response.adaptation().decisionId().toString())).andExpect(jsonPath("$.selectedRule").doesNotExist());
        mvc.perform(get("/api/adaptation/decisions/"+response.adaptation().decisionId())).andExpect(status().isOk()).andExpect(jsonPath("$.llmUsed").value(false));
        mvc.perform(get("/api/attempts/"+response.attemptId()+"/adaptation")).andExpect(status().isOk());
        for(var pair:List.of(Map.of("studentId",UUID.randomUUID(),"attemptId",response.attemptId()),Map.of("studentId",id,"attemptId",UUID.randomUUID()),Map.of("studentId",other,"attemptId",response.attemptId())))
            mvc.perform(post("/api/adaptation/decide").with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf()).contentType("application/json").content(json.writeValueAsString(pair))).andExpect(status().isNotFound());
        mvc.perform(post("/api/adaptation/decide").with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf()).contentType("application/json").content("{}")).andExpect(status().isBadRequest());
    }
    @Test void postAttemptHasAdaptationAndStatelessDoesNotPersist() throws Exception {
        var id=learning.create(null).id();
        mvc.perform(post("/api/attempts").contentType("application/json").content(json.writeValueAsString(new SubmitAttempt(id,"SEQUENCES",1000L,0,fixture("SEQUENCES")))))
            .andExpect(status().isCreated()).andExpect(jsonPath("$.execution.evaluation.activityPassed").value(true)).andExpect(jsonPath("$.adaptation.explanation.reason").value("NO_RULE_MATCHED"));
        int before=jdbc.queryForObject("select count(*) from adaptation_decisions",Integer.class);
        mvc.perform(post("/api/game/levels/LOOPS/execute").contentType("application/json").content(json.writeValueAsString(fixture("LOOPS_MANUAL"))))
            .andExpect(status().isOk()).andExpect(jsonPath("$.evaluation.activityPassed").value(false));
        assertEquals(before,jdbc.queryForObject("select count(*) from adaptation_decisions",Integer.class));
    }
    @Test void legacyAttemptWithoutEvidenceIsExplicitConflictNotReevaluated() throws Exception {
        var id=learning.create(null).id();var attempt=submit(id,"SEQUENCES","SEQUENCES").attemptId();
        jdbc.update("delete from adaptation_decisions where attempt_id=?",attempt);jdbc.update("delete from adaptation_attempt_inputs where attempt_id=?",attempt);
        clearInvocations(execution,evaluation);
        mvc.perform(post("/api/adaptation/decide").with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf()).contentType("application/json").content(json.writeValueAsString(Map.of("studentId",id,"attemptId",attempt)))).andExpect(status().isConflict());
        verifyNoInteractions(execution,evaluation);
    }
}
