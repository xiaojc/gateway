package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.client.restful.RestfulClient;
import com.ohayoyo.gateway.http.core.HttpGateway;

/**
 * @author 蓝明乐
 */
public interface GatewayClient {

    GatewayClient DEFAULT_CLIENT = RestfulClient.defaultRestfulClient();

    HttpGateway getHttpGateway();

    GatewayClient setHttpGateway(HttpGateway httpGateway);

    <RequestBody> GatewayResponse<String> session(GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

    <ResponseBody, RequestBody> GatewayResponse<ResponseBody> session(Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

}
