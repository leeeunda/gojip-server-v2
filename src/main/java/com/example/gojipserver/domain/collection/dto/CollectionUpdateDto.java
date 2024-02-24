package com.example.gojipserver.domain.collection.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionUpdateDto {

    @NotBlank(message = "컬렉션 이름은 필수 입력값입니다.")
    private String collectionName;
}
