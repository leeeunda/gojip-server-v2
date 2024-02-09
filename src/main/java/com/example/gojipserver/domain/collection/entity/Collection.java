package com.example.gojipserver.domain.collection.entity;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "users_id")
    private User user;

    @OneToMany(mappedBy = "collection")
    private List<CheckList> checkLists = new ArrayList<>();

    private String collectionName;

    @Builder
    public Collection(User user, String collectionName) {
        this.user = user;
        this.collectionName = collectionName;
    }
}
