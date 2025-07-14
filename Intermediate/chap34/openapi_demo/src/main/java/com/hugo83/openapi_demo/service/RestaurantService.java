package com.hugo83.openapi_demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hugo83.openapi_demo.dto.RestaurantItem;
import com.hugo83.openapi_demo.response.ApiResponse;

@Service
public class RestaurantService {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<RestaurantItem> fetchRestaurants() {
        try {
            String serviceKey = "JzmUY2JqiPqaZHmZ7VDke8wMFu3m%2FCXZSUCawmglK99g1cw5ytYYWZ%2F4VmiJz2Wn5MB1aBEA7N0YlXlJz%2B%2FK8A%3D%3D";
            String url = "https://apis.data.go.kr/6260000/FoodService/getFoodKr"
                       + "?serviceKey=" + serviceKey
                       + "&pageNo=1&numOfRows=10&resultType=json";

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            String body = response.getBody();

            ObjectMapper mapper = new ObjectMapper();
            ApiResponse apiResponse = mapper.readValue(body, ApiResponse.class);

            return apiResponse.getFoodKr.item;  // ✅ 구조 수정한 부분
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
