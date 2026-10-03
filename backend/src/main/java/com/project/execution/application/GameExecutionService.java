package com.project.execution.application;

import com.project.execution.domain.GameTypes.*;
import com.project.execution.domain.GridWorldExecutionEngine;
import com.project.programming.api.ProgramDto;
import com.project.programming.application.ProgrammingModelMapper;
import com.project.evaluation.application.SolutionEvaluationService;
import com.project.evaluation.api.EvaluatedExecution;
import java.util.List;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GameExecutionService {
    @org.springframework.beans.factory.annotation.Autowired(required=false) private com.project.telemetry.application.TelemetryRecorder telemetry;
    private final ProgrammingModelMapper mapper;
    private final int limit;
    private final SolutionEvaluationService evaluation;
    public GameExecutionService(ProgrammingModelMapper mapper, @Value("${mdedu.game.operation-limit:200}") int limit, SolutionEvaluationService evaluation) {
        this.mapper = mapper; this.limit = limit; this.evaluation = evaluation;
    }
    public EvaluatedExecution execute(Level level, ProgramDto dto) {
        var program = mapper.map(dto);
        if(telemetry!=null)telemetry.model(com.project.telemetry.domain.TelemetryTypes.Type.PROGRAM_MODEL_CREATED,program,dto,null);
        var diagnostic = Diagnostician.INSTANCE.validate(program);
        if (diagnostic.getSeverity() != Diagnostic.OK)
            return EvaluatedExecution.of(new Result(false, "INVALID_MODEL", 0, null, List.of(),
                List.of(new Failure("INVALID_EMF", "El modelo no cumple la estructura requerida."))), null);
        if(telemetry!=null){telemetry.model(com.project.telemetry.domain.TelemetryTypes.Type.PROGRAM_MODEL_VALIDATED,program,dto,true);
            telemetry.emit(com.project.telemetry.domain.TelemetryTypes.Type.EXECUTION_STARTED,new com.project.telemetry.domain.TelemetryTypes.MarkerPayload("STARTED"),"");}
        var execution = new GridWorldExecutionEngine().execute(program, level.worldConfig(), limit);
        if(telemetry!=null)telemetry.emit(com.project.telemetry.domain.TelemetryTypes.Type.EXECUTION_COMPLETED,new com.project.telemetry.domain.TelemetryTypes.ExecutionPayload(execution.success(),execution.status(),execution.steps(),execution.finalState()!=null&&execution.finalState().atGoal(),execution.errors().size(),telemetry.hashObject(execution.trace())),"");
        var evaluated=evaluation.evaluate(level.id(), program, execution);
        if(telemetry!=null){
            telemetry.emit(com.project.telemetry.domain.TelemetryTypes.Type.EVALUATION_COMPLETED,new com.project.telemetry.domain.TelemetryTypes.EvaluationPayload(evaluated.evaluationVersion(),evaluated.functionalCorrectness().passed(),evaluated.structuralCorrectness().requiredConstructsSatisfied()&&evaluated.structuralCorrectness().constraintsSatisfied(),evaluated.activityPassed(),evaluated.structuralCorrectness().requiredConceptUsed(),evaluated.patterns().size()),"");
            for(var pattern:evaluated.patterns())telemetry.emit(com.project.telemetry.domain.TelemetryTypes.Type.ERROR_PATTERN_DETECTED,new com.project.telemetry.domain.TelemetryTypes.PatternPayload(pattern.id(),pattern.severity().name(),pattern.concept().name()),pattern.id());
        }
        return EvaluatedExecution.of(execution,evaluated);
    }
}
