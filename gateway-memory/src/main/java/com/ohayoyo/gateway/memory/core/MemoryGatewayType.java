package com.ohayoyo.gateway.memory.core;

import com.ohayoyo.gateway.define.core.GatewayType;
import com.ohayoyo.gateway.define.resolver.GatewayTypeResolver;

public class MemoryGatewayType implements GatewayType {

    public static final MemoryGatewayType STRING_MEMORY_GATEWAY_TYPE = new MemoryGatewayType();

    static {
        STRING_MEMORY_GATEWAY_TYPE.setName(GatewayTypeResolver.STRING);
    }

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
