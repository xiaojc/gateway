package com.ohayoyo.gateway.define.http;

/**
 * @author 蓝明乐
 */
public interface GatewayHost {

    Integer DEFAULT_PORT_VALUE = -1;

    Integer DEFAULT_HTTP_PORT = 80;

    Integer DEFAULT_HTTPS_PORT = 443;

    String getHostname();

    void setHostname(String hostname);

    Integer getPort();

    void setPort(Integer port);

    String getScope();

    void setScope(String scope);

}
