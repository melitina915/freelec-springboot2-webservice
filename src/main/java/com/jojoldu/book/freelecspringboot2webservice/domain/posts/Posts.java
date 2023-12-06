// Domain Model (domain services, entitles, and value objects)
// 비지니스 처리를 담당한다.

// Domain Model
// - 도메인이라 불리는 개발 대상을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화시킨 것
// - 이를테면 택시 앱이라고 하면 배차, 탑승, 요금 등이 모두 도메인이 될 수 있다.
// - @Entity가 사용된 영역 역시 도메인 모델이라고 이해하면 된다.
// - 다만, 무조건 데이터베이스의 테이블과 관계가 있어야만 하는 것은 아니다.
// - VO처럼 값 객체들도 이 영역에 해당하기 때문이다.



package com.jojoldu.book.freelecspringboot2webservice.domain.posts;

// 도메인
// 게시글, 댓글, 회원, 정산, 결제 등 소프트웨어에 대한 요구사항 혹은 문제 영역

// 롬복 라이브러리의 어노테이션들
import com.jojoldu.book.freelecspringboot2webservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
// 서비스 초기 구축 단계에선 테이블 설계(여기선 Entity 설계)가 빈번하게 변경되는데,
// 이때 롬복의 어노테이션들은 코드 변경량을 최소화시켜 주기 때문에 적극적으로 사용한다.

// Setter 메소드가 없는 이유
// 자바빈 규약을 생각하면서 getter/setter를 무작정 생성하는 경우가 있다.
// 이렇게 되면 해당 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 명확하게 구분할 수가 없어,
// 차후 기능 변경 시 정말 복잡해진다.
// 그래서 Entity 클래스에서는 절대 Setter 메소드를 만들지 않는다.
// 대신, 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야만 한다.

// Setter가 없는 이 상황에서 어떻게 값을 채워 DB에 삽입해야 할까?
// 기본적인 구조는 생성자를 통해 최종값을 채운 후 DB에 삽입하는 것이며,
// 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경하는 것을 전제로 한다.
// 이 책에서는 생성자 대신에 @Builder를 통해 제공되는 빌더 클래스를 사용한다.
// 생성자나 빌더나 생성 시점에 값을 채워주는 역할은 똑같다.
// 다만, 생성자의 경우 지금 채워야 할 필드가 무엇인지 명확히 지정할 수가 없다.
// 하지만 빌더를 사용하게 되면 다음과 같이 어느 필드에 어떤 값을 채워야 할지 명확하게 인지할 수 있다.

/*
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// 어노테이션 순서를 주요 어노테이션을 클래스에 가깝게 둔다
// 어노테이션 정렬 기준
// @Entity는 JPA의 어노테이션이며,
// @Getter와 @NoArgsConstructor는 롬복의 어노테이션이다.
// 롬복은 코드를 단순화시켜 주지만 필수 어노테이션은 아니다
// 때문에 주요 어노테이션인 @Entity를 클래스에 가깝게 두고,
// 롬복 어노테이션을 그 위로 둔다.
// 이렇게 하면 이후 코들린 드으이 새 언어 전환으로 롬복이 더 이상 필요 없을 경우 쉽게 삭제할 수 있다.

@Getter
// @Getter
// - 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor
// @NoArgsConstructor
// - 기본 생성자 자동 추가
// - public Posts() {} 와 같은 효과
@Entity
// @Entity
// - 테이블과 링크될 클래스임을 나타낸다.
// - 기본값으로 클래스의 카멜케이스 이름을 언더스코어( _ ) 네이밍으로 테이블 이름을 매칭한다.
// ex) SalesManager.java -> sales_manager table
public class Posts extends BaseTimeEntity {
    // Posts 클래스가 BaseTimeEntity를 상속받도록 변경한다.
    // 앞으로 추가될 엔티티들은 BaseTimeEntity만 상속받으면 자동으로 해결되기 때문에 더 이상 등록일/수정일로 고민할 필요가 없다.
    
    // Posts 클래스는 실제 DB의 테이블과 매칭될 클래스이다.
    // 보통 Entity 클래스라고도 한다.
    // JPA를 사용하면 DB 데이터에 작업할 경우 실제 쿼리를 날리기보다는,
    // 이 Entity 클래스의 수정을 통해 작업한다.

    @Id
    // @Id
    // - 해당 테이블의 PK 필드를 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue
    // - PK의 생성 규칙을 나타낸다.
    // - 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 된다.
    // 웬만하면 Entity의 PK는 Long 타입의 Auto_increment를 추천한다.
    // (MySQL 기준으로 이렇게 하면 bigint 타입이 된다.)
    // 주민등록번호와 같이 비즈니스상 유니크 키나,
    // 여러 키를 조합한 복합키로 PK를 잡을 경우 난감한 상황이 종종 발생한다.
    // (1) FK를 맺을 때 다른 테이블에서 복합키 전부를 갖고 있거나,
    // 중간 테이블을 하나 더 둬야 하는 상황이 발생한다.
    // (2) 인덱스에 좋은 영향을 끼치지 못한다.
    // (3) 유니크한 조건이 변경될 경우 PK 전체를 수정해야 하는 일이 발생한다.
    // 주민등록번호, 복합키 등은 유니크 키로 별도로 추가하는 것이 추천된다.
    private Long id;

    @Column(length = 500, nullable = false)
    // @Column
    // - 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
    // - 사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.
    // - 문자열의 경우 VARCHAR(255)가 기본값인데, 사이즈를 500으로 늘리고 싶거나 (ex: title)
    // 타입을 TEXT로 변경하고 싶거나(ex: content) 등의 경우에 사용된다.
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    // @Builder
    // - 해당 클래스의 빌더 패턴 클래스를 생성
    // - 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // 수정 기능
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
