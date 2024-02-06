package com.example.gojipserver.global.aws.controller;

import com.example.gojipserver.global.aws.service.AwsS3Service;
import com.example.gojipserver.global.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/s3")
public class AmazonS3Controller {

    private final AwsS3Service awsS3Service;

    /**
     * Amazon S3에 파일 업로드
     *
     * @return 성공 시 200 Success와 함께 업로드 된 파일의 파일명 리스트 반환
     */
    @PostMapping("/files")
    public ApiResponse<List<String>> uploadFile(@RequestPart List<MultipartFile> multipartFile) {
        return ApiResponse.createSuccess(awsS3Service.uploadFile(multipartFile));
    }

    /**
     * S3에 업로드된 단일 파일 조회
     * @param fileName
     * @return
     */
    @GetMapping("/files")
    public String getFile(@RequestParam("fileName") String fileName) {
        return awsS3Service.getFile(fileName);
    }

    /**
     * Amazon S3에 업로드 된 파일을 삭제
     *
     * @return 성공 시 200 Success
     */
    @DeleteMapping("/files")
    public ApiResponse<Object> deleteFile(@RequestParam String fileName) {
        awsS3Service.deleteFile(fileName);
        return ApiResponse.createSuccess(null);
    }
}
