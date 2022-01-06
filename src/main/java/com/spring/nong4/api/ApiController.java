package com.spring.nong4.api;

import com.spring.nong4.api.model.monthFarmTechDomain;
import com.spring.nong4.api.model.monthFarmTechDtlDomain;
import com.spring.nong4.board.model.PageMaker;
import com.spring.nong4.board.model.SearchCriteria;
import com.spring.nong4.api.model.apiVideoDomain;
import com.spring.nong4.common.MyFarmFileUtils;
import com.spring.nong4.common.MyFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api")
public class ApiController {
    @Autowired private MyFarmFileUtils myFileUtils;

    private static String getTagValue(String tag, Element eElement) {
        NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    @GetMapping("/apiVideo")
    public String apiVideo (apiVideoDomain apiVideoDomain, Model model, SearchCriteria scri) {
        StringBuffer result = new StringBuffer();
        String urlParse = "";
        apiVideoDomain.itemTag itemTag;

        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/curationMvp/curationMvpList");
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20210713ZU1XHCDLCGWITY5LN99HBW");
            if(apiVideoDomain.getMainCategory() != null) {
                urlBuilder.append("&" + URLEncoder.encode("mainCategory", "UTF-8") + "=" + URLEncoder.encode(apiVideoDomain.getMainCategory(), "UTF-8"));
            }
            if(apiVideoDomain.getSType() != null) {
                urlBuilder.append("&" + URLEncoder.encode("sType", "UTF-8") + "=" + URLEncoder.encode(apiVideoDomain.getSType(), "UTF-8"));
            }
            if(apiVideoDomain.getSText() != null){
                urlBuilder.append("&" + URLEncoder.encode("sText", "UTF-8") + "=" + URLEncoder.encode(apiVideoDomain.getSText(), "UTF-8"));
            }
            if(apiVideoDomain.getPageNo() != null) {
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(apiVideoDomain.getPageNo(), "UTF-8"));
            } else
            {
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            }
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10","UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while((line = rd.readLine()) != null) {
                result.append(line + "\n");
            }
            urlParse = result.toString();

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new InputSource(new StringReader(urlParse)));

            //root tag(<response>)
            document.getDocumentElement().normalize();

            // node tag name
            NodeList nList = document.getElementsByTagName("item");
            NodeList itemsList = document.getElementsByTagName("items");

            List<apiVideoDomain.itemTag> videoList = new ArrayList<>();

            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    itemTag = new apiVideoDomain.itemTag();

                    itemTag.setMvpCipNo(getTagValue("mvpCipNo",eElement));
                    itemTag.setMvpClipSj(getTagValue("mvpClipSj",eElement));
                    itemTag.setMvpNo(getTagValue("mvpNo",eElement));
                    itemTag.setSj(getTagValue("sj",eElement));
                    itemTag.setStdPrdlstCodeNm(getTagValue("stdPrdlstCodeNm",eElement));
                    itemTag.setVideoImg(getTagValue("videoImg",eElement));
                    itemTag.setVideoLink(getTagValue("videoLink",eElement));

