package com.ohayoyo.gateway.client.core;

import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

public interface GatewayScope {

    GatewayConfig getGatewayConfig();

    GatewayRequest getGatewayRequest();

    GatewayDefine getGatewayDefine();

    ClientHttpRequest getClientHttpRequest();

    ClientHttpResponse getClientHttpResponse();

}
