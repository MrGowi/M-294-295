package ch.jose.gonzalez.todoapp;

import ch.jose.gonzalez.todoapp.task.Task;
import ch.jose.gonzalez.todoapp.task.TaskRepository;
import ch.jose.gonzalez.todoapp.task.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private TaskService taskService;
    private final TaskRepository taskRepositoryMock = mock(TaskRepository.class);

    private final Task taskMock = mock(Task.class);

    @BeforeEach
    void setUp() {
        taskService = new TaskService(taskRepositoryMock);
    }

    @Test
    void createTask() {
        when(taskRepositoryMock.save(taskMock)).thenReturn(taskMock);
        taskService.createTask(taskMock);
        verify(taskRepositoryMock, times(1)).save(any());
    }

    @Test
    void findTask() {
        when(taskRepositoryMock.findById(any())).thenReturn(Optional.ofNullable(taskMock));
        Task t = taskService.getTaskById(any());
        verify(taskRepositoryMock, times(1)).findById(any());
    }

    @Test
    void deleteTask() {
        taskService.deleteTask(any());
        verify(taskRepositoryMock, times(1)).deleteById(any());
    }
}