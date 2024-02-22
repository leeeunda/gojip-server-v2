package com.example.gojipserver.domain.oauth2.userInfo;

import com.example.gojipserver.domain.oauth2.userInfo.KakaoOAuth2UserInfo;
import com.example.gojipserver.domain.oauth2.entity.AuthProvider;
import com.example.gojipserver.domain.oauth2.userInfo.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(AuthProvider authProvider, Map<String, Object> attributes) {
        switch (authProvider) {
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);

            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
