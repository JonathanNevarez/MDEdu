package com.project.evaluation.api;
import com.project.execution.domain.GameTypes.*;
import com.project.evaluation.domain.EvaluationTypes.EvaluationResult;
import java.util.List;
/** Additive HTTP response; execution fields retain their Phase 4 names and meanings. */
public record EvaluatedExecution(boolean success,String status,int steps,State finalState,List<Event> trace,List<Failure> errors,EvaluationResult evaluation) {
    public static EvaluatedExecution of(Result r,EvaluationResult evaluation){return new EvaluatedExecution(r.success(),r.status(),r.steps(),r.finalState(),r.trace(),r.errors(),evaluation);}
}
