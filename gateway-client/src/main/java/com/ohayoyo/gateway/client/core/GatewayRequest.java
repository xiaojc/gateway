package com.ohayoyo.gateway.client.core;

import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * 网关请求
 */
public interface GatewayRequest {

    /**
     * 获取选择定义的作用域
     *
     * @return 返回选择定义的作用域
     */
    String getSelect();

    /**
     * 设置选择定义的作用域
     *
     * @param select 选择定义的作用域
     * @return 返回网关请求
     */
    GatewayRequest setSelect(String select);

    /**
     * 获取请求路径变量集合
     *
     * @return 返回请求路径变量集合
     */
    Map<String, String> getRequestPathVariables();

    /**
     * 设置请求路径变量集合
     *
     * @param requestPathVariables 请求路径变量集合
     * @return 返回网关请求
     */
    GatewayRequest setRequestPathVariables(Map<String, String> requestPathVariables);

    /**
     * 获取请求参数多值集合
     * <p>
     * 暂时不支持
     * </p>
     *
     * @return 返回请求参数多值集合
     */
    @Deprecated
    MultiValueMap<String, String> getRequestParameters();

    /**
     * 设置请求参数多值集合
     * <p>
     * 暂时不支持
     * </p>
     *
     * @param requestParameters 请求参数多值集合
     * @return 返回网关请求
     */
    @Deprecated
    GatewayRequest setRequestParameters(MultiValueMap<String, String> requestParameters);

    /**
     * 获取请求查询多值集合
     *
     * @return 返回请求查询多值集合
     */
    MultiValueMap<String, String> getRequestQueries();

    /**
     * 设置请求查询多值集合
     *
     * @param requestQueries 请求查询多值集合
     * @return 返回网关请求
     */
    GatewayRequest setRequestQueries(MultiValueMap<String, String> requestQueries);

    /**
     * 获取请求头多值集合
     *
     * @return 返回请求头多值集合
     */
    MultiValueMap<String, String> getRequestHeaders();

    /**
     * 设置请求头多值集合
     *
     * @param requestHeaders 请求头多值集合
     * @return 返回网关请求
     */
    GatewayRequest setRequestHeaders(MultiValueMap<String, String> requestHeaders);

    /**
     * 获取请求实体
     *
     * @return 返回请求实体
     */
    Object getRequestEntity();

    /**
     * 设置请求实体
     *
     * @param requestEntity 请求实体
     * @return 返回网关请求
     */
    GatewayRequest setRequestEntity(Object requestEntity);

}
