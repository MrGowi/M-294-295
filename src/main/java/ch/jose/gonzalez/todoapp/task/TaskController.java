package ch.jose.gonzalez.todoapp.task;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ch.jose.gonzalez.todoapp.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/api/task/{id}")
    @RolesAllowed(Roles.Mitarbeiter)
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("api/task")
    @RolesAllowed(Roles.Mitarbeiter)
    public ResponseEntity<List<Task>> all() {
        List<Task> result = taskService.getAllTasks();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/api/task/{id}")
    @RolesAllowed(Roles.Teamleiter)
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @PutMapping("/api/task/{id}")
    @RolesAllowed(Roles.Teamleiter)
    public Task updateTask(@PathVariable Long id, @RequestBody Task task) {
        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/api/task/{id}")
    @RolesAllowed(Roles.Teamleiter)
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}