package com.hugo83.openapi_demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.hugo83.openapi_demo.repository.AreaCodeRepository;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
public class AreaCodeController {

    // private final AreaCodeRepository areaCodeRepository;

    @RequestMapping("/api")
    public String requestApi() throws IOException  {
        StringBuffer result = new StringBuffer();

        try {
            StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/B551011/KorWithService2/areaCode2"); //URL
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=JzmUY2JqiPqaZHmZ7VDke8wMFu3m%2FCXZSUCawmglK99g1cw5ytYYWZ%2F4VmiJz2Wn5MB1aBEA7N0YlXlJz%2B%2FK8A%3D%3D"); //서비스키
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //페이지 번호
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); //한 페이지 결과 수
            urlBuilder.append("&MobileOS=ETC&MobileApp=AppTest&areaCode=1"); 
            urlBuilder.append("&_type=json"); //결과 json 포맷

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd;
            if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line + "\n");
            }
            rd.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "";
    }
    
}
