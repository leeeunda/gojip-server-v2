package com.example.gojipserver.domain.oauth2.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class KakaoUserInfoDto {

    @Getter
    @NoArgsConstructor
    public static class KakaoUserInfoResponse {
        private Long id;
        private String connected_at;
        private KakaoAccount kakao_account;
        private KakaoProperties properties;
    }

    @Getter
    @NoArgsConstructor
    public static class KakaoAccount {

        private Boolean profile_nickname_needs_agreement;
        private KakaoProfile profile;
        private String email;
    }

    @Getter
    @NoArgsConstructor
    public static class KakaoProperties {

        private String nickname;
        private String profile_image;
        private String thumbnail_image;
    }

    @Getter
    @NoArgsConstructor
    public static class KakaoProfile {

        private String nickname;
    }
}
