package com.spring.nong4.board.model;

import lombok.Data;

@Data
public class BoardEntity {
    private int iboard; //게시물 번호
    private int iuser; //사용자 pk
    private String title; //게시물 제목
    private String ctnt; //게시물 내용
    private String regdt; //게시물 작성 시간
}
