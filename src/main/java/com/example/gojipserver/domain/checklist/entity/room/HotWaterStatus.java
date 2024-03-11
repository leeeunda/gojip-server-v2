package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum HotWaterStatus {
    BAD("온수가 느려요"),
    SOSO("그냥그래요"),
    GOOD("온수가 빨라요");

    private final String value;

    HotWaterStatus(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static HotWaterStatus from(String param) {
        for (HotWaterStatus hotWaterStatus : HotWaterStatus.values()) {
            if (hotWaterStatus.getValue().equals(param)) {
                return hotWaterStatus;
            }
        }
        log.debug("HotWaterStatus.from() exception occur param: {}", param);
        return null;
    }
}
