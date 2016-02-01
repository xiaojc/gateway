package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.components.*;
import com.ohayoyo.gateway.client.core.*;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;

import java.net.URI;

/**
 * Restful网关客户端
 */
public class RestfulClient implements GatewayClient {

    /**
     * 网关配置
     */
    private GatewayConfig gatewayConfig;

    /**
     * 网关执行器
     */
    private GatewayExecutor gatewayExecutor;

    /**
     * 构建一个默认配置的Restful网关客户端
     */
    public RestfulClient() {
        this(null, null);
    }

    /**
     * 构建一个指定网关配置的Restful网关客户端
     *
     * @param gatewayConfig 网关配置
     */
    public RestfulClient(GatewayConfig gatewayConfig) {
        this(gatewayConfig, null);
    }

    /**
     * 构建一个指定网关执行器的Restful网关客户端
     *
     * @param gatewayExecutor 网关执行器
     */
    public RestfulClient(GatewayExecutor gatewayExecutor) {
        this(null, gatewayExecutor);
    }

    /**
     * 构建一个指定网关配置和网关执行器的Restful网关客户端
     *
     * @param gatewayConfig   网关配置
     * @param gatewayExecutor 网关执行器
     */
    public RestfulClient(GatewayConfig gatewayConfig, GatewayExecutor gatewayExecutor) {
        this.gatewayConfig = gatewayConfig;
        this.gatewayExecutor = gatewayExecutor;
        if (null == this.gatewayConfig) {
            this.gatewayConfig = new RestfulConfig();
        }
        if (null == this.gatewayExecutor) {
            this.gatewayExecutor = new RestfulExecutor();
        }
    }

