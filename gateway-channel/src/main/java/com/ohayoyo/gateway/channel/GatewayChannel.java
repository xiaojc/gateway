package com.ohayoyo.gateway.channel;

import com.ohayoyo.gateway.client.GatewayClient;
import com.ohayoyo.gateway.client.GatewayRequest;
import com.ohayoyo.gateway.client.restful.RestfulGatewayRequestBuilder;
import com.ohayoyo.gateway.container.GatewayContainer;

/**
 * @author 蓝明乐
 */
public interface GatewayChannel {

    GatewayContainer getGatewayContainer();

    GatewayChannel setGatewayContainer(GatewayContainer gatewayContainer);

    GatewayClient getGatewayClient();

    GatewayChannel setGatewayClient(GatewayClient gatewayClient);

    RestfulGatewayRequestBuilder newRestfulGatewayRequestBuilder();

    <Result> Result channel(Class<Result> gatewayResultClass, String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws Exception;

}

