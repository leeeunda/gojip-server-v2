package com.example.gojipserver.domain.checklist.entity.bathroomstatus;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum Sink {
    GOOD("GOOD"),
    BAD("BAD");

    String value;

    Sink(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static Sink from(String param) {
        for (Sink sink : Sink.values()) {
            if (sink.getValue().equals(param)) {
                return sink;
            }
        }
        log.debug("Sink.from() exception occur param: {}", param);
        return null;
    }
}
