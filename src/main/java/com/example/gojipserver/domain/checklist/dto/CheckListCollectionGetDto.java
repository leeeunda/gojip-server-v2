package com.example.gojipserver.domain.checklist.dto;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "체크리스트 컬렉션별 조회 DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CheckListCollectionGetDto {
    // 비용
    @Schema(description = "비용 / 보증금")
    private int deposit; //보증금

    @Schema(description = "비용 / 월세")
    private int monthlyCost; //월세

    //주소 이름
    @Schema(description = "체크리스트의 주소 이름, Ex) 서울시 강남구 테헤란로 21")
    private String addressName;

    @Builder
    public CheckListCollectionGetDto(CheckList checkList, RoomAddress roomAddress){
        this.addressName = roomAddress.getAddressName();
        this.deposit= checkList.getDeposit();
        this.monthlyCost = checkList.getMonthlyCost();
    }
}
