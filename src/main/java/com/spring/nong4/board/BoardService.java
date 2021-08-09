package com.spring.nong4.board;

import com.spring.nong4.board.model.*;
import com.spring.nong4.cmt.BoardCmtMapper;
import com.spring.nong4.cmt.model.BoardCmtDomain;
import com.spring.nong4.common.MyFileUtils;
import com.spring.nong4.security.IAuthenticationFacade;
import com.spring.nong4.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired private BoardMapper mapper;
    @Autowired private BoardCmtMapper cmtMapper;
    @Autowired private IAuthenticationFacade auth;
    @Autowired private MyFileUtils MyFileUtils;

    // 이메일 인증 처리
    public int auth(UserEntity param) {
        return mapper.auth(param);
    }

//    if(chkTitle == "" && chkCtnt == "" && chkTitle == null && chkCtnt == null) {return 0;}


    public Map<String, Object> boardWrite(BoardDomain param, MultipartFile[] imgArr) {
        Map<String, Object> map = new HashMap<>();

        int write = mapper.boardWrite(param);
        map.put("data1",write);


        if(imgArr == null && param.getTitle() == null && param.getCtnt() == null) { return null; }

        // 파일 업로드
        if(param.getIboard() > 0 && imgArr != null && imgArr.length > 0) {
            BoardImgEntity paramImg = new BoardImgEntity();
            paramImg.setIboard(param.getIboard());

            String target = "board/" + param.getIboard();
            for(MultipartFile img : imgArr) {
                String saveFileNm = MyFileUtils.transferTo(img , target);
                System.out.println("NM : "+saveFileNm);
                if(saveFileNm != null) {
                    paramImg.setImg(saveFileNm);
                    System.out.println("IMG"+paramImg);
                    map.put("data",mapper.boardWriteImg(paramImg));
                }
            }
        }
        return map;
    }

    public List<BoardDomain> newsList(BoardDomain param, SearchCriteria scri){
        return mapper.mainBoardList(param, scri);
    }
    public Map<String, Object> boardUpdate(BoardDomain param) {
        param.setIuser(auth.getLoginUserPk());
        Map<String, Object> map = new HashMap<>();
        map.put("data",mapper.boardUpdate(param));
        return map;
    }

    public Map<String,Object> boardDelete(BoardDomain param) {
        param.setIuser(auth.getLoginUserPk());
        Map<String, Object> map = new HashMap<>();
        map.put("data",mapper.boardDelete(param));
        return map;
    }

    public Map<String,Object> mainBoardList(BoardDomain param, SearchCriteria scri) {
        param.setIuser(auth.getLoginUserPk());
        Map<String,Object> map = new HashMap<>();

        int total = mapper.countBoardList(param);

        PageMaker pageMaker = new PageMaker();
        pageMaker.setCri(scri);
        pageMaker.setTotalCount(total);

        map.put("pageMaker",pageMaker);
        map.put("list",mapper.mainBoardList(param,scri));

        return map;
    }

    public Map<String,Object> boardDetail(BoardDomain param, BoardImgEntity imgParam) {
        param.setIuser(auth.getLoginUserPk());
        Map<String,Object> map = new HashMap<>();
        map.put("detail", mapper.boardDetail(param));
        map.put("img", mapper.selBoardImgList(imgParam));
        return map;
    }
    public Map<String,Object> boardDetailHit(BoardDomain param) {
        param.setIuser(auth.getLoginUserPk());
        Map<String,Object> map = new HashMap<>();
        map.put("detail", mapper.detailHitCount(param));
        return map;
    }

    public int insCmt(BoardCmtDomain param){
        param.setIuser(auth.getLoginUserPk());
        return cmtMapper.insCmt(param);
    }

    public List<BoardCmtDomain> cmtList(BoardCmtDomain param){
        return cmtMapper.cmtList(param);
    }

    public int updCmt(BoardCmtDomain param) {
        param.setIuser(auth.getLoginUserPk());
        return cmtMapper.updCmt(param);
    }

    public int delCmt(BoardCmtDomain param){
        param.setIuser(auth.getLoginUserPk());
        return cmtMapper.delCmt(param);
    }
}
