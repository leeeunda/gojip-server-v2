package com.example.gojipserver.domain.checklist.dto;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "체크리스트 전체 조회 DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CheckListAllGetDto {

    // 비용
    @Schema(description = "비용 / 보증금")
    private int deposit; //보증금

    @Schema(description = "비용 / 월세")
    private int monthlyCost; //월세

    //주소 이름
    @Schema(description = "체크리스트의 주소 이름, Ex) 서울시 강남구 테헤란로 21")
    private String addressName;

    // 경도
    @Schema(description = "체크리스트의 위도")
    private double latitude;

    //위도
    @Schema(description = "체크리스트의 경도")
    private double longitude;

    @Builder
    public CheckListAllGetDto(CheckList checkList, RoomAddress roomAddress){
        this.addressName = roomAddress.getAddressName();
        this.deposit= checkList.getDeposit();
        this.monthlyCost = checkList.getMonthlyCost();
        this.latitude = roomAddress.getLatitude();
        this.longitude = roomAddress.getLongitude();
    }
}
