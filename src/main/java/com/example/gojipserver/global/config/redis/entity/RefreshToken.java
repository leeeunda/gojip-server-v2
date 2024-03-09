package com.example.gojipserver.global.config.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "refreshToken", timeToLive = 14440)
@AllArgsConstructor
public class RefreshToken {
    @Id
    private String refreshToken;
    private Long userId;

    public RefreshToken update(String refreshToken){
        this.refreshToken = refreshToken;
        return this;
    }
}
