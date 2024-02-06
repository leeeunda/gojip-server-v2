package com.example.gojipserver.domain.collection.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id")
    private Long id;

}
