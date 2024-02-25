package com.example.gojipserver.domain.oauth2.controller;

import com.example.gojipserver.domain.oauth2.dto.UserDto.*;
import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.domain.oauth2.service.KakaoService;
import com.example.gojipserver.domain.oauth2.service.OAuthService;
import com.example.gojipserver.global.config.jwt.JwtTokenProvider;
import com.example.gojipserver.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Kakao", description = "카카오 로그인 API")
public class KakaoController {
    private final KakaoService kakaoService;
    private final OAuthService oauthService;
    private final JwtTokenProvider jwtTokenProvider;

    // 카카오 로그인
    @PostMapping("/login/kakao")
    @Operation(summary = "카카오 로그인", description = "카카오 로그인 api")
    @Parameter(name = "token", description = "카카오에서 받은 AccessToken을 헤더에 넣습니다.")
    public ApiResponse<UserInfoDto> login(@RequestHeader String token,HttpServletResponse response) {
        UserInfoDto userInfo = kakaoService.getUserProfileByToken(token);
        String refreshToken = oauthService.getRefreshToken(userInfo.getEmail());
        String accessToken = oauthService.getAccessToken(userInfo.getEmail(), refreshToken);
        userInfo.setToken(accessToken,refreshToken);
        jwtTokenProvider.sendAccessAndRefreshToken(response,accessToken,refreshToken);
        return ApiResponse.createSuccess(userInfo);
    }

    // 유저 테스트
    @Hidden
    @GetMapping("/user-details")
    public ResponseEntity<UserDetails> getUserDetails(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        log.info("principal : {}", userPrincipal);
        return ResponseEntity.ok(userPrincipal);
    }

    // 카카오 로그아웃
    @Operation(summary = "카카오 로그아웃", description = "카카오 로그아웃 api")
    @Parameter(name = "Authorization", description = "Bearer + accessToken을 헤더에 넣습니다.")
    @Parameter(name = "Authorization-refresh", description = "Bearer + refreshToken을 헤더에 넣습니다.")
    @PostMapping("/logout/kakao")
    public ApiResponse<String> logout(HttpServletRequest request) {
        String accessToken = jwtTokenProvider.getJwtFromRequest(request, "Authorization");
        String refreshToken = jwtTokenProvider.getJwtFromRequest(request, "Authorization-refresh");
        oauthService.logout(accessToken,refreshToken);
        return ApiResponse.createSuccess("로그아웃 성공");
    }

    // 액세스토큰 갱신
    @Operation(summary = "AccessToken 갱신", description = "RefreshToken을 이용하여 AccessToken을 갱신합니다.")
    @Parameter(name = "Authorization-refresh", description = "Bearer + refreshToken을 헤더에 넣습니다.")
    @GetMapping("/refresh")
    public ApiResponse<?> refresh(HttpServletRequest request,HttpServletResponse response) {
        String refreshToken = jwtTokenProvider.getJwtFromRequest(request,"Authorization-refresh");
        try{
            jwtTokenProvider.validateToken(request,refreshToken);
            AccessTokenDto accessTokenDto = oauthService.updateAccessToken(refreshToken);
            return ApiResponse.createSuccess(accessTokenDto);
        }catch (Exception e) {
            return ApiResponse.create400Error(e.getMessage());
        }
    }
}
