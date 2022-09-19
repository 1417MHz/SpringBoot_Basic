package hello.hellospring.controller;

public class MemberForm {
    // createMemberForm.html 에서 받아오는 파라미터의 타입 지정

    // createMemberForm.html의 input 태그에 있는 name="name"과 매칭됨
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
