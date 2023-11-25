package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello") // hello url이 들어오면 아래 호출
    public String hello(Model model) {

        //name:data라는 key의 값을 hello!!!로 해서 넘김
        model.addAttribute("data", "hello!!!");
        return "hello"; //hello.html 보여줌

    }
}
