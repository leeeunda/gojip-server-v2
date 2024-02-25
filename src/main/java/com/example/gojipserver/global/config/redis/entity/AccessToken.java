package com.example.gojipserver.global.config.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@RedisHash(value = "accessToken", timeToLive = 14440)
@AllArgsConstructor
public class AccessToken {
    @Id
    private String accessToken;
    private String status;
}
