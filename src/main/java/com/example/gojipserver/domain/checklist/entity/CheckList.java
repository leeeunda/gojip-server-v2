package com.example.gojipserver.domain.checklist.entity;

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
    private Address address;

    private int deposit; //보증금

    private int monthlyCost; //월세

    private int managementCost; //관리비

    @Embedded
    private ManagementCostIncludeOption managementCostIncludeOption; //관리비 포함 옵션

    @Embedded
    private Status status;

    @Embedded
    private Noise noise; //소음

    @Embedded
    private RoomOption roomOption;

    private int area; //평수

    private int stationDistance; //역과의 거리

    private String text; //이미지 설명

    @ManyToOne(fetch = LAZY)
    private Collection collection;


}
