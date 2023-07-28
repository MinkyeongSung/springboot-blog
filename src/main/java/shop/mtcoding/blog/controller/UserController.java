package shop.mtcoding.blog.controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.blog.dto.JoinDTO;
import shop.mtcoding.blog.repository.UserRepository;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // ip주소 부여 : 10.1.1.1. -> 도메인을 사서 mtcoding.com
    // 포트로 결정(:8080) -> 10.1.1.1:8080
    // a태그 form태그 method=get
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    @GetMapping("/loginForm")
    public String loginForm() {
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String userForm() {
        return "user/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

    // 더 좋은 방법.(실무 1)
    // 회원가입을 위한 DTO를 만들어서 클래스로 받기
    @PostMapping("/join")
    public String join(JoinDTO joinDTO) {

        userRepository.save(joinDTO);
        // validation check (유효성 검사)
        if (joinDTO.getUsername() == null || joinDTO.getUsername().isEmpty()) {
            return "error/ex40x";
        }
        if (joinDTO.getPassword() == null || joinDTO.getPassword().isEmpty()) {
            return "error/ex40x";
        }
        if (joinDTO.getUsername() == null || joinDTO.getUsername().isEmpty()) {
            return "error/ex40x";
        }
        return "redirect:/loginForm";
    }

}
