package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;

// 방 상태 척도
@Getter
@Slf4j
public enum RoomStatusType {

    곰팡이("곰팡이"),
    벌레("벌레"),
    벽지오염("벽지오염"),
    웃풍("웃풍"),
    바닥오염("바닥오염"),
    조명고장("조명고장"),
    가스문제("가스문제"),
    창틀오염("창틀오염");

    @JsonValue
    private final String value;

    RoomStatusType(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static RoomStatusType from(String param) {
        for (RoomStatusType roomStatusType : RoomStatusType.values()) {
            if (roomStatusType.getValue().equals(param)) {
                return roomStatusType;
            }
        }
        log.debug("RoomStatusType.from() exception occur param: {}", param);
        throw new IllegalArgumentException("올바른 방 상태 종류를 선택해주세요.");
    }
}
