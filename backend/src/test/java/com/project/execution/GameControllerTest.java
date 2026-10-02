package com.project.execution;

import com.project.execution.api.GameController;
import com.project.execution.application.*;
import com.project.programming.application.ProgrammingModelMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(controllers=GameController.class,properties="spring.config.import=")
@Import({LevelCatalog.class,GameExecutionService.class,ProgrammingModelMapper.class})
class GameControllerTest {
    @Autowired MockMvc mvc;
    String path="/api/game/levels/SEQUENCES/execute";
    @Test void catalogAndLookup() throws Exception {
        mvc.perform(get("/api/game/levels")).andExpect(status().isOk()).andExpect(jsonPath("$.levels.length()").value(4));
        mvc.perform(get("/api/game/levels/SEQUENCES")).andExpect(status().isOk()).andExpect(jsonPath("$.title").value("Primeros pasos"));
        mvc.perform(get("/api/game/levels/missing")).andExpect(status().isNotFound());
        mvc.perform(post("/api/game/levels/missing/execute").contentType(MediaType.APPLICATION_JSON).content("{\"contractVersion\":1,\"name\":\"p\",\"statements\":[]}")).andExpect(status().isNotFound());
    }
    @Test void executionAndApiDeterminism() throws Exception {
        var input="""
            {"contractVersion":1,"name":"p","statements":[{"kind":"move"},{"kind":"move"},{"kind":"turnRight"},{"kind":"move"},{"kind":"move"}]}
            """;
        var first=mvc.perform(post(path).contentType(MediaType.APPLICATION_JSON).content(input)).andExpect(status().isOk()).andExpect(jsonPath("$.success").value(true)).andReturn().getResponse().getContentAsString();
        var second=mvc.perform(post(path).contentType(MediaType.APPLICATION_JSON).content(input)).andReturn().getResponse().getContentAsString();assertEquals(first,second);
    }
    @ParameterizedTest @ValueSource(strings={"{}","{broken","{\"contractVersion\":2,\"name\":\"p\",\"statements\":[]}"})
    void badRequest(String input)throws Exception {mvc.perform(post(path).contentType(MediaType.APPLICATION_JSON).content(input)).andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors[0].code").isString());}
    @Test void invalidEmf422()throws Exception {
        mvc.perform(post(path).contentType(MediaType.APPLICATION_JSON).content("""
            {"contractVersion":1,"name":"p","statements":[{"kind":"variableDeclaration","declarationId":"v","name":null,"valueType":"INTEGER"}]}
            """)).andExpect(status().isUnprocessableEntity()).andExpect(jsonPath("$.status").value("INVALID_MODEL"));
    }
    @Test void runtimeErrorsAndInfiniteLoop200()throws Exception {
        mvc.perform(post(path).contentType(MediaType.APPLICATION_JSON).content("""
            {"contractVersion":1,"name":"p","statements":[{"kind":"turnLeft"},{"kind":"move"},{"kind":"move"}]}
            """)).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("RUNTIME_ERROR")).andExpect(jsonPath("$.success").value(false));
        mvc.perform(post(path).contentType(MediaType.APPLICATION_JSON).content("""
            {"contractVersion":1,"name":"p","statements":[{"kind":"while","condition":{"kind":"literal","valueType":"BOOLEAN","value":"true"},"body":[]}]}
            """)).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("STEP_LIMIT_EXCEEDED")).andExpect(jsonPath("$.steps").value(200));
    }
}
