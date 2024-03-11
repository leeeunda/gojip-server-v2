package com.example.gojipserver.domain.checklist.entity.cost;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

// 매물형태
@Getter
@Slf4j
public enum PropertyType {

    월세("월세"),
    전세("전세"),
    매매("매매");

    private final String value;

    PropertyType(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static PropertyType from(String param) {
        for (PropertyType propertyType : PropertyType.values()) {
            if (propertyType.getValue().equals(param)) {
                return propertyType;
            }
        }
        log.debug("PropertyType.from() exception occur param: {}", param);
        return null;
    }
}
