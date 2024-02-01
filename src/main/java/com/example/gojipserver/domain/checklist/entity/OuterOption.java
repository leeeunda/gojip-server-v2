package com.example.gojipserver.domain.checklist.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OuterOption {

    private boolean parkingLot; //주차장

    private boolean cctv; //cctv

    private boolean elevator; //엘리베이터

    private boolean managementOffice; //관리실

    private boolean commonEntrance; //공동현관

    private boolean separateDischargeSpace; //분리배출공간
}
