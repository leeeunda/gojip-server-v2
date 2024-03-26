package com.example.gojipserver.domain.roomimage.controller;

import com.example.gojipserver.domain.roomimage.dto.RoomImageSaveDto;
import com.example.gojipserver.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import com.example.gojipserver.domain.roomimage.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

// 이미지 등록
@Tag(name = "RoomImage API", description = "방 이미지 API")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/checklists/images")
public class RoomImageController {

    private final ImageService imageService;

    // 한 번에 한 장의 이미지 업로드
    @Operation(summary = "단일 이미지 업로드", description = "하나의 이미지를 받아 업로드")
    @Parameter(name = "MultipartFile", description = "사용자가 업로드하려고 하는 한 장의 이미지를 받습니다.")
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ApiResponse<String> execWrite(@RequestPart MultipartFile file) throws IOException{
        String imgPath = imageService.upload(file);
        RoomImageSaveDto roomImageSaveDto = new RoomImageSaveDto(imgPath);
        imageService.saveImageToDB(roomImageSaveDto); // 업로드 이후 DB 저장
        log.info("imagePath = {}", imgPath);
        return ApiResponse.responseSuccess(imgPath);
    }

    // 여러장의 이미지 한 번에 업로드
    @Operation(summary="여러 이미지 업로드", description = "여러 장의 이미지를 받아 업로드")
    @Parameter(name = "requestUser", description = "사용자가 업로드하려고 하는 여러 이미지를 받습니다.")
    @PostMapping(value="/bulk", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<List<String>> execWriteBulk(@RequestPart List<MultipartFile> file) throws IOException{

        final List<String> imgurl = file.stream()
                .map(multipartFile -> {
                    try {
                        String imgPath = imageService.upload(multipartFile);
                        RoomImageSaveDto roomImageSaveDto = new RoomImageSaveDto(imgPath);
                        imageService.saveImageToDB(roomImageSaveDto);
                        return imgPath;
                    } catch (IOException e) {
                        log.error("이미지 업로드 오류", e);
                        return null;
                    }
                }).peek(s -> log.info("imagePath = {}", s))
                .collect(Collectors.toList());
        return ApiResponse.responseSuccess(imgurl);
    }

    // 이미지 업데이트
    @Operation(summary = "이미지 수정", description = "이미지를 삭제 후 다시 업로드")
    @Parameter(name = "requestUser", description = "사용자가 삭제하려고 이미지 id와 imgUrl, 올리려고 하는 새로운 파일을 받습니다.")
    @PutMapping(value="/{id}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<String> updateFile(@PathVariable Long id, @RequestParam String imgUrl, @RequestPart MultipartFile newFile) throws IOException{

        String newImgUrl = imageService.updateImage(id, imgUrl, newFile);
        log.info("새로운 imageUrl = {}", newImgUrl);

        return ApiResponse.responseSuccess(newImgUrl);
    }

    // 이미지 조회 -> 체크리스트 관련 이미지 전부 반환
    @Operation(summary = "이미지 조회", description = "이미지 url을 반환하는 조회 API입니다.")
    @Parameter(name="id", description = "체크리스트의 id를 받아 그 체크리스트 id에 포함되어 있는 이미지 url들을 반환합니다.")
    @GetMapping(value="/{id}")
    public ApiResponse<List<String>> getImages(@PathVariable Long id){
        List<String> imageUrls = imageService.getImagesByCheckListId(id);
        return ApiResponse.responseSuccess(imageUrls);
    }

    // 이미지 삭제
    @Operation(summary = "이미지 삭제", description = "이미지 url을 DB에서 삭제")
    @Parameter(name = "requestUser", description = "사용자가 삭제하려고 하는 이미지 Id를 받습니다.")
    @DeleteMapping(value = "/{id}")
    public ApiResponse<String> deleteFile(@PathVariable Long id, @RequestParam String imgUrl) throws IOException{
        try {
            imageService.deleteImage(id, imgUrl);
            log.info("Deleted image id: {}, fileName: {}", id, imgUrl);
        } catch (IOException e) {
            log.error("파일 삭제 에러", e);
        }
        return ApiResponse.responseSuccess("파일 성공적으로 삭제 imgUrl: " + imgUrl);
    }

    //대표 이미지 설정
    @Operation(summary = "대표 이미지 설정", description = "업로드하는 이미지 중 대표 이미지를 설정")
    @Parameter()
    public ApiResponse<?> setThumbnailImage(@PathVariable Long id){
        try{
            imageService.setThumbnailImage(id);
            return ApiResponse.createSuccess(id);
        } catch (Exception e){
            return ApiResponse.
        }
    }
}
