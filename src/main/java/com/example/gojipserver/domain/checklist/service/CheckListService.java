package com.example.gojipserver.domain.checklist.service;

import com.example.gojipserver.domain.checklist.dto.*;
import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.repository.CheckListRepository;
import com.example.gojipserver.domain.checklist_collection.entity.CheckListCollection;
import com.example.gojipserver.domain.checklist_collection.repository.CheckListCollectionRepository;
import com.example.gojipserver.domain.collection.entity.Collection;
import com.example.gojipserver.domain.collection.repository.CollectionRepository;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import com.example.gojipserver.domain.roomaddress.repository.RoomAddressRepository;
import com.example.gojipserver.domain.roomimage.entity.RoomImage;
import com.example.gojipserver.domain.roomimage.repository.RoomImageRepository;
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
public class CheckListService {

    private final CheckListRepository checkListRepository;
    private final UserRepository userRepository;
    private final RoomImageRepository roomImageRepository;
    private final RoomAddressRepository roomAddressRepository;
    private final CollectionRepository collectionRepository;
    private final CheckListCollectionRepository checkListCollectionRepository;


    @Transactional
    public Long saveCheckList(Long userId, CheckListSaveDto checkListSaveDto) {

        // 체크리스트 등록 유저와 주소 세팅
        User findUser = findUserById(userId);

        RoomAddress findRoomAddress = findRoomAddressById(checkListSaveDto.getRoomAddressId());

        CheckList savedCheckList = checkListRepository.save(checkListSaveDto.toEntity(findUser, findRoomAddress));


        // 체크리스트와 이미지 연관관계 세팅
        List<Long> roomImageIdList = checkListSaveDto.getRoomImageIdList();
        setRoomImageOfCheckList(savedCheckList, roomImageIdList);

        // 체크리스트를 등록할 컬렉션이 존재하는 경우
        List<Long> collectionIdList = checkListSaveDto.getCollectionIdList();
        setCollectionOfCheckList(savedCheckList, collectionIdList);

        return savedCheckList.getId();
    }


    @Transactional
    public Long updateCheckList(Long checkListId, Long requestUserId, CheckListUpdateDto checkListUpdateDto) {
        CheckList findCheckList = findCheckListById(checkListId);

        validCheckListOwner(requestUserId, findCheckList);

        // 연관관계 세팅
        roomImageRepository.deleteByCheckList(findCheckList);
        // doLogic TODO: 실제 s3 스토리지에서도 이미지 삭제

        List<Long> roomImageIdList = checkListUpdateDto.getRoomImageIdList();
        setRoomImageOfCheckList(findCheckList, roomImageIdList);

        checkListCollectionRepository.deleteByCheckList(findCheckList);
        List<Long> collectionIdList = checkListUpdateDto.getCollectionIdList();
        setCollectionOfCheckList(findCheckList, collectionIdList);


        findCheckList.update(checkListUpdateDto);

        return findCheckList.getId();
    }


    @Transactional
    public void deleteCheckList(Long requestUserId, Long checkListId) {
        CheckList findCheckList = findCheckListById(checkListId);

        validCheckListOwner(requestUserId, findCheckList);

        checkListCollectionRepository.deleteByCheckList(findCheckList);

        checkListRepository.delete(findCheckList);

    }

    public CheckList getCheckListById(Long checkListId) {
        return checkListRepository.findById(checkListId)
                .orElseThrow(() -> new IllegalArgumentException("체크리스트 찾기 실패!, 대상 체크리스트가 존재하지 않습니다. checkListId + " + checkListId));

    }


    // 체크리스트 id를 받아서 RoomAddress 엔티티의 addressName을 얻어오는 코드
    public RoomAddress getRoomAddressByCheckListId(Long checkListId){

        CheckList checkList = checkListRepository.findById(checkListId)
                .orElseThrow(() -> new IllegalArgumentException("CheckListId가 유효하지 않습니다: " + checkListId));

        return checkList.getRoomAddress();
    }

