package com.example.gojipserver.domain.checklist.controller;

import com.example.gojipserver.domain.checklist.dto.*;
import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.service.CheckListService;
import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.domain.roomaddress.dto.RoomAddressCheckListInfoDto;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import com.example.gojipserver.domain.roomimage.repository.RoomImageRepository;
import com.example.gojipserver.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    @Parameter(name="checkListSaveDto")
    public ApiResponse<Long> saveCheckList(@AuthenticationPrincipal UserPrincipal requestUser, @RequestBody @Valid CheckListRequestDto.SaveDto requestDto) {
        Long savedCheckListId = checkListService.saveCheckList(requestUser.getId(), requestDto);
        return ApiResponse.response201Success(savedCheckListId, "체크리스트 등록 완료!");
    }

    // 체크리스트 수정
    @PutMapping("/{id}")
    @Operation(summary = "체크리스트 수정", description = "체크리스트 수정 api")
    @Parameter(name = "requestUser", description = "요청을 보내는 회원의 정보를 UserPrincipal 타입으로 받습니다.")
    @Parameter(name="requestDto")
    public ApiResponse<Long> updateCheckList(@PathVariable("id") Long checkListId, @AuthenticationPrincipal UserPrincipal requestUser, @RequestBody @Valid CheckListRequestDto.UpdateDto requestDto){
        Long updatedCheckListId = checkListService.updateCheckList(checkListId, requestUser.getId(), requestDto);
        return ApiResponse.responseSuccess(updatedCheckListId, "체크리스트 수정 완료!");
    }

    @PutMapping("/public/{id}")
    @Operation(summary = "체크리스트 공개 범위 수정", description = "체크리스트 공개 범위 수정 api")
    @Parameter(name = "requestUser", description = "요청을 보내는 회원의 정보를 UserPrincipal 타입으로 받습니다.")
    @Parameter(name="requestDto")
    public ApiResponse updatePublic(@PathVariable("id") Long checkListId, @AuthenticationPrincipal UserPrincipal requestUser){
        checkListService.updatePublic(requestUser.getId(), checkListId);
        return ApiResponse.responseSuccessWithNoContent("공개 범위 수정 완료!");
    }


    // 체크리스트 삭제
    @DeleteMapping("/{id}")
    @Operation(summary = "체크리스트 단일 삭제", description = "체크리스트를 삭제, 삭제 요청을 한 유저가 해당 체크리스트의 주인인지 확인")
    @Parameter(name = "requestUser", description = "요청을 보내는 회원의 정보를 UserPrincipal 타입으로 받습니다.")
    @Parameter(name = "id", description = "삭제할 CheckList의 id")
    public ApiResponse deleteCheckList(@PathVariable("id") Long checkListId, @AuthenticationPrincipal UserPrincipal requestUser) {
        checkListService.deleteCheckList(requestUser.getId(), checkListId);
        return ApiResponse.responseSuccessWithNoContent("체크리스트 삭제 완료!");
    }

    // 체크리스트 단일 조회
    @GetMapping("/{id}")
    @Operation(summary = "체크리스트 단일 조회", description = "하나의 체크리스트를 조회")
    @Parameter(name = "id", description = "조회할 CheckList의 id")
    public ApiResponse<CheckListOneGetDto> checkListOneGet(@PathVariable("id") Long checkListId){

        CheckList checkList=checkListService.getCheckListById(checkListId);
        RoomAddress roomAddress = checkListService.getRoomAddressByCheckListId(checkListId);
//        CheckListOneGetDto checkListOneGetDto = new CheckListOneGetDto(checkList, roomAddress);
//        return ApiResponse.createSuccess(checkListOneGetDto);
        return null;
    }

    //체크리스트 전체 조회
    @GetMapping
    @Operation(summary = "유저가 보유한 체크리스트 조회", description = "유저가 작성한 체크리스트 전부를 조회")
    @Parameter(name = "requestUser", description = "요청을 보내는 회원의 정보를 UserPrincipal 타입으로 받습니다.")
    @Parameter(name="checkListAllGetDto")
    public ApiResponse<List<CheckListAllGetDto>> checkListAllGet(@AuthenticationPrincipal UserPrincipal requestUser){

        List<CheckListAllGetDto> checkListAll = checkListService.getAllCheckListByUserId(requestUser.getId());

        return ApiResponse.responseSuccess(checkListAll);
    }

    // 컬렉션별 체크리스트 조회
    @GetMapping("/collections/{collectionId}")
    @Operation(summary = "컬렉션 별 체크리스트 조회", description = "컬렉션에 속한 체크리스트들을 조회")
    @Parameter(name="collectionId", description = "체크리스트들을 조회할 컬렉션의 ID")
    public ApiResponse<List<CheckListCollectionGetDto>> checkListCollectionGet(@PathVariable Long collectionId){
        List<CheckListCollectionGetDto> checkLists = checkListService.getChecklistsByCollectionId(collectionId);

        return ApiResponse.responseSuccess(checkLists);
    }

