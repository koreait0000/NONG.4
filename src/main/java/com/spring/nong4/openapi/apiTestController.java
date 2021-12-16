package com.spring.nong4.openapi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Controller
@RequestMapping("/openapi")
public class apiTestController {

    @GetMapping("/apiTest")
    public String callApiHttp() {
        StringBuffer result = new StringBuffer();
        try {
            String urlstr = "http://api.nongsaro.go.kr/service/cropEbook/mainTechList?" + "apiKey=20210713ZU1XHCDLCGWITY5LN99HBW" +
                    "&type=xml"+
                    "&pageNo=1"+
                    "&numOfRows=10000000";
            URL url = new URL(urlstr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));

            String returnLine;
            result.append("<xmp>");
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n");
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result+"</xmp>";
    }
}