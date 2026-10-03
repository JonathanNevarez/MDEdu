package com.project.shared.security;
import java.nio.charset.StandardCharsets;
import java.util.*;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.web.cors.*;
import com.project.shared.infrastructure.config.CorsProperties;
@Configuration(proxyBeanMethods=false)
public class TeacherSecurityConfiguration {
 public static final String[] TEACHER_PATHS={"/api/meta/**","/api/teacher/**","/api/adaptation/**","/api/attempts/*/adaptation","/api/students/*/attempts/*/timeline","/api/sessions/*/timeline"};
 @Bean UserDetailsService teacherUsers(Environment env) {
  String user=env.getProperty("META_UI_USERNAME",""),password=env.getProperty("META_UI_PASSWORD","");
  // Missing/weak configuration creates no user: fail closed, including production.
  if(user.isBlank() || user.length()>80 || password.length()<12 || password.getBytes(StandardCharsets.UTF_8).length>72 || password.equalsIgnoreCase(user) || password.contains("<configure-me>"))
   return new InMemoryUserDetailsManager();
  return new InMemoryUserDetailsManager(User.withUsername(user).password("{bcrypt}"+new BCryptPasswordEncoder().encode(password)).roles("TEACHER").build());
 }
 @Bean CorsConfigurationSource securityCors(CorsProperties properties) {
  if(properties.allowedOrigins().stream().anyMatch(o->o.contains("*")))throw new IllegalArgumentException("Explicit CORS origins required");
  var config=new CorsConfiguration();config.setAllowedOrigins(properties.allowedOrigins());config.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS"));
  config.setAllowedHeaders(List.of("Content-Type","Accept","X-Request-Id","X-Session-Id","X-CSRF-TOKEN"));config.setExposedHeaders(List.of("X-Request-Id"));config.setAllowCredentials(true);config.setMaxAge(3600L);
  var source=new UrlBasedCorsConfigurationSource();source.registerCorsConfiguration("/api/**",config);return source;
 }
 private HttpSecurity headers(HttpSecurity http)throws Exception {
  return http.headers(h->h.contentTypeOptions(c->{}).frameOptions(f->f.deny()).referrerPolicy(r->r.policy(ReferrerPolicy.NO_REFERRER))
   .contentSecurityPolicy(c->c.policyDirectives("default-src 'none'; frame-ancestors 'none'; base-uri 'none'")));
 }
 @Bean @Order(1) SecurityFilterChain teacher(HttpSecurity http,CorsConfigurationSource securityCors)throws Exception {
  headers(http).securityMatcher(TEACHER_PATHS).cors(c->c.configurationSource(securityCors))
   .authorizeHttpRequests(a->a.requestMatchers("/api/teacher/csrf","/api/teacher/login").permitAll().anyRequest().hasRole("TEACHER"))
   .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).sessionFixation(f->f.changeSessionId()))
   .requestCache(c->c.disable())
   .exceptionHandling(e->e.authenticationEntryPoint((r,s,x)->ApiErrors.write(s,401)).accessDeniedHandler((r,s,x)->ApiErrors.write(s,403)))
   .formLogin(f->f.loginProcessingUrl("/api/teacher/login").successHandler((r,s,a)->s.setStatus(204)).failureHandler((r,s,e)->ApiErrors.write(s,401)))
   .logout(l->l.logoutUrl("/api/teacher/logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").logoutSuccessHandler((r,s,a)->s.setStatus(204)));
  // Default HttpSession CSRF protection remains enabled for teacher login/logout/writes.
  return http.build();
 }
 @Bean @Order(2) SecurityFilterChain student(HttpSecurity http,CorsConfigurationSource securityCors)throws Exception {
  headers(http).cors(c->c.configurationSource(securityCors)).authorizeHttpRequests(a->a.anyRequest().permitAll())
   .sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   // Anonymous JSON student API has no cookie-based authority; teacher endpoints use the first chain.
   .csrf(c->c.ignoringRequestMatchers("/api/**"));
  return http.build();
 }
}
