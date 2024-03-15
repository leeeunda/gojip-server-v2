package com.example.gojipserver.domain.roomimage.entity;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.global.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomImage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_image_id")
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "check_list_id")
    private CheckList checkList;

    @CreatedDate
    @Column(name="created_at", nullable = false, updatable=false)
    private LocalDateTime createdDate;

    @Builder
    public RoomImage(String imgUrl, CheckList checkList) {
        this.imgUrl = imgUrl;
    }

    public void registerToCheckList(CheckList checkList){
        this.checkList = checkList;
    }

}
