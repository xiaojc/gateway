package com.ohayoyo.gateway.client.components;

import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayResponse;
import com.ohayoyo.gateway.client.core.GatewayWrapper;
import com.ohayoyo.gateway.client.restful.RestfulResponse;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * 网关实体包装器组件
 *
 * @param <T> 响应实体类型
 */
public class GatewayWrapperEntity<T> extends AbstractGatewayComponent<GatewayWrapper<T>> implements GatewayWrapper<T> {

    /**
     * 响应实体类型
     */
    private Class<T> responseClass;

    /**
     * 响应实体结果
     */
    private T responseResult;

    /**
     * HTTP请求客户端
     */
    private ClientHttpRequest clientHttpRequest;

    /**
     * HTTP响应客户端
     */
    private ClientHttpResponse clientHttpResponse;

    /**
     * 构建一个网关实体包装器
     *
     * @param responseClass      响应实体类型
     * @param responseResult     响应实体结果
     * @param clientHttpRequest  HTTP请求客户端
     * @param clientHttpResponse HTTP响应客户端
     */
    public GatewayWrapperEntity(Class<T> responseClass, T responseResult, ClientHttpRequest clientHttpRequest, ClientHttpResponse clientHttpResponse) {
        this.responseClass = responseClass;
        this.responseResult = responseResult;
        this.clientHttpRequest = clientHttpRequest;
        this.clientHttpResponse = clientHttpResponse;
    }

    /**
     * 包装网关响应
     *
     * @return 返回网关响应
     * @throws GatewayException 抛出网关异常
     */
    @Override
    public GatewayResponse<T> wrapGatewayResponse() throws GatewayException {
        RestfulResponse<T> restfulResponse;
        try {
            restfulResponse = new RestfulResponse<T>();
            restfulResponse.setResponseEntity(this.getResponseResult());
            restfulResponse.setStatusCode(clientHttpResponse.getRawStatusCode());
            restfulResponse.setReasonPhrase(clientHttpResponse.getStatusText());
            restfulResponse.setResponseHeaders(clientHttpResponse.getHeaders());
        } catch (IOException e) {
            throw new GatewayException(e);
        }
        return restfulResponse;
    }

    /**
     * 获取网关实体包装器组件
     *
     * @return 返回网关实体包装器组件
     * @throws GatewayException 抛出网关异常
     */
    @Override
    public GatewayWrapper<T> getComponent() throws GatewayException {
        return this;
    }

    /**
     * 获取响应实体类型
     *
     * @return 返回响应实体类型
     */
    public Class<T> getResponseClass() {
        return responseClass;
    }

    /**
     * 设置响应实体类型
     *
     * @param responseClass 响应实体类型
     * @return 返回网关实体包装器
     */
    public GatewayWrapperEntity setResponseClass(Class<T> responseClass) {
        this.responseClass = responseClass;
        return this;
    }

    /**
     * 获取响应实体结果
     *
     * @return 返回响应实体结果
     */
    public T getResponseResult() {
        return responseResult;
    }

    /**
     * 设置响应实体结果
     *
     * @param responseResult 响应实体结果
     * @return 返回网关实体包装器
     */
    public GatewayWrapperEntity setResponseResult(T responseResult) {
        this.responseResult = responseResult;
        return this;
    }

    /**
     * 获取HTTP请求客户端
     *
     * @return 返回HTTP请求客户端
     */
    public ClientHttpRequest getClientHttpRequest() {
        return clientHttpRequest;
    }

    /**
     * 设置HTTP请求客户端
     *
     * @param clientHttpRequest HTTP请求客户端
     * @return 返回网关实体包装器
     */
    public GatewayWrapperEntity setClientHttpRequest(ClientHttpRequest clientHttpRequest) {
        this.clientHttpRequest = clientHttpRequest;
        return this;
    }

    /**
     * 获取HTTP响应客户端
     *
     * @return 返回HTTP响应客户端
     */
    public ClientHttpResponse getClientHttpResponse() {
        return clientHttpResponse;
    }

    /**
     * 设置HTTP响应客户端
     *
     * @param clientHttpResponse HTTP响应客户端
     * @return 返回网关实体包装器
     */
    public GatewayWrapperEntity setClientHttpResponse(ClientHttpResponse clientHttpResponse) {
        this.clientHttpResponse = clientHttpResponse;
        return this;
    }

}
