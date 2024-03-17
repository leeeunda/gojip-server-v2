package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum BuildingStatus {
    BAD("노후되었어요"),
    SOSO("평범해요"),
    GOOD("신축같아요");

    @JsonValue
    private final String value;

    BuildingStatus(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static BuildingStatus from(String param) {
        for (BuildingStatus building : BuildingStatus.values()) {
            if (building.getValue().equals(param)) {
                return building;
            }
        }
        log.debug("BuildingStatus.from() exception occur param: {}", param);
        return null;
    }



}
