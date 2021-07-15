package com.spring.nong4.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService service;

    @GetMapping("/home")
    public String home() { return "board/home";}

    @GetMapping("/freeBoard")
    public String freeBoard() { return "board/freeBoard";}
}
