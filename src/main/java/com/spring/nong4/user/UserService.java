package com.spring.nong4.user;

import com.spring.nong4.common.EmailService;
import com.spring.nong4.common.MyFileUtils;
import com.spring.nong4.common.MySecurityUtils;
import com.spring.nong4.security.IAuthenticationFacade;
import com.spring.nong4.user.model.UserEntity;
import com.spring.nong4.user.model.UserProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println(param);
        int result = mapper.join(param);

        if(result == 1) {
            String subject = "NONG4 인증 메일 입니다.";
            String txt = String.format("<a href=\"http://localhost:8090/user/auth?email=%s&authCd=%s\">인증하기</a>"
                    , param.getEmail(), authCd);
            System.out.println("txt : " + txt);
            email.sendMimeMessage(param.getEmail(), subject, txt);
            System.out.println("sub : " + subject);
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

    public int profileMod(MultipartFile[] imgArr, UserProfileEntity param) {
        if (imgArr == null) {
            return 0;
        }
        param.setIuser(auth.getLoginUserPk());

        int result = 0;
        if (imgArr != null && imgArr.length > 0) {
            String target = "user/" + param.getIuser();
            for (MultipartFile img : imgArr) {
                String saveFileNm = myFileUtils.transferTo(img, target);
                if (saveFileNm != null) { //이미지 정보 DB에 저장
                    param.setProfileImg(saveFileNm);
                    result = mapper.insUserProfile(param);
                }
            }
        }
        return result;
    }
}