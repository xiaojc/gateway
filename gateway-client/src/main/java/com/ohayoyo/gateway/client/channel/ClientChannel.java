package com.ohayoyo.gateway.client.channel;

import com.ohayoyo.gateway.client.core.GatewayClient;
import com.ohayoyo.gateway.client.core.GatewayResponse;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.core.InterfaceDefine;

/**
 * @author 蓝明乐
 */
public class ClientChannel extends AbstractChannel {

    public ClientChannel(GatewayContainer gatewayContainer) {
        this(gatewayContainer, GatewayClient.DEFAULT_CLIENT);
    }

    public ClientChannel(GatewayContainer gatewayContainer, GatewayClient gatewayClient) {
        super(gatewayContainer, gatewayClient);
    }

    @Override
    protected <Result> Result resolveGatewayResult(Class<Result> responseType, InterfaceDefine interfaceDefine, GatewayResponse<?> gatewayResponse) {
        Object responseBody = gatewayResponse.getResponseBody();
        if (null != responseBody && responseBody.getClass().isAssignableFrom(responseType)) {
            return (Result) responseBody;
        }
        return null;
    }

}
