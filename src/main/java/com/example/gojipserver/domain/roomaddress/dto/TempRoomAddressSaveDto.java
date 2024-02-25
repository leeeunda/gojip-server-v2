package com.example.gojipserver.domain.roomaddress.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TempRoomAddressSaveDto {

    private String addressName;
    private double latitude;
    private double longitude;

    @Builder
    public TempRoomAddressSaveDto(String addressName, double latitude, double longitude) {
        this.addressName = addressName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
