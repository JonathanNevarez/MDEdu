package com.project.student.infrastructure;
import jakarta.persistence.EntityManager;
import java.util.*;
import org.springframework.stereotype.Repository;
@Repository
public class LearningStore {
    private final EntityManager em;
    public LearningStore(EntityManager em) { this.em=em; }
    public void save(Object entity) { em.persist(entity); }
    public void flush() { em.flush(); }
    public List<ConceptRow> concepts() { return em.createQuery("from ConceptRow order by sortOrder",ConceptRow.class).getResultList(); }
    public List<ActivityRow> activities() { return em.createQuery("from ActivityRow order by id",ActivityRow.class).getResultList(); }
    public List<ErrorPatternRow> patterns() { return em.createQuery("from ErrorPatternRow order by id",ErrorPatternRow.class).getResultList(); }
    public List<MasteryRow> masteries(UUID id) { return em.createQuery("from MasteryRow where studentId=:id order by conceptId",MasteryRow.class).setParameter("id",id).getResultList(); }
    public List<ProgressRow> progress(UUID id) { return em.createQuery("from ProgressRow where studentId=:id order by activityId",ProgressRow.class).setParameter("id",id).getResultList(); }
    public List<AttemptRow> recent(UUID id,String concept,int limit) {
        String query="from AttemptRow where studentId=:id"+(concept==null?"":" and conceptId=:concept")+" order by studentOrdinal desc";
        var q=em.createQuery(query,AttemptRow.class).setParameter("id",id).setMaxResults(limit);
        if(concept!=null)q.setParameter("concept",concept);
        return q.getResultList();
    }
}
