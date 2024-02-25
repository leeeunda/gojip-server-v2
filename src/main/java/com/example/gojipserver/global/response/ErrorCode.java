package com.example.gojipserver.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NON_LOGIN(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다.", 401),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.", 401),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.", 401),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.", 403),
    NO_BEARER_TOKEN(HttpStatus.UNAUTHORIZED, "Bearer 토큰이 필요합니다.", 401),
    LOGOUT_TOKEN(HttpStatus.UNAUTHORIZED, "로그아웃된 토큰입니다.", 401);

    private HttpStatus status;
    private String message;
    private int code;
}
