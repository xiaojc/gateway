package com.ohayoyo.gateway.client.core;

public interface GatewayClient {

    GatewayConfig getGatewayConfig();

    GatewayClient setGatewayConfig(GatewayConfig gatewayConfig);

    GatewayExecutor getGatewayExecutor();

    GatewayClient setGatewayExecutor(GatewayExecutor gatewayExecutor);

    GatewayResponse session(GatewayRequest gatewayRequest, GatewayDefine gatewayDefine) throws GatewayException;

}
