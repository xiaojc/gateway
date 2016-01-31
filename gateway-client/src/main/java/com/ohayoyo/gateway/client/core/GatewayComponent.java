package com.ohayoyo.gateway.client.core;

public interface GatewayComponent<Component> {

    Component getComponent() throws GatewayException;

    GatewayConfig getGatewayConfig();

    GatewayComponent<Component> setGatewayConfig(GatewayConfig gatewayConfig);

    GatewayDefine getGatewayDefine();

    GatewayComponent<Component> setGatewayDefine(GatewayDefine gatewayDefine);

    GatewayRequest getGatewayRequest();

    GatewayComponent<Component> setGatewayRequest(GatewayRequest gatewayRequest);

}
