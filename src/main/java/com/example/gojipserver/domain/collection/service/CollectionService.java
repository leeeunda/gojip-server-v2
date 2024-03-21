package com.example.gojipserver.domain.collection.service;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.repository.CheckListRepository;
import com.example.gojipserver.domain.checklist_collection.entity.CheckListCollection;
import com.example.gojipserver.domain.checklist_collection.repository.CheckListCollectionRepository;
import com.example.gojipserver.domain.collection.dto.CollectionRequestDto;
import com.example.gojipserver.domain.collection.dto.CollectionResponseDto;
import com.example.gojipserver.domain.collection.entity.Collection;
import com.example.gojipserver.global.exception.DuplicateException;
import com.example.gojipserver.domain.collection.repository.CollectionRepository;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.domain.user.repository.UserRepository;
import com.example.gojipserver.global.exception.NotOwnerException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;
    private final CheckListRepository checkListRepository;
    private final CheckListCollectionRepository checkListCollectionRepository;

    @Transactional
    public Long saveCollection(Long userId, CollectionRequestDto.CollectionSaveDto collectionSaveDto) {
        User findUser = findUserById(userId);
        validCollectionNameDuplicate(userId, collectionSaveDto.getCollectionName());

        Collection savedCollection = collectionRepository.save(collectionSaveDto.toEntity(findUser));
        setCheckListOfCollection(savedCollection, collectionSaveDto.getCheckListIdList());
        return savedCollection.getId();
    }

    @Transactional
    public Long updateCollection(Long collectionId, Long requestUserId, CollectionRequestDto.CollectionUpdateDto collectionUpdateDto) {
        Collection findCollection = findCollectionById(collectionId);
        String collectionName = collectionUpdateDto.getCollectionName();
        // 검증
        validCollectionOwner(requestUserId,findCollection);
        validCollectionNameDuplicate(requestUserId, collectionName);
        // 컬렉션과 체크리스트 연관관계 수정
        // 1.현재 DB에 저장된 id List와 요청으로 온 id List
        List<Long> currentCheckListIdList = checkListCollectionRepository.findCheckListIdsByCollectionId(findCollection.getId());
        List<Long> requestCheckListIdList = collectionUpdateDto.getCheckListIdList();
        // 2.삭제할 id와 추가할 id 분류
        List<Long> checkListIdListToAdd = getIdListToAdd(currentCheckListIdList, requestCheckListIdList);
        List<Long> checkListIdListToRemove = getIdListToRemove(currentCheckListIdList, requestCheckListIdList);
        // 3.삭제 후 추가
        deleteCheckListCollectionByCheckListIdListAndCollectionId(collectionId, checkListIdListToRemove);
        setCheckListOfCollection(findCollection, checkListIdListToAdd);
        // 컬렉션 이름 수정
        findCollection.updateCollectionName(collectionName);
        return findCollection.getId();
    }

    @Transactional
    public void deleteCollection(Long requestUserId , Long collectionId) {
        Collection findCollection = findCollectionById(collectionId);
        validCollectionOwner(requestUserId, findCollection);
        collectionRepository.delete(findCollection);
    }

    public List<CollectionResponseDto> getCollections(Long requestUserId) {
        return collectionRepository.findCollectionsByUserId(requestUserId);
    }

    // 컬렉션 이름 중복 검증
    private void validCollectionNameDuplicate(Long userId, String collectionName) {
        if (collectionRepository.existsCollectionNameByUserId(userId, collectionName)) {
            throw new DuplicateException("이미 존재하는 컬렉션 이름입니다. (" + collectionName + ")");
        }
    }

    // 삭제 요청을 한 유저가 해당 컬렉션의 소유자가 맞는지 검증
    private static void validCollectionOwner(Long requestUserId, Collection collection) {
        if (!collection.getUser().getId().equals(requestUserId)) {
            throw new NotOwnerException("다른 회원의 컬렉션입니다. collectionId = " + collection.getId());
        }
    }

    private User findUserById(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다. userId = " + userId));
        return findUser;
    }

    private Collection findCollectionById(Long collectionId) {
        Collection findCollection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 컬렉션이 존재하지 않습니다. collectionId = " + collectionId));
        return findCollection;
    }

    private CheckList findCheckListById(Long checkListId) {
        CheckList findCheckList = checkListRepository.findById(checkListId)
                .orElseThrow(() -> new IllegalArgumentException("해당 체크리스트가 존재하지 않습니다. checkListId = " + checkListId));
        return findCheckList;
    }

    private static List<Long> getIdListToRemove(List<Long> currentIdList, List<Long> requestIdList) {
        // 현재 존재하는 엔티티중 삭제해야되는 엔티티의  (현재 엔티티의 id 중 수정 요청에 담겨있지 있지 않는 id)
        List<Long> idListToRemove = currentIdList.stream()
                .filter(id -> !requestIdList.contains(id))
                .collect(Collectors.toList());
        return idListToRemove;
    }

    private static List<Long> getIdListToAdd(List<Long> currentIdList, List<Long> requestIdList) {
        // dto로 전달받은 idList중 새로 추가해야할 엔티티의 id
        List<Long> idListToAdd = requestIdList.stream()
                .filter(id -> !currentIdList.contains(id))
                .collect(Collectors.toList());
        return idListToAdd;
    }

    private void deleteCheckListCollectionByCheckListIdListAndCollectionId(Long collectionId, List<Long> checkListIdListToRemove) {
        checkListIdListToRemove.stream()
                .forEach(checkListId -> {
                    checkListCollectionRepository.deleteByCheckListIdAndCollectionId(checkListId, collectionId);
                });
    }

    private void setCheckListOfCollection(Collection collection, List<Long> checkListIdList) {
        if (!checkListIdList.isEmpty()) {
            checkListIdList.stream()
                    .forEach(checkListId -> {
                        CheckList findCheckList = findCheckListById(checkListId);
                        CheckListCollection checkListCollection = CheckListCollection.createCheckListCollection(findCheckList, collection);
                        findCheckList.addCheckListCollection(checkListCollection);
                        collection.addCheckListCollection(checkListCollection);
                    });
        }
    }
}
