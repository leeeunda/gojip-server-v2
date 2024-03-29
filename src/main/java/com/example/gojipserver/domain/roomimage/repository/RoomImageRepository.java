package com.example.gojipserver.domain.roomimage.repository;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.gojipserver.domain.roomimage.entity.RoomImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface RoomImageRepository extends JpaRepository<RoomImage, Long> {

    // 게시글 저장 시 연관관계 매핑
    @Query("SELECT roomImage FROM RoomImage roomImage " +
            "WHERE roomImage.id in :list")
    List<RoomImage> findAllRoomImagesById(@Param("list") final List<Long> roomImageIds);

    @Query("SELECT ri FROM RoomImage ri WHERE ri.checkList IS NULL")
    List<RoomImage> findAllRoomImagesByNull();

    List<RoomImage> findByCheckList_Id(Long checkListId);

    Optional<RoomImage> findByCheckList_IdAndIsThumbnailTrue(Long checkListId);
}