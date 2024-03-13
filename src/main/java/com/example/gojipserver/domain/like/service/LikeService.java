package com.example.gojipserver.domain.like.service;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.like.dto.LikeGetResponseDto;
import com.example.gojipserver.domain.like.dto.LikePreviewResponseDto;
import com.example.gojipserver.domain.like.entity.Like;
import com.example.gojipserver.domain.like.repository.LikeRepository;
import com.example.gojipserver.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeService {
    private final LikeRepository likeRepository;

    @Transactional
    public LikeGetResponseDto addLike(CheckList checkList, User user) {
        if(likeRepository.findByCheckListAndUser(checkList,user).isPresent()){
            throw new IllegalArgumentException("이미 좋아요를 누르셨습니다.");
        }
        Like newLike = Like.builder()
                .checkList(checkList)
                .user(user)
                .build();
        likeRepository.save(newLike);
        int likeCount = likeRepository.countByCheckList(checkList);
        checkList.addLikeInCheckList(newLike);
        checkList.updateLikeCount(likeCount);
        return likeEntityToDto(newLike,likeCount);
    }

    @Transactional
    public LikeGetResponseDto deleteLike(CheckList checkList, User user) {
        Like like = likeRepository.findByCheckListAndUser(checkList, user).orElseThrow(() -> new IllegalArgumentException("해당 좋아요가 존재하지 않습니다."));
        deleteLike(like.getId());
        int likeCount = likeRepository.countByCheckList(checkList);
        checkList.updateLikeCount(likeCount);
        return likeEntityToDto(like, likeCount);
    }

    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
    private LikeGetResponseDto likeEntityToDto(Like like,int count){
        return LikeGetResponseDto.builder()
                .checkListId(like.getCheckList().getId())
                .count(count)
                .build();
    }

    public Page<LikePreviewResponseDto> getLikeCheckList(User user, Pageable pageable) {
        Page<LikePreviewResponseDto> likeByUserOrderByCreatedDateDesc = likeRepository.findLikePreviewResponseDto(user, pageable);
        return likeByUserOrderByCreatedDateDesc;
    }
}
