package com.project.execution;
import com.project.execution.api.LaboratoryController;
import com.project.programming.application.ProgrammingModelMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(controllers=LaboratoryController.class,properties="spring.config.import=")
@Import({com.project.shared.security.TeacherSecurityConfiguration.class,ProgrammingModelMapper.class})
class LaboratoryControllerTest {
 @Autowired MockMvc mvc;
 private final String input="{\"contractVersion\":1,\"name\":\"sandbox\",\"statements\":[{\"kind\":\"move\"},{\"kind\":\"move\"},{\"kind\":\"move\"}]}";
 @Test void anonymousAndMissingCsrfFailClosed()throws Exception{
  mvc.perform(get("/api/laboratory/world")).andExpect(status().isUnauthorized());
  mvc.perform(post("/api/laboratory/execute").with(user("student").roles("STUDENT")).contentType("application/json").content(input)).andExpect(status().isForbidden());
 }
 @Test void sameInterpreterDeterministicAndNoGrading()throws Exception{
  mvc.perform(get("/api/laboratory/world").with(user("student").roles("STUDENT"))).andExpect(status().isOk()).andExpect(jsonPath("$.width").value(6));
  String first=null;
  for(int i=0;i<2;i++){
   String next=mvc.perform(post("/api/laboratory/execute").with(user("student").roles("STUDENT")).with(csrf()).contentType("application/json").content(input)).andExpect(status().isOk()).andExpect(jsonPath("$.finalState.playerPosition.x").value(4)).andExpect(jsonPath("$.finalState.doors[0].open").value(true)).andExpect(jsonPath("$.evaluation").doesNotExist()).andReturn().getResponse().getContentAsString();
   if(first!=null)assertEquals(first,next);first=next;
  }
 }
 @Test void unboundedLoopKeepsExistingBudget()throws Exception{
  mvc.perform(post("/api/laboratory/execute").with(user("student").roles("STUDENT")).with(csrf()).contentType("application/json").content("{\"contractVersion\":1,\"name\":\"p\",\"statements\":[{\"kind\":\"while\",\"condition\":{\"kind\":\"literal\",\"valueType\":\"BOOLEAN\",\"value\":\"true\"},\"body\":[]}]}")).andExpect(status().isOk()).andExpect(jsonPath("$.steps").value(200));
 }
 @Test void malformedContractReturnsSafeFailure()throws Exception{
  mvc.perform(post("/api/laboratory/execute").with(user("student").roles("STUDENT")).with(csrf()).contentType("application/json").content("{}")).andExpect(status().isBadRequest()).andExpect(jsonPath("$.errors[0].code").value("INVALID_CONTRACT"));
 }
}
