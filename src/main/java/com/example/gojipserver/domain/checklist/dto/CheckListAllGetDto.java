package com.example.gojipserver.domain.checklist.dto;

import com.example.gojipserver.domain.checklist.entity.bathroomstatus.*;
import com.example.gojipserver.domain.checklist.entity.roomcondition.Building;
import com.example.gojipserver.domain.checklist.entity.roomstatus.Boiler;
import com.example.gojipserver.domain.checklist.entity.roomstatus.Light;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.List;

public class CheckListAllGetDto {

    // 컬렉션 id의 리스트
    @Schema(description = "체크리스트를 등록할 컬렉션의 id 리스트")
    private List<Long> collectionIdList;

    // 주소 정보
    @Schema(description = "체크리스트의 주소 id")
    private Long roomAddressId;

    // 이미지 PK 리스트
    @Schema(description = "체크리스트에 등록할 이미지의 id 리스트")
    private List<Long> roomImageIdList;

}
