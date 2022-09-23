package hello.hellospring.domain;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // JPA가 관리하는 엔티티임을 나타냄
public class Member {

    // id = 사용자가 지정하는 것이 아닌 시스템이 지정(데이터를 구분하기 위함)
    // 사용자가 직접 키 값을 입력하는게 아닌 DB에서 스스로 키 값을 넣어주는 것을 'Identity'라고 함
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    // PK 매핑
    private Long id;

    // name = 사용자가 입력
    // DB에서의 열 이름이 다를 경우 @Column(name = "열 이름")을 통해 매핑 가능
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
