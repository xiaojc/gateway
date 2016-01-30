package com.ohayoyo.gateway.define.core;

import java.io.Serializable;
import java.util.Set;

public interface ProtocolDefine extends Serializable {

    String HTTP = "http";

    String HTTPS = "https";

    String getName();

    ProtocolDefine setName(String name);

    Set<String> getOptions();

    ProtocolDefine setOptions(Set<String> options);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
