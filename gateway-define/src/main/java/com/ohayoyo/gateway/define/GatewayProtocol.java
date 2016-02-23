package com.ohayoyo.gateway.define;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public interface GatewayProtocol {

    String HTTP_NAME = "http";

    String HTTPS_NAME = "https";

    String getName();

    void setName(String name);

    Set<String> getScopes();

    void setScopes(Set<String> scopes);

}
