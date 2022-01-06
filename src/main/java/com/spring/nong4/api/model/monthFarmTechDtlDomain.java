package com.spring.nong4.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class monthFarmTechDtlDomain {
    private String srchCntntsSnn;
    private String srchCurationNo;
    @JsonProperty("data")
    private List<monthFarmTechDtlDomain.itemTag> farmTechItemList; // <item> 하위 태그 리스트

    @Data
    public static class itemTag { // <itmes>의 하위 태그인 <item>의 하위 태그
        private String cntntsInfoHtml;
    }

}
