package com.ohayoyo.gateway.define.container;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.define.resolver.GatewayTypeResolver;
import com.ohayoyo.gateway.define.resolver.SimpleGatewayTypeResolver;

public abstract class AbstractGatewayContainer<Interface extends GatewayInterface> implements GatewayContainer<Interface> {

    private static final GatewayTypeResolver GATEWAY_TYPE_RESOLVER = new SimpleGatewayTypeResolver();

    @Override
    public GatewayTypeResolver getTypeResolver() {
        return GATEWAY_TYPE_RESOLVER;
    }

}
