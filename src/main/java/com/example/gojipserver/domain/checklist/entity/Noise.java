package com.example.gojipserver.domain.checklist.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Noise {

    private boolean floor; //층간소음
    private boolean wall; //벽간소음
    private boolean outside; //외부소음
}
