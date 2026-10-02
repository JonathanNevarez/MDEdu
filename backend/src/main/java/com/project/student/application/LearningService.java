package com.project.student.application;

import com.project.student.infrastructure.*;
import com.project.student.domain.*;
import com.project.student.api.LearningDtos.*;
import com.project.execution.application.*;
import java.time.Instant;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;

@Service
public class LearningService {
    private final StudentRepository students;private final LearningStore store;private final StudentModelPolicy policy;
    private final ConceptGraphService graph;private final GameExecutionService execution;private final LevelCatalog levels;
    private final StudentModelProjectionService projection;
    private final com.project.adaptation.manager.AdaptationManager adaptation;
    private final com.project.llm.application.FeedbackEvidenceCapture feedbackEvidence;
    private final com.project.ui.infrastructure.AttemptProgramStore attemptPrograms;
    public LearningService(StudentRepository students,LearningStore store,StudentModelPolicy policy,ConceptGraphService graph,
                           GameExecutionService execution,LevelCatalog levels,StudentModelProjectionService projection,com.project.adaptation.manager.AdaptationManager adaptation,com.project.llm.application.FeedbackEvidenceCapture feedbackEvidence,com.project.ui.infrastructure.AttemptProgramStore attemptPrograms) {
        this.students=students;this.store=store;this.policy=policy;this.graph=graph;this.execution=execution;this.levels=levels;this.projection=projection;this.adaptation=adaptation;this.feedbackEvidence=feedbackEvidence;
        this.attemptPrograms=attemptPrograms;
    }
    @Transactional
    public StudentDto create(String displayName) {
        var now=Instant.now();var s=new StudentRow();s.id=UUID.randomUUID();s.displayName=displayName;s.createdAt=now;s.lastUpdated=now;students.saveAndFlush(s);
        for(var concept:store.concepts()) {var m=new MasteryRow();m.id=UUID.randomUUID();m.studentId=s.id;m.conceptId=concept.id;m.masteryScore=policy.values().initialMastery();m.lastUpdated=now;store.save(m);}
        for(var activity:store.activities()) if(!activity.reinforcement) {var p=new ProgressRow();p.id=UUID.randomUUID();p.studentId=s.id;p.activityId=activity.id;store.save(p);}
        unlock(s.id,now);return new StudentDto(s.id,s.displayName,s.createdAt);
    }
    @Transactional
    public AttemptResponse submit(SubmitAttempt request) {
        // A per-student row lock serializes every mastery/progress update, including different concepts.
        var student=students.lock(request.studentId()).orElseThrow(()->new ResponseStatusException(NOT_FOUND,"STUDENT_NOT_FOUND"));
        var level=levels.find(request.levelId()).orElseThrow(()->new ResponseStatusException(NOT_FOUND,"LEVEL_NOT_FOUND"));
        var activity=store.activities().stream().filter(a->a.id.equals(level.id())).findFirst().orElseThrow(()->new ResponseStatusException(NOT_FOUND,"ACTIVITY_NOT_FOUND"));
        var progress=store.progress(student.id).stream().filter(p->p.activityId.equals(activity.id)).findFirst().orElseThrow();
        if(progress.unlockedAt==null)throw new ResponseStatusException(CONFLICT,"ACTIVITY_LOCKED");
        var result=execution.execute(level,request.program());
        if(result.evaluation()==null)throw new ResponseStatusException(UNPROCESSABLE_ENTITY,"INVALID_EMF");
        var m=store.masteries(student.id).stream().filter(a->a.conceptId.equals(activity.conceptId)).findFirst().orElseThrow();
        var ids=result.evaluation().patterns().stream().map(p->p.id()).distinct().toList();
        var previous=store.recent(student.id,activity.conceptId,policy.values().recentErrorWindow()).stream().map(a->a.patterns).toList();
        var change=MasteryUpdater.update(new MasteryUpdater.State(m.masteryScore,m.attemptCount,m.successCount,m.failureCount,m.consecutiveFailures,m.averageResolutionTime,m.hintCount),result.evaluation().activityPassed(),request.hintCount(),request.resolutionTimeMs(),ids,previous,policy.values());
        var now=Instant.now();var next=change.state();m.masteryScore=next.score();m.attemptCount=next.attempts();m.successCount=next.successes();m.failureCount=next.failures();
        m.consecutiveFailures=next.consecutiveFailures();m.averageResolutionTime=next.averageTime();m.hintCount=next.hints();m.lastUpdated=now;
        student.lastUpdated=now;student.attemptSequence=Math.addExact(student.attemptSequence,1);
        var attempt=new AttemptRow();attempt.id=UUID.randomUUID();attempt.studentId=student.id;attempt.activityId=activity.id;attempt.conceptId=activity.conceptId;
        attempt.studentOrdinal=student.attemptSequence;attempt.successful=result.evaluation().activityPassed();attempt.functionalPassed=result.success();attempt.resolutionTime=request.resolutionTimeMs();
        attempt.hintCount=request.hintCount();attempt.submittedAt=now;attempt.policyVersion=policy.values().version();attempt.masteryBefore=change.before();attempt.masteryDelta=change.delta();attempt.masteryAfter=change.after();attempt.updateReasons=String.join(",",change.reasons());attempt.patterns.addAll(ids);store.save(attempt);
        if(attempt.successful && progress.completedAt==null)progress.completedAt=now;
        unlock(student.id,now);store.flush();
        attemptPrograms.capture(attempt.id,request.program());
        var model=projection.project(student.id);
        var decision=adaptation.captureAndDecide(model,model.getAttempts().getFirst(),result.evaluation(),change);
        feedbackEvidence.capture(attempt.id,level,request.program(),result,decision,request.hintCount());
        return new AttemptResponse(attempt.id,result,projection.dto(model),projection.progress(model),change,decision);
    }
    private void unlock(UUID studentId,Instant now) {
        var concepts=store.concepts();var activities=store.activities();var rows=store.progress(studentId);
        var states=new HashMap<String,ConceptGraphService.Achievement>();store.masteries(studentId).forEach(m->states.put(m.conceptId,new ConceptGraphService.Achievement(m.successCount,m.masteryScore)));
        var history=new HashSet<String>();for(var p:rows)if(p.unlockedAt!=null)activities.stream().filter(a->a.id.equals(p.activityId)).forEach(a->history.add(a.conceptId));
        var unlocked=graph.unlocked(concepts.stream().map(c->new ConceptGraphService.Node(c.id,c.minimumMasteryToUnlock,c.prerequisites)).toList(),states,history);
        for(var p:rows)if(p.unlockedAt==null && activities.stream().anyMatch(a->a.id.equals(p.activityId)&&unlocked.contains(a.conceptId)))p.unlockedAt=now;
    }
}
