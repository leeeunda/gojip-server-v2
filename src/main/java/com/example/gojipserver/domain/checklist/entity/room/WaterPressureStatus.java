package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum WaterPressureStatus {
    BAD("노후되었어요"),
    SOSO("평범해요"),
    GOOD("신축같아요");

    private final String value;

    WaterPressureStatus(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static WaterPressureStatus from(String param) {
        for (WaterPressureStatus waterPressureStatus : WaterPressureStatus.values()) {
            if (waterPressureStatus.getValue().equals(param)) {
                return waterPressureStatus;
            }
        }
        log.debug("WaterPressureStatus.from() exception occur param: {}", param);
        return null;
    }
}
