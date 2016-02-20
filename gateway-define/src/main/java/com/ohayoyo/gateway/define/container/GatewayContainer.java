package com.ohayoyo.gateway.define.container;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.define.resolver.GatewayTypeResolver;

/**
 * @author 蓝明乐
 */
public interface GatewayContainer<Interfaces extends GatewayInterface> {

    Interfaces query(String key);

    void save(Interfaces interfaces);

    void remove(Interfaces interfaces);

    GatewayTypeResolver getGatewayTypeResolver();

}
