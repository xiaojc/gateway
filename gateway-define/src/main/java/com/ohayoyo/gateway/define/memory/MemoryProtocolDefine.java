package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.ProtocolDefine;

import java.util.Set;

public class MemoryProtocolDefine implements ProtocolDefine {

    public static final MemoryProtocolDefine HTTP = new MemoryProtocolDefine().setName("http");

    public static final MemoryProtocolDefine HTTPS = new MemoryProtocolDefine().setName("https");

    private String name;

    private Set<String> options;

    public MemoryProtocolDefine() {
    }

    public String getName() {
        return name;
    }

    public MemoryProtocolDefine setName(String name) {
        this.name = name;
        return this;
    }

    public Set<String> getOptions() {
        return options;
    }

    public MemoryProtocolDefine setOptions(Set<String> options) {
        this.options = options;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryProtocolDefine)) return false;
        MemoryProtocolDefine that = (MemoryProtocolDefine) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
