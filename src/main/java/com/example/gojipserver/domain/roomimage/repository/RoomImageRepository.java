package com.example.gojipserver.domain.roomimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gojipserver.domain.roomimage.entity.RoomImage;

public interface RoomImageRepository extends JpaRepository<RoomImage, Long> {
}