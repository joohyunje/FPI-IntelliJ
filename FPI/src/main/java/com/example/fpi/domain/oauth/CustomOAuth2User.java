package com.example.fpi.domain.oauth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

//만든 목적
// OAuth2User 인터페이스를 구현하여, 사용자 정보와 추가적인 사용자 세부 정보를 저장하고 우리가 정한 양식으로 간편하게 들고 나올 수 있음

@Getter
//모든 필드에 대한 매개변수를 갖는 생성자를 생성,초기화 생성자임
@AllArgsConstructor
public class CustomOAuth2User implements OAuth2User {

    private final OAuth2User oauth2User;
    private final String userId;
    private final String userName;
    private final String userImg;

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return oauth2User.getAuthorities();
    }

    @Override
    public String getName() {
        return userName;
    }
    public String getId() {
        return userId;
    }
}
