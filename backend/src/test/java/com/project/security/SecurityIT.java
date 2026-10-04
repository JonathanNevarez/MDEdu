package com.project.security;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.core.env.Environment;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@Testcontainers @SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT,properties={"spring.config.import=","LLM_PROVIDER=DISABLED","server.servlet.session.cookie.secure=true"})
@AutoConfigureMockMvc @TestPropertySource(locations="classpath:teacher-test.properties")
class SecurityIT {
 @Container static final PostgreSQLContainer<?> DB=new PostgreSQLContainer<>("postgres:17-bookworm");
 @DynamicPropertySource static void db(DynamicPropertyRegistry r){r.add("spring.datasource.url",DB::getJdbcUrl);r.add("spring.datasource.username",DB::getUsername);r.add("spring.datasource.password",DB::getPassword);}
 @Autowired MockMvc mvc;@Autowired ObjectMapper json;@Autowired Environment env;
 @org.springframework.boot.test.web.server.LocalServerPort int port;
 record Csrf(MockHttpSession session,String token){}
 Csrf csrf(MockHttpSession session)throws Exception{var request=get("/api/teacher/csrf");if(session!=null)request.session(session);var result=mvc.perform(request).andExpect(status().isOk()).andReturn();return new Csrf((MockHttpSession)result.getRequest().getSession(),json.readTree(result.getResponse().getContentAsString()).path("token").asText());}
 MockHttpSession login()throws Exception{var token=csrf(null);var result=mvc.perform(post("/api/teacher/login").session(token.session()).header("X-CSRF-TOKEN",token.token()).param("username",env.getProperty("META_UI_USERNAME")).param("password",env.getProperty("META_UI_PASSWORD"))).andExpect(status().isNoContent()).andReturn();return (MockHttpSession)result.getRequest().getSession();}
 @Test void teacherEndpointsRequireAuthentication()throws Exception{
  for(String path:List.of("/api/meta/students","/api/adaptation/rules","/api/adaptation/parameters","/api/students/"+UUID.randomUUID()+"/attempts/"+UUID.randomUUID()+"/timeline","/api/sessions/"+UUID.randomUUID()+"/timeline?studentId="+UUID.randomUUID()))mvc.perform(get(path)).andExpect(status().isUnauthorized()).andExpect(jsonPath("$.code").value("UNAUTHORIZED"));
  mvc.perform(get("/api/game/levels")).andExpect(status().isOk());
 }
 @Test void loginCsrfWrongPasswordAndRealLogout()throws Exception{
  mvc.perform(post("/api/teacher/login").param("username",env.getProperty("META_UI_USERNAME")).param("password",env.getProperty("META_UI_PASSWORD"))).andExpect(status().isForbidden());
  var token=csrf(null);
  mvc.perform(post("/api/teacher/login").session(token.session()).header("X-CSRF-TOKEN",token.token()).param("username",env.getProperty("META_UI_USERNAME")).param("password","wrong")).andExpect(status().isUnauthorized()).andExpect(jsonPath("$.message").value("Se requiere una sesión válida."));
  mvc.perform(get("/api/meta/students").session(token.session())).andExpect(status().isUnauthorized());
  var session=login();mvc.perform(get("/api/meta/students").session(session)).andExpect(status().isOk());
  mvc.perform(post("/api/teacher/logout").session(session)).andExpect(status().isForbidden());
  var logout=csrf(session);mvc.perform(post("/api/teacher/logout").session(session).header("X-CSRF-TOKEN",logout.token())).andExpect(status().isNoContent());assertTrue(session.isInvalid());
  mvc.perform(get("/api/meta/students")).andExpect(status().isUnauthorized());
 }
 @Test void actualSessionCookieHasProductionFlags()throws Exception{
  var request=java.net.http.HttpRequest.newBuilder(java.net.URI.create("http://127.0.0.1:"+port+"/api/teacher/csrf")).GET().build();
  var response=java.net.http.HttpClient.newHttpClient().send(request,java.net.http.HttpResponse.BodyHandlers.ofString());assertEquals(200,response.statusCode());
  String cookie=response.headers().firstValue("set-cookie").orElseThrow();assertTrue(cookie.contains("HttpOnly"));assertTrue(cookie.contains("Secure"));assertTrue(cookie.contains("SameSite=Strict"));
 }
 @Test void corsAndSecurityHeaders()throws Exception{
  mvc.perform(get("/api/teacher/csrf").header("Origin","http://127.0.0.1:5173")).andExpect(status().isOk()).andExpect(header().string("Access-Control-Allow-Origin","http://127.0.0.1:5173")).andExpect(header().string("Access-Control-Allow-Credentials","true"));
  mvc.perform(options("/api/meta/students").header("Origin","https://evil.invalid").header("Access-Control-Request-Method","GET")).andExpect(status().isForbidden()).andExpect(header().doesNotExist("Access-Control-Allow-Origin"));
  mvc.perform(get("/api/game/levels")).andExpect(header().string("X-Content-Type-Options","nosniff")).andExpect(header().string("X-Frame-Options","DENY")).andExpect(header().string("Referrer-Policy","no-referrer")).andExpect(header().exists("Content-Security-Policy"));
 }
 @Test void malformedInputsAndPayloadLimitsHaveSafeEnvelopes()throws Exception{
  mvc.perform(post("/api/students").with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user("teacher").roles("TEACHER")).with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf()).contentType("application/json").content("{\"displayName\":\""+"x".repeat(262144)+"\"}")).andExpect(status().isPayloadTooLarge()).andExpect(jsonPath("$.code").value("VALIDATION_ERROR"));
  mvc.perform(get("/api/students/not-a-uuid/model").with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user("teacher").roles("TEACHER"))).andExpect(status().isBadRequest()).andExpect(jsonPath("$.requestId").isString()).andExpect(jsonPath("$.trace").doesNotExist());
  mvc.perform(post("/api/students").with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user("teacher").roles("TEACHER")).with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf()).contentType("application/json").content("{\"displayName\":\""+"x".repeat(81)+"\"}")).andExpect(status().isBadRequest());
  mvc.perform(post("/api/programming/models").with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user("teacher").roles("TEACHER")).with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf()).contentType("application/json").content("{\"contractVersion\":1,\"name\":\"test\",\"statements\":[{\"kind\":\"unsafe\"}]}")).andExpect(status().isBadRequest()).andExpect(jsonPath("$.httpStatus").value(400));
  var session=login();mvc.perform(get("/api/meta/students?size=999999").session(session)).andExpect(status().isBadRequest());
  mvc.perform(get("/api/meta/students?page=-1").session(session)).andExpect(status().isBadRequest());
  mvc.perform(post("/api/programming/models").with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user("teacher").roles("TEACHER")).with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf()).contentType("application/json").content("[".repeat(300)+"0"+"]".repeat(300))).andExpect(status().isBadRequest());
 }
 @Test void healthOnlyExposesStatus()throws Exception{
  for(String path:List.of("/actuator/health/liveness","/actuator/health/readiness"))mvc.perform(get(path)).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("UP")).andExpect(jsonPath("$.components").doesNotExist()).andExpect(jsonPath("$.details").doesNotExist());
 }
}
