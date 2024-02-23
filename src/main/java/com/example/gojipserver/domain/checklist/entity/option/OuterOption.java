package com.example.gojipserver.domain.checklist.entity.option;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OuterOption {

    private boolean parkingLot; //주차장
    private boolean cctv; //cctv
    private boolean elevator; //엘리베이터
    private boolean managementOffice; //관리실
    private boolean commonEntrance; //공동현관
    private boolean separateDischargeSpace; //분리배출공간

    @Builder
    public OuterOption(boolean parkingLot, boolean cctv, boolean elevator, boolean managementOffice, boolean commonEntrance, boolean separateDischargeSpace) {
        this.parkingLot = parkingLot;
        this.cctv = cctv;
        this.elevator = elevator;
        this.managementOffice = managementOffice;
        this.commonEntrance = commonEntrance;
        this.separateDischargeSpace = separateDischargeSpace;
    }
}
