package com.ohayoyo.gateway.define.http;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public interface GatewayRequest<Protocol extends GatewayProtocol,
        Host extends GatewayHost, Path extends GatewayPath, Queries extends GatewayQueries,
        Method extends GatewayMethod, Headers extends GatewayHeaders, Entity extends GatewayEntity> {

    Set<Protocol> getProtocols();

    void setProtocols(Set<Protocol> protocols);

    Set<Host> getHosts();

    void setHosts(Set<Host> hosts);

    Path getPath();

    void setPath(Path path);

    Queries getQueries();

    void setQueries(Queries queries);

    String getFragment();

    void setFragment(String fragment);

    Set<Method> getMethods();

    void setMethods(Set<Method> methods);

    Headers getHeaders();

    void setHeaders(Headers headers);

    Entity getEntity();

    void setEntity(Entity entity);

}
