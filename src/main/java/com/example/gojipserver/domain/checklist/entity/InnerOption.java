package com.example.gojipserver.domain.checklist.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InnerOption {


    private boolean airConditioner; //에어컨

    private boolean refrigerator; //냉장고

    private boolean washingMachine; //세탁기

    private boolean microwave; //전자레인지

    private boolean gasRange; //가스레인지

    private boolean induction; //인덕션

    private boolean bed; //침대

    private boolean desk; //책상

    private boolean closet; //옷장

    private boolean tv; //TV

    private boolean wifiRouter; //공유기

    private boolean computer; //컴퓨터

    private boolean doorLock; //도어락

    private boolean ventilator; //환풍기

}
