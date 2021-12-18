package com.spring.nong4.openapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@RestController
@RequestMapping("/openapi")
public class apiTestController {

    @GetMapping("/apiTest")
    public String callApiHttp() {
//        StringBuffer result = new StringBuffer();
//        try {
//            String urlstr = "http://api.nongsaro.go.kr/service/cropEbook/videoList?" + "apiKey=20210713ZU1XHCDLCGWITY5LN99HBW" +
//                    "&type=json"+
//                    "&numOfRows=1000";
//            URL url = new URL(urlstr);
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
//
//            String returnLine;
//            result.append("<xmp>");
//            while((returnLine = br.readLine()) != null) {
//                result.append(returnLine + "\n");
//            }
//            urlConnection.disconnect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return result+"</xmp>";
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder("http://api.nongsaro.go.kr/service/cropEbook/videoList");
            urlBuilder.append("?" + URLEncoder.encode("apiKey", "UTF-8") + "=20210713ZU1XHCDLCGWITY5LN99HBW");
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1000","UTF-8"));
            urlBuilder.append("&type=json");
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

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
            System.out.println("result : ");
            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "";
    }
}