                    videoList.add(itemTag);
                }
            }

            Node nNode = itemsList.item(0);
            Element eElement = (Element) nNode;
            apiVideoDomain.setPageNo(getTagValue("pageNo",eElement));
            apiVideoDomain.setNumOfRows(getTagValue("numOfRows",eElement));
            apiVideoDomain.setTotalCount(getTagValue("totalCount",eElement));
            apiVideoDomain.setSType(apiVideoDomain.getSType());
            apiVideoDomain.setSText(apiVideoDomain.getSText());
            apiVideoDomain.setMainCategory(apiVideoDomain.getMainCategory());
            apiVideoDomain.setPageNo(apiVideoDomain.getPageNo());
            apiVideoDomain.setVideoItemList(videoList);

            PageMaker pageMaker = new PageMaker();
            pageMaker.setCri(scri);
            pageMaker.setDisplayPageNum(Integer.parseInt(apiVideoDomain.getNumOfRows()));
            pageMaker.setTotalCount(Integer.parseInt(apiVideoDomain.getTotalCount()));

            Map<String, Object> map = new HashMap<>();

            map.put("apiVideoDomain", apiVideoDomain);
            map.put("pageMaker",pageMaker);

            model.addAllAttributes(map);

            rd.close();
            conn.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "api/apiVideo";
    }

    @GetMapping("/monthFarmTech")
    public String monthFarmTech(monthFarmTechDomain farmTechDomain, Model model) {
        StringBuffer result = new StringBuffer();
        String urlParse = "";
        monthFarmTechDomain.itemTag itemTag;

        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/monthFarmTech/monthFarmTechLst");
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20220105GHIZRN4S603UMMISOFCEXQ");
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("5","UTF-8"));

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while((line = rd.readLine()) != null) {
                result.append(line + "\n");
            }
            urlParse = result.toString();

            System.out.println("urlParse : " + urlParse);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(new InputSource(new StringReader(urlParse)));

            //root tag(<response>)
            document.getDocumentElement().normalize();

            // node tag name
            NodeList nList = document.getElementsByTagName("item");
            NodeList itemsList = document.getElementsByTagName("items");

            List<monthFarmTechDomain.itemTag> farmTechItemList = new ArrayList<>();

            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    itemTag = new monthFarmTechDomain.itemTag();

                    itemTag.setAtchmnflGroupEsntlCode(getTagValue("atchmnflGroupEsntlCode",eElement));
                    itemTag.setAtchmnflSn(getTagValue("atchmnflSn",eElement));
                    itemTag.setAtchmnflStreNm(getTagValue("atchmnflStreNm",eElement));
                    itemTag.setClCodeNm(getTagValue("clCodeNm",eElement));
                    itemTag.setContentCnt(getTagValue("contentCnt",eElement));
                    itemTag.setCurationNm(getTagValue("curationNm",eElement));
                    itemTag.setCurationNo(getTagValue("curationNo",eElement));
                    itemTag.setCurationSumryDtl(getTagValue("curationSumryDtl",eElement));
                    itemTag.setRdcnt(getTagValue("rdcnt",eElement));
                    itemTag.setRecomendAt(getTagValue("recomendAt",eElement));
                    itemTag.setStreCours(getTagValue("streCours",eElement));
                    itemTag.setSvcDt(getTagValue("svcDt",eElement));
                    itemTag.setThumbFileNm(getTagValue("thumbFileNm",eElement));

                    System.out.println("저장경로 : " + itemTag.getStreCours());

                    farmTechItemList.add(itemTag);
                }

            }

            Node nNode = itemsList.item(0);
            Element eElement = (Element) nNode;

            farmTechDomain.setPageNo(getTagValue("pageNo",eElement));
            farmTechDomain.setNumOfRows(getTagValue("numOfRows",eElement));
            farmTechDomain.setTotalCount(getTagValue("totalCount",eElement));
            farmTechDomain.setSrchStr(farmTechDomain.getSrchStr());
            farmTechDomain.setSEraInfo(farmTechDomain.getSEraInfo());

            farmTechDomain.setFarmTechItemList(farmTechItemList);

            System.out.println("farmTechDomain : " + farmTechDomain);

            Map<String, Object> map = new HashMap<>();

            map.put("farmTechDomain", farmTechDomain);

            model.addAllAttributes(map);

            rd.close();
            conn.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return "api/monthFarmTech";
    }

//    @GetMapping("/monthFarmTech")
//    public String monthFarmTechDtl(monthFarmTechDtlDomain farmTechDomain) {
//        StringBuffer result = new StringBuffer();
//        String urlParse = "";
//        monthFarmTechDtlDomain.itemTag itemTag;
//
//        System.out.println("farmTechDomain! : " + farmTechDomain);
//
//        try {
//            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/monthFarmTech/monthFarmTechDtl");
//            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20220105GHIZRN4S603UMMISOFCEXQ");
//            urlBuilder.append("&" + URLEncoder.encode("srchCntntsSnn", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
//            urlBuilder.append("&" + URLEncoder.encode("srchCurationNo", "UTF-8") + "=" + URLEncoder.encode("1724","UTF-8"));
//
//            URL url = new URL(urlBuilder.toString());
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setRequestProperty("Content-type", "application/json");
//
//            BufferedReader rd;
//            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
//            } else {
//                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//            }
//            String line;
//            while((line = rd.readLine()) != null) {
//                result.append(line + "\n");
//            }
//            urlParse = result.toString();
//
//            System.out.println("urlParse : " + urlParse);
//
//            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//            Document document = dBuilder.parse(new InputSource(new StringReader(urlParse)));
//
//            //root tag(<response>)
//            document.getDocumentElement().normalize();
//
//            // node tag name
//            NodeList nList = document.getElementsByTagName("item");
//
//            rd.close();
//            conn.disconnect();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @GetMapping("/intermediate")
    public String intermediate() {
        return "api/intermediate";
    }

    @GetMapping("/advanced")
    public String advanced() {
        return "api/advanced";
    }

    @GetMapping("/master")
    public String master() {
        return "api/master";
    }
}
