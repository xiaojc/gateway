package com.ohayoyo.gateway.container;

public abstract class AbstractGatewayQuery implements GatewayQuery {

    private GatewayContainer gatewayContainer;

    public AbstractGatewayQuery(GatewayContainer gatewayContainer) {
        this.gatewayContainer = gatewayContainer;
    }

    @Override
    public GatewayContainer getGatewayContainer() {
        return this.gatewayContainer;
    }

}
