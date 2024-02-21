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
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;

    public String getRefreshToken(UserDto.UserInfoDto userInfoDto){
        Authentication authentication = getAuthentication(userInfoDto.getEmail());
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        final String refreshToken =  jwtTokenProvider.createRefreshToken(userInfoDto.getEmail(), userPrincipal.getAuthorities());
        return refreshToken;
    }

    public String getAccessToken(UserDto.UserInfoDto userInfoDto, String refreshToken){
        Authentication authentication = getAuthentication(userInfoDto.getEmail());
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        final String accessToken = jwtTokenProvider.createAccessToken(refreshToken, userPrincipal.getAuthorities());
        return accessToken;
    }

    private Authentication getAuthentication(String email){
        UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userPrincipal, "", userPrincipal.getAuthorities());
    }
}
