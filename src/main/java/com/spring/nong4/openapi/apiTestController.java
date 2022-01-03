package com.spring.nong4.openapi;

import com.spring.nong4.board.model.PageMaker;
import com.spring.nong4.board.model.SearchCriteria;
import com.spring.nong4.openapi.model.apiReqDomain;
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
@RequestMapping("/level")
public class apiTestController {

    private static String getTagValue(String tag, Element eElement) {
        NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

//    @GetMapping("/junior")
//    public String callApiHttp(apiReqDomain reqDomain, Model model)
//    {
//        StringBuffer result = new StringBuffer();
//        String urlParse = "";
//        System.out.println("reqDomain : " + reqDomain);
//        System.out.println("get.sText ! : " + reqDomain.getSText());
////        apiReqDomain reqDomain = new apiReqDomain();
//        System.out.println("reqDomain.getPageNo() : " + reqDomain.getPageNo());
//
//        try {
//            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/curationMvp/curationMvpList");
//            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20210713ZU1XHCDLCGWITY5LN99HBW");
//            urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json","UTF-8"));
//            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
//            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10","UTF-8"));
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
//            NodeList itemsList = document.getElementsByTagName("items");
//
//            List<apiReqDomain.itemTag> videoList = new ArrayList<>();
//            apiReqDomain.itemTag itemTag;
//
//            for(int temp = 0; temp < nList.getLength(); temp++) {
//                Node nNode = nList.item(temp);
//
//                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
//                    Element eElement = (Element) nNode;
//
//                    itemTag = new apiReqDomain.itemTag();
//
//                    itemTag.setMvpCipNo(getTagValue("mvpCipNo",eElement));
//                    itemTag.setMvpClipSj(getTagValue("mvpClipSj",eElement));
//                    itemTag.setMvpNo(getTagValue("mvpNo",eElement));
//                    itemTag.setSj(getTagValue("sj",eElement));
//                    itemTag.setStdPrdlstCodeNm(getTagValue("stdPrdlstCodeNm",eElement));
//                    itemTag.setVideoImg(getTagValue("videoImg",eElement));
//                    itemTag.setVideoLink(getTagValue("videoLink",eElement));
//
//                    videoList.add(itemTag);
//                }
//            }
//
//            Node nNode = itemsList.item(0);
//            Element eElement = (Element) nNode;
//            reqDomain.setPageNo(getTagValue("pageNo",eElement));
//            reqDomain.setNumOfRows(getTagValue("numOfRows",eElement));
//            reqDomain.setTotalCount(getTagValue("totalCount",eElement));
//
//            reqDomain.setVideoItemList(videoList);
//
//            Map<String, Object> map = new HashMap<>();
//            map.put("reqDomain", reqDomain);
//            model.addAllAttributes(map);
//            System.out.println("reqDomain : " + reqDomain);
//
//            rd.close();
//            conn.disconnect();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return "level/junior";
//    }

    @GetMapping("/junior")
    public String category (apiReqDomain reqDomain, Model model, SearchCriteria scri) {
        StringBuffer result = new StringBuffer();
        String urlParse = "";
        apiReqDomain param = new apiReqDomain();
        apiReqDomain.itemTag itemTag = new apiReqDomain.itemTag();

        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/curationMvp/curationMvpList");
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20210713ZU1XHCDLCGWITY5LN99HBW");
            urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json","UTF-8"));
            if(reqDomain.getMainCategory() != null) {
                urlBuilder.append("&" + URLEncoder.encode("mainCategory", "UTF-8") + "=" + URLEncoder.encode(reqDomain.getMainCategory(), "UTF-8"));
            }
            if(reqDomain.getSType() != null) {
                urlBuilder.append("&" + URLEncoder.encode("sType", "UTF-8") + "=" + URLEncoder.encode(reqDomain.getSType(), "UTF-8"));
            }
            if(reqDomain.getSText() != null){
                urlBuilder.append("&" + URLEncoder.encode("sText", "UTF-8") + "=" + URLEncoder.encode(reqDomain.getSText(), "UTF-8"));
            }
//            if((!ajaxValue.get("pageNo").toString().isEmpty())) {
//                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(ajaxValue.get("pageNo").toString(), "UTF-8"));
//            } else
//            {
//                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
//            }
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

            List<apiReqDomain.itemTag> videoList = new ArrayList<>();

            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    itemTag = new apiReqDomain.itemTag();

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
            reqDomain.setPageNo(getTagValue("pageNo",eElement));
            reqDomain.setNumOfRows(getTagValue("numOfRows",eElement));
            reqDomain.setTotalCount(getTagValue("totalCount",eElement));
            reqDomain.setSType(reqDomain.getSType());
            reqDomain.setSText(reqDomain.getSText());
            reqDomain.setMainCategory(reqDomain.getMainCategory());
//            reqDomain.setPageNo(ajaxValue.get("pageNo").toString());
            reqDomain.setVideoItemList(videoList);

            PageMaker pageMaker = new PageMaker();
//            pageMaker.setCri(scri);
//            pageMaker.setTotalCount(reqDomain.getTotalCount().toString());

            Map<String, Object> map = new HashMap<>();
            map.put("reqDomain", reqDomain);
            model.addAllAttributes(map);

            rd.close();
            conn.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "level/junior";
    }
}