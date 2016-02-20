package com.ohayoyo.gateway.session.core;

/**
 * @author 蓝明乐
 */
public abstract class AbstractGatewayAccessor implements GatewayAccessor {

    private GatewayContext gatewayContext;

    public GatewayContext getGatewayContext() {
        return gatewayContext;
    }

    public void setGatewayContext(GatewayContext gatewayContext) {
        this.gatewayContext = gatewayContext;
    }

}
