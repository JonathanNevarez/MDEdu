package com.project.telemetry.api;
import com.project.telemetry.application.CorrelationContext;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;
import org.slf4j.*;
import java.io.IOException;
import java.util.UUID;
@Component @Order(-120)
public class CorrelationIdFilter extends OncePerRequestFilter {
 @Override protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)throws ServletException,IOException {
  var previous=MDC.getCopyOfContextMap();MDC.clear();
  var id=CorrelationContext.uuid(request.getHeader("X-Request-Id"));if(id==null)id=UUID.randomUUID();
  MDC.put("requestId",id.toString());response.setHeader("X-Request-Id",id.toString());
  var session=CorrelationContext.uuid(request.getHeader("X-Session-Id"));if(session!=null)MDC.put("sessionId",session.toString());
  boolean failed=false;
  try{if(request.getHeader("X-Session-Id")!=null && session==null){response.setStatus(400);return;}chain.doFilter(request,response);}catch(ServletException|IOException|RuntimeException ex){
   failed=true;try{LoggerFactory.getLogger(getClass()).warn("event=TECHNICAL_ERROR status=FAILED errorCode=REQUEST_FAILED component=HTTP requestId={} sessionId={} attemptId={}",id,MDC.get("sessionId"),MDC.get("attemptId"));}catch(RuntimeException ignored){}throw ex;
  }finally{
   try{LoggerFactory.getLogger(getClass()).info("event=HTTP_COMPLETED status={} requestId={} sessionId={} attemptId={}",failed?500:response.getStatus(),id,MDC.get("sessionId"),MDC.get("attemptId"));}catch(RuntimeException ignored){}
   MDC.clear();if(previous!=null)MDC.setContextMap(previous);
  }
 }
}
