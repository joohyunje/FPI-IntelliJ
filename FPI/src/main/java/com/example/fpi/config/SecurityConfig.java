package com.example.fpi.config;

import com.example.fpi.domain.dto.user.UserDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.mapper.user.UserMapper;
import com.example.fpi.service.user.CustomOAuth2UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity //웹 보안 활성화 ,spring security
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final UserMapper userMapper;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // 전체 요청에 접근할 수 있도록 하는 코드
//        return http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll()).build();
        return http
//                csrf 프로젝트시 비활성화 해주기
//                cross site request forgery : csrf 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                //요청에 대한 인증 및 인가를 설정.
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )
                //로그인을 OAuth2으로 구성할 것이다.
                .oauth2Login(login->login
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService)
                        ).successHandler(authenticationSuccessHandler())


                )

                .logout(logout->logout
                                .logoutSuccessHandler((request, response, authentication)->{
//                            앱키 : REST API키 복붙
                                    String clientId="6d8634b2b70d1f0713825ca4b0c881e6";
                                    String logoutRedirectUri="http://localhost:8070";
                                    String logoutUri="https://kauth.kakao.com/oauth/logout?client_id="
                                            + clientId + "&logout_redirect_uri=" + logoutRedirectUri;
                                    response.sendRedirect(logoutUri);

                                })
                )

                .build();

    }
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, auth) -> {
            CustomOAuth2User customOAuth2User = (CustomOAuth2User) auth.getPrincipal();
            UserDTO user = userMapper.findByUserId(customOAuth2User.getUserId());
            HttpSession session = request.getSession();
            if(user.getRole().equals("new")){
                response.sendRedirect("/main/sign");
            }
            else {
//                홈페이지 이동시 헤더에서 계속 정보를 가지고 있어야 하기 때문에 session에 담아줌
                session.setAttribute("loginName", userMapper.findByUserId(user.getUserId()).getUserName());
                response.sendRedirect("/main");
            }
        };
    }
}