package com.example.gojipserver.domain.checklist.entity.cost;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ManagementCostIncludeOption {

    private boolean waterCost; //수도세
    private boolean heatingCost; //난방비
    private boolean electricCost; //전기세
    private boolean internetCost; //인터넷비

    public ManagementCostIncludeOption(boolean waterCost, boolean heatingCost, boolean electricCost, boolean internetCost) {
        this.waterCost = waterCost;
        this.heatingCost = heatingCost;
        this.electricCost = electricCost;
        this.internetCost = internetCost;
    }
}


