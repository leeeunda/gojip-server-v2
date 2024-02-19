package com.example.gojipserver.domain.oauth2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class UserInfoDto{
        private Long id;
        private String email;
        private String accessToken;
        private String refreshToken;

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
