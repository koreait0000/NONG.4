package com.spring.nong4.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class monthFarmTechDomain {
    private String apiKey;
    private String srchStr;
    private String sEraInfo;
    private String pageNo;
    private String numOfRows;
    private String totalCount;
    @JsonProperty("data")
    private List<itemTag> farmTechItemList; // <item> 하위 태그 리스트

    @Data
    public static class itemTag { // <itmes>의 하위 태그인 <item>의 하위 태그
        private String atchmnflGroupEsntlCode;
        private String atchmnflSn;
        private String atchmnflStreNm;
        private String clCodeNm;
        private String contentCnt;
        private String curationNm;
        private String curationNo;
        private String curationSumryDtl;
        private String rdcnt;
        private String recomendAt;
        private String streCours;
        private String svcDt;
        private String thumbFileNm;
    }
}
