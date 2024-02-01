package com.example.gojipserver.domain.checklist.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cost {

    private int deposit; //보증금

    private int monthlyCost; //월세

    private int managementCost; //관리비

    @Embedded
    private ManagementCostIncludeOption managementCostIncludeOption; //관리비 포함 옵션

}
