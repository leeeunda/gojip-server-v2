package com.example.gojipserver.domain.checklist.entity.option;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum OuterOptionType {

    주차장("주차장"),
    CCTV("CCTV"),
    엘리베이터("엘리베이터"),
    관리실("관리실"),
    공동현관("공동현관"),
    분리배출공간("분리배출공간");

    @JsonValue
    private final String value;

    OuterOptionType(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static OuterOptionType from(String param) {
        for (OuterOptionType outerOptionType : OuterOptionType.values()) {
            if (outerOptionType.getValue().equals(param)) {
                return outerOptionType;
            }
        }
        log.debug("OuterOptionType.from() exception occur param: {}", param);
        return null;
    }
}
