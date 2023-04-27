package ch.jose.gonzalez.todoapp.member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ch.jose.gonzalez.todoapp.storage.EntityNotFoundException;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id, Member.class));
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member insertMember(Member member) {
        return memberRepository.save(member);
    }

    public Member updateMember(Long id, Member member) {
        return memberRepository.findById(id)
        .map(memberOrig -> {
            memberOrig.setName(member.getName());
            return memberRepository.save(memberOrig);
        })
        .orElseGet(() -> memberRepository.save(member));
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
