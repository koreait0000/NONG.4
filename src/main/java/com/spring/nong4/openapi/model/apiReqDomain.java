package com.spring.nong4.openapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.List;

@Data
public class apiReqDomain { // <items> 하위 태그

    private String apiKey; // 발급받은 Open API 인증키
    private String sType;  // 검색 항목
    private String sText;  // 검색 단어
    private String mainCategory; // 품목 분류 코드
    private String pageNo; // 조회할 페이지 번호
    private String numOfRows; // 한 페이지에 제공할 건수
    private String totalCount; // 토탈 카운트
    private String stdPrdlstCodeNm;

    @JsonProperty("data")
    private List<itemTag> videoItemList; // <item> 하위 태그 리스트

    @Data
    public static class itemTag { // <itmes>의 하위 태그인 <item>의 하위 태그
        private String videoImg; // 비디오 이미지
        private String videoLink; // 비디오 링크
        private String videoOriginInstt; // 비디오 출처
        private String videoTitle; // 비디오 제목
    }
}
