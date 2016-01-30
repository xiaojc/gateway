package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.define.core.InterfaceDefine;
import com.ohayoyo.gateway.define.core.RequestDefine;
import com.ohayoyo.gateway.define.core.ResponseDefine;

public class RestfulDefine implements InterfaceDefine, GatewayDefine {

    private InterfaceDefine delegate;

    public RestfulDefine(InterfaceDefine delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getKey() {
        return delegate.getKey();
    }

    @Override
    public InterfaceDefine setKey(String key) {
        return delegate.setKey(key);
    }

    @Override
    public String getDescription() {
        return delegate.getDescription();
    }

    @Override
    public InterfaceDefine setDescription(String description) {
        return delegate.setDescription(description);
    }

    @Override
    public RequestDefine getRequest() {
        return delegate.getRequest();
    }

    @Override
    public InterfaceDefine setRequest(RequestDefine request) {
        return delegate.setRequest(request);
    }

    @Override
    public ResponseDefine getResponse() {
        return delegate.getResponse();
    }

    @Override
    public InterfaceDefine setResponse(ResponseDefine response) {
        return delegate.setResponse(response);
    }

    @Override
    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

}
