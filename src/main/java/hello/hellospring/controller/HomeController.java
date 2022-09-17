package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 홈 페이지로 들어가게 되면 웰컴 페이지로 연결되지 않을까 싶지만,
    // 컨트롤러는 정적 페이지보다 우선순위가 높으므로 해당 메소드가 호출된다.
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
