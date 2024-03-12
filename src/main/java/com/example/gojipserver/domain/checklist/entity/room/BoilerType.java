package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum BoilerType {
    개별난방("개별난방"),
    공동난방("공동난방");

    private final String value;

    BoilerType(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static BoilerType from(String param) {
        for (BoilerType boilerType : BoilerType.values()) {
            if (boilerType.getValue().equals(param)) {
                return boilerType;
            }
        }
        log.debug("BoilerType.from() exception occur param: {}", param);
        return null;
    }

}
