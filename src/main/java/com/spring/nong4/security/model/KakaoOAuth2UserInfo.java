package com.spring.nong4.security.model;

import com.spring.nong4.security.SocialType;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {
    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        int id = (int)attributes.get("id");
        return Integer.toString(id);
    }

    @Override
    public String getName() {
        return (String) attributes.get("nickname");
    }

    @Override
    public String getEmail() { //카카오는 이메일 정보 제공 X
        return "";
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("profile_image");
    }

    @Override
    public String getProvider() {
        return SocialType.KAKAO.getValue();
    }
}
