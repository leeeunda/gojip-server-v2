package com.example.gojipserver.domain.checklist.entity;

import com.example.gojipserver.domain.checklist_collection.entity.CheckListCollection;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import com.example.gojipserver.domain.checklist.entity.bathroomstatus.BathRoomStatus;
import com.example.gojipserver.domain.checklist.entity.cost.Cost;
import com.example.gojipserver.domain.checklist.entity.option.InnerOption;
import com.example.gojipserver.domain.checklist.entity.option.OuterOption;
import com.example.gojipserver.domain.checklist.entity.roomcondition.RoomCondition;
import com.example.gojipserver.domain.checklist.entity.roomstatus.RoomStatus;
import com.example.gojipserver.domain.roomimage.entity.RoomImage;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.global.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CheckList extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "check_list_id")
    private Long id;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "room_address_id")
    private RoomAddress roomAddress;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "checkList", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CheckListCollection> checkListCollections = new ArrayList<>();

    @OneToMany(mappedBy = "checkList",orphanRemoval = true, cascade = CascadeType.ALL)
    private List<RoomImage> roomImages = new ArrayList<>();

    @Embedded
    private Cost cost; //비용

    @Embedded
    private RoomCondition roomCondition; //집 조건

    @Embedded
    private RoomStatus roomStatus; //방 상태

    @Embedded
    private BathRoomStatus bathRoomStatus; //화장실 상태

    @Embedded
    private InnerOption innerOption; //내부 옵션

    @Embedded
    private OuterOption outerOption; //외부 옵션

    private String note; //추가 사항

    private String imgDescription; //이미지 설명

    // 연관관계 편의 메서드

    public void addCheckListCollection(CheckListCollection checkListCollection) {
        this.checkListCollections.add(checkListCollection);
        checkListCollection.setCheckList(this);
    }

    @Builder
    public CheckList(RoomAddress roomAddress, User user, List<CheckListCollection> checkListCollections, List<RoomImage> roomImages, Cost cost, RoomCondition roomCondition, RoomStatus roomStatus, BathRoomStatus bathRoomStatus, InnerOption innerOption, OuterOption outerOption, String note, String imgDescription) {
        this.roomAddress = roomAddress;
        this.user = user;
        this.checkListCollections = checkListCollections;
        this.roomImages = roomImages;
        this.cost = cost;
        this.roomCondition = roomCondition;
        this.roomStatus = roomStatus;
        this.bathRoomStatus = bathRoomStatus;
        this.innerOption = innerOption;
        this.outerOption = outerOption;
        this.note = note;
        this.imgDescription = imgDescription;
    }



}
