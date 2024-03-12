package com.example.gojipserver.domain.checklist.dto;

import com.example.gojipserver.domain.checklist.entity.room.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "체크리스트 변경 DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
public class CheckListUpdateDto {

    // 컬렉션 id의 리스트
    @Schema(description = "체크리스트를 등록할 컬렉션의 id 리스트")
    private List<Long> collectionIdList;

    // 이미지 PK 리스트
    @Schema(description = "체크리스트에 새로 등록할 이미지의 id 리스트")
    private List<Long> roomImageIdList;

    // 비용
    @Schema(description = "비용 / 보증금")
    private int deposit; //보증금

    @Schema(description = "비용 / 월세")
    private int monthlyCost; //월세

    @Schema(description = "비용 / 관리비")
    private int managementCost; //관리비

    // 관리비 포함 옵션
    @Schema(description = "관리비 포함 옵션 / 수도세")
    private boolean waterCost; //수도세

    @Schema(description = "관리비 포함 옵션 / 난방비")
    private boolean heatingCost; //난방비

    @Schema(description = "관리비 포함 옵션 / 전기세")
    private boolean electricCost; //전기세

    @Schema(description = "관리비 포함 옵션 / 인터넷비")
    private boolean internetCost; //인터넷비

    // 집 조건
    @Schema(description = "집 조건 / 평수")
    private int area; //평수

    @Schema(description = "집 조건 / 건물상태")
    @Enumerated(EnumType.STRING)
    private BuildingStatus building; //건물상태

    @Schema(description = "집 조건 / 역과의 거리")
    private int stationDistance; //역과의 거리

    @Schema(description = "집 조건 / 층간소음")
    private boolean floor; //층간소음

    @Schema(description = "집 조건 / 방간소음")
    private boolean wall; //방간소음

    @Schema(description = "집 조건 / 외부소음")
    private boolean outside; //외부소음

    //방 상태
    @Schema(description = "방 상태 / 채광")
    @Enumerated(EnumType.STRING)
    private Light light; //채광

    @Schema(description = "방 상태 / 보일러")
    @Enumerated(EnumType.STRING)
    private BoilerType boiler; //보일러

    @Schema(description = "방 상태 / 곰팡이")
    private boolean mold; //곰팡이

    @Schema(description = "방 상태 / 옷풍")
    private boolean wind; //옷풍

    @Schema(description = "방 상태 / 벌레")
    private boolean bug; //벌레

    @Schema(description = "방 상태 / 벽지오염")
    private boolean wallpaperPollution; //벽지오염

    //화장실 상태
//    @Schema(description = "화장실 상태 / 변기")
//    @Enumerated(EnumType.STRING)
//    private Toilet toilet; //변기
//
//    @Schema(description = "화장실 상태 / 세면대")
//    @Enumerated(EnumType.STRING)
//    private WashStand washstand; //세면대
//
//    @Schema(description = "화장실 상태 / 싱크대")
//    @Enumerated(EnumType.STRING)
//    private Sink sink; //싱크대
//
//    @Schema(description = "화장실 상태 / 샤워기")
//    @Enumerated(EnumType.STRING)
//    private ShowerHead showerHead; //샤워기

    @Schema(description = "화장실 상태 / 온수")
    @Enumerated(EnumType.STRING)
    private HotWaterStatus hotWater; //온수

    @Schema(description = "화장실 상태 / 타일")
    @Enumerated(EnumType.STRING)
    private TileStatus tile; //타일

    //내부 옵션
    @Schema(description = "내부 옵션 / 에어컨")
    private boolean airConditioner; //에어컨

    @Schema(description = "내부 옵션 / 냉장고")
    private boolean refrigerator; //냉장고

    @Schema(description = "내부 옵션 / 세탁기")
    private boolean washingMachine; //세탁기

    @Schema(description = "내부 옵션 / 전자레인지")
    private boolean microwave; //전자레인지

    @Schema(description = "내부 옵션 / 가스레인지")
    private boolean gasRange; //가스레인지

    @Schema(description = "내부 옵션 / 인덕션")
    private boolean induction; //인덕션

    @Schema(description = "내부 옵션 / 침대")
    private boolean bed; //침대

    @Schema(description = "내부 옵션 / 책상")
    private boolean desk; //책상

    @Schema(description = "내부 옵션 / 옷장")
    private boolean closet; //옷장

    @Schema(description = "내부 옵션 / TV")
    private boolean tv; //TV

    @Schema(description = "내부 옵션 / 공유기")
    private boolean wifiRouter; //공유기

    @Schema(description = "내부 옵션 / 컴퓨터")
    private boolean computer; //컴퓨터

    @Schema(description = "내부 옵션 / 도어락")
    private boolean doorLock; //도어락

    @Schema(description = "내부 옵션 / 환풍기")
    private boolean ventilator; //환풍기

    //외부 옵션
    @Schema(description = "외부 옵션 / 주차장")
    private boolean parkingLot; //주차장

    @Schema(description = "외부 옵션 / cctv")
    private boolean cctv; //cctv

    @Schema(description = "외부 옵션 / 엘리베이터")
    private boolean elevator; //엘리베이터

    @Schema(description = "외부 옵션 / 관리실")
    private boolean managementOffice; //관리실

    @Schema(description = "외부 옵션 / 공동현관")
    private boolean commonEntrance; //공동현관

    @Schema(description = "외부 옵션 / 분리배출공간")
    private boolean separateDischargeSpace; //분리배출공간

    @Schema(description = "추가사항")
    private String note;

    @Schema(description = "이미지 설명")
    private String imgDescription;


}
