package com.ohayoyo.gateway.define.http;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public interface GatewayMethod {

    String GET_NAME = "GET";

    String HEAD_NAME = "HEAD";

    String POST_NAME = "POST";

    String PUT_NAME = "PUT";

    String PATCH_NAME = "PATCH";

    String DELETE_NAME = "DELETE";

    String OPTIONS_NAME = "OPTIONS";

    String TRACE_NAME = "TRACE";

    String getName();

    void setName(String name);

    Set<String> getScopes();

    void setScopes(Set<String> scopes);

}
