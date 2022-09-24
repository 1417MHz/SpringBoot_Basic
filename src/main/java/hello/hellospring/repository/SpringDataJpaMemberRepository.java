package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    // findByName 메소드만 구현하는 이유는 JpaRepository의 기본 CRUD 기능 메소드엔 포함되어있지 않기 때문
    // save, findAll, findById와 같은 메소드는 기본 제공

    // 스프링 데이터 JPA가 스스로 findBy / Name 식으로 규칙을 파악하여 스스로 JPQL을 작성한다
    // JPQL - select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
