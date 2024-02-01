package com.example.gojipserver.domain.checklist.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomOption {


    private boolean airConditioner; //에어컨

    private boolean refrigerator; //냉장고

    private boolean washingMachine; //세탁기

    private boolean microwave; //전자레인지

    private boolean gasRange; //가스레인지

    private boolean induction; //인덕션

    private boolean bed; //침대

    private boolean closet; //옷장

    private boolean tv; //TV

    private boolean wifiRouter; //공유기

    private boolean computer; //컴퓨터

    private boolean doorLock; //도어락

    private boolean ventilator; //환풍기

    private boolean parkingLot; //주차장

    private boolean cctv; //cctv

    private boolean elevator; //엘리베이터

    private boolean managementOffice; //관리실

    private boolean commonEntrance; //공동현관

    private boolean separateDischargeSpace; //분리배출공간
}
