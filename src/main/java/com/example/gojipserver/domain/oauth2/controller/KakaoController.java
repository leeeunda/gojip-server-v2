package com.example.gojipserver.domain.oauth2.controller;

import com.example.gojipserver.domain.oauth2.dto.UserRequestDto.CreateAccessTokenRequest;
import com.example.gojipserver.domain.oauth2.service.KakaoService;
import com.example.gojipserver.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.example.gojipserver.domain.oauth2.dto.UserResponseDto.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Kakao", description = "카카오 로그인 관련 API")
public class KakaoController {
    private final KakaoService kakaoService;
    @PostMapping("/newToken")
    @Operation(summary = "새로운 토큰 발급", description = "리프레시 토큰을 이용하여 새로운 액세스 토큰을 발급합니다.")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "리프레시 토큰을 담은 DTO")
    public ApiResponse<CreatedAccessTokenResponse >  createNewAccessToken(@RequestBody CreateAccessTokenRequest createAccessTokenRequest) {
        CreatedAccessTokenResponse newAccessToken = kakaoService.createNewAccessToken(createAccessTokenRequest.getRefreshToken());
        return ApiResponse.createSuccess(newAccessToken, "새로운 액세스 토큰 발급 성공");
    }

}
