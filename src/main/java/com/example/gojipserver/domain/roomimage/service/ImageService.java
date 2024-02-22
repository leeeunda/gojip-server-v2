package com.example.gojipserver.domain.roomimage.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.gojipserver.domain.checklist.entity.CheckList;
import com.example.gojipserver.domain.checklist.repository.CheckListRepository;
import com.example.gojipserver.domain.roomimage.dto.RoomImageSaveDto;
import com.example.gojipserver.domain.roomimage.entity.RoomImage;
import com.example.gojipserver.domain.roomimage.repository.RoomImageRepository;
import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

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


    // S3로 파일 업로드 -> 이미지의 url 반환
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

    // 이미지 이름 중복 방지 파일 이름 생성 -> UUID 방식으로 변경
    private String createFileName(String originalFilename) {
        return UUID.randomUUID().toString().concat(getFileExtension(originalFilename));
    }

    private String getFileExtension(String fileName){
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        } catch(StringIndexOutOfBoundsException se){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일입니다.");
        }
    }

    // 업로드 이후 RoomImage Entity를 DB에 저장 (imgUrl만 우선저장)
    @Transactional
    public void saveImageToDB(RoomImageSaveDto roomImageSaveDto) throws IOException{
//        CheckList checkList=checkListRepository.findById(roomImageSaveDto.getCheckListId())
//                .orElseThrow(() -> new IllegalArgumentException("해당 체크리스트를 찾을 수 없습니다. id= "+ roomImageSaveDto.getCheckListId()));
        RoomImage roomImage = roomImageSaveDto.toEntity();
        roomImageRepository.save(roomImage);

    }

    // 파일 수정
    @Transactional
    public String updateImage(Long id, String imgUrl, MultipartFile newFile) throws IOException{

        // s3에서 삭제
        deleteImage(id, imgUrl);

        // DB에서 삭제



        String newImgUrl = upload(newFile);
        RoomImageSaveDto roomImageSaveDto = new RoomImageSaveDto(newImgUrl);
        RoomImage newRoomImage= roomImageSaveDto.toEntity();

        roomImageRepository.save(newRoomImage);

        //새 파일 업로드
        return newImgUrl;
    }

    // s3로부터 이미지 찾기
    public String getUrl(String imgUrl){
        return s3Client.getUrl(bucket, imgUrl).toString();
    }

    // 이미지 삭제
    @Transactional
    public void deleteImage(Long id, String imgUrl) throws IOException{

        try{
            RoomImage roomImage = roomImageRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 없습니다. id=" + id));
            String splitStr = ".com/";
            String fileName = imgUrl.substring(imgUrl.lastIndexOf(splitStr) + splitStr.length());
            log.info("file name: " + fileName);

            s3Client.deleteObject(bucket, fileName); //삭제할 버킷 및 객체의 이름 전달
            roomImageRepository.delete(roomImage); // db에서 삭제

        } catch (SdkClientException e){
            throw new IOException("S3부터 파일 제거 오류", e);
        }
    }

    // 체크리스트 생성 이후 연관관계 설정
    @Transactional
    public void setCheckListToRoomImage(Long roomImageId, CheckList checkList){
        RoomImage roomImage = roomImageRepository.findById(roomImageId)
                .orElseThrow(() -> new IllegalArgumentException("해당 이미지가 없습니다. id = "+ roomImageId));
        roomImage.setCheckList(checkList);
    }

}
