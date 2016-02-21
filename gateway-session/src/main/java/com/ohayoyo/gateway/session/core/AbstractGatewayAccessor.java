package com.ohayoyo.gateway.session.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 蓝明乐
 */
public abstract class AbstractGatewayAccessor implements GatewayAccessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGatewayAccessor.class);

    private GatewayContext gatewayContext;

    public GatewayContext getGatewayContext() {
        return gatewayContext;
    }

    public void setGatewayContext(GatewayContext gatewayContext) {
        this.gatewayContext = gatewayContext;
    }

}
