package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    // 구동이 되는지 확인하기 위해 TestCase 작성!!

    // Map은 <키, 값>으로 데이터를 묶어줌
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    // 세팅 이전에 이름(member)은 이미 세팅되어있음
    // id는 시스템에서 정함
    public Member save(Member member) {
        member.setId(++sequence);
        // store(map)에 저장
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 데이터가 null일 가능성이 있다는 것을 알림
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다식을 이용
        return store.values().stream()
                    // member에서 가져온 name이 파라미터로 넘어온 name과 일치한지 확인
                    .filter(member -> member.getName().equals(name))
                    .findAny(); // 그 중에 아무거나 찾으면 리턴
    }

    @Override
    // List안에 담을 수 있는 자료형은 Member 타입
    public List<Member> findAll() {
        // store 속에 있는 Member들을 전부 ArrayList 타입으로 변환하여 반환
        return new ArrayList<>(store.values());
    }
}
