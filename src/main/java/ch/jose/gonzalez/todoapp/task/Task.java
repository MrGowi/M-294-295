package ch.jose.gonzalez.todoapp.task;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Task {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String taskName;

    @Column(nullable = false)
    @Size(max = 255)
    @NotEmpty
    private String taskDescription;

    @Column(nullable = false)
    @NotNull
    private LocalDate taskStartDate;

    @Column(nullable = false)
    @NotNull
    private LocalDate taskEndDate;


    public Task(String taskName, String taskDescription, LocalDate taskStartDate, LocalDate taskEndDate) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskStartDate = taskStartDate;
        this.taskEndDate = taskEndDate;
    }

}
