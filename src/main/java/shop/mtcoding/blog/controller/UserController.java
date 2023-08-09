package shop.mtcoding.blog.controller;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public HttpSession session; // request는 가방, session은 서랍

    @GetMapping("/check")
    public ResponseEntity<String> check(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO);
        if (user != null) {
            return new ResponseEntity<String>("유저네임이 중복 되었습니다", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("유저네임을 사용할 수 있습니다", HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(LoginDTO loginDTO) {
        // validation check (유효성 검사)
        if (loginDTO.getUsername() == null || loginDTO.getUsername().isEmpty()) {
            return "redirect:/40x";
        }
        if (loginDTO.getPassword() == null || loginDTO.getPassword().isEmpty()) {
            return "redirect:/40x";
        }
        // 핵심 기능
        System.out.println("테스트 1 :" + loginDTO.getPassword());
        User user = userRepository.findByUsername(loginDTO);

        boolean isVaild = BCrypt.checkpw(loginDTO.getPassword(), user.getPassword());

        // int isVaild = 1;
        if (isVaild == true) {
            // if (isVaild == 1) {
            session.setAttribute("sessionUser", user);
            return "redirect:/";
        } else {
            return "redirect:/exLogin";
        }
    }

    // 더 좋은 방법.(실무 1)
    // 회원가입을 위한 DTO를 만들어서 클래스로 받기
    @PostMapping("/join")
    public String join(JoinDTO joinDTO) {

        // userRepository.save(joinDTO);
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
        session.invalidate(); // 세션 무효화 (내 서랍 비우기)
        return "redirect:/";
    }

}
