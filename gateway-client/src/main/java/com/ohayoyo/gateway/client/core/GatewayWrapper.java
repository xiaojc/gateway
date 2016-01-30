package com.ohayoyo.gateway.client.core;

public interface GatewayWrapper {

    GatewayResponse wrap(GatewayEntity gatewayEntity, GatewayConfig gatewayConfig, GatewayRequest gatewayRequest, GatewayDefine gatewayDefine) throws GatewayException;

}
