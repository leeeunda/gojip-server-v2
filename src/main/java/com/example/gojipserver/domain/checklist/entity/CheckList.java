package com.example.gojipserver.domain.checklist.entity;

import com.example.gojipserver.domain.address.entity.Address;
import com.example.gojipserver.domain.checklist.entity.bathroomstatus.BathRoomStatus;
import com.example.gojipserver.domain.checklist.entity.cost.Cost;
import com.example.gojipserver.domain.checklist.entity.option.InnerOption;
import com.example.gojipserver.domain.checklist.entity.option.OuterOption;
import com.example.gojipserver.domain.checklist.entity.roomcondition.RoomCondition;
import com.example.gojipserver.domain.checklist.entity.roomstatus.RoomStatus;
import com.example.gojipserver.domain.collection.entity.Collection;
import com.example.gojipserver.domain.user.entity.User;
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
    @Column(name = "check_list_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @ManyToOne(fetch = LAZY)
    private Collection collection;

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
    



}
