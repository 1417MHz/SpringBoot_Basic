package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // id를 통해 멤버 검색
    Optional<Member> findById(Long id);
    // 이름을 통해 멤버 검색
    Optional<Member> findByName(String name);
    // 지금까지 저장된 모든 회원 리스트 반환
    List<Member> findAll();
}


// Optional이란?
// 예) findById, findByName 등의 메소드로 데이터를 불러올 경우
// 데이터가 null인 경우에 그대로 null을 반환시키지 않고
// 데이터가 없다는 것을 나타내주기 위해 사용됨