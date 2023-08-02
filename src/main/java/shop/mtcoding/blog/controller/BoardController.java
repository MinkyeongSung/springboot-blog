package shop.mtcoding.blog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shop.mtcoding.blog.dto.WriteDTO;
import shop.mtcoding.blog.model.Board;
import shop.mtcoding.blog.model.User;
import shop.mtcoding.blog.repository.BoardRepository;

@Controller
public class BoardController {

    @Autowired
    private HttpSession session;

    @Autowired
    private BoardRepository boardRepository;

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id) {
        // 0. PathVariable 값 받기(완)

        // session에 접근해서 sessionUser 키값을 가져오기
        // null 이면, 로그인페이지로 보내고 아니면 3번을 실행하세요
        // 1. 인증검사 (로그인 페이지 보내기)
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        // 2. 유효성검사 X
        // 권한검사 O
        Board board = boardRepository.findById(id);
        if (board.getUser().getId() != sessionUser.getId()) {
            return "redirect:/40x";
        }

        // boardRepository.deleteById(id); 호출
        // 3. 모델에 접근해서 삭제 (DB 삭제 쿼리 delete from board_tb where id = :id) (완);
        boardRepository.deleteById(id);

        return "redirect:/";
    }

    @GetMapping({ "/", "/board" })
    public String index(@RequestParam(defaultValue = "0") Integer page, HttpServletRequest request) {
        // 1. 유효성 검사 x
        // 2. 인증검사 x

        List<Board> boardList = boardRepository.findAll(page);
        int totalCount = boardRepository.count();
        int totalPage = totalCount / 3;
        if (totalCount % 3 > 0) {
            totalPage = totalPage + 1;
        }
        boolean last = totalPage - 1 == page;

        System.out.println("테스트 :" + boardList.size());
        System.out.println("테스트 :" + boardList.get(0).getTitle());

        request.setAttribute("boardList", boardList);
        request.setAttribute("prevPage", page - 1);
        request.setAttribute("nextPage", page + 1);
        request.setAttribute("first", page == 0 ? true : false);
        request.setAttribute("last", last);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("totalCount", totalCount);

        return "index";
    }

    @PostMapping("/board/save")
    public String save(WriteDTO writeDTO) {
        // validation check (유효성 검사)
        if (writeDTO.getTitle() == null || writeDTO.getTitle().isEmpty()) {
            return "redirect:/40x";
        }
        if (writeDTO.getContent() == null || writeDTO.getContent().isEmpty()) {
            return "redirect:/40x";
        }

        // 인증체크
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }

        boardRepository.save(writeDTO, sessionUser.getId());
        return "redirect:/";
    }

    @GetMapping("/board/saveForm")
    public String saveForm() {
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            return "redirect:/loginForm";
        }
        return "board/saveForm";
    }

    @GetMapping("/logo")
    public String logo() {
        return "redirect:/";
    }

    @GetMapping("/board/1")
    public String detail() {
        return "board/detail";
    }

    // localhost:8080/board/1
    // localhost:8080/board/50
    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) { // C
        User sessionUser = (User) session.getAttribute("sessionUser"); // 세션접근
        Board board = boardRepository.findById(id); // M

        boolean pageOwner = false;
        if (sessionUser != null) {
            System.out.println("테스트 세션 ID: " + sessionUser.getId());
            System.out.println("테스트 세션 board.getUser().getId(): " + board.getUser().getId());
            pageOwner = sessionUser.getId() == board.getUser().getId();
        } else {
        }

        request.setAttribute("board", board);
        request.setAttribute("pageOwner", pageOwner);
        return "board/detail"; // V
    }
}
