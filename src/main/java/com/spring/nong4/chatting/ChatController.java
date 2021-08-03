package com.spring.nong4.chatting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ChatController {

    @GetMapping("/chat")
    public ModelAndView chat() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("user/chat");
        return mv;
    }
}