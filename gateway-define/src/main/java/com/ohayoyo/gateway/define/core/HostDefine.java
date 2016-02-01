package com.ohayoyo.gateway.define.core;

import java.io.Serializable;

/**
 * 主机定义
 */
public interface HostDefine extends Serializable {

    Integer DEFAULT_HTTP_PORT = 80;

    Integer DEFAULT_HTTPS_PORT = 443;

    String getHostname();

    HostDefine setHostname(String hostname);

    Integer getPort();

    HostDefine setPort(Integer port);

    String getOption();

    HostDefine setOption(String option);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
