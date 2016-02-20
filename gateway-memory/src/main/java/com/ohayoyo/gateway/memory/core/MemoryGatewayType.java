package com.ohayoyo.gateway.memory.core;

import com.ohayoyo.gateway.define.core.GatewayType;

public class MemoryGatewayType implements GatewayType {

    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryGatewayType)) return false;
        MemoryGatewayType that = (MemoryGatewayType) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
