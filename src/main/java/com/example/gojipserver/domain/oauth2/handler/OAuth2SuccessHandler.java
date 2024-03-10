package com.example.gojipserver.domain.oauth2.handler;

import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.domain.oauth2.repository.OAuth2AuthorizationRequestBasedOnCookieRepository;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.domain.user.service.UserService;
import com.example.gojipserver.global.config.jwt.JwtTokenProvider;
import com.example.gojipserver.global.config.redis.entity.RefreshToken;
import com.example.gojipserver.global.config.redis.repository.RefreshTokenRepository;
import com.example.gojipserver.global.entity.ExpireTime;
import com.example.gojipserver.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    public static final String REDIRECT_PATH = "/oauth2/redirect";

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final OAuth2AuthorizationRequestBasedOnCookieRepository authorizationRequestRepository;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("OAuth2SuccessHandler.onAuthenticationSuccess() 실행 - OAuth2 로그인 성공");
        UserPrincipal oAuth2User = (UserPrincipal) authentication.getPrincipal();

        String refreshToken = jwtTokenProvider.createRefreshToken(oAuth2User.getId());
        saveRefreshToken(oAuth2User.getId(), refreshToken);
        addRefreshTokenToCookie(request, response, refreshToken);

        String accessToken = jwtTokenProvider.createAccessToken(oAuth2User.getId());
        String targetUrl = getTargetUrl(accessToken);

        clearAuthenticationAttributes(request, response);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private void saveRefreshToken(Long userId, String newRefreshToken) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId)
                .map(entity -> entity.update(newRefreshToken))
                .orElse(new RefreshToken(newRefreshToken,userId));

        refreshTokenRepository.save(refreshToken);
    }

    private void addRefreshTokenToCookie(HttpServletRequest request, HttpServletResponse response, String refreshToken) {
        int cookieMaxAge = (int) ExpireTime.REFRESH_TOKEN_EXPIRE_TIME;

        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        CookieUtil.addCookie(response, REFRESH_TOKEN_COOKIE_NAME, refreshToken, cookieMaxAge);
    }

    private void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
        authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
    }

    private String getTargetUrl(String token) {
        return UriComponentsBuilder.fromUriString(REDIRECT_PATH)
                .queryParam("token", token)
                .build()
                .toUriString();
    }
}
