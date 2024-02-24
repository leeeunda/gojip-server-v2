package com.example.gojipserver.domain.collection.controller;

import com.example.gojipserver.domain.collection.dto.CollectionResponseDto;
import com.example.gojipserver.domain.collection.dto.CollectionSaveDto;
import com.example.gojipserver.domain.collection.dto.CollectionUpdateDto;
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

import java.util.List;

@Tag(name = "Collection API", description = "테스트 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/collections")
public class CollectionController {

    private final CollectionService collectionService;

    // TODO: collectionName null, 중복 예외 처리
    @PostMapping
    @Operation(summary = "컬렉션 등록", description = "요청한 유저의 정보를 받아 컬렉션을 등록")
    @Parameter(name = "requestUser", description = "요청을 보내는 회원의 정보를 UserPrincipal 타입으로 받습니다.")
    @Parameter(name = "collectionSaveDto", description = "collectionName을 담은 DTO")
    public ApiResponse<Long> saveCollection(@AuthenticationPrincipal UserPrincipal requestUser, @RequestBody @Valid CollectionSaveDto collectionSaveDto) {

        Long savedCollectionId = collectionService.saveCollection(requestUser.getId(), collectionSaveDto);
        return ApiResponse.createSuccess(savedCollectionId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "컬렉션 수정", description = "컬렉션 이름 수정")
    @Parameter(name = "requestUser", description = "요청을 보내는 회원의 정보를 UserPrincipal 타입으로 받습니다.")
    @Parameter(name = "id", description = "수정할 Collection의 id")
    @Parameter(name = "collectionUpdateDto", description = "collectionName을 담은 DTO")
    public ApiResponse updateCollection(@PathVariable("id") Long collectionId, @AuthenticationPrincipal UserPrincipal requestUser, @RequestBody @Valid CollectionUpdateDto collectionUpdateDto) {

        Long updatedCollection = collectionService.updateCollection(collectionId, requestUser.getId(), collectionUpdateDto);

        return ApiResponse.createSuccess(updatedCollection);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "컬렉션 단일 삭제", description = "컬렉션을 삭제, 삭제 요청을 한 유저가 해당 컬렉션의 주인인지 확인")
    @Parameter(name = "requestUser", description = "요청을 보내는 회원의 정보를 UserPrincipal 타입으로 받습니다.")
    @Parameter(name = "id", description = "삭제할 Collection의 id")
    public ApiResponse deleteCollection(@AuthenticationPrincipal UserPrincipal requestUser, @PathVariable("id") Long collectionId) {

        collectionService.deleteCollection(requestUser.getId(), collectionId);

        return ApiResponse.createSuccessWithNoContent();
    }

    @GetMapping
    @Operation(summary = "유저의 컬렉션 조회", description = "요청한 유저의 정보를 받아 컬렉션을 조회")
    @Parameter(name = "requestUser", description = "요청을 보내는 회원의 정보를 UserPrincipal 타입으로 받습니다.")
    public ApiResponse<List<CollectionResponseDto>> getCollections(@AuthenticationPrincipal UserPrincipal requestUser) {

        List<CollectionResponseDto> collectionList = collectionService.getCollections(requestUser.getId());

        return ApiResponse.createSuccess(collectionList);
    }

}
