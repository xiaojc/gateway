package com.ohayoyo.gateway.client.core;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

public class AbstractGatewayScope implements GatewayScope {

    private GatewayConfig gatewayConfig;

    private GatewayRequest gatewayRequest;

    private GatewayDefine gatewayDefine;

    private ClientHttpRequest clientHttpRequest;

    private ClientHttpResponse clientHttpResponse;

    @Override
    public GatewayConfig getGatewayConfig() {
        return gatewayConfig;
    }

    public AbstractGatewayScope setGatewayConfig(GatewayConfig gatewayConfig) {
        this.gatewayConfig = gatewayConfig;
        return this;
    }

    @Override
    public GatewayRequest getGatewayRequest() {
        return gatewayRequest;
    }

    public AbstractGatewayScope setGatewayRequest(GatewayRequest gatewayRequest) {
        this.gatewayRequest = gatewayRequest;
        return this;
    }

    @Override
    public GatewayDefine getGatewayDefine() {
        return gatewayDefine;
    }

    public AbstractGatewayScope setGatewayDefine(GatewayDefine gatewayDefine) {
        this.gatewayDefine = gatewayDefine;
        return this;
    }

    @Override
    public ClientHttpRequest getClientHttpRequest() {
        return clientHttpRequest;
    }

    public AbstractGatewayScope setClientHttpRequest(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
        return this;
    }

    @Override
    public ClientHttpResponse getClientHttpResponse() {
        return clientHttpResponse;
    }

    public AbstractGatewayScope setClientHttpResponse(ClientHttpResponse clientHttpResponse) {
        this.clientHttpResponse = clientHttpResponse;
        return this;
    }
}
