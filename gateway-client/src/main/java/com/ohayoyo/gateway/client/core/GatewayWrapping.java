package com.ohayoyo.gateway.client.core;

public interface GatewayWrapping {

    GatewayResponse wrap(GatewayEntity gatewayEntity, GatewayConfig gatewayConfig);

}
