package shop.mtcoding.blog.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * 회원가입 API
 * 1. URL : http://localhost   :8000  /login
 * 2. method : POST (로그인은 select 이지만, post로 한다)
 * 3. 요청 body(request body) : username=값(String)&password=값(String)
 * 4. MIME타입 : x-www-form-urlencoded
 * 5. 응답 : view(html)를 응답함. index페이지.
 */

@Getter
@Setter
public class LoginDTO {
    private String username;
    private String password;
}
