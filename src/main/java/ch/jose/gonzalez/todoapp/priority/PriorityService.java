package ch.jose.gonzalez.todoapp.priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ch.jose.gonzalez.todoapp.storage.EntityNotFoundException;
import jakarta.validation.Valid;

import java.util.List;

@Service
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public Priority getPriorityById(Long id) {
        return priorityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Priority.class));
    }

    public List<Priority> getAllPriorities() {
        return priorityRepository.findAll();
    }

    public Priority insertPriority(Priority priority) {
        return priorityRepository.save(priority);
    }

    public Priority updatePriority(Long id, Priority priority) {
        Priority existingPriority = priorityRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, Priority.class));
    
        existingPriority.setTaskPriority(priority.getTaskPriority());
    
        return priorityRepository.save(existingPriority);
    }

    public void deletePriority (Long id) {
        priorityRepository.deleteById(id);
    }
}
