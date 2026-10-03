package com.project.telemetry.api;
import com.project.telemetry.application.*;
import com.project.telemetry.domain.TelemetryTypes.*;
import java.util.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
@RestController @RequestMapping("/api")
public class TelemetryController {
 public record StudentRequest(@NotNull UUID studentId){}
 public record ClientEvent(@NotNull UUID studentId,UUID attemptId,@NotBlank @Size(max=64) String activityId,@NotNull Type type,@NotNull UUID clientEventId,@Size(max=64) String configurationFingerprint){}
 private final SessionService sessions;private final ClientTelemetryService client;private final AttemptTimelineService timeline;
 public TelemetryController(SessionService sessions,ClientTelemetryService client,AttemptTimelineService timeline){this.sessions=sessions;this.client=client;this.timeline=timeline;}
 @PostMapping("/sessions") @ResponseStatus(HttpStatus.CREATED) public Session create(@Valid @RequestBody StudentRequest request){return sessions.create(request.studentId());}
 @GetMapping("/sessions/{id}") public Session get(@PathVariable UUID id,@RequestParam UUID studentId){return sessions.get(studentId,id);}
 @PostMapping("/sessions/{id}/end") public Session end(@PathVariable UUID id,@Valid @RequestBody StudentRequest request){return sessions.end(request.studentId(),id);}
 @GetMapping("/sessions/{id}/timeline") public List<Event> session(@PathVariable UUID id,@RequestParam UUID studentId){return timeline.session(studentId,id);}
 @PostMapping("/telemetry/events") @ResponseStatus(HttpStatus.NO_CONTENT) public void record(@Valid @RequestBody ClientEvent e){client.record(e.studentId(),e.attemptId(),e.activityId(),e.type(),e.clientEventId(),e.configurationFingerprint());}
 @GetMapping("/students/{studentId}/attempts/{attemptId}/timeline") public AttemptTimelineService.AttemptReconstruction attempt(@PathVariable UUID studentId,@PathVariable UUID attemptId){return timeline.attempt(studentId,attemptId);}
}
