package com.example.gojipserver.domain.roomaddress.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coordinates {
    private String x; // 위도
    private String y; // 경도
    private String city; // 구

    @Builder
    public Coordinates(String x,String y,String city){
        this.x=x;
        this.y=y;
        this.city=city;
    }

    public static Coordinates createCoordinate(String x, String y){
        return Coordinates.builder()
                .x(x)
                .y(y)
                .build();
    }
}
