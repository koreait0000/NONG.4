package com.spring.nong4.board;

import com.spring.nong4.board.model.*;
import com.spring.nong4.cmt.model.BoardCmtDomain;
import com.spring.nong4.security.IAuthenticationFacade;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired private BoardService service;
    @Autowired private IAuthenticationFacade auth;

    @GetMapping("/home")
    public String home() {
        System.out.println("로그인 시도중이다");
        return "board/home";
    }


    @GetMapping("/community")
    public String community() { return "board/community";}

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
    public String friendWrite(@RequestParam(value="provider") String provider, BoardDomain param, MultipartFile[] imgArr,Model model ) {
        param.setIuser(auth.getLoginUserPk());
        model.addAllAttributes(service.friendWrite(param,imgArr));
        return "redirect:/board/friendBoardList?provider=" + provider;
    }

    @GetMapping("/boardUpdate")
    public String boardUpdate() {
        return "board/boardUpdate";
    }

    @ResponseBody
    @RequestMapping(value = "/boardUpdate", method = RequestMethod.PUT)
    public Map<String,Object> boardUpdate(@RequestBody BoardDomain param, Model model) {
        model.addAllAttributes(service.friendUpdate(param));
        System.out.println("title : "+param.getTitle());
        return service.friendUpdate(param);
    }
    @ResponseBody
    @RequestMapping(value = "/boardDelete",method = RequestMethod.DELETE)
    public Map<String,Object> boardDelete(@RequestBody BoardDomain param, Model model) {
        model.addAllAttributes(service.friendDelete(param));
        System.out.println("title! : "+service.friendDelete(param));
        return service.friendDelete(param);
    }

    @GetMapping("/friendBoardList")
    public String friendList(BoardDomain param, SearchCriteria scri, Model model) {
        model.addAllAttributes(service.friendList(param,scri));
        return "board/friendBoardList";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(HttpServletResponse response, @CookieValue (value="hitCount1", required = false) Cookie cookie, BoardDomain param, BoardImgEntity imgParam, Model model) {
//        cookie = new Cookie("hit",null);
//        cookie.setComment("게시글 조회 확인중");
//        System.out.println("조회 작업중 : " + cookie.getComment());
//        cookie.setMaxAge(60*60*24);
//        response.addCookie(cookie);
//        System.out.println("cookie : "+cookie);
        System.out.println("con Hit : "+param.getHitCount());

        model.addAllAttributes(service.boardDetail(param, imgParam));

        return "board/boardDetail";
    }

    @ResponseBody
    @RequestMapping(value = "/insCmt", method = RequestMethod.POST)
    public Map<String, Integer> insCmt(@RequestBody BoardCmtDomain param){
        int result = service.insCmt(param);

        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);

        return data;
    }

    @ResponseBody
    @RequestMapping("/cmt/{iboard}")
    public List<BoardCmtDomain> cmtList(@PathVariable("iboard") int iboard){
        BoardCmtDomain param = new BoardCmtDomain();
        param.setIboard(iboard);
        return service.cmtList(param);
    }

}
