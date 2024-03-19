package com.example.gojipserver.domain.roomaddress.dto;

import com.example.gojipserver.domain.checklist.dto.CheckListSummaryGetDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(description = "특정주소 체크리스트 조회 DTO" )
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RoomAddressCheckListInfoDto {
    @Schema(description = "주소 이름")
    private String addressName;
    @Schema(description = "주소별 체크리스트 개수")
    private Long count;
    @Schema(description = "주소별 평균 평점")
    private double AVGRating;

    @Builder
    public RoomAddressCheckListInfoDto(String addressName, Long count, double AVGRating){
        this.addressName = addressName;
        this.count = count;
        this.AVGRating = AVGRating;
    }
}
