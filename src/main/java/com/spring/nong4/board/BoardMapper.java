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
    int boardWrite(BoardDomain param);
    int boardWriteImg(BoardImgEntity param);
    int boardUpdate(BoardDomain param);
    int boardDelete(BoardDomain param);
    int detailHitCount(BoardDomain param);

    List<BoardDomain> mainBoardList(@Param("param") BoardDomain param, @Param("scri") SearchCriteria scri);

    int countBoardList(BoardDomain param);

    BoardDomain boardDetail(BoardDomain param);

    List<BoardImgEntity> selBoardImgList(BoardImgEntity param);
}
