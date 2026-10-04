package com.project.student;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
class ChallengeMigrationIT {
    @Container static final PostgreSQLContainer<?> DB=new PostgreSQLContainer<>("postgres:17-bookworm");
    @Test void v9PreservesAccountMasteryAttemptsAndHistoricalProgress() {
        var config=Flyway.configure().dataSource(DB.getJdbcUrl(),DB.getUsername(),DB.getPassword()).locations("classpath:db/migration");
        config.target("8").load().migrate();
        var jdbc=new JdbcTemplate(new DriverManagerDataSource(DB.getJdbcUrl(),DB.getUsername(),DB.getPassword()));
        var student=UUID.randomUUID();var attempt=UUID.randomUUID();
        jdbc.update("insert into students(id,created_at,last_updated,attempt_sequence) values (?,now(),now(),1)",student);
        jdbc.update("insert into student_accounts(id,student_id,student_code,password_hash) values (?,?,'EST-001','migration-test-hash')",UUID.randomUUID(),student);
        jdbc.update("insert into student_concept_mastery select gen_random_uuid(),?,id,0.1,1,1,0,0,1000,0,now() from concepts",student);
        jdbc.update("insert into student_activity_progress select gen_random_uuid(),?,id,now(),now() from learning_activities",student);
        jdbc.update("insert into attempts values (?,?,'SEQUENCES','SEQUENCES',1,true,true,1000,0,now(),1,0,0.1,0.1,'SUCCESS')",attempt,student);
        var before=new LinkedHashMap<String,List<Map<String,Object>>>();
        for(String table:List.of("students","student_accounts","student_concept_mastery","attempts"))before.put(table,jdbc.queryForList("select * from "+table+" order by id"));
        var previous=jdbc.queryForList("select * from student_activity_progress order by id");
        config.target("latest").load().migrate();
        before.forEach((table,rows)->assertEquals(rows,jdbc.queryForList("select * from "+table+" order by id"),table));
        assertTrue(jdbc.queryForList("select * from student_activity_progress").containsAll(previous));
        assertEquals(18,jdbc.queryForObject("select count(*) from learning_activities where not archived",Integer.class));
        assertEquals(4,jdbc.queryForObject("select count(*) from learning_activities where archived",Integer.class));
        assertEquals(18,jdbc.queryForObject("select count(*) from student_activity_progress p join learning_activities a on a.id=p.activity_id where not a.archived and p.completed_at is null",Integer.class));
        assertEquals(4,jdbc.queryForObject("select count(*) from student_activity_progress p join learning_activities a on a.id=p.activity_id where not a.archived and p.unlocked_at is not null",Integer.class));
        assertEquals("SEQUENCES",jdbc.queryForObject("select activity_id from attempts where id=?",String.class,attempt));
    }
}
