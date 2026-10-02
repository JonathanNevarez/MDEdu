package com.project.evaluation;
import com.project.evaluation.application.SolutionEvaluationService;
import com.project.evaluation.catalog.PatternCatalog;
import com.project.execution.api.GameController;
import com.project.execution.application.*;
import com.project.programming.application.ProgrammingModelMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers=GameController.class,properties="spring.config.import=")
@Import({LevelCatalog.class,GameExecutionService.class,ProgrammingModelMapper.class,SolutionEvaluationService.class,PatternCatalog.class})
class EvaluationPipelineTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper json;
    String fixture(String id)throws Exception {try(var in=getClass().getResourceAsStream("/evaluation/"+id+".json")){return new String(in.readAllBytes(),java.nio.charset.StandardCharsets.UTF_8);}}
    String request(String level,String input)throws Exception {
        return mvc.perform(post("/api/game/levels/"+level+"/execute").contentType(MediaType.APPLICATION_JSON).content(input))
            .andExpect(status().isOk()).andExpect(jsonPath("$.evaluation.evaluationVersion").value(1)).andReturn().getResponse().getContentAsString();
    }
    @ParameterizedTest @ValueSource(strings={"SEQUENCES","VARIABLES","CONDITIONALS","LOOPS"})
    void fourRealPipelinesPassAndAreDeterministic(String id)throws Exception {
        String a=request(id,fixture(id)),b=request(id,fixture(id));assertEquals(a,b);
        var r=json.readTree(a);assertTrue(r.path("success").asBoolean());assertTrue(r.at("/evaluation/activityPassed").asBoolean());
        assertTrue(r.at("/evaluation/structuralCorrectness/requiredConceptUsed").asBoolean());assertEquals(0,r.at("/evaluation/patterns").size());
        int starts=0;for(var event:r.path("trace"))if(event.path("type").asText().equals("PROGRAM_STARTED"))starts++;assertEquals(1,starts);
    }
    @Test void manualLoopsReachGoalButDoNotPass()throws Exception {
        var a=request("LOOPS",fixture("LOOPS_MANUAL"));assertEquals(a,request("LOOPS",fixture("LOOPS_MANUAL")));var r=json.readTree(a);
        assertTrue(r.path("success").asBoolean());assertTrue(r.at("/evaluation/functionalCorrectness/passed").asBoolean());
        assertFalse(r.at("/evaluation/activityPassed").asBoolean());assertFalse(r.at("/evaluation/structuralCorrectness/requiredConceptUsed").asBoolean());
        assertEquals("REPETITIVE_SEQUENCE_WITHOUT_LOOP",r.at("/evaluation/patterns/0/id").asText());
        assertEquals("statements[0]",r.at("/evaluation/patterns/0/evidence/statementPath").asText());
    }
    @Test void runtimeErrorStillEvaluated()throws Exception {
        var r=json.readTree(request("SEQUENCES","""
            {"contractVersion":1,"name":"p","statements":[{"kind":"turnLeft"},{"kind":"move"},{"kind":"move"}]}
            """));assertEquals("RUNTIME_ERROR",r.path("status").asText());assertFalse(r.at("/evaluation/activityPassed").asBoolean());assertTrue(r.at("/evaluation/functionalCorrectness/runtimeError").asBoolean());
    }
    @Test void stepLimitEvaluatedWithAttribution()throws Exception {
        var r=json.readTree(request("LOOPS","""
            {"contractVersion":1,"name":"p","statements":[{"kind":"while","condition":{"kind":"literal","valueType":"BOOLEAN","value":"true"},"body":[]}]}
            """));assertEquals("STEP_LIMIT_EXCEEDED",r.path("status").asText());assertFalse(r.at("/evaluation/activityPassed").asBoolean());
        assertEquals("POSSIBLE_INFINITE_LOOP",r.at("/evaluation/patterns/0/id").asText());assertTrue(r.at("/evaluation/patterns/0/evidence/traceIndex").isInt());
    }
    @Test void invalidInputsDoNotEnterEvaluator()throws Exception {
        mvc.perform(post("/api/game/levels/LOOPS/execute").contentType(MediaType.APPLICATION_JSON).content("{}"))
            .andExpect(status().isBadRequest()).andExpect(jsonPath("$.evaluation").doesNotExist());
        mvc.perform(post("/api/game/levels/VARIABLES/execute").contentType(MediaType.APPLICATION_JSON).content("""
            {"contractVersion":1,"name":"p","statements":[{"kind":"variableDeclaration","declarationId":"v","name":null,"valueType":"INTEGER"}]}
            """)).andExpect(status().isUnprocessableEntity()).andExpect(jsonPath("$.evaluation").isEmpty());
    }
}
