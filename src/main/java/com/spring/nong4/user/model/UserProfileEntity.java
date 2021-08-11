package com.spring.nong4.user.model;

import lombok.Data;

@Data
public class UserProfileEntity {
    private int iprofile;      // 사용자 프로필번호
    private int iuser;         // 사용자 pk
    private String profileImg; // 사용자 프로필사진
    private String regdt;      // 사용자 프로필사진 등록 일자
}
