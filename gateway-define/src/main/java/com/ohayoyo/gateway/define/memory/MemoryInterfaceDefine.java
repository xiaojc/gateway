package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.InterfaceDefine;
import com.ohayoyo.gateway.define.core.RequestDefine;
import com.ohayoyo.gateway.define.core.ResponseDefine;

public class MemoryInterfaceDefine implements InterfaceDefine {

    private String key;

    private String description;

    private RequestDefine request;

    private ResponseDefine response;

    public MemoryInterfaceDefine() {
    }

    public String getKey() {
        return key;
    }

    public MemoryInterfaceDefine setKey(String key) {
        this.key = key;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MemoryInterfaceDefine setDescription(String description) {
        this.description = description;
        return this;
    }

    public RequestDefine getRequest() {
        return request;
    }

    public MemoryInterfaceDefine setRequest(RequestDefine request) {
        this.request = request;
        return this;
    }

    public ResponseDefine getResponse() {
        return response;
    }

    public MemoryInterfaceDefine setResponse(ResponseDefine response) {
        this.response = response;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryInterfaceDefine)) return false;
        MemoryInterfaceDefine that = (MemoryInterfaceDefine) o;
        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

}
