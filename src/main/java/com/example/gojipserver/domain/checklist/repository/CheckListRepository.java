package com.example.gojipserver.domain.checklist.repository;

import com.example.gojipserver.domain.checklist.dto.CheckListCollectionGetDto;
import com.example.gojipserver.domain.checklist.entity.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CheckListRepository extends JpaRepository<CheckList, Long> {
    List<CheckList> findByUserIdOrderByCreatedDateDesc(Long userId);

    @Query("SELECT new com.example.gojipserver.domain.checklist.dto.CheckListCollectionGetDto(clc.checkList, a) " +
            "FROM CheckListCollection clc INNER JOIN clc.checkList.roomAddress a " +
            "WHERE clc.collection.id = :collectionId")
    List<CheckListCollectionGetDto> findByCollectionId(@Param("collectionId") Long collectionId);

    List<CheckList> findTop3ByUserIdOrderByLastModifiedDateDesc(long userId);
}
