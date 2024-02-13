package com.example.gojipserver.global.config;

import com.example.gojipserver.domain.oauth2.service.CustomOAuth2UserService;
import com.example.gojipserver.domain.oauth2.service.CustomUserDetailsService;
import com.example.gojipserver.global.config.security.handler.OAuth2AuthenticationSuccessHandler;
import com.example.gojipserver.global.config.security.jwt.JwtAuthenticationFilter;
import com.example.gojipserver.global.config.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String[] AUTH_WHITELIST = {
            "/oauth2/**", "/login", "/swagger-ui/**", "/api-docs", "/swagger-ui-custom.html",
            "/v3/api-docs/**", "/api-docs/**", "/swagger-ui.html"
    }; // 인증 필터를 거치지 않는 경로

    private final CustomOAuth2UserService customOAuth2Service;
    private final CustomUserDetailsService customUserDetailsService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(corfconfig -> corfconfig.disable())
                .csrf(csrfconfig -> csrfconfig.disable())
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .rememberMe(rememberMe -> rememberMe.disable())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않기 때문에 세션 설정을 STATELESS로 설정
        );

        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated() // 나머지 요청은 모두 인증 필요
        );

        http.oauth2Login(oauth->oauth
                .authorizationEndpoint(authorization->authorization.baseUri("/oauth2/authorize")) // 인증 요청을 처리하는 Endpoint 설정
                .redirectionEndpoint(redirection->redirection.baseUri("/oauth2/callback/*")) // OAuth2 인증 서버로부터 리다이렉트를 받을 Endpoint 설정
                .userInfoEndpoint(userInfo->userInfo.userService(customOAuth2Service))
                .successHandler(oAuth2AuthenticationSuccessHandler) //성공시
//                .failureHandler(null) //실패시
        );
        http.userDetailsService(customUserDetailsService);

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
