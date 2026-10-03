package com.project.shared.security;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
@Component @Order(-110)
public class PayloadLimitFilter extends OncePerRequestFilter {
 public static final int MAX_BYTES=262144;
 @Override protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain chain)throws IOException,ServletException {
  if(!request.getRequestURI().startsWith("/api/") || !(request.getMethod().equals("POST")||request.getMethod().equals("PUT")||request.getMethod().equals("PATCH"))){chain.doFilter(request,response);return;}
  if(request.getContentLengthLong()>MAX_BYTES){ApiErrors.write(response,413);return;}
  if(request.getRequestURI().equals("/api/teacher/login")){chain.doFilter(request,response);return;}
  byte[] body=request.getInputStream().readNBytes(MAX_BYTES+1);
  if(body.length>MAX_BYTES){ApiErrors.write(response,413);return;}
  // Form login must use the servlet's parameter parser, so only JSON bodies are cached.
  if(request.getContentType()==null || !request.getContentType().toLowerCase(java.util.Locale.ROOT).startsWith("application/json")){
   if(body.length>0){ApiErrors.write(response,415);return;}chain.doFilter(request,response);return;
  }
  chain.doFilter(new HttpServletRequestWrapper(request){
   @Override public ServletInputStream getInputStream(){var input=new ByteArrayInputStream(body);return new ServletInputStream(){public int read(){return input.read();}public boolean isFinished(){return input.available()==0;}public boolean isReady(){return true;}public void setReadListener(ReadListener listener){throw new UnsupportedOperationException();}};}
   @Override public BufferedReader getReader(){return new BufferedReader(new InputStreamReader(getInputStream(),java.nio.charset.StandardCharsets.UTF_8));}
  },response);
 }
}
