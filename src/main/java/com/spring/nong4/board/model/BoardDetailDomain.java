package com.spring.nong4.board.model;

import com.spring.nong4.cmt.model.BoardCmtDomain;
import lombok.Data;

import java.util.List;

@Data
public class BoardDetailDomain extends BoardEntity {
    private String userNick;
    private List<BoardImgEntity> imgList;
    private List<BoardCmtDomain> cmtList;

}
