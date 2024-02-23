package com.example.gojipserver.domain.collection.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionResponseDto {

    private Long id;

    private String collectionName;

    @Builder
    public CollectionResponseDto(Long id, String collectionName) {
        this.id = id;
        this.collectionName = collectionName;
    }


}
