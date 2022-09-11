package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    // URL /hello 로 들어오면 이 메소드를 호출한다.
    @GetMapping("hello")
    public String Hello(Model model) {
        // 키, 값을 전달
        model.addAttribute("data", "hello Spring!!");
        return "hello";
    }
}
 