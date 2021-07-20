package com.spring.nong4.board.model;

import lombok.Data;

@Data
public class BoardDomain extends BoardEntity{
    public String userNick;
    public int providerType;
}
