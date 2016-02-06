package com.ohayoyo.gateway.define;

import java.io.Serializable;

public interface HostDefine extends Serializable {

    Integer DEFAULT_HTTP_PORT = 80;

    Integer DEFAULT_HTTPS_PORT = 443;

    String getHostname();

    HostDefine setHostname(String hostname);

    Integer getPort();

    HostDefine setPort(Integer port);

    String getScope();

    HostDefine setScope(String scope);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
