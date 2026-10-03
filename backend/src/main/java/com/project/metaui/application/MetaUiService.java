package com.project.metaui.application;

import com.project.metaui.dto.MetaUiDtos.*;
import com.project.student.application.StudentModelProjectionService;
import com.project.adaptation.manager.DecisionTypes.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;

/** Read-only composition. No dependency on execution, decision or LLM commands. */
@Service
@Transactional(readOnly=true, isolation=Isolation.REPEATABLE_READ)
public class MetaUiService {
    private final JdbcTemplate jdbc;
    private final StudentModelProjectionService models;
    private final ObjectMapper json;
    public MetaUiService(JdbcTemplate jdbc, StudentModelProjectionService models, ObjectMapper json) {
        this.jdbc=jdbc; this.models=models; this.json=json;
    }
    public Capabilities capabilities() {return new Capabilities(true,true,true,true,false,false,false);}
    private static long offset(int page,int size) {
        if(page<0 || page>100000 || size<1 || size>50) throw new ResponseStatusException(BAD_REQUEST,"PAGE_OUT_OF_RANGE");
        return (long)page*size;
    }
    private void requireStudent(UUID id) {
        if(!Boolean.TRUE.equals(jdbc.queryForObject("select exists(select 1 from students where id=?)",Boolean.class,id)))
            throw new ResponseStatusException(NOT_FOUND,"STUDENT_NOT_FOUND");
    }
    public Page<StudentSummary> students(int page,int size) {
        long offset=offset(page,size);
        var items=jdbc.query("""
            with selected as (select id,created_at,last_updated from students order by created_at desc,id limit ? offset ?)
            select s.*, (select count(*) from attempts a where a.student_id=s.id) attempt_count,
            (select count(distinct l.concept_id) from student_activity_progress p join learning_activities l on l.id=p.activity_id
             where p.student_id=s.id and p.completed_at is not null) completed_concepts
            from selected s order by s.created_at desc,s.id
            """,(rs,n)->new StudentSummary(rs.getObject("id",UUID.class),rs.getTimestamp("created_at").toInstant(),
                rs.getTimestamp("last_updated").toInstant(),rs.getLong("attempt_count"),rs.getLong("completed_concepts")),size,offset);
        return new Page<>(items,page,size,jdbc.queryForObject("select count(*) from students",Long.class));
    }
    public Overview overview(UUID student) {
        var model=models.project(student);var dto=models.dto(model);
        return new Overview(student,dto.modelVersion(),dto.lastUpdated(),dto.conceptMasteries(),models.progress(model),
            model.getConcepts().stream().map(c->new ConceptInfo(c.getId(),c.getName(),c.getMinimumMasteryToUnlock(),
                c.getPrerequisites().stream().map(p->p.getId()).toList())).toList());
    }
    public Page<AttemptSummary> attempts(UUID student,int page,int size) {
        long offset=offset(page,size);requireStudent(student);
        var items=jdbc.query("""
            select a.*, coalesce((select string_agg(p.pattern_id, ',' order by p.pattern_order)
            from attempt_error_patterns p where p.attempt_id=a.id),'') patterns
            from attempts a where student_id=? order by student_ordinal desc limit ? offset ?
            """,(rs,n)->{
                var id=rs.getObject("id",UUID.class);String patterns=rs.getString("patterns");
                return new AttemptSummary(id,rs.getString("activity_id"),rs.getString("concept_id"),rs.getTimestamp("submitted_at").toInstant(),
                    rs.getBoolean("functional_passed"),rs.getBoolean("successful"),patterns.isEmpty()?List.of():List.of(patterns.split(",")),
                    "/api/students/"+student+"/attempts/"+id+"/timeline");
            },student,size,offset);
        return new Page<>(items,page,size,jdbc.queryForObject("select count(*) from attempts where student_id=?",Long.class,student));
    }
    public Page<AdaptationSummary> adaptations(UUID student,int page,int size) {
        long offset=offset(page,size);requireStudent(student);
        var items=jdbc.query("""
            select d.*,a.activity_id,a.concept_id from adaptation_decisions d join attempts a on a.id=d.attempt_id and a.student_id=d.student_id
            where d.student_id=? order by d.created_at desc,d.id desc limit ? offset ?
            """,(rs,n)->{
                SemanticDecision semantic;
                try{semantic=json.readValue(rs.getString("semantic_json"),SemanticDecision.class);}
                catch(Exception e){throw new IllegalStateException("DECISION_REFERENCE_INVALID");}
                return new AdaptationSummary(rs.getString("activity_id"),rs.getString("concept_id"),DecisionDto.of(rs.getObject("id",UUID.class),student,
                    rs.getObject("attempt_id",UUID.class),rs.getTimestamp("created_at").toInstant(),semantic,rs.getString("decision_fingerprint")));
            },student,size,offset);
        return new Page<>(items,page,size,jdbc.queryForObject("select count(*) from adaptation_decisions where student_id=?",Long.class,student));
    }
}
