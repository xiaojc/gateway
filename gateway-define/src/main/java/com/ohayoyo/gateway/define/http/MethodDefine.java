package com.ohayoyo.gateway.define.http;

import com.ohayoyo.gateway.define.ObjectDefine;

import java.util.Set;

public interface MethodDefine extends ObjectDefine {

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

}
