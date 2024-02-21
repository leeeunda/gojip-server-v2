package com.example.gojipserver.domain.checklist.dto;

import com.example.gojipserver.domain.checklist.entity.bathroomstatus.*;
import com.example.gojipserver.domain.checklist.entity.roomcondition.Building;
import com.example.gojipserver.domain.checklist.entity.roomstatus.Boiler;
import com.example.gojipserver.domain.checklist.entity.roomstatus.Light;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.user.entity.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CheckListSaveDto {

    // 컬렉션 정보
    private List<Long> collectionIdList;

    // 주소 정보
    private String addressName;
    private double latitude;
    private double longitude;

    // 이미지 PK 리스트
    private List<Long> roomImageIdList;

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

    private String note;
    private String imgDescription;

    public CheckList toEntity(User user, RoomAddress roomAddress) {
        return CheckList.builder()
                .roomAddress(roomAddress)
                .user(user)
                .deposit(deposit)
                .monthlyCost(monthlyCost)
                .managementCost(managementCost)
                .waterCost(waterCost)
                .heatingCost(heatingCost)
                .electricCost(electricCost)
                .internetCost(internetCost)
                .area(area)
                .building(building)
                .stationDistance(stationDistance)
                .floor(floor)
                .wall(wall)
                .outside(outside)
                .light(light)
                .boiler(boiler)
                .mold(mold)
                .wind(wind)
                .bug(bug)
                .wallpaperPollution(wallpaperPollution)
                .toilet(toilet)
                .washstand(washstand)
                .sink(sink)
                .showerHead(showerHead)
                .hotWater(hotWater)
                .tile(tile)
                .airConditioner(airConditioner)
                .refrigerator(refrigerator)
                .washingMachine(washingMachine)
                .microwave(microwave)
                .gasRange(gasRange)
                .induction(induction)
                .bed(bed)
                .desk(desk)
                .closet(closet)
                .tv(tv)
                .wifiRouter(wifiRouter)
                .computer(computer)
                .doorLock(doorLock)
                .ventilator(ventilator)
                .parkingLot(parkingLot)
                .cctv(cctv)
                .elevator(elevator)
                .managementOffice(managementOffice)
                .commonEntrance(commonEntrance)
                .separateDischargeSpace(separateDischargeSpace)
                .note(note)
                .imgDescription(imgDescription)
                .build();
    }


}
