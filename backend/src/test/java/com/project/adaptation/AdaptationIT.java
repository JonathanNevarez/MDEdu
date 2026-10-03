package com.project.adaptation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.adaptation.application.*;
import com.project.adaptation.domain.RuleEngineResult;
import com.project.student.application.*;
import com.project.student.api.LearningDtos.*;
import com.project.programming.api.ProgramDto;
import com.project.mde.learning.*;
import java.nio.file.*;
import java.util.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers @SpringBootTest(properties="spring.config.import=")
class AdaptationIT {
    @Container static final PostgreSQLContainer<?> DB=new PostgreSQLContainer<>("postgres:17-bookworm");
    @DynamicPropertySource static void database(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url",DB::getJdbcUrl);r.add("spring.datasource.username",DB::getUsername);r.add("spring.datasource.password",DB::getPassword);
    }
    @Autowired LearningService learning;
    @Autowired StudentModelProjectionService projection;
    @Autowired RuleEvaluationService rules;
    @Autowired RuleContextFactory contexts;
    @Autowired ObjectMapper json;
    @Autowired JdbcTemplate jdbc;
    AttemptResponse submit(UUID id,String level,String file) throws Exception {
        try(var in=getClass().getResourceAsStream("/evaluation/"+file+".json")) {
            return learning.submit(new SubmitAttempt(id,level,1000L,0,json.readValue(in,ProgramDto.class)));
        }
    }
    void openLoops(UUID id) throws Exception { for(String c:List.of("SEQUENCES","VARIABLES","CONDITIONALS")) submit(id,c,c); }
    RuleEngineResult evaluate(UUID id,AttemptResponse response) {
        var model=projection.project(id);var before=EcoreUtil.copy(model);
        var attempt=model.getAttempts().stream().filter(a->a.getId().equals(response.attemptId().toString())).findFirst().orElseThrow();
        var result=rules.evaluate(model,attempt,response.execution().evaluation(),response.masteryUpdate());
        assertTrue(EcoreUtil.equals(before,model));assertTrue(EcoreUtil.equals(model,projection.project(id)));
        return result;
    }
    @Test void realStudentsProduceDifferentCandidatesWithoutSideEffects() throws Exception {
        var a=learning.create("Rules A").id();var b=learning.create("Rules B").id();openLoops(a);openLoops(b);
        AttemptResponse ra=null,rb=null;
        for(int i=0;i<8;i++) ra=submit(a,"LOOPS","LOOPS");
        for(int i=0;i<3;i++) rb=submit(b,"LOOPS","LOOPS_MANUAL");
        var resultA=evaluate(a,ra);var resultB=evaluate(b,rb);
        assertEquals(List.of("DominioAlto"),resultA.matches().stream().map(m->m.ruleId()).toList());
        assertEquals(List.of("ReforzarCiclos","ErrorRepetido","FuncionalSinConcepto"),resultB.matches().stream().map(m->m.ruleId()).toList());
        assertEquals(.80,projection.project(a).getConceptMasteries().get(3).getMasteryScore());
        assertEquals(0,projection.project(b).getConceptMasteries().get(3).getMasteryScore());
        assertEquals(7,jdbc.queryForObject("select count(*) from flyway_schema_history where success",Integer.class));
        assertEquals(4,jdbc.queryForObject("select count(*) from information_schema.tables where table_schema='public' and table_name like 'adaptation%'",Integer.class));
        Files.createDirectories(Path.of("target/adaptation-evidence"));
        Files.writeString(Path.of("target/adaptation-evidence/students-ab.json"),json.writerWithDefaultPrettyPrinter().writeValueAsString(Map.of("A",resultA,"B",resultB)));
    }
    @Test void phaseFiveManualLoopAndExactRepeatedPolicy() throws Exception {
        var id=learning.create(null).id();openLoops(id);
        var first=submit(id,"LOOPS","LOOPS_MANUAL");var firstMatches=evaluate(id,first);
        assertTrue(first.execution().evaluation().functionalCorrectness().passed());
        assertFalse(first.execution().evaluation().structuralCorrectness().requiredConceptUsed());
        assertEquals("REPETITIVE_SEQUENCE_WITHOUT_LOOP",first.execution().evaluation().patterns().getFirst().id());
        assertFalse(first.masteryUpdate().reasons().contains("REPEATED_ERROR_PATTERN"));
        assertEquals(List.of("FuncionalSinConcepto"),firstMatches.matches().stream().map(m->m.ruleId()).toList());
        var second=submit(id,"LOOPS","LOOPS_MANUAL");
        assertTrue(second.masteryUpdate().reasons().contains("REPEATED_ERROR_PATTERN"));
        assertTrue(evaluate(id,second).matches().stream().anyMatch(m->m.ruleId().equals("ErrorRepetido")));
        var correct=submit(id,"LOOPS","LOOPS");assertTrue(correct.execution().evaluation().activityPassed());
        assertTrue(evaluate(id,correct).matches().stream().noneMatch(m->m.ruleId().equals("FuncionalSinConcepto")));
    }
    @Test void rejectsCrossStudentOrStaleContext() throws Exception {
        var a=learning.create(null).id();var b=learning.create(null).id();
        var response=submit(a,"SEQUENCES","SEQUENCES");var modelA=projection.project(a);var modelB=projection.project(b);
        assertThrows(IllegalArgumentException.class,()->contexts.create(modelB,modelA.getAttempts().getFirst(),response.execution().evaluation(),response.masteryUpdate()));
        submit(a,"SEQUENCES","SEQUENCES");var after=projection.project(a);
        var old=after.getAttempts().stream().filter(t->t.getId().equals(response.attemptId().toString())).findFirst().orElseThrow();
        assertThrows(IllegalArgumentException.class,()->contexts.create(after,old,response.execution().evaluation(),response.masteryUpdate()));
    }
}
