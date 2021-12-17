package com.spring.nong4.openapi;

import com.sun.deploy.xml.XMLParser;
import org.json.simple.ItemList;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.crypto.dsig.XMLObject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.events.EndElement;
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

@RestController
@RequestMapping("/openapi")
public class apiTestController {

    private static String getTagValue(String tag, Element eElement) {
        NodeList nList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node) nList.item(0);
        if(nValue == null)
            return null;
        return nValue.getNodeValue();
    }

    @ResponseBody
    @GetMapping("/apiTest")
    public List<Map<String, Object>> callApiHttp(Model model) {
        StringBuffer result = new StringBuffer();
        String urlParse = "";
        List<Map<String, Object>> itemList = new ArrayList<>();
        ModelAndView mv = new ModelAndView();

        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/cropEbook/videoList");
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=" + "20210713ZU1XHCDLCGWITY5LN99HBW");
            urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json","UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000","UTF-8"));

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

            for(int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                Map<String, Object> itemMap = new HashMap<>();

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    itemMap.put("videoImg", getTagValue("videoImg",eElement));
                    itemMap.put("videoLink", getTagValue("videoLink",eElement));
                    itemMap.put("videoOriginInstt", getTagValue("videoOriginInstt",eElement));
                    itemMap.put("videoTitle", getTagValue("videoTitle",eElement));

//                    System.out.println("################");
//                    System.out.println("videoImg : " + getTagValue("videoImg",eElement));
//                    System.out.println("videoLink : " + getTagValue("videoLink",eElement));
//                    System.out.println("videoOriginInstt : " + getTagValue("videoOriginInstt",eElement));
//                    System.out.println("videoTitle : " + getTagValue("videoTitle",eElement));

                    mv.addObject("itemList",itemList);
                    itemList.add(itemMap);

                }
            }
//            System.out.println("itemList : " + itemList);
            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return itemList;
    }
}