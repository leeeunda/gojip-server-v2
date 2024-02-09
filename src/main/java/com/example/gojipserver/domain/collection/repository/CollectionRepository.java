package com.example.gojipserver.domain.collection.repository;

import com.example.gojipserver.domain.collection.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository extends JpaRepository<Collection, Long> {
}
