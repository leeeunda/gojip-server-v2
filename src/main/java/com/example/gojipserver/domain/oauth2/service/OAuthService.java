package com.example.gojipserver.domain.oauth2.service;

import com.example.gojipserver.domain.oauth2.dto.UserDto;
import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.domain.user.repository.UserRepository;
import com.example.gojipserver.global.config.security.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final KakaoService kakaoService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final CustomUserDetailsService userDetailsService;

    public String getToken(UserDto.UserInfoDto userInfoDto, HttpServletResponse response){
        Authentication authentication = getAuthentication(userInfoDto.getEmail());
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        System.out.println(userPrincipal.getAuthorities());
        final String accessToken = jwtTokenProvider.createAccessToken(userInfoDto.getEmail(), userPrincipal.getAuthorities());
        jwtTokenProvider.sendAccessAndRefreshToken(response, accessToken,"");
        // 리프레시 토큰 만들기

        User user = userRepository.findById(userInfoDto.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        // 리프레시 저장
        // 리프레시 업데이트

        // 리프레시 쿠키설정
        return accessToken;
    }

    private Authentication getAuthentication(String email){
        UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userPrincipal, "", userPrincipal.getAuthorities());
    }
}
