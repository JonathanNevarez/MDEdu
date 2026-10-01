package com.project.programming.api;

import com.project.programming.application.ContractException;
import com.project.programming.application.ProgrammingModelService;
import com.project.programming.application.ProgrammingModelService.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/programming/models")
public class ProgrammingModelController {
    private final ProgrammingModelService service;
    public ProgrammingModelController(ProgrammingModelService service) { this.service = service; }
    @PostMapping
    public ResponseEntity<Result> build(@RequestBody ProgramDto dto) {
        var result = service.build(dto);
        return ResponseEntity.status(result.valid() ? 200 : 422).body(result);
    }
    @ExceptionHandler(ContractException.class)
    public ResponseEntity<Result> badContract(ContractException e) {
        return ResponseEntity.badRequest().body(new Result(false,
            List.of(new ModelDiagnostic(e.code(), e.getMessage())), null, null));
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Result> malformed() {
        return ResponseEntity.badRequest().body(new Result(false,
            List.of(new ModelDiagnostic("MALFORMED_DTO", "JSON, kind o estructura de contrato inválidos.")), null, null));
    }
}
