package com.ohayoyo.gateway.define.container;

import com.ohayoyo.gateway.define.http.InterfaceDefine;

/**
 * @author 蓝明乐
 */
public interface GatewayContainer {

    InterfaceDefine query(String interfaceDefineKey);

    GatewayContainer save(InterfaceDefine interfaceDefine);

    GatewayContainer remove(InterfaceDefine interfaceDefine);

}
