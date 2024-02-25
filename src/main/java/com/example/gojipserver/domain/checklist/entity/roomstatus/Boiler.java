package com.example.gojipserver.domain.checklist.entity.roomstatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Boiler {
    INDIVIDUAL("개별난방"), COMMON("공동난방"); //개별, 공동

    String value;

    Boiler(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static Boiler from(String param) {
        for (Boiler boiler : Boiler.values()) {
            if (boiler.getValue().equals(param)) {
                return boiler;
            }
        }
        log.debug("Boiler.from() exception occur param: {}", param);
        return null;
    }

}
