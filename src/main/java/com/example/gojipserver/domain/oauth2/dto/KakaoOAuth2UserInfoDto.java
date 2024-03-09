package com.example.gojipserver.domain.oauth2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;


@Getter
@AllArgsConstructor
public class KakaoOAuth2UserInfoDto {

    private Map<String, Object> attributes;
    private Long id;
    private String email;
    private String nickname;
    private String profileImage;

    public KakaoOAuth2UserInfoDto(Map<String, Object> attributes) {
        this.id = (Long) attributes.get("id");
        this.email = (String) ((Map<String, Object>) attributes.get("kakao_account")).get("email");
        this.nickname = (String) ((Map<String, Object>) ((Map<String, Object>) attributes.get("kakao_account")).get("profile")).get("nickname");
        this.profileImage = (String) ((Map<String, Object>) ((Map<String, Object>) attributes.get("kakao_account")).get("profile")).get("profile_image_url");
    }

}
