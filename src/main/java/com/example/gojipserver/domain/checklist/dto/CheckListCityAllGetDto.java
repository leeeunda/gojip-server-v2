package com.example.gojipserver.domain.checklist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Schema(description = "체크리스트 구별 전체 조회 DTO")
@Getter
@Builder
@AllArgsConstructor
public class CheckListCityAllGetDto {
    @Schema(description = "주소이름")
    private String addressName;

    @Schema(description = "체크리스트 대표이미지")
    private String mainImage;

    @Schema(description = "평균총점")
    private double avgRating;

    @Schema(description = "체크리스트 개수")
    private Long checkListCount;
}
