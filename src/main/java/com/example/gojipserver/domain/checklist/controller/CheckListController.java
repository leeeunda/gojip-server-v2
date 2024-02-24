package com.example.gojipserver.domain.checklist.controller;

import com.example.gojipserver.domain.checklist.dto.CheckListResponseDto;
import com.example.gojipserver.domain.checklist.dto.CheckListSaveDto;
import com.example.gojipserver.domain.checklist.service.CheckListService;
import com.example.gojipserver.domain.collection.dto.CollectionResponseDto;
import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.domain.roomimage.dto.RoomImageSaveDto;
import com.example.gojipserver.domain.roomimage.entity.RoomImage;
import com.example.gojipserver.domain.roomimage.repository.RoomImageRepository;
import com.example.gojipserver.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "CheckList API", description = "체크리스트 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/checklists")
@Slf4j
public class CheckListController {

    private final CheckListService checkListService;
    private final RoomImageRepository roomImageRepository;

    //체크리스트 등록
    @PostMapping
    @Operation(summary = "체크리스트 등록", description = "체크리스트 등록 api")
    @Parameter(name = "requestUser", description = "요청을 보내는 회원의 정보를 UserPrincipal 타입으로 받습니다.")
    @Parameter(name="checkListSaveDto", description = "노션의 기능명세서를 참고해주세요")
    public ApiResponse<Long> saveCheckList(@AuthenticationPrincipal UserPrincipal requestUser, @RequestBody @Valid CheckListSaveDto checkListSaveDto) {

        Long savedCheckListId = checkListService.saveCheckList(requestUser.getId(), checkListSaveDto);

        return ApiResponse.createSuccess(savedCheckListId);
    }

//    삭제 여부 물어보기
//    @PostMapping("/test-images")
//    @Operation(summary = "image 등록 API", description = "임시 api : 실제 이미지를 등록하는게 아닌 imgUrl만 dto로 받아 등록")
//    @Parameter(name="roomImageSaveDto", description = "imgUrl을 String 값으로 담아주세요.")
//    public ApiResponse<Long> saveRoomImage(@RequestBody RoomImageSaveDto roomImageSaveDto) {
//
//        RoomImage savedRoomImage = roomImageRepository.save(roomImageSaveDto.toEntity());
//
//        return ApiResponse.createSuccess(savedRoomImage.getId());
//    }



//    체크리스트 전체 조회
//    @GetMapping()
//    @Operation(summary="체크리스트 전체 조회", description = "User의 체크리스트 전체를 조회하는 api")
//    @Parameter(name = "requestUser", description = "요청을 보내는 회원의 정보를 UserPrincipal 타입으로 받습니다.")
//    public ApiResponse<List<CheckListResponseDto>> checkListAllGet(@AuthenticationPrincipal UserPrincipal requestUser){
//        List<CheckListResponseDto> checkListResponseDto = checkListService.getChecklists(requestUser.getId());
//        return ApiResponse.createSuccess(check)
//
//    }

//    @GetMapping
//    @Operation(summary = "유저의 컬렉션 조회", description = "요청한 유저의 정보를 받아 컬렉션을 조회")
//    @Parameter(name = "requestUser", description = "요청을 보내는 회원의 정보를 UserPrincipal 타입으로 받습니다.")
//    public ApiResponse<List<CollectionResponseDto>> getCollections(@AuthenticationPrincipal UserPrincipal requestUser) {
//
//        List<CollectionResponseDto> collectionList = collectionService.getCollections(requestUser.getId());
//
//        return ApiResponse.createSuccess(collectionList);
//    }
//    // 체크리스트 단일 조회
//    @GetMapping("/{id}")
//    public String checkListOneGet(){
//        return
//    }
//
//    // 체크리스트 구별 조회 -> 수정 가능
//    @GetMapping("/checklists?city=\"동작구\"")
//    public String checkListCityGet(){
//
//        return
//    }
//
//    // 체크리스트 컬렉션 별 조회
//    @GetMapping("/{collection_id}")
//    public String checkListCollectionGet(){
//        return
//    }
//
//    // 체크리스트 수정
//    @PutMapping("/{checklist_id}")
//    public String checkListCollectionGet(){
//        return
//    }
//
//    // 체크리스트 삭제
//    @DeleteMapping("/{checklist_id}")
//    public String checkListDelete(){
//        return
//    }
}
