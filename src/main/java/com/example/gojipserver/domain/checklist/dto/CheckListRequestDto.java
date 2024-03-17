package com.example.gojipserver.domain.checklist.dto;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.entity.cost.ManagementCostOptionType;
import com.example.gojipserver.domain.checklist.entity.cost.PropertyType;
import com.example.gojipserver.domain.checklist.entity.option.InnerOptionType;
import com.example.gojipserver.domain.checklist.entity.option.OuterOptionType;
import com.example.gojipserver.domain.checklist.entity.room.*;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import com.example.gojipserver.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

public class CheckListRequestDto {

    @Schema(description = "체크리스트 저장 DTO")
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class CheckListSaveDto {

        @Schema(description = "체크리스트의 주소 id")
        @NotNull(message = "방의 주소를 입력해주세요.")
        private Long roomAddressId;

        @Schema(description = "비용 / 매물 형태 (월세, 전세, 매매 중 택1)")
        @NotNull(message = "매물 형태를 선택해주세요.")
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
        private List<ManagementCostOptionType> managementCostOptionTypes;

        @Schema(description = "집 / 평수")
        @NotNull(message = "평수를 입력해주세요")
        private Integer area;

        @Schema(description = "집 / 구조 (원룸, 투룸, 쓰리룸, 아파트)")
        @NotNull(message = "집 구조를 선택해주세요.")
        private Structure structure;

        @Schema(description = "집 / 층 (반지하, 1~3층, 4층이상)")
        @NotNull(message = "몇층인지 선택해주세요.")
        private Floor floor;

        @Schema(description = "집 / 건물상태")
        @NotNull(message = "건물상태를 선택해주세요.")
        private BuildingStatus buildingStatus;

        @Schema(description = "집 / 역세권(역과의 거리)")
        @NotNull(message = "역과의 거리를 입력해주세요.")
        private Integer stationDistance;

        @Schema(description = "집 / 소음 종류 (층간소음, 외부소음, 방간소음)")
        private List<NoiseType> noiseTypes;

        @Schema(description = "집 / 채광 (남향, 북향, 서향, 동향)")
        @NotNull(message = "채광을 선택해주세요.")
        private Light light;

        @Schema(description = "집 / 보일러 종류 (공동난방, 개별난방)")
        @NotNull(message = "보일러 종류를 선택해주세요.")
        private BoilerType boilerType;

        @Schema(description = "집 / 방 상태 종류 (곰팡이, 벌레, 벽지오염, 옷풍, 바닥오염, 조명고장, 가스문제, 창틀오염)")
        private List<RoomStatusType> roomStatusTypes;

        @Schema(description = "집 / 수압상태")
        @NotNull(message = "수압상태를 선택해주세요.")
        private WaterPressureStatus waterPressureStatus;

        @Schema(description = "집 / 온수상태")
        @NotNull(message = "온수상태를 선택해주세요.")
        private HotWaterStatus hotWaterStatus;

        @Schema(description = "집 / 타일상태")
        @NotNull(message = "타일상태를 선택해주세요.")
        private TileStatus tileStatus;

        @Schema(description = "옵션 / 내부옵션 종류")
        private List<InnerOptionType> innerOptionTypes;

        @Schema(description = "옵션 / 외부옵션 종류")
        private List<OuterOptionType> outerOptionTypes;

        @Schema(description = "추가 / 체크리스트를 등록할 컬렉션의 id 리스트")
        private List<Long> collectionIdList;

        @Schema(description = "추가 / 추가사항")
        private String note;

        @Schema(description = "추가 / 체크리스트에 등록할 이미지의 id 리스트")
        private List<Long> roomImageIdList;

        @Schema(description = "체크리스트 제목")
        @NotBlank(message = "제목을 입력해주세요.")
        private String checkListName;

        @Schema(description = "평점")
        @NotNull(message = "평점을 입력해주세요.")
        private Integer rating; // TODO: enum으로 만들 수도 있음

        public CheckList toEntity(User user, RoomAddress roomAddress) {
            return CheckList.builder()
                    .roomAddress(roomAddress)
                    .user(user)
                    .propertyType(propertyType)
                    .deposit(deposit)
                    .monthlyCost(monthlyCost)
                    .charterCost(charterCost)
                    .tradingCost(tradingCost)
                    .area(area)
                    .structure(structure)
                    .floor(floor)
                    .buildingStatus(buildingStatus)
                    .stationDistance(stationDistance)
                    .light(light)
                    .boilerType(boilerType)
                    .waterPressureStatus(waterPressureStatus)
                    .hotWaterStatus(hotWaterStatus)
                    .tileStatus(tileStatus)
                    .note(note)
                    .checkListName(checkListName)
                    .rating(rating)
                    .build();
        }

    }
}
