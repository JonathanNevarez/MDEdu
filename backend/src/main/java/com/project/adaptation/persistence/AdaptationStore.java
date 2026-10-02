package com.project.adaptation.persistence;

import com.project.adaptation.manager.DecisionTypes.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class AdaptationStore {
    private final EntityManager em;private final JdbcTemplate jdbc;private final ObjectMapper json;
    public AdaptationStore(EntityManager em,JdbcTemplate jdbc,ObjectMapper json) {this.em=em;this.jdbc=jdbc;this.json=json;}
    public <T> T read(String text,Class<T> type) {try{return json.readValue(text,type);}catch(Exception e){throw new IllegalStateException("Invalid stored adaptation evidence",e);}}
    public String write(Object value) {try{return json.writeValueAsString(value);}catch(Exception e){throw new IllegalStateException("Cannot persist adaptation evidence",e);}}
    public AdaptationInputRow input(UUID attempt) {return em.find(AdaptationInputRow.class,attempt);}
    public AdaptationDecisionRow byId(UUID id) {return em.find(AdaptationDecisionRow.class,id);}
    public AdaptationDecisionRow byAttempt(UUID attempt,int rules,int parameters) {
        return em.createQuery("from AdaptationDecisionRow where attemptId=:a and rulesetVersion=:r and parametersVersion=:p",AdaptationDecisionRow.class)
            .setParameter("a",attempt).setParameter("r",rules).setParameter("p",parameters).getResultStream().findFirst().orElse(null);
    }
    public void saveInput(AdaptationInputRow row) {em.persist(row);em.flush();}
    public void saveDecision(AdaptationDecisionRow row,SemanticDecision semantic) {
        em.persist(row);em.flush();
        for(var rule:semantic.ruleAudit()) jdbc.update("insert into adaptation_decision_rules(decision_id,source_order,rule_id,rule_version,priority,specificity,severity,status,reason_code,evidence_json) values (?,?,?,?,?,?,?,?,?,?)",
            row.id,rule.sourceOrder(),rule.ruleId(),rule.version(),rule.priority(),rule.specificity(),rule.severity(),rule.status(),rule.reasonCode(),write(rule.evidence()));
        int order=0;
        for(var action:semantic.actionAudit()) jdbc.update("insert into adaptation_decision_actions(decision_id,action_order,rule_id,source_action_order,action_type,hint_level,feedback_style,target_concept_id,status,reason_code) values (?,?,?,?,?,?,?,?,?,?)",
            row.id,order++,action.ruleId(),action.sourceActionOrder(),action.action().type().getLiteral(),
            action.action().hintLevel()==null?null:action.action().hintLevel().getLiteral(),
            action.action().feedbackStyle()==null?null:action.action().feedbackStyle().getLiteral(),action.action().targetConceptId(),action.status(),action.reasonCode());
    }
    public DecisionDto dto(AdaptationDecisionRow row) {return DecisionDto.of(row.id,row.studentId,row.attemptId,row.createdAt,read(row.semanticJson,SemanticDecision.class),row.decisionFingerprint);}
}
