package com.spring.nong4.board;

import com.spring.nong4.board.model.BoardDomain;
import com.spring.nong4.board.model.BoardEntity;
import com.spring.nong4.board.model.Criteria;
import com.spring.nong4.board.model.SearchCriteria;
import com.spring.nong4.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {
    int auth(UserEntity param);
    int freeBoard(BoardEntity param);
    List<BoardEntity> freeBoardList();
    int friendWrite(BoardDomain param);

    List<BoardDomain> friendList(@Param("param") BoardDomain param, @Param("scri") SearchCriteria scri);

    int countBoardList(BoardDomain param);
}
