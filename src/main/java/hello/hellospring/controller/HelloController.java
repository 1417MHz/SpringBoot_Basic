package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // URL /hello 로 들어오면 이 메소드를 호출한다.
    @GetMapping("hello")
    public String Hello(Model model) {
        // 키, 값을 전달
        model.addAttribute("data", "hello Spring!!");
        return "hello";
        // resources/templates 폴더에 해당 리턴 값과 같은 이름의 파일이 있는지 확인하고 그 HTML파일로 연결시킨다.
        // 같은 이름인지 확인할 때 대소문자는 구분하지 않는 듯하다. (Hello로 작성해도 동작하긴 한다)
        // hello.html 파일로 연결시킨다.
    }

    @GetMapping("hello-mvc")
    // name이라는 파라미터가 브라우저로부터 넘겨와야 한다.
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // Http의 Body부에 이 데이터를 넣겠다는 뜻
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        // 객체 생성
        Hello hello = new Hello();
        // 인자로 넘어온 name으로 값을 set 해준다
        hello.setName(name);
        // 객체를 리턴
        return hello;
    }

    static class Hello {
        // private 멤버는 메소드를 통해서 외부에서 접근시킨다.
        // 프로퍼티 접근 방식이라고도 부름.
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
 