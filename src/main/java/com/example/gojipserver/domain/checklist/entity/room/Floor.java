package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Floor {

    반지하("반지하"),
    저층("1~3층"),
    고층("4층이상");

    private final String value;

    Floor(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static Floor from(String param) {
        for (Floor floor : Floor.values()) {
            if (floor.getValue().equals(param)) {
                return floor;
            }
        }
        log.debug("Floor.from() exception occur param: {}", param);
        return null;
    }

}
