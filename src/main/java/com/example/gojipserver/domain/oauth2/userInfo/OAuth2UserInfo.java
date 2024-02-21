package com.example.gojipserver.global.oauth2.userInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public abstract class OAuth2UserInfo {

    protected Map<String, Object> attributes;
    public abstract String getEmail();
    public abstract String getName();
}
