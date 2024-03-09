package com.example.gojipserver.domain.like.repository;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.like.entity.Like;
import com.example.gojipserver.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByCheckListAndUser(CheckList checkList, User user);
    int countByCheckList(CheckList checkList);
}
