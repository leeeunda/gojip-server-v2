package com.example.gojipserver.domain.collection.controller;

import com.example.gojipserver.domain.collection.dto.CollectionSaveDto;
import com.example.gojipserver.domain.collection.service.CollectionService;
import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Collection API", description = "테스트 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;

    // TODO: collectionName null, 중복 예외 처리
    @PostMapping
    @Operation(summary = "컬렉션 등록", description = "로그인한 유저의 정보를 받아 컬렉션을 등록")
    @Parameter(name = "collectionSaveDto", description = "collectionName을 받습니다.")
    public ApiResponse<Long> saveCollection(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody @Valid CollectionSaveDto collectionSaveDto) {

        Long savedCollectionId = collectionService.saveCollection(userPrincipal.getId(), collectionSaveDto);
        return ApiResponse.createSuccess(savedCollectionId);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "컬렉션 삭제", description = "컬렉션을 삭제, 삭제 요청을 한 유저가 해당 컬렉션의 주인인지 확인")
    @Parameter(name = "id", description = "삭제할 Collection의 id")
    public ApiResponse<?> deleteCollection(@AuthenticationPrincipal UserPrincipal userPrincipal, @PathVariable Long id) {

        collectionService.deleteCollection(userPrincipal.getId(), id);

        return ApiResponse.createSuccessWithNoContent();
    }

}
