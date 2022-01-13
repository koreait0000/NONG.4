package com.spring.nong4.user;

import com.spring.nong4.common.EmailService;
import com.spring.nong4.common.MyFileUtils;
import com.spring.nong4.common.MySecurityUtils;
import com.spring.nong4.security.IAuthenticationFacade;
import com.spring.nong4.user.model.UserEntity;
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

        if(param.getProvider() == null){
            param.setProvider("nong4");
        }

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

    // 이메일 찾기
    public UserEntity findEmail(UserEntity param) {
        UserEntity dbData = mapper.findEmail(param);

        if(dbData == null) {
            return null;
        }
        return dbData;
    }

    // 비밀번호 찾기
    public int findPw(UserEntity param) {
        UserEntity emailChk = mapper.chkOverlap(param);

        if(!emailChk.getEmail().equals(param.getEmail())) {

            return 0;
        }

        // 난수 생성(임시비밀번호)
        String code = secUtils.getRandomDigit(8);

        // 비밀번호 암호화
        String hashedPw = passwordEncoder.encode(code);
        emailChk.setPw(hashedPw);
        mapper.temporaryPw(emailChk);

        // 유저가 보는 임시비밀번호로 설정 후 해당 이메일로 발송
        emailChk.setPw(code);
        String subject = "NONG4 임시비밀번호 메일 입니다.";

        String txt = String.format("회원님의 임시 비밀번호는 : "+"<a href=\"http://localhost:8090/user/login\" onclick=\"clipboard(\"emailChk.getPw()\"); return false;\">"+emailChk.getPw()+"</a>"
                , param.getEmail(),email);
        email.sendMimeMessage(param.getEmail(), subject, txt);

        return 1;
    }

    // 현재 비밀번호와 입력된 비밀번호 확인
    public int currentPw(UserEntity param, String currentInput) {
        String decoderPw;
        int result = 0;
        param.setIuser(auth.getLoginUserPk());
        UserEntity currentPw = mapper.currentPw(param,currentInput);
        param.setPw(currentInput); // 입력된 비밀번호를 param에 넣음
        decoderPw = param.getPw().replaceAll("\"",""); // currentInput이 json형태로 넘어오기때문에 ""을 제거

        passwordEncoder.matches(param.getPw(),currentPw.getPw()); // 입력받은 비밀번호, 이미 암호화된 비밀번호를 비교

        if(!passwordEncoder.matches(decoderPw,currentPw.getPw())) {
            return result;
        }
        return 1;
    }

    public int changePw1(UserEntity param, String changeInput) {
        String decoderPw;
        param.setIuser(auth.getLoginUserPk());
        decoderPw = changeInput.replaceAll("\"","");
        String hashedPw = passwordEncoder.encode(decoderPw);
        param.setPw(hashedPw);

        int result = 0;
        result = mapper.changePw1(param);

        return result;
    }

    public int profileMod(MultipartFile[] imgArr, UserEntity param, String userNick) {
        param.setIuser(auth.getLoginUserPk());
        int result = 0;

        if(imgArr == null || !userNick.equals("")) {
            UserEntity param2 = mapper.selUserProfile(param);
            param.setProfileImg(param2.getProfileImg());
            result = mapper.updUserProfile(param,userNick);
        }

        if (imgArr != null && imgArr.length > 0) {
            String target = "profileImg/" + param.getIuser();
            UserEntity param2 = mapper.selUserProfile(param);
            userNick = param2.getUserNick();
            for (MultipartFile img : imgArr) {
                String saveFileNm = myFileUtils.transferTo(img, target);
                if (saveFileNm != null) { //이미지 정보 DB에 저장
                    param.setProfileImg(saveFileNm);
                    result = mapper.updUserProfile(param,userNick);
                }
            }
        }
        return result;
    }
}