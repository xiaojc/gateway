package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.define.core.InterfaceDefine;
import com.ohayoyo.gateway.define.core.RequestDefine;
import com.ohayoyo.gateway.define.core.ResponseDefine;

/**
 * Restful网关定义
 */
public class RestfulDefine implements InterfaceDefine, GatewayDefine {

    /**
     * 委托的接口定义
     */
    private InterfaceDefine delegate;

    /**
     * 构建一个指定接口定义委托的Restful网关定义
     *
     * @param delegate 委托的接口定义
     */
    public RestfulDefine(InterfaceDefine delegate) {
        this.delegate = delegate;
    }

    /**
     * 获取键值
     *
     * @return 返回键值
     */
    @Override
    public String getKey() {
        return delegate.getKey();
    }

    /**
     * 设置键值
     *
     * @param key 键值
     * @return 返回接口定义
     */
    @Override
    public InterfaceDefine setKey(String key) {
        return delegate.setKey(key);
    }

    /**
     * 获取描述
     *
     * @return 返回描述
     */
    @Override
    public String getDescription() {
        return delegate.getDescription();
    }

    /**
     * 设置描述
     *
     * @param description 描述
     * @return 返回接口定义
     */
    @Override
    public InterfaceDefine setDescription(String description) {
        return delegate.setDescription(description);
    }

    /**
     * 获取请求定义
     *
     * @return 返回请求定义
     */
    @Override
    public RequestDefine getRequest() {
        return delegate.getRequest();
    }

    /**
     * 设置请求定义
     *
     * @param request 请求定义
     * @return 返回接口定义
     */
    @Override
    public InterfaceDefine setRequest(RequestDefine request) {
        return delegate.setRequest(request);
    }

    /**
     * 获取响应定义
     *
     * @return 返回响应定义
     */
    @Override
    public ResponseDefine getResponse() {
        return delegate.getResponse();
    }

    /**
     * 设置响应定义
     *
     * @param response 响应定义
     * @return 返回接口定义
     */
    @Override
    public InterfaceDefine setResponse(ResponseDefine response) {
        return delegate.setResponse(response);
    }

    @Override
    public boolean equals(Object o) {
        return delegate.equals(o);
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }
}
