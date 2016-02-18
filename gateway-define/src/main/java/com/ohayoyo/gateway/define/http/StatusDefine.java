package com.ohayoyo.gateway.define.http;

import com.ohayoyo.gateway.define.core.ObjectDefine;

/**
 * @author 蓝明乐
 */
public interface StatusDefine extends ObjectDefine {

    Integer getStatusCode();

    StatusDefine setStatusCode(Integer statusCode);

    String getReasonPhrase();

    StatusDefine setReasonPhrase(String reasonPhrase);

}
