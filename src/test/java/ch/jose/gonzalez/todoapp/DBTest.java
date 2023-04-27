package ch.jose.gonzalez.todoapp;
import ch.jose.gonzalez.todoapp.task.Task;
import ch.jose.gonzalez.todoapp.task.TaskRepository;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest()
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class DBTests {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void insertTask() {
        Task objTask1 = this.taskRepository.save(new Task("Meeting", "Vorbereitung meeting", LocalDate.of(2023, 4, 30), LocalDate.of(2023, 5, 5)));
        Assertions.assertNotNull(objTask1.getId());
        Task objTask2 = this.taskRepository.save(new Task("WebApp", "WebApp fertig machen", LocalDate.of(2023, 4, 27), LocalDate.of(2023, 5, 27)));
        Assertions.assertNotNull(objTask2.getId());
    }
}
