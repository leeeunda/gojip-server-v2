package com.example.gojipserver.domain.collection.dto;

import com.example.gojipserver.domain.collection.entity.Collection;
import com.example.gojipserver.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Getter
@NoArgsConstructor
public class CollectionSaveDto {

    private User user;
    private String collectionName;

    @Builder
    public CollectionSaveDto(User user, String collectionName) {
        this.user = user;
        this.collectionName = collectionName;
    }

    public Collection toEntity(Optional<User> user) {
        return Collection.builder()
                .user(user)
                .collectionName(collectionName)
                .build();
    }
}
