package com.example.gojipserver.domain.like.repository;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.like.dto.LikePreviewResponseDto;
import com.example.gojipserver.domain.like.dto.LikeResponseDto;
import com.example.gojipserver.domain.like.entity.Like;
import com.example.gojipserver.domain.user.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.gojipserver.domain.like.dto.LikeResponseDto.*;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByCheckListAndUser(CheckList checkList, User user);
    int countByCheckList(CheckList checkList);
    @Query("SELECT new com.example.gojipserver.domain.like.dto.LikePreviewResponseDto(l.checkList.id, '대표이미지 준비중', '제목 준비중', l.checkList.roomAddress.addressName, 0.0, l.checkList.likeCount) " +
            "FROM Like l WHERE l.user = :user " +
            "ORDER BY l.createdDate DESC")
    Page<LikePreviewResponseDto> findLikePreviewResponseDto(@Param("user") User user, Pageable pageable);

}
