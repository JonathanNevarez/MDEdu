package com.project.llm.application;
import com.project.llm.domain.LlmTypes.*;
import com.project.adaptation.manager.DecisionTypes.DecisionDto;
import com.project.evaluation.api.EvaluatedExecution;
import com.project.execution.domain.GameTypes.Level;
import com.project.programming.api.ProgramDto;
import com.project.programming.application.ProgrammingModelMapper;
import com.project.adaptation.persistence.AdaptationStore;
import com.project.adaptation.manager.DecisionTypes.Snapshot;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class FeedbackEvidenceCapture {
    private final ContextSanitizer sanitizer;private final ProgrammingModelMapper mapper;private final JdbcTemplate jdbc;private final ObjectMapper json;private final AdaptationStore adaptation;
    public FeedbackEvidenceCapture(ContextSanitizer sanitizer,ProgrammingModelMapper mapper,JdbcTemplate jdbc,ObjectMapper json,AdaptationStore adaptation){this.sanitizer=sanitizer;this.mapper=mapper;this.jdbc=jdbc;this.json=json;this.adaptation=adaptation;}
    /** Inside the existing attempt transaction; no provider invocation and no re-evaluation. */
    public void capture(UUID attempt,Level level,ProgramDto program,EvaluatedExecution result,DecisionDto decision,int hintCount) {
        var input=adaptation.input(attempt);var context=adaptation.read(input.evidenceJson,Snapshot.class).context();
        var evidence=new AttemptEvidence(context.concept(),level.description(),context.masteryScore(),context.attemptCount(),hintCount,
            result.evaluation().activityPassed(),context.requiredConceptUsed(),!result.evaluation().functionalCorrectness().runtimeError(),
            context.detectedPatterns(),sanitizer.summarize(mapper.map(program),result));
        try {jdbc.update("insert into feedback_attempt_inputs(attempt_id,evidence_json) values (?,?)",attempt,json.writeValueAsString(evidence));}
        catch(com.fasterxml.jackson.core.JsonProcessingException e){throw new IllegalStateException("Cannot encode sanitized feedback evidence");}
    }
}
