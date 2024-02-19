package com.example.gojipserver.domain.oauth2.controller;

import com.example.gojipserver.domain.oauth2.dto.UserDto.*;
import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.domain.oauth2.service.KakaoService;
import com.example.gojipserver.domain.oauth2.service.OAuthService;
import com.example.gojipserver.global.config.security.jwt.JwtTokenProvider;
import com.example.gojipserver.global.response.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

@RestController
@RequestMapping
@RequiredArgsConstructor
@Slf4j
public class KakaoController {
    private final KakaoService kakaoService;
    private final OAuthService oauthService;

    @PostMapping("/login/kakao")
    public ApiResponse<UserInfoDto> login(@RequestHeader String token,HttpServletResponse response) {
        UserInfoDto userInfo = kakaoService.getUserProfileByToken(token);
        String accessToken = oauthService.getToken(userInfo,response);
        userInfo.setAccessToken(accessToken);
        return ApiResponse.createSuccess(userInfo);
    }

    @GetMapping("/user-details")
    public ResponseEntity<UserDetails> getUserDetails(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        log.info("principal : {}", userPrincipal);
        return ResponseEntity.ok(userPrincipal);
    }

    @PostMapping
    public void logout(HttpSession session) {
        String token = (String) session.getAttribute("access_token");
        String reqURL = "https://kapi.kakao.com/v1/user/logout";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String result = "";
            String line = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
