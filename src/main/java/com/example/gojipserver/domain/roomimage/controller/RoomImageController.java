package com.example.gojipserver.domain.roomimage.controller;

import com.amazonaws.Response;
import com.example.gojipserver.domain.roomimage.dto.RoomImageDto;
import com.example.gojipserver.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import com.example.gojipserver.domain.roomimage.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
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
    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ApiResponse<String> execWrite(@RequestPart MultipartFile file) throws IOException{
        String imgPath = imageService.upload(file);
        log.info("imagePath = {}", imgPath);
        return ApiResponse.createSuccess(imgPath);
    }

    // 여러장의 이미지 한 번에 업로드
    @Operation(summary="여러 이미지 업로드", description = "여러 장의 이미지를 받아 업로드")
    @PostMapping(value="/bulk", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<List<String>> execWriteBulk(@RequestPart List<MultipartFile> file) throws IOException{

        final List<String> imgurl = file.stream()
                .map(multipartFile -> {
                    try {
                        return imageService.upload(multipartFile);
                    } catch (IOException e) {
                        log.error("이미지 업로드 오류", e);
                        return null;
                    }
                }).peek(s -> log.info("imagePath = {}", s))
                .collect(Collectors.toList());
        return ApiResponse.createSuccess(imgurl);
    }

    // 이미지 업데이트
    @Operation(summary = "이미지 수정", description = "이미지를 삭제 후 다시 업로드")
    @PutMapping(value="/{fileName}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ApiResponse<String> updateFile(@PathVariable String fileName, @RequestPart MultipartFile newFile) throws IOException{

        String newImgPath = imageService.updateImage(fileName, newFile);
        log.info("new imagePath = {}", newImgPath);

        return ApiResponse.createSuccess(newImgPath);
    }

    // 이미지 삭제
    @Operation(summary = "이미지 삭제", description = "이미지 url을 DB에서 삭제")
    @DeleteMapping(value = "/{fileName}")
    ApiResponse<String> deleteFile(@PathVariable String fileName) throws IOException{
        try {
            // s3에서 이미지 원본 삭제
            imageService.deleteImage(fileName);
            log.info("deleted file : {}", fileName);
        } catch (IOException e) {
            log.error("파일 삭제 에러", e);
        }
        return ApiResponse.createSuccess("Deleted"+fileName)
    }
}
