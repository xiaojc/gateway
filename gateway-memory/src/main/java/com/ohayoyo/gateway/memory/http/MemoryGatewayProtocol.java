package com.ohayoyo.gateway.memory.http;

import com.ohayoyo.gateway.define.http.GatewayProtocol;

import java.util.Set;

public class MemoryGatewayProtocol implements GatewayProtocol {

    private String name;

    private Set<String> scopes;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<String> getScopes() {
        return scopes;
    }

    @Override
    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryGatewayProtocol)) return false;
        MemoryGatewayProtocol that = (MemoryGatewayProtocol) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
