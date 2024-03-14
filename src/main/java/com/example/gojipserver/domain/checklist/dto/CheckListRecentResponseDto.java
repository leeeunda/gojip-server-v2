package com.example.gojipserver.domain.checklist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "최근 체크리스트 조회 응답 DTO")
@Getter
@Builder
@AllArgsConstructor
public class CheckListRecentResponseDto {
    @Schema(description = "체크리스트 ID")
    private Long checklistId;

    @Schema(description = "체크리스트 대표이미지")
    private String mainImage;

    @Schema(description = "주소이름")
    private String addressName;
}
