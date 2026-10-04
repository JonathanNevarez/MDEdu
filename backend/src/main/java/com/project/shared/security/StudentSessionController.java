package com.project.shared.security;
import java.util.*;
import jakarta.servlet.http.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.csrf.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/auth/student")
public class StudentSessionController {
 private final StudentAccounts accounts;private final StudentLoginLimiter limiter;
 public StudentSessionController(StudentAccounts accounts,StudentLoginLimiter limiter){this.accounts=accounts;this.limiter=limiter;}
 public record Login(@NotBlank @Size(max=32) String studentCode,@NotBlank @Size(max=72) String password){@Override public String toString(){return "Login[credentials=REDACTED]";}}
 public record Identity(String studentCode,UUID studentId,boolean authenticated){}
 private String peer(HttpServletRequest request){jakarta.servlet.ServletRequest raw=request;while(raw instanceof jakarta.servlet.ServletRequestWrapper wrapper)raw=wrapper.getRequest();return raw.getRemoteAddr();}
 @GetMapping("/csrf") public Map<String,String> csrf(CsrfToken token){return Map.of("token",token.getToken(),"headerName",token.getHeaderName());}
 @GetMapping("/me") public Identity me(){var a=SecurityContextHolder.getContext().getAuthentication();if(a==null || !(a.getPrincipal() instanceof StudentPrincipal p))throw new org.springframework.web.server.ResponseStatusException(org.springframework.http.HttpStatus.UNAUTHORIZED);return new Identity(p.studentCode(),p.studentId(),true);}
 @PostMapping("/login") public Identity login(@Valid @RequestBody Login body,HttpServletRequest request,HttpServletResponse response)throws java.io.IOException {
  response.setHeader("Cache-Control","no-store");
  String peer=peer(request);
  if(!limiter.allowed(peer)){ApiErrors.write(response,429);return null;}
  StudentPrincipal p=null;
  try {p=accounts.authenticate(body.studentCode(),body.password());}finally{limiter.completed(peer,p!=null);}
  if(p==null){response.setStatus(401);response.setContentType("application/json");response.setCharacterEncoding("UTF-8");response.getWriter().write("{\"code\":\"UNAUTHORIZED\",\"message\":\"Código o clave incorrectos.\"}");return null;}
  if(request.getSession(false)!=null)request.changeSessionId();else request.getSession(true);
  var context=SecurityContextHolder.createEmptyContext();context.setAuthentication(new UsernamePasswordAuthenticationToken(p,null,List.of(new SimpleGrantedAuthority("ROLE_STUDENT"))));
  SecurityContextHolder.setContext(context);new HttpSessionSecurityContextRepository().saveContext(context,request,response);
  new HttpSessionCsrfTokenRepository().saveToken(null,request,response);
  return new Identity(p.studentCode(),p.studentId(),true);
 }
}
