package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.*;
import org.springframework.http.client.support.InterceptingHttpAccessor;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.net.URI;

/**
 * Restful网关执行器
 */
public class RestfulExecutor extends InterceptingHttpAccessor implements GatewayExecutor {

    /**
     * HTTP响应客户端本地线程
     */
    private final ThreadLocal<ClientHttpResponse> clientHttpResponseThreadLocal = new ThreadLocal<ClientHttpResponse>();

    /**
     * 执行状态本地线程
     */
    private final ThreadLocal<Boolean> executionStatusThreadLocal = new ThreadLocal<Boolean>();

    /**
     * 委托的HTTP请求客户端
     */
    private final ClientHttpRequestFactory delegateClientHttpRequestFactory;

    /**
     * 标记是否使用了缓存
     */
    private volatile boolean buffering = false;

    /**
     * 响应错误处理器
     */
    private volatile ResponseErrorHandler responseErrorHandler;

    /**
     * 构建一个默认的Restful网关执行器
     */
    public RestfulExecutor() {
        this(new SimpleClientHttpRequestFactory(), false);
    }

    /**
     * 构建一个指定委托的HTTP请求客户端并设置是否使用缓存的Restful网关执行器
     *
     * @param delegateClientHttpRequestFactory 委托的HTTP请求客户端
     * @param isBuffering                      使用缓存
     */
    public RestfulExecutor(ClientHttpRequestFactory delegateClientHttpRequestFactory, boolean isBuffering) {
        this(delegateClientHttpRequestFactory, null, isBuffering);
    }

    /**
     * 构建一个指定委托的HTTP请求客户端和响应错误处理器并设置是否使用缓存的Restful网关执行器
     *
     * @param delegateClientHttpRequestFactory 委托的HTTP请求客户端
     * @param responseErrorHandler             响应错误处理器
     * @param isBuffering                      使用缓存
     */
    public RestfulExecutor(ClientHttpRequestFactory delegateClientHttpRequestFactory, ResponseErrorHandler responseErrorHandler, boolean isBuffering) {
        this.delegateClientHttpRequestFactory = delegateClientHttpRequestFactory;
        this.responseErrorHandler = responseErrorHandler;
        if (null == this.responseErrorHandler) {
            this.responseErrorHandler = new DefaultResponseErrorHandler();
        }
        if (isBuffering) {
            //使用缓存器包装
            this.enableBuffering();
        } else {
            //禁用缓存器包装
            this.disableBuffering();
        }
    }

    /**
     * 构建使用HttpComponents委托客户端
     *
     * @return 返回网关执行器
     */
    public static RestfulExecutor delegateClientByHttpComponents() {
        return new RestfulExecutor(new HttpComponentsClientHttpRequestFactory(), false);
    }

    /**
     * 构建使用HttpComponents异步委托客户端
     *
     * @return 返回网关执行器
     */
    public static RestfulExecutor delegateClientByHttpComponentsAsync() {
        return new RestfulExecutor(new HttpComponentsAsyncClientHttpRequestFactory(), false);
    }

    /**
     * 构建使用Netty4委托客户端
     *
     * @return 返回网关执行器
     */
    public static RestfulExecutor delegateClientByNetty4() {
        return new RestfulExecutor(new Netty4ClientHttpRequestFactory(), false);
    }

    /**
     * 构建使用OkHttp委托客户端
     *
     * @return 返回网关执行器
     */
    public static RestfulExecutor delegateClientByOkHttp() {
        return new RestfulExecutor(new OkHttpClientHttpRequestFactory(), false);
    }

    /**
     * 使用缓存器包装
     *
     * @see org.springframework.http.client.BufferingClientHttpRequestFactory
     */
    @Override
    public void enableBuffering() {
        BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(this.delegateClientHttpRequestFactory);
        this.setRequestFactory(bufferingClientHttpRequestFactory);
        this.buffering = true;
    }

    /**
     * 禁用缓存器包装
     *
     * @see org.springframework.http.client.BufferingClientHttpRequestFactory
     */
    @Override
    public void disableBuffering() {
        this.setRequestFactory(this.delegateClientHttpRequestFactory);
        this.buffering = false;
    }

    /**
     * 执行器执行任务之前执行打开操作,详细看 {@link GatewayClient#session(Class, GatewayDefine, GatewayRequest)},{@link GatewayClient#session(GatewayDefine, GatewayRequest)} 实现
     *
     * @throws GatewayException 抛出网关异常
     */
    @Override
    public void open() throws GatewayException {
        Boolean isExecution = executionStatusThreadLocal.get();
        if (null != isExecution && isExecution) {
            clientHttpResponseThreadLocal.set(null);
        }
    }

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
    @Override
    public <T> T execute(URI url, HttpMethod httpMethod, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws GatewayException {
        //设置状态为打开
        executionStatusThreadLocal.set(true);
        GatewayException gatewayException = null;
        T result = null;
        try {
            //创建请求
            ClientHttpRequest clientHttpRequest = createRequest(url, httpMethod);
            //处理请求
            requestCallback.doWithRequest(clientHttpRequest);
            //执行请求
            ClientHttpResponse clientHttpResponse = clientHttpRequest.execute();
            //设置HTTP响应客户端
            clientHttpResponseThreadLocal.set(clientHttpResponse);
            //执行响应错误处理
            executeResponseErrorHandler(clientHttpResponse);
            //提取数据
            result = responseExtractor.extractData(clientHttpResponse);
        } catch (IOException ex) {
            gatewayException = new GatewayException(ex);
        }
        //设置状态为关闭
        executionStatusThreadLocal.set(false);
        if (null != gatewayException) {
            throw gatewayException;
        }
        return result;
    }

    /**
     * 执行器执行任务之后执行关闭操作,详细看 {@link GatewayClient#session(Class, GatewayDefine, GatewayRequest)},{@link GatewayClient#session(GatewayDefine, GatewayRequest)} 实现
     *
     * @throws GatewayException 抛出网关异常
     */
    @Override
    public void close() throws GatewayException {
        ClientHttpResponse clientHttpResponse = clientHttpResponseThreadLocal.get();
        if (null != clientHttpResponse) {
            clientHttpResponse.close();
        }
        executionStatusThreadLocal.set(null);
        clientHttpResponseThreadLocal.set(null);
    }

    /**
     * 执行响应错误处理
     *
     * @param response HTTP响应客户端
     * @throws IOException 抛出IO异常
     */
    protected void executeResponseErrorHandler(ClientHttpResponse response) throws IOException {
        ResponseErrorHandler responseErrorHandler = getResponseErrorHandler();
        //是否存在错误
        boolean hasError = responseErrorHandler.hasError(response);
        if (hasError) {
            responseErrorHandler.handleError(response);
        }
    }

    /**
     * 是否缓存器包装
     *
     * @return 返回是否缓存器包装
     */
    public boolean isBuffering() {
        return buffering;
    }

    /**
     * 获取响应错误处理器
     *
     * @return 返回响应错误处理器
     */
    @Override
    public ResponseErrorHandler getResponseErrorHandler() {
        return responseErrorHandler;
    }

    /**
     * 设置响应错误处理器
     *
     * @param responseErrorHandler 响应错误处理器
     * @return 返回网关执行器
     */
    @Override
    public RestfulExecutor setResponseErrorHandler(ResponseErrorHandler responseErrorHandler) {
        this.responseErrorHandler = responseErrorHandler;
        return this;
    }

}
