package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

// 소음척도
@Getter
@Slf4j
public enum NoiseType {

    층간소음("층간소음"),
    외부소음("외부소음"),
    방간소음("방간소음");

    @JsonValue
    private final String value;

    NoiseType(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static NoiseType from(String param) {
        for (NoiseType noiseType : NoiseType.values()) {
            if (noiseType.getValue().equals(param)) {
                return noiseType;
            }
        }
        log.debug("NoiseType.from() exception occur param: {}", param);
        throw new IllegalArgumentException("올바른 소음 종류를 선택해주세요.");
    }
}