    // user id를 받아서 checkList들의 List를 반환하는 코드
    public List<CheckListAllGetDto> getAllCheckListByUserId(Long userId) {
        List<CheckList> checkLists = checkListRepository.findByUserIdOrderByCreatedDateDesc(userId);

        // 해당 userId에 대한 CheckList가 존재하지 않는 경우
        if (checkLists.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자에 대한 체크리스트가 존재하지 않습니다: " + userId);
        }

        return checkLists.stream().map(checkList -> {
            RoomAddress roomAddress = checkList.getRoomAddress();
            if (roomAddress == null) {
                throw new IllegalStateException("체크리스트에 연결된 RoomAddress가 존재하지 않습니다: " + checkList.getId());
            }
            return new CheckListAllGetDto(checkList, roomAddress);
        }).collect(Collectors.toList());
    }

    // CollectionId를 받아서 collection에 들어있는 checkList들을 반환하는 코드
    public List<CheckListCollectionGetDto> getChecklistsByCollectionId(Long collectionId){
        return checkListRepository.findByCollectionId(collectionId);
    }

    private User findUserById(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. userId = " + userId));
        return findUser;
    }

    private RoomAddress findRoomAddressById(Long roomAddressId) {
        RoomAddress findRoomAddress = roomAddressRepository.findById(roomAddressId)
                .orElseThrow(() -> new IllegalArgumentException("해당 주소가 존재하지 않습니다. roomAddressId = " + roomAddressId));
        return findRoomAddress;
    }

    private CheckList findCheckListById(Long checkListId) {
        CheckList findCheckList = checkListRepository.findById(checkListId)
                .orElseThrow(() -> new IllegalArgumentException("해당 체크리스트가 존재하지 않습니다. checkListId = " + checkListId));
        return findCheckList;
    }

    private Collection findCollectionById(Long collectionId) {
        Collection findCollection = collectionRepository.findById(collectionId)
                .orElseThrow(() -> new IllegalArgumentException("해당 컬렉션이 존재하지 않습니다. collectionId = " + collectionId));
        return findCollection;
    }

    private RoomImage findRoomImageById(Long roomImageId) {
        RoomImage findRoomImage = roomImageRepository.findById(roomImageId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 존재하지 않습니다. roomImageId = " + roomImageId));
        return findRoomImage;
    }

    // CheckList <-> RoomImage 양방향 연관관계 설정
    @Transactional
    public void setRoomImageOfCheckList(CheckList checkList, List<Long> roomImageIdList) {
        if (roomImageIdList != null) {
            for (Long roomImageId : roomImageIdList) {
                RoomImage findRoomImage = findRoomImageById(roomImageId);

                checkList.addRoomImage(findRoomImage);
            }
        }
    }

    // CheckList <-> CheckListCollection, Collection <-> CheckListCollection 양방향 연관관계 설정
    @Transactional
    public void setCollectionOfCheckList(CheckList checkList, List<Long> collectionIdList) {
        if (collectionIdList != null) {
            for (Long collectionId : collectionIdList) {
                Collection findCollection = findCollectionById(collectionId);

                CheckListCollection checkListCollection = CheckListCollection.createCheckListCollection(checkList, findCollection);

                checkList.addCheckListCollection(checkListCollection);
                findCollection.addCheckListCollection(checkListCollection);

                checkListCollectionRepository.save(checkListCollection);
            }
        }
    }

    // 삭제 요청을 한 유저가 해당 컬렉션의 소유자가 맞는지 검증
    private static void validCheckListOwner(Long requestUserId, CheckList checkList) {
        if (!checkList.getUser().getId().equals(requestUserId)) {
            throw new NotOwnerException("다른 회원의 체크리스트입니다. checkListId = " + checkList.getId());
        }
    }

    public List<CheckListRecentResponseDto> getRecentCheckListTop3(Long userId) {
        List<CheckList> recentTop3ByUser = findRecentTop3ByUser(userId);
        if (recentTop3ByUser.isEmpty()) {
            throw new IllegalArgumentException("해당 사용자에 대한 체크리스트가 존재하지 않습니다: " + userId);
        }
        return recentTop3ByUser.stream().map(
                checkList -> CheckListRecentResponseDto.builder()
                        .checklistId(checkList.getId())
                        .addressName(checkList.getRoomAddress().getAddressName())
                        .mainImage(null)
                        .build()).collect(Collectors.toList());
    }
    private List<CheckList> findRecentTop3ByUser(Long userId) {
        return checkListRepository.findTop3ByUserIdOrderByLastModifiedDateDesc(userId);
    }

    public List<CheckListCityCountGetDto> getCityCountTop7() {
        return checkListRepository.findCityCountTop7();
    }
}
