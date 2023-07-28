package shop.mtcoding.blog.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * 회원가입 API
 * 1. URL : http://localhost   :8000  /join
 * 2. method : POST (get은 바디가 없음)
 * 3. 요청 body(request body) : username=값(String)&password=값(String)&email=값(String)
 * 4. MIME타입 : x-www-form-urlencoded
 * 5. 응답 : view를 응답함.
 */
@Getter
@Setter
public class JoinDTO {
    private String username;
    private String password;
    private String email;
}
