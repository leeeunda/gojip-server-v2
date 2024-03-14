package com.example.gojipserver.domain.checklist.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "체크리스트 구별 개수 조회 DTO")
@AllArgsConstructor
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckListCityCountGetDto {
    @Schema(description = "구 이름")
    private String city;

    @Schema(description = "구별 체크리스트 개수")
    private Long cityCount;
}
