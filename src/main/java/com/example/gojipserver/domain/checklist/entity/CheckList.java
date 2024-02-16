package com.example.gojipserver.domain.checklist.entity;

import com.example.gojipserver.domain.checklist.entity.bathroomstatus.*;
import com.example.gojipserver.domain.checklist.entity.roomcondition.Building;
import com.example.gojipserver.domain.checklist.entity.roomstatus.Boiler;
import com.example.gojipserver.domain.checklist.entity.roomstatus.Light;
import com.example.gojipserver.domain.checklist_collection.entity.CheckListCollection;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
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

    @OneToMany(mappedBy = "checkList",orphanRemoval = true, cascade = CascadeType.ALL)
    private List<RoomImage> roomImages = new ArrayList<>();

    // 비용
    private int deposit; //보증금
    private int monthlyCost; //월세
    private int managementCost; //관리비

    // 관리비 포함 옵션
    private boolean waterCost; //수도세
    private boolean heatingCost; //난방비
    private boolean electricCost; //전기세
    private boolean internetCost; //인터넷비

    // 집 조건
    private int area; //평수

    @Enumerated(EnumType.STRING)
    private Building building; //건물상태

    private int stationDistance; //역과의 거리

    private boolean floor; //층간소음
    private boolean wall; //벽간소음
    private boolean outside; //외부소음

    //방 상태
    @Enumerated(EnumType.STRING)
    private Light light; //채광

    @Enumerated(EnumType.STRING)
    private Boiler boiler; //보일러

    private boolean mold; //곰팡이
    private boolean wind; //옷풍
    private boolean bug; //벌레
    private boolean wallpaperPollution; //벽지오염

    //화장실 상태
    @Enumerated(EnumType.STRING)
    private Toilet toilet; //변기

    @Enumerated(EnumType.STRING)
    private WashStand washstand; //세면대

    @Enumerated(EnumType.STRING)
    private Sink sink; //싱크대

    @Enumerated(EnumType.STRING)
    private ShowerHead showerHead; //샤워기

    @Enumerated(EnumType.STRING)
    private HotWater hotWater; //온수

    @Enumerated(EnumType.STRING)
    private Tile tile; //타일

    //내부 옵션
    private boolean airConditioner; //에어컨
    private boolean refrigerator; //냉장고
    private boolean washingMachine; //세탁기
    private boolean microwave; //전자레인지
    private boolean gasRange; //가스레인지
    private boolean induction; //인덕션
    private boolean bed; //침대
    private boolean desk; //책상
    private boolean closet; //옷장
    private boolean tv; //TV
    private boolean wifiRouter; //공유기
    private boolean computer; //컴퓨터
    private boolean doorLock; //도어락
    private boolean ventilator; //환풍기

    //외부 옵션
    private boolean parkingLot; //주차장
    private boolean cctv; //cctv
    private boolean elevator; //엘리베이터
    private boolean managementOffice; //관리실
    private boolean commonEntrance; //공동현관
    private boolean separateDischargeSpace; //분리배출공간

    //기타
    private String note; //추가 사항
    private String imgDescription; //이미지 설명

    @Builder
    public CheckList(RoomAddress roomAddress, User user, List<RoomImage> roomImages, int deposit, int monthlyCost, int managementCost, boolean waterCost, boolean heatingCost, boolean electricCost, boolean internetCost, int area, Building building, int stationDistance, boolean floor, boolean wall, boolean outside, Light light, Boiler boiler, boolean mold, boolean wind, boolean bug, boolean wallpaperPollution, Toilet toilet, WashStand washstand, Sink sink, ShowerHead showerHead, HotWater hotWater, Tile tile, boolean airConditioner, boolean refrigerator, boolean washingMachine, boolean microwave, boolean gasRange, boolean induction, boolean bed, boolean desk, boolean closet, boolean tv, boolean wifiRouter, boolean computer, boolean doorLock, boolean ventilator, boolean parkingLot, boolean cctv, boolean elevator, boolean managementOffice, boolean commonEntrance, boolean separateDischargeSpace, String note, String imgDescription) {
        this.roomAddress = roomAddress;
        this.user = user;
        this.roomImages = roomImages;
        this.deposit = deposit;
        this.monthlyCost = monthlyCost;
        this.managementCost = managementCost;
        this.waterCost = waterCost;
        this.heatingCost = heatingCost;
        this.electricCost = electricCost;
        this.internetCost = internetCost;
        this.area = area;
        this.building = building;
        this.stationDistance = stationDistance;
        this.floor = floor;
        this.wall = wall;
        this.outside = outside;
        this.light = light;
        this.boiler = boiler;
        this.mold = mold;
        this.wind = wind;
        this.bug = bug;
        this.wallpaperPollution = wallpaperPollution;
        this.toilet = toilet;
        this.washstand = washstand;
        this.sink = sink;
        this.showerHead = showerHead;
        this.hotWater = hotWater;
        this.tile = tile;
        this.airConditioner = airConditioner;
        this.refrigerator = refrigerator;
        this.washingMachine = washingMachine;
        this.microwave = microwave;
        this.gasRange = gasRange;
        this.induction = induction;
        this.bed = bed;
        this.desk = desk;
        this.closet = closet;
        this.tv = tv;
        this.wifiRouter = wifiRouter;
        this.computer = computer;
        this.doorLock = doorLock;
        this.ventilator = ventilator;
        this.parkingLot = parkingLot;
        this.cctv = cctv;
        this.elevator = elevator;
        this.managementOffice = managementOffice;
        this.commonEntrance = commonEntrance;
        this.separateDischargeSpace = separateDischargeSpace;
        this.note = note;
        this.imgDescription = imgDescription;
    }
}
