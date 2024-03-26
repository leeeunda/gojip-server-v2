package com.example.gojipserver.domain.checklist.dto;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.entity.cost.ManagementCostOptionType;
import com.example.gojipserver.domain.checklist.entity.cost.PropertyType;
import com.example.gojipserver.domain.checklist.entity.option.InnerOptionType;
import com.example.gojipserver.domain.checklist.entity.option.OuterOptionType;
import com.example.gojipserver.domain.checklist.entity.room.*;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Schema(description = "체크리스트 단일 조회 DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CheckListOneGetDto {

    @Schema(description = "체크리스트의 주소 이름")
    private String roomAddressName;

    @Schema(description = "체크리스트 제목")
    private String checkListName;

    @Schema(description = "평점")
    private Integer rating;

    @Schema(description = "좋아요 개수")
    private Integer likeCount;

    @Schema(description = "비용 / 매물 형태 (월세, 전세, 매매)")
    private PropertyType propertyType;

    @Schema(description = "비용 / 보증금")
    private Integer deposit; //보증금

    @Schema(description = "비용 / 월세비용")
    private Integer monthlyCost; //월세비용

    @Schema(description = "비용 / 전세비용")
    private Integer charterCost; //전세비용

    @Schema(description = "비용 / 매매비용")
    private Integer tradingCost; //매매비용

    @Schema(description = "비용 / 관리비 옵션")
    private List<ManagementCostOptionType> managementCostOptionTypes = new ArrayList<>();

    @Schema(description = "집 / 평수")
    private Integer area;

    @Schema(description = "집 / 구조 (원룸, 투룸, 쓰리룸, 아파트)")
    private Structure structure;

    @Schema(description = "집 / 층 (반지하, 1~3층, 4층이상)")
    private Floor floor;

    @Schema(description = "집 / 건물상태")
    private BuildingStatus buildingStatus;

    @Schema(description = "집 / 역세권(역과의 거리)")
    private Integer stationDistance;

    @Schema(description = "집 / 소음 종류 (층간소음, 외부소음, 방간소음)")
    private List<NoiseType> noiseTypes = new ArrayList<>();

    @Schema(description = "집 / 채광 (남향, 북향, 서향, 동향)")
    private Light light;

    @Schema(description = "집 / 보일러 종류 (공동난방, 개별난방)")
    private BoilerType boilerType;

    @Schema(description = "집 / 방 상태 종류 (곰팡이, 벌레, 벽지오염, 옷풍, 바닥오염, 조명고장, 가스문제, 창틀오염)")
    private List<RoomStatusType> roomStatusTypes = new ArrayList<>();

    @Schema(description = "집 / 수압상태")
    private WaterPressureStatus waterPressureStatus;

    @Schema(description = "집 / 온수상태")
    private HotWaterStatus hotWaterStatus;

    @Schema(description = "집 / 타일상태")
    private TileStatus tileStatus;

    @Schema(description = "옵션 / 내부옵션 종류")
    private List<InnerOptionType> innerOptionTypes = new ArrayList<>();

    @Schema(description = "옵션 / 외부옵션 종류")
    private List<OuterOptionType> outerOptionTypes = new ArrayList<>();

    @Schema(description = "추가 / 추가사항")
    private String note;

    @Schema(description = "추가 / 체크리스트에 등록할 이미지의 id 리스트")
    private List<String> roomImageUrls = new ArrayList<>();;



}
