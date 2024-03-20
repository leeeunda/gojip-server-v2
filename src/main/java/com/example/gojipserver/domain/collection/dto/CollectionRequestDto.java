package com.example.gojipserver.domain.collection.dto;

import com.example.gojipserver.domain.collection.entity.Collection;
import com.example.gojipserver.domain.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class CollectionRequestDto {

    @Schema(description = "컬렉션 저장 DTO")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class CollectionSaveDto {

        @NotBlank(message = "컬렉션 이름은 필수 입력값입니다.")
        private String collectionName;

        private List<Long> checkListIdList = new ArrayList<>();

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

    @Schema(description = "컬렉션 수정 DTO")
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class CollectionUpdateDto {

        @Schema(description = "수정할 컬렉션의 이름")
        @NotBlank(message = "컬렉션 이름은 필수 입력값입니다.")
        private String collectionName;

        private List<Long> checkListIdList = new ArrayList<>();
    }
}
