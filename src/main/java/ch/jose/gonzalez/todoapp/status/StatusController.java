package ch.jose.gonzalez.todoapp.status;
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
public class StatusController {

    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping("/api/status/{id}")
    @RolesAllowed(Roles.Mitarbeiter)
    public ResponseEntity<Status> one(@PathVariable Long id) {
        Status status = statusService.getStatusById(id);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("api/status")
    @RolesAllowed(Roles.Mitarbeiter)
    public ResponseEntity<List<Status>> all() {
        List<Status> result = statusService.getAllStatus();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("api/status")
    @RolesAllowed(Roles.Teamleiter)
    public ResponseEntity<Status> newStatus(@Valid @RequestBody Status status) {
        Status savedStatus = statusService.insertStatus(status);
        return new ResponseEntity<>(savedStatus, HttpStatus.OK);
    }

    @PutMapping("api/status/{id}")
    @RolesAllowed(Roles.Teamleiter)
    public ResponseEntity<Status> updateStatus(@Valid @RequestBody Status status, @PathVariable Long id) {
        Status savedStatus = statusService.updateStatus(id, status);
        return new ResponseEntity<>(savedStatus, HttpStatus.OK);
    }

    @DeleteMapping("api/status/{id}")
    @RolesAllowed(Roles.Teamleiter)
    public void deleteStatus(@PathVariable Long id) {
        statusService.deleteStatus(id);
    }
}