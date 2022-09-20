package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // Post 액션을 Mapping
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        // soutv + TAB == System.out.println() 자동 작성

        memberService.join(member);

        return "redirect:/";
        // 작업이 끝났으니 홈 화면으로 redirect
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        // .addAttribute(String name, Object value)
        // value 객체를 name 이름으로 추가한다.
        // view 코드에서는 name으로 지정한 이름을 통해서 value를 사용한다
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
