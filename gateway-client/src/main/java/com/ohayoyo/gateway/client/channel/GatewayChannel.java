package com.ohayoyo.gateway.client.channel;

import com.ohayoyo.gateway.client.core.GatewayContext;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.exception.GatewayException;

/**
 * @author 蓝明乐
 */
public interface GatewayChannel {

    GatewayContext getGatewayContext();

    GatewayChannel setGatewayContext(GatewayContext gatewayContext);

    String channel(String interfaceDefineKey) throws GatewayException;

    String channel(String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws GatewayException;

    <Result> Result channel(Class<Result> responseType, String interfaceDefineKey) throws GatewayException;

    <Result> Result channel(Class<Result> responseType, String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws GatewayException;

}

