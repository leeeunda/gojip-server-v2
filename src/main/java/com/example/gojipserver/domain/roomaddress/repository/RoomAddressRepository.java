package com.example.gojipserver.domain.roomaddress.repository;

import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomAddressRepository extends JpaRepository<RoomAddress, Long> {
}
