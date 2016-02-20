package com.ohayoyo.gateway.define.container;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.define.resolver.GatewayTypeResolver;

/**
 * @author 蓝明乐
 */
public interface GatewayContainer<Interface extends GatewayInterface> {

    Interface query(String key);

    void save(Interface anInterface);

    void remove(Interface anInterface);

    GatewayTypeResolver getTypeResolver();

}
