package com.example.gojipserver.domain.checklist.entity.roomstatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Light {

    SOUTH("남향"), NORTH("북향"), WEST("서향"), EAST("동향"); // 남, 북, 서, 동

    String value;

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
