package com.project.security;
import com.project.shared.security.TeacherSecurityConfiguration;
import com.project.llm.provider.LlmRateLimiter;
import org.junit.jupiter.api.Test;
import org.springframework.mock.env.MockEnvironment;
import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;
class SecurityPolicyTest {
 @Test void productionRejectsFakeProvider(){
  var settings=new com.project.llm.provider.LlmSettings(new MockEnvironment().withProperty("LLM_PROVIDER","FAKE"));
  assertThrows(IllegalStateException.class,()->new com.project.shared.security.ProductionLlmGuard(settings));
  assertDoesNotThrow(()->new com.project.shared.security.ProductionLlmGuard(new com.project.llm.provider.LlmSettings(new MockEnvironment())));
 }
 @Test void quotaIsThreadSafeAndBounded()throws Exception{
  var quota=new LlmRateLimiter(3,60,5,2,Clock.systemUTC());var student=UUID.randomUUID();
  try(var pool=Executors.newFixedThreadPool(4)){var jobs=new ArrayList<Future<Boolean>>();for(int i=0;i<20;i++)jobs.add(pool.submit(()->{try(var scope=LlmRateLimiter.forStudent(student)){return quota.acquire();}}));int allowed=0;for(var job:jobs)if(job.get())allowed++;assertEquals(3,allowed);}
  try(var scope=LlmRateLimiter.forStudent(UUID.randomUUID())){assertTrue(quota.acquire());}
  try(var scope=LlmRateLimiter.forStudent(UUID.randomUUID())){assertFalse(quota.acquire());}
  assertEquals(2,quota.trackedKeys());
 }
 @Test void quotaWindowExpiresAndOwnerIsRestored(){
  var now=new java.util.concurrent.atomic.AtomicLong(0);Clock clock=new Clock(){public ZoneId getZone(){return ZoneOffset.UTC;}public Clock withZone(ZoneId z){return this;}public Instant instant(){return Instant.ofEpochMilli(now.get());}};
  var quota=new LlmRateLimiter(1,2,2,1,clock);try(var scope=LlmRateLimiter.forStudent(UUID.randomUUID())){assertTrue(quota.acquire());assertFalse(quota.acquire());now.set(2001);assertTrue(quota.acquire());}
 }
 @Test void missingAndWeakCredentialsFailClosed()throws Exception{
  var method=TeacherSecurityConfiguration.class.getDeclaredMethod("teacherUsers",org.springframework.core.env.Environment.class);method.setAccessible(true);
  for(var env:List.of(new MockEnvironment(),new MockEnvironment().withProperty("META_UI_USERNAME","admin").withProperty("META_UI_PASSWORD","admin"))){var users=(org.springframework.security.core.userdetails.UserDetailsService)method.invoke(new TeacherSecurityConfiguration(),env);assertThrows(org.springframework.security.core.userdetails.UsernameNotFoundException.class,()->users.loadUserByUsername("admin"));}
 }
}
