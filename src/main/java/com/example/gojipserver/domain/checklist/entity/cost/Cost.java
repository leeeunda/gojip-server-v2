package com.example.gojipserver.domain.checklist.entity.cost;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cost {

    private int deposit; //보증금
    private int monthlyCost; //월세
    private int managementCost; //관리비
    // 관리비 포함 옵션
    private boolean waterCost; //수도세
    private boolean heatingCost; //난방비
    private boolean electricCost; //전기세
    private boolean internetCost; //인터넷비

    @Builder
    public Cost(int deposit, int monthlyCost, int managementCost, boolean waterCost, boolean heatingCost, boolean electricCost, boolean internetCost) {
        this.deposit = deposit;
        this.monthlyCost = monthlyCost;
        this.managementCost = managementCost;
        this.waterCost = waterCost;
        this.heatingCost = heatingCost;
        this.electricCost = electricCost;
        this.internetCost = internetCost;
    }
}
