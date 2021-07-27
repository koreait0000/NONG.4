package com.spring.nong4.board.model;

import lombok.Data;

@Data
public class SearchCriteria extends Criteria{
    private String searchType;
    private String keyword;

    @Override
    public String toString() {
        return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword + "]";
    }
}
