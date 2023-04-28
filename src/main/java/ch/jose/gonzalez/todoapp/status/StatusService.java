package ch.jose.gonzalez.todoapp.status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ch.jose.gonzalez.todoapp.storage.EntityNotFoundException;
import java.util.List;

@Service
public class StatusService {

    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status getStatusById(Long id) {
        return statusRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Status.class));
    }

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public Status insertStatus(Status status) {
        return statusRepository.save(status);
    }



    public Status updateStatus(Long id, Status status) {
        Status existingStatus = statusRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, Status.class ));

        existingStatus.setTaskStatus(status.getTaskStatus());

        return statusRepository.save(existingStatus);
    }

    public void deleteStatus (Long id) {
        statusRepository.deleteById(id);
    }
}
