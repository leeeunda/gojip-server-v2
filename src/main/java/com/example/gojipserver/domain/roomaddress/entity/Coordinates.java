package com.example.gojipserver.domain.roomaddress.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coordinates {
    private String x; // x좌표
    private String y; // y좌표

    @Builder
    public Coordinates(String x,String y) {
        this.x=x;
        this.y=y;
    }

    public static Coordinates createCoordinate(String x, String y){
        return Coordinates.builder()
                .x(x)
                .y(y)
                .build();
    }
}
