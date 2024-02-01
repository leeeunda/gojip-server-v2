package com.example.gojipserver.domain.checklist.entity;

import com.example.gojipserver.domain.checklist.entity.status.*;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Status {

    @Enumerated(EnumType.STRING)
    private Building building; //건물상태

    @Enumerated(EnumType.STRING)
    private Light light; //채광, 값 지정 필요!

    @Enumerated(EnumType.STRING)
    private Boiler boiler; //보일러, 값 지정 필요!

    @Enumerated(EnumType.STRING)
    private Toilet toilet; //변기

    @Enumerated(EnumType.STRING)
    private WashStand washstand; //세면대

    @Enumerated(EnumType.STRING)
    private Sink sink; //싱크대

    @Enumerated(EnumType.STRING)
    private ShowerHead showerHead; //샤워기

    @Enumerated(EnumType.STRING)
    private HotWater hotWater; //온수

    @Enumerated(EnumType.STRING)
    private Tile tile; //타일

    private boolean mold; //곰팡이

    private boolean wind; //옷풍

    private boolean bug; //벌레

    private boolean wallpaperPollution; //벽지오염

}
