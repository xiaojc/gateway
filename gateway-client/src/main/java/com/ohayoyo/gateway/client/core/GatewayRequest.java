package com.ohayoyo.gateway.client.core;

import org.springframework.util.MultiValueMap;

import java.util.Map;

public interface GatewayRequest {

    String getSelect();

    GatewayRequest setSelect(String select);

    Map<String, String> getRequestPathVariables();

    GatewayRequest setRequestPathVariables(Map<String, String> requestPathVariables);

    @Deprecated
    MultiValueMap<String, String> getRequestParameters();

    @Deprecated
    GatewayRequest setRequestParameters(MultiValueMap<String, String> requestParameters);

    MultiValueMap<String, String> getRequestQueries();

    GatewayRequest setRequestQueries(MultiValueMap<String, String> requestQueries);

    MultiValueMap<String, String> getRequestHeaders();

    GatewayRequest setRequestHeaders(MultiValueMap<String, String> requestHeaders);

    Object getRequestEntity();

    GatewayRequest setRequestEntity(Object requestEntity);

}
