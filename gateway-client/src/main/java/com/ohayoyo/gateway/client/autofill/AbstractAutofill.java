package com.ohayoyo.gateway.client.autofill;

import com.ohayoyo.gateway.client.core.GatewayContext;

/**
 * @author 蓝明乐
 */
public abstract class AbstractAutofill implements GatewayAutofill{

    private GatewayContext gatewayContext;

    @Override
    public GatewayContext getGatewayContext() {
        return gatewayContext;
    }

    @Override
    public AbstractAutofill setGatewayContext(GatewayContext gatewayContext) {
        this.gatewayContext = gatewayContext;
        return this;
    }

}
