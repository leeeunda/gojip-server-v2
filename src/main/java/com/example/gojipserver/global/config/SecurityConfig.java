package com.example.gojipserver.global.config;

import com.example.gojipserver.domain.oauth2.handler.OAuth2SuccessHandler;
import com.example.gojipserver.domain.oauth2.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.example.gojipserver.domain.oauth2.service.CustomOAuth2UserService;
import com.example.gojipserver.domain.user.service.UserService;
import com.example.gojipserver.global.config.jwt.JwtAuthenticationFilter;
import com.example.gojipserver.global.config.jwt.JwtTokenProvider;

import com.example.gojipserver.global.config.redis.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private static final String[] AUTH_WHITELIST = {
            "/oauth2/**", "/login", "/swagger-ui/**", "/api-docs", "/swagger-ui-custom.html",
            "/v3/api-docs/**", "/api-docs/**", "/swagger-ui.html","/login/kakao", "/newToken",
    }; // 인증 필터를 거치지 않는 경로

    CorsConfigurationSource corsConfigurationSource() {
        return request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowedOriginPatterns(Collections.singletonList("http://localhost:3000")); // ⭐️ 허용할 origin
            config.setAllowCredentials(true);
            return config;
        };
    }


    private final JwtTokenProvider jwtTokenProvider;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserService userService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(corsconfig -> corsconfig.configurationSource(corsConfigurationSource()))
                .csrf(csrfconfig -> csrfconfig.disable())
                .formLogin(formLogin -> formLogin.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .rememberMe(rememberMe -> rememberMe.disable())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 사용하지 않기 때문에 세션 설정을 STATELESS로 설정
        );

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);

        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .anyRequest().authenticated() // 나머지 요청은 모두 인증 필요
        );

        http.oauth2Login(oauth -> oauth
                .authorizationEndpoint(authorization -> authorization
                                .authorizationRequestRepository(new OAuth2AuthorizationRequestBasedOnCookieRepository())
                                .baseUri("/oauth2/authorize")
                                )
                .redirectionEndpoint(redirection -> redirection.baseUri("/oauth2/callback/*"))
                .successHandler(new OAuth2SuccessHandler(jwtTokenProvider,refreshTokenRepository, new OAuth2AuthorizationRequestBasedOnCookieRepository(), userService))
                .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
        );

        http.exceptionHandling(authentication -> authentication
                .defaultAuthenticationEntryPointFor(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED),new AntPathRequestMatcher("/**")));

        return http.build();
    }

}
