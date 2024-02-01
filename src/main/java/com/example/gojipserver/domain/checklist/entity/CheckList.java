package com.example.gojipserver.domain.checklist.entity;

import com.example.gojipserver.domain.checklist.entity.status.Building;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "checklist_id")
    private Long id;

    @OneToOne
    private Address address; //주소

    @Embedded
    private Cost cost; //비용

    @Embedded
    private ManagementCostIncludeOption managementCostIncludeOption; //관리비 포함 옵션

    @Embedded
    private RoomCondition roomCondition; //집 조건

    @Embedded
    private Noise noise; //소음

    @Embedded
    private RoomStatus roomStatus; //방 상태

    @Embedded
    private BathRoomStatus bathRoomStatus; //화장실 상태

    @Embedded
    private InnerOption innerOption; //내부 옵션

    @Embedded
    private OuterOption outerOption; //외부 옵션

    private String note; //추가 사항

    // imgUrl 필요

    @ManyToOne(fetch = LAZY)
    private Collection collection;


}
