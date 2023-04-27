package ch.jose.gonzalez.todoapp.status;
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
public class Status {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotNull
    private TaskStatus taskStatus;

    public enum TaskStatus{
        Bearbeitung,
        Erledigt,
        Pausiert,
        Nicht_gestartet
    }

}
