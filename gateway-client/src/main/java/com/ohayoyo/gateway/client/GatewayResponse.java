package com.ohayoyo.gateway.client;

import org.springframework.util.MultiValueMap;

/**
 * @author 蓝明乐
 */
public interface GatewayResponse<ResponseBody> {

    Integer getStatusCode();

    GatewayResponse<ResponseBody> setStatusCode(Integer statusCode);

    String getReasonPhrase();

    GatewayResponse<ResponseBody> setReasonPhrase(String reasonPhrase);

    MultiValueMap<String, String> getResponseHeaders();

    GatewayResponse<ResponseBody> setResponseHeaders(MultiValueMap<String, String> responseHeaders);

    ResponseBody getResponseBody();

    GatewayResponse<ResponseBody> setResponseBody(ResponseBody responseBody);

}
