package com.ohayoyo.gateway.client.spring.builder;

import com.ohayoyo.gateway.client.core.GatewayContext;
import org.springframework.beans.factory.FactoryBean;

public abstract class AbstractRestfulBuilderFactory<Bean> implements FactoryBean<Bean> {

    private GatewayContext gatewayContext;

    public GatewayContext getGatewayContext() {
        return gatewayContext;
    }

    public AbstractRestfulBuilderFactory setGatewayContext(GatewayContext gatewayContext) {
        this.gatewayContext = gatewayContext;
        return this;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
