package com.spring.nong4.board.model;

import lombok.Data;

@Data
public class BoardEntity {
    private int iboard;      // 게시물 번호
    private int iuser;       // 회원번호
    private String title;    // 게시물 제목
    private String ctnt;     // 게시물 내용
    private String regdt;    // 게시물 작성 날짜
    private String provider; // 게시물 성격 분리
    private String delYN;    // 게시물 삭제 유무

}
