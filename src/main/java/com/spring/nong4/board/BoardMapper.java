package com.spring.nong4.board;

import com.spring.nong4.board.model.*;
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
    int friendWriteImg(BoardImgEntity param);
    int friendUpdate(BoardDomain param);
    int friendDelete(BoardDomain param);
    int detailHitCount(BoardDomain param);

    List<BoardDomain> friendList(@Param("param") BoardDomain param, @Param("scri") SearchCriteria scri);

    int countBoardList(BoardDomain param);

    BoardDomain boardDetail(BoardDomain param);

    List<BoardImgEntity> selBoardImgList(BoardImgEntity param);
}
