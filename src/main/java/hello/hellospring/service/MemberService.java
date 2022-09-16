package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // Ctrl + Shift + T를 누르면 IDE가 자동으로 테스트 클래스를 만들어준다......

    private final MemberRepository memberRepository;

    // 직접 new로 생성하는 것이 아닌
    // 같은 인스턴스를 사용하기 위해 MemberRepository를 외부에서 넣는 식으로 변경(DI)
    public MemberService(MemberRepository memberRepository) { // 생성자
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        // 회원 이름 중복 불가
        validateDuplicateMember(member); // 중복 회원 검증 메소드
        memberRepository.save(member);
        return member.getId();

        // * 참조 // .orElseGet() == 값이 있을 경우 가져오고 아니면 가져오지 않는다.
    }

    // 중복 회원 검증 메소드
    private void validateDuplicateMember(Member member) {
        // Optional<> 사용과 유사함
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { // ifPresent == 값이 있다면
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // Id를 통해 유저 찾기
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
