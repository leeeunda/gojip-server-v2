package com.example.gojipserver.domain.collection.entity;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist_collection.entity.CheckListCollection;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.global.auditing.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Collection extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Long id;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CheckListCollection> checkListCollections = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @Column(nullable = false)
    private String collectionName;

    //연관관계 편의 메서드

    public void addCheckListCollection(CheckListCollection checkListCollection) {
        this.checkListCollections.add(checkListCollection);
        checkListCollection.setCollection(this);
    }

    @Builder
    public Collection(User user, String collectionName) {
        this.user = user;
        this.collectionName = collectionName;
    }
}
