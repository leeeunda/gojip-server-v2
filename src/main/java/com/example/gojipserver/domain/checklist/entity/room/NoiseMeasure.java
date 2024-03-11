package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

// 소음척도
@Getter
@Slf4j
public enum NoiseMeasure {

    층간소음("층간소음"),
    외부소음("외부소음"),
    방간소음("방간소음");

    private final String value;

    NoiseMeasure(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static NoiseMeasure from(String param) {
        for (NoiseMeasure noiseMeasure : NoiseMeasure.values()) {
            if (noiseMeasure.getValue().equals(param)) {
                return noiseMeasure;
            }
        }
        log.debug("NoiseMeasure.from() exception occur param: {}", param);
        return null;
    }
}
