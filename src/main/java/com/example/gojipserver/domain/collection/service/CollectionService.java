package com.example.gojipserver.domain.collection.service;

import com.example.gojipserver.domain.collection.dto.CollectionSaveDto;
import com.example.gojipserver.domain.collection.repository.CollectionRepository;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.domain.user.repository.UserRepository;
import com.example.gojipserver.global.oauth2.entity.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveCollection(UserPrincipal userPrincipal, CollectionSaveDto collectionSaveDto) {

        Optional<User> findUser = Optional.of(userRepository.findById(userPrincipal.getId());

        return collectionRepository.save(collectionSaveDto.toEntity(findUser)).getId();
    }
}
