package com.ohayoyo.gateway.client.core;

import org.springframework.http.HttpMethod;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;

import java.net.URI;

/**
 * 网关执行器
 */
public interface GatewayExecutor {

    /**
     * 使用缓存器包装
     *
     * @see org.springframework.http.client.BufferingClientHttpRequestFactory
     */
    void enableBuffering();

    /**
     * 禁用缓存器包装
     *
     * @see org.springframework.http.client.BufferingClientHttpRequestFactory
     */
    void disableBuffering();

    /**
     * 是否缓存器包装
     *
     * @return 返回是否缓存器包装
     */
    boolean isBuffering();

    /**
     * 执行器执行任务之前执行打开操作,详细看 {@link GatewayClient#session(Class, GatewayDefine, GatewayRequest)},{@link GatewayClient#session(GatewayDefine, GatewayRequest)} 实现
     *
     * @throws GatewayException 抛出网关异常
     */
    void open() throws GatewayException;

    /**
     * 执行器执行一个内部调用HTTP请求.
     *
     * @param url               请求URI
     * @param httpMethod        请求方法
     * @param requestCallback   请求回调器
     * @param responseExtractor 响应提取器
     * @param <T>               响应实体类型
     * @return 如果响应实体
     * @throws GatewayException 抛出网关异常
     */
    <T> T execute(URI url, HttpMethod httpMethod, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws GatewayException;

    /**
     * 执行器执行任务之后执行关闭操作,详细看 {@link GatewayClient#session(Class, GatewayDefine, GatewayRequest)},{@link GatewayClient#session(GatewayDefine, GatewayRequest)} 实现
     *
     * @throws GatewayException 抛出网关异常
     */
    void close() throws GatewayException;

    /**
     * 获取响应错误处理器
     *
     * @return 返回响应错误处理器
     */
    ResponseErrorHandler getResponseErrorHandler();

    /**
     * 设置响应错误处理器
     *
     * @param responseErrorHandler 响应错误处理器
     * @return 返回网关执行器
     */
    GatewayExecutor setResponseErrorHandler(ResponseErrorHandler responseErrorHandler);
}
