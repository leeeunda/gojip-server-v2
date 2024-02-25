package com.example.gojipserver.domain.roomaddress.controller;

import com.example.gojipserver.domain.roomaddress.dto.RoomAddressResponseDto;
import com.example.gojipserver.domain.roomaddress.dto.RoomAddressSaveDto;
import com.example.gojipserver.domain.roomaddress.entity.Coordinates;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import com.example.gojipserver.domain.roomaddress.repository.RoomAddressRepository;
import com.example.gojipserver.domain.roomaddress.service.RoomAddressService;
import com.example.gojipserver.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roomAddresses")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "RoomAddress", description = "방 주소 등록 API")
public class RoomAddressController {

    private final RoomAddressService roomAddressService;
    private final RoomAddressRepository roomAddressRepository;

    // 이 집 체크하러가기 버튼 클릭시 우선 주소만 저장하는 등록 API
    @PostMapping()
    @Operation(summary = "주소 저장 API", description = "주소를 받아서 주소이름, x좌표, y좌표를 저장하는 API")
    public ApiResponse<RoomAddressResponseDto> saveRoomAddress(@RequestBody RoomAddressSaveDto roomAddressSaveDto){

        RoomAddress roomAddress = roomAddressService.getCoordinateAndSave(roomAddressSaveDto.getAddressName());
        RoomAddressResponseDto roomAddressResponseDto = new RoomAddressResponseDto(roomAddress.getAddressName(), String.valueOf(roomAddress.getLatitude()), String.valueOf(roomAddress.getLongitude()));

        return ApiResponse.createSuccess(roomAddressResponseDto);
    }

    //좌표 조회 테스트 API -> 주석처리
//    @GetMapping()
//    @Operation(summary="좌표 반환 테스트 API", description="주소를 받아서 x좌표,y좌표를 반환 (프론트에서 쓰지 않아도 됨")
//    public Coordinates Roomaddress(@RequestParam String RoomAddress){
//        return roomAddressService.getCoordinate(RoomAddress);
//    }
}
