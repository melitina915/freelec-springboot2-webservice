package com.jojoldu.book.freelecspringboot2webservice.web;
// 컨트롤러와 관련된 클래스들은 모두 이 패키지에 담는다

import com.jojoldu.book.freelecspringboot2webservice.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
// - 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 준다.
// - 예전에는 @ResponseBody를 각 메소드마다 선언했던 것을 한 번에 사용할 수 있게 해준다
public class HelloController {

    @GetMapping("/hello")
    // - HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어 준다.
    // - 예전에는 @RequestMapping(method = RequestMethod.GET)으로 사용되었다.
    // 이제 이 프로젝트는 /hello로 요청이 오면 문자열 hello를 반환하는 기능을 가지게 된다.
    public String hello(){
        return "hello";
    }

    // HelloController에도 새로 만든 ResponseDto를 사용하도록 코드 추가
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
    // @RequestParam
    // - 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
    // - 여기서는 외부에서 name ( @RequestParam("name") )이란 이름으로 넘긴 파라미터를
    // 메소드 파라미터 name( String name )에 저장하게 된다.
        return new HelloResponseDto(name, amount);
        // name과 amount는 API를 호출하는 곳에서 넘겨준 값들이다.
        // 추가된 API를 테스트하는 코드를 HelloControllerTest에 추가한다.
    }
}
