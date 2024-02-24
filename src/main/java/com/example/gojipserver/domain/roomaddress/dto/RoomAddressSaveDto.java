package com.example.gojipserver.domain.roomaddress.dto;

import com.example.gojipserver.domain.collection.entity.Collection;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import com.example.gojipserver.domain.user.entity.User;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomAddressSaveDto {

    private String addressName;


    @Builder
    public RoomAddressSaveDto(String addressName, String latitude, String longitude) {

        this.addressName = addressName;

    }

    public RoomAddress toEntity(String latitude, String longitude){
        return RoomAddress.builder()
                .addressName(addressName)
                .latitude(Double.parseDouble(latitude))
                .longitude(Double.parseDouble(longitude))
                .build();
    }
}
