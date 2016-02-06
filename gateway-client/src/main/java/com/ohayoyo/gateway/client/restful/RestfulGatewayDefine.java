package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.GatewayDefine;
import com.ohayoyo.gateway.define.InterfaceDefine;
import com.ohayoyo.gateway.define.RequestDefine;
import com.ohayoyo.gateway.define.ResponseDefine;

public class RestfulGatewayDefine implements InterfaceDefine, GatewayDefine {

    private InterfaceDefine delegateInterfaceDefine;

    public RestfulGatewayDefine(InterfaceDefine delegateInterfaceDefine) {
        this.delegateInterfaceDefine = delegateInterfaceDefine;
    }

    @Override
    public String getKey() {
        return delegateInterfaceDefine.getKey();
    }

    @Override
    public InterfaceDefine setKey(String key) {
        return delegateInterfaceDefine.setKey(key);
    }

    @Override
    public String getDescription() {
        return delegateInterfaceDefine.getDescription();
    }

    @Override
    public InterfaceDefine setDescription(String description) {
        return delegateInterfaceDefine.setDescription(description);
    }

    @Override
    public RequestDefine getRequest() {
        return delegateInterfaceDefine.getRequest();
    }

    @Override
    public InterfaceDefine setRequest(RequestDefine request) {
        return delegateInterfaceDefine.setRequest(request);
    }

    @Override
    public ResponseDefine getResponse() {
        return delegateInterfaceDefine.getResponse();
    }

    @Override
    public InterfaceDefine setResponse(ResponseDefine response) {
        return delegateInterfaceDefine.setResponse(response);
    }

    @Override
    public boolean equals(Object o) {
        return delegateInterfaceDefine.equals(o);
    }

    @Override
    public int hashCode() {
        return delegateInterfaceDefine.hashCode();
    }
}
