package com.example.gojipserver.domain.roomaddress.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class RoomAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_address_id")
    private Long id;

    private String addressName;

    private double latitude; //위도

    private double longitude; //경도

}
