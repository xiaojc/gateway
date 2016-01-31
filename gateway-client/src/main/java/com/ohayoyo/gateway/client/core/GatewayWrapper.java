package com.ohayoyo.gateway.client.core;

import java.io.IOException;

public interface GatewayWrapper<T> extends GatewayComponent<GatewayWrapper<T>> {

    GatewayResponse<T> wrapGatewayResponse() throws IOException;

}
