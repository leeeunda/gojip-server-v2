package com.example.gojipserver.global.config;

import com.example.gojipserver.domain.oauth2.service.CustomOAuth2UserService;
import com.example.gojipserver.domain.oauth2.service.CustomUserDetailsService;
import com.example.gojipserver.domain.oauth2.handler.OAuth2AuthenticationSuccessHandler;
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
    private final CustomUserDetailsService customUserDetailsService;
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
                .requestMatchers("/oauth2/**").permitAll() // oauth2로 시작하는 모든 요청은 인증 없이 접근 가능
                .requestMatchers("/login").permitAll() // login으로 시작하는 모든 요청은 인증 없이 접근 가능
                .requestMatchers("/swagger-ui/index.html").permitAll()
//                .anyRequest().authenticated() // 나머지 요청은 모두 인증 필요
        );

        http.userDetailsService(customUserDetailsService);

        http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
