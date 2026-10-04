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
 public boolean challenge(String id){
  var a=SecurityContextHolder.getContext().getAuthentication();
  return a!=null && a.getPrincipal() instanceof StudentPrincipal p && Boolean.TRUE.equals(jdbc.queryForObject(
   "select exists(select 1 from student_activity_progress p join learning_activities a on a.id=p.activity_id where p.student_id=? and p.activity_id=? and p.unlocked_at is not null and not a.archived)",Boolean.class,p.studentId(),id));
 }
}
