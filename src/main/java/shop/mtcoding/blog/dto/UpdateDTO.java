package shop.mtcoding.blog.dto;

import lombok.Getter;
import lombok.Setter;

/*
 * 회원가입 API
 * 1. URL : http://localhost   :8000  /board/  {id}/update
 * 2. method : POST
 * 3. 요청 body(request body) : title=값(String)&content=값(String)
 * 4. MIME타입 : x-www-form-urlencoded
 * 5. 응답 : 수정한 페이지 detail을 응답함.
 */

@Getter
@Setter
public class UpdateDTO {
    private String title;
    private String content;
}
