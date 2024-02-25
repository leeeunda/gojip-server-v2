package com.example.gojipserver.domain.checklist.service;

import com.example.gojipserver.domain.checklist.dto.CheckListSaveDto;
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
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("회원 찾기 실패!, 대상 회원이 존재하지 않습니다. userId = " + userId));

        RoomAddress findRoomAddress = roomAddressRepository.findById(checkListSaveDto.getRoomAddressId())
                .orElseThrow(() -> new IllegalArgumentException("회원 찾기 실패!, 대상 회원이 존재하지 않습니다. roomAddressId = " + checkListSaveDto.getRoomAddressId()));

        CheckList savedCheckList = checkListRepository.save(checkListSaveDto.toEntity(findUser, findRoomAddress));


        // 체크리스트와 이미지 연관관계 세팅
        List<Long> roomImageIdList = checkListSaveDto.getRoomImageIdList();

        if (roomImageIdList != null) {
            for (Long roomImageId : roomImageIdList) {
                RoomImage findRoomImage = roomImageRepository.findById(roomImageId)
                        .orElseThrow(() -> new IllegalArgumentException("이미지 찾기 실패!, 대상 이미지가 존재하지 않습니다. roomImageId = " + roomImageId));

                savedCheckList.addRoomImage(findRoomImage);
            }
        }

        // 체크리스트를 등록할 컬렉션이 존재하는 경우
        List<Long> collectionIdList = checkListSaveDto.getCollectionIdList();

        if (collectionIdList != null) {
            for (Long collectionId : collectionIdList) {
                Collection findCollection = collectionRepository.findById(collectionId)
                        .orElseThrow(() -> new IllegalArgumentException("컬렉션 찾기 실패!, 대상 컬렉션이 존재하지 않습니다. collectionId = " + collectionId));

                CheckListCollection checkListCollection = CheckListCollection.createCheckListCollection(savedCheckList, findCollection);

                savedCheckList.addCheckListCollection(checkListCollection);
                findCollection.addCheckListCollection(checkListCollection);
                
                checkListCollectionRepository.save(checkListCollection);
            }
        }

        return savedCheckList.getId();
    }

    @Transactional
    public void deleteCheckList(Long requestUserId, Long checkListId) {
        CheckList findCheckList = checkListRepository.findById(checkListId)
                .orElseThrow(() -> new IllegalArgumentException("체크리스트 찾기 실패!, 대상 체크리스트가 존재하지 않습니다. checkListId + " + checkListId));

        validCheckListOwner(requestUserId, findCheckList);

        checkListCollectionRepository.deleteByCheckList(findCheckList);

        checkListRepository.delete(findCheckList);

    }

    public CheckList getCheckListById(Long checkListId) {
        return checkListRepository.findById(checkListId)
                .orElseThrow(() -> new IllegalArgumentException("체크리스트 찾기 실패!, 대상 체크리스트가 존재하지 않습니다. checkListId + " + checkListId));

    }

    @Transactional
    public RoomAddress getRoomAddressByCheckListId(Long checkListId){

        // 체크리스트 id를 받아서 RoomAddress 엔티티의 addressName을 얻어오는 코드

        CheckList checkList = checkListRepository.findById(checkListId)
                .orElseThrow(() -> new IllegalArgumentException("CheckListId가 유효하지 않습니다: " + checkListId));

        return checkList.getRoomAddress();
    }

    private static void validCheckListOwner(Long requestUserId, CheckList checkList) {
        // 삭제 요청을 한 유저가 해당 컬렉션의 소유자가 맞는지 검증
        if (!checkList.getUser().getId().equals(requestUserId)) {
            throw new NotOwnerException("해당 체크리스트에 권한이 없습니다. checkListId = " + checkList.getId());
        }
    }

}
