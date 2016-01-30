package com.ohayoyo.gateway.define.core;

import java.io.Serializable;
import java.util.Set;

public interface MethodDefine extends Serializable {

    //	GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE;

    String getName();

    MethodDefine setName(String name);

    Set<String> getOptions();

    MethodDefine setOptions(Set<String> options);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();


}
