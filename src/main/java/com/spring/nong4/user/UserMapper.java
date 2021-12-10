package com.spring.nong4.user;

import com.spring.nong4.user.model.UserEntity;
import com.spring.nong4.user.model.UserProfileEntity;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 회원가입
    int join(UserEntity param);

    int auth(UserEntity param);

    UserEntity selUser(UserEntity param);

    int insUserProfile(UserProfileEntity param);

    // 유저프로필 조회
    UserEntity selUserProfile(UserEntity param);

    // 유저 프로필 등록
    int updUserProfile(UserEntity param, String userNick);

    int updUserProfileNick(UserEntity param);

    // 이메일&닉네임&휴대번호 중복 체크
    UserEntity chkOverlap(UserEntity param);

    // 이메일 찾기
    UserEntity findEmail(UserEntity param);

    // 비밀번호 찾기
    int changePw(UserEntity param);
}
