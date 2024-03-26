package com.example.gojipserver.domain.checklist.repository;

import com.example.gojipserver.domain.checklist.dto.*;
import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.roomaddress.dto.RoomAddressCheckListInfoDto;
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

    @Query("SELECT new com.example.gojipserver.domain.checklist.dto.CheckListRecentResponseDto(cl.id,ri.imgUrl,a.addressName) " +
            "FROM CheckList cl INNER JOIN cl.roomAddress a " +
            "left JOIN cl.roomImages ri " +
            "WHERE cl.user.id = :userId " +
            "order by cl.lastModifiedDate desc limit 3")
    List<CheckListRecentResponseDto> findTop3ByUserIdOrderByLastModifiedDateDesc(long userId);

    @Query("SELECT new com.example.gojipserver.domain.checklist.dto.CheckListCityCountGetDto(a.city, count(a.city)) " +
            "FROM CheckList cl INNER JOIN cl.roomAddress a " +
            "GROUP BY a.city " +
            "ORDER BY count(a.city) DESC " +
            "limit 7")
    List<CheckListCityCountGetDto> findCityCountTop7();

    @Query("SELECT new com.example.gojipserver.domain.checklist.dto.CheckListCityAllGetDto(a.addressName,ri.imgUrl,avg(cl.rating),count(a.addressName)) " +
            "FROM CheckList cl INNER JOIN cl.roomAddress a " +
            "left JOIN cl.roomImages ri " +
            "WHERE a.city = :city " +
            "group by a.addressName")
    Page<CheckListCityAllGetDto> findAllCity(String city, Pageable pageable);

    @Query("SELECT new com.example.gojipserver.domain.checklist.dto.CheckListSummaryGetDto(cl.checkListName,ri.imgUrl,cl.rating,cl.likeCount) " +
            "FROM CheckList cl INNER JOIN cl.roomAddress a " +
            "left JOIN cl.roomImages ri " +
            "WHERE a.latitude = :latitude AND a.longitude = :longitude")
    Page<CheckListSummaryGetDto> findCheckListSummary(String latitude, String longitude, Pageable pageable);

    @Query("SELECT new com.example.gojipserver.domain.roomaddress.dto.RoomAddressCheckListInfoDto(a.addressName,count(a.addressName),AVG(cl.rating)) " +
            "FROM CheckList cl INNER JOIN cl.roomAddress a " +
            "WHERE a.latitude = :latitude AND a.longitude = :longitude " +
            "group by a.addressName")
    RoomAddressCheckListInfoDto findCheckListByRoomAddress(String latitude, String longitude);


}

