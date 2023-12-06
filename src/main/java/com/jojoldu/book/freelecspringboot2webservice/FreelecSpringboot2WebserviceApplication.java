package com.jojoldu.book.freelecspringboot2webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
// JPA Auditing 활성화
// JPA Auditing 어노테이션들을 모두 활성화할 수 있도록 Application 클래스에 활성화 어노테이션 하나를 추가
@SpringBootApplication
// 스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
// @SpringBootApplication이 있는 위치부터 설정을 읽는다
// 항상 프로젝트의 최상단에 위치해야 한다

// 메인 클래스
public class FreelecSpringboot2WebserviceApplication {

	// 실행해보면 톰캣 서버가 8080 포트로 실행되었다는 것이 로그에 출력된다.
	public static void main(String[] args) {
		SpringApplication.run(FreelecSpringboot2WebserviceApplication.class, args);
		// SpringApplication.run으로 인해 내장 WAS(Web Application Server, 웹 애플리케이션 서버)를 실행
		// 내장 WAS : 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행하는 것
		// 이렇게 되면 항상 서버에 톰캣을 설치할 필요가 없게 되고,
		// 스프링 부트로 만들어진 Jar 파일(실행 가능한 Java 패키징 파일)로 실행하면 된다.
		// 꼭 스프링 부트에서만 내장 WAS를 사용할 수 있는 것은 아니지만,
		// 스프링 부트에서는 내장 WAS를 사용하는 것을 권장
		// 이유 : '언제 어디서나 같은 환경에서 스프링 부트를 배포'할 수 있기 때문
		// 외장 WAS를 쓴다고 하면 모든 서버는 WAS의 종류와 버전, 설정을 일치시켜야만 한다.
		// 새로운 서버가 추가되면 모든 서버가 같은 WAS 환경을 구축해야만 한다.
		// 하지만 내장 wAS를 사용할 경우 이 문제를 모두 해결할 수 있다.
		// 그래서 많은 회사에서 내장 wAS를 사용하도록 전환하고 있다.
	}

}
