package com.ohayoyo.gateway.channel;

import com.ohayoyo.gateway.client.GatewayClient;
import com.ohayoyo.gateway.container.GatewayContainer;

public interface GatewayChannel {

    GatewayContainer getGatewayContainer();

    GatewayChannel setGatewayContainer(GatewayContainer gatewayContainer);

    GatewayClient getGatewayClient();

    GatewayChannel setGatewayClient(GatewayClient gatewayClient);

    <Result> Result channel(Class<Result> resultClass, String key, GatewayWrapper gatewayWrapper) throws Exception;

}
