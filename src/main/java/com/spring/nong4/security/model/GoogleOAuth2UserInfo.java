package com.spring.nong4.security.model;

import com.spring.nong4.security.SocialType;

import java.util.Map;

public class GoogleOAuth2UserInfo extends com.spring.nong4.security.model.OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        return (String) attributes.get("picture");
    }

    @Override
    public String getProvider() {
        return SocialType.GOOGLE.getValue();
    }
}