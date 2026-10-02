package com.project.execution.application;

import com.project.execution.domain.GameTypes.*;
import com.project.execution.domain.GridWorldExecutionEngine;
import com.project.programming.api.ProgramDto;
import com.project.programming.application.ProgrammingModelMapper;
import java.util.List;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GameExecutionService {
    private final ProgrammingModelMapper mapper;
    private final int limit;
    public GameExecutionService(ProgrammingModelMapper mapper, @Value("${mdedu.game.operation-limit:200}") int limit) {
        this.mapper = mapper; this.limit = limit;
    }
    public Result execute(Level level, ProgramDto dto) {
        var program = mapper.map(dto);
        var diagnostic = Diagnostician.INSTANCE.validate(program);
        if (diagnostic.getSeverity() != Diagnostic.OK)
            return new Result(false, "INVALID_MODEL", 0, null, List.of(),
                List.of(new Failure("INVALID_EMF", "El modelo no cumple la estructura requerida.")));
        return new GridWorldExecutionEngine().execute(program, level.worldConfig(), limit);
    }
}
