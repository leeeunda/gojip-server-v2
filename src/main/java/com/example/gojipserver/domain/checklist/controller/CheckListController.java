package com.example.gojipserver.domain.checklist.controller;

import com.example.gojipserver.domain.checklist.dto.CheckListSaveDto;
import com.example.gojipserver.domain.checklist.service.CheckListService;
import com.example.gojipserver.global.oauth2.entity.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/checklists")
@Slf4j
public class CheckListController {

    private final CheckListService checkListService;

    //체크리스트 등록
    @PostMapping
    public void saveCheckList(@AuthenticationPrincipal UserPrincipal user, @RequestBody CheckListSaveDto checkListSaveDto) {
        Long userId = user.getId();
    }

    // 컬렉션 등록
    // 체크리스트 전체 조회
//    @GetMapping()
//    public ResponseEntity<MessageDto> checkListAllGet(){
//
//        MessageDto messageDto = new MessageDto();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
//
//        messageDto.setStatus(HttpStatus.OK.value());
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