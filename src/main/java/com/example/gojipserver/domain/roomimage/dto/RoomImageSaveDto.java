package com.example.gojipserver.domain.roomimage.dto;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.roomimage.entity.RoomImage;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomImageSaveDto {

    private String imgUrl;

    // DTO 객체를 Entity로 변환
    public RoomImage toEntity(){
        return RoomImage.builder()
                .imgUrl(imgUrl)
                .isThumbnail(false)
                .build();
    }

    @Builder
    public RoomImageSaveDto(String imgUrl){
        this.imgUrl = imgUrl;
    }
}
