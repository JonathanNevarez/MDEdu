package com.project.shared.security;
import java.util.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
@RestController @RequestMapping("/api/teacher/participants")
public class ParticipantController {
 private final StudentAccounts accounts;
 public ParticipantController(StudentAccounts accounts){this.accounts=accounts;}
 public record Enabled(@NotNull Boolean enabled){}
 @GetMapping public List<StudentAccounts.Participant> list(@RequestParam(defaultValue="0") int page){return accounts.list(page);}
 @PostMapping public ResponseEntity<StudentAccounts.Issued> create(){return ResponseEntity.status(201).cacheControl(CacheControl.noStore()).body(accounts.create());}
 @PostMapping("/{id}/reset-password") public ResponseEntity<StudentAccounts.Issued> reset(@PathVariable UUID id){return ResponseEntity.ok().cacheControl(CacheControl.noStore()).body(accounts.reset(id));}
 @PatchMapping("/{id}") public StudentAccounts.Participant enable(@PathVariable UUID id,@Valid @RequestBody Enabled body){return accounts.enable(id,body.enabled());}
}
