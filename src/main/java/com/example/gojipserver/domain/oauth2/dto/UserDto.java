package com.example.gojipserver.domain.oauth2.dto;

import com.example.gojipserver.domain.user.entity.Role;
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
        private String nickname;
        private Role role;
        private String accessToken;
        private String refreshToken;

        public void setToken(String accessToken,String refreshToken) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
        }
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class AccessTokenDto {
        private String email;
        private String accessToken;
    }
}
