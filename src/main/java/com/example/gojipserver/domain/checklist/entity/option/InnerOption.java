package com.example.gojipserver.domain.checklist.entity.option;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
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

    @Builder
    public InnerOption(boolean airConditioner, boolean refrigerator, boolean washingMachine, boolean microwave, boolean gasRange, boolean induction, boolean bed, boolean desk, boolean closet, boolean tv, boolean wifiRouter, boolean computer, boolean doorLock, boolean ventilator) {
        this.airConditioner = airConditioner;
        this.refrigerator = refrigerator;
        this.washingMachine = washingMachine;
        this.microwave = microwave;
        this.gasRange = gasRange;
        this.induction = induction;
        this.bed = bed;
        this.desk = desk;
        this.closet = closet;
        this.tv = tv;
        this.wifiRouter = wifiRouter;
        this.computer = computer;
        this.doorLock = doorLock;
        this.ventilator = ventilator;
    }
}
