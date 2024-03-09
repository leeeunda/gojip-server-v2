package com.example.gojipserver.domain.like.dto;

import lombok.Builder;
import lombok.Getter;

public class LikeResponseDto {
    @Builder
    @Getter
    public static class LikeResponse{
        private Long checkListId;
        private int count;
    }
}
