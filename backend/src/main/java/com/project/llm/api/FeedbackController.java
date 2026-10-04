package com.project.llm.api;
import com.project.llm.application.FeedbackOrchestrator;
import com.project.llm.infrastructure.FeedbackStore;
import com.project.llm.domain.LlmTypes.FeedbackDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    public record Request(@NotNull UUID studentId,@NotNull UUID attemptId) {}
    private final FeedbackOrchestrator orchestrator;private final FeedbackStore store;
    public FeedbackController(FeedbackOrchestrator orchestrator,FeedbackStore store){this.orchestrator=orchestrator;this.store=store;}
    @org.springframework.security.access.prepost.PreAuthorize("@studentAccess.owns(#request.studentId())")
 @PostMapping("/generate") public FeedbackDto generate(@Valid @RequestBody Request request){return orchestrator.generate(request.studentId(),request.attemptId());}
    @org.springframework.security.access.prepost.PreAuthorize("@studentAccess.feedback(#id)")
 @GetMapping("/{id}") public FeedbackDto get(@PathVariable UUID id){return store.get(id);}
}
