package com.ohayoyo.gateway.define;

import com.ohayoyo.gateway.define.GatewayInterface;
import com.ohayoyo.gateway.define.GatewayTypeResolver;

/**
 * @author 蓝明乐
 */
public interface GatewayContainer<Interface extends GatewayInterface> {

    Interface query(String key);

    void save(Interface anInterface);

    void remove(Interface anInterface);

    GatewayTypeResolver getGatewayTypeResolver();

}
