package com.example.gojipserver.domain.checklist.entity.cost;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public enum ManagementCostOptionType {

    수도세("수도세"),
    난방비("난방비"),
    전기세("전기세"),
    인터넷비("인터넷비");

    private final String value;

    ManagementCostOptionType(String value) {
        this.value = value;
    }

    @JsonCreator // Json -> Object, 역직렬화 수행하는 메서드
    public static ManagementCostOptionType from(String param) {
        for (ManagementCostOptionType managementCostOptionType : ManagementCostOptionType.values()) {
            if (managementCostOptionType.getValue().equals(param)) {
                return managementCostOptionType;
            }
        }
        log.debug("ManagementCostOptionType.from() exception occur param: {}", param);
        return null;
    }
}


