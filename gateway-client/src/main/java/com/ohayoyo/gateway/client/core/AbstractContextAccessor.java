package com.ohayoyo.gateway.client.core;

/**
 * @author 蓝明乐
 */
public abstract class AbstractContextAccessor implements GatewayContextAccessor {

    private GatewayContext gatewayContext;

    @Override
    public GatewayContext getGatewayContext() {
        return gatewayContext;
    }

    @Override
    public void setGatewayContext(GatewayContext gatewayContext) {
        this.gatewayContext = gatewayContext;
    }

}
