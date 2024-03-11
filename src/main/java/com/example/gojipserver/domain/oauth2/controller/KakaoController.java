package com.example.gojipserver.domain.oauth2.controller;

import com.example.gojipserver.domain.oauth2.dto.UserRequestDto.CreateAccessTokenRequest;
import com.example.gojipserver.domain.oauth2.service.KakaoService;
import com.example.gojipserver.global.config.jwt.JwtTokenProvider;
import com.example.gojipserver.global.response.ApiResponse;
import com.example.gojipserver.global.util.CookieUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static com.example.gojipserver.domain.oauth2.dto.UserResponseDto.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Kakao", description = "카카오 로그인 관련 API")
public class KakaoController {
    public static final String REFRESH_TOKEN_COOKIE_NAME = "refresh_token";
    private final KakaoService kakaoService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/newToken")
    @Operation(summary = "새로운 토큰 발급", description = "리프레시 토큰을 이용하여 새로운 액세스 토큰을 발급합니다.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "리프레시 토큰을 담은 DTO")
    public ApiResponse<CreatedAccessTokenResponse >  createNewAccessToken(@RequestBody CreateAccessTokenRequest createAccessTokenRequest, HttpServletRequest request) throws IOException {
        CreatedAccessTokenResponse newAccessToken = kakaoService.createNewAccessToken(request, createAccessTokenRequest.getRefreshToken());
        return ApiResponse.createSuccess(newAccessToken, "새로운 액세스 토큰 발급 성공");
    }

    @PostMapping("/kakao/logout")
    @Operation(summary = "로그아웃", description = "로그아웃을 수행합니다.")
    public ApiResponse<?> logout(HttpServletRequest request, HttpServletResponse response){
        String accessToken = jwtTokenProvider.getJwtFromRequest(request, "Authorization");
        kakaoService.saveAccessToken(accessToken);
        CookieUtil.deleteCookie(request, response, REFRESH_TOKEN_COOKIE_NAME);
        return ApiResponse.createSuccess(null,"로그아웃 성공");
    }

}
