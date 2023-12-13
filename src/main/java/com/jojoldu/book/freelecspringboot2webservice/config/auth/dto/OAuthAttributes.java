// 필자의 경우 OAuthAttributes는 Dto로 보기 때문에 config.auth.dto 패키지를 만들어 해당 패키지에 생성했다.

package com.jojoldu.book.freelecspringboot2webservice.config.auth.dto;

import com.jojoldu.book.freelecspringboot2webservice.domain.user.Role;
import com.jojoldu.book.freelecspringboot2webservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributeKey, String name,
                           String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // of()
    // - OAuth2User에서 반환하는 사용자 정보는 Map이기 대문에 값 하나하나를 변환해야만 한다.
    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributionName,
                                     Map<String, Object> attributes) {


        // 구글 로그인을 등록하면서 대부분 코드가 확장성 있게 작성되었다 보니 네이버는 쉽게 등록 가능하다.
        // OAuthAttributes에 다음과 같이 네이버인지 판단하는 코드와 네이버 생성자만 추가해 주면 된다.
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }

        return ofGoogle(userNameAttributionName, attributes);
    }

    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // 구글 로그인을 등록하면서 대부분 코드가 확장성 있게 작성되었다 보니 네이버는 쉽게 등록 가능하다.
    // OAuthAttributes에 다음과 같이 네이버인지 판단하는 코드와 네이버 생성자만 추가해 주면 된다.
    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(response)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    // toEntity()
    // - User 엔티티를 생성한다.
    // - OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때이다.
    // - 가입할 때의 기본 권한을 GUEST로 주기 위해서 role 빌더값에는 Role.GUEST를 사용한다.
    // - OAuthAttributes 클래스 생성이 끝났으면 같은 패키지에 SessionUser 클래스를 생성한다.
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