//    일단 주석 처리
//    @PostMapping("/test-images")
//    @Operation(summary = "image 등록 API", description = "임시 api : 실제 이미지를 등록하는게 아닌 imgUrl만 dto로 받아 등록")
//    @Parameter(name="roomImageSaveDto", description = "imgUrl을 String 값으로 담아주세요.")
//    public ApiResponse<Long> saveRoomImage(@RequestBody RoomImageSaveDto roomImageSaveDto) {
//
//        RoomImage savedRoomImage = roomImageRepository.save(roomImageSaveDto.toEntity());
//
//        return ApiResponse.createSuccess(savedRoomImage.getId());
//    }

    @GetMapping("/recent")
    @Operation(summary = "최근 체크리스트 조회", description = "최근에 작성한 체크리스트 3개를 조회")
    public ApiResponse<List<CheckListRecentResponseDto>> getRecentCheckListTop3(@AuthenticationPrincipal UserPrincipal requestUser) {
        List<CheckListRecentResponseDto> recentCheckListTop3 = checkListService.getRecentCheckListTop3(requestUser.getId());
        return ApiResponse.responseSuccess(recentCheckListTop3);
    }

    // 리뷰가 많은 구 상위 7개
    @GetMapping("/city/count")
    @Operation(summary = "리뷰가 많은 구 조회", description = "리뷰가 많은 구 상위 7개를 조회")
    public ApiResponse<List<CheckListCityCountGetDto>> checkListcityCountGet(){
        List<CheckListCityCountGetDto> checkListCityCountGetDtos = checkListService.getCityCountTop7();
        return ApiResponse.responseSuccess(checkListCityCountGetDtos);
    }

    @GetMapping("/city")
    @Operation(summary = "구별 체크리스트 조회", description = "구별 체크리스트 조회")
    public ApiResponse<Page<CheckListCityAllGetDto>> checkListCityAllGet(@RequestParam String city, @RequestParam(defaultValue = "0") int page) {
        PageRequest pageable = PageRequest.of(page, 5);
        Page<CheckListCityAllGetDto> checkListsByCity = checkListService.getCheckListsByCity(city,pageable);
        return ApiResponse.responseSuccess(checkListsByCity);
    }

    @GetMapping("/location")
    @Operation(summary = "위치별 체크리스트 요약 조회", description = "위치별 체크리스트 요약 조회")
    public ApiResponse<Page<CheckListSummaryGetDto>> checkListsByAddressGet(
            @RequestParam String latitude,
            @RequestParam String longitude,
            @RequestParam(defaultValue = "0") int page){
        PageRequest pageRequest = PageRequest.of(page, 5);
        Page<CheckListSummaryGetDto> checkListSummaryGetDtos = checkListService.getCheckListSummarys(
                latitude,longitude, pageRequest);
        return ApiResponse.responseSuccess(checkListSummaryGetDtos);
    }

    @GetMapping("/location/info")
    @Operation(summary = "위치별 체크리스트 정보 조회", description = "위치별 체크리스트 정보 조회")
    public ApiResponse<RoomAddressCheckListInfoDto> checkListInfoByAddressGet(
            @RequestParam String latitude,
            @RequestParam String longitude){
        RoomAddressCheckListInfoDto roomAddressCheckListInfoDto = checkListService.getCheckListsByAddress(latitude, longitude);
        return ApiResponse.responseSuccess(roomAddressCheckListInfoDto);
    }
}
