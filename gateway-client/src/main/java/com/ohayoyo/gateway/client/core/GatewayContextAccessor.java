package com.ohayoyo.gateway.client.core;

/**
 * @author 蓝明乐
 */
public interface GatewayContextAccessor {

    GatewayContext getGatewayContext();

    void setGatewayContext(GatewayContext gatewayContext);

}
