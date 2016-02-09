package com.ohayoyo.gateway.define;

import java.io.Serializable;
import java.util.Set;

public interface MethodDefine extends Serializable {

    String GET_NAME = "GET";

    String HEAD_NAME = "HEAD";

    String POST_NAME = "POST";

    String PUT_NAME = "PUT";

    String PATCH_NAME = "PATCH";

    String DELETE_NAME = "DELETE";

    String OPTIONS_NAME = "OPTIONS";

    String TRACE_NAME = "TRACE";

    String getName();

    MethodDefine setName(String name);

    Set<String> getScopes();

    MethodDefine setScopes(Set<String> scopes);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();


}
