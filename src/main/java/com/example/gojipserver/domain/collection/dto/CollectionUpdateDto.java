package com.example.gojipserver.domain.collection.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "컬렉션 수정 DTO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionUpdateDto {

    @Schema(description = "수정할 컬렉션의 이름")
    @NotBlank(message = "컬렉션 이름은 필수 입력값입니다.")
    private String collectionName;
}
