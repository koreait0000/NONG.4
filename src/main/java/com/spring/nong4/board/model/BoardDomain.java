package com.spring.nong4.board.model;

import lombok.Data;

@Data
public class BoardDomain extends BoardEntity{
    private String userNick;
    private String providerType;
}
