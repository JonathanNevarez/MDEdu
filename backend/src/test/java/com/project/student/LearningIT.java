package com.project.student;

import com.fasterxml.jackson.databind.*;
import com.project.student.application.*;
import com.project.student.api.LearningDtos.*;
import com.project.programming.api.ProgramDto;
import com.project.mde.learning.*;
import com.project.mde.learning.validation.LearningModels;
import java.nio.file.*;
import java.util.*;
import java.util.concurrent.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.util.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.*;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Testcontainers @SpringBootTest(properties="spring.config.import=") @AutoConfigureMockMvc
@org.springframework.security.test.context.support.WithMockUser(roles="TEACHER")
class LearningIT {
    private static org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder post(String path,Object... args){return org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(path,args).with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf());}
    @Container static final PostgreSQLContainer<?> DB=new PostgreSQLContainer<>("postgres:17-bookworm");
    @DynamicPropertySource static void database(DynamicPropertyRegistry r) {
        r.add("spring.datasource.url",DB::getJdbcUrl);r.add("spring.datasource.username",DB::getUsername);r.add("spring.datasource.password",DB::getPassword);
    }
    @Autowired LearningService service;
    @Autowired StudentModelProjectionService projection;
    @Autowired ObjectMapper json;
    @Autowired MockMvc mvc;
    @Autowired JdbcTemplate jdbc;
    @Autowired PlatformTransactionManager tx;
    ProgramDto fixture(String name)throws Exception {try(var in=getClass().getResourceAsStream("/evaluation/"+name+".json")){return json.readValue(in,ProgramDto.class);}}
    SubmitAttempt request(UUID id,String level,String fixture,int hints,long time)throws Exception{return new SubmitAttempt(id,level,time,hints,fixture(fixture));}
    SubmitAttempt fail(UUID id)throws Exception {return new SubmitAttempt(id,"SEQUENCES",1000L,0,json.readValue("{\"contractVersion\":1,\"name\":\"p\",\"statements\":[{\"kind\":\"move\"}]}",ProgramDto.class));}
    ConceptMastery sequences(UUID id){return projection.project(id).getConceptMasteries().getFirst();}
    boolean unlocked(UUID id,String level){return projection.progress(projection.project(id)).levels().stream().filter(p->p.levelId().equals(level)).findFirst().orElseThrow().unlocked();}
    void openLoops(UUID id)throws Exception {for(String level:List.of("SEQUENCES","VARIABLES","CONDITIONALS"))service.submit(request(id,level,level,0,1000));}
    @Test void seedAndStudentCreation()throws Exception {
        var response=mvc.perform(post("/api/students").contentType("application/json").content("{}")).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        UUID id=UUID.fromString(json.readTree(response).path("id").asText());var m=projection.project(id);
        assertEquals(4,m.getConceptMasteries().size());assertEquals(4,m.getConcepts().size());assertEquals(4,m.getActivities().size());assertEquals(15,m.getErrorPatterns().size());
        assertTrue(m.getConceptMasteries().stream().allMatch(c->c.getAttemptCount()==0&&c.getMasteryScore()==0));
        assertTrue(unlocked(id,"SEQUENCES"));assertFalse(unlocked(id,"VARIABLES"));
        mvc.perform(get("/api/students/"+id+"/model")).andExpect(status().isOk()).andExpect(jsonPath("$.modelVersion").value(1));
        mvc.perform(get("/api/students/"+id+"/progress")).andExpect(status().isOk()).andExpect(jsonPath("$.levels.length()").value(4));
        assertEquals(8,jdbc.queryForObject("select count(*) from flyway_schema_history where success",Integer.class));
        assertEquals(0,jdbc.queryForObject("select count(*) from hint_usages",Integer.class));
    }
    @Test void twoStudentsAreIndependentAndSnapshotsRoundTrip()throws Exception {
        UUID a=service.create("Estudiante A").id(),b=service.create("Estudiante B").id();
        service.submit(request(a,"SEQUENCES","SEQUENCES",0,1000));service.submit(fail(b));
        assertEquals(1,sequences(a).getSuccessCount());assertEquals(0,sequences(b).getSuccessCount());assertTrue(sequences(a).getMasteryScore()>sequences(b).getMasteryScore());
        assertTrue(unlocked(a,"VARIABLES"));assertFalse(unlocked(b,"VARIABLES"));
        roundTrip(projection.project(a),"student-a.learning");roundTrip(projection.project(b),"student-b.learning");
    }
    void roundTrip(StudentModel model,String name)throws Exception {
        LearningModels.validate(model);Files.createDirectories(Path.of("target/learning-snapshots"));var path=Path.of("target/learning-snapshots",name).toAbsolutePath();
        var set=new ResourceSetImpl();set.getPackageRegistry().put(LearningPackage.eNS_URI,LearningPackage.eINSTANCE);set.getResourceFactoryRegistry().getExtensionToFactoryMap().put("learning",new XMIResourceFactoryImpl());
        var resource=set.createResource(URI.createFileURI(path.toString()));resource.getContents().add(model);resource.save(Map.of());resource.unload();
        var fresh=new ResourceSetImpl();fresh.getPackageRegistry().put(LearningPackage.eNS_URI,LearningPackage.eINSTANCE);fresh.getResourceFactoryRegistry().getExtensionToFactoryMap().put("learning",new XMIResourceFactoryImpl());
        var loaded=(StudentModel)fresh.getResource(resource.getURI(),true).getContents().getFirst();EcoreUtil.resolveAll(fresh);LearningModels.validate(loaded);
        assertEquals(model.getStudent().getId(),loaded.getStudent().getId());assertSame(loaded.getStudent(),loaded.getConceptMasteries().getFirst().getStudent());
        assertSame(loaded.getStudent(),loaded.getAttempts().getFirst().getStudent());assertTrue(loaded.getActivities().contains(loaded.getAttempts().getFirst().getActivity()));
        assertTrue(loaded.getErrorPatterns().containsAll(loaded.getAttempts().getFirst().getErrorPatterns()));assertTrue(loaded.getActivities().contains(loaded.getProgress().getFirst().getActivity()));
        assertEquals(model.getConceptMasteries().getFirst().getAttemptCount(),loaded.getConceptMasteries().getFirst().getAttemptCount());
    }
    @Test void hintSuccessUnlockAndMonotonicProgress()throws Exception {
        var id=service.create(null).id();assertEquals(.05,service.submit(request(id,"SEQUENCES","SEQUENCES",2,1000)).masteryUpdate().after());
        assertTrue(unlocked(id,"VARIABLES"));service.submit(fail(id));service.submit(fail(id));
        assertEquals(0,sequences(id).getMasteryScore());assertTrue(unlocked(id,"VARIABLES"));assertTrue(projection.project(id).getProgress().getFirst().isCompleted());
        assertNotNull(projection.project(id).getProgress().getFirst().getCompletedAt());
    }
    @Test void repeatedPatternsCountersAverageAndConceptSeparation()throws Exception {
        var id=service.create(null).id();var first=service.submit(fail(id));var second=service.submit(fail(id));
        assertEquals(-.03,first.masteryUpdate().delta());assertEquals(-.05,second.masteryUpdate().delta());assertEquals(2,sequences(id).getConsecutiveFailures());
        assertEquals(List.of("MISSING_ACTION"),sequences(id).getRecentErrorPatterns().stream().map(ErrorPattern::getId).toList());
        service.submit(request(id,"SEQUENCES","SEQUENCES",0,3000));assertEquals(0,sequences(id).getConsecutiveFailures());assertEquals(5000.0/3,sequences(id).getAverageResolutionTime(),1e-9);
        assertTrue(projection.project(id).getConceptMasteries().get(1).getRecentErrorPatterns().isEmpty());
        service.submit(fail(id));assertEquals(1,sequences(id).getConsecutiveFailures());assertEquals(4,sequences(id).getAttemptCount());
        assertEquals(3,jdbc.queryForObject("select count(*) from attempt_error_patterns p join attempts a on a.id=p.attempt_id where a.student_id=?",Integer.class,id));
    }
    @Test void linearGraphAndPhaseFiveRegression()throws Exception {
        var id=service.create(null).id();openLoops(id);assertTrue(unlocked(id,"LOOPS"));
        var manual=service.submit(request(id,"LOOPS","LOOPS_MANUAL",0,1000));assertTrue(manual.execution().success());assertFalse(manual.execution().evaluation().activityPassed());
        assertEquals("REPETITIVE_SEQUENCE_WITHOUT_LOOP",manual.execution().evaluation().patterns().getFirst().id());
        var loops=projection.project(id).getConceptMasteries().get(3);assertEquals(1,loops.getFailureCount());assertEquals(0,loops.getSuccessCount());
        var proper=service.submit(request(id,"LOOPS","LOOPS",0,1000));assertTrue(proper.execution().evaluation().activityPassed());assertTrue(proper.progress().levels().get(3).completed());
    }
    @Test void persistenceFailureRollsBackAllChanges()throws Exception {
        var id=service.create(null).id();var req=request(id,"SEQUENCES","SEQUENCES",0,1000);
        assertThrows(IllegalStateException.class,()->new TransactionTemplate(tx).execute(status->{service.submit(req);throw new IllegalStateException("simulated persistence transaction failure");}));
        assertEquals(0,sequences(id).getAttemptCount());assertTrue(projection.project(id).getAttempts().isEmpty());assertFalse(unlocked(id,"VARIABLES"));
    }
    @Test void concurrentAttemptsDoNotLoseUpdates()throws Exception {
        var id=service.create(null).id();var req=request(id,"SEQUENCES","SEQUENCES",0,1000);var ready=new CountDownLatch(2);var start=new CountDownLatch(1);
        try(var pool=Executors.newFixedThreadPool(2)) {
            Callable<AttemptResponse> task=()->{ready.countDown();start.await();return service.submit(req);};var a=pool.submit(task);var b=pool.submit(task);assertTrue(ready.await(10,TimeUnit.SECONDS));start.countDown();
            assertNotEquals(a.get(20,TimeUnit.SECONDS).attemptId(),b.get(20,TimeUnit.SECONDS).attemptId());
        }
        assertEquals(2,sequences(id).getAttemptCount());assertEquals(.2,sequences(id).getMasteryScore());assertEquals(2,projection.project(id).getAttempts().size());
    }
    @Test void attemptApiValidationAndStatelessRegression()throws Exception {
        var id=service.create(null).id();var req=request(id,"SEQUENCES","SEQUENCES",0,1000);
        mvc.perform(post("/api/attempts").contentType("application/json").content(json.writeValueAsString(req))).andExpect(status().isCreated()).andExpect(jsonPath("$.execution.evaluation.activityPassed").value(true));
        mvc.perform(get("/api/students/"+UUID.randomUUID()+"/model")).andExpect(status().isNotFound());
        mvc.perform(get("/api/students/"+UUID.randomUUID()+"/progress")).andExpect(status().isNotFound());
        mvc.perform(post("/api/attempts").contentType("application/json").content(json.writeValueAsString(request(UUID.randomUUID(),"SEQUENCES","SEQUENCES",0,1)))).andExpect(status().isNotFound());
        mvc.perform(post("/api/attempts").contentType("application/json").content(json.writeValueAsString(request(id,"UNKNOWN","SEQUENCES",0,1)))).andExpect(status().isNotFound());
        for(long time:new long[]{-1,86400001})mvc.perform(post("/api/attempts").contentType("application/json").content(json.writeValueAsString(request(id,"SEQUENCES","SEQUENCES",0,time)))).andExpect(status().isBadRequest());
        var invalid=json.valueToTree(req);((com.fasterxml.jackson.databind.node.ObjectNode)invalid).set("program",json.readTree("{}"));
        mvc.perform(post("/api/attempts").contentType("application/json").content(json.writeValueAsString(invalid))).andExpect(status().isBadRequest());
        var emfInvalid=json.readTree("{\"contractVersion\":1,\"name\":\"p\",\"statements\":[{\"kind\":\"variableDeclaration\",\"declarationId\":\"v\",\"name\":null,\"valueType\":\"INTEGER\"}]}");
        ((com.fasterxml.jackson.databind.node.ObjectNode)invalid).set("program",emfInvalid);
        mvc.perform(post("/api/attempts").contentType("application/json").content(json.writeValueAsString(invalid))).andExpect(status().isUnprocessableEntity());
        assertEquals(1,sequences(id).getAttemptCount());
        mvc.perform(post("/api/game/levels/SEQUENCES/execute").contentType("application/json").content(json.writeValueAsString(fixture("SEQUENCES")))).andExpect(status().isOk()).andExpect(jsonPath("$.evaluation.activityPassed").value(true));
    }
    @Test void lockedActivityCannotBeRecorded()throws Exception {
        var id=service.create(null).id();mvc.perform(post("/api/attempts").contentType("application/json").content(json.writeValueAsString(request(id,"LOOPS","LOOPS",0,1)))).andExpect(status().isConflict());assertTrue(projection.project(id).getAttempts().isEmpty());
    }
    @Test void modelUtilitiesRejectInvalidCountersAndReferences() {
        var id=service.create(null).id();var m=projection.project(id);m.getConceptMasteries().getFirst().setMasteryScore(1.1);assertThrows(IllegalArgumentException.class,()->LearningModels.validate(m));
        m.getConceptMasteries().getFirst().setMasteryScore(0);m.getConceptMasteries().getFirst().setAttemptCount(1);assertThrows(IllegalArgumentException.class,()->LearningModels.validate(m));
        m.getConceptMasteries().getFirst().setAttemptCount(0);m.getConcepts().get(1).setId(m.getConcepts().getFirst().getId());assertThrows(IllegalArgumentException.class,()->LearningModels.validate(m));
    }
}
