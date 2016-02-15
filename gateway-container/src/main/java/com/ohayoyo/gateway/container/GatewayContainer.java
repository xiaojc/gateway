package com.ohayoyo.gateway.container;

import com.ohayoyo.gateway.define.http.InterfaceDefine;

public interface GatewayContainer {

    InterfaceDefine query(String interfaceDefineKey);

    GatewayContainer add(InterfaceDefine interfaceDefine);

}
