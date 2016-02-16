package com.ohayoyo.gateway.client;

import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @author 蓝明乐
 */
public interface GatewayRequest<RequestBody> {

    String getSelect();

    GatewayRequest<RequestBody> setSelect(String select);

    Map<String, String> getRequestPathVariables();

    GatewayRequest<RequestBody> setRequestPathVariables(Map<String, String> requestPathVariables);

    MultiValueMap<String, String> getRequestQueries();

    GatewayRequest<RequestBody> setRequestQueries(MultiValueMap<String, String> requestQueries);

    MultiValueMap<String, String> getRequestHeaders();

    GatewayRequest<RequestBody> setRequestHeaders(MultiValueMap<String, String> requestHeaders);

    RequestBody getRequestBody();

    GatewayRequest<RequestBody> setRequestBody(RequestBody requestBody);

}
