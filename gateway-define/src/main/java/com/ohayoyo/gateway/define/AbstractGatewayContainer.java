package com.ohayoyo.gateway.define;

public abstract class AbstractGatewayContainer<Interface extends GatewayInterface> implements GatewayContainer<Interface> {

    private static final GatewayTypeResolver GATEWAY_TYPE_RESOLVER = new SimpleGatewayTypeResolver();

    @Override
    public GatewayTypeResolver getGatewayTypeResolver() {
        return GATEWAY_TYPE_RESOLVER;
    }

}
