package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.define.core.EntityDefine;
import com.ohayoyo.gateway.define.core.ResponseDefine;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.lang.reflect.Type;

public class ResponseTypeUtil {

    public static Type resolve(GatewayConfig gatewayConfig, GatewayRequest gatewayRequest, GatewayDefine gatewayDefine, ClientHttpRequest clientHttpRequest, ClientHttpResponse clientHttpResponse) {
        Type responseType = gatewayRequest.getResponseType();
        if (null != responseType) {
            return responseType;
        }
        //这里还没有处理
        ResponseDefine responseDefine = gatewayDefine.getResponse();
        EntityDefine entityDefine = responseDefine.getEntity();
        String type = entityDefine.getType();
        return null;
    }
}
