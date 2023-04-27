package ch.jose.gonzalez.todoapp.priority;
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
public class Priority {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotNull
    private TaskPriority taskPriority;

    public enum TaskPriority {
        HIGH,
        LOW,
        SIDE
    }

}