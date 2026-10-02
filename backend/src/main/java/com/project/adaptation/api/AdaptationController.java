package com.project.adaptation.api;

import com.project.adaptation.manager.AdaptationManager;
import com.project.adaptation.manager.DecisionTypes.DecisionDto;
import java.util.UUID;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api")
public class AdaptationController {
    public record Request(@NotNull UUID studentId,@NotNull UUID attemptId) {}
    private final AdaptationManager manager;
    public AdaptationController(AdaptationManager manager) {this.manager=manager;}
    @PostMapping("/adaptation/decide") public DecisionDto decide(@Valid @RequestBody Request request) {return manager.decide(request.studentId(),request.attemptId());}
    @GetMapping("/adaptation/decisions/{id}") public DecisionDto get(@PathVariable UUID id) {return manager.get(id);}
    @GetMapping("/attempts/{id}/adaptation") public DecisionDto byAttempt(@PathVariable UUID id) {return manager.byAttempt(id);}
}
