package com.ohayoyo.gateway.memory;

import com.ohayoyo.gateway.define.GatewayObject;

import java.util.Set;

public class MemoryGatewayObject implements GatewayObject<MemoryGatewayField> {

    private Set<MemoryGatewayField> fields;

    @Override
    public Set<MemoryGatewayField> getFields() {
        return fields;
    }

    @Override
    public void setFields(Set<MemoryGatewayField> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryGatewayObject)) return false;
        MemoryGatewayObject that = (MemoryGatewayObject) o;
        return fields != null ? fields.equals(that.fields) : that.fields == null;
    }

    @Override
    public int hashCode() {
        return fields != null ? fields.hashCode() : 0;
    }

}
