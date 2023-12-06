// Domain Model (domain services, entitles, and value objects) > Repository Layer (repository interfaces and their implementations)

// Domain Model
// - 도메인이라 불리는 개발 대상을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화시킨 것
// - 이를테면 택시 앱이라고 하면 배차, 탑승, 요금 등이 모두 도메인이 될 수 있다.
// - @Entity가 사용된 영역 역시 도메인 모델이라고 이해하면 된다.
// - 다만, 무조건 데이터베이스의 테이블과 관계가 있어야만 하는 것은 아니다.
// - VO처럼 값 객체들도 이 영역에 해당하기 때문이다.

package com.jojoldu.book.freelecspringboot2webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// JPA Repository
// 인터페이스로 생성한다.
// 단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
// @Repository를 추가할 필요도 없다.
// 주의할 점은 Entity 클래스와 기본 Entity Repository는 함께 위치해야 하는 점이다.
// 둘은 아주 밀접한 관계이고, Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없다.
// 나중에 프로젝트 규모가 커져 도메인별로 프로젝트를 분리해야 한다면
// 이때 Entity 클래스와 기본 Repository는 함께 움직여야 하므로 도메인 패키지에서 함께 관리한다.

// Posts 클래스로 Database를 접근하게 해줄 JpaRepository
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
