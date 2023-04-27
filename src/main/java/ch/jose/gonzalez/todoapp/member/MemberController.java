package ch.jose.gonzalez.todoapp.member;
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
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/api/member/{id}")
    @RolesAllowed(Roles.Mitarbeiter)
    public ResponseEntity<Member> one(@PathVariable Long id) {
        Member member = memberService.getMemberById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    @GetMapping("api/member")
    @RolesAllowed(Roles.Mitarbeiter)
    public ResponseEntity<List<Member>> all() {
        List<Member> result = memberService.getAllMembers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("api/member")
    @RolesAllowed(Roles.Teamleiter)
    public ResponseEntity<Member> newMember(@Valid @RequestBody Member member) {
        Member savedMember = memberService.insertMember(member);
        return new ResponseEntity<>(savedMember, HttpStatus.OK);
    }

    @PutMapping("api/member/{id}")
    @RolesAllowed(Roles.Teamleiter)
    public ResponseEntity<Member> updateMember(@Valid @RequestBody Member member, @PathVariable Long id) {
        Member savedMember = memberService.updateMember(id, member);
        return new ResponseEntity<>(savedMember, HttpStatus.OK);
    }

    @DeleteMapping("api/member/{id}")
    @RolesAllowed(Roles.Teamleiter)
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }
}