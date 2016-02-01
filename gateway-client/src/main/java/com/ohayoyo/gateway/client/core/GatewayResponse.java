package com.ohayoyo.gateway.client.core;

import org.springframework.util.MultiValueMap;

/**
 * 网关响应
 *
 * @param <T> 响应实体类型
 */
public interface GatewayResponse<T> {

    /**
     * 获取HTTP响应状态代码
     *
     * @return 返回HTTP响应状态代码
     */
    Integer getStatusCode();

    /**
     * 设置HTTP响应状态代码
     *
     * @param statusCode HTTP响应状态代码
     * @return 网关响应
     */
    GatewayResponse<T> setStatusCode(Integer statusCode);

    /**
     * 获取HTTP响应状态描述
     *
     * @return 返回HTTP响应状态描述
     */
    String getReasonPhrase();

    /**
     * 设置HTTP响应状态描述
     *
     * @param reasonPhrase HTTP响应状态描述
     * @return 网关响应
     */
    GatewayResponse<T> setReasonPhrase(String reasonPhrase);

    /**
     * 获取响应头多值集合
     *
     * @return 返回响应头多值集合
     */
    MultiValueMap<String, String> getResponseHeaders();

    /**
     * 设置响应头多值集合
     *
     * @param responseHeaders 响应头多值集合
     * @return 网关响应
     */
    GatewayResponse<T> setResponseHeaders(MultiValueMap<String, String> responseHeaders);

    /**
     * 获取响应实体
     *
     * @return 返回响应实体
     */
    T getResponseEntity();

    /**
     * 设置响应实体
     *
     * @param responseEntity 响应实体
     * @return 网关响应
     */
    GatewayResponse<T> setResponseEntity(T responseEntity);

}
