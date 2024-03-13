package com.example.gojipserver.domain.checklist.repository;

import com.example.gojipserver.domain.checklist.entity.room.RoomStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomStatusRepository extends JpaRepository<RoomStatus, Long> {
}
