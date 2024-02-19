package com.example.gojipserver.domain.checklist.dto;

import com.example.gojipserver.domain.checklist.entity.cost.Cost;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.entity.bathroomstatus.BathRoomStatus;
import com.example.gojipserver.domain.checklist.entity.option.InnerOption;
import com.example.gojipserver.domain.checklist.entity.option.OuterOption;
import com.example.gojipserver.domain.checklist.entity.roomcondition.RoomCondition;
import com.example.gojipserver.domain.checklist.entity.roomstatus.RoomStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CheckListSaveDto {

    private RoomAddress roomAddress;
    private Cost cost;
    private RoomCondition roomCondition;
    private RoomStatus roomStatus;
    private BathRoomStatus bathRoomStatus;
    private InnerOption innerOption;
    private OuterOption outerOption;
    private String note;
    private String imgDescription;

    @Builder
    public CheckListSaveDto(CheckList checkList) {
        this.roomAddress = checkList.getRoomAddress();
        this.cost = checkList.getCost();
        this.roomCondition = checkList.getRoomCondition();
        this.roomStatus = checkList.getRoomStatus();
        this.bathRoomStatus = checkList.getBathRoomStatus();
        this.innerOption = checkList.getInnerOption();
        this.outerOption = checkList.getOuterOption();
        this.note = checkList.getNote();
        this.imgDescription=checkList.getImgDescription();
    }

    public CheckList toEntity() {
        return CheckList.builder()
                .roomAddress(roomAddress)
                .cost(cost)
                .roomCondition(roomCondition)
                .roomStatus(roomStatus)
                .bathRoomStatus(bathRoomStatus)
                .innerOption(innerOption)
                .outerOption(outerOption)
                .note(note)
                .build();
    }


}
