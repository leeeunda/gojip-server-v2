package com.example.gojipserver.domain.roomimage.dto;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.roomimage.entity.RoomImage;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Getter
@Setter
public class RoomImageDto {

    private String imgUrl;
    private Long checkListId;

    // DTO 객체를 Entity로 변환
    public RoomImage toEntity(CheckList checkList){
        return RoomImage.builder()
                .imgUrl(imgUrl)
                .checkList(checkList)
                .build();
    }

    @Builder
    public RoomImageDto(String imgUrl, Long checkListId){
        this.imgUrl = imgUrl;
        this.checkListId = checkListId;
    }
}
