package ch.jose.gonzalez.todoapp.task;
import ch.jose.gonzalez.todoapp.storage.EntityNotFoundException;
import ch.jose.gonzalez.todoapp.task.Task;
import ch.jose.gonzalez.todoapp.task.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, Task.class ));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task task) {
        Task existingTask = taskRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, Task.class ));

        existingTask.setTaskName(task.getTaskName());
        existingTask.setTaskDescription(task.getTaskDescription());
        existingTask.setTaskStartDate(task.getTaskStartDate());
        existingTask.setTaskEndDate(task.getTaskEndDate());

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}