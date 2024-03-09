package com.example.gojipserver.global.config.redis.repository;

import com.example.gojipserver.global.config.redis.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByUserId(Long userId);
}
