package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.InterfaceDefine;
import com.ohayoyo.gateway.define.core.RequestDefine;
import com.ohayoyo.gateway.define.core.ResponseDefine;

/**
 * 内存接口定义
 */
public class MemoryInterfaceDefine implements InterfaceDefine {

    /**
     * 键值
     */
    private String key;

    /**
     * 描述
     */
    private String description;

    /**
     * 请求定义
     */
    private RequestDefine request;

    /**
     * 响应定义
     */
    private ResponseDefine response;

    /**
     * 构建一个内存接口定义
     */
    public MemoryInterfaceDefine() {
    }

    /**
     * 获取键值
     *
     * @return 返回键值
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * 设置键值
     *
     * @param key 键值
     * @return 返回接口定义
     */
    @Override
    public MemoryInterfaceDefine setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * 获取描述
     *
     * @return 返回描述
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     * @return 返回接口定义
     */
    @Override
    public MemoryInterfaceDefine setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 获取请求定义
     *
     * @return 返回请求定义
     */
    @Override
    public RequestDefine getRequest() {
        return request;
    }

    /**
     * 设置请求定义
     *
     * @param request 请求定义
     * @return 返回接口定义
     */
    @Override
    public MemoryInterfaceDefine setRequest(RequestDefine request) {
        this.request = request;
        return this;
    }

    /**
     * 获取响应定义
     *
     * @return 返回响应定义
     */
    @Override
    public ResponseDefine getResponse() {
        return response;
    }

    /**
     * 设置响应定义
     *
     * @param response 响应定义
     * @return 返回接口定义
     */
    @Override
    public MemoryInterfaceDefine setResponse(ResponseDefine response) {
        this.response = response;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryInterfaceDefine)) return false;
        MemoryInterfaceDefine that = (MemoryInterfaceDefine) o;
        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

}
