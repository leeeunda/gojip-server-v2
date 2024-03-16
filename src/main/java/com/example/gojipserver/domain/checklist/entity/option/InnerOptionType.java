package com.example.gojipserver.domain.checklist.entity.option;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum InnerOptionType {

    에어컨("에어컨"),
    냉장고("냉장고"),
    세탁기("세탁기"),
    전자레인지("전자레인지"),
    가스레인지("가스레인지"),
    침대("침대"),
    책상("책상"),
    옷장("옷장"),
    TV("TV"),
    공유기("공유기"),
    컴퓨터("컴퓨터"),
    도어락("도어락"),
    환풍기("환풍기"),
    소파("소파");

    @JsonValue
    private final String value;

    InnerOptionType(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static InnerOptionType from(String param) {
        for (InnerOptionType innerOptionType : InnerOptionType.values()) {
            if (innerOptionType.getValue().equals(param)) {
                return innerOptionType;
            }
        }
        log.debug("InnerOptionType.from() exception occur param: {}", param);
        throw new IllegalArgumentException("올바른 내부 옵션을 선택해주세요.");
    }
}
