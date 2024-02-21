package com.example.gojipserver.domain.checklist.entity.bathroomstatus;

import com.example.gojipserver.domain.checklist.entity.roomstatus.Boiler;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Toilet {
    GOOD("GOOD"),
    BAD("BAD");

    String value;

    Toilet(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static Toilet from(String param) {
        for (Toilet toilet : Toilet.values()) {
            if (toilet.getValue().equals(param)) {
                return toilet;
            }
        }
        log.debug("Toilet.from() exception occur param: {}", param);
        return null;
    }
}
