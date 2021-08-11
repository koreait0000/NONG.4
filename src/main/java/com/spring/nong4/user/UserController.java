package com.spring.nong4.user;

import com.spring.nong4.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/login")
    public String login(){ return "user/login"; }

    @GetMapping("/join")
    public String join(){
        return "user/join";
    }

    @PostMapping("/join")
    public String join(UserEntity param){
        service.join(param);
        return "redirect:login?needEmail=1";
    }

    @GetMapping("/auth")
    public String auth(UserEntity param) {
        int result = service.auth(param);
        return "redirect:login?auth=" + result;
    }

    // 프로필
    @GetMapping("/profile")
    public String profile(UserEntity param, Model model) {
        model.addAllAttributes(service.selUserProfile(param));
        return "user/profile";
    }

    @PostMapping("/profile")
    public String profile(MultipartFile[] imgArr, UserEntity param) {
        System.out.println("프로필 확인용");
        service.profileMod(imgArr, param);
        return "redirect:profile";
    }
}
