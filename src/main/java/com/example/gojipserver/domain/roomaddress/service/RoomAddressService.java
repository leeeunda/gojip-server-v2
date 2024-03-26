package com.example.gojipserver.domain.roomaddress.service;

import com.example.gojipserver.domain.roomaddress.dto.RoomAddressSaveDto;
import com.example.gojipserver.domain.roomaddress.entity.RoomAddress;
import com.example.gojipserver.domain.roomaddress.repository.RoomAddressRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.gojipserver.domain.roomaddress.entity.Coordinates;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Service
@RequiredArgsConstructor
public class RoomAddressService {

    private final RoomAddressRepository roomAddressRepository;
    private final String uri = "https://dapi.kakao.com/v2/local/search/address.json";

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoLocalKey;

    @Transactional
    public Coordinates getCoordinate(String RoomAddress){
        RestTemplate restTemplate = new RestTemplate();
        String apiKey = "KakaoAK " + kakaoLocalKey;

        // 요청 헤더에 만들기, Authorization 헤더 설정하기
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", apiKey);
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);

        UriComponents uriComponents = UriComponentsBuilder
                .fromHttpUrl(uri)
                .queryParam("query",RoomAddress)
                .build();

        ResponseEntity<String> response = restTemplate.exchange(uriComponents.toString(), HttpMethod.GET, entity, String.class);

        // API Response로부터 body 추출하기
        String body = response.getBody();
        JSONObject json = new JSONObject(body);
        try {
            // body에서 좌표 추출하기
            JSONArray documents = json.getJSONArray("documents");
            String x = documents.getJSONObject(0).getString("x");
            String y = documents.getJSONObject(0).getString("y");
            String city = null;

            JSONObject document = documents.getJSONObject(0);
            if (document.has("road_address")) {
                JSONObject roadAddress = document.getJSONObject("road_address");
                city = roadAddress.getString("region_2depth_name");
            } else if (document.has("address")) {
                JSONObject address = document.getJSONObject("address");
                city = address.getString("region_2depth_name");
            } else {
                city = "Unknown";
            }
            return new Coordinates(x, y, city);
        } catch (Exception e) {
            throw new IllegalArgumentException("주소를 찾을 수 없습니다.");
        }
    }

    @Transactional
    public RoomAddress getCoordinateAndSave(String addressName){

        //주소를 통해 좌표를 조회한 후
        Coordinates coordinates = getCoordinate(addressName);
        RoomAddressSaveDto roomAddressSaveDto= new RoomAddressSaveDto(addressName);
        RoomAddress roomAddress = roomAddressSaveDto.toEntity(coordinates.getX(), coordinates.getY(),coordinates.getCity());

        //DB에 저장하고 RoomAddress 반환
        return roomAddressRepository.save(roomAddress);
    }
}
