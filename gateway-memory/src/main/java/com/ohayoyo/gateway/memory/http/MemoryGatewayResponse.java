package com.ohayoyo.gateway.memory.http;

import com.ohayoyo.gateway.define.http.GatewayResponse;

import java.util.Set;

public class MemoryGatewayResponse implements GatewayResponse<MemoryGatewayStatus, MemoryGatewayHeaders, MemoryGatewayEntity> {

    private Set<MemoryGatewayStatus> statuses;

    private MemoryGatewayHeaders headers;

    private MemoryGatewayEntity entity;

    @Override
    public Set<MemoryGatewayStatus> getStatuses() {
        return statuses;
    }

    @Override
    public void setStatuses(Set<MemoryGatewayStatus> statuses) {
        this.statuses = statuses;
    }

    @Override
    public MemoryGatewayHeaders getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(MemoryGatewayHeaders headers) {
        this.headers = headers;
    }

    @Override
    public MemoryGatewayEntity getEntity() {
        return entity;
    }

    @Override
    public void setEntity(MemoryGatewayEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryGatewayResponse)) return false;
        MemoryGatewayResponse that = (MemoryGatewayResponse) o;
        if (statuses != null ? !statuses.equals(that.statuses) : that.statuses != null) return false;
        if (headers != null ? !headers.equals(that.headers) : that.headers != null) return false;
        return entity != null ? entity.equals(that.entity) : that.entity == null;
    }

    @Override
    public int hashCode() {
        int result = statuses != null ? statuses.hashCode() : 0;
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        return result;
    }

}
