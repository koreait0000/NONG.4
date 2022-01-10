package com.spring.nong4.api;

import com.spring.nong4.api.model.farmWorkingPlanDomain;
import com.spring.nong4.api.model.apiVideoDomain;
import com.spring.nong4.api.model.monthFarmTechDomain;
import com.spring.nong4.api.model.monthFarmTechDtlDomain;
import com.spring.nong4.board.model.PageMaker;
import com.spring.nong4.board.model.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ApiService {
    @Autowired
    private ApiService service;

    private static String getTagValue(String tag, Element eElement) {
        NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    public Map<String, Object> apiVideo (apiVideoDomain apiVideoDomain, SearchCriteria scri) {
        Map<String, Object> map = new HashMap<>();
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


            map.put("apiVideoDomain", apiVideoDomain);
            map.put("pageMaker",pageMaker);

            rd.close();
            conn.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return map;
    }

    public Map<String, Object> monthFarmTech(monthFarmTechDomain farmTechDomain, SearchCriteria scri) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer result = new StringBuffer();
        String urlParse = "";
        monthFarmTechDomain.itemTag itemTag;

        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/monthFarmTech/monthFarmTechLst");
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20220105GHIZRN4S603UMMISOFCEXQ");
            if(farmTechDomain.getPageNo() != null) {
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(farmTechDomain.getPageNo(), "UTF-8"));
            } else
            {
                urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            }
            if(farmTechDomain.getSrchStr() != null){
                urlBuilder.append("&" + URLEncoder.encode("srchStr", "UTF-8") + "=" + URLEncoder.encode(farmTechDomain.getSrchStr(), "UTF-8"));
            }
            if(farmTechDomain.getSEraInfo() != null){
                urlBuilder.append("&" + URLEncoder.encode("sEraInfo", "UTF-8") + "=" + URLEncoder.encode(farmTechDomain.getSEraInfo(), "UTF-8"));
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
                    itemTag.setRdCnt(getTagValue("rdcnt",eElement));
                    itemTag.setRecomendAt(getTagValue("recomendAt",eElement));
                    itemTag.setStreCours(getTagValue("streCours",eElement));
                    itemTag.setSvcDt(getTagValue("svcDt",eElement));
                    itemTag.setThumbFileNm(getTagValue("thumbFileNm",eElement));

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

            PageMaker pageMaker = new PageMaker();
            pageMaker.setCri(scri);
            pageMaker.setDisplayPageNum(Integer.parseInt(farmTechDomain.getNumOfRows()));
            pageMaker.setTotalCount(Integer.parseInt(farmTechDomain.getTotalCount()));

            map.put("farmTechDomain", farmTechDomain);
            map.put("pageMaker",pageMaker);

            rd.close();
            conn.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, Object> monthFarmTechDtl(monthFarmTechDtlDomain farmTechDtlDomain) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer result = new StringBuffer();
        String urlParse = "";
        monthFarmTechDtlDomain.itemTag itemTag;

        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/monthFarmTech/monthFarmTechDtl");
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20220105GHIZRN4S603UMMISOFCEXQ");
            urlBuilder.append("&" + URLEncoder.encode("srchCntntsSnn", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("srchCurationNo", "UTF-8") + "=" + URLEncoder.encode(farmTechDtlDomain.getSrchCurationNo(),"UTF-8"));

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
            List<monthFarmTechDtlDomain.itemTag> farmTechItemList = new ArrayList<>();

            Node nNode = nList.item(0);

            if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;

                itemTag = new monthFarmTechDtlDomain.itemTag();

                String str = getTagValue("cntntsInfoHtml",eElement);
                str = str.replace("href=\"/ps","href=\"http://www.nongsaro.go.kr/ps");
                str = str.replace("src=\"/ps","src=\"http://www.nongsaro.go.kr/ps");

                itemTag.setCntntsInfoHtml(str);
                farmTechItemList.add(itemTag);
            }

            farmTechDtlDomain.setSrchCntntsSnn(farmTechDtlDomain.getSrchCntntsSnn());
            farmTechDtlDomain.setSrchCurationNo(farmTechDtlDomain.getSrchCurationNo());

            farmTechDtlDomain.setFarmTechItemList(farmTechItemList);

            map.put("farmTechDtlDomain", farmTechDtlDomain);

            rd.close();
            conn.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, Object> monthFarmTechDtlImg(monthFarmTechDomain farmTechDomain, SearchCriteria scri){
        Map<String, Object> monthFarmTechMap = new HashMap<>();
        monthFarmTechMap.put("monthFarmTechMap", service.monthFarmTech(farmTechDomain,scri));
        Map monthFarmTechDomainMap = (Map)monthFarmTechMap.get("monthFarmTechMap");
        monthFarmTechDomain monthFarmTechDomain = (monthFarmTechDomain)monthFarmTechDomainMap.get("farmTechDomain");
        List<monthFarmTechDomain.itemTag> farmTechItemList = monthFarmTechDomain.getFarmTechItemList();
        System.out.println("farmTechItemList: " + farmTechItemList);

        Map<String, Object> imgMap = new HashMap<>();
        List<String> imgList = new ArrayList<>();

        for(int i = 0; i < farmTechItemList.size(); i++){
            String curationNo = farmTechItemList.get(i).getCurationNo();
            monthFarmTechDtlDomain farmTechDtlDomain = new monthFarmTechDtlDomain();
            farmTechDtlDomain.setSrchCurationNo(curationNo);
            Map<String, Object> monthFarmTechDtlMap = new HashMap<>();
            monthFarmTechDtlMap.put("monthFarmTechDtlMap", service.monthFarmTechDtl(farmTechDtlDomain));
            Map monthFarmTechDtlDomainMap = (Map)monthFarmTechDtlMap.get("monthFarmTechDtlMap");
            monthFarmTechDtlDomain monthFarmTechDtlDomain = (monthFarmTechDtlDomain)monthFarmTechDtlDomainMap.get("farmTechDtlDomain");
            List<monthFarmTechDtlDomain.itemTag> farmTechDtlItemList = monthFarmTechDtlDomain.getFarmTechItemList();

            String cntntsInfoHtml = farmTechDtlItemList.get(0).getCntntsInfoHtml();
            Pattern pattern = Pattern.compile("src=\"http(?:.*?).jpg");
            String result = "";

            Matcher matcher = pattern.matcher(cntntsInfoHtml);

            if(matcher.find()) {
                result = matcher.group(0).substring(10);
                imgList.add(result);
            } else {
                pattern = Pattern.compile("src=\"http(?:.*?).png");
                matcher = pattern.matcher(cntntsInfoHtml);
                if(matcher.find()){
                    result = matcher.group(0).substring(10);
                    imgList.add(result);
                } else {
                    pattern = Pattern.compile("src=\"http(?:.*?).gif");
                    matcher = pattern.matcher(cntntsInfoHtml);
                    if(matcher.find()){
                        result = matcher.group(0).substring(10);
                        imgList.add(result);
                    } else {
                        imgList.add(" ");
                    }
                }
            }
        }
        imgMap.put("img", imgList);

        return imgMap;
    }

    public Map<String, Object> farmWorkingPlan() {

        Map<String, Object> map = new HashMap<>();
        StringBuffer result = new StringBuffer();
        String urlParse = "";
        farmWorkingPlanDomain workingDomain = new farmWorkingPlanDomain();

        farmWorkingPlanDomain.itemTag itemTag;

        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/farmWorkingPlanNew/workScheduleGrpList");
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20220105GHIZRN4S603UMMISOFCEXQ");

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

            List<farmWorkingPlanDomain.itemTag> workingItemList = new ArrayList<>();

            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    itemTag = new farmWorkingPlanDomain.itemTag();

                    itemTag.setCodeNm(getTagValue("codeNm",eElement));
                    itemTag.setKidofcomdtySeCode(getTagValue("kidofcomdtySeCode",eElement));
                    itemTag.setSort(getTagValue("sort",eElement));

                    workingItemList.add(itemTag);
                }
            }

            workingDomain.setWorkingItemList(workingItemList);

            map.put("workingDomain", workingDomain);

            rd.close();
            conn.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, Object> workScheduleLst(farmWorkingPlanDomain workingDomain, String ajaxValue) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> workingMap = new HashMap<>();
        workingMap.put("workingMap", service.farmWorkingPlan());

        Map workingDomainMap = (Map)workingMap.get("workingMap");
        farmWorkingPlanDomain workingPlanDomainMap = (farmWorkingPlanDomain)workingDomainMap.get("workingDomain");

        List<farmWorkingPlanDomain.itemTag> workingItemListMap = workingPlanDomainMap.getWorkingItemList();

        StringBuffer result = new StringBuffer();
        String urlParse = "";
        farmWorkingPlanDomain.scheduleLst scheduleLst;

        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/farmWorkingPlanNew/workScheduleLst");
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20220105GHIZRN4S603UMMISOFCEXQ");
            urlBuilder.append("&" + URLEncoder.encode("kidofcomdtySeCode", "UTF-8") + "=" + ajaxValue);

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

            List<farmWorkingPlanDomain.scheduleLst> workingItemList = new ArrayList<>();

            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    scheduleLst = new farmWorkingPlanDomain.scheduleLst();

                    scheduleLst.setCntntsNo(getTagValue("cntntsNo",eElement));
                    scheduleLst.setFileDownUrlInfo(getTagValue("fileDownUrlInfo",eElement));
                    scheduleLst.setFileName(getTagValue("fileName",eElement));
                    scheduleLst.setFileSeCode(getTagValue("fileSeCode",eElement));
                    scheduleLst.setOrginlFIleNm(getTagValue("orginlFileNm",eElement));
                    scheduleLst.setSj(getTagValue("sj",eElement));

                    workingItemList.add(scheduleLst);
                }
            }
            workingDomain.setWorkingScheduleList(workingItemList);

            map.put("workScheduleLst", workingDomain.getWorkingScheduleList());

            rd.close();
            conn.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, Object> farmWorkingInfo(String cntntsNo) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer result = new StringBuffer();
        String urlParse = "";
        farmWorkingPlanDomain.itemTagHtml itemTagHtml;

        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/farmWorkingPlanNew/workScheduleEraInfoLst");
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20220105GHIZRN4S603UMMISOFCEXQ");
            urlBuilder.append("&" + URLEncoder.encode("cntntsNo", "UTF-8") + "=" + cntntsNo);

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

            List<farmWorkingPlanDomain.itemTagHtml> workingInfoList = new ArrayList<>();

            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    itemTagHtml = new farmWorkingPlanDomain.itemTagHtml();

                    itemTagHtml.setHtmlCn(getTagValue("htmlCn",eElement));

                    workingInfoList.add(itemTagHtml);
                }
            }
            farmWorkingPlanDomain workingDomain = new farmWorkingPlanDomain();

            workingDomain.setWorkingInfoList(workingInfoList);

            map.put("workingDomain", workingDomain);

            rd.close();
            conn.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public Map<String, Object> schedultDt(String cntntsNo) {
        Map<String, Object> map = new HashMap<>();
        StringBuffer result = new StringBuffer();
        String urlParse = "";
        farmWorkingPlanDomain.schedultDt schedultDt;

        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/farmWorkingPlanNew/workScheduleDtl");
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20220105GHIZRN4S603UMMISOFCEXQ");
            urlBuilder.append("&" + URLEncoder.encode("cntntsNo", "UTF-8") + "=" + cntntsNo);

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

            List<farmWorkingPlanDomain.schedultDt> workScheduleDtList = new ArrayList<>();

            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    schedultDt = new farmWorkingPlanDomain.schedultDt();

                    schedultDt.setCn(getTagValue("cn",eElement));
                    schedultDt.setKidofcomdtySeCodeNm(getTagValue("kidofcomdtySeCodeNm", eElement));
                    schedultDt.setCntntsSj(getTagValue("cntntsSj", eElement));

                    workScheduleDtList.add(schedultDt);
                }
            }
            farmWorkingPlanDomain workingDomain = new farmWorkingPlanDomain();

            workingDomain.setWorkScheduleDtList(workScheduleDtList);

            map.put("workScheduleDt", workingDomain);

            rd.close();
            conn.disconnect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
