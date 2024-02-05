package com.example.gojipserver.domain.address.entity;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "check_list_id")
    private CheckList checkList;

    private double latitude; //위도

    private double longitude; //경도

}
