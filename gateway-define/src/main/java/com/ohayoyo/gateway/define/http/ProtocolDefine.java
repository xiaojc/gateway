package com.ohayoyo.gateway.define.http;

import com.ohayoyo.gateway.define.ObjectDefine;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public interface ProtocolDefine extends ObjectDefine {

    String HTTP_NAME = "http";

    String HTTPS_NAME = "https";

    String getName();

    ProtocolDefine setName(String name);

    Set<String> getScopes();

    ProtocolDefine setScopes(Set<String> scopes);

}
