package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayResponse;
import org.springframework.util.MultiValueMap;

/**
 * Restful网关响应
 *
 * @param <T> 响应实体类型
 */
public class RestfulResponse<T> implements GatewayResponse<T> {

    /**
     * HTTP响应状态代码
     */
    private Integer statusCode;

    /**
     * HTTP响应状态描述
     */
    private String reasonPhrase;

    /**
     * 响应头多值集合
     */
    private MultiValueMap<String, String> responseHeaders;

    /**
     * 响应实体
     */
    private T responseEntity;

    /**
     * 获取HTTP响应状态代码
     *
     * @return 返回HTTP响应状态代码
     */
    @Override
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * 设置HTTP响应状态代码
     *
     * @param statusCode HTTP响应状态代码
     * @return 网关响应
     */
    @Override
    public RestfulResponse<T> setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    /**
     * 获取HTTP响应状态描述
     *
     * @return 返回HTTP响应状态描述
     */
    @Override
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    /**
     * 设置HTTP响应状态描述
     *
     * @param reasonPhrase HTTP响应状态描述
     * @return 网关响应
     */
    @Override
    public RestfulResponse<T> setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
        return this;
    }

    /**
     * 获取响应头多值集合
     *
     * @return 返回响应头多值集合
     */
    @Override
    public MultiValueMap<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    /**
     * 设置响应头多值集合
     *
     * @param responseHeaders 响应头多值集合
     * @return 网关响应
     */
    @Override
    public RestfulResponse setResponseHeaders(MultiValueMap<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
        return this;
    }

    /**
     * 获取响应实体
     *
     * @return 返回响应实体
     */
    @Override
    public T getResponseEntity() {
        return responseEntity;
    }

    /**
     * 设置响应实体
     *
     * @param responseEntity 响应实体
     * @return 网关响应
     */
    @Override
    public RestfulResponse setResponseEntity(T responseEntity) {
        this.responseEntity = responseEntity;
        return this;
    }

}
