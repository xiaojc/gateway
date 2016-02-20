package com.ohayoyo.gateway.define.support;

import com.ohayoyo.gateway.define.http.GatewayInterface;

public interface AutoScanGatewayContainer<Interface extends GatewayInterface> extends ConfigurableGatewayContainer<Interface> {

    void autoScanGatewayInterfaces();

}
