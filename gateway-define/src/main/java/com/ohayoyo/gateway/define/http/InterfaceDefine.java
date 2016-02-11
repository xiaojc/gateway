package com.ohayoyo.gateway.define.http;

import com.ohayoyo.gateway.define.ObjectDefine;

public interface InterfaceDefine extends ObjectDefine {

    String getKey();

    InterfaceDefine setKey(String key);

    String getDescription();

    InterfaceDefine setDescription(String description);

    RequestDefine getRequest();

    InterfaceDefine setRequest(RequestDefine request);

    ResponseDefine getResponse();

    InterfaceDefine setResponse(ResponseDefine response);

}
