package com.example.gojipserver.domain.checklist.entity.roomstatus;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomStatus {

    @Enumerated(EnumType.STRING)
    private Light light; //채광

    @Enumerated(EnumType.STRING)
    private Boiler boiler; //보일러

    private boolean mold; //곰팡이

    private boolean wind; //옷풍

    private boolean bug; //벌레

    private boolean wallpaperPollution; //벽지오염

}
