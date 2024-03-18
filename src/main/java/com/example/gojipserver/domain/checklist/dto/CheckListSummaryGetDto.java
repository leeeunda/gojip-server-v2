package com.example.gojipserver.domain.checklist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "체크리스트 요약 조회 DTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@Builder
public class CheckListSummaryGetDto {
    @Schema(description = "체크리스트 이름")
    private String title;
    @Schema(description = "체크리스트 메인 이미지")
    private String mainImage;
    @Schema(description = "체크리스트 평점")
    private int rating;
    @Schema(description = "체크리스트 좋아요 수")
    private int likeCount;
}
