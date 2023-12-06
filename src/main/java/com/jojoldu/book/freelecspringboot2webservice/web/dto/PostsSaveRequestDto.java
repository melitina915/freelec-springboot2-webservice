// Request 데이터를 받을 Dto

// Dtos
// - Dto(Data Transfer Object)는 계층 간에 데이터 교환을 위한 객체를 이야기하며 Dtos는 이들의 영역을 얘기한다.
// - 예를 들어 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer에서 결과로 넘겨준 객체 등이 이들을 이야기한다.



// Controller와 Service에서 사용할 Dto 클래스

// Entity 클래스와 거의 유사한 형태임에도 Dto 클래스를 추가로 생성했다.
// 하지만, 절대로 Entity 클래스를 Request/Response 클래스로 사용해서는 안된다.

// Entity 클래스
// 데이터베이스와 맞닿은 핵심 클래스
// Entity 클래스를 기준으로 테이블이 생성되고, 스키마가 변경된다.
// 수많은 서비스 클래스나 비즈니스 로직들이 Entity 클래스를 기준으로 동작한다.
// Entity 클래스가 변경되면 여러 클래스에 영향을 끼치지만,
// Request와 Response용 Dto는 View를 위한 클래스라 정말 자주 변경이 필요하다.
// View Layer와 DB Layer의 역할 분리를 철저하게 하는 것이 좋다.
// 실제로 Controller에서 결과값으로 여러 테이블을 조인해서 줘야 할 경우가 빈번하므로 Entity 클래스만으로 표현하기가 어려운 경우가 많다.
// 꼭 Entity 클래스와 Controller에서 쓸 Dto는 분리해서 사용해야 한다.

package com.jojoldu.book.freelecspringboot2webservice.web.dto;

import com.jojoldu.book.freelecspringboot2webservice.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 등록 기능
@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;
    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
