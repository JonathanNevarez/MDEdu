package com.project.shared.security;
import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
@Component("studentAccess")
public class StudentAccess {
 private final JdbcTemplate jdbc;
 public StudentAccess(JdbcTemplate jdbc){this.jdbc=jdbc;}
 public boolean owns(UUID id){var a=SecurityContextHolder.getContext().getAuthentication();return a!=null && (a.getAuthorities().stream().anyMatch(r->r.getAuthority().equals("ROLE_TEACHER")) || a.getPrincipal() instanceof StudentPrincipal p && p.studentId().equals(id));}
 public boolean feedback(UUID id){return jdbc.query("select student_id from feedback_records where id=?",(rs,n)->rs.getObject(1,UUID.class),id).stream().anyMatch(this::owns);}
}
