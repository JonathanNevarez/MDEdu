package com.project.shared.security;
import java.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class StudentLoginLimiter {
 private record Window(long start,int failures,int inFlight){}
 private final Map<String,Window> windows=new HashMap<>();
 private final int limit;private final long duration;
 public StudentLoginLimiter(@Value("${app.student-auth.max-failures:5}") int limit,@Value("${app.student-auth.window-seconds:300}") long seconds){this.limit=Math.max(1,limit);duration=Math.max(1,seconds)*1000;}
 // Only direct peer address, never a user-controlled forwarded header. Bounded and fail closed.
 public synchronized boolean allowed(String address){long now=System.currentTimeMillis();windows.entrySet().removeIf(e->e.getValue().inFlight()==0&&now-e.getValue().start()>=duration);var w=windows.get(address);if(w==null?windows.size()>=10000:w.failures()+w.inFlight()>=limit)return false;windows.put(address,new Window(w==null?now:w.start(),w==null?0:w.failures(),w==null?1:w.inFlight()+1));return true;}
 public synchronized void completed(String address,boolean success){var w=windows.get(address);if(w==null)return;int failed=w.failures()+(success?0:1),active=Math.max(0,w.inFlight()-1);if(failed==0&&active==0)windows.remove(address);else windows.put(address,new Window(w.start(),failed,active));}
}
