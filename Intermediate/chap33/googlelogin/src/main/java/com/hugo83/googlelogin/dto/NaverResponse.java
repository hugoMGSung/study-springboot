package com.hugo83.googlelogin.dto;

import java.util.Map;

public class NaverResponse implements OAuth2Response {

    @SuppressWarnings("unused")
    private final Map<String, Object> attribute;

    @SuppressWarnings("unchecked")
    public NaverResponse(Map<String, Object> attribute) {
        this.attribute = (Map<String, Object>) attribute.get("response");
    }

    @Override
    public String getProvider() {
        return null;
    }

    @Override
    public String getProviderId() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

}
