package com.example.gojipserver.domain.oauth2.dto;

import lombok.Getter;

public class UserRequestDto {
    @Getter
    public static class CreateAccessTokenRequest {
        private String refreshToken;
    }
}
