package com.example.gojipserver.domain.roomimage.controller;

import com.amazonaws.Response;
import com.example.gojipserver.global.response.ApiResponse;
import org.springframework.http.MediaType;
import com.example.gojipserver.domain.roomimage.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

// 이미지 등록
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/checklists/images")
public class RoomImageController {

    private final ImageService imageService;

    // 한 번에 한 장의 이미지 업로드

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE } )
    public ApiResponse<String> execWrite(@RequestPart MultipartFile file) throws IOException{
        String imgPath = imageService.upload(file);
        log.info("imagePath = {}", imgPath);
        return ApiResponse.createSuccess(imgPath);
    }

    // 여러장의 이미지 한 번에 업로드
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
}
