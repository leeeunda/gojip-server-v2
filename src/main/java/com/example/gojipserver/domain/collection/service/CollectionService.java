package com.example.gojipserver.domain.collection.service;

import com.example.gojipserver.domain.collection.dto.CollectionResponseDto;
import com.example.gojipserver.domain.collection.dto.CollectionSaveDto;
import com.example.gojipserver.domain.collection.dto.CollectionUpdateDto;
import com.example.gojipserver.domain.collection.entity.Collection;
import com.example.gojipserver.domain.collection.repository.CollectionRepository;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long saveCollection(Long userId, CollectionSaveDto collectionSaveDto) {

        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원 찾기 실패!, 대상 회원이 존재하지 않습니다."));

        validCollectionNameDuplicate(userId, collectionSaveDto.getCollectionName());

        return collectionRepository.save(collectionSaveDto.toEntity(findUser)).getId();
    }

    @Transactional
    public Long updateCollection(Long collectionId, Long requestUserId, CollectionUpdateDto collectionUpdateDto) {
        Collection findCollection = findCollectionById(collectionId);
        String collectionName = collectionUpdateDto.getCollectionName();

        // 검증
        validCollectionOwner(requestUserId, collectionId, findCollection);
        validCollectionNameDuplicate(requestUserId, collectionName);

        findCollection.updateCollectionName(collectionName);

        return findCollection.getId();
    }

    // TODO: Custom 예외 처리
    @Transactional
    public void deleteCollection(Long requestUserId , Long collectionId) {
        Collection findCollection = findCollectionById(collectionId);

        validCollectionOwner(requestUserId, collectionId, findCollection);

        collectionRepository.delete(findCollection);
    }

    // TODO:
    public List<CollectionResponseDto> getCollections(Long requestUserId) {
        return collectionRepository.findCollectionsByUserId(requestUserId);
    }

    // 컬렉션 이름 중복 검증
    private void validCollectionNameDuplicate(Long userId, String collectionName) {
        if (collectionRepository.existsCollectionNameByUserId(userId, collectionName)) {
            throw new IllegalArgumentException("이미 존재하는 컬렉션 이름입니다. collectionName = " + collectionName);
        }
    }

    // 삭제 요청을 한 유저가 해당 컬렉션의 소유자가 맞는지 검증
    private static void validCollectionOwner(Long requestUserId, Long collectionId, Collection findCollection) {
        if (!findCollection.getUser().getId().equals(requestUserId)) {
            throw new IllegalArgumentException("해당 컬렉션에 권한이 없습니다. collectionId = " + collectionId);
        }
    }

    private Collection findCollectionById(Long collectionId) {
        Collection findCollection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new IllegalArgumentException("컬렉션 찾기 실패!, 대상 컬렉션이 없습니다. collectionId = " + collectionId));
        return findCollection;
    }

}
