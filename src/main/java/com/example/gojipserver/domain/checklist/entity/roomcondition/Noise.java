package com.example.gojipserver.domain.checklist.entity.roomcondition;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Noise {

    private boolean floor; //층간소음
    private boolean wall; //벽간소음
    private boolean outside; //외부소음
}
