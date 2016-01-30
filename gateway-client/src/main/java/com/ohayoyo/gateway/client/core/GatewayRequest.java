package com.ohayoyo.gateway.client.core;

import java.lang.reflect.Type;
import java.util.Map;

public interface GatewayRequest {

    String getSelect();

    GatewayRequest setSelect(String select);

    Map<String, Object> getRequestPathVariables();

    GatewayRequest setRequestPathVariables(Map<String, Object> requestPathVariables);

    @Deprecated
    Map<String, Object> getRequestParameters();

    @Deprecated
    GatewayRequest setRequestParameters(Map<String, Object> requestParameters);

    Map<String, Object> getRequestQueries();

    GatewayRequest setRequestQueries(Map<String, Object> requestQueries);

    Map<String, Object> getRequestHeaders();

    GatewayRequest setRequestHeaders(Map<String, Object> requestHeaders);

    Object getRequestEntity();

    GatewayRequest setRequestEntity(Object requestEntity);

    Type getResponseType();

    GatewayRequest setResponseType(Type responseType);

    GatewayRequest setResponseType(Class<?> responseType);

}
