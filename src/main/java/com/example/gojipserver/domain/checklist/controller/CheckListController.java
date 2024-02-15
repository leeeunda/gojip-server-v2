package com.example.gojipserver.domain.checklist.controller;

import com.example.gojipserver.domain.checklist.dto.CheckListSaveDto;
import com.example.gojipserver.domain.checklist.service.CheckListService;
import com.example.gojipserver.domain.collection.dto.CollectionSaveDto;
import com.example.gojipserver.domain.oauth2.entity.UserPrincipal;
import com.example.gojipserver.global.response.ApiResponse;
import com.example.gojipserver.global.response.StatusEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@Tag(name = "CheckList API", description = "체크리스트 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/checklists")
@Slf4j
public class CheckListController {

    private final CheckListService checkListService;

//    //체크리스트 등록
//    @PostMapping
//    @Operation(summary = "체크리스트 등록", description = "체크리스트 등록 api")
//    @Parameter(name="checkListSaveDto", description = "")
//    public ApiResponse saveCheckList(@RequestBody CheckListSaveDto checkListSaveDto) {
//
//        return ApiResponse.createSuccess();
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