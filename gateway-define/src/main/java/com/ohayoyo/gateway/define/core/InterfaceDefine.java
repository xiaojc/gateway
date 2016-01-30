package com.ohayoyo.gateway.define.core;

import java.io.Serializable;

public interface InterfaceDefine extends Serializable {

    String getKey();

    InterfaceDefine setKey(String key);

    String getDescription();

    InterfaceDefine setDescription(String description);

    RequestDefine getRequest();

    InterfaceDefine setRequest(RequestDefine request);

    ResponseDefine getResponse();

    InterfaceDefine setResponse(ResponseDefine response);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
