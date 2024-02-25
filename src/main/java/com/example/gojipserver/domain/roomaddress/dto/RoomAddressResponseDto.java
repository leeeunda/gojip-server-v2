package com.example.gojipserver.domain.roomaddress.dto;

import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomAddressResponseDto {

    private String addressName;
    private String latitude;
    private String longitude;

    @Builder
    public RoomAddressResponseDto(String addressName, String latitude, String longitude) {
        this.addressName = addressName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static RoomAddressResponseDto of(RoomAddress roomAddress){
        return RoomAddressResponseDto.builder()
                .addressName(roomAddress.getAddressName())
                .latitude(String.valueOf(roomAddress.getLatitude()))
                .longitude(String.valueOf(roomAddress.getLongitude()))
                .build();
    }
}
