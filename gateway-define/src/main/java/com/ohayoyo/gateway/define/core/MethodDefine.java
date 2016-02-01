package com.ohayoyo.gateway.define.core;

import java.io.Serializable;
import java.util.Set;

/**
 * 方法定义
 */
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

    Set<String> getOptions();

    MethodDefine setOptions(Set<String> options);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();


}
