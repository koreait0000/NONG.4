package com.spring.nong4.user;

import com.spring.nong4.user.model.UserEntity;
import com.spring.nong4.user.model.UserProfileEntity;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    // 회원가입
    int join(UserEntity param);

    int auth(UserEntity param);

    UserEntity selUser(UserEntity param);

    // 유저프로필 조회
    UserEntity selUserProfile(UserEntity param);

    // 유저 프로필 등록
    int updUserProfile(UserEntity param, String userNick);

    // 이메일&닉네임&휴대번호 중복 체크
    UserEntity chkOverlap(UserEntity param);

    // 이메일 찾기
    UserEntity findEmail(UserEntity param);

    // 비밀번호 찾기
    int temporaryPw(UserEntity param);

    // 현재비밀번호와 입력된 비밀번호 체크
    UserEntity currentPw(UserEntity param, @Param("pw")String currentInput);

    // 비밀번호 변경
    int changePw1(UserEntity param);

    // 중복 닉네임 체크
    UserEntity nickValid(UserEntity param, @Param("nick")String nickValid);
}
