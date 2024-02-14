package com.example.gojipserver.domain.roomimage.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import jakarta.annotation.PostConstruct;
import lombok.NoArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Slf4j
@Service
@NoArgsConstructor
public class ImageService {

    private AmazonS3 s3Client;

    // 버킷 이름
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    // 파일 업로드
    public String upload(MultipartFile file) throws IOException {
        String fileName = createFileName(file.getOriginalFilename());
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), objectMetadata)
                .withCannedAcl(CannedAccessControlList.PublicRead)); // 외부에 공개할 이미지이므로, 해당 파일에 public read 권한을 추가
        return s3Client.getUrl(bucket, fileName).toString();
    }

    private String createFileName(String originalFilename) {
        return new Date().getTime() + "-" + originalFilename;
    }
}
