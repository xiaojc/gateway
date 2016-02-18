package com.ohayoyo.gateway.client.channel;

import com.ohayoyo.gateway.client.core.GatewayClient;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.define.container.GatewayContainer;

/**
 * @author 蓝明乐
 */
public interface GatewayChannel {

    GatewayContainer getGatewayContainer();

    GatewayChannel setGatewayContainer(GatewayContainer gatewayContainer);

    GatewayClient getGatewayClient();

    GatewayChannel setGatewayClient(GatewayClient gatewayClient);

    String channel(String interfaceDefineKey) throws GatewayException;

    String channel(String interfaceDefineKey,GatewayRequest<Object> gatewayRequest) throws GatewayException;

    <Result> Result channel(Class<Result> responseType, String interfaceDefineKey) throws GatewayException;

    <Result> Result channel(Class<Result> responseType, String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws GatewayException;

}

