package com.example.gojipserver.domain.checklist_collection.entity;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.collection.entity.Collection;
import com.example.gojipserver.global.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

// 중간 테이블
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckListCollection extends BaseTimeEntity {

    //기본키 매핑

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="checklist_collection_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_list_id")
    private CheckList checkList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Builder
    public CheckListCollection(CheckList checkList, Collection collection) {
        this.checkList = checkList;
        this.collection = collection;
    }

    public void registerCheckList(CheckList checkList) {
        this.checkList = checkList;
    }

    public void registerCollection(Collection collection) {
        this.collection = collection;
    }

    public static CheckListCollection createCheckListCollection(CheckList checkList, Collection collection) {
        return CheckListCollection.builder()
                .checkList(checkList)
                .collection(collection)
                .build();
    }

}
