package com.example.gojipserver.domain.checklist.entity.room;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum TileStatus {
    DESTROYED("파손되었어요"),
    SOSO("그냥그래요"),
    GOOD("상태가 좋아요");

    private final String value;

    TileStatus(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static TileStatus from(String param) {
        for (TileStatus tileStatus : TileStatus.values()) {
            if (tileStatus.getValue().equals(param)) {
                return tileStatus;
            }
        }
        log.debug("TileStatus.from() exception occur param: {}", param);
        return null;
    }
}
