package com.spring.nong4.security;

public enum SocialType {
    FACEBOOK("facebook"),
    GOOGLE("google"),
    KAKAO("kakao"),
    NAVER("naver");

    private String name;
    private final String ROLE_PREFIX = "ROLE_";

    SocialType(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
    public String getRoleType() {
        return ROLE_PREFIX + name.toUpperCase();
    }
    public boolean isEquals(String authority) {
        return this.getRoleType().equals(authority);
    }
}
