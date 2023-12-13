package com.jojoldu.book.freelecspringboot2webservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
// WebMvcTest는 일반적인 @Configuration은 스캔하지 않는다.
// 이렇게 하면 모든 테스트를 통과한다.
// 앞의 과정을 토대로 스프링 시큐리티 적용으로 깨진 테스트를 적절하게 수정할 수 있게 되었다.
@EnableJpaAuditing
// JPA Auditing 활성화
public class JpaConfig {

}
