package ch.jose.gonzalez.todoapp.priority;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ch.jose.gonzalez.todoapp.member.Member;
import ch.jose.gonzalez.todoapp.member.MemberService;
import ch.jose.gonzalez.todoapp.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class PriorityController {

    private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("/api/priority/{id}")
    @RolesAllowed(Roles.Mitarbeiter)
    public ResponseEntity<Priority> one(@PathVariable Long id) {
        Priority priority = priorityService.getPriorityById(id);
        return new ResponseEntity<>(priority, HttpStatus.OK);
    }

    @GetMapping("api/priority")
    @RolesAllowed(Roles.Mitarbeiter)
    public ResponseEntity<List<Priority>> all() {
        List<Priority> result = priorityService.getAllPriorities();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("api/priority")
    @RolesAllowed(Roles.Teamleiter)
    public ResponseEntity<Priority> newCommment(@Valid @RequestBody Priority priority) {
        Priority savedPriority = priorityService.insertPriority(priority);
        return new ResponseEntity<>(savedPriority, HttpStatus.OK);
    }

    @PutMapping("api/priority/{id}")
    @RolesAllowed(Roles.Teamleiter)
    public ResponseEntity<Priority> updatePriority(@Valid @RequestBody Priority priority, @PathVariable Long id) {
        Priority savedPriority = priorityService.updatePriority(id, priority);
        return new ResponseEntity<>(savedPriority, HttpStatus.OK);
    }

    @DeleteMapping("api/priority/{id}")
    @RolesAllowed(Roles.Teamleiter)
    public void deletePriority(@PathVariable Long id) {
        priorityService.deletePriority(id);
    }
}