package com.hugo83.openapi_demo.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hugo83.openapi_demo.dto.RestaurantItem;
import com.hugo83.openapi_demo.response.ApiResponse;
import com.hugo83.openapi_demo.service.RestaurantService;


@Controller
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurants")
    public String showRestaurants(Model model) {
        List<RestaurantItem> restaurants = restaurantService.fetchRestaurants();
        model.addAttribute("restaurants", restaurants);
        return "restaurant-list";
    }

    @GetMapping("/newrestaurant")
    public String getRestaurantNew(Model model) {
        List<RestaurantItem> restaurants = new ArrayList<RestaurantItem>();
        StringBuffer result = new StringBuffer();

        try {
            StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/6260000/FoodService/getFoodKr"); //URL
            urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=JzmUY2JqiPqaZHmZ7VDke8wMFu3m%2FCXZSUCawmglK99g1cw5ytYYWZ%2F4VmiJz2Wn5MB1aBEA7N0YlXlJz%2B%2FK8A%3D%3D"); //서비스키
            urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); //페이지 번호
            urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); //한 페이지 결과 수
            urlBuilder.append("&resultType=json"); //결과 json 포맷

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


            // JSON 문자열 파싱
            ObjectMapper mapper = new ObjectMapper();
            ApiResponse apiResponse = mapper.readValue(result.toString(), ApiResponse.class);

            // 리스트에 담기
            restaurants = apiResponse.getFoodKr.item;
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("restaurants", restaurants);
        return "restaurant-list";
        
    }
    
}
