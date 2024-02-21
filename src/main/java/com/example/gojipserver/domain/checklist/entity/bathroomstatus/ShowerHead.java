package com.example.gojipserver.domain.checklist.entity.bathroomstatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum ShowerHead {
    GOOD("GOOD"),
    BAD("BAD");

    String value;

    ShowerHead(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static ShowerHead from(String param) {
        for (ShowerHead showerHead : ShowerHead.values()) {
            if (showerHead.getValue().equals(param)) {
                return showerHead;
            }
        }
        log.debug("ShowerHead.from() exception occur param: {}", param);
        return null;
    }
}
