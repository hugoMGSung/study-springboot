package com.hugo83.openapi_demo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hugo83.openapi_demo.dto.RestaurantItem;

public class ApiResponse {
    @JsonProperty("getFoodKr")
    public GetFoodKr getFoodKr;

    public static class GetFoodKr {
        public Header header;
        public List<RestaurantItem> item;
        public int numOfRows;
        public int pageNo;
        public int totalCount;
    }

    public static class Header {
        public String code;
        public String message;
    }
}
