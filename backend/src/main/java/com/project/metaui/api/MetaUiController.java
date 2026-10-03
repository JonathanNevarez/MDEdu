package com.project.metaui.api;

import com.project.metaui.application.*;
import com.project.metaui.dto.MetaUiDtos.*;
import java.util.UUID;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MetaUiController {
    private final MetaUiService service;
    private final AdaptationInspectionService inspection;
    public MetaUiController(MetaUiService service,AdaptationInspectionService inspection) {this.service=service;this.inspection=inspection;}
    @GetMapping("/meta/capabilities") public Capabilities capabilities(){return service.capabilities();}
    @GetMapping("/meta/students") public Page<StudentSummary> students(@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="20") int size){return service.students(page,size);}
    @GetMapping("/meta/students/{id}") public Overview overview(@PathVariable UUID id){return service.overview(id);}
    @GetMapping("/meta/students/{id}/attempts") public Page<AttemptSummary> attempts(@PathVariable UUID id,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="20") int size){return service.attempts(id,page,size);}
    @GetMapping("/meta/students/{id}/adaptations") public Page<AdaptationSummary> adaptations(@PathVariable UUID id,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="20") int size){return service.adaptations(id,page,size);}
    @GetMapping("/adaptation/rules") public AdaptationInspectionService.Rules rules(){return inspection.rules();}
    @GetMapping("/adaptation/parameters") public AdaptationInspectionService.Parameters parameters(){return inspection.parameters();}
}
