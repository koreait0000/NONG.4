package com.spring.nong4.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class farmWorkingPlanDomain {
    private String apiKey; // 발급받은 Open API 인증키
    @JsonProperty("data")
    private List<itemTag> workingItemList;
    @JsonProperty("schedule")
    private List<scheduleLst> workingScheduleList;

    @Data
    public static class itemTag {
        private String codeNm; // 품목 명
        private String kidofcomdtySeCode; // 품목 코드
        private String sort; // 순서
    }
    @Data
    public static class scheduleLst {
        private String cntntsNo; // 컨텐츠 번호
        private String fileDownUrlInfo; // 첨부파일 다운로드 URL
        private String fileName; // 첨부파일명
        private String fileSeCode; // 첨부파일구분코드
        private String orginlFIleNm; // 첨부파일물리명
        private String sj; // 제목
    }
}
