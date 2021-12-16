package com.spring.nong4.user;

import com.spring.nong4.board.BoardService;
import com.spring.nong4.board.model.BoardDomain;
import com.spring.nong4.board.model.SearchCriteria;
import com.spring.nong4.security.IAuthenticationFacade;
import com.spring.nong4.user.model.UserEntity;
import com.spring.nong4.user.model.UserProfileEntity;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.transform.Result;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private BoardService boardService;

    @Autowired private IAuthenticationFacade auth;

    @GetMapping("/login")
    public String login(){ return "user/login"; }

    @GetMapping("/join")
    public String join(){
        return "user/join";
    }

    @GetMapping("/findUser")
    public String findUser() { return "user/findUser"; }

    @ResponseBody
    @PostMapping("/join")
    public Map<String,Object> join(@RequestBody UserEntity param){
        Map<String,Object> returnValue = new HashMap<>();
        System.out.println("join : " + param.getUserNick());
        returnValue.put("result", service.join(param));
        return returnValue;
    }

    // 이메일,닉네임,휴대전화번호 중복확인 체크
    @ResponseBody
    @PostMapping("/chkOverlap")
    public Map<String,Object> chkOverlap(@RequestBody UserEntity param) {
        Map<String,Object> returnValue = new HashMap<>();
        returnValue.put("result", service.chkOverlap(param));
        System.out.println("Eamil 확인 : "+param.getEmail());
        return returnValue;
    }

    // 이메일 찾기
    @ResponseBody
    @PostMapping("/findEmail")
    public Map<String, Object> findEmail(@RequestBody UserEntity param) {
        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("result",service.findEmail(param));
        return returnValue;
    }

    // 비밀번호 찾기
    @ResponseBody
    @PostMapping("/findPw")
    public Map<String, Object> findPw(@RequestBody UserEntity param) {
        Map<String, Object> returnValue = new HashMap<>();
        returnValue.put("result",service.findPw(param));
        return returnValue;
    }

    @GetMapping("/auth")
    public String auth(UserEntity param) {
        int result = service.auth(param);
        return "redirect:login?auth=" + result;
    }

    // 프로필
    @GetMapping("/profile")
    public String profile(UserEntity param, BoardDomain boardParam, SearchCriteria scri, Model model) {
        boardParam.setIsFav(1);
        model.addAllAttributes(service.selUserProfile(param));
        model.addAllAttributes(boardService.mainBoardList(boardParam,scri));
        return "user/profile";
    }

    // 비밀번호 변경
    @ResponseBody
    @PostMapping("/profile")
    public Map<String, Object> profile(@RequestBody String currentInput) {
        Map<String, Object> returnValue = new HashMap<>();
        UserEntity param = new UserEntity();

        returnValue.put("result",service.currentPw(param,currentInput));
        return returnValue;
    }

    @ResponseBody
    @PutMapping("/profile")
    public Map<String, Object> profile(MultipartFile[] imgArr, UserEntity param, @RequestParam("nick") String userNick) {
        Map<String, Object> map = new HashMap<>();
        param.setUserNick(userNick);
        map.put("result", service.profileMod(imgArr,param,userNick));
        System.out.println("userNick : " + param.getUserNick());
        System.out.println("userNickRequest : " + userNick);
        System.out.println("Img : " + auth.getLoginUser().getProfileImg());
        return map;
    }

    @ResponseBody
    @PostMapping("/changePw")
    public Map<String, Object> changePw(@RequestBody String changeInput) {
        Map<String, Object> returnValue = new HashMap<>();
        UserEntity param = new UserEntity();

        returnValue.put("result",service.currentPw(param,changeInput));
        returnValue.put("result",service.changePw1(param,changeInput));
        return returnValue;
    }
}