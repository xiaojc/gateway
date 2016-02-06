package com.ohayoyo.gateway.define;

import java.io.Serializable;
import java.util.Set;

public interface ProtocolDefine extends Serializable {

    String HTTP_NAME = "http";

    String HTTPS_NAME = "https";

    String getName();

    ProtocolDefine setName(String name);

    Set<String> getScopes();

    ProtocolDefine setScopes(Set<String> scopes);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
