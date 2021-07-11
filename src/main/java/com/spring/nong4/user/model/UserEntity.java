package com.spring.nong4.user.model;

import lombok.Data;

@Data
public class UserEntity {
    private int iuser;
    private String email;
    private String pw;
    private String nm;
    private String nick;
    private String tel;
    private String authCd;
    private String regdt;
}
