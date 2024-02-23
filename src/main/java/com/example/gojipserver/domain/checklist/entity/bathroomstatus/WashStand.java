package com.example.gojipserver.domain.checklist.entity.bathroomstatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum WashStand {
    GOOD("GOOD"),
    BAD("BAD");

    String value;

    WashStand(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static WashStand from(String param) {
        for (WashStand washStand : WashStand.values()) {
            if (washStand.getValue().equals(param)) {
                return washStand;
            }
        }
        log.debug("WashStand.from() exception occur param: {}", param);
        return null;
    }
}
