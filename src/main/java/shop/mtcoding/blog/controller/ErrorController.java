package shop.mtcoding.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {

    // 파일명은 숫자로 시작 x
    @GetMapping("/40x")
    public String ex40x() {
        return "error/ex40x";
    }
}
