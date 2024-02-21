package com.example.gojipserver.domain.collection.dto;

import com.example.gojipserver.domain.collection.entity.Collection;
import com.example.gojipserver.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CollectionSaveDto {

    @NotBlank(message = "컬렉션 이름은 필수 입력값입니다.")
    private String collectionName;

    @Builder
    public CollectionSaveDto(String collectionName) {
        this.collectionName = collectionName;
    }

    public Collection toEntity(User user) {
        return Collection.builder()
                .user(user)
                .collectionName(collectionName)
                .build();
    }
}
