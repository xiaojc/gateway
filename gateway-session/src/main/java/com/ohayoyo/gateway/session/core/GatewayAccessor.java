package com.ohayoyo.gateway.session.core;

/**
 * @author 蓝明乐
 */
public interface GatewayAccessor {

    GatewayContext getGatewayContext();

    void setGatewayContext(GatewayContext gatewayContext);

}
