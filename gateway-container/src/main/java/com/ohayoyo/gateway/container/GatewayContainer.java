package com.ohayoyo.gateway.container;

import com.ohayoyo.gateway.define.http.InterfaceDefine;

/**
 * @author 蓝明乐
 */
public interface GatewayContainer {

    InterfaceDefine query(String interfaceDefineKey);

    GatewayContainer add(InterfaceDefine interfaceDefine);

}
