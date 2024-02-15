package com.example.gojipserver.domain.collection.repository;

import com.example.gojipserver.domain.collection.dto.CollectionResponseDto;
import com.example.gojipserver.domain.collection.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    @Query("select new com.example.gojipserver.domain.collection.dto.CollectionResponseDto(c.id, c.collectionName) " +
            "from Collection c where c.user.id = :userId " +
            "order by c.id asc")
    List<CollectionResponseDto> findCollectionsByUserId(Long userId);

}
