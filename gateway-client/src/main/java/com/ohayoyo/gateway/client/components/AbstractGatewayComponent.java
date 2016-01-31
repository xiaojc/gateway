package com.ohayoyo.gateway.client.components;

import com.ohayoyo.gateway.client.core.GatewayComponent;
import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayRequest;

public abstract class AbstractGatewayComponent<Component> implements GatewayComponent<Component> {

    private GatewayConfig gatewayConfig;

    private GatewayDefine gatewayDefine;

    private GatewayRequest gatewayRequest;

    @Override
    public GatewayConfig getGatewayConfig() {
        return gatewayConfig;
    }

    @Override
    public AbstractGatewayComponent<Component> setGatewayConfig(GatewayConfig gatewayConfig) {
        this.gatewayConfig = gatewayConfig;
        return this;
    }

    @Override
    public GatewayDefine getGatewayDefine() {
        return gatewayDefine;
    }

    @Override
    public AbstractGatewayComponent<Component> setGatewayDefine(GatewayDefine gatewayDefine) {
        this.gatewayDefine = gatewayDefine;
        return this;
    }

    @Override
    public GatewayRequest getGatewayRequest() {
        return gatewayRequest;
    }

    @Override
    public AbstractGatewayComponent<Component> setGatewayRequest(GatewayRequest gatewayRequest) {
        this.gatewayRequest = gatewayRequest;
        return this;
    }

}
