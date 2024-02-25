package com.example.gojipserver.global.config.redis.repository;

import com.example.gojipserver.global.config.redis.entity.AccessToken;
import org.springframework.data.repository.CrudRepository;

public interface AccessTokenRepository extends CrudRepository<AccessToken, String> {
}
