package com.ohayoyo.gateway.client;

import com.ohayoyo.gateway.http.HttpClientHandler;

public interface GatewayClient {

    HttpClientHandler getHttpClientHandler();

    GatewayClient setHttpClientHandler(HttpClientHandler httpClientHandler);

    <RequestBody> GatewayResponse<String> session(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

    <ResponseBody, RequestBody> GatewayResponse<ResponseBody> session(Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

}
