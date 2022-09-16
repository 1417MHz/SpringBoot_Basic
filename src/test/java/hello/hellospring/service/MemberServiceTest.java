package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    // MemberService와 MemberServiceTest 클래스 둘 다 MemoryMemberRepository 객체를 따로 생성할 경우,
    // 인스턴스의 충돌이 생길 수 있으므로 같은 인스턴스를 사용하도록 DI(Dependency Injection) 시켜주는것이 필요하다.
    @BeforeEach // BeforeEach ==  각 테스트 실행 전에 호출된다. 테스트가 서로 영향이 없도록 항상 새로운 객체를 생성하고, 의존관계도 새로 맺어준다.
    public void beforeEach() {
        // Test파일에서 MemoryMemberRepository 객체를 생성해서 메인 파일로 보낸다
        memberRepository = new MemoryMemberRepository();
        // 같은 인스턴스를 사용하기 위해
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);


        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 확인차 일부러 예외를 발생시키는 메소드
    @Test
    void duplicateMemberException() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring"); // 일부러 이름을 중복으로 생성

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}