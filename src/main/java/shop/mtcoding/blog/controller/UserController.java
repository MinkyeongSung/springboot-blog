package shop.mtcoding.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.blog.dto.JoinDTO;
import shop.mtcoding.blog.dto.LoginDTO;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.repository.UserRepository;

@Controller
public class UserController {

    @Autowired

    private UserRepository userRepository;

    @PostMapping("/login")
    public String login(LoginDTO loginDTO) {
        // validation check (유효성 검사)
        if (loginDTO.getUsername() == null || loginDTO.getUsername().isEmpty()) {
            return "error/ex50x";
        }
        if (loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty()) {
            return "error/ex50x";
        }
        // 핵심 기능
        User user = userRepository.findByUsernameAndPassword(loginDTO);

        if (user == null) {

        } else {

        }

        return "error/ex50x";
    }

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
        try {
            userRepository.save(joinDTO);
        } catch (Exception e) {
            return "redirect:/50x";
        }
        return "redirect:/loginForm";
    }

}
