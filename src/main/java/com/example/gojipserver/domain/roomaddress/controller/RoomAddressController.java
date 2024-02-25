package com.example.gojipserver.domain.roomaddress.controller;

import com.example.gojipserver.domain.roomaddress.dto.TempRoomAddressSaveDto;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import com.example.gojipserver.domain.roomaddress.repository.RoomAddressRepository;
import com.example.gojipserver.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/roomAddresses")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "RoomAddress", description = "방 주소 등록 API")
public class RoomAddressController {

    private final RoomAddressRepository roomAddressRepository;

    @PostMapping("/temp")
    @Operation(summary = "임시 API", description = "방 주소 등록 임시 API")
    public ApiResponse saveTempRoomAddress(@RequestBody @Valid TempRoomAddressSaveDto roomAddressSaveDto) {

        RoomAddress entity = RoomAddress.createRoomAddress(roomAddressSaveDto.getAddressName(),
                roomAddressSaveDto.getLatitude(),
                roomAddressSaveDto.getLongitude());

        RoomAddress savedRoomAddress = roomAddressRepository.save(entity);

        return ApiResponse.createSuccess(savedRoomAddress);

    }
}
