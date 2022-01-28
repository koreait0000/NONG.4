package com.spring.nong4.board;

import com.spring.nong4.api.ApiService;
import com.spring.nong4.api.model.apiVideoDomain;
import com.spring.nong4.api.model.monthFarmTechDomain;
import com.spring.nong4.board.model.*;
import com.spring.nong4.cmt.model.BoardCmtDomain;
import com.spring.nong4.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired private BoardService service;
    @Autowired private IAuthenticationFacade auth;
    @Autowired private HttpServletResponse response;
    @Autowired private ApiService apiService;

    @GetMapping("/home")
    public String home() {
        Cookie cookie = new Cookie("hit",null);
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);

        return "board/home";
    }

    @GetMapping("/community")
    public String community() {
        return "board/community";
    }

    @ResponseBody
    @RequestMapping("/community/{currentPage}")
    public List<BoardDomain> community(BoardDomain param, SearchCriteria scri, @PathVariable("currentPage") int currentPage) {

        scri.setPage(currentPage);
        scri.setPerPageNum(5);

        return service.newsList(param,scri);
    }

    @GetMapping("/boardWrite")
    public String boardWrite() { return "board/boardWrite"; }

    @PostMapping("/boardWrite")
    public String boardWrite(BoardDomain param, MultipartFile[] imgArr) throws IOException {
        int result = 0;
        param.setIuser(auth.getLoginUserPk());
        result = service.boardWrite(param,imgArr);

        if(result == 3) {
            response.setContentType("text/html; charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('제목을 작성해주세요');</script>");
            out.flush();
            return null;
        } else if(result == 4) {
            response.setContentType("text/html; charset=euc-kr");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('내용을 작성해주세요');</script>");
            out.flush();
            return null;
        }
        return "redirect:/board/mainBoard?provider=" + param.getProvider();
    }

    @GetMapping("/boardUpdate")
    public String boardUpdate() {
        return "board/boardUpdate";
    }

    @ResponseBody
    @RequestMapping(value = "/boardUpdate", method = RequestMethod.PUT)
    public Map<String,Object> boardUpdate(@RequestBody BoardDomain param, Model model) {
        model.addAllAttributes(service.boardUpdate(param));
        return service.boardUpdate(param);
    }

    @ResponseBody
    @RequestMapping(value = "/boardDelete",method = RequestMethod.DELETE)
    public Map<String,Object> boardDelete(@RequestBody BoardDomain param, Model model) {
        model.addAllAttributes(service.boardDelete(param));
        return service.boardDelete(param);
    }

    @GetMapping("/mainBoard")
    public String mainBoardList(BoardDomain param, SearchCriteria scri, Model model ) {
        model.addAllAttributes(service.mainBoardList(param,scri));
        return "board/mainBoard";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(@CookieValue(name ="hit", required = false) String cookie, BoardDomain param, BoardImgEntity imgParam, Model model) {
        // home에서 생성된 쿠키를 @CookieValue를 사용하여 detail에서 전달 받음
        if(!(cookie.contains(String.valueOf(param.getIboard())))) { // 쿠키값에 iboard값이 포함이 되어 있지 않다면
            cookie += param.getIboard() + "/"; // 쿠키에 iboard값 마다마다 누적
            model.addAllAttributes(service.boardDetailHit(param)); // 조회수 증가
        }
        response.addCookie(new Cookie("hit",cookie));
        model.addAllAttributes(service.boardDetail(param, imgParam));
        return "board/boardDetail";
    }

    //좋아요
    @ResponseBody
    @GetMapping("/fav")
    public int favProc(BoardDomain param){
        return service.favProc(param);
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

    //댓글 수정
    @ResponseBody
    @RequestMapping(value = "/updCmt", method = RequestMethod.PUT)
    public Map<String, Integer> updCmt(@RequestBody BoardCmtDomain param) {
        int result = service.updCmt(param);

        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);

        return data;
    }

    //댓글 삭제
    @ResponseBody
    @RequestMapping(value = "/delCmt/{icmt}", method = RequestMethod.DELETE)
    public Map<String, Integer> delCmt(@PathVariable("icmt") int icmt) {
        BoardCmtDomain param = new BoardCmtDomain();
        param.setIcmt(icmt);

        int result = service.delCmt(param);

        Map<String, Integer> data = new HashMap<>();
        data.put("result", result);

        return data;
    }

    // 통합 검색
    @GetMapping("/totalSearch")
    public String totalSearch(String keyword, Model model) {

//        System.out.println("VIDEO : " + apiService.apiVideo(apiVideoDomain, scri));
//        model.addAllAttributes(apiService.apiVideo(apiVideoDomain, scri));
//        model.addAllAttributes(service.totalSearch(apiVideoDomain, farmTechDomain, param, scri));
        model.addAttribute("keyword", keyword);
        return "board/totalSearch";
    }

    @ResponseBody
    @RequestMapping("/totalSearch/{currentPage}/{keyword}")
    public Map<String, Object> searchPaging(BoardDomain param, SearchCriteria scri, @PathVariable("currentPage") int currentPage, @PathVariable("keyword") String keyword) {
        Map<String, Object> map = new HashMap<>();
        scri.setPage(currentPage);

        scri.setPerPageNum(5);
        scri.setKeyword(keyword);
        System.out.println("SCRI : " + service.searchPaging(param,scri));

        return service.searchPaging(param,scri);
    }
    @ResponseBody
    @RequestMapping("/totalSearchVideo/{currentPage}/{keyword}")
    public Map<String, Object> searchVideoPaging(apiVideoDomain apiVideoDomain, SearchCriteria scri, @PathVariable("currentPage") int currentPage, @PathVariable("keyword") String keyword) {
        final String VIEW_ROWS = "5";
        apiVideoDomain.setPageNo(String.valueOf(currentPage));
        apiVideoDomain.setNumOfRows(VIEW_ROWS);
        apiVideoDomain.setSText(keyword);
        apiVideoDomain.setSType("sSj");
        scri.setPerPageNum(5);
        scri.setPage(currentPage);

        System.out.println("CURRENT_PAGE : " + currentPage);

        return apiService.apiVideo(apiVideoDomain, scri);
    }
    @ResponseBody
    @RequestMapping("/totalSearchMonth/{currentPage}/{keyword}")
    public Map<String, Object> searchMonthPaging(monthFarmTechDomain farmTechDomain, SearchCriteria scri, @PathVariable("currentPage") int currentPage, @PathVariable("keyword") String keyword) {
        farmTechDomain.setSrchStr(keyword);
        farmTechDomain.setPageNo(String.valueOf(currentPage));
        scri.setPage(currentPage);

        System.out.println("AJAX_DOMAIN : " + farmTechDomain);
        System.out.println("CURRENT_PAGE : " + currentPage);
        System.out.println("KEYWORD : " + keyword);
        Map<String, Object> map = new HashMap();
        map.put("img", apiService.monthFarmTechDtlImg(farmTechDomain, scri));
        map.put("farmTech", apiService.monthFarmTech(farmTechDomain, scri));

        return map;
    }

//-----------------------------서비스------------------------------
    @GetMapping("/service")
    public String service() {
        return "board/service";
    }
}
