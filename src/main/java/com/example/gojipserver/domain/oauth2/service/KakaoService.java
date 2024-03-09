package com.example.gojipserver.domain.oauth2.service;

import com.example.gojipserver.domain.oauth2.dto.UserResponseDto;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.domain.user.repository.UserRepository;
import com.example.gojipserver.global.config.jwt.JwtTokenProvider;
import com.example.gojipserver.global.config.redis.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.gojipserver.domain.oauth2.dto.UserResponseDto.*;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public CreatedAccessTokenResponse createNewAccessToken(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        Long userId = refreshTokenRepository.findById(refreshToken).orElseThrow(() -> new IllegalArgumentException("리프레시토큰이 존재하지않습니다.")).getUserId();

        return new CreatedAccessTokenResponse(jwtTokenProvider.createAccessToken(userId));
    }
}
