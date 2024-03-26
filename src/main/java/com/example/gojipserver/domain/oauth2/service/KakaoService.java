package com.example.gojipserver.domain.oauth2.service;

import com.example.gojipserver.global.config.jwt.JwtTokenProvider;
import com.example.gojipserver.global.config.redis.entity.AccessToken;
import com.example.gojipserver.global.config.redis.repository.AccessTokenRepository;
import com.example.gojipserver.global.config.redis.repository.RefreshTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.example.gojipserver.domain.oauth2.dto.UserResponseDto.*;

@Service
@RequiredArgsConstructor
public class KakaoService {
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenRepository accessTokenRepository;

    public CreatedAccessTokenResponse createNewAccessToken(HttpServletRequest request, String refreshToken) throws IOException {
        if (!jwtTokenProvider.validateToken(request,refreshToken)) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        Long userId = refreshTokenRepository.findById(refreshToken).orElseThrow(() -> new IllegalArgumentException("리프레시토큰이 존재하지않습니다.")).getUserId();

        return new CreatedAccessTokenResponse(jwtTokenProvider.createAccessToken(userId));
    }

    public void saveAccessToken(String accessToken) {
        AccessToken setAccessToken = new AccessToken(accessToken, "logout");
        accessTokenRepository.save(setAccessToken);
    }

}
