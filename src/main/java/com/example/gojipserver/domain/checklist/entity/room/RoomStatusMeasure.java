package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

// 방 상태 척도
@Getter
@Slf4j
public enum RoomStatusMeasure {

    곰팡이("곰팡이"),
    벌레("벌레"),
    벽지오염("벽지오염"),
    옷풍("옷풍"),
    바닥오염("바닥오염"),
    조명고장("조명고장"),
    가스문제("가스문제"),
    창틀오염("창틀오염");

    private final String value;

    RoomStatusMeasure(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static RoomStatusMeasure from(String param) {
        for (RoomStatusMeasure roomStatusMeasure : RoomStatusMeasure.values()) {
            if (roomStatusMeasure.getValue().equals(param)) {
                return roomStatusMeasure;
            }
        }
        log.debug("RoomStatusMeasure.from() exception occur param: {}", param);
        return null;
    }
}
