package com.example.gojipserver.domain.like.controller;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.service.CheckListService;
import com.example.gojipserver.domain.like.dto.LikeGetResponseDto;
import com.example.gojipserver.domain.like.dto.LikePreviewResponseDto;
import com.example.gojipserver.domain.like.service.LikeService;
import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.domain.user.service.UserService;
import com.example.gojipserver.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checklists")
@Tag(name = "Like API", description = "좋아요 API")
public class LikeController {
    private final LikeService likeService;
    private final CheckListService checkListService;
    private final UserService userService;
    @PostMapping("/{checkListId}/like")
    @Operation(summary = "좋아요 추가", description = "체크리스트에 좋아요를 추가합니다.")
    public ApiResponse<LikeGetResponseDto> addLike (@PathVariable Long checkListId, @AuthenticationPrincipal UserPrincipal userPrincipal){
        CheckList checkList = checkListService.getCheckListById(checkListId);
        User user = userService.findById(userPrincipal.getId());
        LikeGetResponseDto likeResponse = likeService.addLike(checkList, user);
        return ApiResponse.responseSuccess(likeResponse);
    }

    @DeleteMapping("/{checkListId}/like")
    @Operation(summary = "좋아요 삭제", description = "체크리스트에 좋아요를 삭제합니다.")
    public ApiResponse<LikeGetResponseDto> deleteLike (@PathVariable Long checkListId, @AuthenticationPrincipal UserPrincipal userPrincipal){
        CheckList checkList = checkListService.getCheckListById(checkListId);
        User user = userService.findById(userPrincipal.getId());
        LikeGetResponseDto likeResponse = likeService.deleteLike(checkList, user);
        return ApiResponse.responseSuccess(likeResponse);
    }

    @GetMapping("/like")
    @Operation(summary = "좋아요한 체크리스트 조회", description = "좋아요한 체크리스트를 조회합니다.")
    public ApiResponse<Page<LikePreviewResponseDto>> getLikeCheckLists(
             @AuthenticationPrincipal UserPrincipal userPrincipal,
             @RequestParam(defaultValue = "0") int page){
        User user = userService.findById(userPrincipal.getId());
        Pageable pageable = PageRequest.of(page, 5);
        Page<LikePreviewResponseDto> likeCheckList = likeService.getLikeCheckList(user, pageable);
        return ApiResponse.responseSuccess(likeCheckList);
    }
}
