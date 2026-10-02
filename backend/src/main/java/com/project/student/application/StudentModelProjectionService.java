package com.project.student.application;

import com.project.student.infrastructure.*;
import com.project.student.domain.*;
import com.project.student.api.LearningDtos.*;
import com.project.mde.learning.*;
import com.project.mde.learning.validation.LearningModels;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class StudentModelProjectionService {
    private final StudentRepository students;
    private final LearningStore store;
    private final StudentModelPolicy policy;
    public StudentModelProjectionService(StudentRepository students,LearningStore store,StudentModelPolicy policy) {
        this.students=students;this.store=store;this.policy=policy;
    }
    @Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ)
    public StudentModel project(UUID id) {
        var row=students.findById(id).orElseThrow(()->new ResponseStatusException(NOT_FOUND,"STUDENT_NOT_FOUND"));
        var f=LearningFactory.eINSTANCE;var model=f.createStudentModel();model.setModelVersion(1);model.setLastUpdated(Date.from(row.lastUpdated));
        var student=f.createStudent();student.setId(row.id.toString());student.setDisplayName(row.displayName);student.setCreatedAt(Date.from(row.createdAt));model.setStudent(student);
        var concepts=new LinkedHashMap<String,Concept>();var conceptRows=store.concepts();
        for(var r:conceptRows) {var c=f.createConcept();c.setId(r.id);c.setName(r.name);c.setMinimumMasteryToUnlock(r.minimumMasteryToUnlock);concepts.put(r.id,c);model.getConcepts().add(c);}
        for(var r:conceptRows) r.prerequisites.stream().sorted().forEach(idRef->concepts.get(r.id).getPrerequisites().add(concepts.get(idRef)));
        var activities=new LinkedHashMap<String,Activity>();
        for(var r:store.activities()) {
            var a=f.createActivity();a.setId(r.id);a.setTitle(r.title);a.setConcept(concepts.get(r.conceptId));activities.put(r.id,a);model.getActivities().add(a);
            (r.reinforcement?a.getConcept().getReinforcementActivities():a.getConcept().getActivities()).add(a);
            var objective=f.createLearningObjective();objective.setId("OBJECTIVE_"+r.id);objective.setDescription("Aplicar "+a.getConcept().getName()+" en "+r.title);objective.setConcept(a.getConcept());
            model.getLearningObjectives().add(objective);a.getLearningObjectives().add(objective);
        }
        var patterns=new LinkedHashMap<String,ErrorPattern>();
        for(var r:store.patterns()) {var p=f.createErrorPattern();p.setId(r.id);p.setConcept(concepts.get(r.conceptId));p.setSeverity(r.severity);p.setPedagogicalMeaning(r.pedagogicalMeaning);patterns.put(r.id,p);model.getErrorPatterns().add(p);}
        var masteries=store.masteries(id);
        for(var concept:concepts.values()) {
            var r=masteries.stream().filter(m->m.conceptId.equals(concept.getId())).findFirst().orElseThrow();
            var m=f.createConceptMastery();m.setStudent(student);m.setConcept(concept);m.setMasteryScore(r.masteryScore);m.setAttemptCount(r.attemptCount);
            m.setSuccessCount(r.successCount);m.setFailureCount(r.failureCount);m.setConsecutiveFailures(r.consecutiveFailures);m.setAverageResolutionTime(r.averageResolutionTime);m.setHintCount(r.hintCount);m.setLastUpdated(Date.from(r.lastUpdated));
            MasteryUpdater.recentPatterns(store.recent(id,r.conceptId,policy.values().recentErrorWindow()).stream().map(a->a.patterns).toList(),policy.values()).forEach(p->m.getRecentErrorPatterns().add(patterns.get(p)));
            model.getConceptMasteries().add(m);
        }
        // A bounded snapshot; the relational database retains the complete attempt history.
        for(var r:store.recent(id,null,20)) {
            var a=f.createAttempt();a.setId(r.id.toString());a.setStudent(student);a.setActivity(activities.get(r.activityId));a.setConcept(concepts.get(r.conceptId));
            a.setSuccessful(r.successful);a.setFunctionalPassed(r.functionalPassed);a.setResolutionTime(r.resolutionTime);a.setHintCount(r.hintCount);a.setSubmittedAt(Date.from(r.submittedAt));
            r.patterns.forEach(p->a.getErrorPatterns().add(patterns.get(p)));model.getAttempts().add(a);
        }
        var progress=store.progress(id);
        for(var concept:concepts.values()) for(var activity:concept.getActivities()) {
            var r=progress.stream().filter(p->p.activityId.equals(activity.getId())).findFirst().orElseThrow();
            var p=f.createProgress();p.setActivity(activity);p.setConcept(concept);p.setCompleted(r.completedAt!=null);p.setUnlocked(r.unlockedAt!=null);
            if(r.completedAt!=null)p.setCompletedAt(Date.from(r.completedAt));model.getProgress().add(p);
        }
        LearningModels.validate(model);return model;
    }
    public ModelDto dto(StudentModel m) {
        var s=m.getStudent();return new ModelDto(new StudentDto(UUID.fromString(s.getId()),s.getDisplayName(),s.getCreatedAt().toInstant()),m.getModelVersion(),
            m.getConceptMasteries().stream().map(c->new MasteryDto(c.getConcept().getId(),c.getMasteryScore(),c.getAttemptCount(),c.getSuccessCount(),c.getFailureCount(),c.getConsecutiveFailures(),c.getAverageResolutionTime(),c.getHintCount(),c.getRecentErrorPatterns().stream().map(ErrorPattern::getId).toList(),c.getLastUpdated().toInstant())).toList(),
            m.getAttempts().stream().map(a->new AttemptDto(a.getId(),a.getActivity().getId(),a.getConcept().getId(),a.isSuccessful(),a.isFunctionalPassed(),a.getResolutionTime(),a.getHintCount(),a.getSubmittedAt().toInstant(),a.getErrorPatterns().stream().map(ErrorPattern::getId).toList())).toList(),m.getLastUpdated().toInstant());
    }
    public ProgressDto progress(StudentModel m) {
        return new ProgressDto(m.getProgress().stream().map(p->{var c=m.getConceptMasteries().stream().filter(a->a.getConcept()==p.getConcept()).findFirst().orElseThrow();
            return new ProgressEntry(p.getActivity().getId(),p.getConcept().getId(),p.isCompleted(),p.isUnlocked(),c.getMasteryScore(),c.getAttemptCount());}).toList());
    }
}
