package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.define.core.RequestDefine;
import com.ohayoyo.gateway.define.core.ResponseDefine;

/**
 * 网关定义
 */
public interface GatewayDefine {

    /**
     * 获取请求定义
     *
     * @return 返回请求定义
     */
    RequestDefine getRequest();

    /**
     * 获取响应定义
     *
     * @return 返回响应定义
     */
    ResponseDefine getResponse();

}
