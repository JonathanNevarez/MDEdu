package com.project.student.api;
import com.project.student.application.*;
import com.project.student.api.LearningDtos.*;
import com.project.programming.application.ContractException;
import jakarta.validation.Valid;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api")
public class LearningController {
    private final LearningService service;private final StudentModelProjectionService projection;
    public LearningController(LearningService service,StudentModelProjectionService projection) {this.service=service;this.projection=projection;}
    @PostMapping("/students") public ResponseEntity<StudentDto> create(@Valid @RequestBody CreateStudent body) {return ResponseEntity.status(201).body(service.create(body.displayName()));}
    @org.springframework.security.access.prepost.PreAuthorize("@studentAccess.owns(#id)")
 @GetMapping("/students/{id}/model") public ModelDto model(@PathVariable UUID id) {return projection.dto(projection.project(id));}
    @org.springframework.security.access.prepost.PreAuthorize("@studentAccess.owns(#id)")
 @GetMapping("/students/{id}/progress") public ProgressDto progress(@PathVariable UUID id) {return projection.progress(projection.project(id));}
    @org.springframework.security.access.prepost.PreAuthorize("@studentAccess.owns(#body.studentId())")
 @PostMapping("/attempts") public ResponseEntity<AttemptResponse> attempt(@Valid @RequestBody SubmitAttempt body) {return ResponseEntity.status(201).body(service.submit(body));}
    @ExceptionHandler(ContractException.class) public ResponseEntity<Map<String,String>> invalid(ContractException e) {return ResponseEntity.badRequest().body(Map.of("code",e.code(),"message",e.getMessage()));}
}
