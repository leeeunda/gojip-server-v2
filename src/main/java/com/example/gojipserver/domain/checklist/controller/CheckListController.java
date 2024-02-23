package com.example.gojipserver.domain.checklist.controller;

import com.example.gojipserver.domain.checklist.dto.CheckListSaveDto;
import com.example.gojipserver.domain.checklist.service.CheckListService;
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
    public ApiResponse<Long> saveCheckList(@AuthenticationPrincipal UserPrincipal requestUser, @RequestBody @Valid CheckListSaveDto checkListSaveDto) {

        Long savedCheckListId = checkListService.saveCheckList(requestUser.getId(), checkListSaveDto);

        return ApiResponse.createSuccess(savedCheckListId);
    }

    //    // 체크리스트 수정
//    @PutMapping("/{checklist_id}")
//    public String checkListCollectionGet(){
//        return
//    }
//

    // 체크리스트 삭제
    @DeleteMapping("/{checkListId}")
    @Operation(summary = "체크리스트 단일 삭제", description = "체크리스트를 삭제, 삭제 요청을 한 유저가 해당 체크리스트의 주인인지 확인")
    @Parameter(name = "requestUser", description = "요청을 보내는 회원의 정보를 UserPrincipal 타입으로 받습니다.")
    @Parameter(name = "id", description = "삭제할 CheckList의 id")
    public ApiResponse deleteCheckList(@AuthenticationPrincipal UserPrincipal requestUser, @PathVariable Long checkListId) {

        checkListService.deleteCheckList(requestUser.getId(), checkListId);

        return ApiResponse.createSuccessWithNoContent();
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



    //체크리스트 전체 조회
//    @GetMapping()
//    public ResponseEntity<ApiResponse> checkListAllGet(){
//
//        ApiResponse apiResponse = new ApiResponse();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//        apiResponse.setStatus(HttpStatus.OK.value());
//        messageDto.setMessage("전체 조회 성공");
//        messageDto.setData(CheckList);
//        return new ResponseEntity<>(messageDto, headers,  )
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

}
