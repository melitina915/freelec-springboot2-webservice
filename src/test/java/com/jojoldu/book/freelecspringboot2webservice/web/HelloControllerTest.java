package com.jojoldu.book.freelecspringboot2webservice.web;

import com.jojoldu.book.freelecspringboot2webservice.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
// - 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
// - 여기서는 SpringExtension이라는 스프링 실행자를 사용한다.
// - 즉, 스프링 부트 테스트와 Junit 사이에 연결자 역할을 한다.
/*
@WebMvcTest(controllers = HelloController.class)
 */
// - 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
// - 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있다.
// - 단, @Service, @Component, @Repository 등은 사용할 수 없다.
// - 여기서는 컨트롤러만 사용하기 때문에 선언한다.
// @WebMvcTest는 CustomOAuth2UserService를 스캔하지 않는다.
// @WebMvcTest는 WebSecurityConfigurerAdapter, WebMvcConfigurer를 비롯한 @ControllerAdvice, @Controller를 읽는다.
// 즉, @Repository, @Service, @Component는 스캔 대상이 아니다.
// 그러니 SecurityConfig는 읽었지만,
// SecurityConfig를 생성하기 위해 필요한 CustomOAuth2UserService는 읽을수가 없어 앞에서와 같이 에러가 발생한 것이다.
// 그래서 이 문제를 해결하기 위해 다음과 같이 스캔 대상에서 SecurityConfig를 제거한다.
@WebMvcTest(controllers = HelloController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
// @WebMvcTest의 secure 옵션은 2.1부터 Deprecated 되었다.
// 언제 삭제될지 모르니 사용하지 않는걸 추천한다.
public class HelloControllerTest {
    @Autowired
    // - 스프링이 관리하는 빈(Bean)을 주입 받는다.
    private MockMvc mvc;
    // - 웹 API를 테스트할 때 사용한다.
    // - 스프링 MVC 테스트의 시작점이다.
    // - 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.

    @WithMockUser(roles = "USER")
    // 여기서도 마찬가지로 @WithMockUser를 사용해서 가짜로 인증된 사용자를 생성한다.
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
        // - MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다.
        // - 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언할 수 있다.
                .andExpect(status().isOk())
                // - mvc.perform의 결과를 검증한다.
                // - HTTP Header의 Status를 검증한다.
                // - 우리가 흔히 알고 있는 200, 404, 500 등의 상태를 검증한다.
                // - 여기선 OK 즉, 200인지 아닌지를 검증한다.
                .andExpect(content().string(hello));
                // - mvc.perform의 결과를 검증한다.
                // - 응답 본문의 내용을 검증한다.
                // - Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증한다.
    }



    // JSON이 리턴되는 API 역시 정상적으로 테스트가 통과하는 것을 확인할 수 있다.
    @WithMockUser(roles = "USER")
    // 여기서도 마찬가지로 @WithMockUser를 사용해서 가짜로 인증된 사용자를 생성한다.
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
                        // param
                        // - API 테스트할 때 사용될 요청 파라미터를 설정
                        // - 단, 값은 String만 허용
                        // - 그래서 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야만 가능하다.
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
                // jsonPath
                // - JSON 응답값을 필드별로 검증할 수 있는 메소드
                // - $를 기준으로 필드명을 명시
                // - 여기서는 name과 amount를 검증하니 $.name, $.amount로 검증
    }
}
