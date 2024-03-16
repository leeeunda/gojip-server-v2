package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum WaterPressureStatus {
    BAD("수압이 약해요"),
    SOSO("그냥그래요"),
    GOOD("수압이 좋아요");

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
