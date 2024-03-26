package com.example.gojipserver.domain.like.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "좋아요한 체크리스트 조회 응답 DTO")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikePreviewResponseDto {
    @Schema(description = "체크리스트 id")
    private Long checklistId;

    @Schema(description = "대표이미지")
    private String mainImage;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "주소")
    private String addressName;

    @Schema(description = "평점")
    private double rating;

    @Schema(description = "좋아요 수")
    private int likeCount;
}
