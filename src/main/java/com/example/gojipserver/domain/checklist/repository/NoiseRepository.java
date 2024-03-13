package com.example.gojipserver.domain.checklist.repository;

import com.example.gojipserver.domain.checklist.entity.room.Noise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoiseRepository extends JpaRepository<Noise, Long> {
}
