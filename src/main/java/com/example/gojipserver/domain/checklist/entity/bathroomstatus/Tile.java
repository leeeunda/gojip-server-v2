package com.example.gojipserver.domain.checklist.entity.bathroomstatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Tile {
    GOOD("GOOD"),
    BAD("BAD");

    String value;

    Tile(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static Tile from(String param) {
        for (Tile tile : Tile.values()) {
            if (tile.getValue().equals(param)) {
                return tile;
            }
        }
        log.debug("Tile.from() exception occur param: {}", param);
        return null;
    }
}
