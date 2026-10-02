package com.project.execution.api;

import com.project.execution.application.*;
import com.project.execution.domain.GameTypes.*;
import com.project.programming.api.ProgramDto;
import com.project.programming.application.ContractException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game/levels")
public class GameController {
    private final LevelCatalog catalog;
    private final GameExecutionService service;
    public GameController(LevelCatalog catalog, GameExecutionService service) { this.catalog = catalog; this.service = service; }
    @GetMapping public Catalog levels() { return catalog.all(); }
    @GetMapping("/{id}") public ResponseEntity<?> level(@PathVariable String id) {
        var level = catalog.find(id);
        return level.isPresent() ? ResponseEntity.ok(level.get()) : ResponseEntity.status(404).body(new Failure("LEVEL_NOT_FOUND", "Nivel no encontrado."));
    }
    @PostMapping("/{id}/execute") public ResponseEntity<?> execute(@PathVariable String id, @RequestBody ProgramDto dto) {
        var level = catalog.find(id);
        if (level.isEmpty()) return ResponseEntity.status(404).body(new Failure("LEVEL_NOT_FOUND", "Nivel no encontrado."));
        var result = service.execute(level.get(), dto);
        return ResponseEntity.status(result.status().equals("INVALID_MODEL") ? 422 : 200).body(result);
    }
    @ExceptionHandler(ContractException.class) public ResponseEntity<Result> invalid(ContractException e) { return bad(e.code(), e.getMessage()); }
    @ExceptionHandler(HttpMessageNotReadableException.class) public ResponseEntity<Result> malformed() {
        return bad("MALFORMED_DTO", "JSON o estructura de programa inválidos.");
    }
    private ResponseEntity<Result> bad(String code, String message) {
        return ResponseEntity.badRequest().body(new Result(false, "INVALID_REQUEST", 0, null, List.of(), List.of(new Failure(code, message))));
    }
}
