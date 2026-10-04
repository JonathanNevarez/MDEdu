package com.project.ui.api;
import com.project.ui.application.UiConfigurationService;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/students/{studentId}")
public class UiController {
    private final UiConfigurationService service;
    public UiController(UiConfigurationService service){this.service=service;}
    @org.springframework.security.access.prepost.PreAuthorize("@studentAccess.owns(#studentId)")
 @GetMapping("/attempts/{attemptId}/ui-configuration")
    public UiConfigurationService.Response attempt(@PathVariable UUID studentId,@PathVariable UUID attemptId){return service.byAttempt(studentId,attemptId);}
    @org.springframework.security.access.prepost.PreAuthorize("@studentAccess.owns(#studentId)")
 @GetMapping("/activities/{activityId}/ui-configuration")
    public UiConfigurationService.Response latest(@PathVariable UUID studentId,@PathVariable String activityId){return service.latest(studentId,activityId);}
}
