package com.spring.nong4.user;

import com.spring.nong4.user.model.UserEntity;
import com.spring.nong4.user.model.UserProfileEntity;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int join(UserEntity param);
    int auth(UserEntity param);
    UserEntity selUser(UserEntity param);
    int insUserProfile(UserProfileEntity param);
    UserEntity selUserProfile(UserEntity param);
    int updUserProfile(UserEntity param, String userNick);
    int updUserProfileNick(UserEntity param);
}
