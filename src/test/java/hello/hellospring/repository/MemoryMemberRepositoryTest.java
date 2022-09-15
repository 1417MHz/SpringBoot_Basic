package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {
    // 테스트는 서로 의존관계 없이 설계되어야 한다

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 매번 테스트 메소드가 종료될 때마다 repository 속 객체를 클리어 해줌
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // assertThat(값1).isEqualTo(값2) == 값1이 값2와 같은지 확인(같을 경우 true)
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        // member1으로 저장한 객체가 result와 같으므로  true
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        // 객체 2개를 생성하고 리스트로 넘겼으니 리스트 크기는 2가 되는게 맞다
        assertThat(result.size()).isEqualTo(2);
    }
}

// 테스트 주도 개발 = TDD
// 코드를 검증할 수 있는 테스트 케이스를 먼저 만들고 그 뒤 구현 클래스를 개발한다(틀을 미리 만든다)

// 코드가 길어질수록 테스트 하기는 더 힘들어진다. 때문에 테스트 코드가 필요한 것.