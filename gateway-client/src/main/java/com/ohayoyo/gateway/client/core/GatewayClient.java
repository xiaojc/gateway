package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.client.exception.GatewayException;

/**
 * @author 蓝明乐
 */
public interface GatewayClient extends GatewayContextAccessor {

    <ResponseBody, RequestBody> GatewayResponse<ResponseBody> session(Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException;

}
