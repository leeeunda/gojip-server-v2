package com.example.gojipserver.domain.roomaddress.entity;

import com.example.gojipserver.global.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomAddress extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_address_id")
    private Long id;

    @Column
    private String addressName;

    @Column
    private double latitude; //위도

    @Column
    private double longitude; //경도

    @Builder
    public RoomAddress(String addressName, double latitude, double longitude) {
        this.addressName = addressName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static RoomAddress createRoomAddress(String addressName, double latitude, double longitude) {
        return RoomAddress.builder()
                .addressName(addressName)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }
}
