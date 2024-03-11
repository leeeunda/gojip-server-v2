package com.example.gojipserver.domain.checklist.entity;

import com.example.gojipserver.domain.checklist.dto.CheckListUpdateDto;
import com.example.gojipserver.domain.checklist.entity.cost.ManagementCostOption;
import com.example.gojipserver.domain.checklist.entity.cost.PropertyType;
import com.example.gojipserver.domain.checklist.entity.option.InnerOption;
import com.example.gojipserver.domain.checklist.entity.option.OuterOption;
import com.example.gojipserver.domain.checklist.entity.room.*;
import com.example.gojipserver.domain.checklist_collection.entity.CheckListCollection;
import com.example.gojipserver.domain.like.entity.Like;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import com.example.gojipserver.domain.roomimage.entity.RoomImage;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.global.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class  CheckList extends BaseTimeEntity {

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

    @OneToMany(mappedBy = "checkList")
    private List<CheckListCollection> checkListCollections = new ArrayList<>();

    @OneToMany(mappedBy = "checkList", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<RoomImage> roomImages = new ArrayList<>();

    @OneToMany(mappedBy = "checkList")
    private List<Like> likes = new ArrayList<>();

    // 비용
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType; //매물형태
    private int deposit; //보증금
    // TODO: 3가지 필드가 아닌 cost라는 하나의 필드로 관리할수도 있음
    private int monthlyCost; //월세비용
    private int charterCost; //전세비용
    private int tradingCost; //매매비용

    // 관리비 포함 옵션
    @OneToMany(mappedBy = "checkList", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ManagementCostOption> managementCostOptions = new HashSet<>();

    // 집 조건
    private int area; //평수

    @Enumerated(EnumType.STRING)
    private BuildingStatus buildingStatus; //건물상태

    private int stationDistance; //역과의 거리

    @OneToMany(mappedBy = "checkList", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<Noise> noises = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Light light; //채광

    @Enumerated(EnumType.STRING)
    private BoilerType boilerType; //보일러

    @OneToMany(mappedBy = "checkList", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<RoomStatus> roomStatuses = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private WaterPressureStatus waterPressureStatus; //수압

    @Enumerated(EnumType.STRING)
    private HotWaterStatus hotWaterStatus; //온수

    @Enumerated(EnumType.STRING)
    private TileStatus tileStatus; //타일

    //내부 옵션
    @OneToMany(mappedBy = "checkList", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<InnerOption> innerOptions = new HashSet<>();

    //외부 옵션
    @OneToMany(mappedBy = "checkList", orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<OuterOption> outerOptions = new HashSet<>();

    //기타
    private String note; //추가 사항
    private String imgDescription; //이미지 설명
    private int likeCount;

    private String checkListName;
    private double rating; // TODO: enum으로 만들 수도 있음


    // 연관관계 편의 메서드
    public void addCheckListCollection(CheckListCollection checkListCollection) {
        this.checkListCollections.add(checkListCollection);
        checkListCollection.registerCheckList(this);
    }

    public void addRoomImage(RoomImage roomImage) {
        this.roomImages.add(roomImage);
        roomImage.registerToCheckList(this);
    }
    public void addLikeInCheckList(Like like){
        this.likes.add(like);
        like.registerCheckList(this);
    }
    public void updateLikeCount(int likeCount){
        this.likeCount = likeCount;
    }
}
