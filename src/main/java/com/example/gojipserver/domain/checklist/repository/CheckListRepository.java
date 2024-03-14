package com.example.gojipserver.domain.checklist.repository;

import com.example.gojipserver.domain.checklist.dto.CheckListCityAllGetDto;
import com.example.gojipserver.domain.checklist.dto.CheckListCityCountGetDto;
import com.example.gojipserver.domain.checklist.dto.CheckListCollectionGetDto;
import com.example.gojipserver.domain.checklist.entity.CheckList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT new com.example.gojipserver.domain.checklist.dto.CheckListCityCountGetDto(a.city, count(a.city)) " +
            "FROM CheckList cl INNER JOIN cl.roomAddress a " +
            "GROUP BY a.city " +
            "ORDER BY count(a.city) DESC " +
            "limit 7")
    List<CheckListCityCountGetDto> findCityCountTop7();

    @Query("SELECT new com.example.gojipserver.domain.checklist.dto.CheckListCityAllGetDto(a.addressName,'이미지 준비중',0,count(a.addressName)) " +
            "FROM CheckList cl INNER JOIN cl.roomAddress a " +
            "WHERE a.city = :city " +
            "group by a.addressName")
    Page<CheckListCityAllGetDto> findAllCity(String city, Pageable pageable);

}
