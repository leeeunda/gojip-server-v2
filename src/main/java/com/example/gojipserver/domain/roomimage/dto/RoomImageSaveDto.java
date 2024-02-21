package com.example.gojipserver.domain.roomimage.dto;

import com.example.gojipserver.domain.roomimage.entity.RoomImage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomImageSaveDto {

    private String imgUrl;

    public RoomImage toEntity() {
        return RoomImage.builder()
                .imgUrl(imgUrl)
                .build();
    }
}
