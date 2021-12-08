package com.spring.nong4.user;

import com.spring.nong4.common.EmailService;
import com.spring.nong4.common.MyFileUtils;
import com.spring.nong4.common.MySecurityUtils;
import com.spring.nong4.security.IAuthenticationFacade;
import com.spring.nong4.user.model.UserEntity;
import com.spring.nong4.user.model.UserProfileEntity;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService {
    @Autowired private EmailService email;
    @Autowired private MySecurityUtils secUtils;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private IAuthenticationFacade auth;
    @Autowired private UserMapper mapper;
    @Autowired private MyFileUtils myFileUtils;

    public int join(UserEntity param) {
        String authCd = secUtils.getRandomDigit(5);

        //비밀번호 암호화
        String hashedPw = passwordEncoder.encode(param.getPw());
        param.setPw(hashedPw);
        param.setAuthCd(authCd);
        int result = mapper.join(param);

        if(result == 1) {
            String subject = "NONG4 인증 메일 입니다.";
            String txt = String.format("<a href=\"http://localhost:8090/user/auth?email=%s&authCd=%s\">인증하기</a>"
                    , param.getEmail(), authCd);
            email.sendMimeMessage(param.getEmail(), subject, txt);
        }
        return result;
    }
    //이메일 인증 처리
    public int auth(UserEntity param) {
        return mapper.auth(param);
    }

    public Map<String, Object> selUserProfile(UserEntity param) {
        param.setIuser(auth.getLoginUserPk());
        Map<String,Object> map = new HashMap<>();

        map.put("profile",mapper.selUserProfile(param));

        return map;
    }

    // 이메일,닉네임,휴대전화번호 중복확인 체크(중복이면 return 0, 중복이 아니라면 return 1)
    public int chkOverlap(UserEntity param) {
        UserEntity chkOverlap = mapper.chkOverlap(param);
        int result = 0;

        if(chkOverlap == null) { // email이 null (중복이 아닐때)
            result = 1;

            return result;
        }
            return 0;
        }

    public int profileMod(MultipartFile[] imgArr, UserEntity param, String userNick) {
//        if (imgArr == null) {
//            return 0;
//        }
        param.setIuser(auth.getLoginUserPk());
//        System.out.println("NickLeng : " + userNick.length());
        int result = 0;
//        if(userNick != null && !userNick.equals(auth.getLoginUser().getUserNick())) {
//            result = mapper.updUserProfile(param, userNick);
//        }
        System.out.println("imgArr :" + imgArr);
        System.out.println("auth :" + auth.getLoginUser().getProfileImg());
        System.out.println("getProfileImg[1] : " + param.getProfileImg());
        if(imgArr == null ) {
//            param.setProfileImg(param.getProfileImg());
//            System.out.println("getImgAuth : " + param.getProfileImg());
//            result = mapper.updUserProfile(param,userNick);
            UserEntity param2 = mapper.selUserProfile(param);
            param.setProfileImg(param2.getProfileImg());
            result = mapper.updUserProfile(param,userNick);
        }

        if(userNick != null && userNick != ""){
            UserEntity param2 = mapper.selUserProfile(param);
            userNick = param2.getUserNick();
            result = mapper.updUserProfile(param,userNick);
        }

        if (imgArr != null && imgArr.length > 0) {
//            || userNick != null && userNick.length() > 0
            String target = "profileImg/" + param.getIuser();
            for (MultipartFile img : imgArr) {
                String saveFileNm = myFileUtils.transferTo(img, target);
                System.out.println("saveFileNm : " + saveFileNm);
                if (saveFileNm != null) { //이미지 정보 DB에 저장
                    param.setProfileImg(saveFileNm);
                    System.out.println("getProfileImg[2] : " + param.getProfileImg());
                    result = mapper.updUserProfile(param,userNick);
                }
            }
        }
        return result;
    }
}