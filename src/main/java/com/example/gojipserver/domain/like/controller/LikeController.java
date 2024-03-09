package com.example.gojipserver.domain.like.controller;

import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.service.CheckListService;
import com.example.gojipserver.domain.like.dto.LikeResponseDto;
import com.example.gojipserver.domain.like.service.LikeService;
import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.domain.user.entity.User;
import com.example.gojipserver.domain.user.service.UserService;
import com.example.gojipserver.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import static com.example.gojipserver.domain.like.dto.LikeResponseDto.*;

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
    public ApiResponse<LikeResponse> addLike (@PathVariable Long checkListId, @AuthenticationPrincipal UserPrincipal userPrincipal){
        CheckList checkList = checkListService.getCheckListById(checkListId);
        User user = userService.findById(userPrincipal.getId());
        LikeResponse likeResponse = likeService.addLike(checkList, user);
        return ApiResponse.createSuccess(likeResponse);
    }

    @DeleteMapping("/{checkListId}/like")
    @Operation(summary = "좋아요 삭제", description = "체크리스트에 좋아요를 삭제합니다.")
    public ApiResponse<LikeResponse> deleteLike (@PathVariable Long checkListId, @AuthenticationPrincipal UserPrincipal userPrincipal){
        CheckList checkList = checkListService.getCheckListById(checkListId);
        User user = userService.findById(userPrincipal.getId());
        LikeResponse likeResponse = likeService.deleteLike(checkList, user);
        return ApiResponse.createSuccess(likeResponse);
    }
}
