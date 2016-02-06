package com.ohayoyo.gateway.define;

import java.io.Serializable;

/**
 * 接口定义
 */
public interface InterfaceDefine extends Serializable {

    /**
     * 获取键值
     *
     * @return 返回键值
     */
    String getKey();

    /**
     * 设置键值
     *
     * @param key 键值
     * @return 返回接口定义
     */
    InterfaceDefine setKey(String key);

    /**
     * 获取描述
     *
     * @return 返回描述
     */
    String getDescription();

    /**
     * 设置描述
     *
     * @param description 描述
     * @return 返回接口定义
     */
    InterfaceDefine setDescription(String description);

    /**
     * 获取请求定义
     *
     * @return 返回请求定义
     */
    RequestDefine getRequest();

    /**
     * 设置请求定义
     *
     * @param request 请求定义
     * @return 返回接口定义
     */
    InterfaceDefine setRequest(RequestDefine request);

    /**
     * 获取响应定义
     *
     * @return 返回响应定义
     */
    ResponseDefine getResponse();

    /**
     * 设置响应定义
     *
     * @param response 响应定义
     * @return 返回接口定义
     */
    InterfaceDefine setResponse(ResponseDefine response);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
