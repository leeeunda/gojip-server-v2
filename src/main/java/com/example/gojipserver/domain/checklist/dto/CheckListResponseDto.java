package com.example.gojipserver.domain.checklist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class CheckListResponseDto {
    @Schema(description = "최근 체크리스트 조회 응답 DTO")
    @Getter
    @Builder
    @AllArgsConstructor
    public static class CheckListRecentResponseDto {
        private Long checklistId;
        private String mainImage;
        private String addressName;
    }
}
