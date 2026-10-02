package com.project.adaptation.manager;

import com.project.adaptation.application.ContextProjectionService;
import com.project.adaptation.domain.RuleEvaluationContext;
import com.project.adaptation.engine.EcaRuleEngine;
import com.project.adaptation.rules.AdaptationRuleLoader;
import com.project.adaptation.persistence.*;
import com.project.adaptation.manager.DecisionTypes.*;
import com.project.evaluation.catalog.PatternCatalog;
import com.project.evaluation.domain.EvaluationTypes.EvaluationResult;
import com.project.mde.adaptation.EventType;
import com.project.mde.learning.*;
import com.project.student.domain.*;
import com.project.student.infrastructure.*;
import java.util.*;
import java.time.Instant;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;

@Service
public class AdaptationManager {
    private final StudentRepository students;private final EntityManager em;private final AdaptationStore store;
    private final AdaptationRuleLoader loader;private final AdaptationParametersConfig parameters;private final ContextProjectionService contexts;
    private final AdaptationConflictResolver resolver;private final ConceptGraphService graph;private final PatternCatalog patterns;
    public AdaptationManager(StudentRepository students,EntityManager em,AdaptationStore store,AdaptationRuleLoader loader,
        AdaptationParametersConfig parameters,ContextProjectionService contexts,AdaptationConflictResolver resolver,ConceptGraphService graph,PatternCatalog patterns) {
        this.students=students;this.em=em;this.store=store;this.loader=loader;this.parameters=parameters;this.contexts=contexts;this.resolver=resolver;this.graph=graph;this.patterns=patterns;
    }
    /** Called once in the learning transaction with the already computed evaluation. */
    @Transactional
    public DecisionDto captureAndDecide(StudentModel model,Attempt attempt,EvaluationResult evaluation,MasteryUpdater.Change change) {
        UUID studentId=UUID.fromString(model.getStudent().getId()),attemptId=UUID.fromString(attempt.getId());
        lock(studentId);var persisted=attempt(studentId,attemptId);
        if(store.input(attemptId)==null) {
            var formal=contexts.create(model,attempt,evaluation,change);contexts.validate(formal,loader.catalog().concepts(),loader.catalog().patterns());
            var states=new LinkedHashMap<String,ConceptGraphService.Achievement>();
            model.getConceptMasteries().forEach(m->states.put(m.getConcept().getId(),new ConceptGraphService.Achievement(m.getSuccessCount(),m.getMasteryScore())));
            var nodes=model.getConcepts().stream().map(c->new ConceptGraphService.Node(c.getId(),c.getMinimumMasteryToUnlock(),
                new LinkedHashSet<>(c.getPrerequisites().stream().map(Concept::getId).toList()))).toList();
            var historical=new LinkedHashSet<String>();model.getProgress().stream().filter(Progress::isUnlocked).forEach(p->historical.add(p.getConcept().getId()));
            var unlocked=graph.unlocked(nodes,states,historical);
            var next=model.getConcepts().stream().filter(c->c.getPrerequisites().contains(attempt.getConcept()) && unlocked.contains(c.getId()))
                .map(Concept::getId).sorted().toList();
            var resources=new Resources(attempt.getActivity().getId(),next,attempt.getConcept().getReinforcementActivities().stream().map(Activity::getId).sorted().toList());
            var input=new AdaptationInputRow();input.attemptId=attemptId;input.studentId=studentId;input.activityId=persisted.activityId;
            input.evidenceJson=store.write(new Snapshot(ContextProjectionService.toRules(formal),resources));store.saveInput(input);
        }
        return decideLocked(studentId,attemptId);
    }
    @Transactional
    public DecisionDto decide(UUID studentId,UUID attemptId) {lock(studentId);attempt(studentId,attemptId);return decideLocked(studentId,attemptId);}
    private DecisionDto decideLocked(UUID studentId,UUID attemptId) {
        var rules=loader.snapshot();var p=parameters.values();var old=store.byAttempt(attemptId,rules.getVersion(),p.version());
        if(old!=null) {
            if(!old.rulesetHash.equals(loader.hash()) || !old.parametersHash.equals(CanonicalHashes.hash(p)))
                throw new ResponseStatusException(CONFLICT,"VERSION_CONTENT_MISMATCH");
            return store.dto(old);
        }
        var input=store.input(attemptId);
        if(input==null)throw new ResponseStatusException(CONFLICT,"HISTORICAL_ADAPTATION_SNAPSHOT_UNAVAILABLE");
        var snapshot=store.read(input.evidenceJson,Snapshot.class);
        var context=contexts.restore(studentId.toString(),input.activityId,snapshot.context());
        contexts.validate(context,loader.catalog().concepts(),loader.catalog().patterns());
        var runtime=ContextProjectionService.toRules(context);
        var eca=new EcaRuleEngine(loader.catalog()).evaluate(rules,runtime,EventType.ATTEMPT_EVALUATED);
        var severity=new LinkedHashMap<String,String>();patterns.patterns().forEach(pattern->severity.put(pattern.id(),pattern.severity().name()));
        var semantic=resolver.resolve(rules,eca,runtime,snapshot.resources(),p,severity,loader.hash());
        var formal=AdaptationConflictResolver.toModel(semantic);
        semantic=AdaptationConflictResolver.project(formal,semantic);
        var row=new AdaptationDecisionRow();row.id=UUID.randomUUID();row.studentId=studentId;row.attemptId=attemptId;row.createdAt=Instant.now().truncatedTo(java.time.temporal.ChronoUnit.MICROS);
        row.rulesetVersion=rules.getVersion();row.parametersVersion=p.version();row.selectedRuleId=formal.getRuleId();row.contextHash=semantic.contextHash();
        row.rulesetHash=semantic.rulesetHash();row.parametersHash=semantic.parametersHash();row.decisionFingerprint=CanonicalHashes.hash(semantic);row.llmUsed=false;row.semanticJson=store.write(semantic);
        store.saveDecision(row,semantic);return store.dto(row);
    }
    private void lock(UUID id) {students.lock(id).orElseThrow(()->new ResponseStatusException(NOT_FOUND,"STUDENT_NOT_FOUND"));}
    private AttemptRow attempt(UUID student,UUID id) {
        var attempt=em.find(AttemptRow.class,id);
        if(attempt==null || !attempt.studentId.equals(student))throw new ResponseStatusException(NOT_FOUND,"ATTEMPT_NOT_FOUND");return attempt;
    }
    @Transactional(readOnly=true)
    public DecisionDto get(UUID id) {var row=store.byId(id);if(row==null)throw new ResponseStatusException(NOT_FOUND,"DECISION_NOT_FOUND");return store.dto(row);}
    @Transactional(readOnly=true)
    public DecisionDto byAttempt(UUID attemptId) {
        var row=store.byAttempt(attemptId,loader.snapshot().getVersion(),parameters.values().version());
        if(row==null)throw new ResponseStatusException(NOT_FOUND,"DECISION_NOT_FOUND");return store.dto(row);
    }
}
