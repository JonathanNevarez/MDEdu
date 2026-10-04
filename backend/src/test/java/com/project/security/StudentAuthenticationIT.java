package com.project.security;
import java.util.*;
import java.util.concurrent.*;
import com.fasterxml.jackson.databind.*;
import com.project.shared.security.StudentAccounts;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.*;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@Testcontainers @SpringBootTest(properties={"spring.config.import=","LLM_PROVIDER=DISABLED"}) @AutoConfigureMockMvc
class StudentAuthenticationIT {
 @Container static final PostgreSQLContainer<?> DB=new PostgreSQLContainer<>("postgres:17-bookworm");
 @DynamicPropertySource static void db(DynamicPropertyRegistry r){r.add("spring.datasource.url",DB::getJdbcUrl);r.add("spring.datasource.username",DB::getUsername);r.add("spring.datasource.password",DB::getPassword);}
 @Autowired MockMvc mvc;@Autowired ObjectMapper json;@Autowired StudentAccounts accounts;@Autowired JdbcTemplate jdbc;
 record Csrf(MockHttpSession session,String token){}
 Csrf token(MockHttpSession s)throws Exception{var r=get("/api/auth/student/csrf");if(s!=null)r.session(s);var x=mvc.perform(r).andExpect(status().isOk()).andReturn();return new Csrf((MockHttpSession)x.getRequest().getSession(),json.readTree(x.getResponse().getContentAsString()).path("token").asText());}
 MockHttpSession login(StudentAccounts.Issued issued)throws Exception{var c=token(null);String old=c.session().getId();var r=mvc.perform(post("/api/auth/student/login").session(c.session()).header("X-CSRF-TOKEN",c.token()).contentType("application/json").content(json.writeValueAsString(Map.of("studentCode"," "+issued.participant().studentCode().toLowerCase(Locale.ROOT)+" ","password",issued.temporaryPassword())))).andExpect(status().isOk()).andExpect(jsonPath("$.studentId").value(issued.participant().studentId().toString())).andExpect(jsonPath("$.passwordHash").doesNotExist()).andReturn();assertNotEquals(old,r.getRequest().getSession().getId());return (MockHttpSession)r.getRequest().getSession();}
 @Test void crossDevicePreservesExactModelProgressAndHistory()throws Exception{
  var account=accounts.create();var a=login(account);var c=token(a);var program=json.readTree(getClass().getResourceAsStream("/evaluation/SEQUENCES.json"));
  mvc.perform(post("/api/attempts").session(a).header("X-CSRF-TOKEN",c.token()).contentType("application/json").content(json.writeValueAsString(Map.of("studentId",account.participant().studentId(),"levelId","SEQUENCES","program",program,"hintCount",0,"resolutionTimeMs",1000)))).andExpect(status().isCreated()).andExpect(jsonPath("$.execution.evaluation.activityPassed").value(true));
  String base="/api/students/"+account.participant().studentId();
  String model=mvc.perform(get(base+"/model").session(a)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
  String progress=mvc.perform(get(base+"/progress").session(a)).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
  mvc.perform(post("/api/auth/student/logout").session(a).header("X-CSRF-TOKEN",c.token())).andExpect(status().isNoContent());assertTrue(a.isInvalid());
  var b=login(account);mvc.perform(get(base+"/model").session(b)).andExpect(content().json(model));mvc.perform(get(base+"/progress").session(b)).andExpect(content().json(progress));
  assertEquals(1,jdbc.queryForObject("select count(*) from attempts where student_id=?",Integer.class,account.participant().studentId()));
  mvc.perform(get("/api/auth/student/me").session(b)).andExpect(jsonPath("$.studentCode").value(account.participant().studentCode()));
 }
 @Test void isolationCoversReadsWritesUiSessionsAndFeedback()throws Exception{
  var a=accounts.create();var b=accounts.create();var s=login(a);var c=token(s);UUID id=b.participant().studentId();
  for(String suffix:List.of("/model","/progress","/activities/SEQUENCES/ui-configuration"))mvc.perform(get("/api/students/"+id+suffix).session(s)).andExpect(status().isForbidden());
  for(String path:List.of("/api/sessions","/api/feedback/generate"))mvc.perform(post(path).session(s).header("X-CSRF-TOKEN",c.token()).contentType("application/json").content(json.writeValueAsString(Map.of("studentId",id,"attemptId",UUID.randomUUID())))).andExpect(status().isForbidden());
  var program=json.readTree(getClass().getResourceAsStream("/evaluation/SEQUENCES.json"));
  mvc.perform(post("/api/attempts").session(s).header("X-CSRF-TOKEN",c.token()).contentType("application/json").content(json.writeValueAsString(Map.of("studentId",id,"levelId","SEQUENCES","program",program,"hintCount",0,"resolutionTimeMs",1000)))).andExpect(status().isForbidden());
  mvc.perform(get("/api/feedback/"+UUID.randomUUID()).session(s)).andExpect(status().isForbidden());
  mvc.perform(get("/api/teacher/participants").session(s)).andExpect(status().isForbidden());
 }
 @Test void teacherProvisioningAndBcryptAreSafe()throws Exception{
  var result=mvc.perform(post("/api/teacher/participants").with(user("teacher").roles("TEACHER")).with(csrf())).andExpect(status().isCreated()).andExpect(header().string("Cache-Control","no-store")).andReturn();
  var issued=json.readTree(result.getResponse().getContentAsString());String id=issued.path("participant").path("studentId").asText();String password=issued.path("temporaryPassword").asText();assertTrue(password.length()>=24);
  String hash=jdbc.queryForObject("select password_hash from student_accounts where student_id=?",String.class,UUID.fromString(id));assertNotEquals(password,hash);assertTrue(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder().matches(password,hash));
  mvc.perform(get("/api/teacher/participants").with(user("teacher").roles("TEACHER"))).andExpect(status().isOk()).andExpect(content().string(org.hamcrest.Matchers.not(org.hamcrest.Matchers.containsString(password)))).andExpect(content().string(org.hamcrest.Matchers.not(org.hamcrest.Matchers.containsString(hash))));
  mvc.perform(get("/api/students/"+id+"/model").with(user("teacher").roles("TEACHER"))).andExpect(status().isOk());
 }
 @Test void resetInvalidatesEveryPreviouslyAuthenticatedDevice()throws Exception{
  var issued=accounts.create();var a=login(issued);var b=login(issued);var replacement=accounts.reset(issued.participant().studentId());
  for(var s:List.of(a,b))mvc.perform(get("/api/auth/student/me").session(s)).andExpect(status().isUnauthorized());
  assertNull(accounts.authenticate(issued.participant().studentCode(),issued.temporaryPassword()));login(replacement);
 }
 @Test void disabledAccountAndReenableDoNotResurrectSessions()throws Exception{
  var issued=accounts.create();var s=login(issued);accounts.enable(issued.participant().studentId(),false);
  assertNull(accounts.authenticate(issued.participant().studentCode(),issued.temporaryPassword()));mvc.perform(get("/api/auth/student/me").session(s)).andExpect(status().isUnauthorized());
  accounts.enable(issued.participant().studentId(),true);login(issued);
 }
 @Test void csrfLogoutAndAnonymousAccessFailClosed()throws Exception{
  var issued=accounts.create();mvc.perform(post("/api/auth/student/login").contentType("application/json").content(json.writeValueAsString(Map.of("studentCode",issued.participant().studentCode(),"password",issued.temporaryPassword())))).andExpect(status().isForbidden());
  var s=login(issued);mvc.perform(post("/api/auth/student/logout").session(s)).andExpect(status().isForbidden());mvc.perform(get("/api/auth/student/me").session(s)).andExpect(status().isOk());
  mvc.perform(get("/api/students/"+issued.participant().studentId()+"/progress")).andExpect(status().isUnauthorized());
  mvc.perform(post("/api/students").with(csrf()).contentType("application/json").content("{}")).andExpect(status().isUnauthorized());
  mvc.perform(post("/api/teacher/participants").session(s).with(csrf())).andExpect(status().isForbidden());
  s.invalidate();mvc.perform(get("/api/auth/student/me")).andExpect(status().isUnauthorized());
 }
 @Test void nonexistentAndWrongPasswordHaveSameResponseAndAreRateLimited()throws Exception{
  var issued=accounts.create();var c=token(null);String first=null;
  for(int i=0;i<5;i++){final int n=i;var r=mvc.perform(post("/api/auth/student/login").with(req->{req.setRemoteAddr("192.0.2.55");return req;}).session(c.session()).header("X-CSRF-TOKEN",c.token()).contentType("application/json").content(json.writeValueAsString(Map.of("studentCode",n%2==0?issued.participant().studentCode():"EST-999999","password","wrong")))).andExpect(status().isUnauthorized()).andReturn().getResponse().getContentAsString();if(first==null)first=r;else assertEquals(first,r);}
  mvc.perform(post("/api/auth/student/login").with(req->{req.setRemoteAddr("192.0.2.55");return req;}).session(c.session()).header("X-CSRF-TOKEN",c.token()).contentType("application/json").content("{\"studentCode\":\"EST-999999\",\"password\":\"wrong\"}")).andExpect(status().isTooManyRequests());
 }
 @Test void concurrentProvisioningHasUniqueCodesAndExactlyOneStudentPerAccount()throws Exception{
  try(var pool=Executors.newFixedThreadPool(3)){var tasks=new ArrayList<Callable<StudentAccounts.Issued>>();for(int i=0;i<6;i++)tasks.add(accounts::create);var codes=new HashSet<String>();var ids=new HashSet<UUID>();for(var f:pool.invokeAll(tasks)){var p=f.get().participant();assertTrue(codes.add(p.studentCode()));assertTrue(ids.add(p.studentId()));assertEquals(1,jdbc.queryForObject("select count(*) from students where id=?",Integer.class,p.studentId()));}}
 }
}
