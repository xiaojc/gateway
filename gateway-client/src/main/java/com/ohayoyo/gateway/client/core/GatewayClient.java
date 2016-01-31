package com.ohayoyo.gateway.client.core;

public interface GatewayClient {

    GatewayConfig getGatewayConfig();

    GatewayClient setGatewayConfig(GatewayConfig gatewayConfig);

    GatewayExecutor getGatewayExecutor();

    GatewayClient setGatewayExecutor(GatewayExecutor gatewayExecutor);

    GatewayResponse session(GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException;

    <T> GatewayResponse session(Class<T> responseType, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException;

}
