package com.example.gojipserver.domain.oauth2.dto;

import com.example.gojipserver.domain.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserResponseDto {
    @Getter
    @AllArgsConstructor
    public static class CreatedAccessTokenResponse {
        private String accessToken;
    }
}
