package com.ohayoyo.gateway.memory;

import com.ohayoyo.gateway.define.GatewayInterface;

public class MemoryGatewayInterface implements GatewayInterface<MemoryGatewayRequest, MemoryGatewayResponse> {

    private String key;

    private MemoryGatewayRequest request;

    private MemoryGatewayResponse response;

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public MemoryGatewayRequest getRequest() {
        return request;
    }

    @Override
    public void setRequest(MemoryGatewayRequest request) {
        this.request = request;
    }

    @Override
    public MemoryGatewayResponse getResponse() {
        return response;
    }

    @Override
    public void setResponse(MemoryGatewayResponse response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryGatewayInterface)) return false;
        MemoryGatewayInterface that = (MemoryGatewayInterface) o;
        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

}
