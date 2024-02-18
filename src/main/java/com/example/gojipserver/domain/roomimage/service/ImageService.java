package com.example.gojipserver.domain.roomimage.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.repository.CheckListRepository;
import com.example.gojipserver.domain.roomimage.dto.RoomImageDto;
import com.example.gojipserver.domain.roomimage.entity.RoomImage;
import com.example.gojipserver.domain.roomimage.repository.RoomImageRepository;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final AmazonS3 s3Client;
    private final RoomImageRepository roomImageRepository;
    private final CheckListRepository checkListRepository;


    // 버킷 이름
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    public ImageService(AmazonS3 s3Client){
        this.s3Client=s3Client;
    }

    // 파일 업로드 -> 이미지의 url 반환, db에 저장
    public String upload(MultipartFile file) throws IOException {

        // 원본 이미지 이름
        String fileName = createFileName(file.getOriginalFilename());

        //메타 데이터
        final ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        try{
            s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead)); // 외부에 공개할 이미지이므로, 해당 파일에 public read 권한을 추가
        } catch(IOException e){
            throw new IOException("이미지 등록 오류", e);
        }
        return s3Client.getUrl(bucket, fileName).toString();
    }

    // 이미지 이름 중복 방지 파일 이름 생성
    private String createFileName(String originalFilename) {
        return new Date().getTime() + "-" + originalFilename;
    }

    // 업로드 이후 RoomImage Entity를 DB에 저장
    @Transactional
    public void saveImageToDB(RoomImageDto roomImageDto) throws IOException{

        CheckList checkList=checkListRepository.findById(roomImageDto.getCheckListId())
                .orElseThrow(() -> new IllegalArgumentException("해당 체크리스트를 찾을 수 없습니다. id= "+roomImageDto.getCheckListId());
        RoomImage roomImage = roomImageDto.toEntity(checkList);
        roomImageRepository.save(roomImage);

    }

//    // 파일 수정
//    public String update(String oldFileName, MultipartFile newFile) throws IOException{
//
//        //기존 파일 삭제
//        delete(oldFileName);
//
//        //새 파일 업로드
//        return upload(newFile);
//    }

    // 파일 삭제
    public void delete(String fileName) throws IOException{
        try{
            s3Client.deleteObject(bucket, fileName); //삭제할 버킷 및 객체의 이름 전달
        } catch (SdkClientException e){
            throw new IOException("S3부터 파일 제거 오류", e);
        }
    }


}
