package com.example.gojipserver.domain.checklist.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cost {

    private int deposit; //보증금

    private int monthlyCost; //월세

    private int managementCost; //관리비


}
