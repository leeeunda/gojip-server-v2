package com.example.gojipserver.domain.oauth2.service;

import com.example.gojipserver.domain.oauth2.dto.UserDto;
import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.global.config.jwt.JwtTokenProvider;
import com.example.gojipserver.global.config.redis.entity.AccessToken;
import com.example.gojipserver.global.config.redis.entity.RefreshToken;
import com.example.gojipserver.global.config.redis.repository.AccessTokenRepository;
import com.example.gojipserver.global.config.redis.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenRepository accessTokenRepository;

    public String getRefreshToken(String email){
        Authentication authentication = getAuthentication(email);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        final String refreshToken =  jwtTokenProvider.createRefreshToken(email, userPrincipal.getAuthorities());
        return refreshToken;
    }

    public String getAccessToken(String email, String refreshToken){
        Authentication authentication = getAuthentication(email);
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        final String accessToken = jwtTokenProvider.createAccessToken(refreshToken, userPrincipal.getAuthorities());
        return accessToken;
    }

    private Authentication getAuthentication(String email){
        UserPrincipal userPrincipal = (UserPrincipal) userDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(userPrincipal, "", userPrincipal.getAuthorities());
    }

    public UserDto.AccessTokenDto updateAccessToken(String refreshToken){
        String userEmail = jwtTokenProvider.getUserEmail(refreshToken);
        String accessToken = getAccessToken(userEmail, refreshToken);
        UserDto.AccessTokenDto accessTokenDto = UserDto.AccessTokenDto.builder()
                .email(userEmail)
                .accessToken(accessToken)
                .build();
        return accessTokenDto;
    }

    public void logout(String accessToken,String refreshToken){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        RefreshToken findRefresh = refreshTokenRepository.findById(refreshToken).orElseThrow(() -> new RuntimeException("Refresh Token not found"));
        if(findRefresh.getEmail().equals(email)){
            refreshTokenRepository.delete(findRefresh);
        }
        AccessToken accessTokenLogout = new AccessToken(accessToken,"logout");
        accessTokenRepository.save(accessTokenLogout);
    }
}
