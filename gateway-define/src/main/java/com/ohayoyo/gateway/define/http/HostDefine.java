package com.ohayoyo.gateway.define.http;

import com.ohayoyo.gateway.define.ObjectDefine;

public interface HostDefine extends ObjectDefine {

    Integer DEFAULT_HTTP_PORT = 80;

    Integer DEFAULT_HTTPS_PORT = 443;

    String getHostname();

    HostDefine setHostname(String hostname);

    Integer getPort();

    HostDefine setPort(Integer port);

    String getScope();

    HostDefine setScope(String scope);

}
