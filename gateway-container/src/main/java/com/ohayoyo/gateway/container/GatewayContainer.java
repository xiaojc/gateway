package com.ohayoyo.gateway.container;

public interface GatewayContainer {

    GatewayContainer initialize();

    GatewayContainer destroy();

    GatewayQuery createGatewayQuery();

}
