package com.spring.nong4.cmt.model;

import lombok.Data;

@Data
public class BoardCmtEntity {
    private int icmt; //댓글번호
    private int iuser; //회원번호
    private int iboard; //게시물 번호
    private String cmt; // 댓글 내용
    private String regdt; //댓글 작성 날짜
}
