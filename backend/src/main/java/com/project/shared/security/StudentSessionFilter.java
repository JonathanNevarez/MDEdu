package com.project.shared.security;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
/** Recheck enabled/version on every request, including sessions on other devices. */
public class StudentSessionFilter extends OncePerRequestFilter {
 private final ObjectProvider<StudentAccounts> accounts;
 public StudentSessionFilter(ObjectProvider<StudentAccounts> accounts){this.accounts=accounts;}
 @Override protected void doFilterInternal(HttpServletRequest r,HttpServletResponse s,FilterChain chain)throws IOException,ServletException {
  var a=SecurityContextHolder.getContext().getAuthentication();
  if(a!=null && a.getPrincipal() instanceof StudentPrincipal p && !accounts.getObject().active(p)){
   SecurityContextHolder.clearContext();var session=r.getSession(false);if(session!=null)session.invalidate();ApiErrors.write(s,401);return;
  }
  chain.doFilter(r,s);
 }
}
