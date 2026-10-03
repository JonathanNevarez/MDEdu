package com.project.llm.provider;
import java.time.Clock;
import java.util.*;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;
/** Bounded single-instance quota, charged immediately before every HTTP attempt (including retries). */
@Component
public class LlmRateLimiter {
 private static final ThreadLocal<String> OWNER=new ThreadLocal<>();
 private record Window(long started,int used){}
 private final Map<String,Window> windows=new HashMap<>();
 private final int requests,globalRequests,maxKeys;private final long duration;private final Clock clock;
 private Window global;
 @org.springframework.beans.factory.annotation.Autowired public LlmRateLimiter(Environment env){this(
  env.getProperty("LLM_RATE_LIMIT_REQUESTS",Integer.class,60),env.getProperty("LLM_RATE_LIMIT_WINDOW_SECONDS",Integer.class,60),
  env.getProperty("LLM_RATE_LIMIT_GLOBAL_REQUESTS",Integer.class,600),1024,Clock.systemUTC());}
 public LlmRateLimiter(){this(60,60,600,1024,Clock.systemUTC());}
 public LlmRateLimiter(int requests,int seconds,int globalRequests,int maxKeys,Clock clock){
  if(requests<1||requests>10000||seconds<1||seconds>3600||globalRequests<1||maxKeys<1)throw new IllegalArgumentException("Invalid quota configuration");
  this.requests=requests;this.duration=seconds*1000L;this.globalRequests=globalRequests;this.maxKeys=maxKeys;this.clock=clock;
 }
 public static Scope forStudent(UUID student){String previous=OWNER.get();OWNER.set(student.toString());return new Scope(previous);}
 public static final class Scope implements AutoCloseable {private final String previous;private Scope(String p){previous=p;}public void close(){if(previous==null)OWNER.remove();else OWNER.set(previous);}}
 public synchronized boolean acquire(){
  long now=clock.millis();String key=Objects.toString(OWNER.get(),"unscoped");
  windows.entrySet().removeIf(e->now-e.getValue().started()>=duration);
  if(global==null||now-global.started()>=duration)global=new Window(now,0);
  Window own=windows.get(key);
  if(global.used()>=globalRequests || (own==null && windows.size()>=maxKeys) || (own!=null&&own.used()>=requests))return false;
  if(own==null)own=new Window(now,0);
  windows.put(key,new Window(own.started(),own.used()+1));global=new Window(global.started(),global.used()+1);return true;
 }
 public synchronized int trackedKeys(){return windows.size();}
}