    /**
     * 通过网关客户端进行一次会话,使用默认响应类型为字符串
     *
     * @param gatewayDefine  网关定义
     * @param gatewayRequest 网关请求
     * @return 返回网关响应
     * @throws GatewayException 抛出网关异常
     */
    @Override
    public GatewayResponse<String> session(GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException {
        return session(String.class, gatewayDefine, gatewayRequest);
    }

    /**
     * 通过网关客户端进行一次会话
     *
     * @param responseType   响应实体类型
     * @param gatewayDefine  网关定义
     * @param gatewayRequest 网关请求
     * @param <T>            响应实体类型
     * @return 返回网关响应
     * @throws GatewayException 抛出网关异常
     */
    @Override
    public <T> GatewayResponse<T> session(Class<T> responseType, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException {
        //声明网关响应对象
        GatewayResponse<T> gatewayResponse = null;
        //执行器执行之前打开操作
        gatewayExecutor.open();
        try {
            //获取网关配置
            GatewayConfig gatewayConfig = this.getGatewayConfig();
            //创建URI组件
            URI uri = createUri(gatewayConfig, gatewayDefine, gatewayRequest);
            //创建HTTP方法组件
            HttpMethod httpMethod = createHttpMethod(gatewayConfig, gatewayDefine, gatewayRequest);
            //创建网关请求回调器组件
            GatewayRequestCallback requestCallback = createRequestCallback(gatewayConfig, gatewayDefine, gatewayRequest);
            //创建网关响应提取器组件
            GatewayResponseExtractor<T> responseExtractor = createResponseExtractor(responseType, gatewayConfig, gatewayDefine, gatewayRequest,httpMethod);
            //执行器执行一个内部调用HTTP请求
            T responseResult = gatewayExecutor.execute(uri, httpMethod, requestCallback, responseExtractor);
            //创建网关包装器组件
            GatewayWrapper<T> gatewayWrapper = createGatewayWrapper(responseType, responseResult, gatewayConfig, gatewayDefine, gatewayRequest, requestCallback.getClientHttpRequest(), responseExtractor.getClientHttpResponse());
            //包装网关响应
            gatewayResponse = gatewayWrapper.wrapGatewayResponse();
        } catch (Exception ex) {
            throw new GatewayException(ex.getMessage());
        } finally {
            gatewayExecutor.close(); //执行器执行之后打开操作
        }
        return gatewayResponse;
    }

    /**
     * 编译网关组件,为组件注入客户端所关联的引用
     * <p>
     * 注入对象
     * <ul>
     * <li>网关配置</li>
     * <li>网关定义</li>
     * <li>网关请求</li>
     * <p>
     * </ul>
     * </p>
     *
     * @param component      网关组件
     * @param gatewayConfig  网关配置
     * @param gatewayDefine  网关定义
     * @param gatewayRequest 网关请求
     * @param <Component>    组件
     * @return 返回网关组件
     */
    protected <Component> GatewayComponent<Component> compileGatewayComponent(GatewayComponent<Component> component, GatewayConfig gatewayConfig, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) {
        return component.setGatewayConfig(gatewayConfig).setGatewayDefine(gatewayDefine).setGatewayRequest(gatewayRequest);
    }

    /**
     * 创建URI组件
     *
     * @param gatewayConfig  网关配置
     * @param gatewayDefine  网关定义
     * @param gatewayRequest 网关请求
     * @return 返回URI组件
     * @throws GatewayException 抛出网关异常
     */
    protected URI createUri(GatewayConfig gatewayConfig, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException {
        return compileGatewayComponent(new GatewayUri(), gatewayConfig, gatewayDefine, gatewayRequest).getComponent();
    }

    /**
     * 创建HTTP方法组件
     *
     * @param gatewayConfig  网关配置
     * @param gatewayDefine  网关定义
     * @param gatewayRequest 网关请求
     * @return 返回HTTP方法
     * @throws GatewayException 抛出网关异常
     */
    protected HttpMethod createHttpMethod(GatewayConfig gatewayConfig, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException {
        return compileGatewayComponent(new GatewayHttpMethod(), gatewayConfig, gatewayDefine, gatewayRequest).getComponent();
    }

    /**
     * 创建网关请求回调器组件
     *
     * @param gatewayConfig  网关配置
     * @param gatewayDefine  网关定义
     * @param gatewayRequest 网关请求
     * @return 返回网关请求回调器组件
     * @throws GatewayException 抛出网关异常
     */
    protected GatewayRequestCallback createRequestCallback(GatewayConfig gatewayConfig, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException {
        return (GatewayRequestCallback) compileGatewayComponent(new GatewayRequestCallback(), gatewayConfig, gatewayDefine, gatewayRequest).getComponent();
    }

    /**
     * 创建网关响应提取器组件
     *
     * @param responseType   响应实体类型
     * @param gatewayConfig  网关配置
     * @param gatewayDefine  网关定义
     * @param gatewayRequest 网关请求
     * @param <T>            响应实体类型
     * @return 返回网关响应提取器组件
     * @throws GatewayException 抛出网关异常
     */
    protected <T> GatewayResponseExtractor<T> createResponseExtractor(Class<T> responseType, GatewayConfig gatewayConfig, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest,HttpMethod httpMethod) throws GatewayException {
        return (GatewayResponseExtractor<T>) compileGatewayComponent(new GatewayResponseExtractor<T>(responseType,httpMethod), gatewayConfig, gatewayDefine, gatewayRequest).getComponent();
    }

    /**
     * 创建网关包装器组件
     *
     * @param responseType       响应实体类型
     * @param responseResult     响应结果
     * @param gatewayConfig      网关配置
     * @param gatewayDefine      网关定义
     * @param gatewayRequest     网关请求
     * @param clientHttpRequest  HTTP请求客户端
     * @param clientHttpResponse HTTP响应客户端
     * @param <T>                响应实体类型
     * @return 返回网关包装器组件
     * @throws GatewayException 抛出网关异常
     */
    private <T> GatewayWrapper<T> createGatewayWrapper(Class<T> responseType, T responseResult, GatewayConfig gatewayConfig, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest, ClientHttpRequest clientHttpRequest, ClientHttpResponse clientHttpResponse) throws GatewayException {
        return compileGatewayComponent(new GatewayWrapperEntity<T>(responseType, responseResult, clientHttpRequest, clientHttpResponse), gatewayConfig, gatewayDefine, gatewayRequest).getComponent();
    }

    /**
     * 获取网关配置
     *
     * @return 返回网关配置
     */
    @Override
    public GatewayConfig getGatewayConfig() {
        return gatewayConfig;
    }

    /**
     * 设置网关配置
     *
     * @param gatewayConfig 网关配置
     * @return 返回网关客户端
     */
    @Override
    public RestfulClient setGatewayConfig(GatewayConfig gatewayConfig) {
        this.gatewayConfig = gatewayConfig;
        return this;
    }

    /**
     * 获取网关执行器
     *
     * @return 返回网关执行器
     */
    @Override
    public GatewayExecutor getGatewayExecutor() {
        return gatewayExecutor;
    }

    /**
     * 设置网关执行器
     *
     * @param gatewayExecutor 网关执行器
     * @return 返回网关客户端
     */
    @Override
    public RestfulClient setGatewayExecutor(GatewayExecutor gatewayExecutor) {
        this.gatewayExecutor = gatewayExecutor;
        return this;
    }

}
