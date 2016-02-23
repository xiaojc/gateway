package com.ohayoyo.gateway.memory;

import com.ohayoyo.gateway.define.GatewayHost;

public class MemoryGatewayHost implements GatewayHost {

    private String hostname;

    private Integer port = DEFAULT_PORT_VALUE;

    private String scope;

    @Override
    public String getHostname() {
        return hostname;
    }

    @Override
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    @Override
    public Integer getPort() {
        return port;
    }

    @Override
    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryGatewayHost)) return false;
        MemoryGatewayHost that = (MemoryGatewayHost) o;
        if (hostname != null ? !hostname.equals(that.hostname) : that.hostname != null) return false;
        return port != null ? port.equals(that.port) : that.port == null;
    }

    @Override
    public int hashCode() {
        int result = hostname != null ? hostname.hashCode() : 0;
        result = 31 * result + (port != null ? port.hashCode() : 0);
        return result;
    }

}
