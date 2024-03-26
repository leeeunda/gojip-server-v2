package com.example.gojipserver.domain.like.dto;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Builder
@Getter
@Schema(description = "좋아요한 응답 DTO")
public class LikeGetResponseDto {
    @Schema(description = "체크리스트 id")
    private Long checkListId;

    @Schema(description = "좋아요 수")
    private int count;
}
