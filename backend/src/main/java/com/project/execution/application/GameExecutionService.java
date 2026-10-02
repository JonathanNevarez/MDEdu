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
    private final ProgrammingModelMapper mapper;
    private final int limit;
    private final SolutionEvaluationService evaluation;
    public GameExecutionService(ProgrammingModelMapper mapper, @Value("${mdedu.game.operation-limit:200}") int limit, SolutionEvaluationService evaluation) {
        this.mapper = mapper; this.limit = limit; this.evaluation = evaluation;
    }
    public EvaluatedExecution execute(Level level, ProgramDto dto) {
        var program = mapper.map(dto);
        var diagnostic = Diagnostician.INSTANCE.validate(program);
        if (diagnostic.getSeverity() != Diagnostic.OK)
            return EvaluatedExecution.of(new Result(false, "INVALID_MODEL", 0, null, List.of(),
                List.of(new Failure("INVALID_EMF", "El modelo no cumple la estructura requerida."))), null);
        var execution = new GridWorldExecutionEngine().execute(program, level.worldConfig(), limit);
        return EvaluatedExecution.of(execution, evaluation.evaluate(level.id(), program, execution));
    }
}
