package com.example.gojipserver.domain.checklist.entity.roomcondition;

import com.example.gojipserver.global.response.ApiResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Building {
    GOOD("GOOD"),
    BAD("BAD");

    private final String value;

    Building(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static Building from(String param) {
        for (Building building : Building.values()) {
            if (building.getValue().equals(param)) {
                return building;
            }
        }
        log.debug("Building.from() exception occur param: {}", param);
        return null;
    }



}
