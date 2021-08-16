package com.spring.nong4.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    private int iuser;         // 사용자 pk
    private String email;      // 사용자 이메일
    private String provider;   // 사용자 로그인(소셜)
    private String pw;         // 사용자 비밀번호
    private String nm;         // 사용자 이름
    private String userNick;   // 사용자 닉네임
    private String tel;        // 사용자 번호
    private String authCd;     // 인증번호
    private String profileImg; // 프로필사진
    private String regdt;      // 가입 날짜
}
