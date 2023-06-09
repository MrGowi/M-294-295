package ch.jose.gonzalez.todoapp.comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ch.jose.gonzalez.todoapp.storage.EntityNotFoundException;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Comment.class));
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment insertComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, Comment comment) {
        Comment existingComment = commentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(id, Comment.class ));
    
        existingComment.setName(comment.getName());
        existingComment.setComment_text(comment.getComment_text());
    
        return commentRepository.save(existingComment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
