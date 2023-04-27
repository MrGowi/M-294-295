package ch.jose.gonzalez.todoapp.comment;
import jakarta.annotation.Priority;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ch.jose.gonzalez.todoapp.member.Member;
import ch.jose.gonzalez.todoapp.member.MemberService;
import ch.jose.gonzalez.todoapp.security.Roles;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/api/comment/{id}")
    @RolesAllowed(Roles.Mitarbeiter)
    public ResponseEntity<Comment> one(@PathVariable Long id) {
        Comment comment = commentService.getCommentById(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("api/comment")
    @RolesAllowed(Roles.Mitarbeiter)
    public ResponseEntity<List<Comment>> all() {
        List<Comment> result = commentService.getAllComments();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("api/comment")
    @RolesAllowed(Roles.Teamleiter)
    public ResponseEntity<Comment> newCommment(@Valid @RequestBody Comment comment) {
        Comment savedComment = commentService.insertComment(comment);
        return new ResponseEntity<>(savedComment, HttpStatus.OK);
    }

    @PutMapping("api/comment/{id}")
    @RolesAllowed(Roles.Teamleiter)
    public ResponseEntity<Comment> updateComment(@Valid @RequestBody Comment comment, @PathVariable Long id) {
        Comment savedcComment = commentService.updateComment(id, comment);
        return new ResponseEntity<>(savedcComment, HttpStatus.OK);
    }

    @DeleteMapping("api/comment/{id}")
    @RolesAllowed(Roles.Teamleiter)
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}