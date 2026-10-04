package com.project.programming;

import com.project.programming.api.ProgrammingModelController;
import com.project.programming.application.ProgrammingModelMapper;
import com.project.programming.application.ProgrammingModelService;
import java.nio.file.Files;
import java.nio.file.Path;
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

@WebMvcTest(controllers = ProgrammingModelController.class, properties = "spring.config.import=")
@Import({com.project.shared.security.TeacherSecurityConfiguration.class,ProgrammingModelMapper.class, ProgrammingModelService.class})
@org.springframework.security.test.context.support.WithMockUser(roles="TEACHER")
class ProgrammingModelControllerTest {
    private static org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder post(String path,Object... args){return org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post(path,args).with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf());}
    @Autowired MockMvc mvc;
    @Test void successIsJsonWithXmi() throws Exception {
        mvc.perform(post("/api/programming/models").contentType(MediaType.APPLICATION_JSON)
            .content(Files.readString(Path.of("../contracts/programming/v1/variables.json"))))
            .andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.valid").value(true)).andExpect(jsonPath("$.xmi").isString())
            .andExpect(jsonPath("$.summary.statementCount").value(2));
    }
    @ParameterizedTest @ValueSource(strings = {
        "{}", "{broken", "{\"contractVersion\":2,\"name\":\"p\",\"statements\":[]}",
        "{\"contractVersion\":1,\"name\":\"p\",\"statements\":[{\"kind\":\"unknown\"}]}",
        "{\"contractVersion\":1,\"name\":\"p\",\"statements\":[{\"kind\":\"assignment\",\"targetDeclarationId\":\"absent\",\"value\":{\"kind\":\"literal\",\"valueType\":\"INTEGER\",\"value\":\"1\"}}]}"
    })
    void badRequestsAreControlled(String request) throws Exception {
        mvc.perform(post("/api/programming/models").contentType(MediaType.APPLICATION_JSON).content(request))
            .andExpect(status().isBadRequest()).andExpect(jsonPath("$.valid").value(false))
            .andExpect(jsonPath("$.diagnostics[0].code").isString()).andExpect(jsonPath("$.trace").doesNotExist());
    }
    @Test void emfStructuralFailureIs422() throws Exception {
        mvc.perform(post("/api/programming/models").contentType(MediaType.APPLICATION_JSON).content("""
            {"contractVersion":1,"name":"p","statements":[
              {"kind":"variableDeclaration","declarationId":"v","name":null,"valueType":"INTEGER"}]}
            """))
            .andExpect(status().isUnprocessableEntity()).andExpect(jsonPath("$.valid").value(false))
            .andExpect(jsonPath("$.diagnostics[0].code").isString()).andExpect(jsonPath("$.xmi").isEmpty());
    }
}
