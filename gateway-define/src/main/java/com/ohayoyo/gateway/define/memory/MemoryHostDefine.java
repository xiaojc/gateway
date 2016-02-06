package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.HostDefine;

public class MemoryHostDefine implements HostDefine {

    private String hostname;

    private Integer port;

    private String option;

    public String getHostname() {
        return hostname;
    }

    public MemoryHostDefine setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    public Integer getPort() {
        return port;
    }

    public MemoryHostDefine setPort(Integer port) {
        this.port = port;
        return this;
    }

    public String getOption() {
        return option;
    }

    public MemoryHostDefine setOption(String option) {
        this.option = option;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryHostDefine)) return false;
        MemoryHostDefine that = (MemoryHostDefine) o;
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
