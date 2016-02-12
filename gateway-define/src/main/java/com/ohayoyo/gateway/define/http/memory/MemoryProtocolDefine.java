package com.ohayoyo.gateway.define.http.memory;

import com.ohayoyo.gateway.define.http.ProtocolDefine;

import java.util.Set;

public class MemoryProtocolDefine implements ProtocolDefine {

    public static final MemoryProtocolDefine HTTP = new MemoryProtocolDefine().setName(HTTP_NAME);

    public static final MemoryProtocolDefine HTTPS = new MemoryProtocolDefine().setName(HTTPS_NAME);

    private String name;

    private Set<String> scopes;

    public String getName() {
        return name;
    }

    public MemoryProtocolDefine setName(String name) {
        this.name = name;
        return this;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public MemoryProtocolDefine setScopes(Set<String> scopes) {
        this.scopes = scopes;
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
