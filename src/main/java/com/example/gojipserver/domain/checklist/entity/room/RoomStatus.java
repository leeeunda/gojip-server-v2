package com.example.gojipserver.domain.checklist.entity.room;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.global.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RoomStatus extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_status_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_list_id", nullable = false)
    private CheckList checkList;

    private RoomStatusMeasure roomStatusMeasure;

}
