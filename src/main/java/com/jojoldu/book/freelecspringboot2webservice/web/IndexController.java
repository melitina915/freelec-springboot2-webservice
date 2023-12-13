// index.mustache에 URL 매핑
// index.mustache에서 userName을 사용할 수 있게 IndexController에서 userName을 model에 저장하는 코드를 추가한다.

package com.jojoldu.book.freelecspringboot2webservice.web;

import com.jojoldu.book.freelecspringboot2webservice.config.auth.LoginUser;
import com.jojoldu.book.freelecspringboot2webservice.config.auth.dto.SessionUser;
import com.jojoldu.book.freelecspringboot2webservice.service.posts.PostsService;
import com.jojoldu.book.freelecspringboot2webservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
// 페이지에 관련된 컨트롤러는 모두 IndexController를 사용한다.
public class IndexController {

    private final PostsService postsService;
    // IndexController의 코드에서 반복되는 부분들을 모두 @LoginUser로 개선한다.
    //private final HttpSession httpSession;

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

    /*
    // 전체 조회 화면 만들기
    @GetMapping("/")
    public String index(Model model) {
    // Model
    // - 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있다.
    // - 여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달한다.
        model.addAttribute("posts", postsService.findAllDesc());

        // 세션값을 가져오는 부분
        // index 메소드 외에 다른 컨트롤러와 메소드에서 세션값이 필요하면 그때마다 직접 세션에서 값을 가져와야 한다.
        // 같은 코드가 계속해서 반복되는 것은 불필요하다.
        // 그래서 이 부분을 메소드 인자로 세션값을 바로 받을 수 있도록 변경해 본다.
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        // (SessionUser) httpSession.getAttribute("user");
        // - 앞서 작성된 CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성했다.
        // - 즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있다.

        if (user != null) {
        // if (user != null)
        // - 세션에 저장된 값이 있을 때만 model에 userName으로 등록한다.
        // - 세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 된다.
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }
     */

    // 전체 조회 화면 만들기
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
    // @LoginUser SessionUser user
    // - 기존에 (User) httpSession.getAttribute("user")로 가져오던 세션 정보 값이 개선되었다.
    // - 이제는 어느 컨트롤러든지 @LoginUser만 사용하면 세션 정보를 가져올 수 있게 되었다.
        model.addAttribute("posts", postsService.findAllDesc());

        if (user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }



    // 게시글 등록 화면 만들기
    // index.mustache와 마찬가지로 /posts/save를 호출하면 posts-save.mustache를 호출하는 메소드가 추가되었다.
    @GetMapping("/posts/save")
    public String postsSave(){

        return "posts-save";
    }



    // 게시글 수정
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}
