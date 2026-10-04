package com.project.execution.api;

import com.project.execution.domain.GameTypes.*;
import com.project.execution.domain.GridWorldExecutionEngine;
import com.project.programming.api.ProgramDto;
import com.project.programming.application.ContractException;
import com.project.programming.application.ProgrammingModelMapper;
import java.util.List;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

/** Stateless sandbox using the same EMF interpreter. No grading, attempts or progress writes. */
@RestController
@RequestMapping("/api/laboratory")
public class LaboratoryController {
    private static final WorldConfig WORLD = new WorldConfig(6, 5, new Position(1, 2), Direction.EAST,
        new Position(4, 2), List.of(new Position(3, 1)), List.of(new Position(2, 2)), List.of(new Position(4, 2)));
    private final ProgrammingModelMapper mapper;
    private final int limit;
    public LaboratoryController(ProgrammingModelMapper mapper, @Value("${mdedu.game.operation-limit:200}") int limit) {
        this.mapper = mapper; this.limit = limit;
    }
    @GetMapping("/world") public WorldConfig world() { return WORLD; }
    @PostMapping("/execute") public ResponseEntity<Result> execute(@RequestBody ProgramDto dto) {
        var program = mapper.map(dto);
        if (Diagnostician.INSTANCE.validate(program).getSeverity() != Diagnostic.OK)
            return ResponseEntity.unprocessableEntity().body(failure("INVALID_MODEL", "INVALID_EMF", "Revisa la estructura de tu programa."));
        return ResponseEntity.ok(new GridWorldExecutionEngine().execute(program, WORLD, limit));
    }
    private Result failure(String status, String code, String message) {
        return new Result(false, status, 0, null, List.of(), List.of(new Failure(code, message)));
    }
    @ExceptionHandler(ContractException.class) public ResponseEntity<Result> invalid(ContractException e) {
        return ResponseEntity.badRequest().body(failure("INVALID_REQUEST", e.code(), e.getMessage()));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class) public ResponseEntity<Result> malformed() {
        return ResponseEntity.badRequest().body(failure("INVALID_REQUEST", "MALFORMED_DTO", "Programa inválido."));
    }
}
