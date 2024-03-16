package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Light {

    남향("남향"),
    북향("북향"),
    서향("서향"),
    동향("동향");

    @JsonValue
    private final String value;

    Light(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static Light from(String param) {
        for (Light light : Light.values()) {
            if (light.getValue().equals(param)) {
                return light;
            }
        }
        log.debug("Light.from() exception occur param: {}", param);
        return null;
    }
}
