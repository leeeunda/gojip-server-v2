package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Structure {

    원룸("원룸"),
    투룸("투룸"),
    쓰리룸("쓰리룸"),
    아파트("아파트");

    @JsonValue
    private final String value;

    Structure(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static Structure from(String param) {
        for (Structure structure : Structure.values()) {
            if (structure.getValue().equals(param)) {
                return structure;
            }
        }
        log.debug("Structure.from() exception occur param: {}", param);
        return null;
    }
}
