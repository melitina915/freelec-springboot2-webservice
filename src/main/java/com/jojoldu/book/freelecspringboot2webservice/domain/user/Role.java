// 각 사용자의 권한을 관리할 Enum 클래스 Role을 생성한다.

package com.jojoldu.book.freelecspringboot2webservice.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");
    // 스프링 시큐리티에서는 권한 코드에 항상 ROLE_이 앞에 있어야만 한다.
    // 그래서 코드별 키 값을 ROLE_GUEST, ROLE_USER 등으로 지정한다.

    private final String key;
    private final String title;
}
