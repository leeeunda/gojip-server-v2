package com.example.gojipserver.domain.checklist.entity.roomcondition;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

//집 조건
@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomCondition {

    private int area; //평수

    @Enumerated(EnumType.STRING)
    private Building building; //건물상태

    private int stationDistance; //역과의 거리

    private boolean floor; //층간소음
    private boolean wall; //벽간소음
    private boolean outside; //외부소음

}
