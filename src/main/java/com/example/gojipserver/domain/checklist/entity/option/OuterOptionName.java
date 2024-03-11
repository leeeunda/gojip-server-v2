package com.example.gojipserver.domain.checklist.entity.option;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum OuterOptionName {

    주차장("주차장"),
    CCTV("CCTV"),
    엘리베이터("엘리베이터"),
    관리실("관리실"),
    공동현관("공동현관"),
    분리배출공간("분리배출공간");

    private final String value;

    OuterOptionName(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static OuterOptionName from(String param) {
        for (OuterOptionName outerOption : OuterOptionName.values()) {
            if (outerOption.getValue().equals(param)) {
                return outerOption;
            }
        }
        log.debug("OuterOption.from() exception occur param: {}", param);
        return null;
    }
}
