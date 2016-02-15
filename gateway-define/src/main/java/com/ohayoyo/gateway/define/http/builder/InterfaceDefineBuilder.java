package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.http.InterfaceDefine;
import com.ohayoyo.gateway.define.http.RequestDefine;
import com.ohayoyo.gateway.define.http.ResponseDefine;

public abstract class InterfaceDefineBuilder implements DefineBuilder<InterfaceDefine> {

    protected String key;

    protected String description;

    protected RequestDefine request;

    protected ResponseDefine response;

    public InterfaceDefineBuilder key(String key) {
        this.key = key;
        return this;
    }

    public InterfaceDefineBuilder description(String description) {
        this.description = description;
        return this;
    }

    public InterfaceDefineBuilder request(RequestDefine request) {
        this.request = request;
        return this;
    }

    public InterfaceDefineBuilder response(ResponseDefine response) {
        this.response = response;
        return this;
    }

}
