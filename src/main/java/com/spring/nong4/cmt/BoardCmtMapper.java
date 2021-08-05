package com.spring.nong4.cmt;

import com.spring.nong4.cmt.model.BoardCmtDomain;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardCmtMapper {
    int insCmt(BoardCmtDomain Param);
    List<BoardCmtDomain> cmtList(BoardCmtDomain param);
    int updCmt(BoardCmtDomain param);
    int delCmt(BoardCmtDomain param);
}
