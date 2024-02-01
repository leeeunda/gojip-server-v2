package com.example.gojipserver.domain.checklist.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ManagementCostIncludeOption {

    private boolean waterCost; //수도세
    private boolean heatingCost; //난방비
    private boolean electricCost; //전기세
    private boolean internetCost; //인터넷비
}
