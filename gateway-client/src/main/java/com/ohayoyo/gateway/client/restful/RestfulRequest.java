package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayRequest;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * Restful网关请求
 */
public class RestfulRequest implements GatewayRequest {

    /**
     * 选择定义的作用域
     */
    private String select;

    /**
     * 请求路径变量集合
     */
    private Map<String, String> requestPathVariables;

    /**
     * 请求参数多值集合
     * <p>
     * 暂时不支持
     * </p>
     */
    @Deprecated
    private MultiValueMap<String, String> requestParameters;

    /**
     * 请求查询多值集合
     */
    private MultiValueMap<String, String> requestQueries;

    /**
     * 请求头多值集合
     */
    private MultiValueMap<String, String> requestHeaders;

    /**
     * 请求实体
     */
    private Object requestEntity;

    /**
     * 获取选择定义的作用域
     *
     * @return 返回选择定义的作用域
     */
    @Override
    public String getSelect() {
        return select;
    }

    /**
     * 设置选择定义的作用域
     *
     * @param select 选择定义的作用域
     * @return 返回网关请求
     */
    @Override
    public RestfulRequest setSelect(String select) {
        this.select = select;
        return this;
    }

    /**
     * 获取请求路径变量集合
     *
     * @return 返回请求路径变量集合
     */
    @Override
    public Map<String, String> getRequestPathVariables() {
        return requestPathVariables;
    }

    /**
     * 设置请求路径变量集合
     *
     * @param requestPathVariables 请求路径变量集合
     * @return 返回网关请求
     */
    @Override
    public RestfulRequest setRequestPathVariables(Map<String, String> requestPathVariables) {
        this.requestPathVariables = requestPathVariables;
        return this;
    }

    /**
     * 获取请求参数多值集合
     * <p>
     * 暂时不支持
     * </p>
     *
     * @return 返回请求参数多值集合
     */
    @Override
    @Deprecated
    public MultiValueMap<String, String> getRequestParameters() {
        return requestParameters;
    }

    /**
     * 设置请求参数多值集合
     * <p>
     * 暂时不支持
     * </p>
     *
     * @param requestParameters 请求参数多值集合
     * @return 返回网关请求
     */
    @Override
    @Deprecated
    public RestfulRequest setRequestParameters(MultiValueMap<String, String> requestParameters) {
        this.requestParameters = requestParameters;
        return this;
    }

    /**
     * 获取请求查询多值集合
     *
     * @return 返回请求查询多值集合
     */
    @Override
    public MultiValueMap<String, String> getRequestQueries() {
        return requestQueries;
    }

    /**
     * 设置请求查询多值集合
     *
     * @param requestQueries 请求查询多值集合
     * @return 返回网关请求
     */
    @Override
    public RestfulRequest setRequestQueries(MultiValueMap<String, String> requestQueries) {
        this.requestQueries = requestQueries;
        return this;
    }

    /**
     * 获取请求头多值集合
     *
     * @return 返回请求头多值集合
     */
    @Override
    public MultiValueMap<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    /**
     * 设置请求头多值集合
     *
     * @param requestHeaders 请求头多值集合
     * @return 返回网关请求
     */
    @Override
    public RestfulRequest setRequestHeaders(MultiValueMap<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
        return this;
    }

    /**
     * 获取请求实体
     *
     * @return 返回请求实体
     */
    @Override
    public Object getRequestEntity() {
        return requestEntity;
    }

    /**
     * 设置请求实体
     *
     * @param requestEntity 请求实体
     * @return 返回网关请求
     */
    @Override
    public RestfulRequest setRequestEntity(Object requestEntity) {
        this.requestEntity = requestEntity;
        return this;
    }

}
