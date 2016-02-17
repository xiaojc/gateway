package com.ohayoyo.gateway.define.core;

import com.ohayoyo.gateway.define.ObjectDefine;

/**
 * @author 蓝明乐
 */
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
