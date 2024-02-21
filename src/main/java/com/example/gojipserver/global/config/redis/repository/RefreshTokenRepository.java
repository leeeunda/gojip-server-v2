package com.example.gojipserver.global.config.redis.repository;

import com.example.gojipserver.global.config.redis.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
