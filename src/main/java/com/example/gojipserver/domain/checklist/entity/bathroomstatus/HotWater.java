package com.example.gojipserver.domain.checklist.entity.bathroomstatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum HotWater {
    GOOD("GOOD"),
    BAD("BAD");

    String value;

    HotWater(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static HotWater from(String param) {
        for (HotWater hotWater : HotWater.values()) {
            if (hotWater.getValue().equals(param)) {
                return hotWater;
            }
        }
        log.debug("HotWater.from() exception occur param: {}", param);
        return null;
    }
}
