package com.example.gojipserver.domain.img.entity;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_image_id")
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "check_list_id", nullable = false)
    private CheckList checkList;

    public RoomImage(String imgUrl, CheckList checkList) {
        this.imgUrl = imgUrl;
        this.checkList = checkList;
    }
}
