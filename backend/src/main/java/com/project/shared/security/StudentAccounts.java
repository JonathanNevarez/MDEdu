package com.project.shared.security;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import com.project.student.application.LearningService;
import static org.springframework.http.HttpStatus.*;

@Service
public class StudentAccounts {
 private final JdbcTemplate jdbc; private final LearningService learning;
 private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
 private final SecureRandom random=new SecureRandom();
 private final String dummyHash=encoder.encode(UUID.randomUUID().toString());
 public StudentAccounts(JdbcTemplate jdbc,LearningService learning){this.jdbc=jdbc;this.learning=learning;}
 public record Participant(UUID studentId,String studentCode,boolean enabled,Instant createdAt,Instant lastLoginAt,long attemptCount){}
 public record Issued(Participant participant,String temporaryPassword){@Override public String toString(){return "Issued[credentials=REDACTED]";}}
 private record Account(StudentPrincipal principal,String hash,boolean enabled){@Override public String toString(){return "Account[credentials=REDACTED]";}}
 public StudentPrincipal authenticate(String rawCode,String password){
  String code=rawCode==null?"":rawCode.trim().toUpperCase(Locale.ROOT);
  var rows=code.matches("^EST-[0-9]{3,6}$")?jdbc.query("select student_id,student_code,credential_version,password_hash,enabled from student_accounts where student_code=?",
   (rs,n)->new Account(new StudentPrincipal(rs.getObject(1,UUID.class),rs.getString(2),rs.getLong(3)),rs.getString(4),rs.getBoolean(5)),code):List.<Account>of();
  Account a=rows.isEmpty()?null:rows.getFirst();
  boolean validPassword=password!=null && password.getBytes(java.nio.charset.StandardCharsets.UTF_8).length<=72;
  boolean matches=encoder.matches(validPassword?password:"",a==null?dummyHash:a.hash()) && validPassword;
  if(a==null || !matches || !a.enabled())return null;
  jdbc.update("update student_accounts set last_login_at=now() where student_id=?",a.principal().studentId());
  return a.principal();
 }
 public boolean active(StudentPrincipal p){return Boolean.TRUE.equals(jdbc.queryForObject("select exists(select 1 from student_accounts where student_id=? and enabled and credential_version=?)",Boolean.class,p.studentId(),p.credentialVersion()));}
 public List<Participant> list(int page){
  if(page<0 || page>100000)throw new ResponseStatusException(BAD_REQUEST);
  return jdbc.query("select a.student_id,a.student_code,a.enabled,a.created_at,a.last_login_at,(select count(*) from attempts t where t.student_id=a.student_id) from student_accounts a order by a.created_at,a.student_id limit 20 offset ?",
   (rs,n)->new Participant(rs.getObject(1,UUID.class),rs.getString(2),rs.getBoolean(3),rs.getTimestamp(4).toInstant(),rs.getTimestamp(5)==null?null:rs.getTimestamp(5).toInstant(),rs.getLong(6)),page*20);
 }
 private Participant participant(UUID id){return jdbc.query("select student_id,student_code,enabled,created_at,last_login_at,(select count(*) from attempts where student_id=a.student_id) from student_accounts a where student_id=?",
  (rs,n)->new Participant(rs.getObject(1,UUID.class),rs.getString(2),rs.getBoolean(3),rs.getTimestamp(4).toInstant(),rs.getTimestamp(5)==null?null:rs.getTimestamp(5).toInstant(),rs.getLong(6)),id).stream().findFirst().orElseThrow(()->new ResponseStatusException(NOT_FOUND));}
 private String secret(){byte[] bytes=new byte[18];random.nextBytes(bytes);return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);}
 @Transactional public Issued create(){
  long number=jdbc.queryForObject("select nextval('student_code_sequence')",Long.class);
  String code=String.format(Locale.ROOT,"EST-%03d",number),password=secret();
  UUID student=learning.create(null).id();
  jdbc.update("insert into student_accounts(id,student_id,student_code,password_hash) values (?,?,?,?)",UUID.randomUUID(),student,code,encoder.encode(password));
  return new Issued(participant(student),password);
 }
 @Transactional public Issued reset(UUID id){String password=secret();
  if(jdbc.update("update student_accounts set password_hash=?,credential_version=credential_version+1 where student_id=?",encoder.encode(password),id)!=1)throw new ResponseStatusException(NOT_FOUND);
  return new Issued(participant(id),password);
 }
 @Transactional public Participant enable(UUID id,boolean enabled){
  if(jdbc.update("update student_accounts set enabled=?,credential_version=credential_version+1 where student_id=?",enabled,id)!=1)throw new ResponseStatusException(NOT_FOUND);
  return participant(id);
 }
}
