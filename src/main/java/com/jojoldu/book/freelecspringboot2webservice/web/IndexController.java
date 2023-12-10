// index.mustache에 URL 매핑

package com.jojoldu.book.freelecspringboot2webservice.web;

import com.jojoldu.book.freelecspringboot2webservice.service.posts.PostsService;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
// 페이지에 관련된 컨트롤러는 모두 IndexController를 사용한다.
public class IndexController {

    private final PostsService postsService;

    /*
    // 기본 페이지 만들기
    @GetMapping("/")
    public String index() {
        return "index";
        // 머스테치 스타터 덕분에 컨트롤러에서 문자열을 반환할 때 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.
        // 앞의 경로는 src/main/resources/templates로, 뒤의 파일 확장자는 .mustache가 붙는 것이다.
        // 즉 여기선 "index"를 반환하므로, src/main/resources/templates/index.mustache로 전환되어 View Resolver가 처리하게 된다.
        // ViewResolver는 URL 요청의 결과를 전달할 타입과 값을 지정하는 관리자 격으로 볼 수 있다.
    }
     */

    // 게시글 등록 화면 만들기
    // index.mustache와 마찬가지로 /posts/save를 호출하면 posts-save.mustache를 호출하는 메소드가 추가되었다.
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }



    // 전체 조회 화면 만들기
    @GetMapping("/")
    public String index(Model model) {
    // Model
    // - 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
    // - 여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    // 게시글 수정
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
