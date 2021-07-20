package com.spring.nong4.board;

import com.spring.nong4.board.model.BoardDomain;
import com.spring.nong4.board.model.BoardEntity;
import com.spring.nong4.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired private BoardService service;
    @Autowired private IAuthenticationFacade auth;

    @GetMapping("/home")
    public String home() { return "board/home";}

    @GetMapping("/freeBoard")
    public String freeBoard() { return "board/freeBoard"; }

    @ResponseBody
    @PostMapping("/freeBoard")
    public Map<String, Object> freeBoard(@RequestBody BoardEntity param) {
        Map<String, Object> res = new HashMap<>();
        param.setIuser(auth.getLoginUserPk());
        int result = service.freeBoard(param);
        if(result == 1) {
            res.put("result",result);
        } else {
            res.put("result",result);
        }
        return res;
    }

    @GetMapping("/freeBoardList")
    public String freeBoardList(Model model) {
        model.addAttribute("data", service.freeBoardList());
        return "board/freeBoardList";
    }

    @GetMapping("/friendBoard")
    public String friendWrite() { return "board/friendBoard"; }

    @PostMapping("/friendBoard")
    public String friendWrite(@RequestParam(value="provider") String provider, BoardDomain param) {
        System.out.println("provider : " + provider);
        param.setIuser(auth.getLoginUserPk());
        param.setProvider(param.getProvider());
        System.out.println("provider : "+param.getProvider());
        service.friendWrite(param);
        return "redirect:/board/friendBoard";
    }

    @GetMapping("/friendBoardList")
    public String friendList(BoardDomain param, Model model) {
        model.addAttribute("data",service.friendList(param));
        return "board/friendBoardList";
    }

}
