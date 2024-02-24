package com.example.gojipserver.domain.collection.repository;

import com.example.gojipserver.domain.collection.dto.CollectionResponseDto;
import com.example.gojipserver.domain.collection.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    @Query("select new com.example.gojipserver.domain.collection.dto.CollectionResponseDto(c.id, c.collectionName) " +
            "from Collection c where c.user.id = :userId " +
            "order by c.id asc")
    List<CollectionResponseDto> findCollectionsByUserId(@Param("userId") Long userId);


    @Query("select case when count(c) > 0 then true else false end from Collection c where c.user.id = :userId and c.collectionName = :collectionName")
    boolean existsCollectionNameByUserId(@Param("userId") Long userId, @Param("collectionName") String collectionName);

}
