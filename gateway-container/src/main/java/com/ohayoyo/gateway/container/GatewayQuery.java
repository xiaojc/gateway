package com.ohayoyo.gateway.container;

import com.ohayoyo.gateway.define.http.InterfaceDefine;

public interface GatewayQuery {

    GatewayContainer getGatewayContainer();

    <Parameter> InterfaceDefine query(GatewayAction<Parameter> action);

}
