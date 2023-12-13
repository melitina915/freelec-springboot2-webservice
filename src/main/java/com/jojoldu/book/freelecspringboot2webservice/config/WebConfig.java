// 이제 이렇게 생성된 LoginUserArgumentResolver가 스프링에서 인식될 수 있도록 WebMvcConfigurer에 추가한다.
// config 패키지에 WebConfig 클래스를 생성하여 설정을 추가한다.

package com.jojoldu.book.freelecspringboot2webservice.config;

import com.jojoldu.book.freelecspringboot2webservice.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    // HandlerMethodArgumentResolver는 항상 WebMvcConfigurer의 addArgumentResolvers()를 통해 추가해야 한다.
    // 다른 HandlerMethodArgumentResolver가 필요하다면 같은 방식으로 추가해 주면 된다.
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
