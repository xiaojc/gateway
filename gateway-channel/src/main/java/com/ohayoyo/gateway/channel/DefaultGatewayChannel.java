package com.ohayoyo.gateway.channel;

import com.ohayoyo.gateway.client.GatewayClient;
import com.ohayoyo.gateway.client.GatewayResponse;
import com.ohayoyo.gateway.container.GatewayContainer;
import com.ohayoyo.gateway.define.http.InterfaceDefine;

public class DefaultGatewayChannel extends AbstractGatewayChannel {

    public DefaultGatewayChannel(GatewayContainer gatewayContainer) {
        super(gatewayContainer);
    }

    public DefaultGatewayChannel(GatewayContainer gatewayContainer, GatewayClient gatewayClient) {
        super(gatewayContainer, gatewayClient);
    }

    @Override
    protected <Result> Result resolveResult(Class<Result> gatewayResultClass, InterfaceDefine interfaceDefine, GatewayResponse<?> gatewayResponse) {
        Object responseBody = gatewayResponse.getResponseBody();
        if (null != responseBody && responseBody.getClass().isAssignableFrom(gatewayResultClass)) {
            return (Result) responseBody;
        }
        return null;
    }

}
