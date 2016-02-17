package com.ohayoyo.gateway.define.memory.http;

import com.ohayoyo.gateway.define.core.InterfaceDefine;
import com.ohayoyo.gateway.define.core.RequestDefine;
import com.ohayoyo.gateway.define.core.ResponseDefine;

/**
 * @author 蓝明乐
 */
public class MemoryInterfaceDefine implements InterfaceDefine {

    private String key;

    private String description;

    private RequestDefine request;

    private ResponseDefine response;

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public MemoryInterfaceDefine setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public MemoryInterfaceDefine setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public RequestDefine getRequest() {
        return request;
    }

    @Override
    public MemoryInterfaceDefine setRequest(RequestDefine request) {
        this.request = request;
        return this;
    }

    @Override
    public ResponseDefine getResponse() {
        return response;
    }

    @Override
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
