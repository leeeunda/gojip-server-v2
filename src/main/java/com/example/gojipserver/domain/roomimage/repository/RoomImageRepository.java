package com.example.gojipserver.domain.roomimage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gojipserver.domain.roomimage.entity.RoomImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomImageRepository extends JpaRepository<RoomImage, Long> {

    // 게시글 저장 시 연관관계 매핑
    @Query("SELECT roomImage FROM RoomImage roomImage " +
            "WHERE roomImage.id in :list")
    List<RoomImage> findAllRoomImagesById(@Param("list") final List<Long> roomImageIds);

}