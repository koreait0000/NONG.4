package com.spring.nong4.board;

import com.spring.nong4.board.model.BoardDomain;
import com.spring.nong4.board.model.BoardEntity;
import com.spring.nong4.security.IAuthenticationFacade;
import com.spring.nong4.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired private BoardMapper mapper;
    @Autowired private IAuthenticationFacade auth;

    // 이메일 인증 처리
    public int auth(UserEntity param) {
        return mapper.auth(param);
    }

    public int freeBoard(BoardEntity param) {
        String chkTitle = param.getTitle();
        String chkCtnt  = param.getCtnt();

        System.out.println("ttitle : " + param.getTitle());
        if(chkTitle == "" && chkCtnt == "" && chkTitle == null && chkCtnt == null) {
            return 0;
        }
        return mapper.freeBoard(param);
    }

    public List<BoardEntity> freeBoardList() {
        return mapper.freeBoardList();
    }

    public int friendWrite(BoardDomain param) {
        return mapper.friendWrite(param);
    }

    public List<BoardDomain> friendList(BoardDomain param) {
        param.setIuser(auth.getLoginUserPk());
        return mapper.friendList(param);
    }
}
